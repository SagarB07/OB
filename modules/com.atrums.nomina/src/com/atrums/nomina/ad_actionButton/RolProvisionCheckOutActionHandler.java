package com.atrums.nomina.ad_actionButton;

import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;

import com.atrums.nomina.data.noRolPagoProvision;
import com.atrums.nomina.util.UtilProcesoProcedure;

public class RolProvisionCheckOutActionHandler extends BaseActionHandler {
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
          final noRolPagoProvision rolPagpProv = OBDal.getInstance().get(noRolPagoProvision.class,
              rolId);

          if (rolPagpProv.getDocstatus().equals("CO")) {

            UtilProcesoProcedure.ejecutaProcedure(OBDal.getInstance().getConnection(),
                "no_genera_valor_rprovision", "'" + rolPagpProv.getBusinessPartner().getId()
                    + "','" + rolPagpProv.getPeriod().getId() + "','"
                    + OBContext.getOBContext().getUser().getOrganization().getId() + "','"
                    + OBContext.getOBContext().getUser().getClient().getId() + "','"
                    + OBContext.getOBContext().getUser().getId() + "','"
                    + rolPagpProv.getDateacct().toString() + "'", "no_genera_valor_rprovision");

          }

        }

        result.put("success", true);
      } else {
        result.put("success", false);

      }

    } catch (Exception e) {

    }
    return result;

  }
}
