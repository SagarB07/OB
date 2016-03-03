package com.atrums.contabilidad.ad_callouts;

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

public class CO_Digitos_Control_Emision extends HttpSecureAppServlet
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
	    String strPtoEmision = vars
		    .getStringParameter("inpemCoPuntoEmision");

	    try
	    {
		printPage(response, vars, strPtoEmision);
	    }
	    catch (ServletException ex)
	    {
		pageErrorCallOut(response);
	    }
	    catch (ParseException e)
	    {
		e.printStackTrace();
	    }
	}
	else
	    pageError(response);
    }

    private void printPage(HttpServletResponse response,
	    VariablesSecureApp vars, String strPtoEmision) throws IOException,
	    ServletException, ParseException
    {
	log4j.debug("Output: dataSheet");

	XmlDocument xmlDocument = xmlEngine
		.readXmlTemplate("com/atrums/contabilidad/ad_callouts/CallOut")
		.createXmlDocument();

	StringBuffer result = new StringBuffer();
	result.append("var calloutName='CO_Digitos_Control_Emision';\n\n");

	result.append("var respuesta = new Array(");

	if (strPtoEmision != null)
	{
	    while(strPtoEmision.length() < 3)
		strPtoEmision = "0" + strPtoEmision;

	    result.append("new Array(\"inpemCoPuntoEmision\", \""
		    + strPtoEmision + "\")");

	}

	result.append(");");

	// inject the generated code xmlDocument.setParameter("array",
	// result.toString());
	xmlDocument.setParameter("array", result.toString());
	xmlDocument.setParameter("frameName", "appFrame");

	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println(xmlDocument.print());
	out.close();

    }

}
