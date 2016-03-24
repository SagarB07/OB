package org.openbravo.erpCommon.ad_forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.openbravo.data.UtilSql;
import org.openbravo.database.ConnectionProvider;

public class FileImportUtil {
	static Logger log4j = Logger.getLogger(FileImportUtil.class);
	
	
	public static String validarDocumento(String numero, String tipoIdentificacion) {
	    boolean valor = true;
	    String mensaje = "VALIDO";
	    
	    if (tipoIdentificacion.equals("01")||tipoIdentificacion.equals("02")||tipoIdentificacion.equals("03")||tipoIdentificacion.equals("07") ){
	    	
	    if (tipoIdentificacion.equals("02") || tipoIdentificacion.equals("01")) {

	      try {
	        int suma = 0;
	        int residuo = 0;
	        boolean privada = false;
	        boolean publica = false;
	        boolean natural = false;
	        int numeroProvincias = 24;
	        int digitoVerificador = 0;
	        int modulo = 11;

	        int d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;
	        int p1, p2, p3, p4, p5, p6, p7, p8, p9;

	        d1 = d2 = d3 = d4 = d5 = d6 = d7 = d8 = d9 = d10 = 0;
	        p1 = p2 = p3 = p4 = p5 = p6 = p7 = p8 = p9 = 0;

	        if (numero.length() < 10) {
	          // mensaje = "  No es válido";
	          mensaje = " CI/RUC no válido";

	          valor = false;
	        }

	        // Los primeros dos digitos corresponden al codigo de la
	        // provincia
	        int provincia = Integer.parseInt(numero.substring(0, 2));

	        if (provincia <= 0 || provincia > numeroProvincias) {
	          mensaje = " CI/RUC no válido";
	          // JOptionPane
	          // .showMessageDialog(Motor.getVentana(), "El c" + Motor.o
	          // + "digo de la provincia (dos primeros d" + Motor.i +
	          // "gitos) es inv" + Motor.a
	          // + "lido");
	          valor = false;
	        }

	        // Aqui almacenamos los digitos de la cedula en variables.
	        d1 = Integer.parseInt(numero.substring(0, 1));
	        d2 = Integer.parseInt(numero.substring(1, 2));
	        d3 = Integer.parseInt(numero.substring(2, 3));
	        d4 = Integer.parseInt(numero.substring(3, 4));
	        d5 = Integer.parseInt(numero.substring(4, 5));
	        d6 = Integer.parseInt(numero.substring(5, 6));
	        d7 = Integer.parseInt(numero.substring(6, 7));
	        d8 = Integer.parseInt(numero.substring(7, 8));
	        d9 = Integer.parseInt(numero.substring(8, 9));
	        d10 = Integer.parseInt(numero.substring(9, 10));

	        // El tercer digito es:
	        // 9 para sociedades privadas y extranjeros
	        // 6 para sociedades publicas
	        // menor que 6 (0,1,2,3,4,5) para personas naturales
	        if (d3 == 7 || d3 == 8) {
	          mensaje = " CI/RUC no válido";
	          // JOptionPane.showMessageDialog(Motor.getVentana(),
	          // "El tercer d" + Motor.i
	          // + "gito ingresado es inv" + Motor.a + "lido");
	          valor = false;
	        }

	        // Solo para personas naturales (modulo 10)
	        if (d3 < 6) {
	          natural = true;
	          modulo = 10;
	          p1 = d1 * 2;
	          if (p1 >= 10)
	            p1 -= 9;
	          p2 = d2 * 1;
	          if (p2 >= 10)
	            p2 -= 9;
	          p3 = d3 * 2;
	          if (p3 >= 10)
	            p3 -= 9;
	          p4 = d4 * 1;
	          if (p4 >= 10)
	            p4 -= 9;
	          p5 = d5 * 2;
	          if (p5 >= 10)
	            p5 -= 9;
	          p6 = d6 * 1;
	          if (p6 >= 10)
	            p6 -= 9;
	          p7 = d7 * 2;
	          if (p7 >= 10)
	            p7 -= 9;
	          p8 = d8 * 1;
	          if (p8 >= 10)
	            p8 -= 9;
	          p9 = d9 * 2;
	          if (p9 >= 10)
	            p9 -= 9;
	        }

	        // Solo para sociedades publicas (modulo 11)
	        // Aqui el digito verficador esta en la posicion 9, en las otras
	        // 2
	        // en la pos. 10
	        if (d3 == 6) {
	          publica = true;
	          p1 = d1 * 3;
	          p2 = d2 * 2;
	          p3 = d3 * 7;
	          p4 = d4 * 6;
	          p5 = d5 * 5;
	          p6 = d6 * 4;
	          p7 = d7 * 3;
	          p8 = d8 * 2;
	          p9 = 0;
	        }

	        /* Solo para entidades privadas (modulo 11) */
	        if (d3 == 9) {
	          privada = true;
	          p1 = d1 * 4;
	          p2 = d2 * 3;
	          p3 = d3 * 2;
	          p4 = d4 * 7;
	          p5 = d5 * 6;
	          p6 = d6 * 5;
	          p7 = d7 * 4;
	          p8 = d8 * 3;
	          p9 = d9 * 2;
	        }

	        suma = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;
	        residuo = suma % modulo;

	        // Si residuo=0, dig.ver.=0, caso contrario 10 - residuo
	        digitoVerificador = residuo == 0 ? 0 : modulo - residuo;
	        int longitud = numero.length(); // Longitud del string

	        // ahora comparamos el elemento de la posicion 10 con el dig.
	        // ver.

	        if (publica == true) {
	          if (digitoVerificador != d9) {
	            mensaje = "  El RUC de la empresa del sector público es incorrecto";
	            valor = false;
	          }
	          if (!numero.substring(9, longitud).equals("0001")) {
	            mensaje = "  El RUC de la empresa del sector público debe terminar con 0001";
	            valor = false;
	          }
	          if (!tipoIdentificacion.equals("01")) {
	            mensaje = "Tipo identificacion incorrecto";
	            valor = false;
	          }
	        }

	        if (privada == true) {
	          if (digitoVerificador != d10) {
	            mensaje = "  El RUC de la empresa del sector privado es incorrecto";
	            valor = false;
	          }
	          if (!numero.substring(10, longitud).equals("001")) {
	            mensaje = "  El RUC de la empresa del sector privado debe terminar con 001";
	            valor = false;
	          }
	          if (!tipoIdentificacion.equals("01")) {
	            mensaje = "Tipo identificacion incorrecto";
	            valor = false;
	          }
	        }

	        if (natural == true) {
	          if (digitoVerificador != d10) {

	            mensaje = "  El número de cédula de la persona natural es incorrecto";
	            valor = false;
	          }
	          if (numero.length() > 10 && !numero.substring(10, longitud).equals("001")) {

	            mensaje = "  El ruc de la persona natural debe terminar con 001";
	            valor = false;
	          }

	          if (mensaje.equals("VALIDO")) {

	            if (numero.length() > 10 && tipoIdentificacion.equals("02")) {
	              mensaje = "Tipo identificacion incorrecto";
	              valor = false;
	            } else if (numero.length() == 10 && tipoIdentificacion.equals("01")) {
	              mensaje = "Tipo identificacion incorrecto";
	              valor = false;
	            }

	          }

	        }
	      } catch (Exception e) {
	        valor = false;
	        mensaje = " CI/RUC no válido";
	      }
	    }
	    return mensaje;
	    }else {
	    	
	    	return "TIPO DE IDENTIFICACIÓN INVALIDO";
	    }
	  }
	
	
	
