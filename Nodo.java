
public class Nodo {
	int numero;
	int ent,sal,total;
	
	public Nodo(int i,int sal, int ent) {
		this.numero = i+1;
		this.ent = ent;
		this.sal = sal;
		this.total=ent+sal;
		//System.out.println("Se creo un objeto tipo nodo, el nodo es: "+i);
	}
	public Nodo(int i,int total) {
		this.numero = i+1;
		this.total = total;
		//System.out.println("Se creo un objeto tipo nodo, el nodo es: "+i);
	}

}
