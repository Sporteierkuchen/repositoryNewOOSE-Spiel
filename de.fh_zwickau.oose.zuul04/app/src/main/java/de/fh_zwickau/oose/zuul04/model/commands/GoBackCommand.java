package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

/**
 * Dies ist die Dokumentation der Klasse GoBackCommand. Die Klasse sorgt dafür, dass
 * der Spieler in Räume zurückgehen kann, wo er schon mal war.
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 * @version 1.0
 */
public class GoBackCommand extends Command {

    /**
     * Konstruktor
     */

    public GoBackCommand(){

    }
    /**
     * Führe dss Kommando aus. Liefert als Ergebnis einen booleschen Wert, der aussagt,
     * ob das Spiel beendet werden soll.
     *
     * @param player
     * @return True, wenn das Spiel beendet werden soll, andernfalls false.
     */
    @Override
    public boolean execute(Player player) {
        if (hasSecondWord()){

        	try {
        	          int rueckschritte = Integer.parseInt(getSecondWord());
        	          player.goBack(rueckschritte);
        		}
        		catch(NumberFormatException e) {
        			 System.out.println();
        			 System.out.println("Bitte gebe eine positive Zahl ein!");
        		}

        }
        else {
            System.out.println("Wie oft willst du zurückgehen? Benutze \"zurück\" <Rückwärtsschritte>. " );
        }
        return false;
    }

    /**
     *
     * @return gibt den Hilfetext für das Kommando "zurück" zurück.
     *
     */
    public String getHelp(){
        return "benutzen Sie \"zurück\" um durch die Räume zu navigieren, hierbei fügen sie die Anzahl an Räume, die sie zurückgehen wollen, an das Kommandowort an";
    }
}
