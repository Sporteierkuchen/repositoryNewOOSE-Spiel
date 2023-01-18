package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;

public class UseItems_Command extends Command {
    /**
     * Führe dss Kommando aus. Liefert als Ergebnis einen booleschen Wert, der aussagt,
     * ob das Spiel beendet werden soll.
     *
     * @param player
     * @return True, wenn das Spiel beendet werden soll, andernfalls false.
     */
    @Override
    public boolean execute(Player player) {
        if (hasSecondWord()) {
            //player.useItem(getSecondWord());
        } else {
            System.out.println("Bitte geben sie das Item ein, welches sie verwenden wollen");
        }
        return false;
    }

    @Override
    public String getHelp() {
        return null;
    }
}

