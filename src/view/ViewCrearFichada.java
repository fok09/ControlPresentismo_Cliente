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
import dto.FichadaDTO;

import interfaces.SistemaPresentismo;
import srv.ClienteSrv;

public class ViewCrearFichada extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Legajo;
	SistemaPresentismo controlPresentismo;

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
		setBounds(100, 100, 372, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipoDeFichada = new JLabel("Tipo de Fichada");
		lblTipoDeFichada.setBounds(10, 54, 132, 23);
		contentPane.add(lblTipoDeFichada);
		
		JComboBox<String> comboBox_TipoFichada = new JComboBox<String>();
		comboBox_TipoFichada.setBounds(136, 55, 132, 23);
		comboBox_TipoFichada.addItem("Entrada");
		comboBox_TipoFichada.addItem("Salida");
		contentPane.add(comboBox_TipoFichada);
		
		JLabel lblEmpleado = new JLabel("Empleado (Legajo)");
		lblEmpleado.setBounds(10, 97, 132, 23);
		contentPane.add(lblEmpleado);
		
		textField_Legajo = new JTextField();
		textField_Legajo.setBounds(136, 97, 132, 23);
		contentPane.add(textField_Legajo);
		textField_Legajo.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(10, 11, 108, 23);
		contentPane.add(lblEmpresa);
		JComboBox<String> comboBox_Empresa = new JComboBox<String>();
		comboBox_Empresa.setBounds(136, 12, 132, 22);
		contentPane.add(comboBox_Empresa);
		if(getStub()){
			List<Cliente> clientes;
			clientes=ClienteSrv.getClientes();
			for (int i=0; i<clientes.size();i++){
				comboBox_Empresa.addItem(clientes.get(i).getCuit_cuil());
			}
			System.out.println(clientes.size());
		}
		
		JButton btnNewButton = new JButton("Fichar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getStub()){
					try {
						LocalDateTime hora = LocalDateTime.now();
						Date fecha = Calendar.getInstance().getTime();
						System.out.println("prueba");
						List<Cliente> clientes;
						clientes=ClienteSrv.getClientes();
						Empleado emp= new Empleado();
						for(Cliente c: clientes) {
							if(c.getCuit_cuil().equals(comboBox_Empresa.getSelectedItem())){
								emp= c.obtenerEmpleadoPorLegajo(textField_Legajo.getText());
							}
						}
						String tipo;
						
						if (comboBox_TipoFichada.getSelectedItem().toString().equals("Entrada")) {
							tipo="E";
													}
						else {
							tipo="S";
						}
						
						if(emp != null) {
							FichadaDTO fDTO = new FichadaDTO(
								tipo,
								emp,
								hora,
								fecha);
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
		btnNewButton.setBounds(238, 143, 108, 23);
		contentPane.add(btnNewButton);
		
	}
}
