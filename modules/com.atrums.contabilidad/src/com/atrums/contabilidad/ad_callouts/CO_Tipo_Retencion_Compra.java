package com.atrums.contabilidad.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.utils.FormatUtilities;
import org.openbravo.xmlEngine.XmlDocument;

public class CO_Tipo_Retencion_Compra extends HttpSecureAppServlet 
{
  
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) 
  {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strcRetencionCompraId = vars.getStringParameter("inpcoRetencionCompraId");
      String strtipo = vars.getStringParameter("inptipo");
	  String strCInvoiceId = vars.getStringParameter("inpcInvoiceId");

      try {
        if (strcRetencionCompraId != null)
          printPage(response, vars, strCInvoiceId, strcRetencionCompraId, strtipo);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String strCInvoiceId, String strcRetencionCompraId, String strtipo) throws IOException, ServletException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/contabilidad/ad_callouts/CallOut").createXmlDocument();

    COTipoRetencionCompraData[] datos = COTipoRetencionCompraData.select(this,
        strcRetencionCompraId, strtipo);

	COBaseImponibleData[] data = COBaseImponibleData
		.select(this, strCInvoiceId);
		
    StringBuffer result = new StringBuffer();

    result.append("var calloutName='CO_Tipo_Retencion_Compra';\n\n");
    result.append("var respuesta = new Array(");

	if ( strtipo.equals("FUENTE")) {
		result.append("new Array(\"inpbaseImpRetencion\", \"" + data[0].getField("taxbaseamt") + "\"),");
	}
	else {
		result.append("new Array(\"inpbaseImpRetencion\", \"" + data[0].getField("taxamt") + "\"),");
	}
	
    result.append("new Array(\"inpcoBpRetencionCompraId\", ");
    if (datos != null && datos.length > 0) {
	
      result.append("new Array(");
	  
      for (int j = 0; j < datos.length; j++) {
	  
        result.append("new Array(\"" + datos[j].getField("co_bp_retencion_compra_id") + "\", \"" + datos[j].getField("descripcion") + "\", \"true\")");

        if (j < datos.length - 1)
          result.append(",\n");
      }
	  
      result.append("\n)");
    } else
      result.append("null");
    result.append("\n)");

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
