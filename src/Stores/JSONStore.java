package Stores;

import java.io.File;

import org.json.simple.JSONArray;

import JSON.FileCreator;
import JSON.JSONManager;

public class JSONStore {

	private String next;
	private String previous;
	private String path;
	private String name;
	private String foreign;
	private JSONArray refs;
	
	
	JSONStore(String name, String path){
		
		this.name = name;
		
		this.path = path;
	
		this.createFolder();
		
	}
	JSONStore(String name, String path, String foreign){
		this.name = name;
		this.path = path;
		this.foreign = foreign;
	}
	
	
	private void createFolder() {
		
		new File(path+name).mkdir();
		
		FileCreator creator = new FileCreator(path+name+"/");
		
		creator.createFile(".config");
		
		creator.addValue("next", null);
		
		creator.addArray("refs", refs);
		
		creator.addValue("prev", null);
		
		creator.commit();
		
	}



	public String getNext() {
		return next;
	}



	public void setNext(String next) {

		JSONManager manager = new JSONManager(path);
		
		manager.setArg(".config", "next", next);
		
	}



	public String getPrevious() {
		return previous;
	}



	public void setPrevious(String previous) {
		JSONManager manager = new JSONManager(path);
		
		manager.setArg(".config", "prev", previous);
	}


//-------------------------------------------------------------------------------------------------------------------------------------------
	public String getPath() {
		return path+this.name;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
//-------------------------------------------------------------------------------------------------------------------------------------------


	public static void main(String[] args) {
		
	}
}
