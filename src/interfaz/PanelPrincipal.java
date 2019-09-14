package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class PanelPrincipal  extends JFrame {
	
	
	
	private PanelCabecera panelCabecera;
	
	public PanelPrincipal() {
		// TODO Auto-generated constructor stub
		
	
		setTitle("Minimización: maquinas de estado finito");
		setSize(600, 750);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panelCabecera = new PanelCabecera();
		
		
		
		
		setLayout(new BorderLayout());
		add(panelCabecera, BorderLayout.NORTH);
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		PanelPrincipal  panelPrincipal = new PanelPrincipal();
		panelPrincipal.setVisible(true);
	}

}
