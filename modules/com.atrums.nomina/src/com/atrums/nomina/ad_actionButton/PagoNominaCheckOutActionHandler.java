package com.atrums.nomina.ad_actionButton;

import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;

import com.atrums.nomina.data.noPagoLine;
import com.atrums.nomina.util.UtilProcesoProcedure;

public class PagoNominaCheckOutActionHandler extends BaseActionHandler {
  // one DAY in milliseconds

  private ConnectionProvider conn;

  @Override
  protected JSONObject execute(Map<String, Object> parameters, String content) {

    JSONObject result = new JSONObject();
    try {

      final JSONObject jsonData = new JSONObject(content);
      final JSONArray pagoIds = jsonData.getJSONArray("pagos");

      if (pagoIds.length() > 0) {

        for (int i = 0; i < pagoIds.length(); i++) {
          final String rolId = pagoIds.getString(i);
          final noPagoLine linea = OBDal.getInstance().get(noPagoLine.class, rolId);

          if (linea.getEstado().equals("BR")) {

            UtilProcesoProcedure.ejecutaProcedure(OBDal.getInstance().getConnection(),
                "no_procesa_pago_lineas", "'" + linea.getId() + "','"
                    + OBContext.getOBContext().getUser().getId() + "'", "no_procesa_pago_lineas");
            linea.setEstado("PA");

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
