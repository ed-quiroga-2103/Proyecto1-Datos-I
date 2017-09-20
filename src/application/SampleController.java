package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import Stores.JSONStoreManager;
import UserApps.UserInterface;


public class SampleController {
	@FXML
	Label label0;
	
	@FXML
	Label label1;
	
	@FXML
	Label done;
	
	
	static String a;
	static String b;
	
	boolean check = true;
	
	@FXML
	CheckBox checkbox;
	
	boolean answer = false;
	
	@FXML
	TextField field;
	@FXML
	TextField newStoreName;
	@FXML
	TextField newDocName;
	@FXML
	TextField newJSONName;
	@FXML
	TextField name;
	@FXML
	TextField dataType;
	@FXML
	TextField primary;
	@FXML
	TextField foreign;
	@FXML
	TextField defaultData;
	
	
	@FXML
	Button create;
	
	
	
	@FXML
	Button exit;
	
	CreateWin win = new CreateWin();
	
	UserInterface UI = new UserInterface();
	
	
	
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
		
		UI.newStore(newStoreName.getText());
		
	}

	public void newDoc(ActionEvent event){
		
		try {
			UI.newDoc(newDocName.getText());
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void newJSON(ActionEvent event) throws FileNotFoundException, IOException, ParseException{
		
		a = newStoreName.getText();
		
		System.out.println(a);
		
		UI.newStore(a);
		
		b = newDocName.getText();
		
		UI.newDoc(b);

		
		UI.newJSON(newJSONName.getText());
		UI.addToJSON("nombre", newJSONName.getText());
		UI.addToJSON("tipo", dataType.getText());
		UI.addToJSON("primaria", newJSONName.getText());
		UI.addToJSON("foranea", foreign.getText());
		UI.addToJSON("default", defaultData.getText());
		
		UI.setStoreName(a);
		UI.setDocName(b);
		
		System.out.println(UI.getDocName());
		System.out.println(UI.getStoreName());
		
	}
	
	public void commitJSON(ActionEvent event){
		
		UI.commitJSON();
		
	}
	
	
	public void create(ActionEvent event){
		win.start();
				
		
	}	
	
	
	}
	


