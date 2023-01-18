package de.fh_zwickau.oose.zuul04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.fh_zwickau.oose.zuul04.model.items.Item;

public class Chest {

    /**
     * Liste welche die Items in einer Truhe beinhaltet.
     */

	private List<Item> truheninhalt;
    /**
     *Boolean für den Zustand einer Truhe [true = offen | false = geschlossen].
     */

	private boolean offen;

    /**
     *Konstruktor: Erzeugt ein Chest-Objekt.
     */
    public Chest() 
    {

    	truheninhalt =new ArrayList<>();
    	offen=false;
    	
    }
    /**
     * Gibt die vorhandenen Items in einer Kiste aus.
     * @return String mit den vorhandenen Items.
     */
    public String getItemsInChest()
    {
        
    	String returnString = "Items in dieser Truhe:";
      
        for(Item item : this.truheninhalt) {
            returnString += " " + item.getName();
        }
        if(this.truheninhalt.isEmpty()) {
        	
        	return returnString += " Keine" ;
        	
        }
		return returnString;
           
}
    /**
     * @param item
     * Fügt ein Item der Kiste hinzu
     */
    public void setItem(Item item) {
       
    if(item!=null) {	
        this.truheninhalt.add(item);
        }

    }
    
    public Optional<List<Item>> removeItemsFromChest()
    {
     
    List<Item> items=this.truheninhalt;		
    Optional<List<Item>> optionalItemlist =Optional.ofNullable (items);  
    
    if(isOpen()) {
    	
    return optionalItemlist;	
    	
    }
    else {
    
    System.out.println("Du musst die Kiste zunächst öffnen!");
        return Optional.empty();
        //Optional<List<Item>> optionalEmpty =Optional.empty();
        //return optionalEmpty;
    }
	
    }

    /**
     * Methode zum Öffnen einer Kiste.
     */
    public void openChest()
    {
     
    if(isOpen()) {
    	
    System.out.println("Die Kiste wurde bereits geöffnet!");
    	
    }
    else {
    
    this.offen=true;
    System.out.println("Die Kiste in diesem Raum wurde geöffnet!");
    System.out.println(getItemsInChest());
    
    }
  
    }
    /**
     * Methode zum vollständigen leeren einer Kiste.
     */

	public void clearChest() {
		this.truheninhalt.clear();
	}


    /**
     * Methode zum Überprüfen, ob eine Kiste offen ist.
     * @return true oder false
     */

	public boolean isOpen() {
		return offen;
	}

	public boolean isChestEmpty() {
		return this.truheninhalt.isEmpty();
	}
	
    /**
     * @param offen
     * Methode um eine Kiste auf offen oder geschlossen zu setzen.
     */

	public void setOpen(boolean offen) {
		this.offen = offen;
	}
	
}