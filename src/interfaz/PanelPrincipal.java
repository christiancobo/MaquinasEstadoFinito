package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal  extends JFrame {
	
	
	
	private PanelCabecera panelCabecera;
	private PanelInformacion panelInformacion;
	
	public PanelPrincipal() {
		// TODO Auto-generated constructor stub
		
	
		setTitle("Minimización: maquinas de estado finito");
		setSize(400, 2000);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panelCabecera = new PanelCabecera();
		panelInformacion = new PanelInformacion(this);
		
		
		
		
		setLayout(new BorderLayout());
		
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(1, 2));
		aux.add(panelInformacion);
		
	
		add(panelCabecera, BorderLayout.NORTH);
		add(new JLabel(""), BorderLayout.EAST);
		add(aux, BorderLayout.CENTER);
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		PanelPrincipal  panelPrincipal = new PanelPrincipal();
		panelPrincipal.setVisible(true);
	}

}
