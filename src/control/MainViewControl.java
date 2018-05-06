package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainViewControl {
	
	//FXML
	
	@FXML private Button hostButton;
	@FXML private Button connectButton;
	
	//FXML Methods
	
	@FXML private void hostGame() {
		System.out.println("Hosting Game");
		try {
			Stage secondaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ServerView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/material-fx-v0_3.css").toExternalForm());
			secondaryStage.setTitle("HostGame");
			secondaryStage.setScene(scene);
			secondaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	@FXML private void connectGame() {
		System.out.println("Joining Game");
		try {
			Stage secondaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ClientView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/material-fx-v0_3.css").toExternalForm());
			secondaryStage.setTitle("ClientGame");
			secondaryStage.setScene(scene);
			secondaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	

}
