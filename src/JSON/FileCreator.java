package JSON;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FileCreator {
	
	String path;	
	
	JSONManager manager;
	
	JSONObject obj;
	
	public FileCreator(String path){
		this.path = path;
		this.manager = new JSONManager(path);
	}
	
	public static void main(String[] args) {
		
	}

	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public JSONManager getManager() {
		return manager;
	}

	public void setManager(JSONManager manager) {
		this.manager = manager;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
	/*
	 * Crea un nuevo archivo
	 */
	@SuppressWarnings("unchecked")
	public void createFile(String filename){
		
		obj = new JSONObject();
		
		obj.put("filename", filename);
		
	} 
	/*
	 * Agrega valores al archivo creado
	 */
	@SuppressWarnings("unchecked")
	public void addValue(String arg, String value){
		
		obj.put(arg, value);
		
	}
	/*
	 * Agrega objetos al archivo creado
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public void addObject(String arg, Object object) {
		obj.put(arg, object);

	}
	/*
	 * Agrefa ints al archivo creado
	 */
	@SuppressWarnings("unchecked")
	public void addInt(String arg, int value){
		
		obj.put(arg, value);
	}
	/*
	 * Guarda el archivo creado en memoria
	 */
	public void commit(){
		
		try {
			manager.write(obj);
		} catch (IOException e) {
			System.out.println("Error");
		}
		
	}
	/*
	 * Agrega un JSONArray en el archivo
	 */
	@SuppressWarnings("unchecked")
	public void addArray(String arg, JSONArray refs) {
		obj.put(arg, refs);
	}
	
}
