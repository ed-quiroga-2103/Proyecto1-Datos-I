package Listas;


public class DoubleNode {
	
	private int dato;
	private DoubleNode nextNode;
	private DoubleNode previousNode;
	
	
	//Constructores
	DoubleNode(int dato){
		this.dato = dato;
		this.nextNode = null;
		this.previousNode = null;
	}
	DoubleNode(){
		this.dato = 0;
		this.nextNode =  null;
		this.previousNode = null;
	}
	//Setters y getters
	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public DoubleNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(DoubleNode nextNode) {
		this.nextNode = nextNode;
	}
	
	public DoubleNode getPreviousNode() {
		return previousNode;
	}
	public void setPreviousNode(DoubleNode previousNode) {
		this.previousNode = previousNode;
	}
	
}
