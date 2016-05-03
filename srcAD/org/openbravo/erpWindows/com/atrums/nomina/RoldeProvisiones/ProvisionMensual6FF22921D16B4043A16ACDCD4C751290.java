
package org.openbravo.erpWindows.com.atrums.nomina.RoldeProvisiones;


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

public class ProvisionMensual6FF22921D16B4043A16ACDCD4C751290 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.class);
  
  private static final String windowId = "41844ED4F6D346868D4B5D5F1D4AA14D";
  private static final String tabId = "6FF22921D16B4043A16ACDCD4C751290";
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
     
      if (command.contains("D2999B81B4E84547B24948C424867738")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("D2999B81B4E84547B24948C424867738");
        SessionInfo.setModuleId("3F9AFF0D312A4068A3DE78EDF4326B80");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "D2999B81B4E84547B24948C424867738";
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
        String strnoRolProvisionLineMesId = request.getParameter("inpnoRolProvisionLineMesId");
         String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strnoRolProvisionLineMesId.equals(""))
              total = saveRecord(vars, myError, 'U', strPNO_Rol_Pago_Provision_Line_ID);
          else
              total = saveRecord(vars, myError, 'I', strPNO_Rol_Pago_Provision_Line_ID);
          
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
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID", "");

      String strNO_Rol_Provision_Line_Mes_ID = vars.getGlobalVariable("inpnoRolProvisionLineMesId", windowId + "|NO_Rol_Provision_Line_Mes_ID", "");
            if (strPNO_Rol_Pago_Provision_Line_ID.equals("")) {
        strPNO_Rol_Pago_Provision_Line_ID = getParentID(vars, strNO_Rol_Provision_Line_Mes_ID);
        if (strPNO_Rol_Pago_Provision_Line_ID.equals("")) throw new ServletException("Required parameter :" + windowId + "|NO_Rol_Pago_Provision_Line_ID");
        vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_Line_ID", strPNO_Rol_Pago_Provision_Line_ID);
      vars.removeSessionValue(windowId + "|NO_Rol_Pago_Provision_ID");
        refreshParentSession(vars, strPNO_Rol_Pago_Provision_Line_ID);
      }


      String strView = vars.getSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strNO_Rol_Provision_Line_Mes_ID.equals("")) strNO_Rol_Provision_Line_Mes_ID = firstElement(vars, tableSQL);
          if (strNO_Rol_Provision_Line_Mes_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strNO_Rol_Provision_Line_Mes_ID, strPNO_Rol_Pago_Provision_Line_ID, tableSQL);

      else printPageDataSheet(response, vars, strPNO_Rol_Pago_Provision_Line_ID, strNO_Rol_Provision_Line_Mes_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strNO_Rol_Provision_Line_Mes_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strNO_Rol_Provision_Line_Mes_ID.equals("")) strNO_Rol_Provision_Line_Mes_ID = vars.getRequiredGlobalVariable("inpnoRolProvisionLineMesId", windowId + "|NO_Rol_Provision_Line_Mes_ID");
      else vars.setSessionValue(windowId + "|NO_Rol_Provision_Line_Mes_ID", strNO_Rol_Provision_Line_Mes_ID);
      
      
      String strPNO_Rol_Pago_Provision_Line_ID = getParentID(vars, strNO_Rol_Provision_Line_Mes_ID);
      
      vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_Line_ID", strPNO_Rol_Pago_Provision_Line_ID);
      vars.setSessionValue("00E24D4D2F074977B69197B0A12B8189|Linea.view", "EDIT");
      vars.removeSessionValue(windowId + "|NO_Rol_Pago_Provision_ID");
      refreshParentSession(vars, strPNO_Rol_Pago_Provision_Line_ID);

      vars.setSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.view", "EDIT");

      printPageEdit(response, request, vars, false, strNO_Rol_Provision_Line_Mes_ID, strPNO_Rol_Pago_Provision_Line_ID, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID", false, false, true, "");
      vars.removeSessionValue(windowId + "|NO_Rol_Provision_Line_Mes_ID");
      refreshParentSession(vars, strPNO_Rol_Pago_Provision_Line_ID);


      String strView = vars.getSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.view");
      String strNO_Rol_Provision_Line_Mes_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strNO_Rol_Provision_Line_Mes_ID = firstElement(vars, tableSQL);
          if (strNO_Rol_Provision_Line_Mes_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strNO_Rol_Provision_Line_Mes_ID.equals("")) strNO_Rol_Provision_Line_Mes_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strNO_Rol_Provision_Line_Mes_ID, strPNO_Rol_Pago_Provision_Line_ID, tableSQL);

      } else printPageDataSheet(response, vars, strPNO_Rol_Pago_Provision_Line_ID, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamDocumentno", tabId + "|paramDocumentno");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");

      
      vars.removeSessionValue(windowId + "|NO_Rol_Provision_Line_Mes_ID");
      String strNO_Rol_Provision_Line_Mes_ID="";

      String strView = vars.getSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strNO_Rol_Provision_Line_Mes_ID = firstElement(vars, tableSQL);
        if (strNO_Rol_Provision_Line_Mes_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strNO_Rol_Provision_Line_Mes_ID, strPNO_Rol_Pago_Provision_Line_ID, tableSQL);

      else printPageDataSheet(response, vars, strPNO_Rol_Pago_Provision_Line_ID, strNO_Rol_Provision_Line_Mes_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");
      

      String strNO_Rol_Provision_Line_Mes_ID = vars.getGlobalVariable("inpnoRolProvisionLineMesId", windowId + "|NO_Rol_Provision_Line_Mes_ID", "");
      vars.setSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.view", "RELATION");
      printPageDataSheet(response, vars, strPNO_Rol_Pago_Provision_Line_ID, strNO_Rol_Provision_Line_Mes_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");


      printPageEdit(response, request, vars, true, "", strPNO_Rol_Pago_Provision_Line_ID, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strNO_Rol_Provision_Line_Mes_ID = vars.getGlobalVariable("inpnoRolProvisionLineMesId", windowId + "|NO_Rol_Provision_Line_Mes_ID", "");
      vars.setSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strNO_Rol_Provision_Line_Mes_ID, strPNO_Rol_Pago_Provision_Line_ID, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");
      String strNO_Rol_Provision_Line_Mes_ID = vars.getRequiredStringParameter("inpnoRolProvisionLineMesId");
      
      String strNext = nextElement(vars, strNO_Rol_Provision_Line_Mes_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPNO_Rol_Pago_Provision_Line_ID, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");
      String strNO_Rol_Provision_Line_Mes_ID = vars.getRequiredStringParameter("inpnoRolProvisionLineMesId");
      
      String strPrevious = previousElement(vars, strNO_Rol_Provision_Line_Mes_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPNO_Rol_Pago_Provision_Line_ID, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");

      vars.setSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|NO_Rol_Provision_Line_Mes_ID");
      vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_Line_ID", strPNO_Rol_Pago_Provision_Line_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");

      String strInitRecord = vars.getSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|NO_Rol_Provision_Line_Mes_ID");
      vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_Line_ID", strPNO_Rol_Pago_Provision_Line_ID);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPNO_Rol_Pago_Provision_Line_ID, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPNO_Rol_Pago_Provision_Line_ID, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPNO_Rol_Pago_Provision_Line_ID, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPNO_Rol_Pago_Provision_Line_ID);      
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
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");
      String strNO_Rol_Provision_Line_Mes_ID = vars.getRequiredGlobalVariable("inpnoRolProvisionLineMesId", windowId + "|NO_Rol_Provision_Line_Mes_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPNO_Rol_Pago_Provision_Line_ID);      
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
          String strNext = nextElement(vars, strNO_Rol_Provision_Line_Mes_ID, tableSQL);
          vars.setSessionValue(windowId + "|NO_Rol_Provision_Line_Mes_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");

      String strNO_Rol_Provision_Line_Mes_ID = vars.getRequiredStringParameter("inpnoRolProvisionLineMesId");
      //ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data data = getEditVariables(vars, strPNO_Rol_Pago_Provision_Line_ID);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.delete(this, strNO_Rol_Provision_Line_Mes_ID, strPNO_Rol_Pago_Provision_Line_ID, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|noRolProvisionLineMesId");
        vars.setSessionValue(tabId + "|ProvisionMensual6FF22921D16B4043A16ACDCD4C751290.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONDocaccionnoD2999B81B4E84547B24948C424867738")) {
        vars.setSessionValue("buttonD2999B81B4E84547B24948C424867738.strdocaccionno", vars.getStringParameter("inpdocaccionno"));
        vars.setSessionValue("buttonD2999B81B4E84547B24948C424867738.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonD2999B81B4E84547B24948C424867738.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonD2999B81B4E84547B24948C424867738.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("Docaccionno", vars.getStringParameter("inpdocaccionno"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonD2999B81B4E84547B24948C424867738.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "D2999B81B4E84547B24948C424867738", request.getServletPath());    
     } else if (vars.commandIn("BUTTOND2999B81B4E84547B24948C424867738")) {
        String strNO_Rol_Provision_Line_Mes_ID = vars.getGlobalVariable("inpnoRolProvisionLineMesId", windowId + "|NO_Rol_Provision_Line_Mes_ID", "");
        String strdocaccionno = vars.getSessionValue("buttonD2999B81B4E84547B24948C424867738.strdocaccionno");
        String strProcessing = vars.getSessionValue("buttonD2999B81B4E84547B24948C424867738.strProcessing");
        String strOrg = vars.getSessionValue("buttonD2999B81B4E84547B24948C424867738.strOrg");
        String strClient = vars.getSessionValue("buttonD2999B81B4E84547B24948C424867738.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocaccionnoD2999B81B4E84547B24948C424867738(response, vars, strNO_Rol_Provision_Line_Mes_ID, strdocaccionno, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONDocaccionnoD2999B81B4E84547B24948C424867738")) {
        String strNO_Rol_Provision_Line_Mes_ID = vars.getGlobalVariable("inpKey", windowId + "|NO_Rol_Provision_Line_Mes_ID", "");
        @SuppressWarnings("unused")
        String strdocaccionno = vars.getStringParameter("inpdocaccionno");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "D2999B81B4E84547B24948C424867738", (("NO_Rol_Provision_Line_Mes_ID".equalsIgnoreCase("AD_Language"))?"0":strNO_Rol_Provision_Line_Mes_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
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
        String strNO_Rol_Provision_Line_Mes_ID = vars.getGlobalVariable("inpnoRolProvisionLineMesId", windowId + "|NO_Rol_Provision_Line_Mes_ID", "");
        String strTableId = "B39EEBB236864717A9F7F6F4A11DCB9C";
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
          vars.setSessionValue("Posted|key", strNO_Rol_Provision_Line_Mes_ID);
          vars.setSessionValue("Posted|tableId", strTableId);
          vars.setSessionValue("Posted|tabId", tabId);
          vars.setSessionValue("Posted|posted", strPosted);
          vars.setSessionValue("Posted|processId", strProcessId);
          vars.setSessionValue("Posted|path", strDireccion + request.getServletPath());
          vars.setSessionValue("Posted|windowId", windowId);
          vars.setSessionValue("Posted|tabName", "ProvisionMensual6FF22921D16B4043A16ACDCD4C751290");
          response.sendRedirect(strDireccion + "/ad_actionButton/Posted.html");
        }



    } else if (vars.commandIn("SAVE_XHR")) {
      String strPNO_Rol_Pago_Provision_Line_ID = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPNO_Rol_Pago_Provision_Line_ID);
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
  private ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data getEditVariables(Connection con, VariablesSecureApp vars, String strPNO_Rol_Pago_Provision_Line_ID) throws IOException,ServletException {
    ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data data = new ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data();
    ServletException ex = null;
    try {
    data.documentno = vars.getRequiredStringParameter("inpdocumentno");     data.cPeriodId = vars.getRequiredStringParameter("inpcPeriodId");     data.cPeriodIdr = vars.getStringParameter("inpcPeriodId_R");    try {   data.valor = vars.getRequiredNumericParameter("inpvalor");  } catch (ServletException paramEx) { ex = paramEx; }     data.processed = vars.getStringParameter("inpprocessed", "N");     data.docstatus = vars.getRequiredStringParameter("inpdocstatus");     data.docstatusr = vars.getStringParameter("inpdocstatus_R");     data.dateacct = vars.getRequiredStringParameter("inpdateacct");     data.pago = vars.getStringParameter("inppago", "N");     data.cDoctypeId = vars.getRequiredStringParameter("inpcDoctypeId");     data.cDoctypeIdr = vars.getStringParameter("inpcDoctypeId_R");     data.docaccionno = vars.getRequiredGlobalVariable("inpdocaccionno", windowId + "|Docaccionno");     data.processing = vars.getRequiredStringParameter("inpprocessing");     data.posted = vars.getRequiredStringParameter("inpposted");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.inPayment = vars.getStringParameter("inpinPayment", "N");     data.pagado = vars.getStringParameter("inppagado", "N");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID");     data.noRolProvisionLineMesId = vars.getRequestGlobalVariable("inpnoRolProvisionLineMesId", windowId + "|NO_Rol_Provision_Line_Mes_ID");     data.noRolPagoProvisionLineId = vars.getRequiredGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.noRolPagoProvisionLineId = vars.getGlobalVariable("inpnoRolPagoProvisionLineId", windowId + "|NO_Rol_Pago_Provision_Line_ID");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPNO_Rol_Pago_Provision_Line_ID) throws IOException,ServletException {
      
      Linea00E24D4D2F074977B69197B0A12B8189Data[] data = Linea00E24D4D2F074977B69197B0A12B8189Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), vars.getSessionValue(windowId + "|NO_Rol_Pago_Provision_ID"), strPNO_Rol_Pago_Provision_Line_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|Docaccionno", data[0].docaccionno);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_Line_ID", data[0].noRolPagoProvisionLineId);    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);
      vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_Line_ID", strPNO_Rol_Pago_Provision_Line_ID); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strNO_Rol_Provision_Line_Mes_ID) throws ServletException {
    String strPNO_Rol_Pago_Provision_Line_ID = ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.selectParentID(this, strNO_Rol_Provision_Line_Mes_ID);
    if (strPNO_Rol_Pago_Provision_Line_ID.equals("")) {
      log4j.error("Parent record not found for id: " + strNO_Rol_Provision_Line_Mes_ID);
      throw new ServletException("Parent record not found");
    }
    return strPNO_Rol_Pago_Provision_Line_ID;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|Docaccionno", data[0].getField("docaccionno"));    vars.setSessionValue(windowId + "|NO_Rol_Pago_Provision_Line_ID", data[0].getField("noRolPagoProvisionLineId"));    vars.setSessionValue(windowId + "|NO_Rol_Provision_Line_Mes_ID", data[0].getField("noRolProvisionLineMesId"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPNO_Rol_Pago_Provision_Line_ID) throws IOException,ServletException {
      ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data[] data = ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPNO_Rol_Pago_Provision_Line_ID, vars.getStringParameter("inpnoRolProvisionLineMesId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPNO_Rol_Pago_Provision_Line_ID, String strNO_Rol_Provision_Line_Mes_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamDocumentno = vars.getSessionValue(tabId + "|paramDocumentno");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamDocumentno)) || !(("").equals(strParamDocumentno) || ("%").equals(strParamDocumentno)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strNO_Rol_Provision_Line_Mes_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strNO_Rol_Provision_Line_Mes_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/atrums/nomina/RoldeProvisiones/ProvisionMensual6FF22921D16B4043A16ACDCD4C751290_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "ProvisionMensual6FF22921D16B4043A16ACDCD4C751290", false, "document.frmMain.inpnoRolProvisionLineMesId", "grid", "..", "".equals("Y"), "RoldeProvisiones", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPNO_Rol_Pago_Provision_Line_ID);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("08FE15C2093F4E6ABB5662D8B2CFDA84", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "noRolProvisionLineMesId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ProvisionMensual6FF22921D16B4043A16ACDCD4C751290_Relation.html", "RoldeProvisiones", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ProvisionMensual6FF22921D16B4043A16ACDCD4C751290_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.selectParent(this, strPNO_Rol_Pago_Provision_Line_ID));
    else xmlDocument.setParameter("parent", ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.selectParentTrl(this, strPNO_Rol_Pago_Provision_Line_ID));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strNO_Rol_Provision_Line_Mes_ID, String strPNO_Rol_Pago_Provision_Line_ID, TableSQLData tableSQL)
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
    ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data[] data=null;
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
        data = ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPNO_Rol_Pago_Provision_Line_ID, strNO_Rol_Provision_Line_Mes_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strNO_Rol_Provision_Line_Mes_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("noRolProvisionLineMesId") == null || dataField.getField("noRolProvisionLineMesId").equals("")) {
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
        refreshSessionNew(vars, strPNO_Rol_Pago_Provision_Line_ID);
        data = ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.set(strPNO_Rol_Pago_Provision_Line_ID, Utility.getDefault(this, vars, "Docstatus", "BR", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField), Utility.getDefault(this, vars, "Docaccionno", "CO", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "31D050E5C2D843B99AD7E9470D9E8579", Utility.getDefault(this, vars, "Docaccionno", "CO", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "31D050E5C2D843B99AD7E9470D9E8579", Utility.getDefault(this, vars, "Docaccionno", "CO", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField))), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField), Utility.getDefault(this, vars, "Valor", "0", "41844ED4F6D346868D4B5D5F1D4AA14D", "0", dataField), Utility.getDefault(this, vars, "Dateacct", "", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField), Utility.getDefault(this, vars, "IN_Payment", "", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField), Utility.getDefault(this, vars, "Posted", "N", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "234", Utility.getDefault(this, vars, "Posted", "N", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "234", Utility.getDefault(this, vars, "Posted", "N", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField))), Utility.getDefault(this, vars, "Documentno", "", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField), ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.selectDef5BB1F33113644BD8A7AB5D6E0602BEA7_0(this, Utility.getDefault(this, vars, "Createdby", "", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField)), Utility.getDefault(this, vars, "Updatedby", "", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField), ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.selectDef5F8F9A93A9464997BCE42F6C3ACF2319_1(this, Utility.getDefault(this, vars, "Updatedby", "", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField)), Utility.getDefault(this, vars, "Pagado", "", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField), "Y", Utility.getDefault(this, vars, "Processing", "N", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField), ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.selectDef8E284351C635427393E35181D5A89179(this, Utility.getContext(this, vars, "AD_ORG_ID", "41844ED4F6D346868D4B5D5F1D4AA14D"), Utility.getContext(this, vars, "AD_CLIENT_ID", "41844ED4F6D346868D4B5D5F1D4AA14D")), Utility.getDefault(this, vars, "Pago", "N", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField), Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField), Utility.getDefault(this, vars, "Processed", "N", "41844ED4F6D346868D4B5D5F1D4AA14D", "N", dataField), "", Utility.getDefault(this, vars, "C_Doctype_ID", "", "41844ED4F6D346868D4B5D5F1D4AA14D", "", dataField));
        
      }
     }
      
    String currentPOrg=Linea00E24D4D2F074977B69197B0A12B8189Data.selectOrg(this, strPNO_Rol_Pago_Provision_Line_ID);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/atrums/nomina/RoldeProvisiones/ProvisionMensual6FF22921D16B4043A16ACDCD4C751290_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/atrums/nomina/RoldeProvisiones/ProvisionMensual6FF22921D16B4043A16ACDCD4C751290_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "ProvisionMensual6FF22921D16B4043A16ACDCD4C751290", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpnoRolProvisionLineMesId", "", "..", "".equals("Y"), "RoldeProvisiones", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strNO_Rol_Provision_Line_Mes_ID), !hasReadOnlyAccess);
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
      // if (!strNO_Rol_Provision_Line_Mes_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "ProvisionMensual6FF22921D16B4043A16ACDCD4C751290_Relation.html", "RoldeProvisiones", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "ProvisionMensual6FF22921D16B4043A16ACDCD4C751290_Relation.html", strReplaceWith);
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
comboTableData = new ComboTableData(vars, this, "18", "C_Period_ID", "233", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cPeriodId"):dataField.getField("cPeriodId")));
xmlDocument.setData("reportC_Period_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("buttonValor", Utility.messageBD(this, "Calc", vars.getLanguage()));
comboTableData = new ComboTableData(vars, this, "17", "Docstatus", "31D050E5C2D843B99AD7E9470D9E8579", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("docstatus"):dataField.getField("docstatus")));
xmlDocument.setData("reportDocstatus","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Dateacct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
comboTableData = new ComboTableData(vars, this, "18", "C_Doctype_ID", "E1D9BFAEDABA4F81837C68E0F67F6C5E", "", Utility.getReferenceableOrg(vars, (dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId").equals("")?vars.getOrg():data[0].getField("adOrgId"))), Utility.getContext(this, vars, "#User_Client", windowId), 0);
Utility.fillSQLParameters(this, vars, (dataField==null?data[0]:dataField), comboTableData, windowId, (dataField==null?data[0].getField("cDoctypeId"):dataField.getField("cDoctypeId")));
xmlDocument.setData("reportC_Doctype_ID","liststructure", comboTableData.select(!strCommand.equals("NEW")));
comboTableData = null;
xmlDocument.setParameter("Docaccionno_BTNname", Utility.getButtonName(this, vars, "31D050E5C2D843B99AD7E9470D9E8579", (dataField==null?data[0].getField("docaccionno"):dataField.getField("docaccionno")), "Docaccionno_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalDocaccionno = org.openbravo.erpCommon.utility.Utility.isModalProcess("D2999B81B4E84547B24948C424867738"); 
xmlDocument.setParameter("Docaccionno_Modal", modalDocaccionno?"true":"false");
xmlDocument.setParameter("Posted_BTNname", Utility.getButtonName(this, vars, "234", (dataField==null?data[0].getField("posted"):dataField.getField("posted")), "Posted_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalPosted = org.openbravo.erpCommon.utility.Utility.isModalProcess(""); 
xmlDocument.setParameter("Posted_Modal", modalPosted?"true":"false");
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

    private void printPageButtonDocaccionnoD2999B81B4E84547B24948C424867738(HttpServletResponse response, VariablesSecureApp vars, String strNO_Rol_Provision_Line_Mes_ID, String strdocaccionno, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D2999B81B4E84547B24948C424867738");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocaccionnoD2999B81B4E84547B24948C424867738", discard).createXmlDocument();
      xmlDocument.setParameter("key", strNO_Rol_Provision_Line_Mes_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ProvisionMensual6FF22921D16B4043A16ACDCD4C751290_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "D2999B81B4E84547B24948C424867738");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("D2999B81B4E84547B24948C424867738");
        vars.removeMessage("D2999B81B4E84547B24948C424867738");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("docstatus", "");
    comboTableData = new ComboTableData(vars, this, "17", "docstatus", "31D050E5C2D843B99AD7E9470D9E8579", "DEF0F2249CF5430282917AA0F0D6788F", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonD2999B81B4E84547B24948C424867738.originalParams"), comboTableData, windowId, "");
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPNO_Rol_Pago_Provision_Line_ID) throws IOException, ServletException {
    ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data data = null;
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
            data = getEditVariables(con, vars, strPNO_Rol_Pago_Provision_Line_ID);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.noRolProvisionLineMesId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (ProvisionMensual6FF22921D16B4043A16ACDCD4C751290Data.getCurrentDBTimestamp(this, data.noRolProvisionLineMesId).equals(
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
                    data.noRolProvisionLineMesId = "";
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
            vars.setSessionValue(windowId + "|NO_Rol_Provision_Line_Mes_ID", data.noRolProvisionLineMesId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet ProvisionMensual6FF22921D16B4043A16ACDCD4C751290. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
