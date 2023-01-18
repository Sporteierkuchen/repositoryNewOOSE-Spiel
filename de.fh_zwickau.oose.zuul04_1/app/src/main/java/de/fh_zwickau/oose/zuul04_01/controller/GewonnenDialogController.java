package de.fh_zwickau.oose.zuul04.controller;

import de.fh_zwickau.oose.zuul04.MainApp;
import de.fh_zwickau.oose.zuul04.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;


public class GewonnenDialogController {

	private MainApp mainApp;
	private Player player;

	@FXML
	private Text infos;
	
	@FXML
	private Label geöffneteTruhen;

	@FXML
	private Label umgelegteSchalter;
	
	@FXML
	private Label getöteteGegner;
	
	@FXML
	private void initialize() {

	
		
	}
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}	
	
	public void setPlayer(Player player) {
		this.player=player;
		
	}	
	
	public void werteSetzen() {
		
		String schwierigkeitsgrad=mainApp.getSchwierigkeitsgrad();
		if(schwierigkeitsgrad.equals("")) {
			schwierigkeitsgrad="Leicht";
		}
		
		infos.setText("Schwierigkeitsgrad: "+schwierigkeitsgrad+ "\n"+ "Verbleibende HP: "+player.getHp());
		Integer umgelegteSchalter=player.getPressedLever();
		Integer getöteteGegner=player.getKills();
		Integer geöffneteTruhen=player.getOpenedChests();
		this.umgelegteSchalter.setText(umgelegteSchalter.toString());
		this.getöteteGegner.setText(getöteteGegner.toString());
		this.geöffneteTruhen.setText(geöffneteTruhen.toString());
		
	}
	
	@FXML
	private void handleNochmalSpielenButton() {

		this.mainApp.initRootLayout();
	
	}
	
	@FXML
	private void handleSchließenButton() {

	this.mainApp.getPrimaryStage().close();
	
	}
	
	
}