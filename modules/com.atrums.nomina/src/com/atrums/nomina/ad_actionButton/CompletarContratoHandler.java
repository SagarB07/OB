package com.atrums.nomina.ad_actionButton;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.service.db.DbUtility;

import com.atrums.nomina.ad_process.ContratoProceso;
import com.atrums.nomina.test.TestConnectionDB;

public class CompletarContratoHandler extends BaseActionHandler {
	static Logger log4j = Logger.getLogger(CompletarContratoHandler.class);
	
	

	@Override 
	protected JSONObject execute(Map<String, Object> parameters, String content) {
		// TODO Auto-generated method stub
		ContratoProceso contratoProceso = new ContratoProceso();
		
		  JSONObject result = new JSONObject();
		    try {
			JSONObject message = new JSONObject();
			TestConnectionDB conn= new TestConnectionDB();
		      final JSONObject jsonData = new JSONObject(content);
		      final JSONArray contratosIds = jsonData.getJSONArray("lstContratos");
				if (contratosIds.length() > 0) {
					for (int i = 0; i < contratosIds.length(); i++) {
						final String rolId = contratosIds.getString(i);
						contratoProceso.actualizarContrato(conn.conn, rolId);
					}
				}
				String mensaje = "El proceso se ejecutó correctamente";
				message.put("severity", "success");
				message.put("text", mensaje);
				result.put("message", message);
		    
		    }catch(Exception e){
		    	
		    	  e.printStackTrace();
			      OBDal.getInstance().rollbackAndClose();
			      log4j.error("BaseInvoiceProcess error: " + e.getMessage(), e);
			      Throwable ex = DbUtility.getUnderlyingSQLException(e);
			      String message = OBMessageUtils.translateError(ex.getMessage()).getMessage();

			      try {
			    	  result.put("retryExecution", true);
			        JSONObject errorMessage = new JSONObject();
			        errorMessage.put("severity", "error");
			        errorMessage.put("text", message);
			        result.put("message", errorMessage);
			        e.printStackTrace();
			      } catch (JSONException e1) {
			        // TODO Auto-generated catch block
			        e1.printStackTrace();
			      }
		    }
		return result;
	}


	
}
