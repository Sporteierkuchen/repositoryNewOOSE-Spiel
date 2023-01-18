package de.fh_zwickau.oose.zuul04.model;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

import de.fh_zwickau.oose.zuul04.model.items.Item;

/**
 * Diese Klasse steht für einen Spieler im Spiel.
 * Ein Spieler befindet sich zu jedem Zeitpunkt des Spiels in einem bestimmten Raum.
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */
public class Player
{
	 /**
     * Raum in dem sich der Spieler befindet
     */
    private Room currentRoom;
    
    /**
     * Liste der Räume, in denen der Spieler schon war
     */
    private Stack<Room> roomhistory;
    /**
     * aktuelle Leben des Spielers
     */
    private int hp;
    /**
     * maximale Leben des Spielers
     */
    private int maxHp;
    /**
     * Anzahl der aktivierten Schalter
     */
    private int pressedLever;
    
    private int openedChests;
    
    private int kills;
    
    /**
     * Inventar des Spielers
     */
    private PlayerInventory inventar;

private String useItemText;
    
    /**
     * Konstruktor für die Player-Klasse.
     */
    public Player()
    {
        currentRoom = null;
        roomhistory = new Stack<>();
        hp = 100;
        maxHp=100;
        pressedLever=0;
        inventar = new PlayerInventory();
   
     //  inventar.addItem (new Item_IronSword("Eisenschwert","Dieses Schwert hat eine ziemlich scharfe Klinge..."));
        
    }

    /**
     * Liefert den aktuellen Raum des Spielers zurück.
     */
    public  Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Setzt den aktuellen Raum des Spielers.
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Versuche, in eine bestimmte Richtung zu laufen.
     * Wenn dort eine Tür ist, wird sich dadurch der aktuelle Raum des Spielers ändern.
     */
    public void walk(String direction)
    {
        // Try to leave current room.
    	Optional<Room> whereToGo = currentRoom.getExit(direction);

    	  if (whereToGo.isPresent()) {
          	
    		 Room room=whereToGo.get();
    		  
         	 setCurrentRoom(room);
             roomhistory.push(room);
             System.out.println(room.getLongDescription());
             System.out.println(room.getItemsinRoom()); 
         } 
         else {
   
             System.out.println("Da ist keine Tür!");
             
         }
    
    }



	/**
     * geht die jeweilige Anzahl an Räumen zurück.
     * @param rueckschritte
     * @return
     */
    public String goBack(int rueckschritte){


            if (roomhistory.size() == 1) {
            	System.out.println();
            	System.out.println("Du befindest dich im Startraum und kannst nicht weiter zurück gehen!");
                return String.valueOf(currentRoom);
            }
            else if(roomhistory.size()<=rueckschritte) {
            System.out.println();	
            System.out.println("Du kannst nicht "+rueckschritte+" Schritte zurück gehen!"); 
            int maximaleschritte=roomhistory.size()-1;
            System.out.println("Du kannst höchstens "+maximaleschritte +" Schritte zurück gehen!");
            return String.valueOf(currentRoom);
            
            }
            else if(rueckschritte<0) {
            System.out.println();	
            System.out.println("Bitte gebe eine positive Zahl ein!");	
            return String.valueOf(currentRoom);
            }
            else {
           	
            for(int a=0; a<rueckschritte; a++) {
            	roomhistory.pop();	
            }
            	
                setCurrentRoom(roomhistory.peek());
                System.out.println(currentRoom.getLongDescription());
                return String.valueOf(currentRoom);

            }
         
    }

    /**
     * Methode zum Benutzen von Items des Spielers
     * @param itemName
     */

    public void useItem(int index){
      
   if(this.inventar.getItem(index).isPresent()) {
	   
	   this.inventar.getItem(index).get().use(this);   
	   
   }
   else {
	 
	   System.out.println("Das Item ist nicht in deinem Inventar vorhanden!");   
	   
   }
    	
    }

    public String getItemDescription(int index){
        
    	return this.inventar.getItemDescription(index);

    }
    
    
    /**
     * Methode um Items aus einer Kiste dem Inventar des Spielers hinzuzufügen
     * @param list
     */
    public void getItemsFromChest(List<Item> list){

    	if(list.isEmpty()) {
    		
    	System.out.println("Die Truhe ist leer!");
    		
    	}
    	else {
    		
    	for(Item item : list) {
    	   
    	this.inventar.addItem(item);	
 				
 		 }	
    	
    	System.out.println("Du hast dir die Items aus der Truhe genommen!");
    	
    	}
    	
    }

