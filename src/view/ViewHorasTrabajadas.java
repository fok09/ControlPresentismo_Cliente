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

public class ViewHorasTrabajadas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private SistemaPresentismo controlPresentismo;
	private DefaultTableModel modeloTabla;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> columnNames;
	
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
		setBounds(100, 100, 557, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnVolver)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(btnVolver))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		table.setModel(new DefaultTableModel(datosTabla, columnNames));
		modeloTabla = (DefaultTableModel) table.getModel();
	    modeloTabla.setDataVector(datosTabla, columnNames);
	}
}
