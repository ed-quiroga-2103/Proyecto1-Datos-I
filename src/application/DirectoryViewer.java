	package application;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class DirectoryViewer extends Application {

	Stage stage;
	
	static TreeView<String> a = new TreeView<String>();
	
	File choice;
	
    @Override
    public void start(Stage primaryStage) {
    }
    public void show(){
    	Stage primaryStage = new Stage();
    	stage = primaryStage;
        
        BorderPane b = new BorderPane();
        Button c = new Button("Load Folder");
        c.setOnAction(e -> {
        	
        	this.getNodes(a);
        	
        });
                
        this.getNodes(a);
       
        b.setTop(c);
        b.setCenter(a);
        primaryStage.setScene(new Scene(b, 200, 400));
        primaryStage.setTitle("Folder View");
        primaryStage.show();
       
    
    }

    public static void main(String[] args) {
        launch(args);
    }

    public TreeItem<String> getNodesForDirectory(File directory) { //Returns a TreeItem representation of the specified directory
        TreeItem<String> root = new TreeItem<String>(directory.getName());
        for(File f : directory.listFiles()) {
            System.out.println("Loading " + f.getName());
            if(f.isDirectory()) { //Then we call the function recursively
                root.getChildren().add(getNodesForDirectory(f));
            } else {
                root.getChildren().add(new TreeItem<String>(f.getName()));
            }
        }
        return root;
    }
    public void getNodes(TreeView<String> a){
    	
    	
                 DirectoryChooser dc = new DirectoryChooser();
                 dc.setInitialDirectory(new File(System.getProperty("user.home")));
                 choice = dc.showDialog(stage);
                 
                 if(choice == null || ! choice.isDirectory()) {
                     Alert alert = new Alert(AlertType.ERROR);
                     alert.setHeaderText("Could not open directory");
                     alert.setContentText("The file is invalid.");

                     alert.showAndWait();
                 } else {
                     a.setRoot(getNodesForDirectory(choice));
                 }
             }	
    
    
    public void updateNodes(TreeView<String> a){
    	
    	a.setRoot(getNodesForDirectory(choice));
    	
    	
    }
    
    }






