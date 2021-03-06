package Documents;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Object;

import JSON.JSONManager;
import Stores.JSONStoreManager;

public class Entity {
	
		
		String path;
		String primary;
		String foreign;
		String next;
		
		
		String refs;
	
		JSONObject obj = new JSONObject();
		
		@SuppressWarnings("unchecked")
		public Entity(String name, String foreign, String storeName){
			
			this.path =(String) new JSONStoreManager().getPath()+ storeName;
		
			
			
			this.primary = name;
			
			this.foreign = foreign;
			
			obj.put("path", this.path);
			obj.put("primary", this.primary);
			obj.put("foreign", this.foreign);
			obj.put("next", this.next);
						
		}
		/**
		 * Añade una variable al JSON
		 */
		@SuppressWarnings("unchecked")
		public void addVar(String name, String var){
			
			obj.put(name, var);	
			
		}
		/**
		 * Añade una variable al JSON
		 */
		@SuppressWarnings("unchecked")
		public void addVar(String name, int var){
					
			obj.put(name, var);	
							
		}
		/**
		 * Añade una variable al JSON
		 */
		@SuppressWarnings("unchecked")
		public void addVar(String name, boolean var){
			
			obj.put(name, var);	
			
		}
		/**
		 * Añade una variable al JSON
		 */
		@SuppressWarnings("unchecked")
		public void addVar(String name, float var){
			
			obj.put(name, var);	
			
		}
		/**
		 * Añade el archivo a los documentos
		 */
		@SuppressWarnings("unchecked")
		public void addToObjs(){
			
			String name = this.primary;
			
			
			
			JSONObject objs;
			
			JSONManager manager = new JSONManager(this.path);
			
			
			JSONParser parser = new JSONParser();
			
			System.out.println(this.path);
			
			try {
				
			
				
				Object objeto = parser.parse(manager.getArg(this.foreign, "objs").toString());
				
				
				JSONObject newJSON = (JSONObject) objeto;
				
				objs = newJSON;
				
				

				
				
				objs.put(name, obj);
				
				
				
				//------------
				
				System.out.println(manager.getArg(this.foreign, "root"));
				if(manager.getArg(this.foreign, "root") == null){
					
					manager.setArg(this.foreign , "root", name);
					
					
				}else{
					
					JSONObject current = (JSONObject) parser.parse(newJSON.get((String) 
							manager.getArg(this.foreign, "root")).toString());
					
					while(current.get("next")!= null){
						
						System.out.println("in while");
						
						System.out.println(current.get("primary"));
						
						System.out.println(current.get("next"));
						
						String string = newJSON.get(current.get("next")).toString();
						
						
						JSONObject temp = (JSONObject) parser.parse(string);
						
						
						
						current = temp;
						
						
					}
					
					current.put("next", name);
					
					objs.put(current.get("primary"), current);
					
					
				}
				
				
				manager.setArg(this.foreign, "objs", objs.toJSONString());
				
						
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
	
	//-----------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		
		
	}

}
