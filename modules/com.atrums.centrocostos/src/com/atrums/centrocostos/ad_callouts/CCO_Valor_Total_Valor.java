package com.atrums.centrocostos.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.xmlEngine.XmlDocument;

public class CCO_Valor_Total_Valor extends HttpSecureAppServlet {

  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      // Obtencion de los valores
      String strcInvoiceId = vars.getStringParameter("inpcInvoiceId");
      String stremPorcentaje = vars.getStringParameter("inpporcentaje");
      String stremValorTotal = vars.getNumericParameter("inpvalor");
      String strfinPaymentId = vars.getStringParameter("inpfinPaymentId");
      String strnoContratoEmpleadoId = vars.getStringParameter("inpnoContratoEmpleadoId");

      try {
    	  //Factura Proveedor
        if (!vars.getStringParameter("inpcInvoiceId").isEmpty())
          printPage(response, vars, strcInvoiceId, stremPorcentaje, stremValorTotal);
      //Pago
        if (!vars.getStringParameter("inpfinPaymentId").isEmpty())
            printPage1(response, vars, strfinPaymentId, stremPorcentaje, stremValorTotal);
        //Contrato empleado
        if (!vars.getStringParameter("inpnoContratoEmpleadoId").isEmpty())
            printPage2(response, vars, strnoContratoEmpleadoId, stremPorcentaje, stremValorTotal);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String strcInvoiceId, String stremPorcentaje, String stremValorTotal) throws IOException,
      ServletException, ParseException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/centrocostos/ad_callouts/CallOut").createXmlDocument();

    // Obtener el valor total de la factura a partir de la consulta SQL
    String totalFactura = CCOValorTotalPorcentajeData.select(this, strcInvoiceId);

    Double result = 0.0;
    Double result2 = 0.0;

    double porcentaje = 0.00;

    porcentaje = Double.valueOf(stremPorcentaje).doubleValue();

    StringBuffer resultado = new StringBuffer();
    resultado.append("var calloutName='CCO_Valor_Total_Porcentaje';\n\n");
    resultado.append("var respuesta = new Array(");

    result = (Double.parseDouble(stremValorTotal) * 100) / Double.parseDouble(totalFactura);
    resultado.append("new Array(\"inpporcentaje\", \"" + result + "\")");

    resultado.append(");");
    xmlDocument.setParameter("array", resultado.toString());
    xmlDocument.setParameter("frameName", "appFrame");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();

  }
  
  //Calculo ventana pago proveedor
  private void printPage1(HttpServletResponse response, VariablesSecureApp vars,
	      String strfinPaymentId, String stremPorcentaje, String stremValorTotal) throws IOException,
	      ServletException, ParseException {
	    log4j.debug("Output: dataSheet");

	    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
	        "com/atrums/centrocostos/ad_callouts/CallOut").createXmlDocument();

	    // Obtener el valor total de la factura a partir de la consulta SQL
	    String totalPago = CCOValorTotalPorcentajeData.selectpayment(this, strfinPaymentId);

	    Double result = 0.0;
	   

	    double porcentaje = 0.00;

	    porcentaje = Double.valueOf(stremPorcentaje).doubleValue();

	    StringBuffer resultado = new StringBuffer();
	    resultado.append("var calloutName='CCO_Valor_Total_Porcentaje';\n\n");
	    resultado.append("var respuesta = new Array(");

	    result = (Double.parseDouble(stremValorTotal) * 100) / Double.parseDouble(totalPago);
	    resultado.append("new Array(\"inpporcentaje\", \"" + result + "\")");

	    resultado.append(");");
	    xmlDocument.setParameter("array", resultado.toString());
	    xmlDocument.setParameter("frameName", "appFrame");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.println(xmlDocument.print());
	    out.close();

	  }

  
  //Calculo ventana contrato empleado
  private void printPage2(HttpServletResponse response, VariablesSecureApp vars,
	      String strnoContratoEmpleadoId, String stremPorcentaje, String stremValorTotal) throws IOException,
	      ServletException, ParseException {
	    log4j.debug("Output: dataSheet");

	    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
	        "com/atrums/centrocostos/ad_callouts/CallOut").createXmlDocument();

	    // Obtener el valor total de la factura a partir de la consulta SQL
	    String totalsalario = CCOValorTotalPorcentajeData.selectcontrato(this, strnoContratoEmpleadoId);

	    Double result = 0.0;
	   

	    double porcentaje = 0.00;

	    porcentaje = Double.valueOf(stremPorcentaje).doubleValue();

	    StringBuffer resultado = new StringBuffer();
	    resultado.append("var calloutName='CCO_Valor_Total_Porcentaje';\n\n");
	    resultado.append("var respuesta = new Array(");

	    result = (Double.parseDouble(stremValorTotal) * 100) / Double.parseDouble(totalsalario);
	    resultado.append("new Array(\"inpporcentaje\", \"" + result + "\")");

	    resultado.append(");");
	    xmlDocument.setParameter("array", resultado.toString());
	    xmlDocument.setParameter("frameName", "appFrame");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.println(xmlDocument.print());
	    out.close();

	  }
}
