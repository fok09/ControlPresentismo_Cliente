package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ReportesIntegracion.ReporteBancoDTO;
import bean.Cliente;
import bean.Empleado;
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

public class ViewPagoDelBanco extends JFrame {

	private JPanel contentPane;
	SistemaPresentismo controlPresentismo;
	private JTextField textField_aCobrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPagoDelBanco frame = new ViewPagoDelBanco();
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


	public ViewPagoDelBanco() {
		setTitle("Cobro a Banco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox_Clientes = new JComboBox<String>();
		comboBox_Clientes.setBounds(196, 32, 132, 22);
		contentPane.add(comboBox_Clientes);
		
		if(getStub()){
			List<Cliente> clientes;
			clientes=ClienteSrv.getClientes();
			for (int i=0; i<clientes.size();i++){
				comboBox_Clientes.addItem(clientes.get(i).getCuit_cuil());
			}
		}
		
		textField_aCobrar = new JTextField();
		textField_aCobrar.setEditable(false);
		textField_aCobrar.setBounds(196, 83, 132, 20);
		contentPane.add(textField_aCobrar);
		textField_aCobrar.setColumns(10);
		
		if(getStub()){
			
			String cuit = String.valueOf(comboBox_Clientes.getSelectedItem());
			Cliente cliente = ClienteSrv.getClienteByCuit(cuit);
			
			float monto = ReporteBancoDTO.calcularMonto(cliente, LocalDate.now());
			
			textField_aCobrar.setText(String.valueOf(monto));
			
		}
		
		
		
		JLabel lblMontoACobrar = new JLabel("Monto a cobrar");
		lblMontoACobrar.setBounds(70, 86, 79, 14);
		contentPane.add(lblMontoACobrar);
		
		JLabel label = new JLabel("Empresa");
		label.setBounds(70, 31, 108, 23);
		contentPane.add(label);
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(getStub()) {
					ReporteBancoDTO.EjecutarReporteBancoDTO(String.valueOf(comboBox_Clientes.getSelectedItem()));
				}
			}
				
			});
		
		btnEnviar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 50));
		btnEnviar.setBounds(103, 154, 225, 96);
		contentPane.add(btnEnviar);
		
		JLabel label_1 = new JLabel("$");
		label_1.setBounds(179, 83, 16, 20);
		contentPane.add(label_1);
		

	}
}
