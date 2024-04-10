package Modelo;

import java.util.Observable;

public class CandySopa extends Observable{
	
	private static CandySopa miCandySopa = new CandySopa();
	
	private int numeroCandy;
	private int numeroSopa;
	
	
	///////////////////////////////////////
	
	private CandySopa() {
		
		
	}
	
	public static CandySopa getCandySopa() {
		
		return miCandySopa;
	}
	
	
	public void darCandy() {
		
		if(numeroCandy < 3) {
		
		numeroCandy++;
		
		}
		
		setChanged();
		notifyObservers(new int[] {numeroCandy, numeroSopa});
		
	}
	
	public void darSopa() {
		
		if(numeroSopa < 3) {
			
		numeroSopa++;
		
		}
		
		setChanged();
		notifyObservers(new int[] {numeroCandy, numeroSopa});
		
	}
	
	public void vaciar() {
		
		numeroSopa=0;
		numeroCandy=0;
		setChanged();
		notifyObservers(new int[] {numeroCandy, numeroSopa});
		
	}
	
	public int getCandy() {
		
		return numeroCandy;
		
	}
	
	public int getSopa() {
		
		return numeroSopa;
		
	}

}
