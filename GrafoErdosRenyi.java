import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class GrafoErdosRenyi {
	
	int n,m;
	boolean dirigido, auto;
	String name;
	Map<Integer, Nodo> nodo = new HashMap<Integer, Nodo>();
	Map<Integer, Arista> arista = new HashMap<Integer, Arista>();
	
	public GrafoErdosRenyi(String name, int n, int m, boolean d, boolean a) {
		int[] num = new int[2];
		boolean aux=true;
		System.out.println("Se creo un objeto tipo GrafoErdosRenyi con "+n+" nodos y "+m+" aristas");
		this.n=n;
		this.m=m;
		this.name=name;
		this.dirigido=d;
		this.auto=a;
		
		num=pares(n,auto);
		Arista a1 = new Arista(num[0],num[1]);
		this.arista.put(1,a1);
		int t=1;
		do {
			num=pares(n,auto);
			aux=true;
			for(int i=0;i<t;i++) {
				Arista ai = arista.get(i+1);
				if(ai.a==num[0] && ai.b==num[1] ||(dirigido==false && ai.a==num[1] && ai.b==num[0])) {
					aux=false;
					break;
				}	
			}
			if(aux==true) {
				Arista at = new Arista(num[0], num[1]);
				this.arista.put(t+1,at);
				t++;
			}
			
		}while(t<m);
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
