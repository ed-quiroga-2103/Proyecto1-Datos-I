package application;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;




public class Main extends Application {
	
	
	Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			primaryStage.setTitle("LinkedDB");
			
			stage = primaryStage;
			
			new DirectoryViewer().show();
			this.pathWindow();
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,1000,800);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void setNodes(){
		
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.home")));
        File choice = dc.showDialog(stage);
        if(choice == null || ! choice.isDirectory()) {
            System.out.println("File is invalid");

        } else {
            SampleController.tree.setRoot(getNodesForDirectory(choice));
        }
	}

	public TreeItem<String> getNodesForDirectory(File file) { //Returns a TreeItem representation of the specified directory
    TreeItem<String> root = new TreeItem<String>(file.getName());
    for(File f : file.listFiles()) {
        System.out.println("Loading " + f.getName());
        if(f.isDirectory()) { //Then we call the function recursively
            root.getChildren().add(getNodesForDirectory(f));
        } else {
            root.getChildren().add(new TreeItem<String>(f.getName()));
        }
    }
    return root;
}	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void pathWindow(){
	try{
		Stage primaryStage = new Stage();
		
		primaryStage.initModality(Modality.APPLICATION_MODAL);

		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("PathWindow.fxml"));
		Scene scene = new Scene(root,400,200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
	} catch(Exception e) {
		e.printStackTrace();
	}

	}
	
	
}
