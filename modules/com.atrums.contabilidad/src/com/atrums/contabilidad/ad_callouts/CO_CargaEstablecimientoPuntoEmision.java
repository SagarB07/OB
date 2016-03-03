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

public class CO_CargaEstablecimientoPuntoEmision extends HttpSecureAppServlet
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
	    String strOrgId = vars.getStringParameter("inpadOrgId");
	    String strIsSoTrx = vars.getStringParameter("inpissotrx");

	    try
	    {
		printPage(response, vars, strOrgId, strIsSoTrx);
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
	    VariablesSecureApp vars, String strOrgId, String strIsSoTrx)
	    throws IOException, ServletException
    {
	log4j.debug("Output: dataSheet");

	XmlDocument xmlDocument = xmlEngine
		.readXmlTemplate("com/atrums/contabilidad/ad_callouts/CallOut")
		.createXmlDocument();

	StringBuffer result = new StringBuffer();

	result.append("var calloutName='CO_CargaEstablecimientoPuntoEmision';\n\n");
	result.append("var respuesta = new Array(");
	if (strIsSoTrx.equals("Y")) // solo para ventas
	{
	    CONoEstablecimientoEmisionData[] data = CONoEstablecimientoEmisionData
		    .select(this, strOrgId);

	    result.append("new Array(\"inpemCoNroEstab\", \""
		    + data[0].getField("establecimiento") + "\"),");
	    result.append("new Array(\"inpemCoPuntoEmision\", \""
		    + data[0].getField("emision") + "\")");
	}
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
