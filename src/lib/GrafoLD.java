package lib;

import java.util.*;

public class GrafoLD implements GrafoTDA {
	NodoGrafo origen;

	public void ImprimirLindo(int origen) {
		NodoGrafo _origen = Vert2Nodo(origen);// Encuentro el nodo para el valor origen
		ArrayList<NodoArista> al = getAdyacentes(_origen);
		
		al.forEach((n) -> {
			
			System.out.println("O=" + _origen.nodo + "****" + n.etiqueta + "*****" + "D=" + n.nodoDestino.nodo);
			// Para cada arista voy a ver si el nodo tiene adyacentes o no, si tiene llamo
			// recursivamente al metodo
			// pasandole como nodo origen el nodo destino de la arista que estoy manejando
			if (_origen.arista != null) {
				ImprimirLindo(n.nodoDestino.nodo);
			}
		});

	}

	public void RecorrerDFS(int origen) {
		NodoGrafo _origen = Vert2Nodo(origen);// Encuentro el nodo para el valor origen

		ArrayList<NodoArista> al = new ArrayList<NodoArista>();// Creo una lista de arista donde voy a guardar todas las
																// aristas del nodo _origen en este momento
		NodoArista aux = new NodoArista();// Uso un nodo arista auxiliar para ir agregando las diferentes aristas a la
											// lista
		aux = _origen.arista;
		while (aux != null)// agrego las aristas
		{
			al.add(aux);
			aux = aux.sigArista;
		}

		al.forEach((n) -> {// Para cada arista voy a ver si el nodo tiene adyacentes o no, si tiene llamo
							// recursivamente al metodo
							// pasandole como nodo origen el nodo destino de la arista que estoy manejando
			if (_origen.arista != null) {
				RecorrerDFS(n.nodoDestino.nodo);
			}
		});

		System.out.println(_origen.nodo);
	}

	private ArrayList<NodoArista> getAdyacentes(NodoGrafo _origen) {
		ArrayList<NodoArista> al = new ArrayList<NodoArista>();// Creo una lista de arista donde voy a guardar todas las
																// aristas del nodo _origen en este momento
		NodoArista auxa = new NodoArista();// Uso un nodo arista auxiliar para ir agregando las diferentes aristas a la
											// lista
		auxa = _origen.arista;
		while (auxa != null)// agrego las aristas
		{
			al.add(auxa);
			auxa = auxa.sigArista;
		}

		return al;
	}

	public void RecorrerBFS(int origen) {
		NodoGrafo _origen = Vert2Nodo(origen);// Encuentro el nodo para el valor origen

		ColaTDA ngs = new ColaLD();
		ngs.Acolar(_origen.nodo);

		while (!ngs.ColaVacia()) {
			int auxint = ngs.Primero();
			NodoGrafo aux = Vert2Nodo(auxint);
			ngs.Desacolar();
			getAdyacentes(aux).forEach((n) -> {
				ngs.Acolar(n.nodoDestino.nodo);

			});
			System.out.println(aux.nodo);
		}
	}

	public void InicializarGrafo() {
		origen = null;
	}

	public void AgregarVertice(int v) { // El vertice se inserta al inicio de la lista de nodos
		NodoGrafo aux = new NodoGrafo();
		aux.nodo = v;
		aux.arista = null;
		aux.sigNodo = origen;
		origen = aux;
	}
	/*
	 * * Para agregar una nueva arista al grafo , primero se deben * buscar los
	 * nodos entre los cuales se va agregar la arista , * y luego se inserta sobre
	 * la lista de adyacentes del nodo * origen (en este caso nombrado como v1)
	 */

	public void AgregarArista(int v1, int v2, int peso) {
		NodoGrafo n1 = Vert2Nodo(v1);
		NodoGrafo n2 = Vert2Nodo(v2);
		// La nueva arista se inserta al inicio de la lista //de nodos adyacentes del
		// nodo origen
		NodoArista aux = new NodoArista();
		aux.etiqueta = peso;
		aux.nodoDestino = n2;
		aux.sigArista = n1.arista;
		n1.arista = aux;
	}

	private NodoGrafo Vert2Nodo(int v) {
		NodoGrafo aux = origen;
		while (aux != null && aux.nodo != v) {
			aux = aux.sigNodo;
		}
		return aux;
	}

	public void EliminarVertice(int v) { // Se recorre la lista de ve´rtices para remover el nodo v //y las aristas con
											// este ve´rtice.
		// Distingue el caso que sea el primer nodo
		if (origen.nodo == v) {
			origen = origen.sigNodo;
		}
		NodoGrafo aux = origen;
		while (aux != null) { // remueve de aux todas las aristas hacia v
			this.EliminarAristaNodo(aux, v);

			if (aux.sigNodo != null && aux.sigNodo.nodo == v) { // Si el siguiente nodo de aux es v, lo elimina
				aux.sigNodo = aux.sigNodo.sigNodo;
			}
			aux = aux.sigNodo;
		}
	}

	/* * Si en las aristas del nodo existe * una arista hacia v, la elimina */
	private void EliminarAristaNodo(NodoGrafo nodo, int v) {
		NodoArista aux = nodo.arista;
		if (aux != null) { // Si la arista a eliminar es la primera en //la lista de nodos adyacentes
			if (aux.nodoDestino.nodo == v) {
				nodo.arista = aux.sigArista;
			} else {
				while (aux.sigArista != null && aux.sigArista.nodoDestino.nodo != v) {
					aux = aux.sigArista;
				}
				if (aux.sigArista != null) { // Quita la referencia a la arista hacia v
					aux.sigArista = aux.sigArista.sigArista;
				}
			}
		}
	}

	public ConjuntoTDA Vertices() {
		ConjuntoTDA c = new ConjuntoLD();
		c.InicializarConjunto();
		NodoGrafo aux = origen;
		while (aux != null) {
			c.Agregar(aux.nodo);
			aux = aux.sigNodo;
		}
		return c;
	}

	/*
	 * * Se elimina la arista que tiene como origen al ve´rtice v1 * y destino al
	 * ve´rtice v2
	 */ public void EliminarArista(int v1, int v2) {
		NodoGrafo n1 = Vert2Nodo(v1);
		EliminarAristaNodo(n1, v2);
	}

	public boolean ExisteArista(int v1, int v2) {
		NodoGrafo n1 = Vert2Nodo(v1);
		NodoArista aux = n1.arista;
		while (aux != null && aux.nodoDestino.nodo != v2) {
			aux = aux.sigArista;
		} // Solo si se encontro la arista buscada , aux no es null

		return aux != null;
	}

	public int PesoArista(int v1, int v2) {
		NodoGrafo n1 = Vert2Nodo(v1);
		NodoArista aux = n1.arista;
		while (aux.nodoDestino.nodo != v2) {
			aux = aux.sigArista;
		} // Se encontro´ la arista entre los dos nodos
		return aux.etiqueta;
	}
}
