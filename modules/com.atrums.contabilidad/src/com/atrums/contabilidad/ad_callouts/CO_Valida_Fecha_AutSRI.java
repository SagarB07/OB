package com.atrums.contabilidad.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.xmlEngine.XmlDocument;

public class CO_Valida_Fecha_AutSRI extends HttpSecureAppServlet {

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
      String stremCoVencimientoAutSri = vars.getStringParameter("inpemCoVencimientoAutSri");
      String strdateinvoiced = vars.getStringParameter("inpdateinvoiced");
      String strdateacct = vars.getStringParameter("inpdateacct");

      try {
        if (!((stremCoVencimientoAutSri.equals("") || stremCoVencimientoAutSri == "") && (strdateinvoiced == "" || strdateinvoiced
            .equals("")))) {
          printPage(response, vars, stremCoVencimientoAutSri, strdateinvoiced);
        }
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String stremCoVencimientoAutSri, String strdateinvoiced) throws IOException,
      ServletException, ParseException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/contabilidad/ad_callouts/CallOut").createXmlDocument();

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    // Date dtInvoiced1 = sdf.parse(strdateinvoiced);
    StringBuffer resultado = new StringBuffer();
    resultado.append("var calloutName='CO_Valida_Fecha_AutSRI';\n\n");
    resultado.append("var respuesta = new Array(");

    resultado.append("new Array(\"inpdateacct\", \"" + strdateinvoiced + "\")");
    // resultado.append("new Array(\"CURSOR_FIELD\", \"inpdateacct\")\n");

    if (stremCoVencimientoAutSri.equals("")) {

      resultado.append(");");
      xmlDocument.setParameter("array", resultado.toString());
      xmlDocument.setParameter("frameName", "appFrame");
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println(xmlDocument.print());
      out.close();

    } else {

      Date dtVtoSRI = sdf.parse(stremCoVencimientoAutSri);
      Date dtInvoiced = sdf.parse(strdateinvoiced);

      if (dtVtoSRI.before(dtInvoiced)) {

        resultado
            .append(", new Array('ERROR', \""
                + "La fecha de la factura no puede ser mayor a la fecha de Vencimiento de la Autorización del SRI"
                + "\") ");

      }

      resultado.append(");");
      xmlDocument.setParameter("array", resultado.toString());
      xmlDocument.setParameter("frameName", "appFrame");
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println(xmlDocument.print());
      out.close();
    }
  }
}
