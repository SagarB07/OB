package com.atrums.contabilidad.ad_callouts;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;

public class co_actualiza_dtosri_factura extends HttpSecureAppServlet
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
	    String strBpartnerId = vars.getStringParameter("inpcBpartnerId");
	    String strCoNroEstab = vars.getStringParameter("inpemCoNroEstab");
	    String strCoPuntoEmision = vars
		    .getStringParameter("inpemCoPuntoEmision");
	    String strCoNroAutSri = vars.getStringParameter("inpemCoNroAutSri");
	    String strCoVencimientoAutSri = vars
		    .getStringParameter("inpemCoVencimientoAutSri");
	    String strIsSoTrx = vars.getStringParameter("inpissotrx");
	    try
	    {
		printPage(response, vars, strCoNroEstab, strCoPuntoEmision,
			  strCoNroAutSri, strCoVencimientoAutSri,
			  strBpartnerId, strIsSoTrx);

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
	    VariablesSecureApp vars, String strCoNroEstab,
	    String strCoPuntoEmision, String strCoNroAutSri,
	    String strCoVencimientoAutSri, String strBpartnerId,
	    String strIsSoTrx) throws IOException, ServletException
    {
	log4j.debug("Output: dataSheet");
	if (strIsSoTrx.equalsIgnoreCase("N"))
	    COInformacionSRIData.updateInvoice(this, strCoNroEstab,
					       strCoPuntoEmision,
					       strCoNroAutSri,
					       strCoVencimientoAutSri,
					       strBpartnerId);

    }
}
