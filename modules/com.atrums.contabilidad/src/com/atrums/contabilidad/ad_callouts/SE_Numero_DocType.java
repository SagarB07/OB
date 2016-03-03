/*
 * *************************************************************************
 * EL CONTENIDO DE ESTE ARCHIVO ES PROPIEDAD INTELECTUAL DE ATRUMS-IT Y ESTA
 * SUJETA A LA LICENCIA: MOZILLA PUBLIC LICENSE
 * *************************************************************************
 */
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

public class SE_Numero_DocType extends HttpSecureAppServlet
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
	    String strChanged = vars.getStringParameter("inpLastFieldChanged");
	    if (log4j.isDebugEnabled())
		log4j.debug("CHANGED: " + strChanged);
	    String strAdClient = vars.getStringParameter("inpadClientId");
	    String strDocType = vars.getStringParameter("inpemCoDoctypeId");

	    try
	    {
		printPage(response, vars, strDocType, strAdClient);
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
	    VariablesSecureApp vars, String strDocType, String strAdClient)
	    throws IOException, ServletException
    {
	if (log4j.isDebugEnabled())
	    log4j.debug("Output: dataSheet");
	XmlDocument xmlDocument = xmlEngine
		.readXmlTemplate("com/atrums/contabilidad/ad_callouts/CallOut")
		.createXmlDocument();
	String strDocumentno = NumeroDocTypeData.SequenceDoctype(this,
								 strDocType,
								 strAdClient);

	StringBuffer resultado = new StringBuffer();
	resultado.append("var calloutName='SE_Numero_DocType';\n\n");
	if (!strDocumentno.equals("") && strDocumentno.length() > 0)
	{
	    resultado.append("var respuesta = new Array(");
	    resultado.append("new Array(\"inpdocumentno\", \"" + strDocumentno
		    + "\")");
	    resultado.append("\n);");
	}
	else
	{
	    resultado.append("var respuesta = new Array(");
	    resultado.append("new Array(\"inpdocumentno\", \"\")");
	    resultado.append(");");
	}

	xmlDocument.setParameter("array", resultado.toString());

	xmlDocument.setParameter("frameName", "appFrame");
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println(xmlDocument.print());
	out.close();
    }
}
