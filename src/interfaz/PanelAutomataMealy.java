package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextArea.*;
import javax.swing.JTextField;

import javax.swing.border.TitledBorder;


public class PanelAutomataMealy extends JPanel implements ActionListener{

	
	public static final String ACTUALIZAR = "actualizar";
	public static final String UNIR = "unir";
	public static final String MATRIZ = "matriz";
	
	
	private JLabel labelCantidadEstados;
	private JLabel labelCantidadEntadas;
	private JLabel label0;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel labelRelacion;
	private JLabel labelEstadoInicial;
	private JLabel labelEstadoFinal;
	private JLabel labelEntradaRelacion;
	private JLabel labelSalidaRelacion;
	
	private JButton butActualizarMatriz;
	private JButton butCrearRelacion;
	private JButton butMatriz;
	
	
	private JTextField txtCantidadEstados;
	private JTextField txtCantidadEntradas;
	private JTextField txtEstadoInicial;
	private JTextField txtEstadoFinal;
	private JTextField txtEntrada;
	private JTextField txtSalida;
	
	private JTextArea txtArea;
	
	private PanelInformacion panelInformacion;
	
	
	public PanelAutomataMealy(PanelInformacion c){
		
		panelInformacion = c;
		TitledBorder configuracion = BorderFactory.createTitledBorder("Configuración autómata Mealy");
		configuracion.setTitleColor(Color.black);
		setBorder(configuracion);
		
		
		labelCantidadEstados = new JLabel("Cantidad estados");
		labelCantidadEntadas = new JLabel("Cantidad entradas");
		label0 = new JLabel(" ");
		label1 = new JLabel(" ");
		label2 = new JLabel(" ");
		label3 = new JLabel(" ");
		label4 = new JLabel(" ");
		label5 = new JLabel(" ");
		label6 = new JLabel(" ");
		labelRelacion= new JLabel("Relacion");
		labelEstadoInicial = new JLabel("Estado (incial)");
		labelEstadoFinal = new JLabel("Estado (final)");
		labelEntradaRelacion = new JLabel("Entrada");
		labelSalidaRelacion= new JLabel("Salida");
		
		
		 txtCantidadEstados = new JTextField();
		 txtCantidadEntradas = new JTextField();
		 txtEstadoInicial= new JTextField();
		 txtEstadoFinal= new JTextField();
		 txtEntrada= new JTextField();
		 txtSalida= new JTextField(); 
		 
		 
		butActualizarMatriz = new JButton("Actualizar");
		butActualizarMatriz.setActionCommand(ACTUALIZAR);
		butActualizarMatriz.addActionListener(this);

		butCrearRelacion = new JButton("Unir");
		butCrearRelacion.setActionCommand(UNIR);
		butCrearRelacion.addActionListener(this);

		butMatriz = new JButton("Matriz ");
		butMatriz.setActionCommand(MATRIZ);
		butMatriz.addActionListener(this);
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
