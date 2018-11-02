package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Cliente;
import bean.Empleado;
import bean.Factura;
import dto.FichadaDTO;

import interfaces.SistemaPresentismo;
import srv.ClienteSrv;
import srv.EmpleadoSrv;

public class ViewCrearFichada extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	SistemaPresentismo controlPresentismo;
	private List<Empleado> empleados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCrearFichada frame = new ViewCrearFichada();
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
	public ViewCrearFichada() {
		setTitle("Fichada de Empleados");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipoDeFichada = new JLabel("Tipo de Fichada");
		lblTipoDeFichada.setBounds(10, 54, 132, 23);
		contentPane.add(lblTipoDeFichada);
		
		JComboBox<String> comboBox_TipoFichada = new JComboBox<String>();
		comboBox_TipoFichada.setBounds(136, 55, 146, 23);
		comboBox_TipoFichada.addItem("Entrada");
		comboBox_TipoFichada.addItem("Salida");
		contentPane.add(comboBox_TipoFichada);
		
		JLabel lblEmpleado = new JLabel("Empleado (Legajo)");
		lblEmpleado.setBounds(10, 97, 132, 23);
		contentPane.add(lblEmpleado);
		
		JComboBox<Empleado> comboBoxLegajo = new JComboBox<Empleado>();
		comboBoxLegajo.setBounds(136, 98, 263, 20);
		List<Empleado> listaEmpleados;
		listaEmpleados = EmpleadoSrv.getEmpleados();

		for (Empleado e : listaEmpleados) {
			comboBoxLegajo.addItem(e);
		}
		contentPane.add(comboBoxLegajo);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(10, 11, 108, 23);
		contentPane.add(lblEmpresa);
		
		JComboBox<Cliente> comboBox_Empresa = new JComboBox<Cliente>();
		comboBox_Empresa.setBounds(136, 12, 263, 22);
		List<Cliente> listaClientes = ClienteSrv.getClientes();
		for (Cliente c : listaClientes) {
			comboBox_Empresa.addItem(c);
		}
		contentPane.add(comboBox_Empresa);
//		if(getStub()){
//			List<Cliente> clientes;
//			clientes=ClienteSrv.getClientes();
//			for (int i=0; i<clientes.size();i++){
//				comboBox_Empresa.addItem(clientes.get(i).getCuit_cuil());
//			}
//		}
		
		comboBox_Empresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getStub()) {
					Cliente cliente = (Cliente) comboBox_Empresa.getSelectedItem();
					empleados = cliente.getEmpleados();
					if (comboBoxLegajo.getItemCount() > 0)
						comboBoxLegajo.removeAllItems();
					for (Empleado em : empleados) {
						comboBoxLegajo.addItem(em);
					}

				}
			}

		});
		
		JButton btnNewButton = new JButton("Fichar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getStub()){
					try {
						LocalDateTime hora = LocalDateTime.now();
						Date fecha = Calendar.getInstance().getTime();
//						List<Cliente> clientes;
//						clientes=ClienteSrv.getClientes();
//						Empleado emp= new Empleado();
//						for(Cliente c: clientes) {
//							if(c.getCuit_cuil().equals(comboBox_Empresa.getSelectedItem())){
//								emp= c.obtenerEmpleadoPorLegajo(textField_Legajo.getText());
//							}
//						}
						
						Cliente cliente = (Cliente) comboBox_Empresa.getSelectedItem();
						Empleado empleado= (Empleado) comboBoxLegajo.getSelectedItem();
						
						String tipo;
						
						if (comboBox_TipoFichada.getSelectedItem().toString().equals("Entrada")) {
							tipo="E";
													}
						else {
							tipo="S";
						}
						
						if(empleado != null) {
							FichadaDTO fDTO = new FichadaDTO(
								tipo,
								empleado,
								hora,
								fecha);
//							textField_Legajo.setText("");
							controlPresentismo.altaFichada(fDTO);
						} else {
							Component frame = null;
							JOptionPane.showMessageDialog(frame, "Empleado inexistente");
						}
					} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			}
		});
		btnNewButton.setBounds(291, 162, 108, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolver.setBounds(10, 162, 89, 23);
		contentPane.add(btnVolver);
		
		
		
	}
}
