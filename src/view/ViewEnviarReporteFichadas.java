package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.Contratacion;
import bean.Empleado;
import bean.Fichada;
import bean.PersonaJuridica;
import dto.EmpleadoHorasDTO;
import interfaces.SistemaPresentismo;
import srv.ClienteSrv;

public class ViewEnviarReporteFichadas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private List<EmpleadoHorasDTO> empleadoHoras;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEnviarReporteFichadas frame = new ViewEnviarReporteFichadas();
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
	
	public ViewEnviarReporteFichadas()
	{
		setTitle("Envio de Reporte");
		columnNames = new Vector<String>();
		//columnNames.addElement("Cuit/Cuil");
		//columnNames.addElement("DNI");
	    columnNames.addElement("Legajo");
	    columnNames.addElement("Nombre");
	    columnNames.addElement("Total horas");
	    columnNames.addElement("Horas Ausente");
	    //columnNames.addElement("Horas de Vacaciones");
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
		setBounds(100, 100, 565, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 122, 515, 228);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(423, 421, 112, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		
		lblCuitcuil = new JLabel("CUIT/CUIL");
		lblCuitcuil.setBounds(15, 16, 94, 22);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(datosTabla, columnNames));
		contentPane.setLayout(null);
		contentPane.add(btnVolver);
		contentPane.add(scrollPane);
		contentPane.add(lblCuitcuil);
		
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
		comboBox_Contratacion.setBounds(131, 50, 362, 21);
		contentPane.add(comboBox_Contratacion);
		
		JComboBox comboBox_Empresa = new JComboBox();
		comboBox_Empresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (getStub()) {
					
					try {
						String cuitEmpresa = (String) comboBox_Empresa.getSelectedItem().toString();
						cuitEmpresa = cuitEmpresa.substring(0,cuitEmpresa.indexOf(" "));
						
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
			List<PersonaJuridica> clientesPJ;
			clientesPJ=ClienteSrv.getClientesJuridicos();
			for (int i=0; i<clientesPJ.size();i++){
				comboBox_Empresa.addItem(clientesPJ.get(i).getCuit_cuil().toString() + " - " + clientesPJ.get(i).getRazonSocial());
			}
		}
		comboBox_Empresa.setBounds(131, 17, 362, 21);
		contentPane.add(comboBox_Empresa);
		
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(418, 82, 112, 23);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if (getStub()) {
					Contratacion cont = new Contratacion();
					String cuit = (String) comboBox_Empresa.getSelectedItem();
					String seleccion = comboBox_Contratacion.getSelectedItem().toString();
					Vector<Vector<String>> dt = new Vector<Vector<String>>();
					
					int cod = Integer.parseInt(seleccion.substring(0,seleccion.indexOf(" ")));
					cuit = cuit.substring(0,cuit.indexOf(" "));
					for (Contratacion c : contrataciones ) {
						if (c.getId()==cod) {
							cont = c;
						}
					}
					
					try {
						empleadoHoras = controlPresentismo.getHorasTrabajadasTotalesLiqui(cuit,cFechaInicio,cFechaFin);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					for (EmpleadoHorasDTO e : empleadoHoras) {
						Vector<String> strs = new Vector<String>();
						strs.add(String.valueOf(e.getLegajo()));
						strs.add(String.valueOf(e.getApellido() + " " + e.getNombre()));
						strs.add(String.valueOf(e.getHorasTrabajadas()));
						strs.add(String.valueOf(e.getHorasAusentes()));
						dt.add(strs);
					}
					datosTabla = dt;
					actualizarTabla();
					}
			}
		});
		
		contentPane.add(btnListar);
		
		
		
		JLabel lblContratacion = new JLabel("Contratacion");
		lblContratacion.setBounds(15, 49, 94, 22);
		contentPane.add(lblContratacion);
		
		JLabel lblEnviarReporteA = new JLabel("Enviar reporte a");
		lblEnviarReporteA.setBounds(15, 383, 94, 19);
		contentPane.add(lblEnviarReporteA);
		
		JComboBox comboBox_Destinatario = new JComboBox();
		comboBox_Destinatario.setBounds(136, 382, 208, 21);
		contentPane.add(comboBox_Destinatario);
		comboBox_Destinatario.addItem("Gimnasio");
		comboBox_Destinatario.addItem("Liquidacion de Sueldos");
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if (comboBox_Destinatario.getSelectedItem().toString().equals("Gimnasio")){
					controlPresentismo.enviarHorasTotales(empleadoHoras, false);
				}
				if (comboBox_Destinatario.getSelectedItem().toString().equals("Liquidacion de Sueldos")){
					
						controlPresentismo.enviarHorasTotales(empleadoHoras, true);
					} }catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		btnNewButton.setBounds(423, 379, 112, 23);
		contentPane.add(btnNewButton);

		
		modeloTabla = (DefaultTableModel) table.getModel();
	    modeloTabla.setDataVector(datosTabla, columnNames);
	}
}
