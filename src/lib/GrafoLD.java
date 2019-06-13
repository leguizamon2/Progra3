package lib;

import java.util.*;

public class GrafoLD implements GrafoTDA {
	NodoGrafo origen;
	
	public GrafoTDA Prim() {
		GrafoTDA prim = new GrafoLD();//Creo el grafo que se va a retornar
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		ConjuntoTDA aux = Vertices();//Aca agarro todos los vertices
		while (!aux.ConjuntoVacio())//voy iterando todos los vertices para meterlos en un array
		{
			int v = aux.Elegir();
			aux.Sacar(v);
			vertices.add(v);
		}
		
		ArrayList<Integer> verticesRecorridos = new ArrayList<Integer>();//guardo los vertices recorridos
		verticesRecorridos.add(origen.nodo);//comienza solo con el nodo origen
		
		
		while(vertices.size() != verticesRecorridos.size()) //itero mientras que los vertices recorridos sean todos los vertices del grafo
		{
			NodoArista arista = GetMenorAdj(verticesRecorridos, vertices);//metodo que va a devolver la menor arista entre el arreglo de vertices recorridos y la disyuncion entre todos los vertices y los ya recorridos
			prim.AgregarVertice(arista.origen);//de la arista que encontramos, agregamos los nodos origen destino y la arista
			prim.AgregarVertice(arista.nodoDestino.nodo);
			prim.AgregarArista(arista.origen, arista.nodoDestino.nodo, arista.etiqueta);
			verticesRecorridos.add(arista.nodoDestino.nodo);//agrego al vertice recorrido el nodo destino
		}
		
		return prim;
	}

	public NodoArista GetMenorAdj(ArrayList<Integer> s, ArrayList<Integer> destinos)
	{
		ArrayList<Integer> auxD = new ArrayList<Integer>();
		destinos.forEach((n)->{
			auxD.add(n);
		});//guardo en un arreglo auxiliar los valores del array destinos, que en principio son todos los vertices
		
		for(int i=0; i< s.size(); i++)
		{
			if(auxD.contains(s.get(i)))
			{
				auxD.remove(s.get(i));
			}
		}
	
		//ahora destinos es lla disyuncion con S
		
		//Me traigo todas las aristas con origen en S y destino en Destinos-S
		ArrayList<NodoArista> aristas = new ArrayList<NodoArista>();
		for(int i=0; i< s.size(); i++)
		{
			NodoGrafo aux = Vert2Nodo(s.get(i));
			NodoArista auxa = aux.arista;
			while (auxa != null) 
			{
				if(auxD.contains(auxa.nodoDestino.nodo))
				{
					auxa.origen = aux.nodo;
					aristas.add(auxa);
				}
					
				auxa = auxa.sigArista;
			}
		}

		Collections.sort(aristas);
		return aristas.get(0);
	}
	
