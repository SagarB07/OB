package com.atrums.nomina.rdep.erpCommon.ad_actionButton;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_actionButton.ActionButtonDefaultData;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.xmlEngine.XmlDocument;

import com.atrums.nomina.rdep.data.atrdepCabeceraReten;
import com.atrums.nomina.rdep.util.UtilProcesoProcedure;

/**
 * Proceso que genera los anexos del SRI segun se ha registrado en la parametrizacion.
 * 
 * @author Danie Laguasi
 * 
 */
public class CrearRDEP extends HttpSecureAppServlet {

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
      String strKey = vars.getStringParameter("inpatrdepCabeceraRetenId");
      String strMessage = "";
      printPage(response, vars, strKey, strWindow, strProcessId, strMessage, true);
    } else if (vars.commandIn("GENERATE")) {
      String strKey = vars.getStringParameter("inpatrdepCabeceraRetenId");
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
        "com/atrums/nomina/rdep/erpCommon/ad_actionButton/CrearRDEP", discard).createXmlDocument();
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
        log4j.debug("CrearRDEP - before setMessage");
      if (strMessage == null || strMessage.equals(""))
        myMessage.setType("Success");
      else
        myMessage.setType("Error");
      if (strMessage != null && !strMessage.equals("")) {
        myMessage.setMessage(strMessage);
      } else
        Utility.translateError(this, vars, vars.getLanguage(), "Success");
      if (log4j.isDebugEnabled())
        log4j.debug("CrearRDEP - Message Type: " + myMessage.getType());
      vars.setMessage("CrearRDEP", myMessage);
      if (log4j.isDebugEnabled())
        log4j.debug("CrearRDEP - after setMessage");
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
      VariablesSecureApp vars, String atrdepCabId) throws IOException, ServletException {
    if (log4j.isDebugEnabled())
      log4j.debug("generate " + atrdepCabId);

    StringBuffer StrXML = new StringBuffer();
    String strMessage = "";
    String strNombreFile = "";
    String strNombreXml = "";
    String strProcedimiento = "";
    String strResult = "";

    try {
      atrdepCabeceraReten atrdepCab = OBDal.getInstance().get(atrdepCabeceraReten.class,
          atrdepCabId);
      strNombreFile = atrdepCab.getNombrerdep();
      StrXML = UtilProcesoProcedure.ejecutaProcedure(this, "atrdep_listar_datos", atrdepCabId);

    } catch (Exception e) {
      strMessage = e.getMessage();
    }

    if (!strMessage.equals("")) {
      printPage(response, vars, atrdepCabId, "", "", strMessage, false);
    } else {
      response.setContentType("application/xml");
      response.setHeader("Content-Disposition", "attachment; filename=" + strNombreFile + ".xml");
      PrintWriter out = response.getWriter();
      out.println(StrXML.toString());
      out.close();
    }
  }

  public String getServletInfo() {
    return "Servlet for the generation of files for RDEP";
  } // end of getServletInfo() method
}
