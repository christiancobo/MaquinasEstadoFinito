package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class PanelAutomataMoore  extends JPanel implements ActionListener{

	
	public static final String AGREGAR_ENTRADA = "agregar entrada";
	public static final String AGREGAR_ESTADO = "agregar estado";
	
	
	public static final String UNIR = "Unir";
	public static final String MATRIZ = "Matriz";
	
	private PanelInformacion panelInformacion;
	
	
	private JLabel labelConjuntoEntradas;
	private JLabel labelSalidasEstado;
	private JLabel label0;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel labelRelacion;
	private JLabel labelEstadoInicial;
	private JLabel labelEstafoFinal;
	private JLabel labelEntradas;
	
	
	
	private JTextField txtConjuntoEntradas;
	private JTextField txtSalidasEstado;
	private JTextField txtEstadoInical;
	private JTextField txtEstadoFinal;
	private JTextField txtEntrada;

	private JButton butAgregarSalida;
	private JButton butUnir;
	private JButton butMatriz;
	private JButton butAgregarConjuntoEntradas;
	
	private JTextArea txtArea;
	
	
	public PanelAutomataMoore(PanelInformacion c) {
		// TODO Auto-generated constructor stub
		panelInformacion = c;
		
		TitledBorder opciones = BorderFactory.createTitledBorder("Opciones Autómata de Moore");
		opciones.setTitleColor(Color.BLACK);
		setBorder(opciones);
		
		
		labelConjuntoEntradas = new JLabel("Conjunto entradas");
		labelSalidasEstado = new JLabel("Salidas Estado");
		label0 = new JLabel(" ");
		label1 = new JLabel(" ");
		label2 = new JLabel(" ");
		label3 = new JLabel(" ");
		label4 = new JLabel(" ");
		label5 = new JLabel(" ");
		label6 = new JLabel(" ");
		label7 = new JLabel(" ");
		labelRelacion = new JLabel("Relacion");
		labelEstadoInicial = new JLabel("Estado(inicial)");
		labelEstafoFinal = new JLabel("Estado(final)");
		labelEntradas = new JLabel("Entrada");
		
		
		txtConjuntoEntradas  = new JTextField();
		txtSalidasEstado = new JTextField();
		txtEstadoInical = new JTextField();
		txtEstadoFinal = new JTextField();
		txtEntrada = new JTextField();
		
		butAgregarSalida = new JButton("Agregar estado");
		butAgregarSalida.setActionCommand(AGREGAR_ESTADO);
		butAgregarSalida.addActionListener(this);

		butAgregarConjuntoEntradas = new JButton("Agregar entradas");
		butAgregarConjuntoEntradas.setActionCommand(AGREGAR_ENTRADA);
		butAgregarConjuntoEntradas.addActionListener(this);

		
		 butUnir = new JButton("Unir");
		 butUnir.setActionCommand(UNIR);
		 butUnir.addActionListener(this);

		 butMatriz = new JButton("Matriz");
		 butMatriz.setActionCommand(MATRIZ);
		 butMatriz.addActionListener(this);
		 
		txtArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(txtArea);
		
		txtArea.setEditable(false);

		JPanel auxInputs = new JPanel();
		auxInputs.setLayout(new GridLayout(12, 2));
		
		auxInputs.add(labelConjuntoEntradas);
		auxInputs.add(txtConjuntoEntradas);
		auxInputs.add(butAgregarConjuntoEntradas);
		auxInputs.add(label7);
		auxInputs.add(labelSalidasEstado);
		auxInputs.add(txtSalidasEstado);
		auxInputs.add(butAgregarSalida);
		auxInputs.add(label0);
		auxInputs.add(labelRelacion);
		auxInputs.add(label2);
		auxInputs.add(labelEstadoInicial);
		auxInputs.add(txtEstadoFinal);
		auxInputs.add(labelEstafoFinal);
		auxInputs.add(txtEstadoFinal);
		auxInputs.add(labelEntradas);
		auxInputs.add(txtEntrada);
		auxInputs.add(butUnir);
		auxInputs.add(label2);
		auxInputs.add(label3);
		auxInputs.add(label4);
		auxInputs.add(butMatriz);
		auxInputs.add(label5);
		auxInputs.add(label6);
		
		setLayout(new BorderLayout());

		add(auxInputs, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
