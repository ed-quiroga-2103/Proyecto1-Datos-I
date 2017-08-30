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
		
		File config = new File("/home/eduardo/workspace/Proyecto 1/JSON-Stores/.config.json");
		
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
		System.out.println("adding");
		JSONStore newStore = new JSONStore(name, path);
		
		for(int i = 0; i < this.getLength();i++){
			if(this.refs.get(i).toString().equals(name)){
				System.out.println("Carpeta existente");
				return;
			}
		}
		
		System.out.println("Root: " + getRoot());
		System.out.println("New root: "+ newStore.getPath());
		
		JSONManager current =  new JSONManager(getRoot());
		
		//Si el root es nulo
		if(getRoot()==null){
			System.out.println("Root is null");
			
			//Actualiza el root
			setRoot(newStore.getPath());
			
			
			//Actualiza el next y el prev de el nuevo nodo
			newStore.setNext(null);
			newStore.setPrevious(null);
			
			System.out.println("Updated root: "+getRoot());
			
			//Actualiza el length
			this.length++;
			
			this.refs.add(name);
			
			//Sobreescribe el archivo
			rw();
			
			return;
		}

		else{
			
			System.out.println("Next pointer: " + current.getArg(".config", "next"));
			
			System.out.println("Current root: " + current.getPathWrite());
			
			while(current.getArg(".config", "next") != (null)){
				
				current =  new JSONManager((String) current.getArg(".config","next"));
				
				System.out.println("In while; new current: " + (String)current.getPathRead());
				
			}
			
			
			String next = newStore.getPath();
			
			String prev = current.getPathRead();
			
			System.out.println("New next: " + next);
			
			System.out.println("New prev: " + prev);
			
			
			System.out.println("Final current: " + (String) current.getPathRead());
			
			
			current.setArg(".config", "next", next);
			System.out.println("Current 'next' pointer setted");
			
			
			JSONManager manager = new JSONManager(newStore.getPath());
			
			
			manager.setArg(".config", "prev", prev);
			System.out.println("NewStore 'prev' path setted: "+ prev);
			
			
			newStore.setNext(null);
			System.out.println("NewStore 'next' path setted");
			
			
			this.refs.add(name);
			
			this.length++;
			
			rw();	
			
		}
		
		
		return;
		
		
		
		
		
		
	}
	
	
	
	@SuppressWarnings("unused")
	private boolean deleteComprobation(String name){
	
		for(int i = 0; i < this.getLength();i++){
			if(this.refs.get(i).toString().equals(name)){
				return true;
			}
		}
	
		return false;
	
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
					
		/*manager.addStore("Store1");
		
		manager.addStore("Store2");
		
		manager.addStore("Store3");
		*/
		
		manager.addStore("Store4");
		
		System.out.println(manager.getLength());
	}

}
