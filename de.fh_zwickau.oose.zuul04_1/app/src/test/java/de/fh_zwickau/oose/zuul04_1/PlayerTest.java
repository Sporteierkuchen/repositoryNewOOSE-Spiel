package de.fh_zwickau.oose.zuul04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.fh_zwickau.oose.zuul04.model.Player;
import de.fh_zwickau.oose.zuul04.model.Room;
import de.fh_zwickau.oose.zuul04.model.items.Item;
import de.fh_zwickau.oose.zuul04.model.items.ItemHealthPotion;

class PlayerTest {

Player player1;
Player player2;
Room testraum1; 		
Room testraum2; 		
Room testraum3; 	
Room testraum4; 	
Item testitem1;
Item testitem2;
List<Item> itemlist;
List<Item> emptylist;

final String heiltrank1="Heiltrank1";
final String heiltrank2="Heiltrank2";
final String schadenstrank="Schadenstrank";
final String eisenschwert="Eisenschwert";
final String norden="norden";
final String sueden="süden";
final String westen="westen";

	@BeforeEach
	void setUp() {
		
		player1 = new Player();
		player2 = new Player();
		
		testraum1 = new Room("Testraum1");	
		testraum2 = new Room("Testraum2");
		testraum3 = new Room("Testraum3");
		testraum4 = new Room("Testraum4");
		
		testraum1.setExit(norden, testraum2);
		
		testraum1.setExit(westen, testraum3);
	
		testraum2.setExit(westen, testraum3);
		testraum3.setExit(sueden, testraum4);
	
		player1.setCurrentRoom(testraum1);
		player1.setStartraumtoRoomHistory(testraum1);
		
		player2.setCurrentRoom(testraum1);
		player2.setStartraumtoRoomHistory(testraum1);
		player2.walk(norden);
		player2.walk(westen);
		player2.walk(sueden);
		
		testitem1=new ItemHealthPotion(heiltrank1, "Test1");
		testitem2=new ItemHealthPotion(heiltrank2, "Test2");
		testraum1.addItem(testitem1);
		itemlist=new ArrayList<>();
		emptylist=new ArrayList<>();
		itemlist.add(testitem1);
		itemlist.add(testitem2);
		player2.addItem(testitem1);
	}

	@Test
	public void konstruktorTest() {
		
		Player testplayer= new Player();
		assertNull(testplayer.getCurrentRoom());	
			
	}	

	@Test
	@DisplayName("Test für Spieler bewegung")
	public void walkImTestraum1NachNorden() {
		
	player1.walk(norden);
	assertEquals(testraum2, player1.getCurrentRoom());
	
	}
	
	@Test
	@DisplayName("Test für Spieler bewegung")
	public void walkImTestraum1NachWesten() {
		
	player1.walk(westen);
	assertEquals(testraum3, player1.getCurrentRoom());
	
	}
	
	@Test
	@DisplayName("Test für Spieler bewegung")
	public void walkImTestraum1NachSueden() {
		
	player1.walk(sueden);
	assertEquals(testraum1, player1.getCurrentRoom());
	
	}	
	
	
	@Test
	public void goBackEinenSchritt() {
	
	player2.goBack(1);
	assertEquals(testraum3, player2.getCurrentRoom());
	
	}
	
	@Test
	public void goBackZweiSchritte() {
	
	player2.goBack(2);
	assertEquals(testraum2, player2.getCurrentRoom());
	
	}
	
	@Test
	public void goBackMehrmals() {
	
	player2.goBack(1);
	assertEquals(testraum3, player2.getCurrentRoom());	
			
	player2.goBack(2);
	assertEquals(testraum1, player2.getCurrentRoom());
	
	}
	
	@Test
	public void goBackMehrSchritteAlsMoeglich() {
	
	player2.goBack(4);
	assertEquals(testraum4, player2.getCurrentRoom());
	
	}
	
	@Test
	public void goBackNullSchritte() {
	
	player2.goBack(0);
	assertEquals(testraum4, player2.getCurrentRoom());
	
	}
	
	@Test
	public void goBackNegativeAnzahlAnSchritten() {
	
	player2.goBack(-1);
	assertEquals(testraum4, player2.getCurrentRoom());
	
	}
	
	@Test
	public void goBackZumStartraum() {
	
	player2.goBack(3);
	assertEquals(testraum1, player2.getCurrentRoom());
	
	}
	
	@Test
	public void goBackImStartraum() {
	
	player2.goBack(3);
	player2.goBack(1);
	assertEquals(testraum1, player2.getCurrentRoom());
	
	}
	
	@Test
	public void getItemsFromChest() {
	
	player1.getItemsFromChest(itemlist);
	assertFalse(player1.isInventoryempty());
	assertTrue(player1.contains(heiltrank1));
	assertTrue(player1.contains(heiltrank2));
	
	}
	
	@Test
	public void getItemsFromEmptyChest() {
	player1.getItemsFromChest(emptylist);
	assertTrue(player1.isInventoryempty());
	
	}
	
	@Test
	public void pickUpItemTest() {
	
	player1.pickUpItem(0);
	assertFalse(player1.isInventoryempty());
	assertTrue(player1.contains(heiltrank1));
	assertTrue(player1.getCurrentRoom().noItemsinRoom());
	
	}
	
	@Test
	public void pickUpWrongItemTest() {
	
	player1.pickUpItem(5);
	assertTrue(player1.isInventoryempty());
	assertFalse(player1.contains(heiltrank1));
	assertEquals(Optional.ofNullable (testitem1), player1.getCurrentRoom().getItem(0));
	
	}
	
	@Test
	public void pickUpNoItemTest() {
	
	player1.walk(norden);	
	player1.pickUpItem(0);
	assertTrue(player1.isInventoryempty());
	assertFalse(player1.contains(heiltrank1));
	
	}
	
	@Test
	public void dropNoItemTest() {
	
	player1.dropItem(5);
	assertTrue(player1.isInventoryempty());
	assertFalse(player1.contains("Eisenschwert"));
	
	}
	
	@Test
	public void dropItemTest() {
	
	player2.dropItem(0);
	assertTrue(player2.isInventoryempty());
	assertFalse(player2.contains(heiltrank1));
	assertEquals(Optional.ofNullable (testitem1), player2.getCurrentRoom().getItem(0));
	
	}
	
	@Test
	public void dropWrongItemTest() {
	
	player2.dropItem(1);
	assertFalse(player2.isInventoryempty());
	assertTrue(player2.contains(heiltrank1));
	assertEquals(Optional.empty(), player2.getCurrentRoom().getItem(-1));
	
	}
	
	
}
