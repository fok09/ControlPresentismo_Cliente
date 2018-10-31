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

public class ViewContratacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_CantHoras;
	SistemaPresentismo controlPresentismo;
	private JTextField textField_FI_DD;
	private JTextField textField_FI_MM;
	private JTextField textField_FI_AAAA;
	private JTextField textField_FF_DD;
	private JTextField textField_FF_MM;
	private JTextField textField_FF_AAAA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewContratacion frame = new ViewContratacion();
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
	public ViewContratacion() {
		setTitle("Contratacion");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelEmpresa = new JLabel("Empresa");
		labelEmpresa.setBounds(10, 54, 132, 23);
		contentPane.add(labelEmpresa);
		
		System.out.println("pruebacombotipo");
		JComboBox<String> comboBox_Empresa = new JComboBox<String>();
		comboBox_Empresa.setBounds(136, 55, 132, 23);
		comboBox_Empresa.addItem("Entrada");
		comboBox_Empresa.addItem("Salida");
		contentPane.add(comboBox_Empresa);
		
		JLabel lblCantHoras = new JLabel("Horas a contratar");
		lblCantHoras.setBounds(10, 97, 132, 23);
		contentPane.add(lblCantHoras);
		
		textField_CantHoras = new JTextField();
		textField_CantHoras.setBounds(136, 97, 132, 23);
		contentPane.add(textField_CantHoras);
		textField_CantHoras.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Servicio");
		lblEmpresa.setBounds(10, 11, 108, 23);
		contentPane.add(lblEmpresa);
		System.out.println("prueba comboempresa");
		
		JComboBox<String> comboBox_Servicio = new JComboBox<String>();
		comboBox_Servicio.setBounds(136, 12, 132, 22);
		
		if(getStub()){
			List<Cliente> clientes;
			clientes=ClienteSrv.getClientes();
			System.out.println("prueba comboempresa antes for");
			for (int i=0; i<clientes.size();i++){
				comboBox_Servicio.addItem(clientes.get(i).getCuit_cuil());
			}
			System.out.println(clientes.size());
		}
		contentPane.add(comboBox_Servicio);
		JButton btnNewButton = new JButton("Generar Contratacion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getStub()){
					try {
						System.out.println("prueba");
						List<Cliente> clientes;
						clientes=ClienteSrv.getClientes();
						Empleado emp= new Empleado();
						for(Cliente c: clientes) {
							if(c.getCuit_cuil().equals(comboBox_Servicio.getSelectedItem())){
								
								emp= c.obtenerEmpleadoPorLegajo(textField_CantHoras.getText());
							}
						}
						
						FichadaDTO fDTO = new FichadaDTO(
						(String) comboBox_Empresa.getSelectedItem(),
						emp);
				controlPresentismo.altaFichada(fDTO);
					} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			}
		});
		btnNewButton.setBounds(174, 227, 172, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio");
		lblFechaDeInicio.setBounds(10, 131, 115, 20);
		contentPane.add(lblFechaDeInicio);
		
		textField_FI_DD = new JTextField();
		textField_FI_DD.setColumns(10);
		textField_FI_DD.setBounds(151, 131, 32, 20);
		contentPane.add(textField_FI_DD);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(188, 134, 18, 17);
		contentPane.add(label_1);
		
		textField_FI_MM = new JTextField();
		textField_FI_MM.setColumns(10);
		textField_FI_MM.setBounds(204, 131, 32, 20);
		contentPane.add(textField_FI_MM);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(241, 134, 18, 17);
		contentPane.add(label_2);
		
		textField_FI_AAAA = new JTextField();
		textField_FI_AAAA.setColumns(10);
		textField_FI_AAAA.setBounds(253, 131, 50, 20);
		contentPane.add(textField_FI_AAAA);
		
		JLabel lblFechaFinal = new JLabel("Fecha Final");
		lblFechaFinal.setBounds(10, 162, 115, 20);
		contentPane.add(lblFechaFinal);
		
		textField_FF_DD = new JTextField();
		textField_FF_DD.setColumns(10);
		textField_FF_DD.setBounds(151, 162, 32, 20);
		contentPane.add(textField_FF_DD);
		
		JLabel label_4 = new JLabel("/");
		label_4.setBounds(188, 165, 18, 17);
		contentPane.add(label_4);
		
		textField_FF_MM = new JTextField();
		textField_FF_MM.setColumns(10);
		textField_FF_MM.setBounds(204, 162, 32, 20);
		contentPane.add(textField_FF_MM);
		
		JLabel label_5 = new JLabel("/");
		label_5.setBounds(241, 165, 18, 17);
		contentPane.add(label_5);
		
		textField_FF_AAAA = new JTextField();
		textField_FF_AAAA.setColumns(10);
		textField_FF_AAAA.setBounds(253, 162, 50, 20);
		contentPane.add(textField_FF_AAAA);
		
		JLabel lblTipoDeFactura = new JLabel("Tipo de Factura");
		lblTipoDeFactura.setBounds(10, 193, 108, 23);
		contentPane.add(lblTipoDeFactura);
		
		JComboBox<String> comboBox_TipoFactura = new JComboBox<String>();
		comboBox_TipoFactura.setBounds(136, 194, 132, 22);
		contentPane.add(comboBox_TipoFactura);
		
	}
}
