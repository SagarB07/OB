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

public class CO_Valida_AutSRI extends HttpSecureAppServlet {
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

      String stremCoNroAutSri = vars.getStringParameter("inpemCoNroAutSri");
      String stremCoNroEstab = vars.getStringParameter("inpemCoNroEstab");
      String stremCoPuntoEmision = vars.getStringParameter("inpemCoPuntoEmision");
      try {

        if (!stremCoNroAutSri.equals("") || !stremCoNroAutSri.equals(null)
            || !stremCoNroEstab.equals("") || !stremCoNroEstab.equals(null)
            || !stremCoPuntoEmision.equals("") || !stremCoPuntoEmision.equals(null)) {
          printPage(response, vars, stremCoNroAutSri, stremCoNroEstab, stremCoPuntoEmision);
        }

      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }// Fin doPost

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String stremCoNroAutSri, String stremCoNroEstab, String stremCoPuntoEmision)
      throws IOException, ServletException {
    if (log4j.isDebugEnabled())
      log4j.debug("Output: dataSheet");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/contabilidad/ad_callouts/CallOut").createXmlDocument();

    // use a buffer variable to construct the generated code
    String strConvRateErrorMsg = "";
    StringBuffer result = new StringBuffer();
    result.append("var calloutName='CO_Valida_AutSRI';\n\n");
    result.append("var respuesta = new Array(");

    if (!esEntero(stremCoNroAutSri)) {

      strConvRateErrorMsg = "El campo N° Autorización SRI debe ser sólo caracteres numéricos";

      result.append("new Array(\"ERROR\", \"" + strConvRateErrorMsg + "\"),");

      result.append("new Array(\"inpemCoNroAutSri\", \"" + null + "\")");

    }

    if (!esEntero(stremCoNroEstab)) {

      strConvRateErrorMsg = "El campo N° Establecimiento debe ser sólo caracteres numéricos";
      // use a buffer variable to construct the generated code

      result.append("new Array(\"ERROR\", \"" + strConvRateErrorMsg + "\"),");

      result.append("new Array(\"inpemCoNroEstab\", \"" + null + "\")");

    }// fin if

    if (!esEntero(stremCoPuntoEmision)) {

      strConvRateErrorMsg = "El campo Punto Emisión debe ser sólo caracteres numéricos";

      result.append("new Array(\"ERROR\", \"" + strConvRateErrorMsg + "\"),");

      result.append("new Array(\"inpemCoPuntoEmision\", \"" + null + "\")");

    }// fin if

    result.append(");");
    xmlDocument.setParameter("array", result.toString());
    xmlDocument.setParameter("frameName", "appFrame");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }// Fin printPage

  public boolean esEntero(String cad) {
    for (int i = 0; i < cad.length(); i++) {
      if (!Character.isDigit(cad.charAt(i))) {
        return false;
      }
    }
    return true;
  }// clase validar si es número
}// Fin principal
