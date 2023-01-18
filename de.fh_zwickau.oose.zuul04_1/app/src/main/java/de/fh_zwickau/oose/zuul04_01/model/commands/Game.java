package de.fh_zwickau.oose.zuul04.model.commands;
/*
 * 
 * Dies ist die Hauptklasse des Spiels "Die Welt von Zuul."
 * 
 * "Die Welt von Zuul" ist ein einfaches textbasiertes Adventure-Spiel.
 * Der Spieler kann in der Welt herumlaufen.
 * Um das Spiel interessanter zu machen, sollten weitere Kommandos eingebaut werden!
 * 
 * Um das Spiel zu spielen, wird in der main-Methode eine Instanz dieser Klasse
 * erzeugt und die play-Methode aufgerufen.
 * 
 * Diese Klasse sorgt dafür, dass alle nötigen Objekte erzeugt und
 * initialisiert werden: Es werden alle Räume angelegt, der Parser
 * erzeugt und das Spiel gestartet.
 * 
 * In einer Endlosschleife wird dann dafür gesorgt, dass eingegebene Kommandos
 * analysiert und ausgeführt werden.
 * 
 * @author Michael Kolling and David J. Barnes
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.fh_zwickau.oose.zuul04.model.Chest;
import de.fh_zwickau.oose.zuul04.model.Opponent;
import de.fh_zwickau.oose.zuul04.model.Player;
import de.fh_zwickau.oose.zuul04.model.Room;
import de.fh_zwickau.oose.zuul04.model.Hebel;
import de.fh_zwickau.oose.zuul04.model.items.ItemHealthPotion;
import de.fh_zwickau.oose.zuul04.model.items.ItemIronSword;

/**
 * Dies ist die Dokumentation der Klasse Game. Game erzeugt einen Spieler und
 * die verschiedenen Räume.
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 * @version 1.0
 */
public class Game
{

	
   /** 
   *Parser der Eingaben über die Konsole entgegennimmt.
   */
    private final Parser parser;
    
    /** 
     *Spieler der das Spiel spielt.
     */
    private final Player player;

    /**
     * Konstruktor der Game-Klasse, legt auch die Raumstruktur fest.
     */
    public Game() 
    {
        player = new Player();

        parser = new Parser();

        createRooms();

    }
    
    /**
     * Die main-Methode instanziiert eine neue Game-Klasse und startet das Spiel.
     */
    public static void main(String[] args) {
    	Game game = new Game();
    	game.play();

    }