	public GrafoTDA Kruskal() 
	{
		GrafoTDA kruskal = new GrafoLD();//Creo el grafo que se va a retornar
		ArrayList<AristaKruskal> aristas = GetAristasKruskal();//Traigo la lista de aristas totales del grafo
		Collections.sort(aristas);//Ordeno las aristas de menor a mayor.
				
		ArrayList<ConjuntoTDA> conjuntos = new ArrayList<ConjuntoTDA>();// Este array list va a ser mi lista de conuntos
		//Inicialmente se va a cargar con un nuevo conjunto por cada vertice, que contenga solo un vertice
		ConjuntoTDA aux = Vertices();//Aca agarro todos los vertices
		while (!aux.ConjuntoVacio())//voy iterando todos los vertices para crear un conjunto por cada uno y agragarlo al array de conjuntos
		{
			ConjuntoTDA c = new ConjuntoLD();
			c.InicializarConjunto();
			c.Agregar(aux.Elegir());
			aux.Sacar(c.Elegir());
			conjuntos.add(c);
		}
		
		
		while(conjuntos.size() > 1) //mientras que mi arreglo de conjuntos tenga mas de un valora voya  ir iterando. Luego voy a juntar valores para terminar teniendo uno solo
		{
			AristaKruskal ak = aristas.get(0);//de la lista de aristas que esta ordenada agarro la primera, la menor
			aristas.remove(0);
			if(PertenecenADistintosConjuntos(ak.NodoOrigen, ak.NodoDestino, conjuntos))//Si los nodos origen y destino de esta arista perteneces a conjuntos distintos 
			{
				kruskal.AgregarVertice(ak.NodoOrigen);
				kruskal.AgregarVertice(ak.NodoDestino);
				kruskal.AgregarArista(ak.NodoOrigen, ak.NodoDestino, ak.ValorArista);//Agrego al grafo de retono los vertices y la arista de este momento
				
				ArrayList<ConjuntoTDA> conjuntosAux = new ArrayList<ConjuntoTDA>();//creo un conjunto auxiliar que voy a agregar el merge de los conjuntos donde participan los nodos y los que no participaron
				
				ConjuntoTDA aux1 = new ConjuntoLD();
				ConjuntoTDA aux2 = new ConjuntoLD();
								
				for (ConjuntoTDA conjuntoTDA : conjuntos)//Recorro el conjunto para mergear el origen y destino y dejar afuera el destino 
				{
					if(conjuntoTDA.Pertenece(ak.NodoOrigen))//agarro el conjuntoq ue tiene al nodo origen y lo guardo como auxiliar
					{
						aux1 = conjuntoTDA;					
					}
					else
					if(conjuntoTDA.Pertenece(ak.NodoDestino))//agarro el conjunto que tiene al nodo destino
					{
						aux2 = conjuntoTDA;
					}
					else
					{
						conjuntosAux.add(conjuntoTDA);	//si no participaron los nodos los agrego al conjunto auxiliar
					}
				}
				
				while(!aux1.ConjuntoVacio())
				{
					int e = aux1.Elegir();
					aux2.Agregar(e);
					aux1.Sacar(e);
				}//hago el merge de un conjunto donde participaron lso nodos, al otro
				conjuntosAux.add(aux2);//lo agrego al conjunto auxiliar
				conjuntos = conjuntosAux;//seteo el conjunto sobre el que eestoy iterando como el conuunto auzixliar que tiene el merge
			}
		}
		
		
		return kruskal;
	}
	
	public boolean PertenecenADistintosConjuntos(int a, int b, ArrayList<ConjuntoTDA> conjuntos)
	{
		for(int i = 0; i < conjuntos.size(); i++)
		{
			if(conjuntos.get(i).Pertenece(a) && conjuntos.get(i).Pertenece(b))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public ArrayList<AristaKruskal> GetAristasKruskal()
	{
		ArrayList<AristaKruskal> aristas = new ArrayList<AristaKruskal>();
		
		NodoGrafo aux = origen;
		while (aux != null) {
			NodoArista auxA = aux.arista;
			while(auxA != null)
			{
				AristaKruskal ak = new AristaKruskal();
				ak.ValorArista = auxA.etiqueta;
				ak.NodoDestino = auxA.nodoDestino.nodo;
				ak.NodoOrigen = aux.nodo;
				aristas.add(ak);
				auxA = auxA.sigArista;
			}
			aux = aux.sigNodo;
		}
		
		return aristas;
	}

	public void mostrarMatriz() {
		NodoGrafo aux = origen;
		NodoArista arista;
		while(aux != null) {
		System.out.print(aux.nodo + "\t" );
		arista = aux.arista;
			while(arista != null) {
				
				System.out.print(arista.etiqueta + " "  + arista.nodoDestino.nodo +  "\t");
				arista = arista.sigArista;
			}
			System.out.println();
			aux = aux.sigNodo;
		}
	}
	
	public void ImprimirLindo(int origen1) {
		NodoGrafo aux = origen;
		while (aux != null) {
			NodoArista auxA = aux.arista;
			while(auxA != null)
			{
				System.out.println("Origen: " + aux.nodo + ", Destino: " + auxA.nodoDestino.nodo + ", Arista: " + auxA.etiqueta);
				auxA = auxA.sigArista;
			}
			aux = aux.sigNodo;
		}
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
