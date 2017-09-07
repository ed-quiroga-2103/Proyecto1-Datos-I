package Documents;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.Object;

import JSON.FileCreator;
import JSON.JSONManager;

public class Document {
	
	private String primary;
	private String foreign;
	private String path;
	
	private int length;
	
	private JSONObject objs = new JSONObject();
	
	private Document next;
	private Document prev;
	
	private JSONArray refs;
	
	Document(String name, String path){
		
		this.primary = name;
		this.foreign = (String) new JSONManager(path).getArg(".config", "filename");
		this.path = path;
		
		
	}
	
	//Debe ser movida a un manager
	private void createDoc(String name){
		
		FileCreator creator = new FileCreator(this.path);
		
		creator.createFile(name);
		
		creator.addArray(".config", this.refs);
		creator.addInt("length", length);
		creator.addValue("primary", name);
		creator.addValue("foreign", foreign);
		creator.addObject("next", next);
		creator.addObject("prev", prev);
		creator.addObject("objs", objs);
		
		creator.commit();
		
	}
	
	//Debe ser movida a un manager
	@SuppressWarnings("unchecked")
	public void addToObjs(String name, JSONObject obj){
		
		System.out.println(this.path+"/"+this.primary);
		
		
		JSONManager manager = new JSONManager(this.path);
		
		JSONParser parser = new JSONParser();
		
		try {
			
			System.out.println(manager.getArg("Doc1", "objs").toString());
			
			Object objeto = parser.parse(manager.getArg("Doc1", "objs").toString());
			
			JSONObject newJSON = (JSONObject) objeto;
			
			objs = newJSON;
			
			if(objs.get(name)==null){
			
			System.out.println(objs);
			
			objs.put(name, obj);
			
			
			manager.setArg("Doc1", "objs", objs.toJSONString());
			
			
			System.out.println(manager.getArg("Doc1", "objs"));
			
			}
			else{
				
				System.out.println("El elemento ya existe");
				
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
//---------------------------------------------------------------------------------------------------------
	

	
	public static void main(String[] args) {
		Document doc1 = new Document("Doc1","/home/eduardo/workspace/Proyecto 1/JSON-Stores/Store1");

		doc1.createDoc("Doc1");
		
		System.out.println(doc1.getPrimary());
	
		doc1.addToObjs("Objeto4", new JSONObject());
		
	}


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


	public Document getNext() {
		return next;
	}


	public void setNext(Document next) {
		this.next = next;
	}


	public Document getPrev() {
		return prev;
	}


	public void setPrev(Document prev) {
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
	
	

