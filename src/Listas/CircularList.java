package Listas;
public class CircularList {
	
	private DoubleNode root;
	private int length;
	
	
	CircularList(){
		this.root = new DoubleNode();
		this.length = 0;
	}
	
	CircularList(DoubleNode root){
		this.root = root;
		this.length = 1;
	}
	
	CircularList(int firstData){
		this.root = new DoubleNode(firstData);
		this.length = 1;
	}
	
	
	public void add(int data){
	
		DoubleNode newNode = new DoubleNode(data);
		
		if(root.getNextNode()==null){
			root.setNextNode(newNode);
			root.setPreviousNode(newNode);
			newNode.setNextNode(root);
			newNode.setPreviousNode(root);
			length++;
			return;
		}else{
			DoubleNode current = root;
			
			while(current.getNextNode()!=getRoot()){
				
				current = current.getNextNode();
				
			}
			
			current.setNextNode(newNode);
			newNode.setNextNode(root);
			newNode.setPreviousNode(current);
			root.setPreviousNode(newNode);
			length++;

			return;
		}
	}
	
	public void delete(int dato){
		
		if(root.getDato()==dato){
			if(root.getNextNode()!= null){
				root.getNextNode().setPreviousNode(root.getPreviousNode());
				root.getPreviousNode().setNextNode(root.getNextNode());
				
				setRoot(root.getNextNode());
				length--;
				
				return;
			}else{
				setRoot(null);
				setLength(0);
			}
			
		}else{
			DoubleNode current = root;
			while(current.getNextNode().getDato()!=dato){
				current = current.getNextNode();
			}
			
			current.setNextNode(current.getNextNode().getNextNode());
			current.getNextNode().setPreviousNode(current);
			length--;
			
			return;
		}
	}

	public DoubleNode getNode(int ind){
		
		if(ind==0){
			
			return getRoot();
		}else{
			
			DoubleNode current = root;
			int cont = 0;
			
			int iteraCont = 0;
			
			while(cont != ind){
				iteraCont++;
				
				current = current.getNextNode();
				cont++;
				
				if(iteraCont > 10000){
					System.err.println("Error en la busqueda: Maxima iteraciion alcanzada");
				}
				
			}
			
			return current;
			
		}
		
	}
	
	//-------------------------------------------------------------
	
	public DoubleNode getRoot() {
		return root;
	}

	public void setRoot(DoubleNode doubleNode) {
		this.root = doubleNode;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int len) {
		this.length = len;
	}

	//-------------------------------------------------------------

	public static void main(String[] args) {
	}

}
