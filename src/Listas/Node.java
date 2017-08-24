package Listas;

public class Node {
	private int dato;
	private Node nextNode;
	
	//Constructores
	Node(int dato){
		this.dato = dato;
		this.nextNode = null;
	}
	Node(){
		this.dato = 0;
		this.nextNode =  null;
	}
	//Setters y getters
	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	
	
}
