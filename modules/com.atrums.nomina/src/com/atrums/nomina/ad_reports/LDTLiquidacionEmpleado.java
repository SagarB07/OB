package com.atrums.nomina.ad_reports;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;

public class LDTLiquidacionEmpleado extends HttpSecureAppServlet {

  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strProcessId = vars.getStringParameter("inpProcessId");
      String strWindow = vars.getStringParameter("inpwindowId");
      String strKey = vars.getStringParameter("inpldtHblId");
      String strLdtFh = vars.getStringParameter("inplftFh");
      String strMessage = "";

      printPage(response, vars, strKey, strWindow, strProcessId, strLdtFh, strMessage, true);
    }
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strldtHblid,
      String windowId, String strProcessId, String strLdtFh, String strMessage, boolean isDefault)
      throws IOException, ServletException {

    String strMainReportName = "";
    HashMap<String, Object> parameters = new HashMap<String, Object>();

    if (log4j.isDebugEnabled())
      log4j.debug("Output: Reconciliation PDF report");

    try {
    //  if (strLdtFh.trim().equals("Y")) {
        strMainReportName = "@basedesign@/com/atrums/nomina/ad_reports/Rpt_No_liquidacion";
     /// } else {
        //strMainReportName = "@basedesign@/com/atrums/logisticadetransporte/parametrizacion/erpReports/Rpt_LDT_Aviso_Arribo.jrxml";
      //}
      OBContext.setAdminMode(true);

      // Parameters
      parameters.put("DOCUMENT_ID", strldtHblid);

      OBContext.setAdminMode(true);

      response.setContentType("text/html; charset=UTF-8");
      renderJR(vars, response, strMainReportName, "pdf", parameters, null, null);

    } catch (Exception e) {
      throw new ServletException(e.getMessage());
    }
  }
}