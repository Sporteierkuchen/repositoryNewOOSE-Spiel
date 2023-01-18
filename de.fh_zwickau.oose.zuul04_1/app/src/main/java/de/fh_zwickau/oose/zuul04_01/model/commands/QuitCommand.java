package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

/**
 * Implementierung des Kommandos "ende"
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */
public class QuitCommand extends Command
{
    /**
     * Konstruktor.
     */
    public QuitCommand()
    {
    }

    /**
     * "ende" wurde eingegeben.
      * @return immer true (dadurch wird das Spiel beendet).
     * 
     */
    @Override
	public boolean execute(Player player)
    {
    	return true;
    }


    /**
     *
     * @return gibt den Hilfetext für das Kommando "ende" zurück.
     *
     */
    public String getHelp(){
        return "benutzen Sie \"ende\" um das Spiel vorzeitig zu verlassen";
    }


}
