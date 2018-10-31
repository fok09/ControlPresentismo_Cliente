package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Cliente;
import bean.Empleado;
import dto.FichadaDTO;

import interfaces.SistemaPresentismo;
import srv.ClienteSrv;

public class ViewFichada extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	SistemaPresentismo controlPresentismo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
	public ViewFichada() {
		setTitle("Factura");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_NroFactura = new JLabel("Numero de Factura");
		lbl_NroFactura.setBounds(10, 54, 132, 23);
		contentPane.add(lbl_NroFactura);
		
		System.out.println("pruebacombotipo");
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(10, 11, 108, 23);
		contentPane.add(lblEmpresa);
		System.out.println("prueba comboempresa");
		
		JComboBox<String> comboBox_Empresa = new JComboBox<String>();
		comboBox_Empresa.setBounds(136, 12, 132, 22);
		
		if(getStub()){
			List<Cliente> clientes;
			clientes=ClienteSrv.getClientes();
			System.out.println("prueba comboempresa antes for");
			for (int i=0; i<clientes.size();i++){
				comboBox_Empresa.addItem(clientes.get(i).getCuit_cuil());
			}
			System.out.println(clientes.size());
		}
		contentPane.add(comboBox_Empresa);
		JButton btnNewButton_BuscarFactura = new JButton("Buscar Factura");
		btnNewButton_BuscarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getStub()){
					try {
						System.out.println("prueba");
						List<Cliente> clientes;
						clientes=ClienteSrv.getClientes();
						Empleado emp= new Empleado();
						for(Cliente c: clientes) {
							if(c.getCuit_cuil().equals(comboBox_Empresa.getSelectedItem())){
								
								emp= c.obtenerEmpleadoPorLegajo(textField_Legajo.getText());
							}
						}
						
						FichadaDTO fDTO = new FichadaDTO(
						(String) comboBox_TipoFichada.getSelectedItem(),
						emp);
				controlPresentismo.altaFichada(fDTO);
					} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			}
		});
		btnNewButton_BuscarFactura.setBounds(238, 89, 108, 23);
		contentPane.add(btnNewButton_BuscarFactura);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(136, 55, 132, 23);
		contentPane.add(textField);
		
		JLabel label = new JLabel("Numero de Factura");
		label.setBounds(10, 122, 132, 23);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(136, 123, 132, 23);
		contentPane.add(textField_1);
		
		JLabel lbl_TipoFactura = new JLabel("Tipo de Factura");
		lbl_TipoFactura.setBounds(10, 159, 132, 23);
		contentPane.add(lbl_TipoFactura);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(136, 160, 132, 23);
		contentPane.add(textField_2);
		
		JLabel label_1 = new JLabel("CUIT / CUIL");
		label_1.setBounds(10, 195, 132, 23);
		contentPane.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(136, 196, 132, 23);
		contentPane.add(textField_3);
		
	}
}
