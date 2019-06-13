package lib;

public class NodoArista implements Comparable<NodoArista> {
	int etiqueta;
	NodoGrafo nodoDestino;
	NodoArista sigArista;
	int origen;
	
	@Override
	public int compareTo(NodoArista arg0) {
		Integer estaEtiqueta = etiqueta;
		Integer otraEtiqueta = arg0.etiqueta;
		
		return estaEtiqueta.compareTo(otraEtiqueta);
	}
}
