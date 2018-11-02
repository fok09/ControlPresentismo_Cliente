package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViewAdministracion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAdministracion frame = new ViewAdministracion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewAdministracion() {
		setTitle("Administracion de Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Alta de Persona Fisica");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAltaClienteFisico altaClienteFisico = new ViewAltaClienteFisico();
				altaClienteFisico.setVisible(true);
			}
		});
		btnNewButton.setBounds(25, 37, 186, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Alta de Empleado");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAltaEmpleado altaEmpleado = new ViewAltaEmpleado();
				altaEmpleado.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(25, 209, 186, 36);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_Contratacion = new JButton("Contratacion");
		btnNewButton_Contratacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewContratacion altaContratacion = new ViewContratacion();
				altaContratacion.setVisible(true);
			}
		});
		btnNewButton_Contratacion.setBounds(238, 37, 186, 36);
		contentPane.add(btnNewButton_Contratacion);
		
		JButton button_Facturacion = new JButton("Facturacion");
		button_Facturacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewVerFactura altaFactura = new ViewVerFactura();
				altaFactura.setVisible(true);
			}
		});
		button_Facturacion.setBounds(238, 94, 186, 36);
		contentPane.add(button_Facturacion);
		
		JButton btnNewButton_6 = new JButton("Cobro del Banco");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ViewPagoDelBanco pb = new ViewPagoDelBanco();
				pb.setVisible(true);
				
			}
		});
		btnNewButton_6.setBounds(238, 151, 186, 36);
		contentPane.add(btnNewButton_6);
		
		JButton btnAltaDePersona = new JButton("Alta de Persona Juridica");
		btnAltaDePersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAltaClienteJuridico altaClienteJuridico = new ViewAltaClienteJuridico();
				altaClienteJuridico.setVisible(true);
			}
		});
		btnAltaDePersona.setBounds(25, 94, 186, 36);
		contentPane.add(btnAltaDePersona);
		
		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEliminarCliente eliminarCliente = new ViewEliminarCliente();
				eliminarCliente.setVisible(true);
				
				dispose();
			}
		});
		btnEliminarCliente.setBounds(25, 151, 186, 36);
		contentPane.add(btnEliminarCliente);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVolver.setBounds(323, 269, 121, 35);
		contentPane.add(btnVolver);
	}
}
