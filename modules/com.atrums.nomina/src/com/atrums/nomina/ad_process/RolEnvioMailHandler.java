package com.atrums.nomina.ad_process;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.BaseActionHandler;

public class RolEnvioMailHandler extends BaseActionHandler {
	static Logger log4j = Logger.getLogger(RolEnvioMailHandler.class);

	@Override
	protected JSONObject execute(Map<String, Object> parameters, String content) {
		// TODO Auto-generated method stub
		
		JSONObject result = new JSONObject();
		try {
			CapaIntermedia capaIntermedia = new CapaIntermedia();
			final JSONObject jsonData = new JSONObject(content);
			final JSONArray rolesIds = jsonData.getJSONArray("rolpago");

			if (rolesIds.length() > 0) {

				for (int i = 0; i < rolesIds.length(); i++) {
					final String rolId = rolesIds.getString(i);

					capaIntermedia.enviarMails(rolId);

				}
			}
		} catch (Exception e) {

		}
		return result;

	}

}
