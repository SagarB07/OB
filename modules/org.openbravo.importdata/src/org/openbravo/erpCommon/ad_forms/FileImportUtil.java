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
