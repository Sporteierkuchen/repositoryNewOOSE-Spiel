package de.fh_zwickau.oose.zuul04.model.commands;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Dieser Parser liest die Eingaben des Spielers und versucht, sie als Kommando
 * für das Adventure-Spiel zu interpretieren.
 * Jedesmal, wenn er aufgerufen wird, liest er eine Eingabezeile und versucht,
 * diese Eingabe als Kommando, bestehend aus ein oder zwei Wörtern, zu
 *  interpretieren. Das Kommando wird dann als Objekt der Command-Klasse
 *  zurückgeliefert.
 * 
 * Der Parser greift auf eine Menge bekannter Kommandowörter zu.
 * Die Eingaben des Benutzers werden mit dieser Liste verglichen. Wenn die Eingabe
 * keinem gültigen Kommandowort entspricht, wird ein Objekt der Klasse
 * NullCommand zurückgeliefert.
 *
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */

class Parser 
{
	 /**
     * enthält alle erlaubten Kommandowörter
     */
    private CommandWords commandwords;  

    
    /**
     * Konstruktor
     */
    public Parser() 
    {
        commandwords = new CommandWords();
    }

    public Command getCommand() 
    {
        String inputLine = "";   // In dieser Variablen wird die vom Spieler eingegebene Zeile stehen.
        String word1;
        String word2;

        System.out.print("> ");     // Zeichen ">" als Eingabeaufforderung ausgeben

        BufferedReader reader = 
            new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        }
        catch(java.io.IOException exc) {
            System.out.println ("Fehler beim Lesen von: "
                                + exc.getMessage());
        }

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if(tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // erstes eingegebenes Wort (Kommandowort)
        else
            word1 = null;
        if(tokenizer.hasMoreTokens())
            word2 = tokenizer.nextToken();      // zweites eingegebenes Wort
        else
            word2 = null;

        // Eventuell weitere folgende Wörter werden ignoriert.

        Command command = commandwords.get(word1);
        command.setSecondWord(word2);
        return command;
    }

    /**
     * Gibt eine Liste aller gültigen Kommandowörter aus.
     */
    public void showCommands()
    {
    	commandwords.showAll();
    }
}
