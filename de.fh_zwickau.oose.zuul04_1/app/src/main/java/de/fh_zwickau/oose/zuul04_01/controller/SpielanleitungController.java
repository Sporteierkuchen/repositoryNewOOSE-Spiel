package de.fh_zwickau.oose.zuul04.controller;

import de.fh_zwickau.oose.zuul04.MainApp;
import de.fh_zwickau.oose.zuul04.model.Player;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;


public class SpielanleitungController {

	private MainApp mainApp;

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
	
	@FXML
	private void handleZurückButton() {

		mainApp.getPrimaryStage().setScene(mainApp.getScene());
	
	}
	
	
}