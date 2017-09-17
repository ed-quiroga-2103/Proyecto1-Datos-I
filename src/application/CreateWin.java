package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class CreateWin extends Application {
	
	
	TextField text;
	
	Stage stage = new Stage();

	CreateWin(){}
	
	
	public void start() {
		try {
			stage.initModality(Modality.APPLICATION_MODAL);
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("NewStore.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public String getText(){
		
		return text.getText();
		
	}
	
	public void close(){
		
		Platform.exit();
		
	}
	
}


