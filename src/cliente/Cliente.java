package cliente;

import view.ViewPrincipal;

import java.rmi.Naming;

import interfaces.SistemaPresentismo;

public class Cliente {
	SistemaPresentismo controlPresentismo;
	
	public static void main(String[] args)
	{
		new Cliente();
		ViewPrincipal principal = new ViewPrincipal();
		principal.setVisible(true);
	}
	
	public boolean getStub() {
    	
    	try {
    		controlPresentismo = (SistemaPresentismo)Naming.lookup ("//localhost/ControladorPresentismoRO");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }
	
	public Cliente (){
    	if(getStub()) 
    	{
    		System.out.println("\nConexion exitosa");
    	}
	}
}
