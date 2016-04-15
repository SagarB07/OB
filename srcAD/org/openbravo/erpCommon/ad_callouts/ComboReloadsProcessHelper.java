
package org.openbravo.erpCommon.ad_callouts;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.xmlEngine.XmlDocument;


public class ComboReloadsProcessHelper extends CalloutHelper {
  private static final long serialVersionUID = 1L;

  void printPage(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
   String strProcessId = vars.getStringParameter("inpadProcessId");
   
     if (strProcessId.equals("135")) {
       process135(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800163")) {
       process800163(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("207")) {
       process207(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("221")) {
       process221(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("0DECE2AFEFAB49B1B25D8909A8A3B356")) {
       process0DECE2AFEFAB49B1B25D8909A8A3B356(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("175")) {
       process175(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800136")) {
       process800136(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("140")) {
       process140(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("206")) {
       process206(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("A44D94CE20024981922580D9053FF944")) {
       processA44D94CE20024981922580D9053FF944(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("224")) {
       process224(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("D234AE084F7040DCB66E281A4237FF99")) {
       processD234AE084F7040DCB66E281A4237FF99(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800075")) {
       process800075(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("155")) {
       process155(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("5A2A0AF88AF54BB085DCC52FCC9B17B7")) {
       process5A2A0AF88AF54BB085DCC52FCC9B17B7(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("1004400000")) {
       process1004400000(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("3B73C762A8B74750A3AC5BD1F1A041A8")) {
       process3B73C762A8B74750A3AC5BD1F1A041A8(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("220")) {
       process220(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("108")) {
       process108(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("017312F51139438A9665775E3B5392A1")) {
       process017312F51139438A9665775E3B5392A1(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("D7C6D8A0875D4EB8B453D717525FA9AE")) {
       processD7C6D8A0875D4EB8B453D717525FA9AE(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("176")) {
       process176(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("0BDC2164ED3E48539FCEF4D306F29EFD")) {
       process0BDC2164ED3E48539FCEF4D306F29EFD(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("EE9ACE0D59E147A89A400680EFDBED65")) {
       processEE9ACE0D59E147A89A400680EFDBED65(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("535686AFC88E475383664770714ABE0E")) {
       process535686AFC88E475383664770714ABE0E(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("0586F175A22647FAB964D16B85971CA7")) {
       process0586F175A22647FAB964D16B85971CA7(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("FF8080812E2F8EAE012E2F94CF470014")) {
       processFF8080812E2F8EAE012E2F94CF470014(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("23D1B163EC0B41F790CE39BF01DA320E")) {
       process23D1B163EC0B41F790CE39BF01DA320E(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800131")) {
       process800131(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800172")) {
       process800172(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("225")) {
       process225(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("48433D8674BA4CD5B8A856693D37FB3F")) {
       process48433D8674BA4CD5B8A856693D37FB3F(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("391DE834C5634644A16593B0286EFB64")) {
       process391DE834C5634644A16593B0286EFB64(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("112")) {
       process112(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("D2999B81B4E84547B24948C424867738")) {
       processD2999B81B4E84547B24948C424867738(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("4E8BB4D0A9304152BBF0D79DDD89FAC3")) {
       process4E8BB4D0A9304152BBF0D79DDD89FAC3(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("6E4252763C694A5BADCEB67B1F53D0E0")) {
       process6E4252763C694A5BADCEB67B1F53D0E0(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("154")) {
       process154(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("F9AE3E9FC50A4669AF6D2C7129AACAC3")) {
       processF9AE3E9FC50A4669AF6D2C7129AACAC3(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("A164779BCCA640BC9D5B201574C8B8D2")) {
       processA164779BCCA640BC9D5B201574C8B8D2(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("3CA9715730B240F0A9BEBE9101F93FF6")) {
       process3CA9715730B240F0A9BEBE9101F93FF6(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("6255BE488882480599C81284B70CD9B3")) {
       process6255BE488882480599C81284B70CD9B3(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("E264309FF8244A94936502BF51829109")) {
       processE264309FF8244A94936502BF51829109(response, vars, strTabId, windowId);
       return;
     }
    
    
    pageError(response);
  }
  
  
    private void process135(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads135';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadImpformatId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_ImpFormat_ID", "", "1001100000", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadImpformatId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800163(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800163';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_ID", "", "71188F0005494DA08311B4FFB2C5A993", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmWarehouseId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process207(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads207';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "DocAction", "135", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpdocaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process221(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads221';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_BankAccount_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcBankaccountId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process0DECE2AFEFAB49B1B25D8909A8A3B356(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads0DECE2AFEFAB49B1B25D8909A8A3B356';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpdocactionre", "inpdocactionre", "inpdocactionre", "inpdocactionre", "inpdocactionre")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "AccionRet", "CB29EF103ACC49108693B711ACEF6261", "99BB277B6C514B59A24FBD1EF24F6429", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaccionret";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process175(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads175';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#userClient")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "103", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadClientId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inp#adLanguage")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "AD_Table_ID", "800022", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadTableId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800136(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800136';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FF8081812F06A183012F07323A2A001C", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process140(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads140';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FF8081812F06A183012F07323A2A001C", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process206(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads206';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "DocAction", "135", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpdocaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processA44D94CE20024981922580D9053FF944(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsA44D94CE20024981922580D9053FF944';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpestado")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "Estado", "9C5B12FF0D424C638816FA7BC8E17B9B", "02330180CA23485280390C42F1469A05", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpestado";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process224(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads224';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpcProjectId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_ProjectLine_ID", "", "175", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcProjectlineId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processD234AE084F7040DCB66E281A4237FF99(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsD234AE084F7040DCB66E281A4237FF99';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId", "inpadClientId", "inpadOrgId", "inpadClientId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_BPartner_ID", "", "95548E7077124EB7A83F85A000CB2350", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcBpartnerId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800075(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800075';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#adClientId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "M_Warehouse_ID", "197", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmWarehouseId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process155(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads155';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinppaymentrule")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "PaymentRule", "195", "162", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inppaymentrule";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process5A2A0AF88AF54BB085DCC52FCC9B17B7(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads5A2A0AF88AF54BB085DCC52FCC9B17B7';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpresStatus", "inpresStatus", "inpresStatus", "inpresStatus")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "RES_Action", "440DDA64A43F4799AAFF48BC86DC8F78", "1645143617E44289A08A1EA4D617A184", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpresAction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process1004400000(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads1004400000';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_ID", "", "A3DCDE5EDD4A4403AC205B131F10F84D", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmWarehouseId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process3B73C762A8B74750A3AC5BD1F1A041A8(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads3B73C762A8B74750A3AC5BD1F1A041A8';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpemNeProcesar", "inpemNeProcesar", "inpemNeProcesar", "inpemNeProcesar", "inpemNeProcesar")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "AccionPermiso", "8C83DEB7CA664B359FD14679B64236A0", "584A5CA440A84F7DA70E0A97286CBC33", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaccionpermiso";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process220(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads220';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process108(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads108';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpcAcctschemaId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FDA7BA9355A6468DAF67E1C5288990A6", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process017312F51139438A9665775E3B5392A1(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads017312F51139438A9665775E3B5392A1';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpemAprmProcess", "inpemAprmProcess")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "798239EB069F41A9BA8EE040C63DDBBC", "3842B167CA6F44239C3357A721E3BA6A", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processD7C6D8A0875D4EB8B453D717525FA9AE(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsD7C6D8A0875D4EB8B453D717525FA9AE';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpdocactionre", "inpdocactionre", "inpdocactionre", "inpdocactionre", "inpdocactionre")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "AccionRet", "CB29EF103ACC49108693B711ACEF6261", "99BB277B6C514B59A24FBD1EF24F6429", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaccionret";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process176(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads176';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#userClient")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "103", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadClientId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inp#adLanguage")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "AD_Table_ID", "800022", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadTableId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process0BDC2164ED3E48539FCEF4D306F29EFD(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads0BDC2164ED3E48539FCEF4D306F29EFD';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpemAprmProcess", "inpemAprmProcess")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "798239EB069F41A9BA8EE040C63DDBBC", "3842B167CA6F44239C3357A721E3BA6A", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processEE9ACE0D59E147A89A400680EFDBED65(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsEE9ACE0D59E147A89A400680EFDBED65';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpdocactionno", "inpdocactionno", "inpdocactionno", "inpdocactionno", "inpdocactionno")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "docstatus", "54C2C06B49414368A7ED6D06B67719AA", "7F8B2035CE0E437A8697D1AA82EE9E23", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpdocstatus";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process535686AFC88E475383664770714ABE0E(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads535686AFC88E475383664770714ABE0E';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpdocaccionno", "inpdocaccionno", "inpdocaccionno", "inpdocaccionno")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "docstatus", "31D050E5C2D843B99AD7E9470D9E8579", "89F758A512C748249270970B25072589", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpdocstatus";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process0586F175A22647FAB964D16B85971CA7(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads0586F175A22647FAB964D16B85971CA7';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpprocesar", "inpprocesar", "inpprocesar", "inpprocesar", "inpprocesar", "inpprocesar")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "AccionPedido", "58F1F6B9031E42958986214558A889C2", "C691E7A463974E77BDB1D927FBCD0443", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaccionpedido";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processFF8080812E2F8EAE012E2F94CF470014(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsFF8080812E2F8EAE012E2F94CF470014';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpprocessed", "inpprocessed")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "FF8080812E443491012E443C053A001A", "FF808081332719060133271E5BB1001B", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process23D1B163EC0B41F790CE39BF01DA320E(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads23D1B163EC0B41F790CE39BF01DA320E';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpissotrx", "inpadClientId", "inpissotrx", "inpadOrgId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_Tax_ID", "", "299FA667CF374AC5ACC74739C3251134", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcTaxId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800131(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800131';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpstatus", "inpstatus")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "657B89EF105149F2B011CF8F5034FF92", "C5A7AABB91A440EBAA53A0222B99FF2F", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800172(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800172';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpoutputtype")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "1000200002", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process225(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads225';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpcProjectId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_ProjectLine_ID", "", "174", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcProjectlineId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process48433D8674BA4CD5B8A856693D37FB3F(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads48433D8674BA4CD5B8A856693D37FB3F';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpdocactionno", "inpdocactionno", "inpdocactionno", "inpdocactionno", "inpdocactionno")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "Estado", "7A4D156C7DD94868A3D05363B9EF1AB1", "8E3A956681DF426CB0531E90D9FD60B2", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpestado";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process391DE834C5634644A16593B0286EFB64(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads391DE834C5634644A16593B0286EFB64';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpestado")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "Estado", "6EE3EA7321544309803D4992D165F316", "108DC5CA9711416B862D15D98CBA3BF2", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpestado";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process112(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads112';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FF8081812F06A183012F07323A2A001C", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processD2999B81B4E84547B24948C424867738(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsD2999B81B4E84547B24948C424867738';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpdocaccionno", "inpdocaccionno", "inpdocaccionno", "inpdocaccionno")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "docstatus", "31D050E5C2D843B99AD7E9470D9E8579", "DEF0F2249CF5430282917AA0F0D6788F", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpdocstatus";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process4E8BB4D0A9304152BBF0D79DDD89FAC3(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads4E8BB4D0A9304152BBF0D79DDD89FAC3';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#adClientId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "BODEGA_ID", "197", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpbodegaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process6E4252763C694A5BADCEB67B1F53D0E0(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads6E4252763C694A5BADCEB67B1F53D0E0';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpdocactionno", "inpdocactionno", "inpdocactionno", "inpdocactionno", "inpdocactionno")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "docstatus", "31D050E5C2D843B99AD7E9470D9E8579", "42D8EA75822A4F24ADE2C5032353723C", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpdocstatus";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process154(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads154';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpissotrx", "inpadOrgId", "inpadClientId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_PriceList_Version_ID", "", "26D8602C48004E1182B46310DF7015AE", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmPricelistVersionId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processF9AE3E9FC50A4669AF6D2C7129AACAC3(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsF9AE3E9FC50A4669AF6D2C7129AACAC3';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpdocaccionno", "inpdocaccionno", "inpdocaccionno", "inpdocaccionno")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "docstatus", "31D050E5C2D843B99AD7E9470D9E8579", "92C63A8636B746B9BAB9B1DE387BA898", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpdocstatus";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processA164779BCCA640BC9D5B201574C8B8D2(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsA164779BCCA640BC9D5B201574C8B8D2';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpdocaccionno", "inpdocaccionno", "inpdocaccionno", "inpdocaccionno", "inpdocaccionno")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "Estado", "31D050E5C2D843B99AD7E9470D9E8579", "7160D57388BB4E8C812A53B4634D00DD", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpestado";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process3CA9715730B240F0A9BEBE9101F93FF6(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads3CA9715730B240F0A9BEBE9101F93FF6';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpemNeProcesar", "inpemNeProcesar", "inpemNeProcesar", "inpemNeProcesar")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "AccionVacacion", "D90979EA306F493EBD3EF5D201355C56", "69D9808AB1164161B5A9B0CB7EFB54EF", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaccionvacacion";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process6255BE488882480599C81284B70CD9B3(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads6255BE488882480599C81284B70CD9B3';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpemAprmProcessPayment", "inpemAprmProcessPayment", "inpfinPaymentId", "inpemAprmProcessPayment", "inpfinPaymentId", "inpemAprmProcessPayment", "inpstatus", "inpemAprmProcessPayment")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "36972531DA994BB38ECB91993058282F", "575E470ABADB4C278132C957A78C47E3", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processE264309FF8244A94936502BF51829109(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsE264309FF8244A94936502BF51829109';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#userClient")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "103", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadClientId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inp#adLanguage")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "AD_Table_ID", "800022", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadTableId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
}
