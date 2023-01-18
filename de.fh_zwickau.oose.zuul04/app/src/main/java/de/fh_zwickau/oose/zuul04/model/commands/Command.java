package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

/**
 * 
 * Diese Klasse ist eine abstrakte Oberklasse für alle Kommandoklassen des Spiels.
 * Jedes Kommando, das der Spieler eingeben kann, wird als Unterklasse hiervon implementiert.
 * 
 * Objekte der Klasse Command können ein optionales Objektwort (Attribut secondWord) enthalten,
 * wenn bei der Eingabe in der Kommandozeile ein zweites Wort eingegeben wurde.
 * Besteht das Kommando aus nur einem Wort, ist das Objektwort <null>.
 *
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */

public abstract class Command
{
    private String secondWord;

    /**
     * Konstruktor: Erzeuge ein Command-Objekt.
     */
    public Command()
    {
        secondWord = null;
    }

    /**
     * Liefert das zweite Wort des Kommandos (<null>, wenn kein zweites Wort eingegeben wurde).
     * @return Das zweite Wort des Kommandos (Rückgabe ist <null>, wenn kein zweites Wort eingegeben wurde).
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return true, wenn das Kommando ein zweites Wort hat.
     */
    public boolean hasSecondWord()
    {
        return secondWord != null;
    }

    /**
     * setze das zweite Wort des Kommandos (Attribut secondWord)
     * ein <null>-Wert zeigt an, dass kein zweites Wort eingegeben wurde.
     */
    public void setSecondWord(String secondWord)
    {
        this.secondWord = secondWord;
    }

    /**
     * Führe das Kommando aus. Liefert als Ergebnis einen booleschen Wert, der aussagt,
     * ob das Spiel beendet werden soll.
     * 
     * @return true, wenn das Spiel beendet werden soll, andernfalls false.
     */
    public abstract boolean execute(Player player);


    public abstract String getHelp();
}

