package de.fh_zwickau.oose.zuul04.model.items;

import de.fh_zwickau.oose.zuul04.model.Player;

public class ItemIronSword extends Item {

    /**
     * Schaden welches das Item verursacht
     */
      int damage=25;

    /**
     * Chance mit dem das Item doppelten Schaden anrichtet
     */
    int critChance=10;

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemIronSword(String itemName, String itemBeschreibung) {
        super(itemName, itemBeschreibung);
    }

    /**
     *
     * @return Name des Items
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     *
     * @return Beschreibung des Items
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     *
     * @return Schaden den das Item anrichtet
     */
    public int calculateDamage(){

        int random = ((int)(Math.random() * 100)); //Zahl zwischen 0 und 99
        int damage=this.damage;
        
        if(random<=critChance){damage = damage*2;}

        return damage;
    }







    /**
     * Methode für das Benutzen des Items
     * @param player
     */
    @Override
    public void use(Player player) {
    	
        String text = "";
       
    if(player.getOptionalGegner().isPresent()) {
    	player.getGegner().getDamage(calculateDamage());
    	System.out.println("Du hast den Gegner getroffen!");
    	 text+="Du hast den Gegner getroffen!"+"\n"+player.getCurrentRoom().checkOpponent();	
    	
    	
    }
    else {
    	System.out.println("Du schlägst mit deinem Schwert ins Leere...");
    	text+="Du schlägst mit deinem Schwert ins Leere...";	
    }
    player.setUseItemText(text);
    
    }

	@Override
	public void use() {
	
		
	}
}
