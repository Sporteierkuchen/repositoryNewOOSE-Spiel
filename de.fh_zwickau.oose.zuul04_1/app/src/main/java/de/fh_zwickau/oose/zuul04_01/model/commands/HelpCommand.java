package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

/**
 * Implementation des Kommandos "hilfe"
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */
public class HelpCommand extends Command
{
    private final CommandWords commandWords;
    String Anleitung;
    /**
     * Konstruktor  
     * @param words eine Instanz der Klasse CommandWords.
     */
    public HelpCommand(CommandWords words)
    {
        commandWords = words; // Dadurch weiß die HelpCommand-Klasse,
                              // welche Kommandos es gibt.
    }


    /**
     *
     * @return gibt den Hilfetext für das Kommando "hilfe" zurück.
     *
     */
    public String getHelp(){
        return "zeigt den Hilfetext der entsprechenden Kommandos an";
    }

    
    /**
     * Gibt einen kurzen Hilfetext und alle gültigen Kommandoworte aus.
     * @return immer false.
     */
    @Override
	public boolean execute(Player player)
    {
        if(hasSecondWord()) {
            String word = getSecondWord();

           Command command = commandWords.get(word);

               Anleitung= command.getHelp();


            System.out.println("Anleitung zum Kommandowort "+ "'"+ getSecondWord() +"': " + Anleitung );
        }
        else {
            System.out.println("....");
            System.out.println("Ohne zu wissen wie du dort hingelangtest, wachst du plötzlich auf einer kalten Lichtung auf");
            System.out.println();
            System.out.println("Mögliche Kommandos sind:");
            commandWords.showAll();

        }
        return false;
    }
}
