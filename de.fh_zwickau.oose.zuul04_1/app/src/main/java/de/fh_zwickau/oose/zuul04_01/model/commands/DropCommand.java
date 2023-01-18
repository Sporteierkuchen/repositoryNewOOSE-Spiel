package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

/**
 * Implementierung des Kommandos "ablegen"
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 * @version 1.0
 */
public class DropCommand extends Command
{
    /**
     * Konstruktor.
     */
    public DropCommand()
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
            String item = getSecondWord();
          //  player.dropItem(item);
        }
        else {
            System.out.println("Welches Item ablegen? Benutze \"ablegen\" <Itemname>.");
        }
        return false;
    }


    /**
     *
     * @return gibt den Hilfetext für das Kommando "ablegen" zurück.
     *
     */
    public String getHelp(){
        return "benutzen Sie \"ablegen\" um Items in den Räumen abzulegen, hierbei fügen sie den Namen des Items an das Kommandowort an";
    }


}
