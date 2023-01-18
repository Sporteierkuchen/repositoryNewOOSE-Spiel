package de.fh_zwickau.oose.zuul04;


import de.fh_zwickau.oose.zuul04.controller.GestorbenDialogController;
import de.fh_zwickau.oose.zuul04.controller.GewonnenDialogController;
import de.fh_zwickau.oose.zuul04.controller.MainAppController;
import de.fh_zwickau.oose.zuul04.controller.SchwierigkeitsgradController;
import de.fh_zwickau.oose.zuul04.controller.SpielanleitungController;
import de.fh_zwickau.oose.zuul04.model.Chest;
import de.fh_zwickau.oose.zuul04.model.Opponent;
import de.fh_zwickau.oose.zuul04.model.Player;
import de.fh_zwickau.oose.zuul04.model.Room;
import de.fh_zwickau.oose.zuul04.model.Hebel;
import de.fh_zwickau.oose.zuul04.model.items.ItemHealthPotion;
import de.fh_zwickau.oose.zuul04.model.items.ItemIronSword;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application{

	private Stage primaryStage;
	private AnchorPane rootLayout;
	private Scene scene;
	
	private String schwierigkeitsgrad="";
	
    /** 
     *Spieler der das Spiel spielt.
     */
    private Player player;

    private Room startraum, gegnerraum1, gegnerraum2, gegnerraum3,gegnerraum4, gegnerraum5, gegnerraum6, schatzraum1, schatzraum2, schatzraum3, schatzraum4, schatzraum5, schatzraum6, hebelraum1, hebelraum2, hebelraum3, halle, endgegner;
    
	
	public MainApp() {	
		
	}

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Zuul");
		this.primaryStage.setResizable(false);
		
		initRootLayout();
	}

	public void initRootLayout() {
		
		player = new Player();
        createRooms();
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("fxml_Dateien/Main.fxml"));
			this.rootLayout = (AnchorPane) loader.load();

			MainAppController controller = loader.getController();
			controller.setMainApp(this);
			controller.setPlayer(player);
			controller.startwerteSetzen();
			
			this.scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			
			fehlerBeimLadenVonFxmlDateiAnzeigen(e.getMessage());
			
		}

	}

	public void initGestorbenDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("fxml_Dateien/GestorbenDialog.fxml"));
			VBox vbox = (VBox) loader.load();

			GestorbenDialogController controller = loader.getController();
			controller.setMainApp(this);
			controller.setPlayer(player);
			controller.werteSetzen();
			
			Scene scene = new Scene(vbox);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			
			fehlerBeimLadenVonFxmlDateiAnzeigen(e.getMessage());
			
		}

	}
	
	public void initGewonnenDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("fxml_Dateien/GewonnenDialog.fxml"));
			VBox vbox = (VBox) loader.load();

			GewonnenDialogController controller = loader.getController();
			controller.setMainApp(this);
			controller.setPlayer(player);
			controller.werteSetzen();
			
			Scene scene = new Scene(vbox);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			
			fehlerBeimLadenVonFxmlDateiAnzeigen(e.getMessage());
			
		}

	}
	
	public void initSpielanleitung() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("fxml_Dateien/Spielanleitung.fxml"));
			VBox vbox = (VBox) loader.load();

			SpielanleitungController controller = loader.getController();
			controller.setMainApp(this);
		
			Scene scene = new Scene(vbox);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			
			fehlerBeimLadenVonFxmlDateiAnzeigen(e.getMessage());
			
		}

	}
	
	public void initSchwierigkeitsgradWählen() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("fxml_Dateien/Schwierigkeitsgrad.fxml"));
			VBox vbox = (VBox) loader.load();

			SchwierigkeitsgradController controller = loader.getController();
			controller.setMainApp(this);
			controller.startwerteSetzen();
			Scene scene = new Scene(vbox);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			
			fehlerBeimLadenVonFxmlDateiAnzeigen(e.getMessage());
			
		}

	}
	
	
	private void createRooms()
    {
        

        // Räume erzeugen:
       startraum = new Room("auf einer Wiese vor einer finsteren Höhle");
       player.setCurrentRoom(startraum);
       player.setStartraumtoRoomHistory(startraum);
       //player.setHp(1);
       player.addItem(new ItemIronSword("Eisenschwert","Dieses Schwert hat eine ziemlich scharfe Klinge..."));
    	
        gegnerraum1 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum2 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum3 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum4 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum5 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        gegnerraum6 = new Room("in einem düsteren Raum, du hörst etwas in der Dunkelheit");
        
        schatzraum1 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");
        schatzraum2 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");
        schatzraum3 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern"); 
        schatzraum4 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern"); 
        schatzraum5 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");
        schatzraum6 = new Room("in einem spärlich beleuchtetem Raum... siehst etwas in der Dunkelheit glitzern");
        
        hebelraum1= new Room("in einem Raum, in dessen Mitte ein verosteter Hebel steht");
        hebelraum1.setHebel(new Hebel());
        hebelraum2= new Room("in einem Raum, in dessen Mitte ein verosteter Hebel steht");
        hebelraum2.setHebel(new Hebel());
        hebelraum3= new Room("in einem Raum, in dessen Mitte ein verosteter Hebel steht");
        hebelraum3.setHebel(new Hebel());
     
        halle = new Room("in einem hallenartigen Raum, die Tür, durch die du kamst ist zugefallen. Du hörst ein mulmig machenendes Geräusch....");

        endgegner = new Room("Dir fehlen die Worte um den Raum zu beschreiben, selbst wenn du es hinausschaffst wirst du dich wohl nie daran erinnern");
        
        
     // Ausgänge aufbauen:
        startraum.setExit("norden", gegnerraum1);

        gegnerraum1.setExit("norden", schatzraum3);
        gegnerraum1.setExit("westen", schatzraum1);
        gegnerraum1.setExit("osten", schatzraum2);
        gegnerraum1.setExit("nordwesten", hebelraum1);
        gegnerraum1.setExit("nordosten", gegnerraum3);
        gegnerraum1.setExit("süden",startraum);

        schatzraum1.setExit("norden",gegnerraum2);
        schatzraum1.setExit("nordosten",hebelraum1);
        schatzraum1.setExit("osten",gegnerraum1);

        schatzraum2.setExit("norden", gegnerraum3);
        schatzraum2.setExit("nordwesten",schatzraum3);
        schatzraum2.setExit("westen",gegnerraum1);

        hebelraum1.setExit("nordwesten", gegnerraum2);
        hebelraum1.setExit("nordosten", schatzraum3);
        hebelraum1.setExit("südosten", gegnerraum1);
        hebelraum1.setExit("südewesten", schatzraum1);

        gegnerraum2.setExit("norden", schatzraum4);
        gegnerraum2.setExit("nordosten", gegnerraum4);
        gegnerraum2.setExit("osten", schatzraum3);
        gegnerraum2.setExit("südosten", hebelraum1);
        gegnerraum2.setExit("süden", schatzraum1);

        schatzraum3.setExit("norden", gegnerraum4);
        schatzraum3.setExit("osten", gegnerraum3);
        schatzraum3.setExit("süden",gegnerraum1);
        schatzraum3.setExit("westen", gegnerraum2);
        schatzraum3.setExit("nordosten", hebelraum2);
        schatzraum3.setExit("südosten",schatzraum2);
        schatzraum3.setExit("südwesten", hebelraum1);
        schatzraum3.setExit("nordwesten", schatzraum4);

        gegnerraum3.setExit("norden",schatzraum5);
        gegnerraum3.setExit("süden",schatzraum2);
        gegnerraum3.setExit("westen", schatzraum3);
        gegnerraum3.setExit("nordwesten", hebelraum2);
        gegnerraum3.setExit("südwesten", gegnerraum1);

        schatzraum4.setExit("norden", gegnerraum5);
        schatzraum4.setExit("osten", gegnerraum4);
        schatzraum4.setExit("süden", gegnerraum2);
        schatzraum4.setExit("nordosten", hebelraum3);
        schatzraum4.setExit("südosten", schatzraum3);

        gegnerraum4.setExit("norden", schatzraum6);
        gegnerraum4.setExit("osten", schatzraum5);
        gegnerraum4.setExit("süden", schatzraum3);
        gegnerraum4.setExit("westen", schatzraum4);
        gegnerraum4.setExit("nordosten", gegnerraum6);
        gegnerraum4.setExit("südosten", hebelraum2);
        gegnerraum4.setExit("südwesten", gegnerraum2);
        gegnerraum4.setExit("nordwesten", hebelraum3);

        hebelraum2.setExit("nordosten",schatzraum5);
        hebelraum2.setExit("südosten", gegnerraum3);
        hebelraum2.setExit("südwesten", schatzraum3);
        hebelraum2.setExit("nordwesten", gegnerraum4);

        schatzraum5.setExit("norden", gegnerraum6);
        schatzraum5.setExit("süden", gegnerraum3);
        schatzraum5.setExit("westen", gegnerraum4);
        schatzraum5.setExit("nordwesten", schatzraum6);
        schatzraum5.setExit("südwesten", hebelraum2);

        gegnerraum5.setExit("osten", schatzraum6);
        gegnerraum5.setExit("süden", schatzraum4);
        gegnerraum5.setExit("südosten", hebelraum3);

        hebelraum3.setExit("nordosten", schatzraum6);
        hebelraum3.setExit("südosten", gegnerraum4);
        hebelraum3.setExit("südwesten", schatzraum4);
        hebelraum3.setExit("nordwesten", gegnerraum5);

        schatzraum6.setExit("norden", halle);
        schatzraum6.setExit("osten", gegnerraum6);
        schatzraum6.setExit("westen", gegnerraum5);
        schatzraum6.setExit("süden", gegnerraum4);
        schatzraum6.setExit("südosten", schatzraum5);
        schatzraum6.setExit("südwesten", hebelraum3);

        gegnerraum6.setExit("westen", schatzraum6 );
        gegnerraum6.setExit("süden", schatzraum5);
        gegnerraum6.setExit("südwesten", gegnerraum4);

        halle.setExit("norden", endgegner);
        halle.setExit("süden", schatzraum6);

        
        Chest chest1=new Chest();
        Chest chest2=new Chest();
        Chest chest3=new Chest();
        Chest chest4=new Chest();
        Chest chest5=new Chest();
        Chest chest6=new Chest();
        
        switch(this.schwierigkeitsgrad){
        	
        	case "Leicht", "":
        		
            Opponent gegner1Leicht=new Opponent("Startgegner", 70, 10, 10, 5);
            gegner1Leicht.addItem(new ItemHealthPotion("Heiltrank","Heiltrank vom Startgegner"));
            gegnerraum1.setGegner(gegner1Leicht);
            
            endgegner.setGegner(new Opponent("Endgegner", 100, 35, 30, 10));
            
            
            chest1.setItem(new ItemHealthPotion("Heiltrank","Dieser mysteriöse Trank riecht als ob er etwas bewirken könnte..."));
            schatzraum1.setChest(chest1);
            
            chest2.setItem(new ItemIronSword("Eisenschwert","Dieses Schwert hat eine ziemlich scharfe Klinge..."));
            schatzraum2.setChest(chest2);
           
            schatzraum3.setChest(chest3);
            
            schatzraum4.setChest(chest4);
            
            schatzraum5.setChest(chest5);
            
            schatzraum6.setChest(chest6);
        		
        		break;
        	case "Normal":
        		
                Opponent gegner1Normal=new Opponent("Startgegner", 70, 20, 10, 5);
                gegner1Normal.addItem(new ItemHealthPotion("Heiltrank","Heiltrank vom Startgegner"));
                gegnerraum1.setGegner(gegner1Normal);
                
                endgegner.setGegner(new Opponent("Endgegner", 100, 35, 30, 10));
                
                
                chest1.setItem(new ItemHealthPotion("Heiltrank","Dieser mysteriöse Trank riecht als ob er etwas bewirken könnte..."));
                schatzraum1.setChest(chest1);
                
                chest2.setItem(new ItemIronSword("Eisenschwert","Dieses Schwert hat eine ziemlich scharfe Klinge..."));
                schatzraum2.setChest(chest2);
               
                schatzraum3.setChest(chest3);
                
                schatzraum4.setChest(chest4);
                
                schatzraum5.setChest(chest5);
                
                schatzraum6.setChest(chest6);
        		
        		break;
        	case "Schwer":
        		
                Opponent gegner1Schwer=new Opponent("Startgegner", 70, 30, 10, 5);
                gegner1Schwer.addItem(new ItemHealthPotion("Heiltrank","Heiltrank vom Startgegner"));
                gegnerraum1.setGegner(gegner1Schwer);
                
                endgegner.setGegner(new Opponent("Endgegner", 100, 35, 30, 10));
                
                
                chest1.setItem(new ItemHealthPotion("Heiltrank","Dieser mysteriöse Trank riecht als ob er etwas bewirken könnte..."));
                schatzraum1.setChest(chest1);
                
                chest2.setItem(new ItemIronSword("Eisenschwert","Dieses Schwert hat eine ziemlich scharfe Klinge..."));
                schatzraum2.setChest(chest2);
               
                schatzraum3.setChest(chest3);
                
                schatzraum4.setChest(chest4);
                
                schatzraum5.setChest(chest5);
                
                schatzraum6.setChest(chest6);
        		
        		break;
        	case "Extrem":
        		
                Opponent gegner1Extrem=new Opponent("Startgegner", 70, 40, 10, 5);
                gegner1Extrem.addItem(new ItemHealthPotion("Heiltrank","Heiltrank vom Startgegner"));
                gegnerraum1.setGegner(gegner1Extrem);
                
                endgegner.setGegner(new Opponent("Endgegner", 100, 35, 30, 10));
                
                
                chest1.setItem(new ItemHealthPotion("Heiltrank","Dieser mysteriöse Trank riecht als ob er etwas bewirken könnte..."));
                schatzraum1.setChest(chest1);
                
                chest2.setItem(new ItemIronSword("Eisenschwert","Dieses Schwert hat eine ziemlich scharfe Klinge..."));
                schatzraum2.setChest(chest2);
               
                schatzraum3.setChest(chest3);
                
                schatzraum4.setChest(chest4);
                
                schatzraum5.setChest(chest5);
                
                schatzraum6.setChest(chest6);
        		
        		break;
        	default:
        		unbekanntenFehlerAnzeigen(schwierigkeitsgrad);
        		break;
        	} 
        
    }

	public boolean hallePrüfen(Room halle) {
		
	if(this.halle.equals(halle)) {
		return true;	
	}
	return false;
		
	}
	
	public boolean endgegnerPrüfen(Room room) {
		
	if(this.endgegner.equals(room)) {
		return true;	
	}
	return false;
		
	}
	
	public Player getPlayer() {
		return player;
	}

	public static void fehlerBeimLadenVonFxmlDateiAnzeigen(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Ein Fehler ist aufgetreten!");
		alert.setHeaderText("Fehler beim Laden der fxml-Datei!");
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	public static void unbekanntenFehlerAnzeigen(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Ein Fehler ist aufgetreten!");
		alert.setHeaderText("Unbekannter Fehler!");
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public AnchorPane getRootLayout() {
		return rootLayout;
	}

	public Scene getScene() {
		return scene;
	}

	public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
		this.schwierigkeitsgrad = schwierigkeitsgrad;
	}

	public String getSchwierigkeitsgrad() {
		return schwierigkeitsgrad;
	}
	
}
