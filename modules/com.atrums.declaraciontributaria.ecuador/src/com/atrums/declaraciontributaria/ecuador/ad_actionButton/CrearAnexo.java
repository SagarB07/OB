package com.atrums.declaraciontributaria.ecuador.ad_actionButton;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.ad_actionButton.ActionButtonDefaultData;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.xmlEngine.XmlDocument;

import com.atrums.declaraciontributaria.ecuador.util.UtilProcesoProcedure;

/**
 * Proceso que genera los anexos del SRI segun se ha registrado en la parametrizacion.
 * 
 * @author Danie Laguasi
 * 
 */
public class CrearAnexo extends HttpSecureAppServlet {

  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strProcessId = vars.getStringParameter("inpProcessId");
      String strWindow = vars.getStringParameter("inpwindowId");
      String strKey = vars.getStringParameter("inpatsProcesaAnexoId");
      String strMessage = "";
      printPage(response, vars, strKey, strWindow, strProcessId, strMessage, true);
    } else if (vars.commandIn("GENERATE")) {
      String strKey = vars.getStringParameter("inpcoProcesaAnexoId");
      getPrintPage(request, response, vars, strKey);
    }
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars, String strKey,
      String windowId, String strProcessId, String strMessage, boolean isDefault)
      throws IOException, ServletException {
    if (log4j.isDebugEnabled())
      log4j.debug("Output: Button create file msg:" + strMessage);

    ActionButtonDefaultData[] data = null;
    String strHelp = "", strDescription = "";

    if (vars.getLanguage().equals("en_US"))
      data = ActionButtonDefaultData.select(this, strProcessId);
    else
      data = ActionButtonDefaultData.selectLanguage(this, vars.getLanguage(), strProcessId);

    if (data != null && data.length != 0) {
      strDescription = data[0].description;
      strHelp = data[0].help;
    }
    String[] discard = { "" };
    if (strHelp.equals(""))
      discard[0] = new String("helpDiscard");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/declaraciontributaria/ecuador/ad_actionButton/CrearAnexo", discard)
        .createXmlDocument();
    xmlDocument.setParameter("key", strKey);
    xmlDocument.setParameter("window", windowId);
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\r\n");
    xmlDocument.setParameter("theme", vars.getTheme());
    xmlDocument.setParameter("description", strDescription);
    xmlDocument.setParameter("help", strHelp);

    if (isDefault) {
      xmlDocument.setParameter("messageType", "");
      xmlDocument.setParameter("messageTitle", "");
      xmlDocument.setParameter("messageMessage", "");
    } else {
      OBError myMessage = new OBError();
      myMessage.setTitle("");
      if (log4j.isDebugEnabled())
        log4j.debug("CrearAnexo - before setMessage");
      if (strMessage == null || strMessage.equals(""))
        myMessage.setType("Success");
      else
        myMessage.setType("Error");
      if (strMessage != null && !strMessage.equals("")) {
        myMessage.setMessage(strMessage);
      } else
        Utility.translateError(this, vars, vars.getLanguage(), "Success");
      if (log4j.isDebugEnabled())
        log4j.debug("CrearAnexo - Message Type: " + myMessage.getType());
      vars.setMessage("CrearAnexo", myMessage);
      if (log4j.isDebugEnabled())
        log4j.debug("CrearAnexo - after setMessage");
      if (myMessage != null) {
        xmlDocument.setParameter("messageType", myMessage.getType());
        xmlDocument.setParameter("messageTitle", myMessage.getTitle());
        xmlDocument.setParameter("messageMessage", myMessage.getMessage());
      }
    }

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

  private void getPrintPage(HttpServletRequest request, HttpServletResponse response,
      VariablesSecureApp vars, String strKey) throws IOException, ServletException {
    if (log4j.isDebugEnabled())
      log4j.debug("generate " + strKey);

    StringBuffer StrXML = new StringBuffer();
    String strMessage = "";
    String strNombreFile = "";
    String strNombreXml = "";
    String strProcedimiento = "";
    String strFechaInicio = "";
    String strFechaFin = "";
    String strAdOrgId = "";
    String strResult = "";

    try {
      CrearAnexoData[] cad = CrearAnexoData.select(this, strKey);

      strNombreFile = cad[0].nombreArchivo;
      strNombreXml = cad[0].nombreXml;
      strFechaInicio = cad[0].startdate;
      strFechaFin = cad[0].enddate;
      strAdOrgId = cad[0].adOrgId;

      StrXML.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n");
      StrXML.append("<" + strNombreXml + ">\n"); // Cabecera del archivo

      for (int i = 0; i < cad.length; i++) {
        strProcedimiento = cad[i].procedimiento;
        if (strProcedimiento == null || strProcedimiento.equals(""))
          advisePopUp(request, response, "Error",
              "No se a ingresado un Stored Procedure para realizar el Anexo");

        if (cad[i].tipoInformacion.contentEquals("CB"))// Cabecera
        {
          StrXML.append(UtilProcesoProcedure.ejecutaProcedure(this, strProcedimiento, strAdOrgId
              + "','" + strFechaInicio, "strXml")
              + "\n");
        }
        if (cad[i].tipoInformacion.contentEquals("FC"))// F. Compras
        {

          BuscaFacturaData[] bfd = BuscaFacturaData.selectInvoice(this, strFechaInicio,
              strFechaFin, vars.getClient(), "N");
          StrXML.append("<compras>\n");
          for (int j = 0; j < bfd.length; j++) {

            strResult = UtilProcesoProcedure.ejecutaProcedure(this, strProcedimiento,
                bfd[j].cInvoiceId, "strXml");

            StrXML.append(strResult == null ? "" : "\t" + strResult + "\n");
          }
          StrXML.append("</compras>\n");
        }

        if (cad[i].tipoInformacion.contentEquals("FV"))// F. Ventas
        {

          BuscaFacturaData[] bfd = BuscaFacturaData.selectVentas(this, strFechaInicio, strFechaFin,
              vars.getClient(), "Y");
          StrXML.append("<ventas>\n");
          for (int j = 0; j < bfd.length; j++) {

            StrXML.append("\t"
                + UtilProcesoProcedure.ejecutaProcedure(this, strProcedimiento, bfd[j].tpidcliente,
                    bfd[j].idcliente, bfd[j].tipocomprobante, bfd[j].numerocomprobantes,
                    bfd[j].basenograiva, bfd[j].baseimponible, bfd[j].baseimpgrav, bfd[j].montoiva,
                    bfd[j].valorretiva, bfd[j].valorretrenta, "strXml") + "\n");

          }
          StrXML.append("</ventas>\n");

        }

        if (cad[i].tipoInformacion.contentEquals("ATS_VE"))// Ventas Establecimiento
        {
          StrXML.append("<ventasEstablecimiento>\n");

          ArrayList<String> est = UtilProcesoProcedure.ejecutaProcedure(this, strProcedimiento,
              vars.getClient(), strFechaInicio, strFechaFin, "strXml");

          for (int m = 0; m < est.size(); m++) {
            StrXML.append(est.isEmpty() ? "" : "\t" + est.get(m) + "\n");
          }

          StrXML.append("</ventasEstablecimiento>\n");
        }

        if (cad[i].tipoInformacion.contentEquals("AN"))// Doc. Anulados
        {

          ArrayList<String> anul = UtilProcesoProcedure.ejecutaProcedure(this, strProcedimiento,
              strFechaInicio, strFechaFin, vars.getClient(), "strXml");

          if (anul.isEmpty())
            StrXML.append("");
          else {
            StrXML.append("<anulados>\n");
            	for (int w = 0; w < anul.size(); w++) {
            	StrXML.append("\t" + anul.get(w) + "\n");
          	}
            StrXML.append("</anulados>\n");
          }
        }

      }
      StrXML.append("</" + strNombreXml + ">");

    } catch (Exception e) {
      strMessage = e.getMessage();
    }

    if (!strMessage.equals("")) {
      printPage(response, vars, strKey, "", "", strMessage, false);
    } else {
      response.setContentType("application/xml");
      response.setHeader("Content-Disposition", "attachment; filename=" + strNombreFile + ".xml");
      PrintWriter out = response.getWriter();
      out.println(StrXML.toString());
      out.close();
    }
  }

  public String getServletInfo() {
    return "Servlet for the generation of files for ATS";
  } // end of getServletInfo() method
}
