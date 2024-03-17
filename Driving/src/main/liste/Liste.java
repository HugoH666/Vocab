package main.liste;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Liste {

    private static ArrayList<ArrayList<String>> enListe = new ArrayList<>();
    private static ArrayList<ArrayList<String>> frListe = new ArrayList<>();

    public Liste() {
        fillEnListe();
        fillFrListe();
    }

    public ArrayList<ArrayList<String>> getEnListe() {
        return enListe;
    }

    public ArrayList<ArrayList<String>> getFrListe() {
        return frListe;
    }

    public ArrayList<String> getElementOfEnListe(int i) {
        return enListe.get(i);
    }

    public ArrayList<String> getElementOfFrListe(int i) {
        return frListe.get(i);
    }

    public static void addListToEn(String... tab) {
        ArrayList<String> sousListe = new ArrayList<>(Arrays.asList(tab));
        enListe.add(sousListe);
    }

    public static void addListToFr(String... tab) {
        ArrayList<String> sousListe = new ArrayList<>(Arrays.asList(tab));
        frListe.add(sousListe);
    }

    public abstract void fillEnListe();
    public abstract void fillFrListe();
}
