package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

public class ShowHp_Command extends Command{
    /**
     * Führe dss Kommando aus. Liefert als Ergebnis einen booleschen Wert, der aussagt,
     * ob das Spiel beendet werden soll.
     *
     * @param player
     * @return True, wenn das Spiel beendet werden soll, andernfalls false.
     */
    @Override
    public boolean execute(Player player) {
        System.out.println("Deine Lebenspunkte betragen:"+ player.getHp());
        return false;
    }
    /**
     *
     * @return gibt den Hilfetext für das Kommando "Lebensanzeige" zurück.
     *
     */
    public String getHelp(){
        return "zeigt deine Lebenspunkte an!";
    }
}
