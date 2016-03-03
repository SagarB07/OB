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

public class CO_ValidaNroAutoSri extends HttpSecureAppServlet
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
	    String stremCoNroAutSri = vars
		    .getStringParameter("inpemCoNroAutSri");

	    try
	    {
		printPage(response, vars, stremCoNroAutSri);

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
	    VariablesSecureApp vars, String stremCoNroAutSri)
	    throws IOException, ServletException, ParseException
    {
	log4j.debug("Output: dataSheet");

	XmlDocument xmlDocument = xmlEngine
		.readXmlTemplate("com/atrums/contabilidad/ad_callouts/CallOut")
		.createXmlDocument();

	String numeroCeros = "0";
	String result = "";
	int tamano = stremCoNroAutSri.length();

	if (stremCoNroAutSri.length() < 10)
	{

	    for (int i = 0; i < (10 - tamano); i++)
	    {

		numeroCeros = numeroCeros.concat("0");
	    }

	    result = numeroCeros + "" + stremCoNroAutSri;

	}
	else
	{
	    result = stremCoNroAutSri.substring(tamano - 10);

	}

	StringBuffer resultado = new StringBuffer();
	resultado.append("var calloutName='CO_ValidaNroAutoSri';\n\n");
	resultado.append("var respuesta = new Array(");
	resultado.append("new Array(\"inpemCoNroAutSri\", \"" + result + "\")");
	resultado.append(");");
	xmlDocument.setParameter("array", resultado.toString());
	xmlDocument.setParameter("frameName", "appFrame");
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println(xmlDocument.print());
	out.close();

    }

}
