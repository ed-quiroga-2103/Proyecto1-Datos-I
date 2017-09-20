package UserApps;

import Stores.JSONStoreManager;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.print.attribute.standard.DocumentName;

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
		
		System.out.println(this.storeName);
		
		doc.newDoc(this.storeName, name);
		
		this.docName = name;
		
	}
	
	public void newJSON(String name){
		
		System.out.println(this.docName + " " + this.storeName);
		
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Entity getObj() {
		return obj;
	}

	public void setObj(Entity obj) {
		this.obj = obj;
	}




}

