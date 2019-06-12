package run;

import lib.*;

public class TP {

	public static void main(String[] args) {
		GrafoTDA grafo = new GrafoLD();
		grafo.AgregarVertice(1);
		grafo.AgregarVertice(2);
		grafo.AgregarVertice(3);
		grafo.AgregarVertice(4);
		grafo.AgregarVertice(5);
		grafo.AgregarVertice(6);
		grafo.AgregarVertice(7);
		
		grafo.AgregarArista(1, 2, 1);
		grafo.AgregarArista(1, 3, 1);
		grafo.AgregarArista(2, 4, 1);
		grafo.AgregarArista(2, 5, 1);
		grafo.AgregarArista(3, 6, 1);
		grafo.AgregarArista(3, 7, 1);

		grafo.RecorrerBFS(1);
	}
}
