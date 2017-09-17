package Documents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.Object;

import JSON.FileCreator;
import JSON.JSONManager;
import Stores.JSONStore;
import Stores.JSONStoreManager;

public class Document {
	
	private String primary;
	private String foreign;
	private String path;
	
	private int length;
	
	private JSONObject objs = new JSONObject();
	
	private String next;
	private String prev;
	
	private JSONArray refs;
	
	Document(String path){

		this.foreign = (String) new JSONManager(path).getArg(".config", "filename");
		
		this.path = path;
		
		
	}
	Document(){
	}
		
	private void load() {
		
		String config = this.path;
		
		JSONManager manager =  new JSONManager(config);
		
		
		this.refs = (JSONArray) manager.getArg(".config", "refs");
		
	}

	
	
	//Debe ser movida a un manager
	@SuppressWarnings("unchecked")
	public void createDoc(String name) throws FileNotFoundException, IOException, ParseException{
		
		load();
		
		System.out.println(this.refs);
		
		FileCreator creator = new FileCreator(this.path);
		
		creator.createFile(name);
		
		
		creator.addInt("length", length);
		creator.addValue("primary", name);
		creator.addValue("foreign", foreign);
		
		creator.addObject("objs", objs);
		
		
		JSONManager manager = new JSONManager(this.path);
		
		JSONParser parser = new JSONParser();
		
		Object obj;
		
		obj = parser.parse(new FileReader(this.path+"/.config.json"));
		JSONObject newJSON = (JSONObject) obj;
		
		
		System.out.println(newJSON.get("refs"));
		
		
		JSONArray newRefs= (JSONArray) newJSON.get("refs");
		
		
		System.out.println(newRefs.toString());

		
		
		try{
			
			System.out.println("flag");
			
			for(int i = 0; i < newRefs.size();i++){
			System.out.println(newRefs.get(i));		
				if(newRefs.get(i).toString().equals(name)){
					
					System.out.println("Carpeta existente");
					
					System.out.println("done");
					
					return;
				}
			}
		
			System.out.println("in try");
			newRefs.get(0);
			creator.addValue("root", (String) newRefs.get(0));
			
			System.out.println("1");
			next= (String) newRefs.get(0);
			prev= (String) newRefs.get(newRefs.size()-1);
			
			System.out.println("5");
			
			creator.addObject("next", next);			
			creator.addObject("prev", prev);

			manager.setArg((String) newRefs.get(newRefs.size()-1), "next", name);
			
			manager.setArg((String) newRefs.get(0), "prev", name);
			

			creator.commit();

			System.out.println(newRefs.toString());
			
			
			newRefs.add(name);
			

			
			manager.setArg(".config", "refs", newRefs);
						
			System.out.println("done");
			
			return;
				
			
		}
		catch(Exception e){
		
			System.out.println("ROOT");
			
			creator.addObject("next", next);			
			creator.addObject("prev", prev);
			
			creator.commit();

			
			System.out.println(newRefs.toString());
			
			newRefs.add(name);
			
			manager.setArg(".config", "refs", newRefs);
			
			System.out.println(newRefs.toString());
			
			return;
			
		}
		
		
		
		
		
		
		
		
	}
	
	
//---------------------------------------------------------------------------------------------------------
	

	
	public static void main(String[] args) {
		
		try {
			
			Document doc = new Document();
			
			doc.newDoc("Store1", "Doc3");
			
			
			
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	public void newDoc(String storeName, String docName) throws FileNotFoundException, IOException, ParseException{
		
		JSONStoreManager manager = new JSONStoreManager();
		
		manager.addStore(storeName);
		
		JSONManager man = new JSONManager(manager.getPath()+"/"+storeName);
		
		System.out.println(man.getArg(".config", "refs"));
		
		Document doc1 = new Document(manager.getPath()+storeName);
				
		doc1.createDoc(docName);
		
		System.out.println();
		
		
	}

	
	public void deleteDoc(String storeName, String docName) throws FileNotFoundException, IOException, ParseException{
		
		
		
		if(new File(new JSONStoreManager().getPath() + storeName + "/" + docName+".json").exists()){
			
			JSONManager manager = new JSONManager(this.path);
			
			JSONParser parser = new JSONParser();
			
			Object obj;
			
			obj = parser.parse(new FileReader(this.path+"/.config.json"));
			JSONObject newJSON = (JSONObject) obj;
			
			
			String newNext = (String) manager.getArg(docName, "next");
			
			String newPrev = (String) manager.getArg(docName, "prev");
			
			manager.setArg(newNext, "prev", newPrev);
			
			manager.setArg(newPrev, "next", newNext);
			
			
			
			System.out.println(newJSON.get("refs"));
			
			
			JSONArray newRefs= (JSONArray) newJSON.get("refs");
			
			newRefs.remove(docName);
			
			manager.setArg(".config", "refs", newRefs);
			
			
			new File(new JSONStoreManager().getPath() + storeName + "/" + docName+".json").delete();
		
		System.out.println("deleted");
		}else{System.out.println("no file");}
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
//--------------------------------------------------------------------------------------------------------
	
	public String getPrimary() {
		return primary;
	}


	public void setPrimary(String primary) {

		JSONManager manager = new JSONManager(this.path);
		manager.setArg(this.primary, "primary", primary);
	
	}


	public String getForeign() {
		return foreign;
	}


	public void setForeign(String foreign) {
		this.foreign = foreign;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public JSONObject getObjs() {
		return objs;
	}


	public void setObjs(JSONObject objs) {
		this.objs = objs;
	}


	public String getNext() {
		return next;
	}


	public void setNext(String next) {
		this.next = next;
	}


	public String getPrev() {
		return prev;
	}


	public void setPrev(String prev) {
		this.prev = prev;
	}


	public JSONArray getRefs() {
		return refs;
	}


	public void setRefs(JSONArray refs) {
		this.refs = refs;
	}

}

//---------------------------------------------------------------------------------------------------------
	
	

