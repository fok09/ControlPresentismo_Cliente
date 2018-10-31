package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.Cliente;
import bean.Contratacion;
import interfaces.SistemaPresentismo;
import srv.ClienteSrv;

public class ViewHorasTrabajadas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private SistemaPresentismo controlPresentismo;
	private DefaultTableModel modeloTabla;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> columnNames;
	private List<Contratacion> contrataciones;
	private Date cFechaInicio;
	private Date cFechaFin;
	private JLabel lblCuitcuil;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JTextField textField_FI_DD;
	private JTextField textField_FI_AAAA;
	private JTextField textField_FI_MM;
	private JLabel label;
	private JLabel label_1;
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
					ViewHorasTrabajadas frame = new ViewHorasTrabajadas();
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
	
	public void actualizarTabla()
	{
		modeloTabla = (DefaultTableModel) table.getModel();
		modeloTabla.setDataVector(datosTabla, columnNames);
	}
	
	public ViewHorasTrabajadas()
	{
		setTitle("Horas Trabajadas");
		columnNames = new Vector<String>();
	    columnNames.addElement("Legajo");
	    columnNames.addElement("Nombre");
	    columnNames.addElement("Total horas");
	    columnNames.addElement("Horas Ausente");
		iniciarPantalla();
	}
	
//	public ViewHorasTrabajadas() throws RemoteException
//	{
//		String cuit_cuil = "";
//		Date fechaInicio = new Date;
//		Date fechaFin;
		
//	    columnNames.addElement("Horas Ausente");
//		iniciarVentasAnteriores();
//	}
	
	
	

	/**
	 * Create the frame.
	 */
	private void iniciarPantalla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 206, 515, 211);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(418, 441, 112, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		
		lblCuitcuil = new JLabel("CUIT/CUIL");
		lblCuitcuil.setBounds(15, 16, 94, 22);
		
		lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(15, 84, 100, 22);
		
		lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(15, 120, 112, 22);
		
		textField_FI_DD = new JTextField();
		textField_FI_DD.setEditable(false);
		textField_FI_DD.setBounds(133, 85, 35, 21);
		textField_FI_DD.setColumns(10);
		
		textField_FI_AAAA = new JTextField();
		textField_FI_AAAA.setEditable(false);
		textField_FI_AAAA.setBounds(253, 85, 46, 20);
		textField_FI_AAAA.setColumns(10);
		
		textField_FI_MM = new JTextField();
		textField_FI_MM.setEditable(false);
		textField_FI_MM.setBounds(191, 85, 35, 20);
		textField_FI_MM.setColumns(10);
		
		label = new JLabel("/");
		label.setBounds(172, 82, 15, 27);
		
		label_1 = new JLabel("/");
		label_1.setBounds(234, 82, 15, 27);
		
		textField_FF_DD = new JTextField();
		textField_FF_DD.setEditable(false);
		textField_FF_DD.setBounds(131, 123, 35, 21);
		textField_FF_DD.setColumns(10);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(170, 120, 15, 27);
		
		textField_FF_MM = new JTextField();
		textField_FF_MM.setEditable(false);
		textField_FF_MM.setBounds(189, 123, 35, 20);
		textField_FF_MM.setColumns(10);
		
		JLabel label_3 = new JLabel("/");
		label_3.setBounds(232, 120, 15, 27);
		
		textField_FF_AAAA = new JTextField();
		textField_FF_AAAA.setEditable(false);
		textField_FF_AAAA.setBounds(251, 123, 46, 20);
		textField_FF_AAAA.setColumns(10);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(datosTabla, columnNames));
		contentPane.setLayout(null);
		contentPane.add(btnVolver);
		contentPane.add(scrollPane);
		contentPane.add(lblFechaFin);
		contentPane.add(textField_FF_DD);
		contentPane.add(label_2);
		contentPane.add(textField_FF_MM);
		contentPane.add(label_3);
		contentPane.add(textField_FF_AAAA);
		contentPane.add(lblFechaInicio);
		contentPane.add(lblCuitcuil);
		contentPane.add(textField_FI_DD);
		contentPane.add(label);
		contentPane.add(textField_FI_MM);
		contentPane.add(label_1);
		contentPane.add(textField_FI_AAAA);
		
		JComboBox comboBox_Contratacion = new JComboBox();
		comboBox_Contratacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = comboBox_Contratacion.getSelectedItem().toString();
				int cod = Integer.parseInt(seleccion.substring(0,seleccion.indexOf(" ")));
				for (Contratacion c : contrataciones ) {
					if (c.getId()==cod) {
						cFechaInicio = c.getFechaInicial();
						cFechaFin = c.getFechaFinal();
					}
				}
	
				
			}
		});
		comboBox_Contratacion.setBounds(131, 50, 166, 21);
		contentPane.add(comboBox_Contratacion);
		
		JComboBox comboBox_Empresa = new JComboBox();
		comboBox_Empresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (getStub()) {
					
					try {
						String cuitEmpresa = (String) comboBox_Empresa.getSelectedItem();
//						List<Contratacion> contrataciones;
						contrataciones = controlPresentismo.getContratacionesCliente(cuitEmpresa);
						comboBox_Contratacion.removeAllItems();
						for (Contratacion c : contrataciones){
							comboBox_Contratacion.addItem(c.getId() + " " + c.getServicio().getNombre() + " - " + c.getFechaInicial() + " a " + c.getFechaFinal());							
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		});
		if(getStub()){
			List<Cliente> clientes;
			clientes=ClienteSrv.getClientes();
			
			for (int i=0; i<clientes.size();i++){
				comboBox_Empresa.addItem(clientes.get(i).getCuit_cuil());
			}
		}
		comboBox_Empresa.setBounds(131, 17, 166, 21);
		contentPane.add(comboBox_Empresa);
		
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(293, 156, 112, 23);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if (getStub()) {
					try {
						Contratacion cont = new Contratacion();
						String cuit = (String) comboBox_Empresa.getSelectedItem();
						String seleccion = comboBox_Contratacion.getSelectedItem().toString();
						int cod = Integer.parseInt(seleccion.substring(0,seleccion.indexOf(" ")));
						for (Contratacion c : contrataciones ) {
							if (c.getId()==cod) {
								cont = c;
							}
						}
						
						datosTabla = controlPresentismo.getHorasTrabajadasTotales(cuit,cFechaInicio,cFechaFin, cont);
						actualizarTabla();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					}
			}
		});
		
		contentPane.add(btnListar);
		
		
		
		JLabel lblContratacion = new JLabel("Contratacion");
		lblContratacion.setBounds(15, 49, 94, 22);
		contentPane.add(lblContratacion);
		
		
		modeloTabla = (DefaultTableModel) table.getModel();
	    modeloTabla.setDataVector(datosTabla, columnNames);
	}
}
