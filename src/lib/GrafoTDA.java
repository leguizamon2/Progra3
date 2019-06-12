package lib;

public interface GrafoTDA {

	void InicializarGrafo();
	void AgregarVertice(int a);
	void EliminarVertice(int a);
	ConjuntoTDA Vertices();
	void AgregarArista(int v, int vf, int peso);
	void EliminarArista(int v, int vf);
	boolean ExisteArista(int v, int vf);
	int PesoArista(int v, int vf);
	void RecorrerDFS(int origen);
	void RecorrerBFS(int origen);
	void ImprimirLindo(int i);
	void mostrarMatriz();
	GrafoTDA Kruskal();
}
