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

public class DatosCompletos_Producto extends HttpSecureAppServlet {

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
      String strmProductId = vars.getStringParameter("inpmProductId");

      try {
        if (strmProductId != null)
          printPage(response, vars, strmProductId);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strmProductId)
      throws IOException, ServletException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/atrums/remica/ad_callouts/CallOut")
        .createXmlDocument();

    StringBuffer result = new StringBuffer();

    String codigo = DatosCompletosProductoData.select(this, strmProductId);

    result.append("var calloutName='DatosCompletos_Producto';\n\n");

    result.append("var respuesta = new Array(");
    result.append("new Array(\"inpemReValueProducto\", \"" + codigo + "\")");
    result.append(");");
    // inject the generated code
    xmlDocument.setParameter("array", result.toString());

    xmlDocument.setParameter("frameName", "appFrame");

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

}
