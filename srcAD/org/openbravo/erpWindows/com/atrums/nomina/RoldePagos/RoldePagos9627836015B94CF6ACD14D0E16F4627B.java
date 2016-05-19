
package org.openbravo.erpWindows.com.atrums.nomina.RoldePagos;


import org.openbravo.erpCommon.reference.*;


import org.openbravo.erpCommon.ad_actionButton.*;


import org.codehaus.jettison.json.JSONObject;
import org.openbravo.erpCommon.utility.*;
import org.openbravo.data.FieldProvider;
import org.openbravo.utils.FormatUtilities;
import org.openbravo.utils.Replace;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.exception.OBException;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessRunner;
import org.openbravo.erpCommon.businessUtility.WindowTabs;
import org.openbravo.xmlEngine.XmlDocument;
import java.util.Vector;
import java.util.StringTokenizer;
import org.openbravo.database.SessionInfo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.Connection;
import org.apache.log4j.Logger;

public class RoldePagos9627836015B94CF6ACD14D0E16F4627B extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(RoldePagos9627836015B94CF6ACD14D0E16F4627B.class);
  
  private static final String windowId = "8002680D189A4CB397E63CBEA7FAA18F";
  private static final String tabId = "9627836015B94CF6ACD14D0E16F4627B";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "3F9AFF0D312A4068A3DE78EDF4326B80";
  
  @Override
  public void init(ServletConfig config) {
    setClassInfo("W", tabId, moduleId);
    super.init(config);
  }
  
  
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    String command = vars.getCommand();
    
    boolean securedProcess = false;
    if (command.contains("BUTTON")) {
     SessionInfo.setUserId(vars.getSessionValue("#AD_User_ID"));
     SessionInfo.setSessionId(vars.getSessionValue("#AD_Session_ID"));
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("7D59984E9BC24E2D931DC4869560F7A5")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("7D59984E9BC24E2D931DC4869560F7A5");
        SessionInfo.setModuleId("3F9AFF0D312A4068A3DE78EDF4326B80");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "7D59984E9BC24E2D931DC4869560F7A5";
        }
      }
     
      if (command.contains("F9AE3E9FC50A4669AF6D2C7129AACAC3")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("F9AE3E9FC50A4669AF6D2C7129AACAC3");
        SessionInfo.setModuleId("3F9AFF0D312A4068A3DE78EDF4326B80");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "F9AE3E9FC50A4669AF6D2C7129AACAC3";
        }
      }
     

     
    }
    if (!securedProcess) {
      setClassInfo("W", tabId, moduleId);
    }
    super.service(request, response);
  }
  

  public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
    TableSQLData tableSQL = null;
    VariablesSecureApp vars = new VariablesSecureApp(request);
    Boolean saveRequest = (Boolean) request.getAttribute("autosave");
    
    if(saveRequest != null && saveRequest){
      String currentOrg = vars.getStringParameter("inpadOrgId");
      String currentClient = vars.getStringParameter("inpadClientId");
      boolean editableTab = (!org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)
                            && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars,"#User_Org", windowId, accesslevel), currentOrg)) 
                            && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),currentClient)));
    
        OBError myError = new OBError();
        String commandType = request.getParameter("inpCommandType");
        String strnoRolPagoProvisionId = request.getParameter("inpnoRolPagoProvisionId");
        
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strnoRolPagoProvisionId.equals(""))
              total = saveRecord(vars, myError, 'U');
          else
              total = saveRecord(vars, myError, 'I');
          
          if (!myError.isEmpty() && total == 0)     
            throw new OBException(myError.getMessage());
        }
        vars.setSessionValue(request.getParameter("mappingName") +"|hash", vars.getPostDataHash());
        vars.setSessionValue(tabId + "|Header.view", "EDIT");
        
        return;
    }
    
    try {
      tableSQL = new TableSQLData(vars, this, tabId, Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    String strOrderBy = vars.getSessionValue(tabId + "|orderby");
    if (!strOrderBy.equals("")) {
      vars.setSessionValue(tabId + "|newOrder", "1");
    }

    if (vars.commandIn("DEFAULT")) {

      String strNO_Rol_Pago_Provision_ID = vars.getGlobalVariable("inpnoRolPagoProvisionId", windowId + "|NO_Rol_Pago_Provision_ID", "");
      

      String strView = vars.getSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strNO_Rol_Pago_Provision_ID.equals("")) strNO_Rol_Pago_Provision_ID = firstElement(vars, tableSQL);
          if (strNO_Rol_Pago_Provision_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strNO_Rol_Pago_Provision_ID, tableSQL);

      else printPageDataSheet(response, vars, strNO_Rol_Pago_Provision_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strNO_Rol_Pago_Provision_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strNO_Rol_Pago_Provision_ID.equals("")) strNO_Rol_Pago_Provision_ID = vars.getRequiredGlobalVariable("inpnoRolPagoProvisionId", windowId + "|NO_Rol_Pago_Provision_ID");
      else vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_ID", strNO_Rol_Pago_Provision_ID);
      
      vars.setSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.view", "EDIT");

      printPageEdit(response, request, vars, false, strNO_Rol_Pago_Provision_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {


      String strView = vars.getSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.view");
      String strNO_Rol_Pago_Provision_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strNO_Rol_Pago_Provision_ID = firstElement(vars, tableSQL);
          if (strNO_Rol_Pago_Provision_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strNO_Rol_Pago_Provision_ID.equals("")) strNO_Rol_Pago_Provision_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strNO_Rol_Pago_Provision_ID, tableSQL);

      } else printPageDataSheet(response, vars, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentno", tabId + "|paramDocumentno");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
      
      
      vars.removeSessionValue(windowId + "|NO_Rol_Pago_Provision_ID");
      String strNO_Rol_Pago_Provision_ID="";

      String strView = vars.getSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strNO_Rol_Pago_Provision_ID = firstElement(vars, tableSQL);
        if (strNO_Rol_Pago_Provision_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strNO_Rol_Pago_Provision_ID, tableSQL);

      else printPageDataSheet(response, vars, strNO_Rol_Pago_Provision_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
      

      String strNO_Rol_Pago_Provision_ID = vars.getGlobalVariable("inpnoRolPagoProvisionId", windowId + "|NO_Rol_Pago_Provision_ID", "");
      vars.setSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.view", "RELATION");
      printPageDataSheet(response, vars, strNO_Rol_Pago_Provision_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {


      printPageEdit(response, request, vars, true, "", tableSQL);

    } else if (vars.commandIn("EDIT")) {

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strNO_Rol_Pago_Provision_ID = vars.getGlobalVariable("inpnoRolPagoProvisionId", windowId + "|NO_Rol_Pago_Provision_ID", "");
      vars.setSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strNO_Rol_Pago_Provision_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {

      String strNO_Rol_Pago_Provision_ID = vars.getRequiredStringParameter("inpnoRolPagoProvisionId");
      
      String strNext = nextElement(vars, strNO_Rol_Pago_Provision_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {

      String strNO_Rol_Pago_Provision_ID = vars.getRequiredStringParameter("inpnoRolPagoProvisionId");
      
      String strPrevious = previousElement(vars, strNO_Rol_Pago_Provision_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {

      vars.setSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|NO_Rol_Pago_Provision_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {

      String strInitRecord = vars.getSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|NO_Rol_Pago_Provision_ID");

      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {

      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {

      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {

      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I');      
      if (!myError.isEmpty()) {        
        response.sendRedirect(strDireccion + request.getServletPath() + "?Command=NEW");
      } 
      else {
		if (myError.isEmpty()) {
		  myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsInserted");
		  myError.setMessage(total + " " + myError.getMessage());
		  vars.setMessage(tabId, myError);
		}        
        if (vars.commandIn("SAVE_NEW_NEW")) response.sendRedirect(strDireccion + request.getServletPath() + "?Command=NEW");
        else if (vars.commandIn("SAVE_NEW_EDIT")) response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("SAVE_EDIT_RELATION", "SAVE_EDIT_NEW", "SAVE_EDIT_EDIT", "SAVE_EDIT_NEXT")) {

      String strNO_Rol_Pago_Provision_ID = vars.getRequiredGlobalVariable("inpnoRolPagoProvisionId", windowId + "|NO_Rol_Pago_Provision_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U');      
      if (!myError.isEmpty()) {
        response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
      } 
      else {
        if (myError.isEmpty()) {
          myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsUpdated");
          myError.setMessage(total + " " + myError.getMessage());
          vars.setMessage(tabId, myError);
        }
        if (vars.commandIn("SAVE_EDIT_NEW")) response.sendRedirect(strDireccion + request.getServletPath() + "?Command=NEW");
        else if (vars.commandIn("SAVE_EDIT_EDIT")) response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        else if (vars.commandIn("SAVE_EDIT_NEXT")) {
          String strNext = nextElement(vars, strNO_Rol_Pago_Provision_ID, tableSQL);
          vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {

      String strNO_Rol_Pago_Provision_ID = vars.getRequiredStringParameter("inpnoRolPagoProvisionId");
      //RoldePagos9627836015B94CF6ACD14D0E16F4627BData data = getEditVariables(vars);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = RoldePagos9627836015B94CF6ACD14D0E16F4627BData.delete(this, strNO_Rol_Pago_Provision_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
        } catch(ServletException ex) {
          myError = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          if (!myError.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          } else vars.setMessage(tabId, myError);
        }
        if (myError==null && total==0) {
          myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
        }
        vars.removeSessionValue(windowId + "|noRolPagoProvisionId");
        vars.setSessionValue(tabId + "|RoldePagos9627836015B94CF6ACD14D0E16F4627B.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONPayment7D59984E9BC24E2D931DC4869560F7A5")) {
        vars.setSessionValue("button7D59984E9BC24E2D931DC4869560F7A5.strpayment", vars.getStringParameter("inppayment"));
        vars.setSessionValue("button7D59984E9BC24E2D931DC4869560F7A5.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button7D59984E9BC24E2D931DC4869560F7A5.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button7D59984E9BC24E2D931DC4869560F7A5.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button7D59984E9BC24E2D931DC4869560F7A5.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "7D59984E9BC24E2D931DC4869560F7A5", request.getServletPath());    
     } else if (vars.commandIn("BUTTON7D59984E9BC24E2D931DC4869560F7A5")) {
        String strNO_Rol_Pago_Provision_ID = vars.getGlobalVariable("inpnoRolPagoProvisionId", windowId + "|NO_Rol_Pago_Provision_ID", "");
        String strpayment = vars.getSessionValue("button7D59984E9BC24E2D931DC4869560F7A5.strpayment");
        String strProcessing = vars.getSessionValue("button7D59984E9BC24E2D931DC4869560F7A5.strProcessing");
        String strOrg = vars.getSessionValue("button7D59984E9BC24E2D931DC4869560F7A5.strOrg");
        String strClient = vars.getSessionValue("button7D59984E9BC24E2D931DC4869560F7A5.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonPayment7D59984E9BC24E2D931DC4869560F7A5(response, vars, strNO_Rol_Pago_Provision_ID, strpayment, strProcessing);
        }

     } else if (vars.commandIn("BUTTONDocaccionnoF9AE3E9FC50A4669AF6D2C7129AACAC3")) {
        vars.setSessionValue("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.strdocaccionno", vars.getStringParameter("inpdocaccionno"));
        vars.setSessionValue("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("Docaccionno", vars.getStringParameter("inpdocaccionno"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "F9AE3E9FC50A4669AF6D2C7129AACAC3", request.getServletPath());    
     } else if (vars.commandIn("BUTTONF9AE3E9FC50A4669AF6D2C7129AACAC3")) {
        String strNO_Rol_Pago_Provision_ID = vars.getGlobalVariable("inpnoRolPagoProvisionId", windowId + "|NO_Rol_Pago_Provision_ID", "");
        String strdocaccionno = vars.getSessionValue("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.strdocaccionno");
        String strProcessing = vars.getSessionValue("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.strProcessing");
        String strOrg = vars.getSessionValue("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.strOrg");
        String strClient = vars.getSessionValue("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocaccionnoF9AE3E9FC50A4669AF6D2C7129AACAC3(response, vars, strNO_Rol_Pago_Provision_ID, strdocaccionno, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONPayment7D59984E9BC24E2D931DC4869560F7A5")) {
        String strNO_Rol_Pago_Provision_ID = vars.getGlobalVariable("inpKey", windowId + "|NO_Rol_Pago_Provision_ID", "");
        @SuppressWarnings("unused")
        String strpayment = vars.getStringParameter("inppayment");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "7D59984E9BC24E2D931DC4869560F7A5", (("NO_Rol_Pago_Provision_ID".equalsIgnoreCase("AD_Language"))?"0":strNO_Rol_Pago_Provision_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
          ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
          new ProcessRunner(bundle).execute(this);
          
          PInstanceProcessData[] pinstanceData = PInstanceProcessData.select(this, pinstance);
          myMessage = Utility.getProcessInstanceMessage(this, vars, pinstanceData);
        } catch (ServletException ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          } else vars.setMessage(tabId, myMessage);
        }
        //close popup
        if (myMessage!=null) {
          if (log4j.isDebugEnabled()) log4j.debug(myMessage.getMessage());
          vars.setMessage(tabId, myMessage);
        }
        printPageClosePopUp(response, vars);
    } else if (vars.commandIn("SAVE_BUTTONDocaccionnoF9AE3E9FC50A4669AF6D2C7129AACAC3")) {
        String strNO_Rol_Pago_Provision_ID = vars.getGlobalVariable("inpKey", windowId + "|NO_Rol_Pago_Provision_ID", "");
        @SuppressWarnings("unused")
        String strdocaccionno = vars.getStringParameter("inpdocaccionno");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "F9AE3E9FC50A4669AF6D2C7129AACAC3", (("NO_Rol_Pago_Provision_ID".equalsIgnoreCase("AD_Language"))?"0":strNO_Rol_Pago_Provision_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          String strdocstatus = vars.getStringParameter("inpdocstatus");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "docstatus", strdocstatus, vars.getClient(), vars.getOrg(), vars.getUser());

          
          ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
          new ProcessRunner(bundle).execute(this);
          
          PInstanceProcessData[] pinstanceData = PInstanceProcessData.select(this, pinstance);
          myMessage = Utility.getProcessInstanceMessage(this, vars, pinstanceData);
        } catch (ServletException ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          } else vars.setMessage(tabId, myMessage);
        }
        //close popup
        if (myMessage!=null) {
          if (log4j.isDebugEnabled()) log4j.debug(myMessage.getMessage());
          vars.setMessage(tabId, myMessage);
        }
        printPageClosePopUp(response, vars);



    } else if (vars.commandIn("BUTTONPosted")) {
        String strNO_Rol_Pago_Provision_ID = vars.getGlobalVariable("inpnoRolPagoProvisionId", windowId + "|NO_Rol_Pago_Provision_ID", "");
        String strTableId = "F5057A2680BB43149D7C57B1224A7653";
        String strPosted = vars.getStringParameter("inpposted");
        String strProcessId = "";
        log4j.debug("Loading Posted button in table: " + strTableId);
        String strOrg = vars.getStringParameter("inpadOrgId");
        String strClient = vars.getStringParameter("inpadClientId");
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{
          vars.setSessionValue("Posted|key", strNO_Rol_Pago_Provision_ID);
          vars.setSessionValue("Posted|tableId", strTableId);
          vars.setSessionValue("Posted|tabId", tabId);
          vars.setSessionValue("Posted|posted", strPosted);
          vars.setSessionValue("Posted|processId", strProcessId);
          vars.setSessionValue("Posted|path", strDireccion + request.getServletPath());
          vars.setSessionValue("Posted|windowId", windowId);
          vars.setSessionValue("Posted|tabName", "RoldePagos9627836015B94CF6ACD14D0E16F4627B");
          response.sendRedirect(strDireccion + "/ad_actionButton/Posted.html");
        }



    } else if (vars.commandIn("SAVE_XHR")) {
      
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType);
        if (myError.isEmpty()) {
          myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsUpdated");
          myError.setMessage(total + " " + myError.getMessage());
          myError.setType("Success");
        }
        result.put("oberror", myError.toMap());
        result.put("tabid", vars.getStringParameter("tabID"));
        result.put("redirect", strDireccion + request.getServletPath() + "?Command=" + commandType);
      } catch (Exception e) {
        log4j.error("Error saving record (XHR request): " + e.getMessage(), e);
        myError.setType("Error");
        myError.setMessage(e.getMessage());
      }

      response.setContentType("application/json");
      PrintWriter out = response.getWriter();
      out.print(result.toString());
      out.flush();
      out.close();
    } else if (vars.getCommand().toUpperCase().startsWith("BUTTON") || vars.getCommand().toUpperCase().startsWith("SAVE_BUTTON")) {
      pageErrorPopUp(response);
    } else pageError(response);
  }
  private RoldePagos9627836015B94CF6ACD14D0E16F4627BData getEditVariables(Connection con, VariablesSecureApp vars) throws IOException,ServletException {
    RoldePagos9627836015B94CF6ACD14D0E16F4627BData data = new RoldePagos9627836015B94CF6ACD14D0E16F4627BData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adOrgIdr = vars.getStringParameter("inpadOrgId_R");     data.noAreaEmpresaId = vars.getStringParameter("inpnoAreaEmpresaId");     data.noAreaEmpresaIdr = vars.getStringParameter("inpnoAreaEmpresaId_R");     data.cBpartnerId = vars.getRequiredGlobalVariable("inpcBpartnerId", windowId + "|C_Bpartner_ID");     data.cBpartnerIdr = vars.getStringParameter("inpcBpartnerId_R");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.cPeriodId = vars.getStringParameter("inpcPeriodId");     data.cPeriodIdr = vars.getStringParameter("inpcPeriodId_R");    try {   data.totalIngreso = vars.getNumericParameter("inptotalIngreso");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalEgreso = vars.getNumericParameter("inptotalEgreso");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.totalNeto = vars.getRequiredNumericParameter("inptotalNeto");  } catch (ServletException paramEx) { ex = paramEx; }     data.isactive = vars.getStringParameter("inpisactive", "N");     data.processed = vars.getStringParameter("inpprocessed", "N");     data.payment = vars.getRequiredStringParameter("inppayment");     data.docaccionno = vars.getRequiredGlobalVariable("inpdocaccionno", windowId + "|Docaccionno");     data.posted = vars.getStringParameter("inpposted");     data.processing = vars.getStringParameter("inpprocessing");     data.dateacct = vars.getStringParameter("inpdateacct");     data.emNeObservacion = vars.getStringParameter("inpemNeObservacion");     data.inPayment = vars.getStringParameter("inpinPayment", "N");     data.noCDoctypeId = vars.getStringParameter("inpnoCDoctypeId");     data.emNeNumContrato = vars.getStringParameter("inpemNeNumContrato");     data.enviomail = vars.getStringParameter("inpenviomail");     data.ispago = vars.getRequiredInputGlobalVariable("inpispago", windowId + "|Ispago", "N");     data.docstatus = vars.getRequiredGlobalVariable("inpdocstatus", windowId + "|Docstatus");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.noRolPagoProvisionId = vars.getRequestGlobalVariable("inpnoRolPagoProvisionId", windowId + "|NO_Rol_Pago_Provision_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");



    
    

    
    }
    catch(ServletException e) {
    	vars.setEditionData(tabId, data);
    	throw e;
    }
    // Behavior with exception for numeric fields is to catch last one if we have multiple ones
    if(ex != null) {
      vars.setEditionData(tabId, data);
      throw ex;
    }
    return data;
  }




    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].getField("cBpartnerId"));    vars.setSessionValue(windowId + "|Docaccionno", data[0].getField("docaccionno"));    vars.setSessionValue(windowId + "|docstatus", data[0].getField("docstatus"));    vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_ID", data[0].getField("noRolPagoProvisionId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|Ispago", data[0].getField("ispago"));
    }

    private void refreshSessionNew(VariablesSecureApp vars) throws IOException,ServletException {
      RoldePagos9627836015B94CF6ACD14D0E16F4627BData[] data = RoldePagos9627836015B94CF6ACD14D0E16F4627BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getStringParameter("inpnoRolPagoProvisionId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
      refreshSessionEdit(vars, data);
    }

  private String nextElement(VariablesSecureApp vars, String strSelected, TableSQLData tableSQL) throws IOException, ServletException {
    if (strSelected == null || strSelected.equals("")) return firstElement(vars, tableSQL);
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(), 0, 0);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.NEXT, strSelected, tableSQL.getKeyColumn());
      } catch (Exception e) { 
        log4j.error("Error getting next element", e);
      }
      if (data!=null) {
        if (data!=null) return data;
      }
    }
    return strSelected;
  }

  private int getKeyPosition(VariablesSecureApp vars, String strSelected, TableSQLData tableSQL) throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("getKeyPosition: " + strSelected);
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(),0,0);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.GETPOSITION, strSelected, tableSQL.getKeyColumn());
      } catch (Exception e) { 
        log4j.error("Error getting key position", e);
      }
      if (data!=null) {
        // split offset -> (page,relativeOffset)
        int absoluteOffset = Integer.valueOf(data);
        int page = absoluteOffset / TableSQLData.maxRowsPerGridPage;
        int relativeOffset = absoluteOffset % TableSQLData.maxRowsPerGridPage;
        log4j.debug("getKeyPosition: absOffset: " + absoluteOffset + "=> page: " + page + " relOffset: " + relativeOffset);
        String currPageKey = tabId + "|" + "currentPage";
        vars.setSessionValue(currPageKey, String.valueOf(page));
        return relativeOffset;
      }
    }
    return 0;
  }

  private String previousElement(VariablesSecureApp vars, String strSelected, TableSQLData tableSQL) throws IOException, ServletException {
    if (strSelected == null || strSelected.equals("")) return firstElement(vars, tableSQL);
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(),0,0);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.PREVIOUS, strSelected, tableSQL.getKeyColumn());
      } catch (Exception e) { 
        log4j.error("Error getting previous element", e);
      }
      if (data!=null) {
        return data;
      }
    }
    return strSelected;
  }

  private String firstElement(VariablesSecureApp vars, TableSQLData tableSQL) throws IOException, ServletException {
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(),0,1);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.FIRST, "", tableSQL.getKeyColumn());

      } catch (Exception e) { 
        log4j.debug("Error getting first element", e);
      }
      if (data!=null) return data;
    }
    return "";
  }

  private String lastElement(VariablesSecureApp vars, TableSQLData tableSQL) throws IOException, ServletException {
    if (tableSQL!=null) {
      String data = null;
      try{
        String strSQL = ModelSQLGeneration.generateSQLonlyId(this, vars, tableSQL, (tableSQL.getTableName() + "." + tableSQL.getKeyColumn() + " AS ID"), new Vector<String>(), new Vector<String>(),0,0);
        ExecuteQuery execquery = new ExecuteQuery(this, strSQL, tableSQL.getParameterValuesOnlyId());
        data = execquery.selectAndSearch(ExecuteQuery.SearchType.LAST, "", tableSQL.getKeyColumn());
      } catch (Exception e) { 
        log4j.error("Error getting last element", e);
      }
      if (data!=null) return data;
    }
    return "";
  }

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strNO_Rol_Pago_Provision_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strNO_Rol_Pago_Provision_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strNO_Rol_Pago_Provision_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/atrums/nomina/RoldePagos/RoldePagos9627836015B94CF6ACD14D0E16F4627B_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "RoldePagos9627836015B94CF6ACD14D0E16F4627B", false, "document.frmMain.inpnoRolPagoProvisionId", "grid", "../com.atrums.nomina/roles/print.html", "N".equals("Y"), "RoldePagos", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());



    StringBuffer orderByArray = new StringBuffer();
      vars.setSessionValue(tabId + "|newOrder", "1");
      String positions = vars.getSessionValue(tabId + "|orderbyPositions");
      orderByArray.append("var orderByPositions = new Array(\n");
      if (!positions.equals("")) {
        StringTokenizer tokens=new StringTokenizer(positions, ",");
        boolean firstOrder = true;
        while(tokens.hasMoreTokens()){
          if (!firstOrder) orderByArray.append(",\n");
          orderByArray.append("\"").append(tokens.nextToken()).append("\"");
          firstOrder = false;
        }
      }
      orderByArray.append(");\n");
      String directions = vars.getSessionValue(tabId + "|orderbyDirections");
      orderByArray.append("var orderByDirections = new Array(\n");
      if (!positions.equals("")) {
        StringTokenizer tokens=new StringTokenizer(directions, ",");
        boolean firstOrder = true;
        while(tokens.hasMoreTokens()){
          if (!firstOrder) orderByArray.append(",\n");
          orderByArray.append("\"").append(tokens.nextToken()).append("\"");
          firstOrder = false;
        }
      }
      orderByArray.append(");\n");
