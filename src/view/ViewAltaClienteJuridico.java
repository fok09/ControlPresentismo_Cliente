package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.PersonaJuridicaDTO;
import interfaces.SistemaPresentismo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Time;
import java.awt.event.ActionEvent;

public class ViewAltaClienteJuridico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_CUIT;
	private JTextField textField_CBU;
	private JTextField textField_RazonSocial;
	private JTextField textField_Domicilio;
	private JTextField textField_Telefono;
	private JTextField textField_Mail;
	private JTextField textField_HE_HH;
	private JTextField textField_HE_MM;
	private JTextField textField_HS_HH;
	private JTextField textField_HS_MM;
	SistemaPresentismo controlPresentismo;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAltaClienteJuridico frame = new ViewAltaClienteJuridico();
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

	/**
	 * Create the frame.
	 */
	public ViewAltaClienteJuridico() {
		setTitle("Alta de Persona Juridica");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CUIT");
		lblNewLabel.setBounds(10, 11, 89, 20);
		contentPane.add(lblNewLabel);
		
		textField_CUIT = new JTextField();
		textField_CUIT.setBounds(151, 11, 152, 20);
		contentPane.add(textField_CUIT);
		textField_CUIT.setColumns(10);
		
		JLabel lblRazonSocial = new JLabel("Razon Social");
		lblRazonSocial.setBounds(10, 75, 115, 20);
		contentPane.add(lblRazonSocial);
		
		textField_RazonSocial = new JTextField();
		textField_RazonSocial.setBounds(151, 75, 152, 20);
		contentPane.add(textField_RazonSocial);
		textField_RazonSocial.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 106, 89, 20);
		contentPane.add(lblDomicilio);
		
		textField_Domicilio = new JTextField();
		textField_Domicilio.setBounds(151, 106, 152, 20);
		contentPane.add(textField_Domicilio);
		textField_Domicilio.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 137, 89, 20);
		contentPane.add(lblTelefono);
		
		textField_Telefono = new JTextField();
		textField_Telefono.setBounds(151, 137, 152, 20);
		contentPane.add(textField_Telefono);
		textField_Telefono.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mail");
		lblNewLabel_1.setBounds(10, 168, 89, 20);
		contentPane.add(lblNewLabel_1);
		
		textField_Mail = new JTextField();
		textField_Mail.setBounds(151, 168, 152, 20);
		contentPane.add(textField_Mail);
		textField_Mail.setColumns(10);
		
		JLabel lblHoraDeEntrada = new JLabel("Horario de Entrada");
		lblHoraDeEntrada.setBounds(10, 199, 115, 20);
		contentPane.add(lblHoraDeEntrada);
		
		textField_HE_HH = new JTextField();
		textField_HE_HH.setBounds(151, 199, 57, 20);
		contentPane.add(textField_HE_HH);
		textField_HE_HH.setColumns(10);
		
		JLabel lblHs = new JLabel("Hs");
		lblHs.setBounds(215, 199, 22, 20);
		contentPane.add(lblHs);
		
		textField_HE_MM = new JTextField();
		textField_HE_MM.setBounds(247, 199, 57, 20);
		contentPane.add(textField_HE_MM);
		textField_HE_MM.setColumns(10);
		
		JLabel lblMins = new JLabel("Mins");
		lblMins.setBounds(316, 202, 38, 17);
		contentPane.add(lblMins);
		
		JLabel lblHorarioDeSalida = new JLabel("Horario de Salida");
		lblHorarioDeSalida.setBounds(10, 230, 89, 20);
		contentPane.add(lblHorarioDeSalida);
		
		textField_HS_HH = new JTextField();
		textField_HS_HH.setColumns(10);
		textField_HS_HH.setBounds(151, 230, 57, 20);
		contentPane.add(textField_HS_HH);
		
		JLabel label = new JLabel("Hs");
		label.setBounds(215, 230, 22, 20);
		contentPane.add(label);
		
		textField_HS_MM = new JTextField();
		textField_HS_MM.setColumns(10);
		textField_HS_MM.setBounds(247, 230, 57, 20);
		contentPane.add(textField_HS_MM);
		
		JLabel label_1 = new JLabel("Mins");
		label_1.setBounds(316, 233, 38, 17);
		contentPane.add(label_1);
		
		JButton btnAltaDeCliente = new JButton("Alta de Cliente");
		btnAltaDeCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getStub()) {
				try {
					@SuppressWarnings("deprecation")
					Time hE =new Time(Integer.parseInt(textField_HE_HH.getText()),Integer.parseInt(textField_HE_MM.getText()),00);
					@SuppressWarnings("deprecation")
					Time hS =new Time(Integer.parseInt(textField_HS_HH.getText()),Integer.parseInt(textField_HS_MM.getText()),00);
					
					PersonaJuridicaDTO pjDTO = new PersonaJuridicaDTO(
							textField_RazonSocial.getText(),
							textField_CUIT.getText(), 
							textField_CBU.getText(), 
							textField_Domicilio.getText(), 
							textField_Telefono.getText(),
							textField_Mail.getText(), 
							hE, 
							hS);
					
					
					textField_RazonSocial.setText("");
					textField_CUIT.setText("");
					textField_CBU.setText("");
					textField_Domicilio.setText("");
					textField_Telefono.setText("");
					textField_Mail.setText("");
					textField_HE_HH.setText("");
					textField_HE_MM.setText("");
					textField_HS_HH.setText("");
					textField_HS_MM.setText("");
					
					controlPresentismo.crearClienteJuridico(pjDTO);
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		btnAltaDeCliente.setBounds(215, 261, 139, 23);
		contentPane.add(btnAltaDeCliente);
		
		JLabel labelCBU = new JLabel("CBU");
		labelCBU.setBounds(10, 42, 89, 20);
		contentPane.add(labelCBU);
		
		textField_CBU = new JTextField();
		textField_CBU.setColumns(10);
		textField_CBU.setBounds(151, 44, 152, 20);
		contentPane.add(textField_CBU);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolver.setBounds(247, 297, 107, 23);
		contentPane.add(btnVolver);
	}

}
