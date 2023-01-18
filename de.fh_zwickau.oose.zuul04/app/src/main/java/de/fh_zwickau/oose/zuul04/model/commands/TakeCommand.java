package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

public class TakeCommand extends Command {

	public TakeCommand(){
		
    }

    /** 
     * 
     * Versuche, ein Item zu nehmen.
     * 
     * 
     * 
     * @return immer false.
     */
       
    @Override
	public boolean execute(Player player)
    {
        if(hasSecondWord()) {
            String item = getSecondWord();
           //player.pickUpItem(item);
        }
        else {
        	 // Wenn kein zweites Wort eingegeben wurde, haben wir keine Ahnung, was der Spieler für ein Item nehmen will:
            System.out.println("Welches Item willst du nehmen? Benutze \"nehme\" <Item>.");
        }
        return false;
    }

    /**
     *
     * @return gibt den Hilfetext für das Kommando "nehme" zurück.
     *
     */
    public String getHelp(){
        return "benutzen Sie \"nehme\" um Items in den Räumen aufzuheben, hierbei fügen sie den Namen des Items, dass sie aufheben wollen an das Kommandowort an";
    }
	
}
