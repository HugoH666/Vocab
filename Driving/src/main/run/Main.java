package main.run;

import main.liste.Drivingliste;
import main.liste.Liste;
import main.liste.ShoppingListe;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Liste des différents thèmes
        final String[] themes = new String[]{"Driving", "Shopping"};

        Liste ll = null; // On initialise une liste null
        Random random = new Random();
        Scanner input = new Scanner(System.in); // Pour le question-reponse

        // On demande de choisir un thème et on boucle tant que la réponse reçue est mauvaise
        while (ll == null) {
            StringBuilder themeChooser = new StringBuilder("Choose a theme among: ");
            for (int i = 0; i < themes.length - 1; i++) {
                themeChooser.append(themes[i]).append(", ");
            }
            themeChooser.append(themes[themes.length - 1]).append(".");
            System.out.println(themeChooser);
            String choosenTheme = input.nextLine();

            switch (choosenTheme) {
                case "Driving": ll = new Drivingliste(); break;
                case "Shopping": ll = new ShoppingListe(); break;
                default: System.out.println("\nPlease choose a theme in the following list.");
            }
        }

        // Liste qui répertorie les différentes erreurs faites
        ArrayList<String> errors = new ArrayList<>();

        int score = 0;
        int nbQuestions = 0;

        boolean goOn = true;

        while (goOn) {

            // On choit la liste française ou anglaise au hasard
            int whichList = random.nextInt(2);
            // On choisit un mot au hasard de cette liste
            int whichTransduction = random.nextInt(ll.getEnListe().size());

            if (whichList == 0) {
                // On choisit l'une des possibilités de ce mot au hasard
                int whichPossibility = random.nextInt(ll.getElementOfEnListe(whichTransduction).size());

                System.out.println("What's the french for: <" + ll.getElementOfEnListe(whichTransduction).get(whichPossibility) + "> ?    <shutdown> to stop the processes");
                String response = input.nextLine();

                // Si on écrit "shutdown", le test s'arrête
                if (response.equals("shutdown")) {
                    nbQuestions--;
                    goOn = false;
                // Si la réponse donnée fait partie des différentes possibilités
                } else if (ll.getElementOfFrListe(whichTransduction).contains(response)) {
                    System.out.println("Great !\n");
                    score++;
                // Sinon
                } else {
                    System.out.println("Wrong, the response was: " + ll.getElementOfFrListe(whichTransduction) + "\n");
                    if (!errors.contains(ll.getElementOfEnListe(whichTransduction).get(whichPossibility))) {
                        errors.add("<En>" + ll.getElementOfEnListe(whichTransduction));
                    }
                }

                // On arrête le test lorsque 20 questions ont été posées
                if (nbQuestions >= 19) {
                    goOn = false;
                }
            } else {
                // On choisit l'une des possibilités de ce mot au hasard
                int whichPossibility = random.nextInt(ll.getElementOfFrListe(whichTransduction).size());

                System.out.println("What's the english for: <" + ll.getElementOfFrListe(whichTransduction).get(whichPossibility) + "> ?    <shutdown> to stop the processes");
                String response = input.nextLine();

                // Si on écrit "shutdown", le test s'arrête
                if (response.equals("shutdown")) {
                    nbQuestions--;
                    goOn = false;
                // Si la réponse donnée fait partie des différentes possibilités
                } else if (ll.getElementOfEnListe(whichTransduction).contains(response)) {
                    System.out.println("Great !\n");
                    score++;
                // Sinon
                } else {
                    System.out.println("Wrong, the response was: " + ll.getElementOfEnListe(whichTransduction) + "\n");
                    if (!errors.contains(ll.getElementOfFrListe(whichTransduction).get(whichPossibility))) {
                        errors.add("<Fr>" + ll.getElementOfFrListe(whichTransduction));
                    }
                }

                // On arrête le test lorsque 20 questions ont été posées
                if (nbQuestions >= 19) {
                    goOn = false;
                }
            }

            nbQuestions++;
        }

        System.out.println("\nTEST ENDED HERE\n");

        // Si on n'a répondu à aucune question
        if (nbQuestions == 0) {
            System.out.println("You haven't answered any question.");
        // Sinon on affiche le score et les mots qui ont posé problème
        } else {
            System.out.println("Your score: " + score + "/" + nbQuestions);
            if (!errors.isEmpty()) {
                StringBuilder errorsReview = new StringBuilder("You've got errors on: ");
                for (int i = 0; i < errors.size() - 1; i++) {
                    errorsReview.append(errors.get(i)).append(", ");
                }
                errorsReview.append(errors.get(errors.size() - 1)).append(".");
                System.out.println(errorsReview);
            }
        }
    }
}
