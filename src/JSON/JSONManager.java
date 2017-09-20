package JSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONManager {

	
	String pathWrite = "";
	
	String pathRead = "";
	
	
	public JSONManager(){}

	public JSONManager(String path){
		this.pathRead = path;
		this.pathWrite = path;	
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
	/*
	 * Guarda el archivo en memoria
	 */
	public void write(JSONObject writeObj) throws IOException {
		
		JSONObject obj = writeObj;
					
		try(FileWriter file = 
				new FileWriter(pathWrite + "/" + obj.get("filename") + ".json")){
			file.write(obj.toString());
		}catch (FileNotFoundException e) {
			System.out.println("Bad path for the file writing:"+ pathWrite);
		}		
		
	}
	/*
	 * Busca un argumento de el archivo JSON indicado
	 */
	public Object getArg(String file, String arg){	
		JSONParser parser = new JSONParser();
		
		Object obj;
		try {
			obj = parser.parse(new FileReader(pathRead + "/"+ file +".json"));
			JSONObject newJSON = (JSONObject) obj;
						
			return newJSON.get(arg);

		} catch (FileNotFoundException e) {
			System.out.println("Bad path for the file reading: "+ pathRead);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	
		
		
	}
	/*
	 * Modifica un argumento del archivo JSON
	 */
	@SuppressWarnings("unchecked")
	public void setArg(String file, String arg, String value){
		JSONParser parser = new JSONParser();
		
		Object obj;
		try {
			obj = parser.parse(new FileReader(pathRead + "/"+ file +".json"));
			JSONObject newJSON = (JSONObject) obj;
			
			newJSON.put(arg, value);
			
			write(newJSON);
			
			
							
		}catch (FileNotFoundException e) {
			System.out.println("Bad path for the file setting: " + pathRead);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return;	
	}
	/*
	 * Modifica un argumento del archivo JSON
	 */
	@SuppressWarnings("unchecked")
	public void setArg(String file, String arg, JSONArray value){
		JSONParser parser = new JSONParser();
		
		Object obj;
		try {
			obj = parser.parse(new FileReader(pathRead + "/"+ file +".json"));
			JSONObject newJSON = (JSONObject) obj;
			
			newJSON.put(arg, value);
			
			write(newJSON);
			
			
							
		}catch (FileNotFoundException e) {
			System.out.println("Bad path for the file setting: " + pathRead);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return;	
	}
}
