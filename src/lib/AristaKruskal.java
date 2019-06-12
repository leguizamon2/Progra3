package lib;

public class AristaKruskal implements Comparable< AristaKruskal >
{
	Integer NodoDestino;
	Integer ValorArista;
	Integer NodoOrigen;
	
	@Override
	public int compareTo(AristaKruskal arg0) {
		return this.ValorArista.compareTo(arg0.ValorArista);
	}
}


