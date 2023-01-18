package de.fh_zwickau.oose.zuul04.model;

public class Hebel {
    /**
     * Zustand des Hebels, am Anfang ausgeschaltet
     */
    boolean zustand = false;

    /**
     * Konstruktor
     */
    public Hebel() {

    }

    /**
     *
     * @return Zustand des Hebels
     */
    public  boolean getZustand(){
        return zustand;
    }


    /**
     * ändert den Zustand des Hebels
     */
    public void einschalten(){
        zustand = true;
    }
}
