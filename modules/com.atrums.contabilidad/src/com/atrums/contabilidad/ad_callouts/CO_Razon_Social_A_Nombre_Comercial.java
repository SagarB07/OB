package com.atrums.contabilidad.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.xmlEngine.XmlDocument;

public class CO_Razon_Social_A_Nombre_Comercial extends HttpSecureAppServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strCBPartnerId = vars.getStringParameter("inpcBpartnerId");
      String strvName2 = vars.getStringParameter("inpname2");

      try {
        printPage(response, vars, strCBPartnerId, strvName2);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String strCBpGroupId, String strvName2) throws IOException, ServletException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/contabilidad/ad_callouts/CallOut").createXmlDocument();

    StringBuffer result = new StringBuffer();
    result.append("var calloutName='CO_Razon_Social_A_Nombre_Comercial';\n\n");

    result.append("var respuesta = new Array(");

    if (strvName2 == null) {
      strvName2 = "";
      result.append("new Array(\"inpname\",\"" + strvName2.trim() + "\")");
      result.append(");");
    } else {
      result.append("new Array(\"inpname\", \"" + strvName2.trim() + "\")");
      result.append(");");
    }

    // inject the generated code xmlDocument.setParameter("array",
    // result.toString());
    xmlDocument.setParameter("array", result.toString());
    xmlDocument.setParameter("frameName", "appFrame");

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();

  }

}
