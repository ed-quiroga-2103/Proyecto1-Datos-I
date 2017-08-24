package GUI;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WindowCreator extends Application {
	
	public static void main(String[] args){
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		
		window.onCloseRequestProperty();
		
		GridPane layout = new GridPane();
		
		Scene scene = new Scene(layout);
		
		layout.setPadding(new Insets(10,10,10,10));
		
		//--------------------------------------------------
		
		Label filename = new Label("File Name");
		
		Label name = new Label("Name");
		
		Label id = new Label("ID");
		
		//---------------------------------------------------
		
		
		TextField archField = new TextField();
		archField.setPromptText("File Name");
		
		TextField nomField = new TextField();
		nomField.setPromptText("Name");
		
		TextField cedField = new TextField();
		cedField.setPromptText("ID");
		
		
		//---------------------------------------------------
		
		Button button1 = new Button("Commit");
		Button button2 = new Button("Cancel");
		
		//---------------------------------------------------
		
		window.setScene(scene);
		
		window.show();
		
	}

}
