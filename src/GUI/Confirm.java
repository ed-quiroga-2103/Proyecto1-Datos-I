package GUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Confirm {
	static boolean answer;
	
	public static boolean window(String title, String message){
		
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
		
		return answer;
	}
	
}
