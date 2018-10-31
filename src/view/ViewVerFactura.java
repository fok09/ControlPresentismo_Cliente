package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Cliente;
import bean.Factura;
import interfaces.SistemaPresentismo;
import srv.ClienteSrv;
import srv.FacturaSrv;

public class ViewVerFactura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Empresa;
	SistemaPresentismo controlPresentismo;
	private JTextField textField_FF_DD;
	private JTextField textField_FF_MM;
	private JTextField textField_FF_AAAA;
	private JTextField textField_NroFactura;
	private JTextField textField_NroFac;
	private JTextField textField_tipoFactura;
	private JTextField textField_montoTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewVerFactura frame = new ViewVerFactura();
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
	public ViewVerFactura() {
		setTitle("Facturacion");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 456, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNroFactura = new JLabel("Numero de Factura");
		labelNroFactura.setBounds(10, 54, 132, 23);
		contentPane.add(labelNroFactura);
		
		System.out.println("pruebacombotipo");
		
		JLabel lblCantHoras = new JLabel("CUIT / CUIL");
		lblCantHoras.setBounds(10, 97, 132, 23);
		contentPane.add(lblCantHoras);
		
		textField_Empresa = new JTextField();
		textField_Empresa.setEditable(false);
		textField_Empresa.setBounds(136, 97, 132, 23);
		contentPane.add(textField_Empresa);
		textField_Empresa.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(10, 11, 108, 23);
		contentPane.add(lblEmpresa);
		System.out.println("prueba comboempresa");
		
		JComboBox<String> comboBox_CUITCUIL = new JComboBox<String>();
		comboBox_CUITCUIL.setBounds(136, 12, 132, 22);
		
		List<Cliente> clientes;
		clientes=ClienteSrv.getClientes();
		
		for (int i=0; i<clientes.size();i++){
			comboBox_CUITCUIL.addItem(clientes.get(i).getCuit_cuil());
		}
	
			System.out.println(clientes.size());
			
		contentPane.add(comboBox_CUITCUIL);
		
		JLabel lblNumFact = new JLabel("Numero de Factura");
		lblNumFact.setBounds(10, 131, 115, 20);
		contentPane.add(lblNumFact);
		
		JLabel lblFechaFact = new JLabel("Fecha");
		lblFechaFact.setBounds(10, 162, 115, 20);
		contentPane.add(lblFechaFact);
		
		textField_FF_DD = new JTextField();
		textField_FF_DD.setEditable(false);
		textField_FF_DD.setColumns(10);
		textField_FF_DD.setBounds(136, 162, 32, 20);
		contentPane.add(textField_FF_DD);
		
		JLabel label_4 = new JLabel("/");
		label_4.setBounds(172, 165, 18, 17);
		contentPane.add(label_4);
		
		textField_FF_MM = new JTextField();
		textField_FF_MM.setEditable(false);
		textField_FF_MM.setColumns(10);
		textField_FF_MM.setBounds(183, 162, 38, 20);
		contentPane.add(textField_FF_MM);
		
		JLabel label_5 = new JLabel("/");
		label_5.setBounds(225, 164, 18, 17);
		contentPane.add(label_5);
		
		textField_FF_AAAA = new JTextField();
		textField_FF_AAAA.setEditable(false);
		textField_FF_AAAA.setColumns(10);
		textField_FF_AAAA.setBounds(237, 162, 50, 20);
		contentPane.add(textField_FF_AAAA);
		
		JLabel lblTipoDeFactura = new JLabel("Tipo de Factura");
		lblTipoDeFactura.setBounds(10, 193, 108, 23);
		contentPane.add(lblTipoDeFactura);
		
		textField_NroFactura = new JTextField();
		textField_NroFactura.setColumns(10);
		textField_NroFactura.setBounds(136, 55, 132, 23);
		contentPane.add(textField_NroFactura);
		
		textField_NroFac = new JTextField();
		textField_NroFac.setEditable(false);
		textField_NroFac.setColumns(10);
		textField_NroFac.setBounds(136, 131, 132, 23);
		contentPane.add(textField_NroFac);
		
		textField_tipoFactura = new JTextField();
		textField_tipoFactura.setEditable(false);
		textField_tipoFactura.setColumns(10);
		textField_tipoFactura.setBounds(136, 194, 132, 23);
		contentPane.add(textField_tipoFactura);
		
		JLabel lblPagado = new JLabel("Pagado");
		lblPagado.setBounds(10, 227, 108, 23);
		contentPane.add(lblPagado);
		
		JCheckBox checkBox_pagado = new JCheckBox("");
		checkBox_pagado.setEnabled(false);
		checkBox_pagado.setBounds(136, 227, 97, 23);
		contentPane.add(checkBox_pagado);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 257, 115, 20);
		contentPane.add(lblTotal);
		
		textField_montoTotal = new JTextField();
		textField_montoTotal.setEditable(false);
		textField_montoTotal.setColumns(10);
		textField_montoTotal.setBounds(136, 257, 132, 23);
		contentPane.add(textField_montoTotal);
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if (getStub()){
				
					Cliente cliente = ClienteSrv.getClienteByCuit(comboBox_CUITCUIL.getSelectedItem().toString());
					Factura f = FacturaSrv.getFacturaByNro(Integer.parseInt(textField_NroFactura.getText()));
					
					textField_Empresa.setText(cliente.getCuit_cuil());
					textField_NroFac.setText(String.valueOf(f.getNroFactura()));
					
					textField_FF_DD.setText(String.valueOf(f.getFecha().getDay()));
					textField_FF_MM.setText(String.valueOf(f.getFecha().getMonth()));
					textField_FF_AAAA.setText(String.valueOf(f.getFecha().getYear()));
					
					textField_tipoFactura.setText(f.getTipo());
					
					if(f.isPagado())
						checkBox_pagado.setSelected(true);
						
					textField_montoTotal.setText(String.valueOf(f.getMonto()));
					}
				
			}
		});
		btnBuscar.setBounds(298, 55, 132, 23);
		contentPane.add(btnBuscar);

		JButton btn_Pagar = new JButton("Pagar");
		btn_Pagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getStub()){
					try {
						Cliente cliente = ClienteSrv.getClienteByCuit(textField_Empresa.getText());
						Factura f = FacturaSrv.getFacturaByNro(Integer.parseInt(textField_NroFac.getText()));
				
						controlPresentismo.registrarPago(f.getNroFactura());
					
					
						checkBox_pagado.setSelected(true);
					
						FacturaSrv.grabarFactura(f);
					
					} catch (RemoteException e1) {
	
						e1.printStackTrace();
					}
				
				}
			}
		});
		btn_Pagar.setBounds(136, 299, 132, 32);
		contentPane.add(btn_Pagar);
		
	}
}
