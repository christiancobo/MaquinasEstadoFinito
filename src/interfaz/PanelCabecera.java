package interfaz;

import java.awt.BorderLayout;

import javax.swing.*;


public class PanelCabecera extends JPanel{
	
	private JLabel titulo;
	
	public PanelCabecera() {
		
		titulo = new JLabel("                                      "
				+ "                         Minimización");
		setLayout(new BorderLayout());
		add(titulo, BorderLayout.CENTER);
		
	}

}
