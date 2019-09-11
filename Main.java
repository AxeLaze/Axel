import java.util.Map;
import java.io.File;
import java.io.FileWriter;

public class Main {
	
	public static void 	main(String[] args) {
		
		/*
		GrafoErdosRenyi grafo1 = new GrafoErdosRenyi("GrafoErdosRenyi #1", 30 , 150 , true , true);
		GrafoErdosRenyi grafo2 = new GrafoErdosRenyi("GrafoErdosRenyi #2", 100 , 500 , false , true);
		GrafoErdosRenyi grafo3 = new GrafoErdosRenyi("GrafoErdosRenyi #3", 500 , 2500 , true , false);
		*/
		/*
		GrafoGilbert grafo1 = new GrafoGilbert("GrafoGilbert #1", 30, 0.2f , true , true); //dirigido,auto
		GrafoGilbert grafo2 = new GrafoGilbert("GrafoGilbert #2", 100, 0.1f , true , false); //dirigido,auto
		GrafoGilbert grafo3 = new GrafoGilbert("GrafoGilbert #3", 500, 0.09f , false , false); //dirigido,auto
		*/
		/*
		 GrafoGeografico grafo1 = new GrafoGeografico("GrafoGeografico #1", 30, 0.5f, false, false);
		 GrafoGeografico grafo2 = new GrafoGeografico("GrafoGeografico #2", 100, 0.2f, false, false);
		 GrafoGeografico grafo3 = new GrafoGeografico("GrafoGeografico #3", 500, 0.1f, false, false);
		 */
		
		 GrafoBarabasiAlbert grafo1 = new GrafoBarabasiAlbert("GrafoBarabasiAlbert #1", 30, 10, true, false);
		 //GrafoBarabasiAlbert grafo2 = new GrafoBarabasiAlbert("GrafoBarabasiAlbert #2", 100, 20, true, true);
		 //GrafoBarabasiAlbert grafo3 = new GrafoBarabasiAlbert("GrafoBarabasiAlbert #3", 500, 50, false, false);
		 
		
		for(Map.Entry<Integer, Nodo> entry:grafo1.nodo.entrySet()){    
	        //int key=entry.getKey();  
	        Nodo b=entry.getValue();  
	        if(grafo1.dirigido) {
	        	System.out.println("Nodo "+b.numero+" con "+b.sal+" salidas y "+b.ent+" entradas"); 
	        }
	        else {
	        	System.out.println("Nodo "+b.numero+" con grado "+b.total); 
	        }
	    }    
		/*for(Map.Entry<Integer, Arista> entry:grafo1.arista.entrySet()){      
	        Arista b=entry.getValue();  
	        System.out.println(b.a+"--"+b.b);
	    }*/
		
		try
		{
		File archivo=new File(grafo1.name+".gv");
		FileWriter escribir=new FileWriter(archivo,true);
		if(grafo1.dirigido) {
			escribir.write("di");
		}
		escribir.write("graph {");
		escribir.write("\r\n");
		for(Map.Entry<Integer, Arista> entry:grafo1.arista.entrySet()){      
	        Arista b=entry.getValue(); 
	        if(grafo1.dirigido==false){ 
	        escribir.write(b.a+"--"+b.b);
	        }
	        else{
	        escribir.write(b.a+"->"+b.b);
	        }
			escribir.write("\r\n");
	    }
		escribir.write("}");
		escribir.close();
		}
		catch(Exception e)
		{
		System.out.println("Error al escribir");
		}
		
		
		
	}
		
}

