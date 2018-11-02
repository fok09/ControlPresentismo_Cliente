package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Empleado;
import interfaces.SistemaPresentismo;
import srv.EmpleadoSrv;

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
import java.awt.Color;

public class ViewEliminarEmpleado extends JFrame {

	private JPanel contentPane;
	SistemaPresentismo controlPresentismo;
	private JTextField textField_Apellido;
	private JTextField textField_Nombre;
	private JTextField textField_estado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEliminarEmpleado frame = new ViewEliminarEmpleado();
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


	public ViewEliminarEmpleado() {
		setTitle("Eliminar Empleado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_Apellido = new JTextField();
		textField_Apellido.setEditable(false);
		textField_Apellido.setBounds(196, 65, 132, 20);
		contentPane.add(textField_Apellido);
		textField_Apellido.setColumns(10);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setEditable(false);
		textField_Nombre.setColumns(10);
		textField_Nombre.setBounds(196, 96, 132, 20);
		contentPane.add(textField_Nombre);
		
		textField_estado = new JTextField();
		textField_estado.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		textField_estado.setForeground(Color.RED);
		textField_estado.setEditable(false);
		textField_estado.setColumns(10);
		textField_estado.setBounds(58, 230, 222, 20);
		contentPane.add(textField_estado);
		
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(58, 68, 97, 14);
		contentPane.add(lblApellido);
		
		JComboBox<Empleado> comboBox_Empleados = new JComboBox<Empleado>();
		if(getStub()){
			List<Empleado> empleados;
			empleados=EmpleadoSrv.getEmpleados();
			for (Empleado e: empleados){
				comboBox_Empleados.addItem(e);
			}
		}
		comboBox_Empleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(getStub()){
					Empleado empleado = (Empleado)comboBox_Empleados.getSelectedItem();

					textField_Apellido.setText(empleado.getApellido());
					textField_Nombre.setText(empleado.getNombre());
				}
				
			}
		});
		comboBox_Empleados.setBounds(196, 32, 132, 22);
		contentPane.add(comboBox_Empleados);
		

		
		
		if(getStub()){
			
			Empleado empleado = (Empleado)comboBox_Empleados.getSelectedItem();

			textField_Apellido.setText(empleado.getApellido());
			textField_Nombre.setText(empleado.getNombre());
		
		}
		
		JLabel lblEmpleado = new JLabel("Dni del empleado");
		lblEmpleado.setBounds(58, 32, 128, 23);
		contentPane.add(lblEmpleado);
	
		
		JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
		btnEliminarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Empleado empleado = (Empleado)comboBox_Empleados.getSelectedItem();

				
				EmpleadoSrv.eliminarEmpleado(empleado);
				
				textField_estado.setText("El Empleado ha sido eliminado");
				
			}
		});
		btnEliminarEmpleado.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
		btnEliminarEmpleado.setBounds(101, 134, 204, 67);
		contentPane.add(btnEliminarEmpleado);
		
		JButton btnAtras = new JButton("Volver");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewAdministracion a = new ViewAdministracion();
				a.setVisible(true);
				
				dispose();
			}
		});
		btnAtras.setBounds(318, 227, 89, 23);
		contentPane.add(btnAtras);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(58, 102, 97, 14);
		contentPane.add(lblNombre);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 236, 97, 14);
		contentPane.add(lblEstado);
		

	}
}
