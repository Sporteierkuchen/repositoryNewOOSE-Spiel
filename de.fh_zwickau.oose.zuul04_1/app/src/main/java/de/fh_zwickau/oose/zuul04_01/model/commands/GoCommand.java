package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

/**
 * Implementierung des Kommandos "gehe"
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 * @version 1.0
 */
public class GoCommand extends Command
{
    /**
     * Konstruktor.
     */
    public GoCommand()
    {
    }

    /** 
     * 
     * Versuche, in eine Richtung zu laufen. Wenn es dort einen Ausgang
     * gibt, betritt den betreffenden Raum. Andernfalls wird eine Fehlermeldung
     * ausgegeben.
     * 
     * @return immer false.
     */
       
    @Override
	public boolean execute(Player player)
    {
        if(hasSecondWord()) {
            String direction = getSecondWord();
            player.walk(direction);
        }
        else {
        	 // Wenn kein zweites Wort eingegeben wurde, haben wir keine Ahnung, wohin der Spieler gehen will:
            System.out.println("WOHIN gehen? Benutze \"gehe\" <Richtung>.");
        }
        return false;
    }
    /**
     *
     * @return gibt den Hilfetext für das Kommando "gehe" zurück.
     *
     */
    public String getHelp(){
        return "benutzen Sie \"gehe\" um durch die Räume zu navigieren, hierbei fügen sie eine der Richtungen an das Kommandowort an";
    }
}
