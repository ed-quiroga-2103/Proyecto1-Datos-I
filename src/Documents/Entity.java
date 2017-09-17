package Documents;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.lang.Object;

import JSON.JSONManager;

public class Entity {
	
		
		String path;
		String primary;
		String foreign;
		
		String refs;
	
		JSONObject obj = new JSONObject();
		
		Entity(String name, Document foreign){
			
			this.path = foreign.getPath();
			
			this.primary = name;
			
			this.foreign = foreign.getPrimary();
			
			
			
			
		}
	
		@SuppressWarnings("unchecked")
		public void addVar(String name, String var){
			
			obj.put(name, var);	
			
		}
		
		@SuppressWarnings("unchecked")
		public void addVar(String name, int var){
					
			obj.put(name, var);	
							
		}
		
		@SuppressWarnings("unchecked")
		public void addVar(String name, boolean var){
			
			obj.put(name, var);	
			
		}
		
		@SuppressWarnings("unchecked")
		public void addVar(String name, float var){
			
			obj.put(name, var);	
			
		}
	
		@SuppressWarnings("unchecked")
		public void addToObjs(String name){
			
			System.out.println(this.path+"/"+this.primary);
			
			JSONObject objs;
			
			JSONManager manager = new JSONManager(this.path);
			
			JSONParser parser = new JSONParser();
			
			try {
				
				System.out.println(manager.getArg(this.foreign, "objs").toString());
				
				Object objeto = parser.parse((String) manager.getArg(this.foreign, "objs").toString());
				
				JSONObject newJSON = (JSONObject) objeto;
				
				objs = newJSON;
				
					
				System.out.println(objs);
				
				objs.put(name, obj);
				
				
				manager.setArg(this.foreign, "objs", objs.toJSONString());
				
				
				System.out.println(manager.getArg(this.foreign, "objs"));
				
				
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
	
	//-----------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		Document doc1 = new Document("/home/eduardo/workspace/Proyecto 1/JSON-Stores/Store3");
		
		
		
		System.out.println(doc1.getPath());
		
		
		//doc1.createDoc("Doc1");
		
		Entity obj1 = new Entity("Carros", doc1);
		
		System.out.println(obj1.foreign);
		
		obj1.addVar("Puertas", 4);
		obj1.addVar("Ventanas", 4);
		obj1.addVar("Marca", "Toyota");
		obj1.addVar("Modelo", "Yaris");
		obj1.addVar("Owner", "Eduardo Quiroga");

		
		
		obj1.addToObjs("Carros");
		
	}

}
