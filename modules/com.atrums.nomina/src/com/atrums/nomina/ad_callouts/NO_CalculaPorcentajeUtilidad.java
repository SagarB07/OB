package com.atrums.nomina.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.xmlEngine.XmlDocument;

public class NO_CalculaPorcentajeUtilidad extends HttpSecureAppServlet {

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

      String strUtilidad = vars.getStringParameter("inputilidadTotal");
      String strPorcentaje = vars.getNumericParameter("inpporcentaje");

      try {
        printPage(response, vars, strUtilidad, strPorcentaje);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strUtilidad,
      String strPorcentaje) throws IOException, ServletException, ParseException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/atrums/nomina/ad_callouts/CallOut")
        .createXmlDocument();

    BigDecimal bdUtilidadEmpleado = BigDecimal.ZERO;

    bdUtilidadEmpleado = new BigDecimal(strUtilidad.replace(",", "")).multiply(
        new BigDecimal(strPorcentaje).divide(new BigDecimal("100"))).setScale(2,
        RoundingMode.HALF_UP);

    StringBuffer resultado = new StringBuffer();
    resultado.append("var calloutName='NO_CalculaPorcentajeUtilidad';\n\n");
    resultado.append("var respuesta = new Array(");
    resultado.append("new Array(\"inputilidadEmpleados\", \"" + bdUtilidadEmpleado.toString()
        + "\") ");
    resultado.append(");");

    xmlDocument.setParameter("array", resultado.toString());
    xmlDocument.setParameter("frameName", "appFrame");

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();

  }

}