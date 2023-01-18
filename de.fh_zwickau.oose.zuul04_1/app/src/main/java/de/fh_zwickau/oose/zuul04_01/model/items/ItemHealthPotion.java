package de.fh_zwickau.oose.zuul04.model.items;

import de.fh_zwickau.oose.zuul04.model.Player;

public class ItemHealthPotion extends Item {

    /**
     * Leben welcher der Spieler beim Benutzen des Items erhält
     */
     int hpRestore=50;

    /**
     * Konstruktor
     * @param itemName
     * @param itemBeschreibung
     */
    public ItemHealthPotion(String itemName, String itemBeschreibung) {
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
     * Methode für das Benutzen des Items
     * @param player
     */
    @Override
    public void use(Player player) {
       System.out.println("Die Energie des Heiltrankes erfüllt dich mit neuem Leben.");
       String text = "";
       text+="Die Energie des Heiltrankes erfüllt dich mit neuem Leben."+"\n";
       if(player.getHp() == player.getMaxHp()){
           System.out.println("Du hast bereits volles Leben!");
           text+="Du hast bereits volles Leben!";
       }else {
           player.addHp(hpRestore);
           if (player.getHp() > player.getMaxHp()) {
               player.setHp(player.getMaxHp());
           }
           System.out.println("Deine Trefferpunkte betragen nun:" + player.getHp() + "");
           text+="Deine Trefferpunkte betragen nun: " + player.getHp() + "HP";
           player.removeItems(this);
       }
       player.setUseItemText(text);
    }

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}
}
