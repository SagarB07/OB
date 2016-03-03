package com.atrums.remica.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.xmlEngine.XmlDocument;

public class RE_Porcentaje_Ganancia extends HttpSecureAppServlet {

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
      String strmDiscountschemalineId = vars.getStringParameter("inpmDiscountschemalineId");
      String stremReGasto = vars.getStringParameter("inpemReGasto");
      String stremReGanancia = vars.getStringParameter("inpemReGanancia");

      try {
        if (strmDiscountschemalineId != null)
          printPage(response, vars, strmDiscountschemalineId, stremReGasto, stremReGanancia);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String strmDiscountschemalineId, String stremReGasto, String stremReGanancia)
      throws IOException, ServletException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("com/atrums/remica/ad_callouts/CallOut")
        .createXmlDocument();

    // REPorcentajeGananciaData[] data = REPorcentajeGananciaData.select(this,
    // strmDiscountschemalineId);

    StringBuffer result = new StringBuffer();
    String strgastoNum = stremReGasto.replace(",", "");
    String strgananciaNum = stremReGanancia.replace(",", "");
    Double gasto = Double.parseDouble(strgastoNum);
    Double ganancia = Double.parseDouble(strgananciaNum);

    BigDecimal totalporcentaje = BigDecimal.ZERO;
    BigDecimal negativo = new BigDecimal(-1);

    BigDecimal gastoBig = BigDecimal.valueOf(gasto);
    BigDecimal gananciaBig = BigDecimal.valueOf(ganancia);
    totalporcentaje = (gastoBig.add(gananciaBig)).multiply(negativo);

    result.append("var calloutName='RE_Porcentaje_Ganancia';\n\n");

    result.append("var respuesta = new Array(");
    result.append("new Array(\"inplistDiscount\", \"" + totalporcentaje + "\"),");
    result.append("new Array(\"inplimitDiscount\", \"" + totalporcentaje + "\"),");
    result.append("new Array(\"inpstdDiscount\", \"" + totalporcentaje + "\")");
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
