package com.atrums.nomina.ad_reports;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.openbravo.base.secureApp.VariablesSecureApp;

import com.atrums.print.doctype.utility.reporting.DocumentType;
import com.atrums.print.doctype.utility.reporting.printing.PrintController;

@SuppressWarnings("serial")
public class PrintRolPago extends PrintController{

	@SuppressWarnings("unused")
	private static Logger log4j = Logger.getLogger(PrintRolPago.class);

	  // TODO: Als een email in draft staat de velden voor de email adressen
	  // weghalen en melden dat het document
	  // niet ge-emailed kan worden

	  public void init(ServletConfig config) {
	    super.init(config);
	    boolHist = false;
	  }

	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	      ServletException {
	    VariablesSecureApp vars = new VariablesSecureApp(request);

	    DocumentType documentType = DocumentType.ROLPAGO;
	    // The prefix PRINTINVOICES is a fixed name based on the KEY of the
	    // AD_PROCESS
	    String sessionValuePrefix = "PRINTROLPAGO";
	    String strDocumentId = null;

	    strDocumentId = vars.getSessionValue(sessionValuePrefix + ".inpnoRolPagoProvisionId_R");
	    if (strDocumentId.equals(""))
	      strDocumentId = vars.getSessionValue(sessionValuePrefix + ".inpnoRolPagoProvisionId");

	    post(request, response, vars, documentType, sessionValuePrefix, strDocumentId);
	  }

	  public String getServletInfo() {
	    return "Servlet that processes the print action";
	  } // End of getServletInfo() method
	
	
}
