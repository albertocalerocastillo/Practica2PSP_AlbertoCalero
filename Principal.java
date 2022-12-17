package es.studium.practica2psp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Principal
{
	static List<Ciclista> lista = new ArrayList<Ciclista>();
	static List<Hilo> hilos = new ArrayList<Hilo>();
	public static void main(String[] args) throws IOException
	{
		leerLista();
		listaHilos();
		obtenerRanking();
		obtenerCiclistas();
	}

	public static void leerLista()
	{
		try
		{
			FileReader fr = new FileReader("ciclistas.txt");
			BufferedReader bf = new BufferedReader(fr);
			String line;

			while ((line = bf.readLine()) != null)
			{
					String[] lineasFichero = line.split(", ");

					int numeroDorsal = Integer.parseInt(lineasFichero[0]);
					String nombreCiclista = lineasFichero[1];
					String nombreEquipo = lineasFichero[2];
					int cantidadStamina = 0;
					int cantidadKm = 0;

						int i = 1;
						switch(i)
						{
						case 1:
							cantidadStamina = 10;
							i++;
							break;
						case 2:
							cantidadStamina = 8;
							i++;
							break;
						case 3:
							cantidadStamina = 7;
							i++;
							break;
						case 4:
							cantidadStamina = 6;
							i++;
							break;
						case 5:
							cantidadStamina = 5;
							i++;
							break;
						case 6:
							cantidadStamina = 4;
							i++;
							break;
						case 7:
							cantidadStamina = 3;
							i++;
							break;
						case 8:
							cantidadStamina = 1;
							i=1;
							break;
						}

						Ciclista c = new Ciclista();
						c.setDorsal(numeroDorsal);
						c.setNombre(nombreCiclista);
						c.setEquipo(nombreEquipo);
						c.setKilometros(cantidadKm);
						c.setStamina(cantidadStamina);
						
						lista.add(c);	
			}
			
			System.out.println(lista);
			
			
			bf.close();
			fr.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void listaHilos()
	{
		for(int i = 0; i<lista.size();i++)
		{
			Ciclista c = lista.get(i);
			Hilo h = new Hilo(c);
			hilos.add(h);
			hilos.get(i).start();
		}
		for(int i = 0; i<lista.size();i++)
		{
			try
			{
				hilos.get(i).join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void obtenerRanking() 
	{
		System.out.println();
		System.out.println("Clasificación final:");
		lista.sort(Comparator.comparing(Ciclista :: getKilometros).reversed());
		for(int i = 0; i<lista.size();i++)
		{
			System.out.println((i+ 1) + ": "+ lista.get(i).getDorsal() + "-          " +  lista.get(i).getNombre()+ " " + lista.get(i).getKilometros()+ " kilómetros");
		}
	}
	
	public static void obtenerCiclistas() throws IOException 
	{
		FileWriter fw = null;
		try 
		{
			fw = new FileWriter("ciclistas.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i<lista.size();i++)
			{
				bw.write(lista.get(i).getDorsal()+ ", "+lista.get(i).getNombre()+ ", "+lista.get(i).getEquipo()+ "\n");
			}
			bw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
