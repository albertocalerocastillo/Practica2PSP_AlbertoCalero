package es.studium.practica2psp;

public class Hilo extends Thread
{
	Ciclista c;
	
	public Hilo(Ciclista c)
	{
		this.c=c;
	}
	
	public void run()
	{
		for(int i=0; i<20; i++)
		{
			float avance = (float) (Math.floor(Math.random()*5+1) * c.getStamina()/12 * 10);
			c.setKilometros(avance+c.getKilometros());
			System.out.println("Corredor "+c.getDorsal()+" ha avanzado "+avance+" y en total lleva "+c.getKilometros()+" km");
			try
			{
				Thread.sleep(3000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
