package Modelo;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GestorEventosTama extends Observable{

		private int puntuacion = 0;
	
		private boolean enMini = false;
		private static GestorEventosTama mGestorEventosTama = new GestorEventosTama();
		private static final int PERIODO = 4;
		private static final int PERIODOMINI = 20;
		private int cont;
		private int contMini;
		private Timer timer = null;
		private Timer timerMini = null;
		
		private GestorEventosTama ()
		{
		    
			cont = PERIODO;
			
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					actualizarCont();
				}		
			};
			timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 1000);
		}
		
		private void actualizarCont() {
			
			cont--;
			
			if (cont == 0) {
				
				cont = PERIODO;
				
				
				if(Tamagotchi.getTamagotchi().estaVivo() && !enMini) {
					
					aumentarPuntuacion(1);
					if(Tamagotchi.getTamagotchi().estaSucioOEnfermo()) {
						
						aumentarPuntuacion(-5);
					}
				
					Tamagotchi.getTamagotchi().aburrirYDarHambre(); 
					ponerEnfermoSucio();
					
					if(Tamagotchi.getTamagotchi().estaVivo()) {
						
						activarMinijuego();
					}
				
				}
	
			}
			
			
		}
		
		public static GestorEventosTama getGestorEventosTama() {
			return mGestorEventosTama;
		}
		
		
		private void ponerEnfermoSucio() {
			
			Random random = new Random();
			int randomN = random.nextInt(9);
			
			if(randomN <= 2) {
				
				Tamagotchi.getTamagotchi().ponerEnfermo();
			}
			
			randomN = random.nextInt(9);
			
			if(randomN <= 1) {
				
				Tamagotchi.getTamagotchi().ponerSucio();
			}
			
		}
		
		private void activarMinijuego() {
			
			Random random = new Random();
			int randomN = random.nextInt(99);
			
			if(randomN <= 11) {
				
				enMini = true;
				
				Tablero.getTablero().crearMuro();                ///se crea el Muro en el modelo  
		
				setChanged();
				notifyObservers(new int[] {1});                  ///en la vista se crean los labels del Muro y a cada label se le asocia su ladrillo del modelo
				
				Tablero.getTablero().notificarTodos();           ///se pintan de los colores correspondientes los labels en la vista
				
				ponerTimerMini();
				
			}
			
		}
		
		public void notEnMini() {
			
			enMini = false;
		}
		
		
		///////////////// METODOS TIMER MINIJUEGO  ////////////////
		
		private void ponerTimerMini() {
		
				contMini = PERIODOMINI;
				
				TimerTask timerTaskMini = new TimerTask() {
					@Override
					public void run() {
						
						if(enMini) {
							actualizarContMinijuego();
						}
						else {
							timerMini.cancel();
				
						}
					}		
				};
				
				timerMini = new Timer();
				timerMini.scheduleAtFixedRate(timerTaskMini, 0, 1000);
				
		}
			
		private void actualizarContMinijuego() {
				
				contMini--;
				
				if (contMini == 0) {
					
					aumentarPuntuacion(-20);
					enMini = false;
					timerMini.cancel();
					setChanged();
					notifyObservers(new int[] {2});

				}	
		}

		//////////////////////////////////////
		
	
		
		/////////////////////////  PUNTUACION GLOBAL  ////////////////////////
		
		
		public void aumentarPuntuacion(int pPuntos) {
			
			puntuacion = puntuacion + pPuntos;
			setChanged();
			notifyObservers(new int[] {3, puntuacion});
			
		}
		
		
		//////////////////////////////////////////////////////////////////////
		
		
		public int getPuntos() {
			
			
			return puntuacion;
		}
		
		public void reset() {
			
			puntuacion = 0;
		}
		

}
