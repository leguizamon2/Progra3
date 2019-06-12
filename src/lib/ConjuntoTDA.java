package lib;

public interface ConjuntoTDA {

	void InicializarConjunto();//CTE
	void Agregar(int x);//LINEAL
	void Sacar(int x);//CTE
	boolean Pertenece(int x);//LINEAL
	boolean ConjuntoVacio();//CTE
	int Elegir();//CONSTANTE
}
