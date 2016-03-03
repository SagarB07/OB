package com.atrums.nomina.ad_actionButton;

import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;

import com.atrums.nomina.data.noRolPagoProvision;

public class RolPagoCheckOutActionHandler extends BaseActionHandler {
  // one DAY in milliseconds

  @Override
  protected JSONObject execute(Map<String, Object> parameters, String content) {

    JSONObject result = new JSONObject();
    try {

      final JSONObject jsonData = new JSONObject(content);
      final JSONArray rolesIds = jsonData.getJSONArray("roles");

      if (rolesIds.length() > 0) {

        for (int i = 0; i < rolesIds.length(); i++) {
          final String rolId = rolesIds.getString(i);
          final noRolPagoProvision rol = OBDal.getInstance().get(noRolPagoProvision.class, rolId);

          if (rol.getDocstatus().equals("BR")) {

            rol.setDocstatus("CO");
            rol.setProcesar(true);
            rol.setDocaccionno("RE");

          }

        }

        result.put("success", true);
      } else {
        result.put("success", false);
        result.put(
            "message",
            Utility.messageBD(new DalConnectionProvider(), "NO_NoRolesSelected", OBContext
                .getOBContext().getLanguage().getLanguage()));

      }

    } catch (Exception e) {

    }
    return result;

  }
}
