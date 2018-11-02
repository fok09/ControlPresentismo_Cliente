package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Cliente;
import bean.Factura;
import dto.FacturaDTO;
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
	private JTextField textField_NroFac;
	private JTextField textField_tipoFactura;
	private JTextField textField_montoTotal;
	private JButton btn_Pagar;
	private List<Factura> facturas;
	private JComboBox<Factura> comboBox_Factura;

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
			controlPresentismo = (SistemaPresentismo) Naming.lookup("//localhost/ControladorPresentismo");
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
		setBounds(100, 100, 467, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelNroFactura = new JLabel("Factura");
		labelNroFactura.setBounds(10, 55, 132, 23);
		contentPane.add(labelNroFactura);

		JLabel lblCantHoras = new JLabel("CUIT / CUIL");
		lblCantHoras.setBounds(10, 123, 132, 23);
		contentPane.add(lblCantHoras);

		textField_Empresa = new JTextField();
		textField_Empresa.setEditable(false);
		textField_Empresa.setBounds(136, 123, 132, 23);
		contentPane.add(textField_Empresa);
		textField_Empresa.setColumns(10);

		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(10, 11, 108, 23);
		contentPane.add(lblEmpresa);

		JComboBox<Cliente> comboBox_CUITCUIL = new JComboBox<Cliente>();
		comboBox_CUITCUIL.setBounds(136, 12, 294, 22);

		List<Cliente> listaClientes;
		listaClientes = ClienteSrv.getClientes();

		for (Cliente c : listaClientes) {
			comboBox_CUITCUIL.addItem(c);
		}

		contentPane.add(comboBox_CUITCUIL);

		comboBox_Factura = new JComboBox<Factura>();
		comboBox_Factura.setBounds(136, 56, 294, 20);
		contentPane.add(comboBox_Factura);

		JLabel lblNumFact = new JLabel("Numero de Factura");
		lblNumFact.setBounds(10, 157, 115, 20);
		contentPane.add(lblNumFact);

		JLabel lblFechaFact = new JLabel("Fecha");
		lblFechaFact.setBounds(10, 188, 115, 20);
		contentPane.add(lblFechaFact);

		textField_FF_DD = new JTextField();
		textField_FF_DD.setEditable(false);
		textField_FF_DD.setColumns(10);
		textField_FF_DD.setBounds(136, 188, 32, 20);
		contentPane.add(textField_FF_DD);

		JLabel label_4 = new JLabel("/");
		label_4.setBounds(178, 190, 18, 17);
		contentPane.add(label_4);

		textField_FF_MM = new JTextField();
		textField_FF_MM.setEditable(false);
		textField_FF_MM.setColumns(10);
		textField_FF_MM.setBounds(188, 188, 38, 20);
		contentPane.add(textField_FF_MM);

		JLabel label_5 = new JLabel("/");
		label_5.setBounds(236, 190, 18, 17);
		contentPane.add(label_5);

		textField_FF_AAAA = new JTextField();
		textField_FF_AAAA.setEditable(false);
		textField_FF_AAAA.setColumns(10);
		textField_FF_AAAA.setBounds(246, 188, 50, 20);
		contentPane.add(textField_FF_AAAA);

		JLabel lblTipoDeFactura = new JLabel("Tipo de Factura");
		lblTipoDeFactura.setBounds(10, 219, 108, 23);
		contentPane.add(lblTipoDeFactura);

		textField_NroFac = new JTextField();
		textField_NroFac.setEditable(false);
		textField_NroFac.setColumns(10);
		textField_NroFac.setBounds(136, 157, 160, 23);
		contentPane.add(textField_NroFac);

		textField_tipoFactura = new JTextField();
		textField_tipoFactura.setEditable(false);
		textField_tipoFactura.setColumns(10);
		textField_tipoFactura.setBounds(136, 219, 90, 23);
		contentPane.add(textField_tipoFactura);

		JLabel lblPagado = new JLabel("Pagado");
		lblPagado.setBounds(10, 253, 108, 23);
		contentPane.add(lblPagado);

		JCheckBox checkBox_pagado = new JCheckBox("");
		checkBox_pagado.setEnabled(false);
		checkBox_pagado.setBounds(136, 253, 97, 23);
		contentPane.add(checkBox_pagado);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 287, 115, 20);
		contentPane.add(lblTotal);

		textField_montoTotal = new JTextField();
		textField_montoTotal.setEditable(false);
		textField_montoTotal.setColumns(10);
		textField_montoTotal.setBounds(136, 283, 132, 23);
		contentPane.add(textField_montoTotal);

		/***************************************************************************************/

		comboBox_CUITCUIL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getStub()) {

					try {
						Cliente cliente = (Cliente) comboBox_CUITCUIL.getSelectedItem();
						facturas = controlPresentismo.getFacturasCliente(cliente);
						if (comboBox_Factura.getItemCount() > 0)
							comboBox_Factura.removeAllItems();
						for (Factura f : facturas) {
							comboBox_Factura.addItem(f);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

				}
			}

		});

		/***************************************************************************************/
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getStub()) {
					try {
						Cliente cliente = (Cliente) comboBox_CUITCUIL.getSelectedItem();
						Factura factura = (Factura) comboBox_Factura.getSelectedItem();
						if (factura == null) {
							textField_FF_AAAA.setText("");
							textField_FF_MM.setText("");
							textField_FF_DD.setText("");
							textField_tipoFactura.setText("");
							textField_NroFac.setText("");
							textField_montoTotal.setText("");
							checkBox_pagado.setSelected(false);
						}

						textField_Empresa.setText(cliente.getCuit_cuil());
						textField_NroFac.setText(String.valueOf(factura.getNroFactura()));
						Instant i = factura.getFecha().toInstant();
						ZonedDateTime z = i.atZone(ZoneId.systemDefault());
						LocalDate fecha = z.toLocalDate();
						textField_FF_DD.setText(fecha.getDayOfMonth() + "");
						textField_FF_MM.setText(fecha.getMonthValue() + "");
						textField_FF_AAAA.setText(fecha.getYear() + "");

						textField_tipoFactura.setText(factura.getTipo());

						if (factura.isPagado()) {
							checkBox_pagado.setSelected(true);
							btn_Pagar.setEnabled(false);
						}

						textField_montoTotal.setText(String.valueOf(factura.getMonto()));
					} catch (Exception e1) {
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "La factura ingresada no existe");
						e1.printStackTrace();
					}
				}
			}
		});
		btnBuscar.setBounds(136, 89, 258, 23);
		contentPane.add(btnBuscar);

		btn_Pagar = new JButton("Facturar");
		btn_Pagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getStub()) {
					try {
						ClienteSrv.getClienteByCuit(textField_Empresa.getText());

						Factura f = FacturaSrv.getFacturaByNro(Integer.parseInt(textField_NroFac.getText()));

						Date fechaActual = Calendar.getInstance().getTime();

						FacturaDTO fDTO = new FacturaDTO();
						fDTO.setCliente(f.getCliente());
						fDTO.setFecha(f.getFecha());
						fDTO.setTipo(f.getTipo());
						fDTO.setContratacion(f.getContratacion());
						fDTO.setFechaPago(fechaActual);
						fDTO.setMonto(f.getMonto());
						fDTO.setNroFactura(f.getNroFactura());
						fDTO.setPagado(f.isPagado());
						fDTO.setPagado(true);

						controlPresentismo.registrarPago(fDTO);

						checkBox_pagado.setSelected(true);
						
						if (f.isPagado()) {
							checkBox_pagado.setSelected(true);
							btn_Pagar.setEnabled(false);
						}

					} catch (RemoteException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		btn_Pagar.setBounds(164, 347, 132, 32);
		contentPane.add(btn_Pagar);

	}
}
