package de.fh_zwickau.oose.zuul04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.fh_zwickau.oose.zuul04.model.items.Item;

public class PlayerInventory {
    /**
     * Liste für Items
     */
	private  ArrayList<Item> items = new ArrayList<>();

    /**
     * Gibt das Inventar des Spielers mit den enthaltenen Items aus
     * @return Inventar
     */
    public String inventarAusgeben() {
        for (Item i : items) {
            System.out.println(i.getName());

        }

        return String.valueOf(items);
    }

    /**
     * Fügt ein Item der Item-Liste hinzu
     * @param item
     */
    public void addItem(Item item) {
    
    	if(item!=null) {	
    	 this.items.add(item);
    	} 
    	
    }

    /**
     * Entfernt Items
     * @param item
     */
    public void removeItems(Item item) {

        items.removeIf(i -> i.equals(item));

        //Iterator<Item> it = items.iterator();
        //while(it.hasNext()) {
        //    Item i = it.next();
        //    if(i.equals(item)) {
         //       it.remove();}}
    }

    /**
     *
     * @param secondWord
     * @return
     */
    public boolean contains(String secondWord) {
        for(Item item : items){
            if(item.getName().equals(secondWord)){
                return true;
            }
        }
        return false;
    }

    /**
     * Gibt zurück, ob das Inventar des Spielers leer ist
     * @return
     */
    public boolean isInventoryempty() {
    	
   return this.items.isEmpty();
    
    }

    /**
     * Benutzen eines Items aus dem Inventar des Spielers
     * @param itemName
     * @return
     */
    public Optional<Item> useItemfromInventory(String itemName) {
    	
        for(Item item : items){
        	
            if(item.getName().equals(itemName)){
  	
                return Optional.ofNullable (item);
            
            }
    	}

        return Optional.empty();

    }
    
public List<String> getItemNames() {
	
	List <String>itemNamesList=new ArrayList<>();
	
	for(Item item: this.items) {
		itemNamesList.add(item.getName());
	}
	return itemNamesList;
	
}
    
   public String getItemDescription(int index) {  
	   
	    try {
     	  return items.get(index).getDescription();  
     	}
     	catch(IndexOutOfBoundsException e) {
     		return null;
     	}
	   
	   
	   
   }
  
   public Optional<Item> getItem(int index) {  
	   	
	   try {
    	  return Optional.ofNullable (items.get(index));
	   }
	   catch(IndexOutOfBoundsException e) {
  		return Optional.empty();
	   }
      
   }
   
   public int getInventorySize() {  
	   
		 return items.size();
		   
   }
   
   
}
