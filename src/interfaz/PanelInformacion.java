package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;



public class PanelInformacion extends JPanel implements ActionListener {

	
	private PanelAutomataMealy panelAutomataMealy;
	
	private JLabel lbOpcion;
	private JComboBox<String> CbMaquinas;
	
	JPanel panelAuxI = new JPanel();
	
	PanelPrincipal principal;

	public PanelInformacion(PanelPrincipal c) {
		// TODO Auto-generated constructor stub
		principal = c;
		
		TitledBorder opciones = BorderFactory.createTitledBorder("Maquinas");
		opciones.setTitleColor(Color.BLACK);
		setBorder(opciones);
		
		lbOpcion = new JLabel("Selecciona un tipo de autómata");
		CbMaquinas = new JComboBox<String>();
		
		CbMaquinas.addItem("Selecciona una opción");
		CbMaquinas.addItem("Automata de Moore");
		CbMaquinas.addItem("Automata de Mealy");
		
		CbMaquinas.addActionListener(this);
		
		
		JPanel auxSelec = new JPanel();
		auxSelec.setLayout(new GridLayout(2, 1));
		auxSelec.add(lbOpcion);
		auxSelec.add(CbMaquinas);

		panelAuxI.setLayout(new GridLayout(1, 2));

		add(auxSelec, BorderLayout.NORTH);
		add(panelAuxI, BorderLayout.CENTER);
			
	}
	
	/**
	 * This method show the machine mealy or moore that are going to be created
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type =CbMaquinas.getSelectedItem().toString();
		if (type.equals("Automata de Mealy")) {
			panelAutomataMealy=null;
			panelAuxI.removeAll();
			panelAutomataMealy = new PanelAutomataMealy(this);
			panelAuxI.add(panelAutomataMealy);
			this.validate();
			this.repaint();
			CbMaquinas.setEnabled(true);
			System.out.println("Hola");
			//conecctionMainGUI.setTypeMachine("MEALY");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

}
