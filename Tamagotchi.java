package Modelo;

import java.util.Observable;

public class Tamagotchi extends Observable {
	
	private static Tamagotchi miTama = new Tamagotchi("Kutchipatchi");
	
	private int vivo;
	private int corazones;
	private int apetito;
	private String nombre;
	
	private int enfermo;
	private int sucio;
	
	private int aburrimiento;
	private int hambre;
	

	
	public Tamagotchi(String pNombre) {
		
		nombre = pNombre;
		reset();
		
		///sólo para Kutchipatchi
		aburrimiento = 2;
		hambre = 5;
	}
	
	public void reset() {
		corazones = 40;
		apetito = 40;
		vivo = 1;
		enfermo = 0;
		sucio = 0;
		
		setChanged();
		notifyObservers(new int[] {vivo, corazones, apetito, enfermo, sucio});
	}
	
	public static Tamagotchi getTamagotchi() {
		
		return miTama;
		
	}
	
	
	public void comerYCuidar () {
		
		int puntos;
		
		apetito = apetito + CandySopa.getCandySopa().getSopa();
		corazones = corazones + CandySopa.getCandySopa().getCandy();
		
		puntos = (CandySopa.getCandySopa().getSopa() + CandySopa.getCandySopa().getCandy()) * 3 * (CandySopa.getCandySopa().getSopa() + CandySopa.getCandySopa().getCandy());
		
		
		if (apetito > 40) {
			
			puntos = -5;
			apetito = 40;
		}
		
		if (corazones > 40) {
			
			puntos = -5;
			corazones = 40;
		}
		
		GestorEventosTama.getGestorEventosTama().aumentarPuntuacion(puntos);
		
		setChanged();
		notifyObservers(new int[] {vivo, corazones, apetito, enfermo, sucio});
		
		CandySopa.getCandySopa().vaciar();
		
		
	}
	
	
	
	public void aburrirYDarHambre() {
	
		
		if(enfermo == 1) {
			
			corazones = corazones - 7;
			apetito = apetito + 5;
			
		}
		
		if(sucio == 1) {
			
			corazones = corazones - 5;
			apetito = apetito + 10;
			
		}
		
		corazones = corazones - aburrimiento;
		apetito = apetito - hambre;
		
		if (apetito > 40) {
			
			apetito = 40;
		}
		
		if(apetito <= 0 || corazones <= 0) {
			
			vivo = 0;
			
		}
	
		setChanged();
		notifyObservers(new int[] {vivo, corazones, apetito, enfermo, sucio});
		
	}
	
	public void ponerEnfermo() {
		
		enfermo = 1;
		setChanged();
		notifyObservers(new int[] {vivo, corazones, apetito, enfermo, sucio});
	}
	
	public void ponerSucio() {
		
		sucio = 1;
		setChanged();
		notifyObservers(new int[] {vivo, corazones, apetito, enfermo, sucio});
	}
	
	public void curar() {
		
		enfermo = 0;
		setChanged();
		notifyObservers(new int[] {vivo, corazones, apetito, enfermo, sucio});
	}
	
	public void limpiar() {
		
		sucio = 0;
		setChanged();
		notifyObservers(new int[] {vivo, corazones, apetito, enfermo, sucio});
	}
	
	
	
	public String getNombre() {
		
		return nombre;
	}
	
	public boolean estaVivo() {
		
		return vivo == 1;
	}

	public boolean estaSucioOEnfermo() {
		
		return (enfermo == 1 || sucio == 1);
	}
	

}
