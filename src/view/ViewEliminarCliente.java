package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ReportesIntegracion.ReporteBancoDTO;
import bean.Cliente;
import bean.Empleado;
import dto.ClienteDTO;
import dto.FichadaDTO;
import interfaces.SistemaPresentismo;
import srv.ClienteSrv;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class ViewEliminarCliente extends JFrame {

	private JPanel contentPane;
	SistemaPresentismo controlPresentismo;
	private JTextField textField_Domicilio;
	private JTextField textField_CBU;
	private JTextField textField_estado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEliminarCliente frame = new ViewEliminarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean getStub() {
    	
    	try {
    		controlPresentismo = (SistemaPresentismo)Naming.lookup ("//localhost/ControladorPresentismo");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }


	public ViewEliminarCliente() {
		setTitle("Cobro a Banco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_Domicilio = new JTextField();
		textField_Domicilio.setEditable(false);
		textField_Domicilio.setBounds(196, 65, 132, 20);
		contentPane.add(textField_Domicilio);
		textField_Domicilio.setColumns(10);
		
		textField_CBU = new JTextField();
		textField_CBU.setEditable(false);
		textField_CBU.setColumns(10);
		textField_CBU.setBounds(196, 96, 132, 20);
		contentPane.add(textField_CBU);
		
		textField_estado = new JTextField();
		textField_estado.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		textField_estado.setForeground(Color.RED);
		textField_estado.setEditable(false);
		textField_estado.setColumns(10);
		textField_estado.setBounds(58, 230, 222, 20);
		contentPane.add(textField_estado);
		
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(58, 68, 97, 14);
		contentPane.add(lblDomicilio);
		
		JComboBox<String> comboBox_Clientes = new JComboBox<String>();
		comboBox_Clientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(getStub()){
					
					String cuit = String.valueOf(comboBox_Clientes.getSelectedItem());
					Cliente cliente = ClienteSrv.getClienteByCuit(cuit);

					textField_Domicilio.setText(cliente.getDomicilio());
					textField_CBU.setText(cliente.getCbu());
				}
				
			}
		});
		comboBox_Clientes.setBounds(196, 32, 132, 22);
		contentPane.add(comboBox_Clientes);
		
		if(getStub()){
			List<Cliente> clientes;
			clientes=ClienteSrv.getClientes();
			for (int i=0; i<clientes.size();i++){
				comboBox_Clientes.addItem(clientes.get(i).getCuit_cuil());
			}
		}
		
		
		if(getStub()){
			
			String cuit = String.valueOf(comboBox_Clientes.getSelectedItem());
			Cliente cliente = ClienteSrv.getClienteByCuit(cuit);
			
			textField_Domicilio.setText(cliente.getDomicilio());
			textField_CBU.setText(cliente.getCbu());
		
		}
		
		JLabel label = new JLabel("Empresa");
		label.setBounds(58, 32, 108, 23);
		contentPane.add(label);
	
		
		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cuit = String.valueOf(comboBox_Clientes.getSelectedItem());
				Cliente cliente = ClienteSrv.getClienteByCuit(cuit);
				
				ClienteSrv.eliminarCliente(cliente);
				
				textField_estado.setText("El cliente ha sido eliminado");
				
			}
		});
		btnEliminarCliente.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
		btnEliminarCliente.setBounds(101, 134, 204, 67);
		contentPane.add(btnEliminarCliente);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewAdministracion a = new ViewAdministracion();
				a.setVisible(true);
				
				dispose();
			}
		});
		btnAtras.setBounds(318, 227, 89, 23);
		contentPane.add(btnAtras);
		
		JLabel lblCBU = new JLabel("CBU");
		lblCBU.setBounds(58, 102, 97, 14);
		contentPane.add(lblCBU);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 236, 97, 14);
		contentPane.add(lblEstado);
		

	}
}