	 public static int insertBPartner(Connection conn, ConnectionProvider connectionProvider, String CBPartnerID, String IBPartnerID)    throws ServletException {
		    String strSql = "";
		    strSql = strSql + 
		      "			  INSERT INTO C_BPartner (C_BPartner_ID, AD_Client_ID, AD_Org_ID," +
		      "          IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,Name2," +
		      "          Description,DUNS,TaxID,NAICS,C_BP_Group_ID,IsSummary,em_co_email, em_co_nombres, em_co_apellidos, em_co_tipo_identificacion,em_co_natural_juridico,url,"
		      + "iscustomer,so_creditlimit, isvendor, isemployee, issalesrep, em_no_isdiscapacitado, em_ne_num_car_discapacitado, em_no_fechanacimiento,em_no_genero,em_no_estadocivil, em_ne_perfil_rubro_id,em_no_pago_acct  )" +
		      "  				  SELECT ?, AD_Client_ID, AD_Org_ID," +
		      "            'Y',now(),CreatedBy,now(),UpdatedBy,Value,Name,Name2," +
		      "            Description,DUNS,TaxID,NAICS,C_BP_Group_ID,'N',em_idt_pbemail, em_idt_nombres, em_idt_apellidos, em_idt_tipo_identificacion, em_idt_natural_juridico,em_idt_url,"
		      + "em_idt_iscustomer ,em_idt_creditlimit, em_idt_isvendor,em_idt_isemployee, em_idt_issalesrep,em_idt_isdiscapacitado, em_idt_ndiscapacitado,em_idt_fechanacimiento, em_idt_genero, em_idt_estadocivil, em_idt_ne_perfil_rubro_id, em_idt_pago_acct " +
		      "			  	  FROM I_BPartner" +
		      "				    WHERE I_BPartner_ID=?";

		    int updateCount = 0;
		    PreparedStatement st = null;

		    int iParameter = 0;
		    try {
		    st = connectionProvider.getPreparedStatement(conn, strSql);
		      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPartnerID);
		      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);

