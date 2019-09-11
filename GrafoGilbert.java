import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrafoGilbert {
	int n;
	float p;
	boolean dirigido, auto;
	String name;
	Map<Integer, Nodo> nodo = new HashMap<Integer, Nodo>();
	Map<Integer, Arista> arista = new HashMap<Integer, Arista>();
	
	public GrafoGilbert(String name, int n, float p, boolean d, boolean a) {
		System.out.println("Se creo un objeto tipo GrafoGilbert con "+n+" nodos y probabilidad "+p);
		this.n=n;
		this.p=p;
		this.name=name;
		this.dirigido=d;
		this.auto=a;
		
		int t=2,actual=1;
		Random rand1 = new Random();
		Random rand2 = new Random();
		for(int i=1; i<=n;i++) {
			for(int j=i; j<n;j++) {
				if(i!=j) {
					if(p>rand1.nextFloat() ) {//prob para a--b 
						//Se crea arista de i-t
						Arista ait = new Arista(i,j);
						this.arista.put(actual,ait);
						actual++;
					}
					if(p>rand2.nextFloat() && dirigido) {//prob para a--b 
						//Se crea arista de t-i
						Arista ati = new Arista(j,i);
						this.arista.put(actual,ati);
						actual++;
					}
				}
				if(p>rand1.nextFloat() && auto && i==j) {//prob para a--b 
						//Se crea arista de t-t
						Arista ait = new Arista(i,t);
						this.arista.put(actual,ait);
						actual++;
				}
			}
		}
		int[] hist_sal = new int[n];
		int[] hist_ent = new int[n];
		int[] hist_total = new int[n];
		for(Map.Entry<Integer, Arista> entry:arista.entrySet()){      
	        Arista X=entry.getValue();
	        for(int j=0;j<n;j++) {
				if(X.a==j+1) {
					hist_sal[j]++;
					hist_total[j]++;
				}
				if(X.b==j+1) {
					hist_ent[j]++;
					hist_total[j]++;
				}
	        }
	    }
		for(int i=0;i<n;i++) {
			if(dirigido) {
				Nodo current = new Nodo(i,hist_sal[i],hist_ent[i]);
				this.nodo.put(i+1,current); 
			}
			else {
				Nodo current = new Nodo(i,hist_total[i]);
				this.nodo.put(i+1,current);
			}
		}
		System.out.println("Se crearon "+(actual-1)+" aristas");
	}
	public static int[] pares(int i, boolean x ){
		int[] ab = new int[2];
		Random rnd = new Random();
		boolean y;
		do {
			y=false;
			for (int k=0; k<2;k++) {
			ab[k] = (rnd.nextInt(i) + 1);
				}
			if(x==false && ab[0]==ab[1]) {
				y=true;
			}
			}while(y==true);
	   return(ab);            
	}
}
