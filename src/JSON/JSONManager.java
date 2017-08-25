package JSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

public class JSONManager {

	
	String pathWrite = "";
	
	String pathRead = "";
	
	
	public JSONManager(){}

	public JSONManager(String pathWrite, String pathRead){
		this.pathRead = pathRead;
		this.pathWrite = pathWrite;	
	}
	
	public String getPathWrite() {
		return pathWrite;
	}

	public void setPathWrite(String pathWrite) {
		this.pathWrite = pathWrite;
	}

	public String getPathRead() {
		return pathRead;
	}

	public void setPathRead(String pathRead) {
		this.pathRead = pathRead;
	}

	public static void main(String[] args){
		
	}
	
	public void write(JSONObject writeObj) throws IOException {
		
		JSONObject obj = writeObj;
		
		
		String out = obj.toString();
		
		System.out.println(out);
	
		try(FileWriter file = 
				new FileWriter(pathWrite + "/" + obj.get("filename") + ".txt")){
			file.write(obj.toString());
			System.out.println("Copiado completado!");
			System.out.println("\nJSON Object: " + obj);
		}catch (FileNotFoundException e) {
			System.out.println("Bad path for the file");
		}		
		
	}

	public Object getArg(String file, String arg){	
		JSONParser parser = new JSONParser();
		
		Object obj;
		try {
			obj = parser.parse(new FileReader(pathRead + "/"+ file +".txt"));
			JSONObject newJSON = (JSONObject) obj;
			
			return newJSON.get(arg);

		} catch (FileNotFoundException e) {
			System.out.println("Bad path for the file");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
		
		
		
	}

}
