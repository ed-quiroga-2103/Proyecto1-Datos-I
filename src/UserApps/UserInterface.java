package UserApps;

import Stores.JSONStoreManager;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import Documents.Document;
import Documents.Entity;

public class UserInterface {
	
	String storeName;
	
	String docName;
	
	Entity obj;
	
	public UserInterface() {
	}
	
	public static void main(String[] args) {
		
		
	}
	
	public void newStore(String name){
		
		
		new JSONStoreManager().addStore(name);
		
		this.storeName = name;
		
	}
	
	public void newDoc(String name) throws FileNotFoundException, IOException, ParseException{
		
		
		Document doc = new Document();
		
		doc.newDoc(this.storeName, name);
		
		this.docName = name;
		
	}
	
	public void newJSON(String name){
		
		obj = new Entity(name, this.docName, this.storeName);
		
		
	}
	public void addToJSON(String var, String value){
		
		obj.addVar(var, value);
		
	}
	public void addToJSON(String var, int value){
		
		obj.addVar(var, value);
}
	public void addToJSON(String var, boolean value){
	
		obj.addVar(var, value);
	
}
	public void addToJSON(String var, float value){
		
		obj.addVar(var, value);
}	
	public void commitJSON(){
		
		
		obj.addToObjs();
		
		
	}




}

