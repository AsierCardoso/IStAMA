package Modelo;

public class LadrilloFactory {
	
	private static LadrilloFactory miLadrilloFactory = new LadrilloFactory();
	
	private LadrilloFactory() {
		
	}
	
	public static LadrilloFactory getLadrilloFactory() {	
		return miLadrilloFactory;
	}
	
	
	public Ladrillo createLadrillo(int tipo) {
		Ladrillo l = null;
		if(tipo == 0) {  l = new Ladrillo0(); }
		else if(tipo == 1) {    l = new Ladrillo1();}
		else if(tipo == 2) {    l = new Ladrillo2();}
		else if(tipo == 3) {    l = new Ladrillo3();}
		
		return l;
	}
	
	
	

}
