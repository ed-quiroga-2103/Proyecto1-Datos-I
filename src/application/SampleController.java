package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import Stores.JSONStoreManager;



public class SampleController {
	@FXML
	Label label0;
	
	@FXML
	Label label1;
	
	@FXML
	Label done;
	
	boolean check = true;
	
	@FXML
	CheckBox checkbox;
	
	boolean answer = false;
	
	@FXML
	TextField field;
	@FXML
	TextField newStoreName;
	
	
	@FXML
	Button create;
	
	
	@FXML
	Button exit;
	
	CreateWin win = new CreateWin();
	
	
	
	public void gen(ActionEvent event){
		String name = "Eduardo";
		
		System.out.println(label0.getText());
	
		label0.setText(name);
		label1.setText(name);
		
		
	}
	
	public void window(ActionEvent event){
		
				System.out.println("Flag");
		
				String title = "Test";
				
				String message = "Are you sure?";
		
				Button yes, no;

			
				Stage window = new Stage();
				
				window.initModality(Modality.APPLICATION_MODAL);
				
				window.setTitle(title);
				
				
				Text text = new Text(message);
						
				text.setFont(new Font(12));
				
				VBox layout = new VBox(20);
			
				Scene scene = new Scene(layout);
			
				layout.setPadding(new Insets(10,10,10,10));

				
				yes= new Button("Yes");
				
				yes.setOnAction(e -> {
					
					answer = true;
					
					window.close();
					
				});
				
				no = new Button("No");
				
				no.setOnAction(e -> {
					
					answer = false;
					
					window.close();
					
				});
				
				layout.getChildren().addAll(text, yes, no);
				
				layout.setAlignment(Pos.CENTER);
				
				window.setScene(scene);
				
				window.showAndWait();
				
				
				System.out.println("Flag");

				return;
				
				
			}
			
	public void newStore(ActionEvent event){
		
		JSONStoreManager manager = new JSONStoreManager();
		
		manager.addStore(newStoreName.getText());
		
		done.setText("Store Created!");
	}

	public void create(ActionEvent event){
		win.start();
				
		
	}	
	
	
	}
	


