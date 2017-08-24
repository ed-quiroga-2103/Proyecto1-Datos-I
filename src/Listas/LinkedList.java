package Listas;

public class LinkedList {

	private Node root;
	private int length;
	
	
	LinkedList(){
		this.root = new Node();
		this.length = 0;
	}
	
	LinkedList(Node root){
		this.root = root;
		this.length = 1;
	}
	LinkedList(int firstData){
		this.root = new Node(firstData);
		this.length = 1;
	}
	
	
	public void add(int data){
	
		Node newNode = new Node(data);
		
		if(root.getNextNode()==null){
			root.setNextNode(newNode);
			newNode.setNextNode(null);
			length++;
			return;
		}else{
			Node current = root;
			
			while(current.getNextNode()!=null){
				
				current = current.getNextNode();
				
			}
			
			current.setNextNode(newNode);
			newNode.setNextNode(null);
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
			Node current = root;
			while(current.getNextNode().getDato()!=dato){
				current = current.getNextNode();
			}
			current.setNextNode(current.getNextNode().getNextNode());
			return;
		}
	}

	public Node getNode(int ind){
		
		if(ind==0){
			
			return getRoot();
		}else{
			
			Node current = root;
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
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int len) {
		this.length = len;
	}

	//-------------------------------------------------------------
	
	public static void main(String[] args) {
		
		LinkedList linkedList = new LinkedList(1);
		
		
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);

		System.out.println(linkedList.getLength());
		
		System.out.println(linkedList.getNode(0).getDato());
		System.out.println(linkedList.getNode(1).getDato());
		System.out.println(linkedList.getNode(2).getDato());
		System.out.println(linkedList.getNode(3).getDato());

		linkedList.delete(3);
		System.out.println();
		
		System.out.println(linkedList.getNode(2).getDato());

	}
}
