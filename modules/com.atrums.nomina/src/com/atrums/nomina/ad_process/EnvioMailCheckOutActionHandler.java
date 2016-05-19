package com.atrums.nomina.ad_process;

import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.BaseActionHandler;

public class EnvioMailCheckOutActionHandler extends BaseActionHandler {

	@Override
	protected JSONObject execute(Map<String, Object> parameters, String content) {
		// TODO Auto-generated method stub
		 JSONObject result = new JSONObject();
		    try {

		      final JSONObject jsonData = new JSONObject(content);
		      final JSONArray rolesIds = jsonData.getJSONArray("roles");

		      if (rolesIds.length() > 0) {

//		        for (int i = 0; i < rolesIds.length(); i++) {
//		          final String rolId = rolesIds.getString(i);
//		          final noRolPagoProvision rolPagpProv = OBDal.getInstance().get(noRolPagoProvision.class,
//		              rolId);
//		        }
		      }
		    } catch (Exception e) {

		    }
		    return result;
		      
	}

}
