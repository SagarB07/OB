package com.atrums.contabilidad.ad_callouts;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;

public class co_actualiza_dtosri_retencion extends HttpSecureAppServlet
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
	    String strCoNroEstab = vars
		    .getStringParameter("inpcoNoEstablecimiento");
	    String strCoPuntoEmision = vars
		    .getStringParameter("inpcoPtoEmision");
	    String strCoNroAutSri = vars
		    .getStringParameter("inpnoAutorizacion");
	    String strCoVencimientoAutSri = vars
		    .getStringParameter("inpfechaEmision");
	    try
	    {
		COInformacionSRIData.updateInvoice(this, strCoNroEstab,
						   strCoPuntoEmision,
						   strCoNroAutSri,
						   strCoVencimientoAutSri,
						   strInvoiceId);
	    }
	    catch (ServletException ex)
	    {
		pageErrorCallOut(response);
	    }
	}
	else
	    pageError(response);
    }

}
