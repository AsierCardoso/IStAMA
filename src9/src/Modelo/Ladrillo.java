package Modelo;

import java.util.Observable;

import Vista.Minijuego;

public abstract class Ladrillo extends Observable{
	
	private int jugador;
	private int llave;
	private int dureza;
	
	
	protected Ladrillo (int pDureza) {
		dureza = pDureza;		
	}
	
	public void setLlave(int pLlave) {
		llave = pLlave;
		notificar();
	}
	
	public void setJugador(int pJugador) {
		jugador = pJugador;
		notificar();
	}
	
	public int getDureza() {	
		return dureza;
	}

	public void notificar() {
		setChanged();
		notifyObservers(new int[] {dureza, llave, jugador});
	}
	
	public void bajarDureza() {
		if(dureza >= 1) {
			dureza--;
			GestorEventosTama.getGestorEventosTama().aumentarPuntuacion(1);
		}
		notificar();
	}
	
	public boolean esLlave() {
		return (llave == 1);
	}
	
	public boolean esJugador() {
		return (jugador == 1);
	}
	

}
