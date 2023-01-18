package de.fh_zwickau.oose.zuul04.model.commands;

import de.fh_zwickau.oose.zuul04.model.Player;
import de.fh_zwickau.oose.zuul04.model.Room;

public class PushLeverCommand extends Command {

    public PushLeverCommand() {

    }

    /**
     * Führe dss Kommando aus. Liefert als Ergebnis einen booleschen Wert, der aussagt,
     * ob das Spiel beendet werden soll.
     *
     * @param player
     * @return True, wenn das Spiel beendet werden soll, andernfalls false.
     */
    @Override
    public boolean execute(Player player) {
        Room room = player.getCurrentRoom();


        if (room.leverAvailable() && room.getHebel().isPresent()) {
            System.out.println("Du zögerst leicht.... ziehst aber dennoch an dem verrosteten Schalter... in der Ferne hörst du es krachen  und der Hebel verschwindet im Boden");
            room.getHebel().get().einschalten();
            player.pressLever();
            
        } else {
            System.out.println("In dem Raum gibt es keinen Hebel oder er ist bereits verschwunden.... wer kann das schon sagen");
        }


        return false;
    }

    /**
     *
     * @return gibt den Hilfetext für das Kommando "Hebelziehen" zurück.
     */
    public String getHelp(){
        return "zieht einen Hebel falls er vorhanden ist und noch nicht aktiviert wurde!";
    }

}
