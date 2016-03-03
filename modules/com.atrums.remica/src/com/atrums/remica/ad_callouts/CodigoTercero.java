package com.atrums.remica.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.xmlEngine.XmlDocument;

public class CodigoTercero extends HttpSecureAppServlet {

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
      String strCBpGroupId = vars.getStringParameter("inpcBpGroupId");

      try {
        printPage(response, vars, strCBpGroupId);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strCBpGroupId)
      throws IOException, ServletException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/atrums/remica/ad_callouts/CallOut")
        .createXmlDocument();

    CodigoTerceroData[] data = CodigoTerceroData.select(this, strCBpGroupId);

    StringBuffer result = new StringBuffer();
    result.append("var calloutName='Codigo_Tercero';\n\n");

    result.append("var respuesta = new Array(");

    result.append("new Array(\"inpvalue\", \"" + data[0].getField("codigo") + "\")");

    result.append(");");

    xmlDocument.setParameter("array", result.toString());
    xmlDocument.setParameter("frameName", "appFrame");

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();

  }
}
