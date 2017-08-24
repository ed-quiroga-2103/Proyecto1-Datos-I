package JSON;
import java.io.IOException;

import org.json.simple.JSONObject;

public class FileCreator {
	
	JSONManager manager= new JSONManager("/home/eduardo/workspace/Proyecto 1/JSON-Files", "/home/eduardo/workspace/Proyecto 1/JSON-Files");
	
	JSONObject obj;
	
	public static void main(String[] args) {
		FileCreator kreator =  new FileCreator();
		
		kreator.createFile("Eduardo");
		
		kreator.addValue("Nombre", "Eduardo Quiroga Alfaro");
		kreator.addValue("Universidad", "ITCR");
		kreator.addValue("Residencia", "Cartago");
		kreator.addValue("Cedula", "207900416");

		kreator.commit();
		
		System.out.println("flag");
			
	}

	
	@SuppressWarnings("unchecked")
	public void createFile(String filename){
		
		obj = new JSONObject();
		
		obj.put("filename", filename);
		
	} 
	
	@SuppressWarnings("unchecked")
	public void addValue(String arg, String value){
		
		obj.put(arg, value);
		
	}
	
	public void commit(){
		
		try {
			manager.write(obj);
		} catch (IOException e) {
			System.out.println("Error");
		}
		
	}
	
}
