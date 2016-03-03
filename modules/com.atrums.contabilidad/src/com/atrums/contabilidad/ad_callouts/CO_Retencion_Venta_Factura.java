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

public class CO_Retencion_Venta_Factura extends HttpSecureAppServlet
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
	    String strBpRetencionVentaId = vars
		    .getStringParameter("inpcoBpRetencionVentaId");

	    String strBaseImp = vars.getStringParameter("inpbaseImpRetencion")
		    .replace(",", "");

	    try
	    {
		printPage(response, vars, strBpRetencionVentaId, strBaseImp);
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
	    VariablesSecureApp vars, String strBpRetencionVentaId,
	    String strBaseImp) throws IOException, ServletException
    {
	if (log4j.isDebugEnabled())
	    log4j.debug("Output: dataSheet");

	XmlDocument xmlDocument = xmlEngine
		.readXmlTemplate("com/atrums/contabilidad/ad_callouts/CallOut")
		.createXmlDocument();

	String strValor = "0";
	if (strBpRetencionVentaId != "")
	    strValor = CORetencionVentaFacturaData
		    .select(this, strBaseImp, strBpRetencionVentaId);

	StringBuffer resultado = new StringBuffer();
	resultado.append("var calloutName='CO_Retencion_Venta_Factura';\n\n");
	resultado.append("var respuesta = new Array(");
	resultado.append("new Array(\"inpvalorRetencion\", \"" + strValor
		+ "\")");
	resultado.append(");");
	xmlDocument.setParameter("array", resultado.toString());
	xmlDocument.setParameter("frameName", "frameAplicacion");
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println(xmlDocument.print());
	out.close();
    }
}
