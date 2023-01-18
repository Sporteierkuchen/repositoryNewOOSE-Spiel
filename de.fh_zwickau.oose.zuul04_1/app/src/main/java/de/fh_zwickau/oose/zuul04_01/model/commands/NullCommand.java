package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

/**
 * Ein Objekt der Klasse NullCommand wird erzeugt, wenn ein ungültiges
 * Kommando eingegeben wurde.
 * Das NullCommand tut bei seiner Ausführung nichts weiter, als eine Fehlermeldung auszugeben. 
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */
public class NullCommand extends Command
{
    
    /**
     * Konstruktor.
     */
    public NullCommand()
    {
        
    }
    
    /**
     * Gibt eine Fehlermeldung aus.
     * @return immer false.
     */
    @Override
	public boolean execute(Player player)
    {
        System.out.println("Ich verstehe nicht, was Du meinst... Gib \"hilfe\" ein, um eine Liste der gültigen Kommandos zu sehen.");
        return false;
    }

    @Override
    public String getHelp() {
        return ": Dieses Hilfe-Kommando gibt es nicht. Gib \"hilfe\" ein um alle Hilfe-Kommandos zu sehen";
    }
}
