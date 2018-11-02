package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import bean.Empleado;
import bean.Servicio;
import dto.ContratacionDTO;
import dto.FichadaDTO;

import interfaces.SistemaPresentismo;
import srv.ClienteSrv;
import srv.ContratacionSrv;
import srv.FacturaSrv;
import srv.ServicioSrv;

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
	private JTextField textField_CantEmp;
	private JTextField textField_NuevaFact;

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
		setBounds(100, 100, 372, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelEmpresa = new JLabel("Empresa");
		labelEmpresa.setBounds(10, 54, 132, 23);
		contentPane.add(labelEmpresa);
		
		JComboBox<String> comboBox_Empresa = new JComboBox<String>();
		comboBox_Empresa.setBounds(136, 55, 132, 23);
		if(getStub()){
			List<Cliente> clientes;
			clientes=ClienteSrv.getClientes();
			
			for (int i=0; i<clientes.size();i++){
				comboBox_Empresa.addItem(clientes.get(i).getCuit_cuil().toString());
			}
		}
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
				
		JComboBox<String> comboBox_Servicio = new JComboBox<String>();
		comboBox_Servicio.setBounds(136, 12, 132, 22);
		if(getStub()){
			List<Servicio> servicios;
			servicios=ServicioSrv.getServicios();

			for (int i=0; i<servicios.size();i++){
				comboBox_Servicio.addItem(servicios.get(i).getId() + " "
			+servicios.get(i).getNombre().toString());
			}
		}
		contentPane.add(comboBox_Servicio);
		
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
		comboBox_TipoFactura.addItem("A");
		comboBox_TipoFactura.addItem("B");
		comboBox_TipoFactura.addItem("C");
		contentPane.add(comboBox_TipoFactura);
		
		JLabel lblCantidadDeEmpleados = new JLabel("Cantidad de Empleados");
		lblCantidadDeEmpleados.setBounds(10, 230, 132, 23);
		contentPane.add(lblCantidadDeEmpleados);
		
		textField_CantEmp = new JTextField();
		textField_CantEmp.setColumns(10);
		textField_CantEmp.setBounds(136, 230, 132, 23);
		contentPane.add(textField_CantEmp);

		JButton btnNewButton = new JButton("Generar Contratacion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getStub()){
					try {
						String seleccion = comboBox_Servicio.getSelectedItem().toString();
						int cod = Integer.parseInt(seleccion.substring(0,seleccion.indexOf(" ")));
						Servicio sv = new Servicio();
						sv = ServicioSrv.getServicio(cod);
						Cliente cl = new Cliente();
						cl = ClienteSrv.getClienteByCuit(comboBox_Empresa.getSelectedItem().toString());
						Date fIni = new GregorianCalendar(Integer.parseInt(textField_FI_AAAA.getText()), (Integer.parseInt(textField_FI_MM.getText()))-1, Integer.parseInt(textField_FI_DD.getText())).getTime();
						Date fFin = new GregorianCalendar(Integer.parseInt(textField_FF_AAAA.getText()), (Integer.parseInt(textField_FF_MM.getText()))-1, Integer.parseInt(textField_FF_DD.getText())).getTime();
						ContratacionDTO cDTO = new ContratacionDTO(
						sv,
						cl,
						Integer.parseInt(textField_CantHoras.getText()),
						Integer.parseInt(textField_CantEmp.getText()),
						fIni,
						fFin,
						comboBox_TipoFactura.getSelectedItem().toString(),
						sv.getMonto()						
						);
				controlPresentismo.crearContratacion(cDTO);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			}
		});
		btnNewButton.setBounds(174, 264, 172, 23);
		contentPane.add(btnNewButton);
		
	}
}
