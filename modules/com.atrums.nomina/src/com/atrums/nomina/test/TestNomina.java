package com.atrums.nomina.test;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Test;
import org.openbravo.erpCommon.ad_forms.FileImport;
import org.openbravo.erpCommon.ad_forms.FileImportUtil;
import org.openbravo.exception.PoolNotFoundException;

import com.atrums.nomina.ad_process.CapaIntermedia;
import com.atrums.nomina.ad_process.ContratoProceso;
public class TestNomina {
	
	@Test
	public void testActualizacionContrato () throws PoolNotFoundException, ServletException{
		Integer filas= 0;
		TestConnectionDB conn= new TestConnectionDB();
		ContratoProceso contratoProceso = new ContratoProceso();
		List<String> idContrato = new ArrayList<String>();
		idContrato.add("71026F9E9CD74733B1FFA624EE9D5C04");
		idContrato.add("69BF81B784DF46BEBD112B57B852CB31");
		idContrato.add("B74048E354A24D8D910A013F4ED9208B");
		idContrato.add("7E59FEAAEF7C482189FC794387E4E257");
		for (String iterador :idContrato){
			filas = contratoProceso.actualizarContrato(conn.conn, iterador);
		}
		filas = contratoProceso.actualizarContrato(conn.conn, "71026F9E9CD74733B1FFA624EE9D5C04");
		System.out.print("Se han actualizado: " +idContrato.size() +" filas");
		
		
	}
	
	@Test 
	public void testObtenerGterceros () throws PoolNotFoundException, ServletException{
		 TestConnectionDB conn= new TestConnectionDB();
		 FileImport fileImport = new FileImport();
		 String campo = "'EMPLEADOS ADMINISTRATIVOS'";
		 if (campo.indexOf("'")>=0){
			  campo = campo.substring(campo.indexOf("'")+1, campo.length());
			  campo = campo.substring(0,campo.length()-1 );
			  }
		 String valor = fileImport.obtenerGrupoTercero(conn.conn, campo);
		 System.out.println(valor);
	}
	
	
	@Test
	public void testObtenerperfil () throws PoolNotFoundException, ServletException{
		String campo = "'JEFE DE OPERACIONES MENSUALIZADO'";
		 if (campo.indexOf("'")>=0){
			  campo = campo.substring(campo.indexOf("'")+1, campo.length());
			  campo = campo.substring(0,campo.length()-1 );
			  }
		 TestConnectionDB conn= new TestConnectionDB();
		 FileImport fileImport = new FileImport();
		 Connection c= null;
		 String valor = fileImport.obtenerPerfil(c, conn.conn, campo);
		 System.out.println(valor);
		 
	}
	
	@Test
	public void testValidacionCedula (){
		String numero = "1721737243001";
		String mensaje = FileImportUtil.obtenerTipoIdentificador(numero);
		mensaje = FileImportUtil.validarCedula(numero, mensaje);
		System.out.println(mensaje);
	}
	
	@Test
	public void  testManejoCadena (){
		String sTexto= "EM_Idt_Nombres = 'EDWIN SANTIAGO',EM_Idt_Apellidos = 'YACELGA MALDONADO',Name2 = 'YM',Name = 'YM',EM_Idt_Natural_Juridico = 'PN',EM_Idt_Tipo_Identificacion = '03',TaxID = '1'";
		FileImport  fileImport = new FileImport();
		String valor =  fileImport.obtenerUltimoCampo  (sTexto);
		valor = fileImport.obtenerNombrePersona(sTexto);		
		System.out.println(valor);
		System.out.println("Si paso significa que funcionó");
	}
	
	
	@Test
	public void testEmvioMails() throws Exception {
		CapaIntermedia capa = new CapaIntermedia();
		capa.setIdRolPago("6094096A51B64765AD71EF9940D47BC7");
		//capa.start();
		System.out.println("Si paso significa que funcionó");
	 }
}
