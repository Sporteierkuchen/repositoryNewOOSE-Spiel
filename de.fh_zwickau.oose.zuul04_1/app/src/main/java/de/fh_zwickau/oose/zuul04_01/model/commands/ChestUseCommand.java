package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;
import de.fh_zwickau.oose.zuul04.model.Room;

public class ChestUseCommand extends Command {

	@Override
    public boolean execute(Player player) {
 
		if(hasSecondWord() && player.getCurrentRoom().getChest().isPresent()) {
            String activity = getSecondWord();
           
          Room room=player.getCurrentRoom();  
          
          
          if(activity.equals("öffnen")) {
          	
          room.getChest().get().openChest();
          	
          }
          else if(activity.equals("leeren")) {
          
        	 if( room.getChest().get().removeItemsFromChest().isPresent()) {
        		 
        		 player.getItemsFromChest(room.getChest().get().removeItemsFromChest().get());
        		 room.getChest().get().clearChest();
        	 }	 
        	   
          }
          else {
          	
          System.out.println("Was willst du mit der Truhe machen?");	
          System.out.println("Du kannst die Truhe öffnen oder leeren");
          
          	
          } 
          
             
        }
        else if (!hasSecondWord() && player.getCurrentRoom().getChest().isPresent()) {
        	System.out.println("Bitte gebe an,was du mit der Truhe machen willst!");
        	System.out.println("Benutze \"Truhe öffnen\" oder \"Truhe leeren\"");
        }	
        else {
        System.out.println("In diesem Raum ist keine Truhe vorhanden!");	
        }
		
        return false;
    }
    /**
     *
     * @return gibt den Hilfetext für das Kommando "Truhe" zurück.
     *
     */
    public String getHelp(){
        return "benutzen Sie Truhe um Truhen in den Räumen zu öffnen bzw. zu leeren, hierbei fügen sie den Namen der Aktivität die sie mit der Truhe vorhaben,also \"öffnen\" oder \"leeren\" an das Kommandowort an";
    }
	
}
