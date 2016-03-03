
package org.openbravo.erpWindows.SalesInvoice;


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

public class RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.class);
  
  private static final String windowId = "167";
  private static final String tabId = "FB6C2A2E18604360A5A3E02D9834D343";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "5471BF586FB8424DB4B3F3374F166235";
  
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
     
      if (command.contains("0DECE2AFEFAB49B1B25D8909A8A3B356")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("0DECE2AFEFAB49B1B25D8909A8A3B356");
        SessionInfo.setModuleId("5471BF586FB8424DB4B3F3374F166235");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "0DECE2AFEFAB49B1B25D8909A8A3B356";
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
        String strcoRetencionVentaId = request.getParameter("inpcoRetencionVentaId");
         String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strcoRetencionVentaId.equals(""))
              total = saveRecord(vars, myError, 'U', strPC_Invoice_ID);
          else
              total = saveRecord(vars, myError, 'I', strPC_Invoice_ID);
          
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
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", "");

      String strCO_Retencion_Venta_ID = vars.getGlobalVariable("inpcoRetencionVentaId", windowId + "|CO_Retencion_Venta_ID", "");
            if (strPC_Invoice_ID.equals("")) {
        strPC_Invoice_ID = getParentID(vars, strCO_Retencion_Venta_ID);
        if (strPC_Invoice_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|C_Invoice_ID");
        vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID);

        refreshParentSession(vars, strPC_Invoice_ID);
      }


      String strView = vars.getSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strCO_Retencion_Venta_ID.equals("")) strCO_Retencion_Venta_ID = firstElement(vars, tableSQL);
          if (strCO_Retencion_Venta_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strCO_Retencion_Venta_ID, strPC_Invoice_ID, tableSQL);

      else printPageDataSheet(response, vars, strPC_Invoice_ID, strCO_Retencion_Venta_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strCO_Retencion_Venta_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strCO_Retencion_Venta_ID.equals("")) strCO_Retencion_Venta_ID = vars.getRequiredGlobalVariable("inpcoRetencionVentaId", windowId + "|CO_Retencion_Venta_ID");
      else vars.setSessionValue(windowId + "|CO_Retencion_Venta_ID", strCO_Retencion_Venta_ID);
      
      
      String strPC_Invoice_ID = getParentID(vars, strCO_Retencion_Venta_ID);
      
      vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID);
      vars.setSessionValue("263|Header.view", "EDIT");

      refreshParentSession(vars, strPC_Invoice_ID);

      vars.setSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.view", "EDIT");

      printPageEdit(response, request, vars, false, strCO_Retencion_Venta_ID, strPC_Invoice_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|CO_Retencion_Venta_ID");
      refreshParentSession(vars, strPC_Invoice_ID);


      String strView = vars.getSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.view");
      String strCO_Retencion_Venta_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strCO_Retencion_Venta_ID = firstElement(vars, tableSQL);
          if (strCO_Retencion_Venta_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strCO_Retencion_Venta_ID.equals("")) strCO_Retencion_Venta_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strCO_Retencion_Venta_ID, strPC_Invoice_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPC_Invoice_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentNo", tabId + "|paramDocumentNo");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      
      vars.removeSessionValue(windowId + "|CO_Retencion_Venta_ID");
      String strCO_Retencion_Venta_ID="";

      String strView = vars.getSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strCO_Retencion_Venta_ID = firstElement(vars, tableSQL);
        if (strCO_Retencion_Venta_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strCO_Retencion_Venta_ID, strPC_Invoice_ID, tableSQL);

      else printPageDataSheet(response, vars, strPC_Invoice_ID, strCO_Retencion_Venta_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      

      String strCO_Retencion_Venta_ID = vars.getGlobalVariable("inpcoRetencionVentaId", windowId + "|CO_Retencion_Venta_ID", "");
      vars.setSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.view", "RELATION");
      printPageDataSheet(response, vars, strPC_Invoice_ID, strCO_Retencion_Venta_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");


      printPageEdit(response, request, vars, true, "", strPC_Invoice_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strCO_Retencion_Venta_ID = vars.getGlobalVariable("inpcoRetencionVentaId", windowId + "|CO_Retencion_Venta_ID", "");
      vars.setSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strCO_Retencion_Venta_ID, strPC_Invoice_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      String strCO_Retencion_Venta_ID = vars.getRequiredStringParameter("inpcoRetencionVentaId");
      
      String strNext = nextElement(vars, strCO_Retencion_Venta_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      String strCO_Retencion_Venta_ID = vars.getRequiredStringParameter("inpcoRetencionVentaId");
      
      String strPrevious = previousElement(vars, strCO_Retencion_Venta_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      vars.setSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|CO_Retencion_Venta_ID");
      vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|CO_Retencion_Venta_ID");
      vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPC_Invoice_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPC_Invoice_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPC_Invoice_ID);      
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
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      String strCO_Retencion_Venta_ID = vars.getRequiredGlobalVariable("inpcoRetencionVentaId", windowId + "|CO_Retencion_Venta_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPC_Invoice_ID);      
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
          String strNext = nextElement(vars, strCO_Retencion_Venta_ID, tableSQL);
          vars.setSessionValue(windowId + "|CO_Retencion_Venta_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");

      String strCO_Retencion_Venta_ID = vars.getRequiredStringParameter("inpcoRetencionVentaId");
      //RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data data = getEditVariables(vars, strPC_Invoice_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.delete(this, strCO_Retencion_Venta_ID, strPC_Invoice_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|coRetencionVentaId");
        vars.setSessionValue(tabId + "|RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONDocactionre0DECE2AFEFAB49B1B25D8909A8A3B356")) {
        vars.setSessionValue("button0DECE2AFEFAB49B1B25D8909A8A3B356.strdocactionre", vars.getStringParameter("inpdocactionre"));
        vars.setSessionValue("button0DECE2AFEFAB49B1B25D8909A8A3B356.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button0DECE2AFEFAB49B1B25D8909A8A3B356.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button0DECE2AFEFAB49B1B25D8909A8A3B356.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("Docactionre", vars.getStringParameter("inpdocactionre"));
p.put("Docactionre", vars.getStringParameter("inpdocactionre"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button0DECE2AFEFAB49B1B25D8909A8A3B356.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "0DECE2AFEFAB49B1B25D8909A8A3B356", request.getServletPath());    
     } else if (vars.commandIn("BUTTON0DECE2AFEFAB49B1B25D8909A8A3B356")) {
        String strCO_Retencion_Venta_ID = vars.getGlobalVariable("inpcoRetencionVentaId", windowId + "|CO_Retencion_Venta_ID", "");
        String strdocactionre = vars.getSessionValue("button0DECE2AFEFAB49B1B25D8909A8A3B356.strdocactionre");
        String strProcessing = vars.getSessionValue("button0DECE2AFEFAB49B1B25D8909A8A3B356.strProcessing");
        String strOrg = vars.getSessionValue("button0DECE2AFEFAB49B1B25D8909A8A3B356.strOrg");
        String strClient = vars.getSessionValue("button0DECE2AFEFAB49B1B25D8909A8A3B356.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocactionre0DECE2AFEFAB49B1B25D8909A8A3B356(response, vars, strCO_Retencion_Venta_ID, strdocactionre, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONDocactionre0DECE2AFEFAB49B1B25D8909A8A3B356")) {
        String strCO_Retencion_Venta_ID = vars.getGlobalVariable("inpKey", windowId + "|CO_Retencion_Venta_ID", "");
        @SuppressWarnings("unused")
        String strdocactionre = vars.getStringParameter("inpdocactionre");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "0DECE2AFEFAB49B1B25D8909A8A3B356", (("CO_Retencion_Venta_ID".equalsIgnoreCase("AD_Language"))?"0":strCO_Retencion_Venta_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          String straccionret = vars.getStringParameter("inpaccionret");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AccionRet", straccionret, vars.getClient(), vars.getOrg(), vars.getUser());

          
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
        String strCO_Retencion_Venta_ID = vars.getGlobalVariable("inpcoRetencionVentaId", windowId + "|CO_Retencion_Venta_ID", "");
        String strTableId = "CC155554B375414AA1B8C94B139F1E12";
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
          vars.setSessionValue("Posted|key", strCO_Retencion_Venta_ID);
          vars.setSessionValue("Posted|tableId", strTableId);
          vars.setSessionValue("Posted|tabId", tabId);
          vars.setSessionValue("Posted|posted", strPosted);
          vars.setSessionValue("Posted|processId", strProcessId);
          vars.setSessionValue("Posted|path", strDireccion + request.getServletPath());
          vars.setSessionValue("Posted|windowId", windowId);
          vars.setSessionValue("Posted|tabName", "RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343");
          response.sendRedirect(strDireccion + "/ad_actionButton/Posted.html");
        }



    } else if (vars.commandIn("SAVE_XHR")) {
      String strPC_Invoice_ID = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPC_Invoice_ID);
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
  private RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data getEditVariables(Connection con, VariablesSecureApp vars, String strPC_Invoice_ID) throws IOException,ServletException {
    RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data data = new RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data();
    ServletException ex = null;
    try {
    data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.coNoEstablecimiento = vars.getStringParameter("inpcoNoEstablecimiento");     data.coPtoEmision = vars.getStringParameter("inpcoPtoEmision");     data.noAutorizacion = vars.getRequiredStringParameter("inpnoAutorizacion");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.fechaEmision = vars.getRequiredStringParameter("inpfechaEmision");     data.tipoComprobanteVenta = vars.getRequiredStringParameter("inptipoComprobanteVenta");     data.tipoComprobanteVentar = vars.getStringParameter("inptipoComprobanteVenta_R");     data.noComprobanteVenta = vars.getRequiredStringParameter("inpnoComprobanteVenta");     data.dateacct = vars.getRequiredStringParameter("inpdateacct");    try {   data.totalRetencion = vars.getRequiredNumericParameter("inptotalRetencion");  } catch (ServletException paramEx) { ex = paramEx; }     data.processed = vars.getStringParameter("inpprocessed", "N");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.posted = vars.getStringParameter("inpposted");     data.docactionre = vars.getRequiredGlobalVariable("inpdocactionre", windowId + "|Docactionre");     data.processing = vars.getStringParameter("inpprocessing");     data.docstatus = vars.getRequiredStringParameter("inpdocstatus");     data.cDoctypetargetId = vars.getStringParameter("inpcDoctypetargetId");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.cInvoiceId = vars.getRequiredGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");     data.coRetencionVentaId = vars.getRequestGlobalVariable("inpcoRetencionVentaId", windowId + "|CO_Retencion_Venta_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.cInvoiceId = vars.getGlobalVariable("inpcInvoiceId", windowId + "|C_Invoice_ID");


    
         if (data.documentno.startsWith("<")) data.documentno = Utility.getDocumentNo(con, this, vars, windowId, "CO_RETENCION_VENTA", data.cDoctypetargetId, data.cDoctypeId, false, true);

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPC_Invoice_ID) throws IOException,ServletException {
      
      HeaderData[] data = HeaderData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_Invoice_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|DateInvoiced", data[0].dateinvoiced);    vars.setSessionValue(windowId + "|C_BPartner_ID", data[0].cBpartnerId);    vars.setSessionValue(windowId + "|DocStatus", data[0].docstatus);    vars.setSessionValue(windowId + "|EM_Atecfe_Docstatus", data[0].emAtecfeDocstatus);    vars.setSessionValue(windowId + "|C_Currency_ID", data[0].cCurrencyId);    vars.setSessionValue(windowId + "|IsPaid", data[0].ispaid);    vars.setSessionValue(windowId + "|Totalpaid", data[0].totalpaid);    vars.setSessionValue(windowId + "|Posted", data[0].posted);    vars.setSessionValue(windowId + "|EM_Atecfe_Docaction", data[0].emAtecfeDocaction);    vars.setSessionValue(windowId + "|M_PriceList_ID", data[0].mPricelistId);    vars.setSessionValue(windowId + "|C_DocType_ID", data[0].cDoctypeId);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|IsSOTrx", data[0].issotrx);    vars.setSessionValue(windowId + "|C_Invoice_ID", data[0].cInvoiceId);
      vars.setSessionValue(windowId + "|C_Invoice_ID", strPC_Invoice_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
      vars.setSessionValue(windowId + "|PromotionsDefined", HeaderData.selectAux1459AA66723E4905BE05C09DC757DA6E(this, ((dataField!=null)?dataField.getField("adClientId"):((data==null || data.length==0)?"":data[0].adClientId))));
      
      vars.setSessionValue(windowId + "|DOCBASETYPE", HeaderData.selectAux7A6DD0A1AF304BE288BBFBE305EA1227(this, ((dataField!=null)?dataField.getField("cDoctypetargetId"):((data==null || data.length==0)?"":data[0].cDoctypetargetId))));
      
      vars.setSessionValue(windowId + "|VoidAutomaticallyCreated", HeaderData.selectAuxB54EEEFACAD4427ABE5F88D0C27524B5(this, strPC_Invoice_ID));
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strCO_Retencion_Venta_ID) throws ServletException {
    String strPC_Invoice_ID = RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectParentID(this, strCO_Retencion_Venta_ID);
    if (strPC_Invoice_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strCO_Retencion_Venta_ID);
      throw new ServletException("Parent record not found");
    }
    return strPC_Invoice_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|Docactionre", data[0].getField("docactionre"));    vars.setSessionValue(windowId + "|CO_Retencion_Venta_ID", data[0].getField("coRetencionVentaId"));    vars.setSessionValue(windowId + "|C_Invoice_ID", data[0].getField("cInvoiceId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPC_Invoice_ID) throws IOException,ServletException {
      RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data[] data = RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_Invoice_ID, vars.getStringParameter("inpcoRetencionVentaId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPC_Invoice_ID, String strCO_Retencion_Venta_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strCO_Retencion_Venta_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strCO_Retencion_Venta_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/SalesInvoice/RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343", false, "document.frmMain.inpcoRetencionVentaId", "grid", "..", "".equals("Y"), "SalesInvoice", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPC_Invoice_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("64AB0FD65AB54117B62F04F50AF9DA4D", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "coRetencionVentaId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343_Relation.html", "SalesInvoice", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectParent(this, strPC_Invoice_ID));
    else xmlDocument.setParameter("parent", RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectParentTrl(this, strPC_Invoice_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strCO_Retencion_Venta_ID, String strPC_Invoice_ID, TableSQLData tableSQL)
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
    RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamDocumentNo = vars.getSessionValue(tabId + "|paramDocumentNo");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentNo)) || !(("").equals(strParamDocumentNo) || ("%").equals(strParamDocumentNo)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPC_Invoice_ID, strCO_Retencion_Venta_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strCO_Retencion_Venta_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("coRetencionVentaId") == null || dataField.getField("coRetencionVentaId").equals("")) {
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
        refreshSessionNew(vars, strPC_Invoice_ID);
        data = RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.set(strPC_Invoice_ID, Utility.getDefault(this, vars, "C_Doctypetarget_ID", "", "167", "", dataField), Utility.getDefault(this, vars, "Processed", "N", "167", "N", dataField), Utility.getDefault(this, vars, "Docstatus", "BR", "167", "", dataField), RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectDef180DACC7A8C64503904FDC79FAEC513E(this, strPC_Invoice_ID), Utility.getDefault(this, vars, "Createdby", "", "167", "", dataField), RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectDef28707085D03D4CA593A06A750114DEEE_0(this, Utility.getDefault(this, vars, "Createdby", "", "167", "", dataField)), Utility.getDefault(this, vars, "Fecha_Emision", "@#Date@", "167", "", dataField), Utility.getDefault(this, vars, "Posted", "N", "167", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "N", "167", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "N", "167", "N", dataField))), Utility.getDefault(this, vars, "Dateacct", "@#Date@", "167", "", dataField), RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectDef399EF229625B47D890FE7831F76DB253(this, Utility.getContext(this, vars, "AD_CLIENT_ID", "167"), strPC_Invoice_ID), Utility.getDefault(this, vars, "Updatedby", "", "167", "", dataField), RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectDef4D49B91918F34C47854FE88F1A8C4FAD_1(this, Utility.getDefault(this, vars, "Updatedby", "", "167", "", dataField)), "Y", Utility.getDefault(this, vars, "DocumentNo", "", "167", "", dataField), Utility.getDefault(this, vars, "Docactionre", "CO", "167", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "CB29EF103ACC49108693B711ACEF6261", Utility.getDefault(this, vars, "Docactionre", "CO", "167", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "CB29EF103ACC49108693B711ACEF6261", Utility.getDefault(this, vars, "Docactionre", "CO", "167", "N", dataField))), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "167", "", dataField), Utility.getDefault(this, vars, "Processing", "", "167", "N", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "167", "", dataField), "", Utility.getDefault(this, vars, "Tipo_Comprobante_Venta", "", "167", "", dataField), RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectDefEAB8C210B6D541E68296959AC3104EE1(this, strPC_Invoice_ID), Utility.getDefault(this, vars, "Total_Retencion", "", "167", "0", dataField), RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.selectDefFA8A7F8EBC774ABFBCF7B50F8719DFA0(this, strPC_Invoice_ID), Utility.getDefault(this, vars, "C_Doctype_ID", "", "167", "", dataField));
             data[0].documentno = "<" + Utility.getDocumentNo( this, vars, windowId, "CO_RETENCION_VENTA", data[0].cDoctypetargetId, data[0].cDoctypeId, false, false) + ">";
      }
     }
      
    String currentPOrg=HeaderData.selectOrg(this, strPC_Invoice_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/SalesInvoice/RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/SalesInvoice/RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpcoRetencionVentaId", "", "..", "".equals("Y"), "SalesInvoice", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strCO_Retencion_Venta_ID), !hasReadOnlyAccess);
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
      // if (!strCO_Retencion_Venta_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343_Relation.html", "SalesInvoice", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "18", "C_Doctype_ID", "22F546D49D3A48E1B2B4F50446A8DE58", "38407E5127C14102907E4838D57933C1", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_Doctype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Fecha_Emision_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "17", "Tipo_Comprobante_Venta", "94DD3D9C266148BEAE4E201BD84F8F76", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("tipoComprobanteVenta"):dataField.getField("tipoComprobanteVenta")));
xmlDocument.setData("reportTipo_Comprobante_Venta","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Dateacct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonTotal_Retencion", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
xmlDocument.setParameter("Docactionre_BTNname", Utility.getButtonName(this, vars, "CB29EF103ACC49108693B711ACEF6261", (dataField==null?data[0].getField("docactionre"):dataField.getField("docactionre")), "Docactionre_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalDocactionre = org.openbravo.erpCommon.utility.Utility.isModalProcess("0DECE2AFEFAB49B1B25D8909A8A3B356"); 
xmlDocument.setParameter("Docactionre_Modal", modalDocactionre?"true":"false");
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

    private void printPageButtonDocactionre0DECE2AFEFAB49B1B25D8909A8A3B356(HttpServletResponse response, VariablesSecureApp vars, String strCO_Retencion_Venta_ID, String strdocactionre, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0DECE2AFEFAB49B1B25D8909A8A3B356");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Docactionre0DECE2AFEFAB49B1B25D8909A8A3B356", discard).createXmlDocument();
      xmlDocument.setParameter("key", strCO_Retencion_Venta_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "0DECE2AFEFAB49B1B25D8909A8A3B356");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("0DECE2AFEFAB49B1B25D8909A8A3B356");
        vars.removeMessage("0DECE2AFEFAB49B1B25D8909A8A3B356");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AccionRet", "");
    comboTableData = new ComboTableData(vars, this, "17", "AccionRet", "CB29EF103ACC49108693B711ACEF6261", "99BB277B6C514B59A24FBD1EF24F6429", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button0DECE2AFEFAB49B1B25D8909A8A3B356.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportAccionRet", "liststructure", comboTableData.select(false));
comboTableData = null;
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPC_Invoice_ID) throws IOException, ServletException {
    RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data data = null;
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
            data = getEditVariables(con, vars, strPC_Invoice_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.coRetencionVentaId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343Data.getCurrentDBTimestamp(this, data.coRetencionVentaId).equals(
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
                    data.coRetencionVentaId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.docactionreBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "CB29EF103ACC49108693B711ACEF6261", data.getField("Docactionre"));
                    
                        //BUTTON TEXT FILLING
                    data.postedBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "234", data.getField("Posted"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|CO_Retencion_Venta_ID", data.coRetencionVentaId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet RetenciondeVentasFB6C2A2E18604360A5A3E02D9834D343. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}