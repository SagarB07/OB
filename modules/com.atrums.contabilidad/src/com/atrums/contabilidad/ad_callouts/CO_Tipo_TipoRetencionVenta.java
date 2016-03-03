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

public class CO_Tipo_TipoRetencionVenta extends HttpSecureAppServlet
{

    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config)
    {
	super.init(config);
	boolHist = false;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException
    {
	VariablesSecureApp vars = new VariablesSecureApp(request);
	if (vars.commandIn("DEFAULT"))
	{
	    String strTipo = vars.getStringParameter("inptipo");
	    String strRetencionVentaId = vars
		    .getStringParameter("inpcoRetencionVentaId");
		String strCInvoiceId = vars.getStringParameter("inpcInvoiceId");
	    try
	    {
		if (strRetencionVentaId != null)
		printPage(response, vars, strCInvoiceId, strTipo, strRetencionVentaId);
	    }
	    catch (ServletException ex)
	    {
		pageErrorCallOut(response);
	    }
	}
	else
	    pageError(response);
    }

    private void printPage(HttpServletResponse response,
	    VariablesSecureApp vars, String strCInvoiceId, String strTipo, String strRetencionVentaId)
	    throws IOException, ServletException
    {
	if (log4j.isDebugEnabled())
	    log4j.debug("Output: dataSheet");

	XmlDocument xmlDocument = xmlEngine
		.readXmlTemplate("com/atrums/contabilidad/ad_callouts/CallOut")
		.createXmlDocument();

	COTipoRetencionVentaData[] datos = COTipoRetencionVentaData
		.select(this, strTipo, strRetencionVentaId);

	COBaseImponibleData[] data = COBaseImponibleData
		.select(this, strCInvoiceId);
		
	StringBuffer resultado = new StringBuffer();
	resultado.append("var calloutName='CO_Tipo_TipoRetencionVenta';\n\n");
	resultado.append("var respuesta = new Array(");

	if ( strTipo.equals("FUENTE")) {
			resultado.append("new Array(\"inpbaseImpRetencion\", \"" + data[0].getField("taxbaseamt") + "\"),");
		}
		else {
			resultado.append("new Array(\"inpbaseImpRetencion\", \"" + data[0].getField("taxamt") + "\"),");
		}
	
	resultado.append("new Array(\"inpcoBpRetencionVentaId\", ");

	if (datos != null && datos.length > 0)
	{
	    resultado.append("new Array(");
	    //resultado.append("new Array(\"\", \"\"),");
	    for (int i = 0; i < datos.length; i++)
	    {
		resultado.append("new Array(\""	+ datos[i].getField("co_bp_retencion_venta_id") + "\", \"" + datos[i].getField("descripcion") + "\", \"true\")");
		if (i < datos.length - 1)
		    resultado.append(",\n");
	    }
	    resultado.append("\n)");
	}
	else
	    resultado.append("null");

	resultado.append("\n)");
	resultado.append(");");

	// inject the generated code
	xmlDocument.setParameter("array", resultado.toString());
	xmlDocument.setParameter("frameName", "appFrame");
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println(xmlDocument.print());
	out.close();

    }
}
