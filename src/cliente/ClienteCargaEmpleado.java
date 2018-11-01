package cliente;

import java.rmi.Naming;
import java.rmi.RemoteException;

import interfaces.CargaEmpleado;

public class ClienteCargaEmpleado {
    
	CargaEmpleado carga;
	
	public static void main(String[] args)
	{
		new ClienteCargaEmpleado();
	}
	
    public boolean getStub() {
    	
    	try {
			carga = (CargaEmpleado)Naming.lookup ("//192.168.157.152/CargaEmpleado");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }
    
    public ClienteCargaEmpleado(){
    	if(getStub()) 
    	{
    		try {
				carga.cargaEmpleado("2035359183", "5555", "Facundo", "Prueba", "mail", "35359185", "test", "Mensual", 80);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    			
    	} 
    }

}
