package com.atrums.nomina.ad_process;

import javax.servlet.ServletException;

import org.openbravo.database.ConnectionProvider;
import org.openbravo.database.ConnectionProviderImpl;
import org.openbravo.exception.PoolNotFoundException;

import com.atrums.nomina.util.UtilNomina;


public class CapaIntermedia {
	NOCBPartner partner = null;
	public CapaIntermedia ( ) throws PoolNotFoundException  {
		String strDirectorio = UtilNomina.class.getResource("/").getPath();
		Integer value = strDirectorio.indexOf("/src-core/build/classes/");
		
		if (value>0){
			strDirectorio = strDirectorio.substring(1, value+1);	
		}else{
			value = strDirectorio.indexOf("/build/classes/");
			strDirectorio = strDirectorio.substring(1, value+1);	
		}
		strDirectorio= strDirectorio +"config/";
		final ConnectionProvider conn = new ConnectionProviderImpl(strDirectorio + "/Openbravo.properties");
		partner = new NOCBPartner(conn);
	}
	
	@SuppressWarnings("static-access")
	public void enviarMails (String idRolPago){
		try {
			partner.enviarMail(partner.obtenerMailTerceros(idRolPago));
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	
	
}