    /**
     * Erzeugt alle Räume und die Verbindungen zwischen ihnen.
     */
    private void createRooms()
    {
        Room startraum, gegnerraum1, gegnerraum2, gegnerraum3,gegnerraum4, gegnerraum5, gegnerraum6, schatzraum1, schatzraum2, schatzraum3, schatzraum4, schatzraum5, schatzraum6, schalterraum1, schalterraum2, schalterraum3, halle, endgegner;

        // Räume erzeugen:
        startraum = new Room("auf einer Wiese vor einer finsteren Höhle");
       player.setCurrentRoom(startraum);
       player.setStartraumtoRoomHistory(startraum);

        gegnerraum1 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum1.setGegner(new Opponent("Startgegner", 70, 10, 0, 0));
        
        gegnerraum2 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum3 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum4 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum5 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum6 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");



        schatzraum1 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");
        Chest chest1=new Chest();
        chest1.setItem(new ItemHealthPotion("Heiltrank","Dieser mysteriöse Trank riecht als ob er etwas bewirken könnte..."));
        schatzraum1.setChest(chest1);
        
        schatzraum2 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");
        Chest chest2=new Chest();
        chest2.setItem(new ItemIronSword("Eisenschwert","Dieses Schwert hat eine ziemlich scharfe Klinge..."));
        schatzraum2.setChest(chest2);
        
        schatzraum3 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");
        schatzraum4 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");
        schatzraum5 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");
        schatzraum6 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");


        schalterraum1= new Room("in einem Raum, in dessen Mitte ein verosteter Hebel steht");
        schalterraum1.setHebel(new Hebel());

        schalterraum2= new Room("in einem Raum, in dessen Mitte ein verosteter Hebel steht");
        schalterraum2.setHebel(new Hebel());
      

        schalterraum3= new Room("in einem Raum, in dessen Mitte ein verosteter Hebel steht");
        schalterraum3.setHebel(new Hebel());
     


        halle = new Room("in einem hallenartigen Raum, die Tür, durch die du kamst ist zugefallen. Du hörst ein mulmig machenendes Geräusch....");

        endgegner = new Room("Dir fehlen die Worte um den Raum zu beschreiben, selbst wenn du es hinausschaffst wirst du dich wohl nie daran erinnern");



        // Ausgänge aufbauen:
        startraum.setExit("norden", gegnerraum1);

        gegnerraum1.setExit("norden", schatzraum3);
        gegnerraum1.setExit("westen", schatzraum1);
        gegnerraum1.setExit("osten", schatzraum2);
        gegnerraum1.setExit("nordwesten", schalterraum1);
        gegnerraum1.setExit("nordosten", gegnerraum3);
        gegnerraum1.setExit("süden",startraum);

        schatzraum1.setExit("norden",gegnerraum2);
        schatzraum1.setExit("nordosten",schalterraum1);
        schatzraum1.setExit("westen",gegnerraum1);

        schatzraum2.setExit("norden", gegnerraum3);
        schatzraum2.setExit("nordwesten",schatzraum3);
        schatzraum2.setExit("osten",gegnerraum1);

        schalterraum1.setExit("nordwesten", gegnerraum2);
        schalterraum1.setExit("nordosten", schatzraum3);
        schalterraum1.setExit("südosten", gegnerraum1);
        schalterraum1.setExit("südewesten", schatzraum1);

        gegnerraum2.setExit("norden", schatzraum4);
        gegnerraum2.setExit("nordosten", gegnerraum4);
        gegnerraum2.setExit("osten", schatzraum3);
        gegnerraum2.setExit("südosten", schalterraum1);
        gegnerraum2.setExit("süden", schatzraum1);

        schatzraum3.setExit("norden", gegnerraum4);
        schatzraum3.setExit("osten", gegnerraum3);
        schatzraum3.setExit("süden",gegnerraum1);
        schatzraum3.setExit("westen", gegnerraum2);
        schatzraum3.setExit("nordosten", schalterraum2);
        schatzraum3.setExit("südosten",schatzraum2);
        schatzraum3.setExit("südwesten", schalterraum1);
        schatzraum3.setExit("nordwesten", schatzraum4);

        gegnerraum3.setExit("norden",schatzraum5);
        gegnerraum3.setExit("süden",schatzraum2);
        gegnerraum3.setExit("westen", schatzraum3);
        gegnerraum3.setExit("nordwesten", schalterraum2);
        gegnerraum3.setExit("südwesten", gegnerraum1);

        schatzraum4.setExit("norden", gegnerraum5);
        schatzraum4.setExit("osten", gegnerraum4);
        schatzraum4.setExit("süden", gegnerraum2);
        schatzraum4.setExit("nordosten", schalterraum3);
        schatzraum4.setExit("südosten", schatzraum3);

        gegnerraum4.setExit("norden", schatzraum6);
        gegnerraum4.setExit("osten", schatzraum5);
        gegnerraum4.setExit("süden", schatzraum3);
        gegnerraum4.setExit("westen", schatzraum4);
        gegnerraum4.setExit("nordosten", gegnerraum6);
        gegnerraum4.setExit("südosten", schalterraum2);
        gegnerraum4.setExit("südwesten", gegnerraum2);
        gegnerraum4.setExit("nordwesten", schalterraum3);

        schalterraum2.setExit("nordosten",schatzraum5);
        schalterraum2.setExit("südosten", gegnerraum3);
        schalterraum2.setExit("südwesten", schatzraum3);
        schalterraum2.setExit("nordwesten", gegnerraum4);

        schatzraum5.setExit("norden", gegnerraum6);
        schatzraum5.setExit("süden", gegnerraum3);
        schatzraum5.setExit("westen", gegnerraum4);
        schatzraum5.setExit("nordwesten", schatzraum6);
        schatzraum5.setExit("südwesten", schalterraum2);

        gegnerraum5.setExit("osten", schatzraum6);
        gegnerraum5.setExit("süden", schatzraum4);
        gegnerraum5.setExit("südosten", schalterraum3);

        schalterraum3.setExit("nordosten", schatzraum6);
        schalterraum3.setExit("südosten", gegnerraum4);
        schalterraum3.setExit("südwesten", schatzraum4);
        schalterraum3.setExit("nordwesten", gegnerraum5);

        schatzraum6.setExit("norden", halle);
        schatzraum6.setExit("osten", gegnerraum6);
        schatzraum6.setExit("westen", gegnerraum5);
        schatzraum6.setExit("süden", gegnerraum4);
        schatzraum6.setExit("südosten", schatzraum5);
        schatzraum6.setExit("südwesten", schalterraum3);

        gegnerraum6.setExit("westen", schatzraum6 );
        gegnerraum6.setExit("süden", schatzraum5);
        gegnerraum6.setExit("südwesten", gegnerraum4);

        halle.setExit("norden", endgegner);









    }





    /**
     *  Die Hauptroutine des Spiels
     *  läuft in einer Schleife, bis das Spiel beendet wird.
     */
    public void play() 
    {            
        printWelcome();

        // Hier werden wiederholt Kommando-Eingaben gelesen und die
        // Kommandos ausgeführt, bis das Spiel beendet ist.
                
        boolean finished = false;
        while(! finished) {
            Command command = parser.getCommand();
                finished = command.execute(player);
        }
        System.out.println("Danke fürs Spielen! Schönen Tag noch!");
    }

    /**
     * Gibt die Willkommensnachricht für den Spieler aus.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Willkommen in der Welt von Tyranak-Adventure");
        System.out.println("Gib 'hilfe' ein, um Hilfe zu bekommen.");
        System.out.println("=================================================");
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
}
