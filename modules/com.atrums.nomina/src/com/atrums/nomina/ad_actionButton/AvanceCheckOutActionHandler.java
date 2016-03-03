package com.atrums.nomina.ad_actionButton;

import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;

import com.atrums.nomina.data.noRegistraQuincLine;

public class AvanceCheckOutActionHandler extends BaseActionHandler {
  // one DAY in milliseconds

  @Override
  protected JSONObject execute(Map<String, Object> parameters, String content) {
    JSONObject result = new JSONObject();
    try {

      // get the data as json
      final JSONObject jsonData = new JSONObject(content);
      final JSONArray avanceIds = jsonData.getJSONArray("avances");

      if (avanceIds.length() > 0) {
        // start with zero

        // check out all old stays of this guest
        for (int i = 0; i < avanceIds.length(); i++) {

          final String avanceId = avanceIds.getString(i);
          final noRegistraQuincLine avance = OBDal.getInstance().get(noRegistraQuincLine.class,
              avanceId);
          if (avance.getEstado().equals("BO")) {
            avance.setEstado("CO");
            avance.setProcesarAvance("RE");
            avance.setProcesado(true);

          }
          // check out the particular stay by setting its Date_Out and Final_Sum

          // retrieve correct room rate

          // set calculated final sum

          // add the calculated final_sum to the total`

        }
        result.put("success", true);

      } else {
        // tell the end user there are no stays that are checked in at the moment
        result.put("success", false);
        result.put(
            "message",
            Utility.messageBD(new DalConnectionProvider(), "NO_NoAvanceSelected", OBContext
                .getOBContext().getLanguage().getLanguage()));
      }

      // and return it
      return result;
    } catch (Exception e) {
      throw new OBException(e);
    }

  }
}