		      updateCount = st.executeUpdate();
		    } catch(SQLException e){
		      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
		      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
		    } catch(Exception ex){
		      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
		      throw new ServletException("@CODE=@" + ex.getMessage());
		    } finally {
		      try {
		        connectionProvider.releaseTransactionalPreparedStatement(st);
		      } catch(Exception ignore){
		        ignore.printStackTrace();
		      }
		    }
		    return(updateCount);
		  }
	
	
	  public static int insertBPContact(Connection conn, ConnectionProvider connectionProvider, String ADUserID, String CBPartnerID, String CBPLocationID, String IBPartnerID)    throws ServletException {
		    String strSql = "";
		    String sqlAux = "select COALESCE (em_idt_firstname, em_idt_lastname ) as nombreUsuario from i_bpartner  where I_BPartner_ID= '"+IBPartnerID+"' "; 
		    String nombreUsuario = obtenerIDCampo(conn, connectionProvider, sqlAux, "nombreUsuario");
		    System.out.println(nombreUsuario);
		    strSql = strSql + 
		    		  "				INSERT INTO AD_User (" +
		    	      "          AD_User_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
		    	      "				  C_BPartner_ID,C_BPartner_Location_ID,C_Greeting_ID,Name, firstname, lastname ,Title,Description,Comments,Phone,Phone2,Fax,EMail,Birthday" +
		    	      "        )" +
		    	      "				SELECT ?,AD_Client_ID,AD_Org_ID,'Y',now(),CreatedBy,now(),UpdatedBy," +
		    	      "				?,?,C_Greeting_ID,?,em_idt_firstname, em_idt_lastname, Title,ContactDescription,Comments,Phone,Phone2,Fax,EMail,Birthday" +
		    	      "				FROM I_BPartner" +
		    	      "				WHERE I_BPartner_ID=?";

		    int updateCount = 0;
		    PreparedStatement st = null;

		    int iParameter = 0;
		    try {
		    st = connectionProvider.getPreparedStatement(conn, strSql);
		      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ADUserID);
		      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPartnerID);
		      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPLocationID);
		      iParameter++; UtilSql.setValue(st, iParameter, 12, null, nombreUsuario);
		      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);

		      updateCount = st.executeUpdate();
		    } catch(SQLException e){
		      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
		      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
		    } catch(Exception ex){
		      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
		      throw new ServletException("@CODE=@" + ex.getMessage());
		    } finally {
		      try {
		        connectionProvider.releaseTransactionalPreparedStatement(st);
		      } catch(Exception ignore){
		        ignore.printStackTrace();
		      }
		    }
		    return(updateCount);
		  }
	
	public static String obtenerIDCampo (Connection conn, ConnectionProvider connectionProvider, String strSql, String campo )    throws ServletException {
		String datoCampo= "";
		ResultSet result;
	    PreparedStatement st = null;
	    try {
	    st = connectionProvider.getPreparedStatement(strSql);
	    result = st.executeQuery();
	    campo= campo.replace(" ", "");
	    campo= campo.replace("=", "");
	    boolean continueResult = true;
	    while(continueResult && result.next()) {
	        datoCampo = UtilSql.getValue(result, campo);
	        if (datoCampo!= ""){
	        	continueResult= false;
	        }
	      }
	      result.close();
	    } catch(SQLException e){
	      log4j.error("SQL  error in query: " + strSql + "Exception:"+ e);
	      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
	    } catch(Exception ex){
	      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
	      throw new ServletException("@CODE=@" + ex.getMessage());
	    } finally {
	      try {
	        connectionProvider.releasePreparedStatement(st);
	      } catch(Exception ignore){
	        ignore.printStackTrace();
	      }
	    }

	    return datoCampo;

		
	}
	  
	public static String obtenerIDCampoRubro(Connection conn, ConnectionProvider connectionProvider,String name)    throws ServletException {
	    String datoCampo= "";
	  	String strSql = "";
	  	name = name.replace("'", "");
	    strSql = strSql + 
	  	      "select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where name like '%"+name+"%'";
	    ResultSet result;
	    PreparedStatement st = null;
	    
	    try {
	    st = connectionProvider.getPreparedStatement(strSql);
	    result = st.executeQuery();
        boolean continueResult = true;
        while(continueResult && result.next()) {
	        datoCampo = UtilSql.getValue(result, "no_tipo_ingreso_egreso_id");
	        if (datoCampo!= ""){
	        	continueResult= false;
	        }
	      }
	      result.close();
	    } catch(SQLException e){
	      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
	      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
	    } catch(Exception ex){
	      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
	      throw new ServletException("@CODE=@" + ex.getMessage());
	    } finally {
	      try {
	        connectionProvider.releasePreparedStatement(st);
	      } catch(Exception ignore){
	        ignore.printStackTrace();
	      }
	    }

	    return datoCampo;
	  }
	
	public static String obtenerIDPeriodo(Connection conn, ConnectionProvider connectionProvider,String name)    throws ServletException {
	    String datoCampo= "";
	  	String strSql = "";
	  	name = name.replace("'", "");
	    strSql = strSql + 
	  	      "select c_period_id from c_period where name like '%"+name+"%'";
	    ResultSet result;
	    PreparedStatement st = null;
	    
	    try {
	    st = connectionProvider.getPreparedStatement(strSql);
	    result = st.executeQuery();
        boolean continueResult = true;
	      while(continueResult && result.next()) {
	        	        
	        datoCampo = UtilSql.getValue(result, "c_period_id");
	        datoCampo = UtilSql.getValue(result, "c_period_id");
	        if (datoCampo!= ""){
	        	continueResult= false;
	        }
	      }
	      result.close();
	    } catch(SQLException e){
	      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
	      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
	    } catch(Exception ex){
	      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
	      throw new ServletException("@CODE=@" + ex.getMessage());
	    } finally {
	      try {
	        connectionProvider.releasePreparedStatement(st);
	      } catch(Exception ignore){
	        ignore.printStackTrace();
	      }
	    }
	    return datoCampo;
	  }  
	
	
}
