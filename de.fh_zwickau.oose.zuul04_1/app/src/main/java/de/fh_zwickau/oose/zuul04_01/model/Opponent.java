package de.fh_zwickau.oose.zuul04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.fh_zwickau.oose.zuul04.model.items.Item;

public class Opponent {
    /**
     * Leben des Gegners
     */
    public int opponentHp;
    /**
     * Name des Gegners
     */
    public String opponentName;
    /**
     * Schaden welcher der Gegner anrichtet
     */
    public int opponentDmg;

    private List<Item> itemlist;
    
    private int critChance;
    private int abweichung;
    
    /**
     * Konstruktor
     * @param opponentName
     * @param opponentHp
     * @param opponentDmg
     */
    public Opponent(String opponentName, int opponentHp, int opponentDmg, int critChance, int abweichung){
        this.opponentName= opponentName;
        this.opponentHp= opponentHp;
        this.opponentDmg= opponentDmg;
        this.critChance=critChance;
        this.abweichung=abweichung;
        
    	itemlist =new ArrayList<>();
    }

    /**
     *
     * @return Leben des Gegners
     */
    public int getOpponentHp() {
        return opponentHp;
    }

    /**
     * Setter für das Leben des Gegners
     * @param opponentHp
     */
    public void setOpponentHp(int opponentHp) {this.opponentHp = opponentHp;}

    /**
     *
     * @return Name des Gegners
     */
    public String getOpponentName() {
        return opponentName;
    }

    /**
     * Setter für den Namen des Gegners
     * @param opponentName
     */
    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    /**
     *
     * @return Schaden welcher der Gegner anrichtet
     */
    public int getOpponentDmg() {
        return opponentDmg;
    }

    /**
     * Setter für den Schaden welcher der Gegner anrichtet
     * @param opponentDmg
     */
    public void setOpponentDmg(int opponentDmg) {
        this.opponentDmg = opponentDmg;
    }

    /**
     * Zieht den angerichteten Schaden des Spielers vom Leben des Gegners ab
     * @param damage
     */
    public void getDamage(int damage) {
        this.opponentHp-=damage;
    }

    /**
     *
     * @return true, wenn Gegner am Leben ist false, wenn Gegner nicht mehr am Leben ist
     */
    public boolean isAlive() {

        return this.opponentHp > 0;
	
    }
    
    public void addItem(Item item) 
    {
       
    if(item!=null) {	
    this.itemlist.add(item);
    }
    }
    
    public List<Item> dropAllItems() {
    	
        List<Item> items=this.itemlist;		

        return items;
    	
    }
   
    public int calculateDamagefromAttack(){
		
    	int min=this.opponentDmg-this.abweichung;
    	if(min<1) {
    		min=1;
    	}
    	int max=this.opponentDmg+this.abweichung;
    	
    	Random ran = new Random();
    	int damage = min + ran.nextInt(max - min + 1);
    	System.out.println("damage "+damage);
    	
    	 int random = 1 + ran.nextInt(100 - 1 + 1);
    	 System.out.println("random "+random);
    	 if(random<=critChance){damage = damage*2;}
    	return damage;

    
    }
    
    public int calculateDamage(){
     
        return calculateDamagefromAttack();
        
    }
    
    
    
}
