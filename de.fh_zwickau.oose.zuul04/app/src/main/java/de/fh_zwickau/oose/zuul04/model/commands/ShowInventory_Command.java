package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

public class ShowInventory_Command extends Command {
    /**
     * Führe dss Kommando aus. Liefert als Ergebnis einen booleschen Wert, der aussagt,
     * ob das Spiel beendet werden soll.
     *
     * @param player
     * @return True, wenn das Spiel beendet werden soll, andernfalls false.
     */
    @Override
    public boolean execute(Player player) {
        if (player.isInventoryempty()){
            System.out.println("Dein Inventar ist leer");
        }
        else {
        	player.inventarAusgeben();
        }
        return false;
    }

    /**
     *
     * @return gibt den Hilfetext für das Kommando "Inventar" zurück.
     *
     */
    public String getHelp(){
        return "zeigt dein Inventar an";
    }
}
