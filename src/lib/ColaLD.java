package lib;

import lib.ColaTDA;

public class ColaLD implements ColaTDA{
	class Nodo {
		int info;
		Nodo sig;
	}
	Nodo primero;
	Nodo ultimo;

	
	public void InicializarCola() {
		primero = null;
		ultimo = null;
	}

	public void Acolar(int x) {
		Nodo aux = new Nodo();
		aux.info = x;
		aux.sig = null; 
		if (ultimo != null)
			ultimo.sig = aux; 
		ultimo = aux;
		
		if (primero == null) 
			primero = ultimo;
	}

	public void Desacolar() {
		primero = primero.sig;
		
		if (primero == null) ultimo = null;
	}

	public boolean ColaVacia() {
		return (ultimo == null);
	}

	public int Primero() {
		return primero.info;
	}
}