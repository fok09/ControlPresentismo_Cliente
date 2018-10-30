package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import interfaces.SistemaPresentismo;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewHorasTrabajadas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private SistemaPresentismo controlPresentismo;
	private DefaultTableModel modeloTabla;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> columnNames;
	private JLabel lblCuitcuil;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JTextField textField_CUITCUIL;
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
	
	
	public ViewHorasTrabajadas()
	{
		setTitle("Horas Trabajadas");
		iniciarVentasAnteriores();
	}
	
	public ViewHorasTrabajadas(SistemaPresentismo sp) throws RemoteException
	{
		String cuit_cuil = "";
//		Date fechaInicio = new Date;
//		Date fechaFin;
		
		this.controlPresentismo = sp;
		columnNames = new Vector<String>();
	    columnNames.addElement("Legajo");
	    columnNames.addElement("Nombre");
	    columnNames.addElement("Total horas");
//	    columnNames.addElement("Horas Ausente");
//	    datosTabla = controlPresentismo.getHorasTrabajadas( cuit_cuil,  fechaInicio,  fechaFin);
		iniciarVentasAnteriores();
	}
	
	
	

	/**
	 * Create the frame.
	 */
	private void iniciarVentasAnteriores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 169, 515, 209);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(468, 389, 63, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		
		lblCuitcuil = new JLabel("CUIT/CUIL");
		lblCuitcuil.setBounds(15, 16, 94, 22);
		
		lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(15, 51, 100, 22);
		
		lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(15, 87, 112, 22);
		
		textField_CUITCUIL = new JTextField();
		textField_CUITCUIL.setBounds(133, 17, 148, 20);
		textField_CUITCUIL.setColumns(10);
		
		textField_FI_DD = new JTextField();
		textField_FI_DD.setBounds(133, 52, 35, 21);
		textField_FI_DD.setColumns(10);
		
		textField_FI_AAAA = new JTextField();
		textField_FI_AAAA.setBounds(253, 52, 46, 20);
		textField_FI_AAAA.setColumns(10);
		
		textField_FI_MM = new JTextField();
		textField_FI_MM.setBounds(191, 52, 35, 20);
		textField_FI_MM.setColumns(10);
		
		label = new JLabel("/");
		label.setBounds(172, 49, 15, 27);
		
		label_1 = new JLabel("/");
		label_1.setBounds(234, 49, 15, 27);
		
		textField_FF_DD = new JTextField();
		textField_FF_DD.setBounds(131, 90, 35, 21);
		textField_FF_DD.setColumns(10);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(170, 87, 15, 27);
		
		textField_FF_MM = new JTextField();
		textField_FF_MM.setBounds(189, 90, 35, 20);
		textField_FF_MM.setColumns(10);
		
		JLabel label_3 = new JLabel("/");
		label_3.setBounds(232, 87, 15, 27);
		
		textField_FF_AAAA = new JTextField();
		textField_FF_AAAA.setBounds(251, 90, 46, 20);
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
		contentPane.add(textField_CUITCUIL);
		contentPane.add(textField_FI_DD);
		contentPane.add(label);
		contentPane.add(textField_FI_MM);
		contentPane.add(label_1);
		contentPane.add(textField_FI_AAAA);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(290, 121, 112, 23);
		contentPane.add(btnListar);
		modeloTabla = (DefaultTableModel) table.getModel();
	    modeloTabla.setDataVector(datosTabla, columnNames);
	}
}
