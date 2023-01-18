package de.fh_zwickau.oose.zuul04.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import de.fh_zwickau.oose.zuul04.model.items.Item;

/**
 * 
 * Die Klasse "Room" stellt einen Raum (Ort) in der Spielewelt dar.
 * Die Räume sind miteinander durch Türen verbunden.
 * In jedem Raum sind in der HashMap exits die Ausgänge in alle möglichen Richtungen
 * als Referenz auf den dann erreichten Zielraum gespeichert.
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */

public class Room 
{
	
	 /**
     * Beschreibung des Raumes
     */
    private String description;
    
    /**
     * Diese HashMap speichert die Ausgänge des Raumes.
     * Der Schlüssel ist die Bewegungsrichtung, der Eintrag dazu der bei Bewegung in diese
     * Richtung erreichte Raum.
     */
    private HashMap<String, Room> exits;
    private List<Item> itemlist;
    private Hebel hebel;
    private Chest truhe;
    private Opponent gegner;
    
    /**
     * Konstruktor der Klasse Raum.
     * Lege einen Raum mit einer Raumbeschreibung (description) an.
     * Die Raumbeschreibung ist so etwas wie
     * "der Fahrstuhl" oder "die Herrentoilette".
     * Der neu erzeugte Raum hat noch keine Ausgänge.
     * @param description Die Raumbeschreibung.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        itemlist =new ArrayList<>();
    }

    /**
     * Lege die Ausgänge des Raumes fest.
     * @param direction Bewegungsrichtung.
     * @param neighbor Der bei Bewegung in diese Richtung erreichte Zielraum.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Gib die Raumbeschreibung zurück, so wie sie im Konstruktor angegeben wurde.
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Gib eine längere Beschreibung des Raumes zurück. Beispiel-Format:
     *     Deine Position: die Herrentoilette.
     *     Ausgänge: Norden Westen.
     */
    public String getLongDescription()
    {
        return "Deine Position: " + description + ".\n" + getExitString();
    }
    
    /**
     * Gib einen String zurück, der die Ausgänge des Raumes beschreibt.
     * Beispiel:
     * Ausgänge: Norden Westen.
     */
    private String getExitString()
    {
        StringBuilder returnString = new StringBuilder("Ausgänge:");
        Set<String> keys = exits.keySet();
        for (String key : keys) returnString.append(" ").append(key);

        //for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
         //   returnString += " " + iter.next();

        if(keys.isEmpty()) {
        return returnString.toString()+" Keine";
        }
        
        return returnString.toString();
    }

   
    
    /**
     * Gib den Raum zurück, der bei Bewegung in eine Richtung erreicht wird
     * @param direction Bewegungsrichtung.
     * @return erreichter Zielraum, <null>, wenn es in die angegebene Richtung keinen Ausgang gibt.
     */
    public Optional<Room> getExit(String direction) 
    {

        return Optional.ofNullable (exits. get(direction));
       // Optional<Room > optionalRoom =Optional.ofNullable (exits. get(direction));
        // return optionalRoom;

    }

    public void addItem(Item item) 
    {
       
    if(item!=null) {	
    this.itemlist.add(item);
    }
    }
    
    public void removeItem(Item item) {
        itemlist.removeIf(i -> i.equals(item));  
    }
    
    public Optional<Item> getItem(int index) {
    	 
        	try {
        	    return Optional.of(itemlist.get(index));
        	}
        	catch(IndexOutOfBoundsException e) {
        		return Optional.empty();
        	}
   
	}

    /**
     * Gib eine längere Beschreibung des Raumes zurück. Beispiel-Format:
     *     Deine Position: die Herrentoilette.
     *     Ausgänge: Norden Westen.
     */
    public String getItemsinRoom()
    {
        
    	String returnString = "Items in diesem Raum:";
      
        for(Item item : this.itemlist) {
        	
        	returnString += " " + item.getName();
         
        }
        if(this.itemlist.isEmpty()) {
        	
        	return returnString += " Keine" ;
        	
        }
        
        return returnString;
    }

    
    public List<String> getItemNamesinRoom()
    {
        
    	List<String> itemnames= new ArrayList<>();
      
        for(Item item : this.itemlist) {
            itemnames.add(item.getName());
        }
        
        return itemnames;
    }
    
    
    /**
     *
     * @return true, wenn Hebel vorhanden ist false, wenn kein Schalter vorhanden ist
     */
    public Optional<Hebel> getHebel() {

        return Optional.ofNullable (this.hebel);
    	
    }

    /**
     *
     * @return Zustand des Hebels
     */
    public boolean leverAvailable() {
        if (getHebel().isPresent()) {
            return !getHebel().get().getZustand();

            // if (!getSchalter().get().getZustand()) {
             //   return true;}
        }
        
        return false;
    }

    /**
     * Setter für Hebel
     * @param hebel
     */
    public void setHebel(Hebel hebel) {
    	
    	if(hebel!=null) {	
    	  this.hebel = hebel;
    	  }	
        
    }

    /**
     *
     * @return true, wenn kein Item im Raum vorhanden ist false, wenn ein Item im Raum vorhanden ist
     */
    public boolean noItemsinRoom() {
    	
    return this.itemlist.isEmpty();
    	
    }

    /**
     *
     * @return true, wenn Truhe vorhanden ist false, wenn keine Truhe vorhanden ist
     */
    public Optional<Chest> getChest() {

        return Optional.ofNullable (this.truhe);	
    }

    /**
     * Setter für Truhe
     * @param chest
     */
    public void setChest(Chest chest) {
    	
    	if(chest!=null) {	
    		this.truhe=chest;
      	  }	
    	     
    }

    /**
     *
     * @return true, wenn Gegner vorhanden false, wenn kein Gegner vorhanden ist
     */
	public Optional<Opponent> getOptionalGegner() {

        return Optional.ofNullable (this.gegner);
    	
	}

	public Opponent getGegner() {

        return this.gegner;
    	
	}
	
	
	
    /**
     * Setter für Gegner
     * @param gegner
     */
	public void setGegner(Opponent gegner) {
		
    	if(gegner!=null) {	
    		this.gegner = gegner;
      	  }	
		
	}

    /**
     *
     * @return true, wenn ein Gegner vorhanden ist false, wenn kein Gegner vorhanden ist
     */
    public boolean gegnerVorhanden() {
    return getOptionalGegner().isPresent();
}

    
    
    /**
     * Überprüft, ob ein Gegner noch am Leben ist und gibt seine verbleibenden Lebenspunkte aus
     */
    public String checkOpponent() {

if(gegnerVorhanden() && !this.gegner.isAlive())	{

System.out.println("Der Gegner "+this.gegner.getOpponentName()+" ist gefallen!");
String opponentName=this.gegner.getOpponentName();

for(Item item: this.getGegner().dropAllItems()) {
	this.itemlist.add(item);
}

this.gegner=null;

return "Der Gegner "+opponentName+" ist gefallen!";

}
else {
System.out.println("Der Gegner "+this.gegner.getOpponentName()+" hat noch "+this.gegner.getOpponentHp()+" HP.");
return "Der Gegner "+this.gegner.getOpponentName()+" hat noch "+this.gegner.getOpponentHp()+" HP.";
}

}


}