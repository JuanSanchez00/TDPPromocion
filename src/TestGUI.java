import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Action;
import javax.swing.BorderFactory;

public class TestGUI extends JFrame {

	private PluginDemo plugin;
	private JPanel contentPane;
	private JTextField tF1,tF2;
	private JLabel primerOperando,segundoOperando,eleccionOperacion,resultado,resultadoOperacion;
	private JComboBox comboPlugins;
	private JButton calcular,limpiar,actualizar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI frame = new TestGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TestGUI() {
		contentPane = new JPanel();
		tF1 = new JTextField();
		tF2 = new JTextField();
		primerOperando = new JLabel();
		segundoOperando = new JLabel();
		eleccionOperacion = new JLabel();
		resultado = new JLabel();
		resultadoOperacion = new JLabel();
		comboPlugins = new JComboBox();
		calcular = new JButton();
		limpiar = new JButton();
		actualizar = new JButton();
		plugin = new PluginDemo();
		
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 210);
		this.setResizable(false);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		
		primerOperando.setText("Primer operando:");
		primerOperando.setPreferredSize(new Dimension(130,20));
		contentPane.add(primerOperando);
		
		tF1.setPreferredSize(new Dimension(100,20));
		contentPane.add(tF1);
		
		segundoOperando.setText("Segundo operando:");
		segundoOperando.setPreferredSize(new Dimension(130,20));
		contentPane.add(segundoOperando);
		
		tF2.setPreferredSize(new Dimension(100,20));
		contentPane.add(tF2);
		
		eleccionOperacion.setText("Elija la operación:");
		eleccionOperacion.setPreferredSize(new Dimension(130,20));
		contentPane.add(eleccionOperacion);
		
		comboPlugins.setPreferredSize(new Dimension(100,20));
		comboPlugins.setOpaque(true);
		comboPlugins.setBackground(Color.WHITE);
		contentPane.add(comboPlugins);
		cargarPlugins();
		
		resultado.setText("Resultado:");
		resultado.setPreferredSize(new Dimension(130,20));
		contentPane.add(resultado);
		
		resultadoOperacion.setPreferredSize(new Dimension(100,20));
		resultadoOperacion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		resultadoOperacion.setOpaque(true);
		resultadoOperacion.setBackground(Color.WHITE);
		contentPane.add(resultadoOperacion);
		
		calcular.setPreferredSize(new Dimension(85,20));
		calcular.setText("Calcular");
		contentPane.add(calcular);
		calcular.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       String nombre = (String) comboPlugins.getSelectedItem();
		       float n1,n2;
		       try {
			       n1 = Float.parseFloat(tF1.getText());
			       n2 = Float.parseFloat(tF2.getText());
			       float resultado = plugin.runPlugin(n1, n2, nombre);
			       resultadoOperacion.setText(""+resultado);
		       }
		       catch(Exception ex) {
		    	   JOptionPane cartelError = new JOptionPane();
		    	   cartelError.showMessageDialog(contentPane, "ERROR: Los operandos son inválidos.", "Calculadora", JOptionPane.ERROR_MESSAGE);
		    	   limpiar();
		       }
		    }
		});
		
		limpiar.setPreferredSize(new Dimension(85,20));
		limpiar.setText("Limpiar");
		contentPane.add(limpiar);
		limpiar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       limpiar();
		    }
		});
		
		actualizar.setPreferredSize(new Dimension(93,20));
		actualizar.setText("Actualizar");
		contentPane.add(actualizar);
		actualizar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	limpiar();
		    	comboPlugins.removeAllItems();
				cargarPlugins();
		    }
		});
	}
	
	private void cargarPlugins() {
		List<PluginFunction> listaPlugins = plugin.getPlugins();
		for (PluginFunction p : listaPlugins) {
			comboPlugins.addItem(p.getPluginName());
		}
	}
	
	private void limpiar() {
		tF1.setText("");
	    tF2.setText("");
	    resultadoOperacion.setText("");
	}
}
