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

public class co_datos_sri_retencion extends HttpSecureAppServlet
{
    /**
     * 
     */
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
	    String strInvoiceId = vars.getStringParameter("inpcInvoiceId");

	    try
	    {
		printPage(response, vars, strInvoiceId);
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
	    VariablesSecureApp vars, String strInvoiceId) throws IOException,
	    ServletException
    {
	log4j.debug("Output: dataSheet");

	XmlDocument xmlDocument = xmlEngine
		.readXmlTemplate("com/atrums/contabilidad/ad_callouts/CallOut")
		.createXmlDocument();

	StringBuffer result = new StringBuffer();

	result.append("var calloutName='co_datos_sri_retencion';\n\n");
	result.append("var respuesta = new Array(");
	COInformacionSRIData[] data = COInformacionSRIData
		.selectIv(this, strInvoiceId);

	result.append("new Array(\"inpcoNoEstablecimiento\", \""
		+ data[0].estab + "\"),");
	result.append("new Array(\"inpcoPtoEmision\", \"" + data[0].emision
		+ "\"),");
	result.append("new Array(\"inpnoAutorizacion\", \""
		+ data[0].noautoriza + "\"),");
	result.append("new Array(\"inpfechaEmision\", \"" + data[0].fecautoriza
		+ "\")");
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
