package de.fh_zwickau.oose.zuul04.model.commands;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Diese Klasse enthält alle im Spiel bekannten Kommandowörter.
 * Sie wird genutzt, um eingegebene Kommandos zu erkennen.
 *
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */

public class CommandWords
{
    private final HashMap<String, Command> commands;

    /**
     * Konstruktor - Kommandowörter initialisieren
     */
    public CommandWords()
    {
        commands = new HashMap<String, Command>();
        commands.put("gehe", new GoCommand());
        commands.put("hilfe", new HelpCommand(this));
        commands.put("ende", new QuitCommand());
        commands.put("zurück", new GoBackCommand());
        commands.put("Hebelziehen", new PushLeverCommand());
        commands.put("benutze", new UseItems_Command());
        commands.put("Inventar", new ShowInventory_Command());
        commands.put("Lebensanzeige", new ShowHp_Command());
        commands.put("nehme", new TakeCommand());
        commands.put("Truhe", new ChestUseCommand());
        commands.put("ablegen", new DropCommand());
      
    }

    /**
     * Liefere für ein gegebenes Kommandowort das dazugehörige Command-Objekt
     * Rückgabe ist <null>, wenn ein nicht existierendes Kommandowort eingegeben wurde.
     */
    public Command get(String word)
    {
    	// Wenn word kein gültiges Kommandowort ist, wird in der HashMap commands nichts gefunden.
    	// In diesem Falle liefere ein NullCommand-Objekt zurück.
        if(commands.get(word) == null) {
        	return new NullCommand();
        }
        // Andernfalls liefere das passende Command-Objekt zurück.
        else {
            return commands.get(word);
   	
        }
    }

    /**
     * Gib eine Liste aller existierenden Kommandos auf System.out aus.
     */
    public void showAll() 
    {
        for (String s : commands.keySet()) {
            System.out.print(s + "  ");
        }
        // for(Iterator i = commands.keySet().iterator(); i.hasNext(); ) {
        //            System.out.print(i.next() + "  ");}
        System.out.println();
    }


}
