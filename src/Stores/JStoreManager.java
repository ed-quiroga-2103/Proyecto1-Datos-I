package Stores;

import JSON.FileCreator;
import JSON.JSONManager;

import java.io.File;

public class JStoreManager {
				
	private String previousPath = null;
	private String path = "/home/eduardo/workspace/Proyecto 1/JSON-Stores/";
	private String root = null; 
	

	public String getPreviousPath() {
		return previousPath;
	}

	public void setPreviousPath() {
		
		int counter = 0;
		String prev = "";
		char[] comp = "/".toCharArray();
		
		char[] pathArray= path.toCharArray();
		for(int i = pathArray.length -1 ; i >= 0; i--){
			if(counter == 2){
				for(int j = 0; j <= i; j++){

					prev = prev + pathArray[j];
				}
				prev = prev + "/";
				this.previousPath = prev;
				return;
				
			}if(comp[0] == pathArray[i]){
				
				counter++;
				
				
			}
		}
		
	}

	JStoreManager(){}

	public void newStore(String name){
		new File(path + name).mkdir();
		
		FileCreator creator = new FileCreator(path+name);

		if(this.root == null){
		
			creator.createFile(".config");
		
			creator.addValue("Next", null);
			creator.addValue("Previous", null);
		
			creator.commit();
			
			setRoot(path+name);
			
			System.out.println("Archivo .config creado");
		}else{
			
			
			
		}
	}
	
	public void deleteStore(String name){
		new File(path + name).delete();
		System.out.println("Store deleted");
	}
	
	
	public String getStorePath(String name){
		
		return path + name;
	}
	
	
	public String getPath() {
		return path;
	}

	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public void setPreviousPath(String previousPath) {
		this.previousPath = previousPath;
	}

	public static void main(String[] args) {	
		
		JStoreManager manager = new JStoreManager();
		
		File file = new File("/home/eduardo/workspace/Proyecto 1/JSON-Stores/.config.txt");
		
		manager.newStore("CAR");
		
		if(file.exists()){
			
			String configPath = "/home/eduardo/workspace/Proyecto 1/JSON-Stores/";
			
			JSONManager jmanager = new JSONManager(configPath, configPath);
						
			System.out.println(manager.getRoot());
			
		}else{
			FileCreator creator = new FileCreator("/home/eduardo/workspace/Proyecto 1/JSON-Stores/");
			
			creator.createFile(".config");
			
			creator.addValue("root", manager.getStorePath("CAR"));
			creator.addInt("length", 0);
			
			creator.commit();
			
		}
		
		System.out.println(manager.getPath());	
		
	}

}
