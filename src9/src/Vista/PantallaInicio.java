package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Modelo.CandySopa;
import Modelo.GestorEventosTama;
import Modelo.Tamagotchi;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PantallaInicio extends JFrame {

	private static PantallaInicio miPantallaInicio = new PantallaInicio();
	
	private JPanel contentPane;
	
	private String nombreUsuario;
	private Controler controler = null;
	private JPanel Arriba;
	private JPanel Centro;
	private JPanel Abajo;
	private JLabel Titulo;
	private JLabel TuNombre;
	private JTextField Nombre;
	private JButton Jugar;
	private JButton Salir;
	
	// YO 
	private JTable table;
	private String[] nomColumnas = {"Posición", "Nombre", "Puntuación"};
    private Object[][] datos = new Object [5][nomColumnas.length];



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					miPantallaInicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private PantallaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		contentPane.add(getArriba());
		contentPane.add(getCentro());
		contentPane.add(getAbajo());
	}



	public static PantallaInicio getPantallaInicio() {
		
		return miPantallaInicio;
	}
	

	private JPanel getArriba() {
		if (Arriba == null) {
			Arriba = new JPanel();
			Arriba.add(getTitulo());
		}
		return Arriba;
	}
	private JPanel getCentro() {
		if (Centro == null) {
			Centro = new JPanel();
			Centro.setLayout(new GridLayout(1, 4, 0, 0));
			Centro.add(getTuNombre());
			Centro.add(getNombre());
			Centro.add(getJugar());
			Centro.add(getSalir());
		}
		return Centro;
	}
	private JPanel getAbajo() {
		if (Abajo == null) {
			Abajo = new JPanel();
			Abajo.add(getTable());
		}
		return Abajo;
	}
	private JLabel getTitulo() {
		if (Titulo == null) {
			Titulo = new JLabel("Dragones");
		}
		return Titulo;
	}
	private JLabel getTuNombre() {
		if (TuNombre == null) {
			TuNombre = new JLabel("Tu Nombre: ");
		}
		return TuNombre;
	}
	private JTextField getNombre() {
		if (Nombre == null) {
			Nombre = new JTextField();
			Nombre.setColumns(10);
		}
		return Nombre;
	}
	private JButton getJugar() {
		if (Jugar == null) {
			Jugar = new JButton("Jugar");
		}
		Jugar.addActionListener(getControler());
		return Jugar;
	}
	private JButton getSalir() {
		if (Salir == null) {
			Salir = new JButton("Salir");
		}
		Salir.addActionListener(getControler());
		return Salir;
	}
	
	
	
	
	

	
	////////////////////// CONTROLADOR ////////////////////////////////
	
	
	
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	private class Controler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(Jugar)){
				// YO
				
				nombreUsuario = Nombre.getText(); // Obtener el texto del JTextField
				
				GestorEventosTama.getGestorEventosTama().reset();
				Tamagotchi.getTamagotchi().reset();
				miPantallaInicio.setVisible(false);
				PantallaJuego.getPantallaJuego().setVisible(true);
				GestorEventosTama.getGestorEventosTama();
				
			}
			
			if (e.getSource().equals(Salir)){
			
				System.exit(0);
				
			}
			
		}
	}
	
	
	

	
	
	
	private JTable getTable() {
	    if (table == null) {
	        table = new JTable();	      
	        table.setEnabled(false);
	        
	        for (int i=0; i<5 ; i++) {  // Inicializar valores de la tabla
	           	datos[i][0]=i+1;
	        	datos[i][1]="-";
	        	datos[i][2]=0;
	        }
	        DefaultTableModel dataModel = new DefaultTableModel(datos,nomColumnas);
	        table.setModel(dataModel);
	        // Obtener el encabezado de la tabla
	        JTableHeader header = table.getTableHeader();

	        // Crear un panel para contener el encabezado
	        JPanel headerPanel = new JPanel();
	        headerPanel.setLayout(new BorderLayout());
	        headerPanel.add(header, BorderLayout.CENTER);

	        // Agregar el panel de encabezado al contenedor de la tabla
	        Abajo.add(headerPanel, BorderLayout.NORTH);
	       // this.modificarTabla("usu1",8);
	       // this.modificarTabla("usu2",7);
	       // this.modificarTabla("usu3",3);
	       // this.modificarTabla("usu4",4);
	       // this.modificarTabla("usu5",1);
	      //  this.modificarTabla("usu6",5);
	       // this.modificarTabla("usu7",10);
	        
	        }
	    return table;
	}
	
	public void modificarTabla(int pPuntos) {
		
		
		int pos = 0;
		boolean cambiar = false;
		int puntuacionExistente;
	    while (pos < 5 && !cambiar) { // Comprobar la puntuacion actual con los almacenados en la tabla
	    	puntuacionExistente = (int) datos[pos][2];
	    	if ( pPuntos < puntuacionExistente ) {
	    		pos ++;
	    	} else {
	    		cambiar = true;
	    	}
	    }
	    if (cambiar) { // Hay que reordenar las demas posiciones
	    	if (pos !=4) {
	    		int i = 4;
	    		while (pos<i) {
	    			datos[i][1] = datos[i-1][1] ;
	    	 		datos[i][2] = datos[i-1][2];
	    	 		i --;
	    		}
	    	}
	    	datos[pos][1] = nombreUsuario;
	 		datos[pos][2] = pPuntos;
	 		DefaultTableModel dataModel = new DefaultTableModel(datos,nomColumnas);
	 	    table.setModel(dataModel);
	     }	
	}
	
}
