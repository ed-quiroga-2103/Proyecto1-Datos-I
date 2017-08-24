package Listas;

public class DoubleLinkedList {

	private DoubleNode root;
	private int length;
	
	
	DoubleLinkedList(){
		this.root = new DoubleNode();
		this.length = 0;
	}
	
	DoubleLinkedList(DoubleNode root){
		this.root = root;
		this.length = 1;
	}
	
	DoubleLinkedList(int firstData){
		this.root = new DoubleNode(firstData);
		this.length = 1;
	}
	
	
	public void add(int data){
	
		DoubleNode newNode = new DoubleNode(data);
		
		if(root.getNextNode()==null){
			root.setNextNode(newNode);
			newNode.setNextNode(null);
			newNode.setPreviousNode(root);
			length++;
			return;
		}else{
			DoubleNode current = root;
			
			while(current.getNextNode()!=null){
				
				current = current.getNextNode();
				
			}
			
			current.setNextNode(newNode);
			newNode.setNextNode(null);
			newNode.setPreviousNode(current);
			length++;

			return;
		}
	}
	
	public void delete(int dato){
		
		if(root.getDato()==dato){
			if(root.getNextNode()!= null){
			
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
		DoubleLinkedList dll = new DoubleLinkedList(1);
		
		dll.add(2);
		dll.add(3);
		dll.add(4);
		dll.add(5);
		
		System.out.println(dll.getNode(2).getDato());
		System.out.println();
		
		dll.delete(3);
		
		System.out.println(dll.getNode(2).getPreviousNode().getDato());

	}

}
