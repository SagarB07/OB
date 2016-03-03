/*
 * ************************************************************************
 * The contents of this file are subject to the Openbravo Public License
 * Version 1.1 (the "License"), being the Mozilla Public License
 * Version 1.1 with a permitted attribution clause; you may not use this
 * file except in compliance with the License. You may obtain a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2001-2010 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s): ______________________________________.
 * ***********************************************************************
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
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.reference.ListData;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.utils.FormatUtilities;
import org.openbravo.xmlEngine.XmlDocument;

public class SL_CO_Invoice_DocType extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    if (vars.commandIn("DEFAULT")) {
      String strDocTypeTarget = vars.getStringParameter("inpcDoctypetargetId");
      String strTabId = vars.getStringParameter("inpTabId");
      String strCInvoiceId = vars.getStringParameter("inpcInvoiceId");
      String strWindowId = vars.getStringParameter("inpwindowId");
      String strIsSOTrx = Utility.getContext(this, vars, "isSOTrx", strWindowId);

      try {
        printPage(response, vars, strDocTypeTarget, strTabId, strCInvoiceId, strWindowId,
            strIsSOTrx);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String strDocTypeTarget, String strTabId, String strCInvoiceId, String strWindowId,
      String strIsSOTrx) throws IOException, ServletException {
    if (log4j.isDebugEnabled())
      log4j.debug("Output: dataSheet");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/contabilidad/ad_callouts/CallOut").createXmlDocument();

    COInOutDocTypeData[] data = COInOutDocTypeData.select(this, strDocTypeTarget);

    StringBuffer resultado = new StringBuffer();
    if (data == null || data.length == 0)
      resultado.append("var respuesta = null;");
    else {
      resultado.append("var calloutName='SL_CO_Invoice_DocType';\n\n");
      resultado.append("var respuesta = new Array(");

      // check if doc type target is different, in this case assing new
      // documentno otherwise matain the previous one
      String strDoctypetargetinvoice = COInOutDocTypeData.selectDoctypetargetinvoice(this,
          strCInvoiceId);

      if (strDoctypetargetinvoice == null || strDoctypetargetinvoice.equals("")
          || !strDoctypetargetinvoice.equals(strDocTypeTarget)) {
        String strDocumentNo = Utility.getDocumentNo(this, vars.getClient(), "C_Invoice", false);
        if (data[0].isdocnocontrolled.equals("Y"))
          strDocumentNo = data[0].currentnext;
        resultado.append("new Array(\"inpdocumentno\", \"<" + strDocumentNo + ">\"),");
      } else if (strDoctypetargetinvoice != null && !strDoctypetargetinvoice.equals("")
          && strDoctypetargetinvoice.equals(strDocTypeTarget))
        resultado.append("new Array(\"inpdocumentno\", \""
            + COInOutDocTypeData.selectActualinvoicedocumentno(this, strCInvoiceId) + "\"),");
      // ------

      resultado.append("new Array(\"inpdocbasetype\", \"" + data[0].docbasetype + "\")");
      String strPaymentRule = "";
      if (data[0].docbasetype.endsWith("C")) {
        strPaymentRule = "P";
        resultado.append(", new Array(\"inppaymentrule\", \"" + strPaymentRule + "\"),");
        String strNamePaymentRule = ListData.selectName(this, "195", "P");
        resultado.append("new Array(\"PaymentRule_BTN\", \"" + strNamePaymentRule + "\")");
      }

      String strFechaVenceSri = "";
      String strAutrorizacionSri = "";
      
      if (!CODatosSRITipoDocData.ExisteDatoSRI(this, strDocTypeTarget).equals("0")) {
        CODatosSRITipoDocData[] dataSri = CODatosSRITipoDocData.SelectDatoSRI(this,
            strDocTypeTarget);
        strFechaVenceSri = dataSri[0].coVencimientoAutSri;
        strAutrorizacionSri = dataSri[0].coNroAutSri;
        
      }

      resultado.append(", new Array(\"inpemCoNroAutSri\", \"" + strAutrorizacionSri + "\"),");
      resultado.append("new Array(\"inpemCoVencimientoAutSri\", \"" + strFechaVenceSri + "\")");
      // Fin validacion SRI

      // Inicio validacion Sustentos x documento
      if (strIsSOTrx.equals("N")) {
      FieldProvider[] st = null;
      try {
        ComboTableData comboTableData = new ComboTableData(vars, this, "LIST",
            "Sustento Tributario - 1", "621494B5280946F6A7C4EAB0AAC3AFD5",
            "DB2A723B33D64FEF86534A657B405E77", Utility.getContext(this, vars,
                "#AccessibleOrgTree", strWindowId), Utility.getContext(this, vars, "#User_Client",
                strWindowId), 0);
        Utility.fillSQLParameters(this, vars, null, comboTableData, strWindowId, "");
        st = comboTableData.select(false);
        comboTableData = null;
      } catch (Exception ex) {
        throw new ServletException(ex);
      }

      resultado.append(", new Array(\"inpemCoCodsustento\", ");

      if (st != null && st.length > 0) {
        resultado.append("new Array(");

        // si hay un solo dato se trae el primero
        resultado.append("new Array(\"" + st[0].getField("id") + "\", \""
            + FormatUtilities.replaceJS(st[0].getField("name")) + "\", \"" + "true" + "\")");
        if (st.length > 1) {
          // su hay mas datos se continua el array
          resultado.append(",\n");
        }
        for (int i = 1; i < st.length; i++) {
          resultado.append("new Array(\"" + st[i].getField("id") + "\", \""
              + FormatUtilities.replaceJS(st[i].getField("name")) + "\", \"" + "false" + "\")");
          if (i < st.length - 1)
            resultado.append(",\n");
        }

        resultado.append("\n))");
      } else {

        
          resultado.append("null),");
          resultado.append("new Array('ERROR', \""
              + "El documento no tiene configurado los Códigos de Sustentos Tributarios" + "\")");
        }

      }

      // Fin validacion sustentos x documento

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
