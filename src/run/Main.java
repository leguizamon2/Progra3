package run;

import java.time.Instant;
import lib.*;

public class Main 
{
	public static int SIZE = 18;

	public static void main(String[] args) 
	{
//		GrafosTDA grafo = new GrafoDinamic();
//		
//		int[] a = {1,2,3,4,4,4};
//		
//		System.out.println(CuantasVecesMejor(a, 4, 6));
//		
		System.out.println(Ej3(3, 16));
		System.out.println(Ej32(3, 16));
	}
	
	
	
	public static int Ej3(int a, int n)
	{
		if (n==1)
		{
			return a*a;
		}
		else
		{
			return a*a*Ej3(a, n/2);
		}
	}
	
	public static int Ej32(int a, int n)
	{
		if (n==1)
		{
			return a;
		}
		else
		{
			return Ej32(a, n/2)*Ej32(a, n/2);
		}
	}
	
	public static boolean Ej2() 
	{
		int[] a = {1,2,3,4,5,6,7,8,9,10};
		int i = 0;
		

		System.out.println(Ej21(a, 14, 0, 9));
		
		
		return true;
	}
	
	public static boolean Ej21(int[] a, int x, int i, int f) 
	{
		int pm = ((f-i)/2) + i;
		
		
		if(a[pm] == x)
		{
			return true;
		}
		else
		{
			if(f==i)
				return false;
			else
			if(a[pm] < x)
				return Ej21(a, x, pm+1, f);//
			else
				return Ej21(a, x, i, pm);
		}
	}
	

	
	//Determinar el tiempo de ejecucion en BigO de los siguiente fragmentos de programa
	public static void Calculo(double a, double b, double c)
	{
		double resultado;
		resultado = a+b+c+(a+b-c)/(a+b)+4.0;//constante
		
		
		System.out.println(resultado);
	}
	
	public static float Suma(float[] arreglo, int cantidad)
	{
		float suma = 0;
		for(int i = 0; i < cantidad; i++)
		{
			suma += arreglo[i];
		}
		
		return suma;
	}
	
	
	public static void Transpuesta(int[][] matriz)
	{
		for(int i = 0; i< 18; i++)
		{
			for(int j = i+1; j< 18; j++)
			{
				//ASIGNACION 1
				//ASIGNACION 2
				//ASIGNACION 3
			}
		}
	}
	
	public static long Fibonacci(long n) 
	{
		if(n==0 || n==1)
			return 1;
		else
			return Fibonacci(n-1) + Fibonacci(n-2);
	}
	

	public static long FibonacciFor(int n) 
	{
		long[] f = new long[n+1];
		
		f[0] = 1;
		f[1] = 1;
		
		for(int i=2 ; i<= n; i++)
		{
			f[i] = f[i-1] + f[i-2];
		}
		
		return f[n];
	}
	
	public static int CuantasVeces(int t, int[] a, int v, int p)
	{
		if(p<0)
			return 0;
		else
		if(a[p] == v)
		{
			return 1 + CuantasVeces(t, a, v, p-1);
		}
		else
		{
			return 0+CuantasVeces(t, a, v, p-1);
		}
	}
	
	public static int CuantasVecesMejor(int[] a, int v, int t)
	{
		if(t == 0)
			return 0;
		else
		if(a[t-1] == v)
		{
			return 1 + CuantasVecesMejor(a, v, t-1);
		}
		else
		{
			return CuantasVecesMejor(a, v, t-1);
		}
	}
	
	
} 

