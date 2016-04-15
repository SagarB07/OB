
package org.openbravo.erpWindows.FinancialAccount;




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

public class Depositos1CA823322A874B879DEAA2577476814A extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Depositos1CA823322A874B879DEAA2577476814A.class);
  
  private static final String windowId = "94EAA455D2644E04AB25D93BE5157B6D";
  private static final String tabId = "1CA823322A874B879DEAA2577476814A";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 1;
  private static final String moduleId = "F6F6CB3989A743BFAF62302B20593DD3";
  
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
     
      if (command.contains("15C8708DFC464C2D91286E59624FDD18")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("15C8708DFC464C2D91286E59624FDD18");
        SessionInfo.setModuleId("A918E3331C404B889D69AA9BFAFB23AC");
      }
     
      if (command.contains("6AAF37A95DC9442CA33C8FDD341DB658")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("6AAF37A95DC9442CA33C8FDD341DB658");
        SessionInfo.setModuleId("F6F6CB3989A743BFAF62302B20593DD3");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (securedProcess && command.contains("15C8708DFC464C2D91286E59624FDD18")) {
        classInfo.type = "P";
        classInfo.id = "15C8708DFC464C2D91286E59624FDD18";
      }
     
      if (securedProcess && command.contains("6AAF37A95DC9442CA33C8FDD341DB658")) {
        classInfo.type = "P";
        classInfo.id = "6AAF37A95DC9442CA33C8FDD341DB658";
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
        String strdpFinaccTransactionVId = request.getParameter("inpdpFinaccTransactionVId");
         String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strdpFinaccTransactionVId.equals(""))
              total = saveRecord(vars, myError, 'U', strPFIN_Financial_Account_ID);
          else
              total = saveRecord(vars, myError, 'I', strPFIN_Financial_Account_ID);
          
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
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID", "");

      String strdp_finacc_transaction_v_id = vars.getGlobalVariable("inpdpFinaccTransactionVId", windowId + "|dp_finacc_transaction_v_id", "");
            if (strPFIN_Financial_Account_ID.equals("")) {
        strPFIN_Financial_Account_ID = getParentID(vars, strdp_finacc_transaction_v_id);
        if (strPFIN_Financial_Account_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|FIN_Financial_Account_ID");
        vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);

        refreshParentSession(vars, strPFIN_Financial_Account_ID);
      }


      String strView = vars.getSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strdp_finacc_transaction_v_id.equals("")) strdp_finacc_transaction_v_id = firstElement(vars, tableSQL);
          if (strdp_finacc_transaction_v_id.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strdp_finacc_transaction_v_id, strPFIN_Financial_Account_ID, tableSQL);

      else printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strdp_finacc_transaction_v_id, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strdp_finacc_transaction_v_id = vars.getStringParameter("inpDirectKey");
      
        
      if (strdp_finacc_transaction_v_id.equals("")) strdp_finacc_transaction_v_id = vars.getRequiredGlobalVariable("inpdpFinaccTransactionVId", windowId + "|dp_finacc_transaction_v_id");
      else vars.setSessionValue(windowId + "|dp_finacc_transaction_v_id", strdp_finacc_transaction_v_id);
      
      
      String strPFIN_Financial_Account_ID = getParentID(vars, strdp_finacc_transaction_v_id);
      
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);
      vars.setSessionValue("2845D761A8394468BD3BA4710AA888D4|Account.view", "EDIT");

      refreshParentSession(vars, strPFIN_Financial_Account_ID);

      vars.setSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.view", "EDIT");

      printPageEdit(response, request, vars, false, strdp_finacc_transaction_v_id, strPFIN_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|dp_finacc_transaction_v_id");
      refreshParentSession(vars, strPFIN_Financial_Account_ID);


      String strView = vars.getSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.view");
      String strdp_finacc_transaction_v_id = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strdp_finacc_transaction_v_id = firstElement(vars, tableSQL);
          if (strdp_finacc_transaction_v_id.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strdp_finacc_transaction_v_id.equals("")) strdp_finacc_transaction_v_id = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strdp_finacc_transaction_v_id, strPFIN_Financial_Account_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamFIN_Financial_Account_ID", tabId + "|paramFIN_Financial_Account_ID");
vars.getRequestGlobalVariable("inpParamFIN_Payment_ID", tabId + "|paramFIN_Payment_ID");
vars.getRequestGlobalVariable("inpParamPaymentamt", tabId + "|paramPaymentamt");
vars.getRequestGlobalVariable("inpParamPaymentamt_f", tabId + "|paramPaymentamt_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      
      vars.removeSessionValue(windowId + "|dp_finacc_transaction_v_id");
      String strdp_finacc_transaction_v_id="";

      String strView = vars.getSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strdp_finacc_transaction_v_id = firstElement(vars, tableSQL);
        if (strdp_finacc_transaction_v_id.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strdp_finacc_transaction_v_id, strPFIN_Financial_Account_ID, tableSQL);

      else printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strdp_finacc_transaction_v_id, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      

      String strdp_finacc_transaction_v_id = vars.getGlobalVariable("inpdpFinaccTransactionVId", windowId + "|dp_finacc_transaction_v_id", "");
      vars.setSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.view", "RELATION");
      printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strdp_finacc_transaction_v_id, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");


      printPageEdit(response, request, vars, true, "", strPFIN_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strdp_finacc_transaction_v_id = vars.getGlobalVariable("inpdpFinaccTransactionVId", windowId + "|dp_finacc_transaction_v_id", "");
      vars.setSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strdp_finacc_transaction_v_id, strPFIN_Financial_Account_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      String strdp_finacc_transaction_v_id = vars.getRequiredStringParameter("inpdpFinaccTransactionVId");
      
      String strNext = nextElement(vars, strdp_finacc_transaction_v_id, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      String strdp_finacc_transaction_v_id = vars.getRequiredStringParameter("inpdpFinaccTransactionVId");
      
      String strPrevious = previousElement(vars, strdp_finacc_transaction_v_id, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      vars.setSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|dp_finacc_transaction_v_id");
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|dp_finacc_transaction_v_id");
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPFIN_Financial_Account_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPFIN_Financial_Account_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPFIN_Financial_Account_ID);      
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
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      String strdp_finacc_transaction_v_id = vars.getRequiredGlobalVariable("inpdpFinaccTransactionVId", windowId + "|dp_finacc_transaction_v_id");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPFIN_Financial_Account_ID);      
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
          String strNext = nextElement(vars, strdp_finacc_transaction_v_id, tableSQL);
          vars.setSessionValue(windowId + "|dp_finacc_transaction_v_id", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");

      String strdp_finacc_transaction_v_id = vars.getRequiredStringParameter("inpdpFinaccTransactionVId");
      //Depositos1CA823322A874B879DEAA2577476814AData data = getEditVariables(vars, strPFIN_Financial_Account_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = Depositos1CA823322A874B879DEAA2577476814AData.delete(this, strdp_finacc_transaction_v_id, strPFIN_Financial_Account_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|dpFinaccTransactionVId");
        vars.setSessionValue(tabId + "|Depositos1CA823322A874B879DEAA2577476814A.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

    } else if (vars.commandIn("BUTTONEM_Aprm_Modify15C8708DFC464C2D91286E59624FDD18")) {
        vars.setSessionValue("button15C8708DFC464C2D91286E59624FDD18.stremAprmModify", vars.getStringParameter("inpemAprmModify"));
        vars.setSessionValue("button15C8708DFC464C2D91286E59624FDD18.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button15C8708DFC464C2D91286E59624FDD18.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button15C8708DFC464C2D91286E59624FDD18.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button15C8708DFC464C2D91286E59624FDD18.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "15C8708DFC464C2D91286E59624FDD18", request.getServletPath());
      } else if (vars.commandIn("BUTTON15C8708DFC464C2D91286E59624FDD18")) {
        String strdp_finacc_transaction_v_id = vars.getGlobalVariable("inpdpFinaccTransactionVId", windowId + "|dp_finacc_transaction_v_id", "");
        String stremAprmModify = vars.getSessionValue("button15C8708DFC464C2D91286E59624FDD18.stremAprmModify");
        String strProcessing = vars.getSessionValue("button15C8708DFC464C2D91286E59624FDD18.strProcessing");
        String strOrg = vars.getSessionValue("button15C8708DFC464C2D91286E59624FDD18.strOrg");
        String strClient = vars.getSessionValue("button15C8708DFC464C2D91286E59624FDD18.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Aprm_Modify15C8708DFC464C2D91286E59624FDD18(response, vars, strdp_finacc_transaction_v_id, stremAprmModify, strProcessing);
        }
    } else if (vars.commandIn("BUTTONDelete_Btn6AAF37A95DC9442CA33C8FDD341DB658")) {
        vars.setSessionValue("button6AAF37A95DC9442CA33C8FDD341DB658.strdeleteBtn", vars.getStringParameter("inpdeleteBtn"));
        vars.setSessionValue("button6AAF37A95DC9442CA33C8FDD341DB658.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button6AAF37A95DC9442CA33C8FDD341DB658.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button6AAF37A95DC9442CA33C8FDD341DB658.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button6AAF37A95DC9442CA33C8FDD341DB658.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "6AAF37A95DC9442CA33C8FDD341DB658", request.getServletPath());
      } else if (vars.commandIn("BUTTON6AAF37A95DC9442CA33C8FDD341DB658")) {
        String strdp_finacc_transaction_v_id = vars.getGlobalVariable("inpdpFinaccTransactionVId", windowId + "|dp_finacc_transaction_v_id", "");
        String strdeleteBtn = vars.getSessionValue("button6AAF37A95DC9442CA33C8FDD341DB658.strdeleteBtn");
        String strProcessing = vars.getSessionValue("button6AAF37A95DC9442CA33C8FDD341DB658.strProcessing");
        String strOrg = vars.getSessionValue("button6AAF37A95DC9442CA33C8FDD341DB658.strOrg");
        String strClient = vars.getSessionValue("button6AAF37A95DC9442CA33C8FDD341DB658.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDelete_Btn6AAF37A95DC9442CA33C8FDD341DB658(response, vars, strdp_finacc_transaction_v_id, strdeleteBtn, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONEM_Aprm_Modify15C8708DFC464C2D91286E59624FDD18")) {
        String strdp_finacc_transaction_v_id = vars.getGlobalVariable("inpKey", windowId + "|dp_finacc_transaction_v_id", "");
        
        ProcessBundle pb = new ProcessBundle("15C8708DFC464C2D91286E59624FDD18", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("dp_finacc_transaction_v_id", strdp_finacc_transaction_v_id);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strcGlitemId = vars.getStringParameter("inpcGlitemId");
params.put("cGlitemId", strcGlitemId);
String strmProductId = vars.getStringParameter("inpmProductId");
params.put("mProductId", strmProductId);
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
params.put("cBpartnerId", strcBpartnerId);
String strcProjectId = vars.getStringParameter("inpcProjectId");
params.put("cProjectId", strcProjectId);
String strcCampaignId = vars.getStringParameter("inpcCampaignId");
params.put("cCampaignId", strcCampaignId);
String strcActivityId = vars.getStringParameter("inpcActivityId");
params.put("cActivityId", strcActivityId);
String strcSalesregionId = vars.getStringParameter("inpcSalesregionId");
params.put("cSalesregionId", strcSalesregionId);
String strcCostcenterId = vars.getStringParameter("inpcCostcenterId");
params.put("cCostcenterId", strcCostcenterId);
String struser1Id = vars.getStringParameter("inpuser1Id");
params.put("user1Id", struser1Id);
String struser2Id = vars.getStringParameter("inpuser2Id");
params.put("user2Id", struser2Id);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new ProcessRunner(pb).execute(this);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
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
    } else if (vars.commandIn("SAVE_BUTTONDelete_Btn6AAF37A95DC9442CA33C8FDD341DB658")) {
        String strdp_finacc_transaction_v_id = vars.getGlobalVariable("inpKey", windowId + "|dp_finacc_transaction_v_id", "");
        
        ProcessBundle pb = new ProcessBundle("6AAF37A95DC9442CA33C8FDD341DB658", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("dp_finacc_transaction_v_id", strdp_finacc_transaction_v_id);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new ProcessRunner(pb).execute(this);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
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





    } else if (vars.commandIn("SAVE_XHR")) {
      String strPFIN_Financial_Account_ID = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPFIN_Financial_Account_ID);
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
  private Depositos1CA823322A874B879DEAA2577476814AData getEditVariables(Connection con, VariablesSecureApp vars, String strPFIN_Financial_Account_ID) throws IOException,ServletException {
    Depositos1CA823322A874B879DEAA2577476814AData data = new Depositos1CA823322A874B879DEAA2577476814AData();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");    try {   data.line = vars.getRequiredNumericParameter("inpline");  } catch (ServletException paramEx) { ex = paramEx; }     data.statementdate = vars.getStringParameter("inpstatementdate");     data.emDpDeposito = vars.getStringParameter("inpemDpDeposito");     data.finFinaccTransactionId = vars.getRequiredGlobalVariable("inpfinFinaccTransactionId", windowId + "|FIN_Finacc_Transaction_ID");     data.finPaymentId = vars.getStringParameter("inpfinPaymentId");     data.finPaymentIdr = vars.getStringParameter("inpfinPaymentId_R");     data.emAprmModify = vars.getStringParameter("inpemAprmModify");     data.mProductId = vars.getRequestGlobalVariable("inpmProductId", windowId + "|M_Product_ID");     data.cBpartnerId = vars.getRequestGlobalVariable("inpcBpartnerId", windowId + "|C_Bpartner_ID");     data.status = vars.getRequiredStringParameter("inpstatus");     data.statusr = vars.getStringParameter("inpstatus_R");     data.isactive = vars.getStringParameter("inpisactive", "N");    try {   data.depositamt = vars.getRequiredNumericParameter("inpdepositamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.finFinancialAccountId = vars.getRequiredStringParameter("inpfinFinancialAccountId");    try {   data.paymentamt = vars.getRequiredNumericParameter("inppaymentamt");  } catch (ServletException paramEx) { ex = paramEx; }     data.processed = vars.getStringParameter("inpprocessed", "N");     data.cleared = vars.getStringParameter("inpcleared", "N");     data.processing = vars.getStringParameter("inpprocessing", "N");     data.reconciled = vars.getStringParameter("inpreconciled", "N");     data.user1Id = vars.getStringParameter("inpuser1Id");     data.user2Id = vars.getStringParameter("inpuser2Id");     data.cCurrencyId = vars.getRequiredStringParameter("inpcCurrencyId");     data.cCurrencyIdr = vars.getStringParameter("inpcCurrencyId_R");     data.cActivityId = vars.getRequestGlobalVariable("inpcActivityId", windowId + "|C_Activity_ID");     data.trxtype = vars.getStringParameter("inptrxtype");     data.trxtyper = vars.getStringParameter("inptrxtype_R");     data.description = vars.getStringParameter("inpdescription");     data.cCampaignId = vars.getRequestGlobalVariable("inpcCampaignId", windowId + "|C_Campaign_ID");     data.cProjectId = vars.getRequestGlobalVariable("inpcProjectId", windowId + "|C_Project_ID");     data.cGlitemId = vars.getRequestGlobalVariable("inpcGlitemId", windowId + "|C_Glitem_ID");     data.cGlitemIdr = vars.getStringParameter("inpcGlitemId_R");     data.dateacct = vars.getStringParameter("inpdateacct");    try {   data.foreignAmount = vars.getNumericParameter("inpforeignAmount");  } catch (ServletException paramEx) { ex = paramEx; }     data.finReconciliationId = vars.getStringParameter("inpfinReconciliationId");     data.finReconciliationIdr = vars.getStringParameter("inpfinReconciliationId_R");    try {   data.foreignConvertRate = vars.getNumericParameter("inpforeignConvertRate");  } catch (ServletException paramEx) { ex = paramEx; }     data.createdbyalgorithm = vars.getStringParameter("inpcreatedbyalgorithm", "N");     data.deleteBtn = vars.getStringParameter("inpdeleteBtn");     data.foreignCurrencyId = vars.getStringParameter("inpforeignCurrencyId");     data.cSalesregionId = vars.getRequestGlobalVariable("inpcSalesregionId", windowId + "|C_Salesregion_ID");     data.paymentdocno = vars.getStringParameter("inppaymentdocno");     data.forcedTableId = vars.getRequestGlobalVariable("inpforcedTableId", windowId + "|Forced_Table_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.dpFinaccTransactionVId = vars.getRequestGlobalVariable("inpdpFinaccTransactionVId", windowId + "|dp_finacc_transaction_v_id"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.finFinancialAccountId = vars.getGlobalVariable("inpfinFinancialAccountId", windowId + "|FIN_Financial_Account_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPFIN_Financial_Account_ID) throws IOException,ServletException {
      
      AccountData[] data = AccountData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFIN_Financial_Account_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|Fin_Financial_Account_ID", data[0].finFinancialAccountId);
      vars.setSessionValue(windowId + "|FIN_Financial_Account_ID", strPFIN_Financial_Account_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
      vars.setSessionValue(windowId + "|HasTransaction", AccountData.selectAuxA72A59A036BB47B09105AC5C3361C99C(this, strPFIN_Financial_Account_ID));
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strdp_finacc_transaction_v_id) throws ServletException {
    String strPFIN_Financial_Account_ID = Depositos1CA823322A874B879DEAA2577476814AData.selectParentID(this, strdp_finacc_transaction_v_id);
    if (strPFIN_Financial_Account_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strdp_finacc_transaction_v_id);
      throw new ServletException("Parent record not found");
    }
    return strPFIN_Financial_Account_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|M_Product_ID", data[0].getField("mProductId"));    vars.setSessionValue(windowId + "|Fin_Finacc_Transaction_ID", data[0].getField("finFinaccTransactionId"));    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].getField("cBpartnerId"));    vars.setSessionValue(windowId + "|C_Activity_ID", data[0].getField("cActivityId"));    vars.setSessionValue(windowId + "|C_Campaign_ID", data[0].getField("cCampaignId"));    vars.setSessionValue(windowId + "|C_Glitem_ID", data[0].getField("cGlitemId"));    vars.setSessionValue(windowId + "|C_Project_ID", data[0].getField("cProjectId"));    vars.setSessionValue(windowId + "|C_SalesRegion_ID", data[0].getField("cSalesregionId"));    vars.setSessionValue(windowId + "|Forced_Table_ID", data[0].getField("forcedTableId"));    vars.setSessionValue(windowId + "|dp_finacc_transaction_v_id", data[0].getField("dpFinaccTransactionVId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPFIN_Financial_Account_ID) throws IOException,ServletException {
      Depositos1CA823322A874B879DEAA2577476814AData[] data = Depositos1CA823322A874B879DEAA2577476814AData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFIN_Financial_Account_ID, vars.getStringParameter("inpdpFinaccTransactionVId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPFIN_Financial_Account_ID, String strdp_finacc_transaction_v_id, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamFIN_Financial_Account_ID = vars.getSessionValue(tabId + "|paramFIN_Financial_Account_ID");
String strParamFIN_Payment_ID = vars.getSessionValue(tabId + "|paramFIN_Payment_ID");
String strParamPaymentamt = vars.getSessionValue(tabId + "|paramPaymentamt");
String strParamPaymentamt_f = vars.getSessionValue(tabId + "|paramPaymentamt_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamFIN_Financial_Account_ID) && ("").equals(strParamFIN_Payment_ID) && ("").equals(strParamPaymentamt) && ("").equals(strParamPaymentamt_f)) || !(("").equals(strParamFIN_Financial_Account_ID) || ("%").equals(strParamFIN_Financial_Account_ID))  || !(("").equals(strParamFIN_Payment_ID) || ("%").equals(strParamFIN_Payment_ID))  || !(("").equals(strParamPaymentamt) || ("%").equals(strParamPaymentamt))  || !(("").equals(strParamPaymentamt_f) || ("%").equals(strParamPaymentamt_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strdp_finacc_transaction_v_id.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strdp_finacc_transaction_v_id, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/Depositos1CA823322A874B879DEAA2577476814A_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Depositos1CA823322A874B879DEAA2577476814A", false, "document.frmMain.inpdpFinaccTransactionVId", "grid", "..", "".equals("Y"), "FinancialAccount", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), true, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPFIN_Financial_Account_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("0D7546C1DBB54ACE92824B64A989CDE1", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "dpFinaccTransactionVId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Depositos1CA823322A874B879DEAA2577476814A_Relation.html", "FinancialAccount", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Depositos1CA823322A874B879DEAA2577476814A_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", Depositos1CA823322A874B879DEAA2577476814AData.selectParent(this, strPFIN_Financial_Account_ID));
    else xmlDocument.setParameter("parent", Depositos1CA823322A874B879DEAA2577476814AData.selectParentTrl(this, strPFIN_Financial_Account_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strdp_finacc_transaction_v_id, String strPFIN_Financial_Account_ID, TableSQLData tableSQL)
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
    Depositos1CA823322A874B879DEAA2577476814AData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamFIN_Financial_Account_ID = vars.getSessionValue(tabId + "|paramFIN_Financial_Account_ID");
String strParamFIN_Payment_ID = vars.getSessionValue(tabId + "|paramFIN_Payment_ID");
String strParamPaymentamt = vars.getSessionValue(tabId + "|paramPaymentamt");
String strParamPaymentamt_f = vars.getSessionValue(tabId + "|paramPaymentamt_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamFIN_Financial_Account_ID) && ("").equals(strParamFIN_Payment_ID) && ("").equals(strParamPaymentamt) && ("").equals(strParamPaymentamt_f)) || !(("").equals(strParamFIN_Financial_Account_ID) || ("%").equals(strParamFIN_Financial_Account_ID))  || !(("").equals(strParamFIN_Payment_ID) || ("%").equals(strParamFIN_Payment_ID))  || !(("").equals(strParamPaymentamt) || ("%").equals(strParamPaymentamt))  || !(("").equals(strParamPaymentamt_f) || ("%").equals(strParamPaymentamt_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = Depositos1CA823322A874B879DEAA2577476814AData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFIN_Financial_Account_ID, strdp_finacc_transaction_v_id, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strdp_finacc_transaction_v_id.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (data==null || data.length==0) {
        strdp_finacc_transaction_v_id = firstElement(vars, tableSQL);
        if (strdp_finacc_transaction_v_id.equals("")) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        } else {
          data = Depositos1CA823322A874B879DEAA2577476814AData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPFIN_Financial_Account_ID, strdp_finacc_transaction_v_id, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
        }
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new Depositos1CA823322A874B879DEAA2577476814AData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("dpFinaccTransactionVId") == null || dataField.getField("dpFinaccTransactionVId").equals("")) {
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
        refreshSessionNew(vars, strPFIN_Financial_Account_ID);
        data = Depositos1CA823322A874B879DEAA2577476814AData.set(strPFIN_Financial_Account_ID, Utility.getDefault(this, vars, "Delete_Btn", "", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "FIN_Finacc_Transaction_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Trxtype", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), "", Utility.getDefault(this, vars, "User1_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Depositos1CA823322A874B879DEAA2577476814AData.selectDef1A5CBDE12E734EDCADF7A4BD2CF1AB59(this, strPFIN_Financial_Account_ID), Utility.getDefault(this, vars, "Foreign_Convert_Rate", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Processed", "", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Description", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Cleared", "", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Depositamt", "", "94EAA455D2644E04AB25D93BE5157B6D", "0", dataField), Utility.getDefault(this, vars, "Reconciled", "", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Createdby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Depositos1CA823322A874B879DEAA2577476814AData.selectDef55190A56573F4E78B3C883E11F953DFE_0(this, Utility.getDefault(this, vars, "Createdby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), Utility.getDefault(this, vars, "C_Campaign_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "User2_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Paymentdocno", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Createdbyalgorithm", "", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "C_Glitem_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Currency_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Activity_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Project_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Foreign_Amount", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "C_Salesregion_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "M_Product_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "FIN_Payment_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Paymentamt", "", "94EAA455D2644E04AB25D93BE5157B6D", "0", dataField), Utility.getDefault(this, vars, "Dateacct", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Statementdate", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "EM_Aprm_Modify", "", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), "Y", Utility.getDefault(this, vars, "Processing", "", "94EAA455D2644E04AB25D93BE5157B6D", "N", dataField), Utility.getDefault(this, vars, "Status", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "EM_Dp_Deposito", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Foreign_Currency_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Depositos1CA823322A874B879DEAA2577476814AData.selectDefEFF18BE2A77442C290039A56F8D1A5F2_1(this, Utility.getDefault(this, vars, "Updatedby", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField)), Utility.getDefault(this, vars, "FIN_Reconciliation_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField), Utility.getDefault(this, vars, "Forced_Table_ID", "", "94EAA455D2644E04AB25D93BE5157B6D", "", dataField));
        
      }
     }
      
    String currentPOrg=AccountData.selectOrg(this, strPFIN_Financial_Account_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/Depositos1CA823322A874B879DEAA2577476814A_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/FinancialAccount/Depositos1CA823322A874B879DEAA2577476814A_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Depositos1CA823322A874B879DEAA2577476814A", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpdpFinaccTransactionVId", "", "..", "".equals("Y"), "FinancialAccount", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strdp_finacc_transaction_v_id), !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    toolbar.setDeleteable(true);
    toolbar.prepareEditionTemplate("N".equals("Y"), hasSearchCondition, vars.getSessionValue("#ShowTest", "N").equals("Y"), "SR", Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
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
      // if (!strdp_finacc_transaction_v_id.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Depositos1CA823322A874B879DEAA2577476814A_Relation.html", "FinancialAccount", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Depositos1CA823322A874B879DEAA2577476814A_Relation.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.editionTemplate(strCommand.equals("NEW")));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
		
    
    xmlDocument.setParameter("parentOrg", currentPOrg);
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
xmlDocument.setParameter("Statementdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("EM_Aprm_Modify_BTNname", Utility.getButtonName(this, vars, "CD2BBEC9D14F4792A0AC97A1085CDAAC", "EM_Aprm_Modify_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalEM_Aprm_Modify = org.openbravo.erpCommon.utility.Utility.isModalProcess("15C8708DFC464C2D91286E59624FDD18"); 
xmlDocument.setParameter("EM_Aprm_Modify_Modal", modalEM_Aprm_Modify?"true":"false");
comboTableData = new ComboTableData(vars, this, "19", "FIN_Payment_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finPaymentId"):dataField.getField("finPaymentId")));
xmlDocument.setData("reportFIN_Payment_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Status", "575BCB88A4694C27BC013DE9C73E6FE7", "7A86F689FB1C46F19DBE338D6DECD703", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("status"):dataField.getField("status")));
xmlDocument.setData("reportStatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonDepositamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonPaymentamt", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "C_Currency_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cCurrencyId"):dataField.getField("cCurrencyId")));
xmlDocument.setData("reportC_Currency_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "17", "Trxtype", "4EFC9773F30B4ACE97D225BD13CFF8CB", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("trxtype"):dataField.getField("trxtype")));
xmlDocument.setData("reportTrxtype","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
comboTableData = new ComboTableData(vars, this, "19", "C_Glitem_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cGlitemId"):dataField.getField("cGlitemId")));
xmlDocument.setData("reportC_Glitem_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Dateacct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonForeign_Amount", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("buttonForeign_Convert_Rate", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "19", "FIN_Reconciliation_ID", "", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("finReconciliationId"):dataField.getField("finReconciliationId")));
xmlDocument.setData("reportFIN_Reconciliation_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Delete_Btn_BTNname", Utility.getButtonName(this, vars, "B69B23AA55CD46F0B7CF8CB6B60EF7F8", "Delete_Btn_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));
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



    void printPageButtonEM_Aprm_Modify15C8708DFC464C2D91286E59624FDD18(HttpServletResponse response, VariablesSecureApp vars, String strdp_finacc_transaction_v_id, String stremAprmModify, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 15C8708DFC464C2D91286E59624FDD18");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Aprm_Modify15C8708DFC464C2D91286E59624FDD18", discard).createXmlDocument();
      xmlDocument.setParameter("key", strdp_finacc_transaction_v_id);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Depositos1CA823322A874B879DEAA2577476814A_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "15C8708DFC464C2D91286E59624FDD18");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("15C8708DFC464C2D91286E59624FDD18");
        vars.removeMessage("15C8708DFC464C2D91286E59624FDD18");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("C_GLItem_ID", Depositos1CA823322A874B879DEAA2577476814AData.selectActP15C8708DFC464C2D91286E59624FDD18_C_GLItem_ID(this, Utility.getContext(this, vars, "C_GLItem_ID", "94EAA455D2644E04AB25D93BE5157B6D")));
    xmlDocument.setParameter("M_Product_ID", Utility.getContext(this, vars, "M_Product_ID", "94EAA455D2644E04AB25D93BE5157B6D"));
    xmlDocument.setParameter("C_BPartner_ID", Utility.getContext(this, vars, "C_Bpartner_ID", "94EAA455D2644E04AB25D93BE5157B6D"));
    xmlDocument.setParameter("C_Project_ID", Utility.getContext(this, vars, "C_Project_ID", "94EAA455D2644E04AB25D93BE5157B6D"));
    xmlDocument.setParameter("C_Campaign_ID", Utility.getContext(this, vars, "C_Campaign_ID", "94EAA455D2644E04AB25D93BE5157B6D"));
    xmlDocument.setParameter("C_Activity_ID", Utility.getContext(this, vars, "C_Activity_ID", "94EAA455D2644E04AB25D93BE5157B6D"));
    xmlDocument.setParameter("C_SalesRegion_ID", Utility.getContext(this, vars, "C_Salesregion_ID", "94EAA455D2644E04AB25D93BE5157B6D"));
    xmlDocument.setParameter("C_Costcenter_ID", Utility.getContext(this, vars, "C_Costcenter_ID", "94EAA455D2644E04AB25D93BE5157B6D"));
    xmlDocument.setParameter("User1_ID", Utility.getContext(this, vars, "User1_ID", "94EAA455D2644E04AB25D93BE5157B6D"));
    xmlDocument.setParameter("User2_ID", Utility.getContext(this, vars, "User2_ID", "94EAA455D2644E04AB25D93BE5157B6D"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonDelete_Btn6AAF37A95DC9442CA33C8FDD341DB658(HttpServletResponse response, VariablesSecureApp vars, String strdp_finacc_transaction_v_id, String strdeleteBtn, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6AAF37A95DC9442CA33C8FDD341DB658");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Delete_Btn6AAF37A95DC9442CA33C8FDD341DB658", discard).createXmlDocument();
      xmlDocument.setParameter("key", strdp_finacc_transaction_v_id);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Depositos1CA823322A874B879DEAA2577476814A_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "6AAF37A95DC9442CA33C8FDD341DB658");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("6AAF37A95DC9442CA33C8FDD341DB658");
        vars.removeMessage("6AAF37A95DC9442CA33C8FDD341DB658");
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


    private String getDisplayLogicContext(VariablesSecureApp vars, boolean isNew) throws IOException, ServletException {
      log4j.debug("Output: Display logic context fields");
      String result = "var strShowAudit=\"" +(isNew?"N":Utility.getContext(this, vars, "ShowAudit", windowId)) + "\";\n";
      return result;
    }


    private String getReadOnlyLogicContext(VariablesSecureApp vars) throws IOException, ServletException {
      log4j.debug("Output: Read Only logic context fields");
      String result = "";
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPFIN_Financial_Account_ID) throws IOException, ServletException {
    Depositos1CA823322A874B879DEAA2577476814AData data = null;
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
            data = getEditVariables(con, vars, strPFIN_Financial_Account_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.dpFinaccTransactionVId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (Depositos1CA823322A874B879DEAA2577476814AData.getCurrentDBTimestamp(this, data.dpFinaccTransactionVId).equals(
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
                    data.dpFinaccTransactionVId = "";
                }
                else {                    
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|dp_finacc_transaction_v_id", data.dpFinaccTransactionVId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Depositos1CA823322A874B879DEAA2577476814A. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
