package Stores;

import java.io.File;

import JSON.FileCreator;

public class JSONStore {

	private String next;
	private String previous;
	private String path;
	private String name;
	
	JSONStore(String name, String path){
		
		this.name = name;
		
		this.path = path;
	
		this.createFolder();
		
	}
	
	
	
	private void createFolder() {
		
		new File(path+name).mkdir();
		
		FileCreator creator = new FileCreator(path+name+"/");
		
		creator.createFile(".config");
		
		creator.addValue("next", null);
		
		creator.addValue("prev", null);
		
		creator.commit();
		
	}



	public String getNext() {
		return next;
	}



	public void setNext(String next) {
		FileCreator creator = new FileCreator(path+name+"/");
		
		creator.createFile(".config");
		
		creator.addValue("next", next);
		
		creator.addValue("prev", previous);
		
		creator.commit();
	}



	public String getPrevious() {
		return previous;
	}



	public void setPrevious(String previous) {
		FileCreator creator = new FileCreator(path+name+"/");
		
		creator.createFile(".config");
		
		creator.addValue("next", next);
		
		creator.addValue("prev", previous);
		
		creator.commit();
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
