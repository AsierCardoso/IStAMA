package Modelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import Vista.PantallaJuego;

public class Tablero extends Observable{
	
private static Tablero miTablero = new Tablero();
private Ladrillo[][] muro;
	
	private Tablero() {
		
	}
	
	public static Tablero getTablero() {
		return miTablero;
	}
	
	public void crearMuro(){
		muro = new Ladrillo[8][12];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 12; j++) {
				Random random = new Random();
				int r = random.nextInt(3);
				System.out.println(r);
				muro[i][j]= LadrilloFactory.getLadrilloFactory().createLadrillo(r+1);
			}
		}
		Random random2 = new Random();
		int lFila = random2.nextInt(7);
		int lCol = random2.nextInt(11);
		muro[lFila][lCol].setLlave(1);
		int jFila = random2.nextInt(7);
		int jCol = random2.nextInt(11);
		while(jFila == lFila && jCol == lCol) {
			jFila = random2.nextInt(7);
			jCol = random2.nextInt(11);
		}
		muro[jFila][jCol].setJugador(1);		
	}
	
	public Ladrillo[][] getMuro(){	
		return muro;
	}
	
	
	public void notificarTodos() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 12; j++) {
				muro[i][j].notificar();
			}	
		}
	}
	
	public ArrayList<Integer> buscarLlaveJugador(){	
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(0, 0);
		array.add(1, 0);
		array.add(2, 0);
		array.add(3, 0);
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 12; j++) {
				if(muro[i][j].esLlave()) {
					array.add(0,i);
					array.add(1,j);
					System.out.println("Llave"+ i +j);
				}
			}	
		}
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 12; j++) {		
				if(muro[i][j].esJugador()) {
					array.add(2,i);
					array.add(3,j);
					System.out.println("Jugador"+ i +j);
				}
			}	
		}
		return array;
	}
	
	public void moverJugador(int pTecla) {
		ArrayList<Integer> coords = new ArrayList<Integer>();
		coords = buscarLlaveJugador();
		int filaJug = coords.get(2);
		int colJug = coords.get(3);
		System.out.println(filaJug);
		System.out.println(colJug);
		System.out.println(pTecla);
		if(muro[filaJug][colJug].getDureza() == 0) {
			if(pTecla == 87 || pTecla == 38) { // Tecla W o FlechaAarriba
				if (filaJug >= 1) {
					if(muro[filaJug -1][colJug].getDureza() == 0) {
						muro[filaJug -1][colJug].setJugador(1);
						muro[filaJug][colJug].setJugador(0);
					}
				}
			} else if(pTecla == 65 || pTecla == 37) { // Tecla A o FlechaIzquierda
				if (colJug >= 1) {		
					if(muro[filaJug][colJug-1].getDureza() == 0) {
						muro[filaJug][colJug-1].setJugador(1);
						muro[filaJug][colJug].setJugador(0);
					}
				}
			} else if(pTecla == 83 || pTecla == 40) { // Tecla S o FlechaAbajo
				if (filaJug <= 6) {
					if(muro[filaJug +1][colJug].getDureza() == 0) {
						muro[filaJug +1][colJug].setJugador(1);
						muro[filaJug][colJug].setJugador(0);
					}
				}
			} else if(pTecla == 68 || pTecla == 39) { // Tecla D o FlechaDerecha
				if (colJug <=10) {
					if(muro[filaJug][colJug+1].getDureza() == 0) {
						muro[filaJug][colJug+1].setJugador(1);
						muro[filaJug][colJug].setJugador(0);
					}
				}
		
			}
			coords = buscarLlaveJugador();
			if(coords.get(0)==coords.get(2)&&coords.get(1)==coords.get(3)) {          /// el jugador llega a la llave
				GestorEventosTama.getGestorEventosTama().notEnMini();
				GestorEventosTama.getGestorEventosTama().aumentarPuntuacion(20);
				setChanged();
				notifyObservers();
			}
			System.out.println(""+ coords.get(2) + coords.get(3));
		}
	}
		
		
	
	

}