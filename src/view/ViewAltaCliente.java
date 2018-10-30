package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViewAltaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAltaCliente frame = new ViewAltaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewAltaCliente() {
		setTitle("Alta de Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 218, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Persona Fisica");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAltaClienteFisico altaClienteFisico = new ViewAltaClienteFisico();
				altaClienteFisico.setVisible(true);
			}
		});
		btnNewButton.setBounds(26, 26, 146, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Persona Juridica");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAltaClienteJuridico altaClienteJuridico = new ViewAltaClienteJuridico();
				altaClienteJuridico.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(26, 79, 146, 31);
		contentPane.add(btnNewButton_1);
	}

}
