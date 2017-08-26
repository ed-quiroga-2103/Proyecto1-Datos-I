package Stores;

import java.io.File;

import org.json.simple.JSONArray;

import JSON.FileCreator;
import JSON.JSONManager;

public class JSONStoreManager {

	private String root;
	private int length;
	private JSONArray refs = new JSONArray();
	private String path = "/home/eduardo/workspace/Proyecto 1/JSON-Stores/";
	
	public JSONStoreManager() {
		
		this.init();
		
		
	}
	
	private void init(){
		
		File config = new File("/home/eduardo/workspace/Proyecto 1/JSON-Stores/.config.txt");
		
		if(config.exists()){
			
			load();
			System.out.println("Loaded");
		}
		else{
			
			FileCreator creator = new FileCreator(path+"/");
			
			creator.createFile(".config");
			
			creator.addValue("root", null);
			
			creator.addInt("length", 0);
			
			creator.addValue("path", path);
			
			creator.addArray("refs", refs);
			
			
			creator.commit();
			System.out.println("Commited, Loaded");
			
			
			load();
		}
	}
	
	private void rw(){
		FileCreator creator = new FileCreator(path+"/");
		
		creator.createFile(".config");
		
		creator.addValue("root", this.root);
		
		creator.addInt("length", length);
		
		creator.addValue("path", path);
		
		creator.addArray("refs", this.refs);
		
		
		creator.commit();
	}
	
	private void load() {
		
		String config = "/home/eduardo/workspace/Proyecto 1/JSON-Stores/";
		
		JSONManager manager =  new JSONManager(config);
		
		
		this.root = (String) manager.getArg(".config", "root");
		this.length= Integer.valueOf(manager.getArg(".config", "length").toString());
		this.refs = (JSONArray) manager.getArg(".config", "refs");
		
	}

	@SuppressWarnings("unchecked")
	public void addStore(String name){
		
		JSONStore newStore = new JSONStore(name, path);
		
		for(int i = 0; i < this.getLength();i++){
			if(this.refs.get(i).toString().equals(name)){
				System.out.println("Carpeta existente");
				return;
			}
		}
		
			if(this.root == null){
				
				this.root = newStore.getPath();
				this.length++;
				
				this.refs.add(name);
				
				newStore.setNext(null);
				newStore.setPrevious(null);
					
				rw();
				
			}
			else{
				
				String current = this.root;
				
				
				while((String) new JSONManager(current).getArg(".config", "next")
				!=null){
					
					current = (String) new JSONManager(current).getArg(".config", "next");
					
					
				}
				
				String prev = (String) new JSONManager(current).getArg(".config", "prev");
				
				FileCreator manager = new FileCreator(current+"/");
				
				manager.createFile(".config");
				
				manager.addValue("next", newStore.getPath());
				manager.addValue("prev", prev);
				
				manager.commit();
	
				
				String next = (String) new JSONManager(current).getArg(".config", "next");
				
				FileCreator overwriter = new FileCreator(next);
				
				overwriter.createFile(".config");
				
				overwriter.addValue("next", null);
				overwriter.addValue("prev", current);
				
				overwriter.commit();
				
				this.refs.add(name);
				this.length++;
				
				rw();
				
				return;
				
			}
		
		
	}




//------------------------------------------------------------------------------------------------
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public JSONArray getRefs() {
		return refs;
	}

	public void setRefs(JSONArray refs) {
		this.refs = refs;
	}
//------------------------------------------------------------------------------------------------



	public static void main(String[] args) {
		JSONStoreManager manager = new JSONStoreManager();
		
		manager.addStore("Store1");
		manager.addStore("Store2");
		manager.addStore("Store3");
		manager.addStore("Store4");
		manager.addStore("Store5");
		manager.addStore("Store6");
		manager.addStore("Store7");
		

		System.out.println(manager.getLength());
	}

}
