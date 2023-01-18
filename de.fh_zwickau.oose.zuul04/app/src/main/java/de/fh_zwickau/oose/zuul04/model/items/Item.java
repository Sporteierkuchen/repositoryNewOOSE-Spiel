package de.fh_zwickau.oose.zuul04.model.items;

import de.fh_zwickau.oose.zuul04.model.Player;

public abstract class Item {

    /**
     * Name des Items
     */
    private String name;
    /**
     * Beschreibung des Items
     */
    private String beschreibung;


    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public Item(String itemName, String itemBeschreibung){

        this.name = itemName;
        this.beschreibung = itemBeschreibung;

    }

    /**
     * @return Name des Items
     */
    public String getName() {
        return name;
    }

    /**
     * Setter für den Namen des Items
     * @param itemName
     */
    public void setName(String itemName) {
        this.name = itemName;
    }

    /**
     *
     * @return Beschreibung des Items
     */
    public String getDescription() {
        return beschreibung;
    }

    /**
     * Setzt eine Beschreibung für ein Item
     * @param itemBeschreibung
     */
    public void setDescription(String itemBeschreibung) {
        this.beschreibung = itemBeschreibung;
    }

    public abstract void  use();

	public abstract void use(Player player);
	
}
