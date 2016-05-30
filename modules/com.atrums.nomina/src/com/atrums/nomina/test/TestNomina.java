package com.atrums.nomina.test;
import org.junit.Test;

import com.atrums.nomina.ad_process.CapaIntermedia;
public class TestNomina {
	

	@Test
	public void testEmvioMails() throws Exception {
		CapaIntermedia capa = new CapaIntermedia();
		capa.setIdRolPago("6094096A51B64765AD71EF9940D47BC7");
		capa.start();
		System.out.println("Ojala Funiones");
	 }
}
