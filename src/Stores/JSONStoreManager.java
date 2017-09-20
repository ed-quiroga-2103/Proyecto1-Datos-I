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
	/*
	 *Busca el archivo .config y lo carga. Si el archivo no existe, lo crea. 
	 */
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
	/*
	 *Sobreescribe el archivo .config con datos actualizados
	 */
	private void rw(){
		FileCreator creator = new FileCreator(path+"/");
		
		creator.createFile(".config");
		
		creator.addValue("root", this.root);
		
		creator.addInt("length", length);
		
		creator.addValue("path", path);
		
		creator.addArray("refs", this.refs);
		
		
		creator.commit();
	}
	/*
	 * Carga el archivo .config
	 */
	private void load() {
		
		String config = "/home/eduardo/workspace/Proyecto 1/JSON-Stores/";
		
		JSONManager manager =  new JSONManager(config);
		
		
		this.root = (String) manager.getArg(".config", "root");
		this.length= Integer.valueOf(manager.getArg(".config", "length").toString());
		this.refs = (JSONArray) manager.getArg(".config", "refs");
		
	}
	/*
	 * Crea un Store nuevo
	 */
	@SuppressWarnings("unchecked")
	public void addStore(String name){
		System.out.println("adding");
		
		for(int i = 0; i < this.getLength();i++){
			if(this.refs.get(i).toString().equals(name)){
				System.out.println("Carpeta existente");
				return;
			}
		}
		JSONStore newStore = new JSONStore(name, path);

		
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
	/*
	 * Elimina el Store con el nombre indicado
	 */
	public void deleteStore(String name){
		
		if(deleteComprobation(name)){
			
			JSONManager current =  new JSONManager(this.path+"/"+name);
			
			String next = (String) current.getArg(".config", "next");
			String prev = (String) current.getArg(".config", "prev");
			
			File[] files = new File(this.path+"/"+name).listFiles();
			
			for(int i = 0; i < files.length; i++){
				
				files[i].delete();
				
			}
			new File(this.path+"/"+name).delete();
			
			current = new JSONManager(next);
			
			current.setArg(".config", "prev", prev);
			
			current = new JSONManager(prev);
			
			current.setArg(".config", "next", next);
			
			
			new File(this.path+"/"+name).delete();
			
			this.length--;
			
			this.refs.remove(name);
			
			rw();
			
			
		}
		
		
	}
	/*
	 * Comprueba si el Store existe para eliminarlo
	 */
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
		
	}

}
