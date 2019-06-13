package run;

import lib.*;

public class TP {

	public static void main(String[] args) {
		GrafoTDA grafo1 = new GrafoLD();
		grafo1.AgregarVertice(1);
		grafo1.AgregarVertice(2);
		grafo1.AgregarVertice(3);
		grafo1.AgregarVertice(4);
		grafo1.AgregarVertice(5);
		grafo1.AgregarVertice(6);
		grafo1.AgregarVertice(7);
		
		grafo1.AgregarArista(1, 2, 1);
		grafo1.AgregarArista(1, 3, 2);
		grafo1.AgregarArista(2, 4, 3);
		grafo1.AgregarArista(2, 5, 4);
		grafo1.AgregarArista(3, 6, 5);
		grafo1.AgregarArista(3, 7, 6);

		//grafo1.ImprimirLindo(1);
		
		
		GrafoTDA grafo2 = new GrafoLD();
		grafo2.AgregarVertice(1);
		grafo2.AgregarVertice(2);
		grafo2.AgregarVertice(3);
		grafo2.AgregarVertice(4);
		grafo2.AgregarVertice(5);
		grafo2.AgregarVertice(6);
		
		grafo2.AgregarArista(1, 2, 6);
		grafo2.AgregarArista(2, 1, 6);
		
		grafo2.AgregarArista(1, 3, 1);
		grafo2.AgregarArista(3, 1, 1);
		
		
		grafo2.AgregarArista(1, 4, 5);
		grafo2.AgregarArista(4, 1, 5);
		
		
		grafo2.AgregarArista(2, 3, 5);
		grafo2.AgregarArista(3, 2, 5);
		
		
		grafo2.AgregarArista(4, 3, 5);
		grafo2.AgregarArista(3, 4, 5);
		
		
		grafo2.AgregarArista(2, 5, 3);
		grafo2.AgregarArista(5, 2, 3);
		
		
		grafo2.AgregarArista(4, 6, 2);
		grafo2.AgregarArista(6, 4, 2);
		
		
		grafo2.AgregarArista(5, 6, 6);
		grafo2.AgregarArista(6, 5, 6);
		
		
		grafo2.AgregarArista(5, 3, 6);
		grafo2.AgregarArista(3, 5, 6);
		
		
		grafo2.AgregarArista(6, 3, 4);
		grafo2.AgregarArista(3, 6, 4);
	
		
		
		grafo2.Prim().ImprimirLindo(1);
		System.out.println("KURSKAL");
		grafo2.Kruskal().ImprimirLindo(1);
	}
}
