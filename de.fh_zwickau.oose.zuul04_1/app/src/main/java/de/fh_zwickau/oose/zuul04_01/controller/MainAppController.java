package de.fh_zwickau.oose.zuul04.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import de.fh_zwickau.oose.zuul04.MainApp;
import de.fh_zwickau.oose.zuul04.model.Chest;
import de.fh_zwickau.oose.zuul04.model.Player;
import de.fh_zwickau.oose.zuul04.model.Room;
import de.fh_zwickau.oose.zuul04.model.items.Item;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class MainAppController {

	private MainApp mainApp;
	private Player player;
	
	@FXML
	private Text raumbeschreibung;

	@FXML
	private Text infos;
	
	@FXML
	private Label hpanzeige;
	
	@FXML
	private ComboBox<String> itemsimraum;
	
	@FXML
	ListView<String> listView;
	
	@FXML
	private Text inventarLeer;
	
	@FXML
	private ProgressBar hpAnzeige;
	
	@FXML
	private void initialize() {

		
		
	   
		
	    
	  
	    
	}
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}	
	
	public void setPlayer(Player player) {
		this.player=player;
		
	}	
	
	public void startwerteSetzen() {
		
		this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
		this.infos.setText("Willkommen in der Welt von Tyranak-Adventure!"+"\n"+"Klicke oben links auf \"Hilfe\" um zur Spielanleitung zu kommen.");
		Integer hp=player.getHp();
		this.hpanzeige.setText(hp.toString());
		lebensAnzeigeAktualisieren();
		itemlisteImRaumAktualisieren();
		inventarAktualisieren();
		
	}
	
	
	@FXML
	private void handleNordenButton() {

		walkPlayer("norden");
	
	}
	
	@FXML
	private void handleOstenButton() {

		walkPlayer("osten");
	
	}
	
	@FXML
	private void handleSüdenButton() {

		walkPlayer("süden");
	
	}
	
	@FXML
	private void handleWestenButton() {

		walkPlayer("westen");
	
	}
	
	@FXML
	private void handleNordostenButton() {

		walkPlayer("nordosten");
	
	}
	
	@FXML
	private void handleSüdostenButton() {

		walkPlayer("südosten");
	
	}
	
	@FXML
	private void handleSüdwestenButton() {

		walkPlayer("südwesten");
	
	}
	
	@FXML
	private void handleNordwestenButton() {

		walkPlayer("nordwesten");
	
	}
	
	@FXML
	private void handleZurückButton() {

	if(player.getRoomhistorySize()==1) {
		
		this.infos.setText("Du befindest dich im Startraum und kannst nicht weiter zurück gehen!");	
		
	}
	else if(mainApp.endgegnerPrüfen(player.getCurrentRoom())) {
		this.infos.setText("Du kannst hier nicht mehr zurück!");
	}
	else {	
		player.goBack(1);
		raumbeschreibungAktualisieren();	
		itemlisteImRaumAktualisieren();
		
	}
		
	}
	
	
	
	private void walkPlayer(String direction) {
		
		Room room=player.getCurrentRoom();
		
		if( (!mainApp.hallePrüfen(room)) || (player.getPressedLever()==3) || (mainApp.hallePrüfen(room) && !direction.equals("norden"))) {	
			
		player.walk(direction);
		raumbeschreibungAktualisieren();
		itemlisteImRaumAktualisieren();
		
		if(room.equals(player.getCurrentRoom())) {
			this.infos.setText("Da ist keine Tür!");	
		}
		
		}
		else{
			this.infos.setText("Du musst drei Schalter drücken um hier rein zu kommen!");
		}	
		
	}
	
	@FXML
	private void handleHebelUmlegenButton() {

	    Room room = player.getCurrentRoom();

        if (room.getHebel().isPresent() && !room.leverAvailable()) {
            this.infos.setText("Der Hebel in diesem Raum wurde bereits umgelegt...");
    
        } 
        else if (room.getHebel().isPresent() && room.leverAvailable()) {
            this.infos.setText("Du zögerst leicht.... ziehst aber dennoch an dem verrosteten Hebel... in der Ferne hörst du es krachen...");
            room.getHebel().get().einschalten();
            player.pressLever();
            
        } 
        else {
        	this.infos.setText("In dem Raum gibt es keinen Hebel...");
        }   

	}
	
	@FXML
	private void handleTruheöfffnenButton() {
		
		Room room=player.getCurrentRoom();  
		
		if(room.getChest().isPresent()) {
      
        if(!room.getChest().get().isOpen()) {
        	
        	room.getChest().get().setOpen(true);	
        	this.infos.setText("Die Truhe in diesem Raum wurde geöffnet!"+"\n"+room.getChest().get().getItemsInChest());	
        	player.openchest();
        	
        }
        else {
        	  this.infos.setText("Die Truhe in diesem Raum wurde bereits geöffnet!"+"\n"+room.getChest().get().getItemsInChest());	
        }
				
		}  
	    else {
	        this.infos.setText("In diesem Raum ist keine Truhe vorhanden!");
	    }
	
	}
	
	
	@FXML
	private void handleTruheLeerenButton() {
		
		Room room=player.getCurrentRoom();  
		
		if(room.getChest().isPresent()) {
      
		Chest chest=room.getChest().get();		
        if(chest.removeItemsFromChest().isPresent() && !chest.isChestEmpty()) {
        	
        	Optional<List<Item>> truheninhalt=chest.removeItemsFromChest();
        	player.getItemsFromChest(truheninhalt.get());
        	chest.clearChest();
        	this.infos.setText("Du hast dir die Items aus der Truhe genommen!"); 
        	inventarAktualisieren();
        	
        }
        else if (chest.removeItemsFromChest().isPresent()  && chest.isChestEmpty()) {
        	this.infos.setText("Die Truhe ist leer!"); 
        }
        else {
	        this.infos.setText("Du musst die Truhe zunächst öffnen!");
	    }
			
        
		}  
	    else {
	        this.infos.setText("In diesem Raum ist keine Truhe vorhanden!");
	    }
			
	}
	
	
	@FXML
	private void handleAufhebenButton() {
		
		Room room=player.getCurrentRoom();  

		
		if(!room.getItemNamesinRoom().isEmpty() && itemsimraum.getValue()!=null) {
			
		player.pickUpItem(itemsimraum.getSelectionModel().getSelectedIndex());
		this.infos.setText("Du hast das Item "+itemsimraum.getValue()+" aufgenommen!");	
		this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
		itemlisteImRaumAktualisieren();
		inventarAktualisieren();
		
		}
		else if(!room.getItemNamesinRoom().isEmpty() && itemsimraum.getValue()==null) {
			this.infos.setText("Bitte wähle das Item aus, welches du aufheben möchtest!");
		}
		else {
			this.infos.setText("In diesem Raum liegen keine Items herum!");		
		}
				
	}
	
	@FXML
	private void handleAblegenButton() {
	
		if(!player.isInventoryempty() && !listView.getSelectionModel().isEmpty()) {
			
		player.dropItem(listView.getSelectionModel().getSelectedIndex());
		this.infos.setText("Du hast das Item "+listView.getSelectionModel().getSelectedItem()+" abgelegt!");	
		this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
		itemlisteImRaumAktualisieren();
		inventarAktualisieren();
		
		}
		else if(!player.isInventoryempty() && listView.getSelectionModel().isEmpty()) {
			this.infos.setText("Bitte wähle das Item aus, welches du ablegen möchtest!");
		}
		else {
			this.infos.setText("Dein Inventar ist leer!");		
		}
				
	}
	
	@FXML
	private void handleBenutzenButton(){			
				
		player.setUseItemText(null);
					
		if(!player.isInventoryempty() && !listView.getSelectionModel().isEmpty()) {
						
			String itemname=listView.getSelectionModel().getSelectedItem();
			itemprüfen(itemname);
			
		}
		else if(!player.isInventoryempty() && listView.getSelectionModel().isEmpty()) {
			this.infos.setText("Bitte wähle das Item aus, welches du benutzen möchtest!");
		}
		else {
			this.infos.setText("Dein Inventar ist leer!");		
		}
				
				
				
	}
	
	
	private void afteranimation() {
	
		  player.useItem(listView.getSelectionModel().getSelectedIndex());
			this.infos.setText(player.getUseItemText());		
			inventarprüfen();
			lebensAnzeigeAktualisieren();
		this.mainApp.getRootLayout().setDisable(false);
		
	}
	
	private void itemprüfen(String itemname) {
	
	    switch (itemname) {
	        case "Heiltrank":
	        	this.infos.setText("");	
	        	  if(player.getHp() == player.getMaxHp()){
	                  
	        		  afteranimation();
	        		  
	              }else {
	                
	            		this.mainApp.getRootLayout().setDisable(true);	
					
	            		  delay(3000, () -> afteranimation()
	            				  );
	            			
	              }
	         break;	
	      
	        case "Eisenschwert":
	        	
	            if(player.getOptionalGegner().isPresent()) {
	            		
	            	this.mainApp.getRootLayout().setDisable(true);	
					
	            	
	      		  player.useItem(listView.getSelectionModel().getSelectedIndex());

	      		 
	            	
	      		  if(player.getOptionalGegner().isPresent()) {
	  			
	      			  delay(2000, () -> {
	      				
	      				  this.infos.setText(player.getUseItemText());
	      				  
	      				  
	      				  delay(2000, () -> {
	      					  
	      					  player.getDamaged(player.getGegner().calculateDamagefromAttack());
	      					   spielverlorenPrüfen();
	      					  this.infos.setText(player.getGegner().getOpponentName()+" hat dich angegriffen!"+"\n"+"Du hast noch "+player.getHp()+" HP.");  
	      					  lebensAnzeigeAktualisieren();
	      					  this.mainApp.getRootLayout().setDisable(false);  
	      					  
	      					  
	  	      			  } );
	      				  
	      				  
	      			  } );
	      			
	      		  }
	      		  else {
	  			
	      			  delay(2000, () -> {
	      				this.mainApp.getRootLayout().setDisable(false);  
	      				this.infos.setText(player.getUseItemText());  
	      				player.kill();
	      				spielgewonnenPrüfen();
	      				this.itemlisteImRaumAktualisieren();
	      				this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
	      				
	      			  } );  
	      			
	      		  	}
	  			
	            }
	            else {
	            
	            	this.mainApp.getRootLayout().setDisable(true);	
					
          		  delay(1500, () -> afteranimation()
          				  );	
	            	
	            	
	            }		
	        break;
	        
	        default:
	            System.out.println("Keinen Hunger?");
	            break;
	    }
	
	}
	
    private void spielverlorenPrüfen() {
		
	if(!player.isAlive()) {
		
	this.mainApp.initGestorbenDialog();
		
		
	}
    	
    	
	}

    private void spielgewonnenPrüfen() {
		
	if(player.isAlive() && mainApp.endgegnerPrüfen(player.getCurrentRoom()) && player.getOptionalGegner().isEmpty()) {
		
	this.mainApp.initGewonnenDialog();
		
	}
    	
    	
	}
    

	public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
      }
	
	
	
	
	private void inventarprüfen() {
		
	if(player.getAnzahlItems()!=listView.getItems().size()) {
		inventarAktualisieren();		
	}
		
	}
	
	private void raumbeschreibungAktualisieren() {
		this.raumbeschreibung.setText(getCurrentRoomDescriptionFromPlayer()+"\n"+getItemsfromCurrentRoom());
		this.infos.setText("");	
		
	}
	
	private void lebensAnzeigeAktualisieren() {
	
	    this.hpAnzeige.progressProperty().addListener( new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					 double progress = newValue == null ? 0 : newValue.doubleValue();
				        if (progress < 0.2) {
				        	hpAnzeige.setId("red-bar");
				        } else if (progress < 0.4) {
				        	hpAnzeige.setId("orange-bar");
				        } else if (progress < 0.6) {
				        	hpAnzeige.setId("yellow-bar");
				        }
				        else if (progress < 0.8) {
				        	hpAnzeige.setId("lightgreen-bar");
				        }
				        else {   	
				        	hpAnzeige.setId("darkgreen-bar");
				        }
				      }

				    }); 
		
		
		Integer hp=player.getHp();
		this.hpanzeige.setText(hp.toString());
		Double doublehp=(double) player.getHp();
		this.hpAnzeige.setProgress(doublehp/100);
	
	}
	
	private void itemlisteImRaumAktualisieren() {
		
		Room newroom=player.getCurrentRoom();
		
		ObservableList<String> itemnames = FXCollections.observableArrayList();
		itemnames.addAll(newroom.getItemNamesinRoom());
		this.itemsimraum.setItems(itemnames);
	
	}
	
	
	private void inventarAktualisieren() {
	
	this.listView.getItems().clear();	
	List<String>itemnames=player.getItemNames();
	this.inventarLeer.setVisible(false);
	if(itemnames.isEmpty()) {
		this.inventarLeer.setVisible(true);	
	}
	
		ObservableList<String> elements = FXCollections.observableArrayList();
		elements.addAll(itemnames);
		this.listView.setItems(elements);
		
		this.listView.setCellFactory(param -> new ListCell<String>() {
			/*view the image class to display the image*/
			private ImageView displayImage = new ImageView();
			
			@Override
			public void updateItem(String name, boolean empty) {
			super.updateItem(name, empty);
			if (empty) {
			setText(null);
			setGraphic(null);
			setStyle(null);
			
			} else {
		
			displayImage.setImage(passendesLogofinden(name)); 
			
			setText(name);
			setGraphic(displayImage);
			setStyle("-fx-font-weight: bold;"+"-fx-font-size: 15px;");		
			
			}
			}
			});
		
	}
	
	private Image passendesLogofinden(String itemname) {
		
		HashMap<String,Image> logos= new HashMap<String,Image>();
		
		logos.put("eisenschwert", new Image("de/fh_zwickau/oose/zuul04/item_logos/eisenschwert.png"));
		logos.put("heiltrank", new Image("de/fh_zwickau/oose/zuul04/item_logos/heiltrank.png"));
		
		
		return logos.get(itemname.trim().toLowerCase());
		
	}
	
	@FXML
	private void handleItemBeschreibung() {
	
	if(this.listView.getSelectionModel().getSelectedItem()!=null) {
		
		String itemDescription=player.getItemDescription(this.listView.getSelectionModel().getSelectedIndex());
	     this.infos.setText(itemDescription);
		
	}
			
	}
	
	public String getCurrentRoomDescriptionFromPlayer() {
		
	return player.getCurrentRoom().getLongDescription();
		
	}
	
	public String getItemsfromCurrentRoom() {
		
	return player.getCurrentRoom().getItemsinRoom();
		
	}
	
	
	@FXML
	private void handleSpielanleitung() {
	
		mainApp.initSpielanleitung();
			
	}
	
	
	@FXML
	private void handleSchwierigkeitsgradWählen() {
	
		mainApp.initSchwierigkeitsgradWählen();
			
	}
	
}
