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
import javax.swing.JCheckBox;

public class ViewVerFactura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Empresa;
	SistemaPresentismo controlPresentismo;
	private JTextField textField_FF_DD;
	private JTextField textField_FF_MM;
	private JTextField textField_FF_AAAA;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_montoTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewVerFactura frame = new ViewVerFactura();
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
	public ViewVerFactura() {
		setTitle("Facturacion");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 456, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNroFactura = new JLabel("Numero de Factura");
		labelNroFactura.setBounds(10, 54, 132, 23);
		contentPane.add(labelNroFactura);
		
		System.out.println("pruebacombotipo");
		
		JLabel lblCantHoras = new JLabel("CUIT / CUIL");
		lblCantHoras.setBounds(10, 97, 132, 23);
		contentPane.add(lblCantHoras);
		
		textField_Empresa = new JTextField();
		textField_Empresa.setEditable(false);
		textField_Empresa.setBounds(136, 97, 132, 23);
		contentPane.add(textField_Empresa);
		textField_Empresa.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(10, 11, 108, 23);
		contentPane.add(lblEmpresa);
		System.out.println("prueba comboempresa");
		
		JComboBox<String> comboBox_CUITCUIL = new JComboBox<String>();
		comboBox_CUITCUIL.setBounds(136, 12, 132, 22);
		
//		if(getStub()){
//			List<Cliente> clientes;
//			clientes=ClienteSrv.getClientes();
//			System.out.println("prueba comboempresa antes for");
//			for (int i=0; i<clientes.size();i++){
//				comboBox_CUITCUIL.addItem(clientes.get(i).getCuit_cuil());
//			}
//			System.out.println(clientes.size());
//		}
//		contentPane.add(comboBox_CUITCUIL);
		
		JLabel lblNumFact = new JLabel("Numero de Factura");
		lblNumFact.setBounds(10, 131, 115, 20);
		contentPane.add(lblNumFact);
		
		JLabel lblFechaFact = new JLabel("Fecha");
		lblFechaFact.setBounds(10, 162, 115, 20);
		contentPane.add(lblFechaFact);
		
		textField_FF_DD = new JTextField();
		textField_FF_DD.setEditable(false);
		textField_FF_DD.setColumns(10);
		textField_FF_DD.setBounds(136, 162, 32, 20);
		contentPane.add(textField_FF_DD);
		
		JLabel label_4 = new JLabel("/");
		label_4.setBounds(172, 165, 18, 17);
		contentPane.add(label_4);
		
		textField_FF_MM = new JTextField();
		textField_FF_MM.setEditable(false);
		textField_FF_MM.setColumns(10);
		textField_FF_MM.setBounds(183, 162, 38, 20);
		contentPane.add(textField_FF_MM);
		
		JLabel label_5 = new JLabel("/");
		label_5.setBounds(225, 164, 18, 17);
		contentPane.add(label_5);
		
		textField_FF_AAAA = new JTextField();
		textField_FF_AAAA.setEditable(false);
		textField_FF_AAAA.setColumns(10);
		textField_FF_AAAA.setBounds(237, 162, 50, 20);
		contentPane.add(textField_FF_AAAA);
		
		JLabel lblTipoDeFactura = new JLabel("Tipo de Factura");
		lblTipoDeFactura.setBounds(10, 193, 108, 23);
		contentPane.add(lblTipoDeFactura);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(136, 55, 132, 23);
		contentPane.add(textField);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBounds(298, 55, 132, 23);
		contentPane.add(btnBuscar);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(136, 131, 132, 23);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(136, 194, 132, 23);
		contentPane.add(textField_2);
		
		JLabel lblPagado = new JLabel("Pagado");
		lblPagado.setBounds(10, 227, 108, 23);
		contentPane.add(lblPagado);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setEnabled(false);
		checkBox.setBounds(136, 227, 97, 23);
		contentPane.add(checkBox);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 257, 115, 20);
		contentPane.add(lblTotal);
		
		textField_montoTotal = new JTextField();
		textField_montoTotal.setEditable(false);
		textField_montoTotal.setColumns(10);
		textField_montoTotal.setBounds(136, 257, 132, 23);
		contentPane.add(textField_montoTotal);
		
		JButton btn_Pagar = new JButton("Pagar");
		btn_Pagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Pagar.setBounds(136, 299, 132, 32);
		contentPane.add(btn_Pagar);
		
	}
}
