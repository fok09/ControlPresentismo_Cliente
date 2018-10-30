package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import interfaces.SistemaPresentismo;

public class ViewFichada extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	SistemaPresentismo controlPresentismo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewFichada frame = new ViewFichada();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean getStub() {
    	
    	try {
    		controlPresentismo = (SistemaPresentismo)Naming.lookup ("//localhost/ControladorPresentismoRO");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }

	/**
	 * Create the frame.
	 */
	public ViewFichada() {
		setTitle("Fichada de Empleados");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipoDeFichada = new JLabel("Tipo de Fichada");
		lblTipoDeFichada.setBounds(10, 11, 132, 23);
		contentPane.add(lblTipoDeFichada);
		
		JLabel lblEmpleado = new JLabel("Empleado (DNI)");
		lblEmpleado.setBounds(10, 54, 132, 23);
		contentPane.add(lblEmpleado);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(136, 12, 132, 23);
		comboBox.addItem("Entrada");
		comboBox.addItem("Salida");
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(136, 54, 132, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Fichar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*try {
					controlPresentismo.altaFichada((String) comboBox.getSelectedItem(), textField.getText(),"averiguar como conseguir cuit");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}*/
			}
		});
		btnNewButton.setBounds(238, 106, 108, 23);
		contentPane.add(btnNewButton);
	}
}
