import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrafoGeografico {
	int n;
	float d;
	boolean dirigido, auto;
	String name;
	Map<Integer, Nodo> nodo = new HashMap<Integer, Nodo>();
	Map<Integer, Arista> arista = new HashMap<Integer, Arista>();
	
	public GrafoGeografico(String name, int n, float d, boolean di, boolean a) {
		System.out.println("Se creo un objeto tipo GrafoGeografico con "+n+" nodos y distancia "+d);
		this.n=n;
		this.d=d;
		this.name=name;
		this.dirigido=di;
		this.auto=a;
		
		Random rand1 = new Random();
		Random rand2 = new Random();
		int actual=1;
		float[][] coord = new float[n][2];
		for(int x=0; x<n;x++) {
			coord[x][0]=rand1.nextFloat();
			coord[x][1]=rand2.nextFloat(); //creacion nodo 2----n
		}
		//Creacion de nodos
		for(int i=0;i<n;i++) {
			if(auto) {
				Arista aii = new Arista(i+1,i+1);
				this.arista.put(actual,aii);
				actual++;
			}
			for (int j=i+1;j<n;j++) {
				if(distancia(coord[i][0],coord[i][1],coord[j][0],coord[j][1])<=d) {
					Arista aij = new Arista(i+1,j+1);
					this.arista.put(actual,aij);
					actual++;
					if(dirigido) {
						Arista aji = new Arista(j+1,i+1);
						this.arista.put(actual,aji);
						actual++;
					}
				}
				if(i==j && auto) {
					Arista ajj = new Arista(i+1,j+1);
					this.arista.put(actual,ajj);
					actual++;
				}
			}
		}
		System.out.println("Se crearon "+(actual-1)+" aristas");
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
	public static float distancia(float ax, float ay, float bx, float by){
		float d= (float) Math.sqrt(Math.pow(ax-bx,2)+Math.pow(ay-by,2));
	   return(d);            
	}
}