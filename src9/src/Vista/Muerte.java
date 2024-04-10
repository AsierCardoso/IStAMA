package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.GestorEventosTama;
import Modelo.Tamagotchi;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Muerte extends JFrame {

	private static Muerte miMuerte = new Muerte();
	
	/**
	 * 
	 */
	private JPanel contentPane;
	private JPanel Centro;
	private JLabel Mensaje;
	private JButton BotónVolver;
	
	private Controler controler = null;

	/////////////////////// NUNCA SE EJECUTA //////////////
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Muerte frame = new Muerte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	////////////////////////////////////////////////////////

	/**
	 * Create the frame.
	 */
	private Muerte() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getCentro(), BorderLayout.CENTER);
	}
	
	public static Muerte getMuerte() {
		
		return miMuerte;
	}

	private JPanel getCentro() {
		if (Centro == null) {
			Centro = new JPanel();
			Centro.setLayout(new GridLayout(2, 1, 0, 0));
			Centro.add(getMensaje());
			Centro.add(getBotónVolver());
		}
		return Centro;
	}
	private JLabel getMensaje() {
		if (Mensaje == null) {
			Mensaje = new JLabel("Game Over");
			Mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return Mensaje;
	}
	private JButton getBotónVolver() {
		if (BotónVolver == null) {
			BotónVolver = new JButton("Volver a la Pantalla de Inicio");
		}
		BotónVolver.addActionListener(getControler());
		return BotónVolver;
	}
	
	
	
	
	
	
////////////////CONTROLADOR	 ////////////////////////////////////////////////////////////
	
	
private Controler getControler() {
if (controler == null) {
controler = new Controler();
}
return controler;
}

private class Controler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource().equals(BotónVolver)){
			
			int puntos = GestorEventosTama.getGestorEventosTama().getPuntos();
			PantallaInicio.getPantallaInicio().modificarTabla(puntos);
			
			
			setVisible(false);
			PantallaInicio.getPantallaInicio().setVisible(true);
			
			
			
			
		}
		
	
	}
	
}


	
	
	
	
}
