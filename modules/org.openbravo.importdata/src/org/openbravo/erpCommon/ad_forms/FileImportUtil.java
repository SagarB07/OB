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