//    }

    xmlDocument.setParameter("selectedColumn", "\nvar selectedRow = " + selectedRow + ";\n" + orderByArray.toString());
    xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
    xmlDocument.setParameter("windowId", windowId);
    xmlDocument.setParameter("KeyName", "noRolPagoProvisionId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "RoldePagos9627836015B94CF6ACD14D0E16F4627B_Relation.html", "RoldePagos", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "RoldePagos9627836015B94CF6ACD14D0E16F4627B_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.relationTemplate());
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
    {
      OBError myMessage = vars.getMessage(tabId);
      vars.removeMessage(tabId);
      if (myMessage!=null) {
        xmlDocument.setParameter("messageType", myMessage.getType());
        xmlDocument.setParameter("messageTitle", myMessage.getTitle());
        xmlDocument.setParameter("messageMessage", myMessage.getMessage());
      }
    }


    xmlDocument.setParameter("grid", Utility.getContext(this, vars, "#RecordRange", windowId));
xmlDocument.setParameter("grid_Offset", strOffset);
xmlDocument.setParameter("grid_SortCols", positions);
xmlDocument.setParameter("grid_SortDirs", directions);
xmlDocument.setParameter("grid_Default", selectedRow);


    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strNO_Rol_Pago_Provision_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: edit");
    
    HashMap<String, String> usedButtonShortCuts;
  
    HashMap<String, String> reservedButtonShortCuts;
  
    usedButtonShortCuts = new HashMap<String, String>();
    
    reservedButtonShortCuts = new HashMap<String, String>();
    
    
    
    String strOrderByFilter = vars.getSessionValue(tabId + "|orderby");
    String orderClause = " 1";
    if (strOrderByFilter==null || strOrderByFilter.equals("")) strOrderByFilter = orderClause;
    /*{
      if (!strOrderByFilter.equals("") && !orderClause.equals("")) strOrderByFilter += ", ";
      strOrderByFilter += orderClause;
    }*/
    
    
    String strCommand = null;
    RoldePagos9627836015B94CF6ACD14D0E16F4627BData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = RoldePagos9627836015B94CF6ACD14D0E16F4627BData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strNO_Rol_Pago_Provision_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strNO_Rol_Pago_Provision_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new RoldePagos9627836015B94CF6ACD14D0E16F4627BData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("noRolPagoProvisionId") == null || dataField.getField("noRolPagoProvisionId").equals("")) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        boolNew = true;
      } else {
        discard[0] = new String ("newDiscard");
        strCommand = "EDIT";
      }
    }
    
    
    
    if (dataField==null) {
      if (boolNew || data==null || data.length==0) {
        refreshSessionNew(vars);
        data = RoldePagos9627836015B94CF6ACD14D0E16F4627BData.set("", Utility.getDefault(this, vars, "em_ne_observacion", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), Utility.getDefault(this, vars, "Processed", "N", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField), Utility.getDefault(this, vars, "Total_Ingreso", "0", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), Utility.getDefault(this, vars, "IN_Payment", "N", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), Utility.getDefault(this, vars, "em_ne_num_contrato", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), RoldePagos9627836015B94CF6ACD14D0E16F4627BData.selectDef44057D41DF18403D8D3853403E452D2B_0(this, Utility.getDefault(this, vars, "Createdby", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField)), Utility.getDefault(this, vars, "Total_Neto", "0", "8002680D189A4CB397E63CBEA7FAA18F", "0", dataField), Utility.getDefault(this, vars, "Updatedby", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), RoldePagos9627836015B94CF6ACD14D0E16F4627BData.selectDef584B0B2374BB42A5AB1176EB27F0B063_1(this, Utility.getDefault(this, vars, "Updatedby", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField)), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), RoldePagos9627836015B94CF6ACD14D0E16F4627BData.selectDef64196704CBB64C56B398440A16A42DD5_2(this, Utility.getDefault(this, vars, "C_Bpartner_ID", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField)), Utility.getDefault(this, vars, "Ispago", "@IsSOTrx@", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField), Utility.getDefault(this, vars, "NO_C_Doctype_ID", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), Utility.getDefault(this, vars, "Dateacct", "@#Date@", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), Utility.getDefault(this, vars, "Docstatus", "BR", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), Utility.getDefault(this, vars, "Enviomail", "N", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField), Utility.getDefault(this, vars, "Docaccionno", "CO", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "31D050E5C2D843B99AD7E9470D9E8579", Utility.getDefault(this, vars, "Docaccionno", "CO", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "31D050E5C2D843B99AD7E9470D9E8579", Utility.getDefault(this, vars, "Docaccionno", "CO", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField))), Utility.getDefault(this, vars, "Documentno", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), Utility.getDefault(this, vars, "Payment", "N", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField), Utility.getDefault(this, vars, "Posted", "N", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "N", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "N", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField))), Utility.getDefault(this, vars, "NO_Area_Empresa_ID", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), "Y", Utility.getDefault(this, vars, "C_Doctype_ID", "", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), RoldePagos9627836015B94CF6ACD14D0E16F4627BData.selectDefC708EC749EE04D2E9D1245034DCFF2A7(this, Utility.getContext(this, vars, "AD_ORG_ID", "8002680D189A4CB397E63CBEA7FAA18F"), Utility.getContext(this, vars, "AD_CLIENT_ID", "8002680D189A4CB397E63CBEA7FAA18F")), Utility.getDefault(this, vars, "Total_Egreso", "0", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField), Utility.getDefault(this, vars, "Processing", "N", "8002680D189A4CB397E63CBEA7FAA18F", "N", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "8002680D189A4CB397E63CBEA7FAA18F", "", dataField));
        
      }
     }
      
    
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/atrums/nomina/RoldePagos/RoldePagos9627836015B94CF6ACD14D0E16F4627B_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/atrums/nomina/RoldePagos/RoldePagos9627836015B94CF6ACD14D0E16F4627B_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "RoldePagos9627836015B94CF6ACD14D0E16F4627B", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpnoRolPagoProvisionId", "", "../com.atrums.nomina/roles/print.html", "N".equals("Y"), "RoldePagos", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strNO_Rol_Pago_Provision_ID), !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    toolbar.setDeleteable(true);
    toolbar.prepareEditionTemplate("N".equals("Y"), hasSearchCondition, vars.getSessionValue("#ShowTest", "N").equals("Y"), "STD", Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    // set updated timestamp to manage locking mechanism
    if (!boolNew) {
      xmlDocument.setParameter("updatedTimestamp", (dataField != null ? dataField
          .getField("updatedTimeStamp") : data[0].getField("updatedTimeStamp")));
    }
    
    boolean concurrentSave = vars.getSessionValue(tabId + "|concurrentSave").equals("true");
    if (concurrentSave) {
      //after concurrent save error, force autosave
      xmlDocument.setParameter("autosave", "Y");
    } else {
      xmlDocument.setParameter("autosave", "N");
    }
    vars.removeSessionValue(tabId + "|concurrentSave");

    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, true, (strCommand.equalsIgnoreCase("NEW")));
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      // if (!strNO_Rol_Pago_Provision_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "RoldePagos9627836015B94CF6ACD14D0E16F4627B_Relation.html", "RoldePagos", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "RoldePagos9627836015B94CF6ACD14D0E16F4627B_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
    
    
    xmlDocument.setParameter("commandType", strCommand);
    xmlDocument.setParameter("buscador",buscador);
    xmlDocument.setParameter("windowId", windowId);
    xmlDocument.setParameter("changed", "");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    final String strMappingName = Utility.getTabURL(tabId, "E", false);
    xmlDocument.setParameter("mappingName", strMappingName);
    xmlDocument.setParameter("confirmOnChanges", Utility.getJSConfirmOnChanges(vars, windowId));
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));

    xmlDocument.setParameter("paramSessionDate", strParamSessionDate);

    xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
    OBError myMessage = vars.getMessage(tabId);
    vars.removeMessage(tabId);
    if (myMessage!=null) {
      xmlDocument.setParameter("messageType", myMessage.getType());
      xmlDocument.setParameter("messageTitle", myMessage.getTitle());
      xmlDocument.setParameter("messageMessage", myMessage.getMessage());
    }
    xmlDocument.setParameter("displayLogic", getDisplayLogicContext(vars, boolNew));
    
    
     if (dataField==null) {
      xmlDocument.setData("structure1",data);
      
    } else {
      
        FieldProvider[] dataAux = new FieldProvider[1];
        dataAux[0] = dataField;
        
        xmlDocument.setData("structure1",dataAux);
      
    }
    
      
   
    try {
      ComboTableData comboTableData = null;
String userOrgList = "";
if (editableTab) 
  userOrgList=Utility.getContext(this, vars, "#User_Org", windowId, accesslevel); //editable record 
else 
  userOrgList=currentOrg;
comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "", userOrgList, Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("adOrgId"):dataField.getField("adOrgId")));
xmlDocument.setData("reportAD_Org_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "NO_Area_Empresa_ID", "EECE3B7B82874108B0F72EA85059C57A", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("noAreaEmpresaId"):dataField.getField("noAreaEmpresaId")));
xmlDocument.setData("reportNO_Area_Empresa_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "C_Doctype_ID", "727B9E7086D647E2AD6F386DD9A16C5F", "F202FD619DB3489B9002141CC2581583", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_Doctype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "18", "C_Period_ID", "233", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cPeriodId"):dataField.getField("cPeriodId")));
xmlDocument.setData("reportC_Period_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonTotal_Ingreso", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotal_Egreso", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonTotal_Neto", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Payment_BTNname", Utility.getButtonName(this, vars, "B8BDD02534A1405E9F83D441B9400E5B", "Payment_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPayment = org.openbravo.erpCommon.utility.Utility.isModalProcess("7D59984E9BC24E2D931DC4869560F7A5"); 
xmlDocument.setParameter("Payment_Modal", modalPayment?"true":"false");
xmlDocument.setParameter("Docaccionno_BTNname", Utility.getButtonName(this, vars, "31D050E5C2D843B99AD7E9470D9E8579", (dataField==null?data[0].getField("docaccionno"):dataField.getField("docaccionno")), "Docaccionno_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalDocaccionno = org.openbravo.erpCommon.utility.Utility.isModalProcess("F9AE3E9FC50A4669AF6D2C7129AACAC3"); 
xmlDocument.setParameter("Docaccionno_Modal", modalDocaccionno?"true":"false");
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
xmlDocument.setParameter("Dateacct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("Enviomail_BTNname", Utility.getButtonName(this, vars, "D5DC50695EF545FA90966C5578DBF1D4", "Enviomail_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEnviomail = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Enviomail_Modal", modalEnviomail?"true":"false");
xmlDocument.setParameter("Created_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Created_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
xmlDocument.setParameter("Updated_Format", vars.getSessionValue("#AD_SqlDateTimeFormat"));xmlDocument.setParameter("Updated_Maxlength", Integer.toString(vars.getSessionValue("#AD_SqlDateTimeFormat").length()));
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ServletException(ex);
    }

    xmlDocument.setParameter("scriptOnLoad", getShortcutScript(usedButtonShortCuts, reservedButtonShortCuts));
    
    final String refererURL = vars.getSessionValue(tabId + "|requestURL");
    vars.removeSessionValue(tabId + "|requestURL");
    if(!refererURL.equals("")) {
    	final Boolean failedAutosave = (Boolean) vars.getSessionObject(tabId + "|failedAutosave");
		vars.removeSessionValue(tabId + "|failedAutosave");
    	if(failedAutosave != null && failedAutosave) {
    		final String jsFunction = "continueUserAction('"+refererURL+"');";
    		xmlDocument.setParameter("failedAutosave", jsFunction);
    	}
    }

    if (strCommand.equalsIgnoreCase("NEW")) {
      vars.removeSessionValue(tabId + "|failedAutosave");
      vars.removeSessionValue(strMappingName + "|hash");
    }

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

  private void printPageButtonFS(HttpServletResponse response, VariablesSecureApp vars, String strProcessId, String path) throws IOException, ServletException {
    log4j.debug("Output: Frames action button");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "org/openbravo/erpCommon/ad_actionButton/ActionButtonDefaultFrames").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
    xmlDocument.setParameter("trlFormType", "PROCESS");
    xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    xmlDocument.setParameter("type", strDireccion + path);
    out.println(xmlDocument.print());
    out.close();
  }

    private void printPageButtonPayment7D59984E9BC24E2D931DC4869560F7A5(HttpServletResponse response, VariablesSecureApp vars, String strNO_Rol_Pago_Provision_ID, String strpayment, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7D59984E9BC24E2D931DC4869560F7A5");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Payment7D59984E9BC24E2D931DC4869560F7A5", discard).createXmlDocument();
      xmlDocument.setParameter("key", strNO_Rol_Pago_Provision_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "RoldePagos9627836015B94CF6ACD14D0E16F4627B_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "7D59984E9BC24E2D931DC4869560F7A5");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("7D59984E9BC24E2D931DC4869560F7A5");
        vars.removeMessage("7D59984E9BC24E2D931DC4869560F7A5");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }
    private void printPageButtonDocaccionnoF9AE3E9FC50A4669AF6D2C7129AACAC3(HttpServletResponse response, VariablesSecureApp vars, String strNO_Rol_Pago_Provision_ID, String strdocaccionno, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F9AE3E9FC50A4669AF6D2C7129AACAC3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocaccionnoF9AE3E9FC50A4669AF6D2C7129AACAC3", discard).createXmlDocument();
      xmlDocument.setParameter("key", strNO_Rol_Pago_Provision_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "RoldePagos9627836015B94CF6ACD14D0E16F4627B_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "F9AE3E9FC50A4669AF6D2C7129AACAC3");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("F9AE3E9FC50A4669AF6D2C7129AACAC3");
        vars.removeMessage("F9AE3E9FC50A4669AF6D2C7129AACAC3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("docstatus", "");
    comboTableData = new ComboTableData(vars, this, "17", "docstatus", "31D050E5C2D843B99AD7E9470D9E8579", "92C63A8636B746B9BAB9B1DE387BA898", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonF9AE3E9FC50A4669AF6D2C7129AACAC3.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportdocstatus", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }




    private String getDisplayLogicContext(VariablesSecureApp vars, boolean isNew) throws IOException, ServletException {
      log4j.debug("Output: Display logic context fields");
      String result = "var strEstado=\"" +Utility.getContext(this, vars, "Estado", windowId) + "\";\nvar strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
      return result;
    }


    private String getReadOnlyLogicContext(VariablesSecureApp vars) throws IOException, ServletException {
      log4j.debug("Output: Read Only logic context fields");
      String result = "var strEstado=\"" + Utility.getContext(this, vars, "Estado", windowId) + "\";\n";
      return result;
    }




 
  private String getShortcutScript( HashMap<String, String> usedButtonShortCuts, HashMap<String, String> reservedButtonShortCuts){
    StringBuffer shortcuts = new StringBuffer();
    shortcuts.append(" function buttonListShorcuts() {\n");
    Iterator<String> ik = usedButtonShortCuts.keySet().iterator();
    Iterator<String> iv = usedButtonShortCuts.values().iterator();
    while(ik.hasNext() && iv.hasNext()){
      shortcuts.append("  keyArray[keyArray.length] = new keyArrayItem(\"").append(ik.next()).append("\", \"").append(iv.next()).append("\", null, \"altKey\", false, \"onkeydown\");\n");
    }
    shortcuts.append(" return true;\n}");
    return shortcuts.toString();
  }
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type) throws IOException, ServletException {
    RoldePagos9627836015B94CF6ACD14D0E16F4627BData data = null;
    int total = 0;
    if (org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) {
        OBError newError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        myError.setError(newError);
        vars.setMessage(tabId, myError);
    }
    else {
        Connection con = null;
        try {
            con = this.getTransactionConnection();
            data = getEditVariables(con, vars);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.noRolPagoProvisionId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (RoldePagos9627836015B94CF6ACD14D0E16F4627BData.getCurrentDBTimestamp(this, data.noRolPagoProvisionId).equals(
                vars.getStringParameter("updatedTimestamp"))) {
                total = data.update(con, this);
               } else {
                 myError.setMessage(Replace.replace(Replace.replace(Utility.messageBD(this,
                    "SavingModifiedRecord", vars.getLanguage()), "\\n", "<br/>"), "&quot;", "\""));
                 myError.setType("Error");
                 vars.setSessionValue(tabId + "|concurrentSave", "true");
               } 
		     }		            
          
            }
                else {
            OBError newError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
            myError.setError(newError);            
          }
          releaseCommitConnection(con);
        } catch(Exception ex) {
            OBError newError = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
            myError.setError(newError);   
            try {
              releaseRollbackConnection(con);
            } catch (final Exception e) { //do nothing 
            }           
        }
            
        if (myError.isEmpty() && total == 0) {
            OBError newError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=DBExecuteError");
            myError.setError(newError);
        }
        vars.setMessage(tabId, myError);
            
        if(!myError.isEmpty()){
            if(data != null ) {
                if(type == 'I') {            			
                    data.noRolPagoProvisionId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.docaccionnoBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "31D050E5C2D843B99AD7E9470D9E8579", data.getField("Docaccionno"));
                    
                        //BUTTON TEXT FILLING
                    data.postedBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "234", data.getField("Posted"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_ID", data.noRolPagoProvisionId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet RoldePagos9627836015B94CF6ACD14D0E16F4627B. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
