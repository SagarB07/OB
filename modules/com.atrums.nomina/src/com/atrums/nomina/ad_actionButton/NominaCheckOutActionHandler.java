package com.atrums.nomina.ad_actionButton;

import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;

import com.atrums.nomina.data.noLiquidacionEmpleado;

public class NominaCheckOutActionHandler extends BaseActionHandler {
  // one DAY in milliseconds

  @Override
  protected JSONObject execute(Map<String, Object> parameters, String content) {
    // try {

    // get the data as json
    JSONObject result = new JSONObject();
    try {
      final JSONObject jsonData = new JSONObject(content);
      final JSONArray liquidacionIds = jsonData.getJSONArray("liquidaciones");

      if (liquidacionIds.length() > 0) {

        for (int i = 0; i < liquidacionIds.length(); i++) {
          final String liquidId = liquidacionIds.getString(i);
          final noLiquidacionEmpleado liquidacion = OBDal.getInstance().get(
              noLiquidacionEmpleado.class, liquidId);

          if (liquidacion.getDocstatus().equals("BR")) {

            liquidacion.setDocstatus("CO");
            liquidacion.setProcessed(true);
            liquidacion.setDocaccionno("RE");

          }

        }
        result.put("success", true);
      } else {
        result.put("success", false);
        result.put(
            "message",
            Utility.messageBD(new DalConnectionProvider(), "NO_NoLiquidSelected", OBContext
                .getOBContext().getLanguage().getLanguage()));
      }

    } catch (JSONException e) {
      // TODO Auto-generated catch block

      e.printStackTrace();
    }
    return result;

  }
}
