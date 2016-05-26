package com.atrums.nomina.ad_process;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.service.db.DbUtility;

public class RolEnvioMailHandler extends BaseActionHandler {
	static Logger log4j = Logger.getLogger(RolEnvioMailHandler.class);

	@Override
	protected JSONObject execute(Map<String, Object> parameters, String content) {
		// TODO Auto-generated method stub
		
		JSONObject result = new JSONObject();
		
		
		try {
			JSONObject message = new JSONObject();
			String mensaje = "El sistema se encuentra enviando los roles seleccionados, este proceso no interrumpira otras tareas que se puedan hacer en este momento";
			message.put("severity", "success");
			message.put("text", mensaje);
			result.put("message", message);
			CapaIntermedia capaIntermedia = new CapaIntermedia();
			final JSONObject jsonData = new JSONObject(content);
			final JSONArray rolesIds = jsonData.getJSONArray("rolpago");

			if (rolesIds.length() > 0) {

				for (int i = 0; i < rolesIds.length(); i++) {
					final String rolId = rolesIds.getString(i);

					capaIntermedia.enviarMails(rolId);

				}
			}
			  mensaje = "Se envió " + rolesIds.length() + " correos";
		      message.put("severity", "success");
		      message.put("text", mensaje);
		      result.put("message", message);
			
		} catch (Exception e) {
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
