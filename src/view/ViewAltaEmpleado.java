package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Cliente;
import dto.EmpleadoDTO;
import interfaces.SistemaPresentismo;
import srv.ClienteSrv;

public class ViewAltaEmpleado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Nombre;
	private JTextField textField_DNI;
	private JTextField textField_Telefono;
	private JTextField textField_Mail;
	SistemaPresentismo controlPresentismo;
	private JTextField textField_Apellido;
	private JTextField textField_Legajo;
	private JTextField textField_FNac_DD;
	private JTextField textField_FNac_MM;
	private JTextField textField_FNac_YYYY;
	private JTextField textField_tipoEmpleado;
	private JTextField textField_cantHoras;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAltaEmpleado frame = new ViewAltaEmpleado();
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
	public ViewAltaEmpleado() {
		setTitle("Alta de Empleado");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 414, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 42, 115, 20);
		contentPane.add(lblNombre);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setBounds(151, 42, 152, 20);
		contentPane.add(textField_Nombre);
		textField_Nombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 106, 89, 20);
		contentPane.add(lblDni);
		
		textField_DNI = new JTextField();
		textField_DNI.setBounds(151, 106, 152, 20);
		contentPane.add(textField_DNI);
		textField_DNI.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 165, 89, 20);
		contentPane.add(lblTelefono);
		
		textField_Telefono = new JTextField();
		textField_Telefono.setBounds(151, 165, 152, 20);
		contentPane.add(textField_Telefono);
		textField_Telefono.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mail");
		lblNewLabel_1.setBounds(10, 196, 89, 20);
		contentPane.add(lblNewLabel_1);
		
		textField_Mail = new JTextField();
		textField_Mail.setBounds(151, 196, 152, 20);
		contentPane.add(textField_Mail);
		textField_Mail.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 73, 115, 20);
		contentPane.add(lblApellido);
		
		textField_Apellido = new JTextField();
		textField_Apellido.setColumns(10);
		textField_Apellido.setBounds(151, 73, 152, 20);
		contentPane.add(textField_Apellido);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(10, 11, 89, 20);
		contentPane.add(lblEmpresa);
		
		JComboBox<String> comboBox_Empresa = new JComboBox<String>();
		comboBox_Empresa.setBounds(151, 11, 152, 20);
		if(getStub()){
			List<Cliente> clientes;
			clientes=ClienteSrv.getClientes();
			
			for (int i=0; i<clientes.size();i++){
				comboBox_Empresa.addItem(clientes.get(i).getCuit_cuil());
			}
		}
		contentPane.add(comboBox_Empresa);
		
		textField_Legajo = new JTextField();
		textField_Legajo.setBounds(151, 134, 152, 20);
		contentPane.add(textField_Legajo);
		textField_Legajo.setColumns(10);
		
		JLabel lblLegajo = new JLabel("Legajo");
		lblLegajo.setBounds(10, 137, 115, 14);
		contentPane.add(lblLegajo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(10, 227, 115, 20);
		contentPane.add(lblFechaDeNacimiento);
		
		textField_FNac_DD = new JTextField();
		textField_FNac_DD.setBounds(151, 227, 32, 20);
		contentPane.add(textField_FNac_DD);
		textField_FNac_DD.setColumns(10);
		
		JLabel label = new JLabel("/");
		label.setBounds(188, 230, 18, 17);
		contentPane.add(label);
		
		textField_FNac_MM = new JTextField();
		textField_FNac_MM.setColumns(10);
		textField_FNac_MM.setBounds(204, 227, 32, 20);
		contentPane.add(textField_FNac_MM);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(241, 230, 18, 17);
		contentPane.add(label_1);
		
		textField_FNac_YYYY = new JTextField();
		textField_FNac_YYYY.setColumns(10);
		textField_FNac_YYYY.setBounds(253, 227, 50, 20);
		contentPane.add(textField_FNac_YYYY);
		
		JLabel lbl_tipo = new JLabel("Tipo de Empleado");
		lbl_tipo.setBounds(10, 258, 115, 20);
		contentPane.add(lbl_tipo);
		

		JLabel lblCantidadDeHoras = new JLabel("Cantidad de horas");
		lblCantidadDeHoras.setBounds(10, 289, 115, 20);
		contentPane.add(lblCantidadDeHoras);
		
		textField_tipoEmpleado = new JTextField();
		textField_tipoEmpleado.setColumns(10);
		textField_tipoEmpleado.setBounds(151, 258, 152, 20);
		contentPane.add(textField_tipoEmpleado);
		
		textField_cantHoras = new JTextField();
		textField_cantHoras.setColumns(10);
		textField_cantHoras.setBounds(151, 289, 152, 20);
		contentPane.add(textField_cantHoras);
		
		JButton btnAltaDeCliente = new JButton("Alta de Empleado");
		btnAltaDeCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getStub()){
				try {
					@SuppressWarnings("deprecation")
//					Date fNac = new Date(Integer.parseInt(textField_FNac_DD.getText()), Integer.parseInt(textField_FNac_MM.getText()), Integer.parseInt(textField_FNac_YYYY.getText()));
					Date fNac = new GregorianCalendar(Integer.parseInt(textField_FNac_YYYY.getText()), (Integer.parseInt(textField_FNac_MM.getText()))-1, Integer.parseInt(textField_FNac_DD.getText())).getTime();
					EmpleadoDTO eDTO = new EmpleadoDTO(
								//comboBox_Empresa.getSelectedItem().toString(), 
								textField_Nombre.getText(), 
								textField_Apellido.getText(),
								textField_Mail.getText(),
								textField_DNI.getText(), 
								textField_Telefono.getText(),
								fNac,
								textField_Legajo.getText(),
								textField_tipoEmpleado.getText(),
								Integer.parseInt(textField_cantHoras.getText())								
								);
					
					textField_Nombre.setText("");
					textField_Apellido.setText("");
					textField_Mail.setText("");
					textField_DNI.setText("");
					textField_Telefono.setText("");
					textField_Legajo.setText("");
					textField_tipoEmpleado.setText("");
					textField_FNac_DD.setText("");
					textField_FNac_MM.setText("");
					textField_FNac_YYYY.setText("");
					textField_cantHoras.setText("");
					
					
						controlPresentismo.agregarEmpleado(eDTO,comboBox_Empresa.getSelectedItem().toString());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}}
			}
		});
		btnAltaDeCliente.setBounds(241, 330, 139, 20);
		contentPane.add(btnAltaDeCliente);
			

		
	}
}
