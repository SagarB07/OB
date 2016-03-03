package com.atrums.contabilidad.ad_callouts;

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

public class CO_Valor_Retencion_Linea_Compra extends HttpSecureAppServlet
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
	    String strcRetencionCompraId = vars
		    .getStringParameter("inpcoRetencionCompraId");
	    String strbaseImpRetencion = vars
		    .getStringParameter("inpbaseImpRetencion");
	    String strcBpRetencionCompraId = vars
		    .getStringParameter("inpcoBpRetencionCompraId");

	    try
	    {
		if (strcRetencionCompraId != null)
		    printPage(response, vars, strcBpRetencionCompraId,
			      strbaseImpRetencion);
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
	    VariablesSecureApp vars, String strcBpRetencionCompraId,
	    String strbaseImpRetencion) throws IOException, ServletException
    {
	log4j.debug("Output: dataSheet");

	XmlDocument xmlDocument = xmlEngine
		.readXmlTemplate("com/atrums/contabilidad/ad_callouts/CallOut")
		.createXmlDocument();

	COValorRetencionLineaCompraData[] data = COValorRetencionLineaCompraData
		.select(this, strcBpRetencionCompraId);

	StringBuffer result = new StringBuffer();
	String strbaseImpRetencionNum = strbaseImpRetencion.replace(",", "");
	Double base = Double.parseDouble(strbaseImpRetencionNum);

	BigDecimal totalLinea = BigDecimal.ZERO;
	BigDecimal divisor = BigDecimal.valueOf(100);
	BigDecimal porcentaje = new BigDecimal(data[0].getField("porcentaje"));

	BigDecimal baseImpRetencion = BigDecimal.valueOf(base);

	totalLinea = (porcentaje.divide(divisor)).multiply(baseImpRetencion);

	result.append("var calloutName='CO_Valor_Retencion_Linea_Compra';\n\n");

	result.append("var respuesta = new Array(");
	result.append("new Array(\"inpvalorRetencion\", \"" + totalLinea
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
