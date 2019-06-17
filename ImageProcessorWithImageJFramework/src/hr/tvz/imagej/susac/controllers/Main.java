package hr.tvz.imagej.susac.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

	private static VBox root;
	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		primaryStage = stage;
		
		try {
			root = (VBox)FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("Image Processor with ImageJ Framework - DEMO");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(e -> Platform.exit());
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
