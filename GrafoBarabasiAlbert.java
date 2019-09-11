import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrafoBarabasiAlbert {
	int n,d;
	boolean dirigido, auto;
	String name;
	Map<Integer, Nodo> nodo = new HashMap<Integer, Nodo>();
	Map<Integer, Arista> arista = new HashMap<Integer, Arista>();
	
	public GrafoBarabasiAlbert(String name, int n, int d, boolean di, boolean a) {
		System.out.println("Se creo un objeto tipo GrafoBarabasi con "+n+" nodos y la probabilidad de "+d+" aristas");
		this.n=n;
		this.d=d;
		this.name=name;
		this.dirigido=di;
		this.auto=a;
		int[][] auxnodo = new int[n][3];  //matriz de nodos ent,sal,total
		for(int i=0;i<n;i++) {
			auxnodo[i][0]=0; //salida del nodo
			auxnodo[i][1]=0; //entrada del nodo
			auxnodo[i][2]=0; //total salidas entradas
		}
		Random rand1 = new Random();
		Random rand2 = new Random();
		int actual=0;
		for(int i=0;i<n;i++) {//empezamos desde la "creacion" del nodo 2
			for(int j=0;j<=i;j++) { //hacemos el barrido de nodos previos a i
				
				float prs = rand1.nextFloat();
				float pre = rand2.nextFloat();
				
				if(i!=j && prs<=(1-((float)auxnodo[j][2]/d)) && auxnodo[i][2]<d) { // union j--i
					auxnodo[j][0]++; //Crea una salida del nodo j
					auxnodo[i][1]++; //Crea una entrada del nodo i
					auxnodo[j][2]++;
					auxnodo[i][2]++;
					Arista aji = new Arista(j+1,i+1);
					this.arista.put(actual,aji);
					actual++;
				}
				if(i!=j && di && (pre<=((float)1-(auxnodo[i][2]/d))) && auxnodo[j][2]<d) { //union i--j
					auxnodo[i][0]++; //Crea una salida del nodo i
					auxnodo[j][1]++; //Crea una entrada del nodo j
					auxnodo[j][2]++;
					auxnodo[i][2]++;
					Arista aij = new Arista(i+1,j+1);
					this.arista.put(actual,aij);
					actual++;
				}
				if(i==j && auto && (prs<=(1-((float)auxnodo[j][2]/d)))) {
					auxnodo[j][0]++; //Crea una salida del nodo j
					auxnodo[j][1]++; //Crea una entrada del nodo j
					auxnodo[j][2]=auxnodo[j][2]+2;
					Arista aii = new Arista(i+1,i+1);
					this.arista.put(actual,aii);
					actual++;
				}
				
			}
			
		}
		if(di) {
			for(int k=0;k<n;k++) {
				Nodo current = new Nodo(k,auxnodo[k][0],auxnodo[k][1]);
				this.nodo.put(k+1,current);
			}
			
		}
		else {
			for(int k=0;k<n;k++) {
				Nodo current = new Nodo(k,auxnodo[k][2]);
				this.nodo.put(k+1,current);
			}
		}
		
	}
}