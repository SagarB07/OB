package com.atrums.nomina.test;
import org.openbravo.test.base.BaseTest;

import com.atrums.nomina.ad_process.CapaIntermedia;
//import org.openbravo.test.base.OBBaseTest;
public class TestNomina extends BaseTest{
	

	//@Test
	public void testEmvioMails() throws Exception {
	//Thread hilo = new NOCBPartner(getConnectionProvider(), NOCBPartner.obtenerMailTerceros(getConnectionProvider(), "Y"));
	//hilo.start();
		getConnectionProvider();
		CapaIntermedia capa = new CapaIntermedia();
		capa.enviarMails("AF6F632FA8804B5289AA4DB2107ECD0E");
		
//	HiloEmail hilo2 = new HiloEmail(NOCBPartner.obtenerMailTerceros(getConnectionProvider(), "Y"),getConnectionProvider());
//	System.out.println("Estado del hilo2 después de la creación");
//	hilo2.start();
//	System.out.println("Estado del hilo2 después de la ejecución");
		

//	NOCBPartner nocbPartner = new NOCBPartner(getConnectionProvider(), NOCBPartner.obtenerMailTerceros(getConnectionProvider(), "Y"));
////	nocbPartner.start();
//	System.out.println("debefuncionar");
//	nocbPartner.testEnvios();
//	System.out.println("AAAAAAAAA");
	//nocbPartner.start();
			  //NOCBPartner.obtenerMailTerceros(getConnectionProvider(), "Y");
			
	 }
}
