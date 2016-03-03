package com.atrums.declaraciontributaria.ecuador.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.xmlEngine.XmlDocument;

public class ATSCalculoIva extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    if (vars.commandIn("DEFAULT")) {
      String strChanged = vars.getStringParameter("inpLastFieldChanged");
      if (log4j.isDebugEnabled())
        log4j.debug("CHANGED: " + strChanged);

      String strBase12 = vars.getStringParameter("inpbase12");

      try {
        /*
         * if ("inpAtindpoUnitPrice".equals(strChanged) || "inpAtindpoDiscount".equals(strChanged)
         * || "inpCantidad".equals(strChanged)) { // Indica que objeto requiere el callout
         * printPage(response, vars, strUnitPrice, strCantidad, strDiscount); }
         */
        printPage(response, vars, strBase12);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }// Fin doPost

  private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strBase12)
      throws IOException, ServletException {
    if (log4j.isDebugEnabled())
      log4j.debug("Output: dataSheet");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/declaraciontributaria/ecuador/ad_callouts/CallOut").createXmlDocument();

    float floValorIva = 0;
    float floBase12 = Float.valueOf(strBase12);

    floValorIva = (float) (floBase12 * 0.12);

    // use a buffer variable to construct the generated code
    StringBuffer result = new StringBuffer();
    result.append("var calloutName='ATSCalculoIva';\n\n");
    result.append("var respuesta = new Array(");

    result.append("new Array(\"inpvalorIva\", \"" + floValorIva + "\")");

    result.append(");");

    xmlDocument.setParameter("array", result.toString());
    xmlDocument.setParameter("frameName", "appFrame");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }// Fin printPage
}// Fin principal