    /**
     * Methode zum Aufnehmen von Items in einen Raum
     * @param itemName
     */
    public void pickUpItem(int index){

    if(this.currentRoom.noItemsinRoom())	{
    	
    	System.out.println("In diesem Raum liegen keine Items herum!");
    
    }
    else {
    	
    Optional<Item > optionalItem=this.currentRoom.getItem(index);
    if(optionalItem.isPresent()) {
    	
    	Item item=optionalItem.get();
    	this.inventar.addItem(item);
    	this.currentRoom.removeItem(item);
    	System.out.println("Du hast das Item "+item.getName()+" aufgenommen!");
    	
    }
    else {
    System.out.println("Das Item ist in diesem Raum nicht vorhanden!");
    }
    
    }
    	
    }

    /**
     * Methode zum Ablegen eines Items in einen Raum
     * @param itemName
     */
    public void dropItem(int index){

    	if(inventar.isInventoryempty()) {
	
    	    System.out.println("Dein Inventar ist leer!");		
	
    	}
    	else if(inventar.getItem(index).isPresent()) {
    		
    	Item item=inventar.getItem(index).get();
    	this.currentRoom.addItem(item);
    	this.inventar.removeItems(item);
    	System.out.println(item.getName() +" wurde abgelegt!");
    	
    	}
    	else{
    	
    		 System.out.println("Das Item ist nicht in deinem Inventar vorhanden!");
    		
    	}
    		
    }

    public boolean isInventoryempty() {
    	
    	return this.inventar.isInventoryempty();
     	
    }
    
    public String inventarAusgeben() {
		
    	return this.inventar.inventarAusgeben();
    		
    }
    
    public void removeItems(Item item) {
    	
    this.inventar.removeItems(item);	
    	
    }
    
    public boolean contains(String secondWord) {
		
    	return this.inventar.contains(secondWord);
    		
    }
    
    public void addItem(Item item) {
    	
    this.inventar.addItem(item);	
    	
    }
    
    public List<String> getItemNames() {
    	
     return this.inventar.getItemNames();	
        	
    }
    
    /**
     *
     * @param startraum
     */
    public void setStartraumtoRoomHistory(Room startraum) {
		
		this.roomhistory.push(startraum);
		
	}

    public int getRoomhistorySize() {
    	
    	return this.roomhistory.size();
    }
    
    /**
     *
     * @return gibt die aktuellen Lebenspunkte des Spielers zurück
     */
	public int getHp() {
		return hp;
	}

    /**
     * Setter für das Leben des Spielers
     * @param hp
     */
    public void setHp(int hp){this.hp=hp;}

    /**
     * Fügt dem Spieler Lebenspunkte hinzu
     * @param hp
     */
	public void addHp(int hp) {
		this.hp+=hp;
	}

    /**
     *
     * @return maximale Leben des Spielers
     */
    public int getMaxHp(){return maxHp;}

    /**
     * Setter für das maximale Leben des Spielers
      * @param maxHp
     */
    public void setMaxHp(int maxHp){this.maxHp=maxHp;}


    /**
     * Gibt die Anzahl der bereits umgelegten Schalter zurück
     * @return pressedLever
     */
	public int getPressedLever() {
		return this.pressedLever;
	}

    /**
     * Erhöht die Anzahl der umgelegten Schalter um 1
     */
	public void pressLever() {
		this.pressedLever++;
	}

	public void openchest() {
		this.openedChests++;
	}
	
	public void kill() {
		this.kills++;
	}
	
	public Opponent getGegner() {

        return this.currentRoom.getGegner();
    	
	}
	
	public Optional<Opponent> getOptionalGegner() {

        return this.currentRoom.getOptionalGegner();
    	
	}
	
	public void setUseItemText(String text) {

      this.useItemText=text;
    	
	}
	
	public String getUseItemText() {

	      return this.useItemText;
	    	
		}
	
	public int getAnzahlItems() {

	      return inventar.getInventorySize();
	    	
	}
	
    /**
     *
     */
    public void getDamaged(int damage){

    this.hp-=damage;	
    }

    public boolean isAlive() {

        return this.hp > 0;
	
    }

	public int getOpenedChests() {
		return openedChests;
	}

	public int getKills() {
		return kills;
	}
    
    

}
