
package org.openbravo.erpWindows.com.atrums.nomina.ContratoEmpleado;


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

public class Vacaciones38D375FC19E343C1AD9A1EC73AB0880E extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static Logger log4j = Logger.getLogger(Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.class);
  
  private static final String windowId = "49994E51AC29466FAEF2F122E3654438";
  private static final String tabId = "38D375FC19E343C1AD9A1EC73AB0880E";
  private static final String defaultTabView = "RELATION";
  private static final int accesslevel = 3;
  private static final String moduleId = "53362B63091C4924B6715BFE5C8A3C0F";
  
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
     
      if (command.contains("3CA9715730B240F0A9BEBE9101F93FF6")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("3CA9715730B240F0A9BEBE9101F93FF6");
        SessionInfo.setModuleId("53362B63091C4924B6715BFE5C8A3C0F");
        if (securedProcess) {
          classInfo.type = "P";
          classInfo.id = "3CA9715730B240F0A9BEBE9101F93FF6";
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
        String strnoVacacionId = request.getParameter("inpnoVacacionId");
         String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");
        if (editableTab) {
          int total = 0;
          
          if(commandType.equalsIgnoreCase("EDIT") && !strnoVacacionId.equals(""))
              total = saveRecord(vars, myError, 'U', strPno_contrato_empleado_id);
          else
              total = saveRecord(vars, myError, 'I', strPno_contrato_empleado_id);
          
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
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id", "");

      String strNO_Vacacion_ID = vars.getGlobalVariable("inpnoVacacionId", windowId + "|NO_Vacacion_ID", "");
            if (strPno_contrato_empleado_id.equals("")) {
        strPno_contrato_empleado_id = getParentID(vars, strNO_Vacacion_ID);
        if (strPno_contrato_empleado_id.equals("")) throw new ServletException("Required parameter :" + windowId + "|no_contrato_empleado_id");
        vars.setSessionValue(windowId + "|no_contrato_empleado_id", strPno_contrato_empleado_id);

        refreshParentSession(vars, strPno_contrato_empleado_id);
      }


      String strView = vars.getSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.view");
      if (strView.equals("")) {
        strView = defaultTabView;

        if (strView.equals("EDIT")) {
          if (strNO_Vacacion_ID.equals("")) strNO_Vacacion_ID = firstElement(vars, tableSQL);
          if (strNO_Vacacion_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strNO_Vacacion_ID, strPno_contrato_empleado_id, tableSQL);

      else printPageDataSheet(response, vars, strPno_contrato_empleado_id, strNO_Vacacion_ID, tableSQL);
    } else if (vars.commandIn("DIRECT")) {
      String strNO_Vacacion_ID = vars.getStringParameter("inpDirectKey");
      
        
      if (strNO_Vacacion_ID.equals("")) strNO_Vacacion_ID = vars.getRequiredGlobalVariable("inpnoVacacionId", windowId + "|NO_Vacacion_ID");
      else vars.setSessionValue(windowId + "|NO_Vacacion_ID", strNO_Vacacion_ID);
      
      
      String strPno_contrato_empleado_id = getParentID(vars, strNO_Vacacion_ID);
      
      vars.setSessionValue(windowId + "|no_contrato_empleado_id", strPno_contrato_empleado_id);
      vars.setSessionValue("0C51ECCEBC5F448FA60FF1A7DE775ED9|Contrato Empleado.view", "EDIT");

      refreshParentSession(vars, strPno_contrato_empleado_id);

      vars.setSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.view", "EDIT");

      printPageEdit(response, request, vars, false, strNO_Vacacion_ID, strPno_contrato_empleado_id, tableSQL);

    } else if (vars.commandIn("TAB")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id", false, false, true, "");
      vars.removeSessionValue(windowId + "|NO_Vacacion_ID");
      refreshParentSession(vars, strPno_contrato_empleado_id);


      String strView = vars.getSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.view");
      String strNO_Vacacion_ID = "";
      if (strView.equals("")) {
        strView = defaultTabView;
        if (strView.equals("EDIT")) {
          strNO_Vacacion_ID = firstElement(vars, tableSQL);
          if (strNO_Vacacion_ID.equals("")) strView = "RELATION";
        }
      }
      if (strView.equals("EDIT")) {

        if (strNO_Vacacion_ID.equals("")) strNO_Vacacion_ID = firstElement(vars, tableSQL);
        printPageEdit(response, request, vars, false, strNO_Vacacion_ID, strPno_contrato_empleado_id, tableSQL);

      } else printPageDataSheet(response, vars, strPno_contrato_empleado_id, "", tableSQL);
    } else if (vars.commandIn("SEARCH")) {
vars.getRequestGlobalVariable("inpParamAnio", tabId + "|paramAnio");
vars.getRequestGlobalVariable("inpParamC_Bpartner_ID", tabId + "|paramC_Bpartner_ID");
vars.getRequestGlobalVariable("inpParamem_ne_fecha_inicio", tabId + "|paramem_ne_fecha_inicio");
vars.getRequestGlobalVariable("inpParamem_ne_fecha_inicio_f", tabId + "|paramem_ne_fecha_inicio_f");

        vars.getRequestGlobalVariable("inpParamUpdated", tabId + "|paramUpdated");
        vars.getRequestGlobalVariable("inpParamUpdatedBy", tabId + "|paramUpdatedBy");
        vars.getRequestGlobalVariable("inpParamCreated", tabId + "|paramCreated");
        vars.getRequestGlobalVariable("inpparamCreatedBy", tabId + "|paramCreatedBy");
            String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");

      
      vars.removeSessionValue(windowId + "|NO_Vacacion_ID");
      String strNO_Vacacion_ID="";

      String strView = vars.getSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.view");
      if (strView.equals("")) strView=defaultTabView;

      if (strView.equals("EDIT")) {
        strNO_Vacacion_ID = firstElement(vars, tableSQL);
        if (strNO_Vacacion_ID.equals("")) {
          // filter returns empty set
          strView = "RELATION";
          // switch to grid permanently until the user changes the view again
          vars.setSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.view", strView);
        }
      }

      if (strView.equals("EDIT")) 

        printPageEdit(response, request, vars, false, strNO_Vacacion_ID, strPno_contrato_empleado_id, tableSQL);

      else printPageDataSheet(response, vars, strPno_contrato_empleado_id, strNO_Vacacion_ID, tableSQL);
    } else if (vars.commandIn("RELATION")) {
            String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");
      

      String strNO_Vacacion_ID = vars.getGlobalVariable("inpnoVacacionId", windowId + "|NO_Vacacion_ID", "");
      vars.setSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.view", "RELATION");
      printPageDataSheet(response, vars, strPno_contrato_empleado_id, strNO_Vacacion_ID, tableSQL);
    } else if (vars.commandIn("NEW")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");


      printPageEdit(response, request, vars, true, "", strPno_contrato_empleado_id, tableSQL);

    } else if (vars.commandIn("EDIT")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");

      @SuppressWarnings("unused") // In Expense Invoice tab this variable is not used, to be fixed
      String strNO_Vacacion_ID = vars.getGlobalVariable("inpnoVacacionId", windowId + "|NO_Vacacion_ID", "");
      vars.setSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.view", "EDIT");

      setHistoryCommand(request, "EDIT");
      printPageEdit(response, request, vars, false, strNO_Vacacion_ID, strPno_contrato_empleado_id, tableSQL);

    } else if (vars.commandIn("NEXT")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");
      String strNO_Vacacion_ID = vars.getRequiredStringParameter("inpnoVacacionId");
      
      String strNext = nextElement(vars, strNO_Vacacion_ID, tableSQL);

      printPageEdit(response, request, vars, false, strNext, strPno_contrato_empleado_id, tableSQL);
    } else if (vars.commandIn("PREVIOUS")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");
      String strNO_Vacacion_ID = vars.getRequiredStringParameter("inpnoVacacionId");
      
      String strPrevious = previousElement(vars, strNO_Vacacion_ID, tableSQL);

      printPageEdit(response, request, vars, false, strPrevious, strPno_contrato_empleado_id, tableSQL);
    } else if (vars.commandIn("FIRST_RELATION")) {
vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");

      vars.setSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.initRecordNumber", "0");
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("PREVIOUS_RELATION")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");

      String strInitRecord = vars.getSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      if (strInitRecord.equals("") || strInitRecord.equals("0")) {
        vars.setSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.initRecordNumber", "0");
      } else {
        int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
        initRecord -= intRecordRange;
        strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
        vars.setSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.initRecordNumber", strInitRecord);
      }
      vars.removeSessionValue(windowId + "|NO_Vacacion_ID");
      vars.setSessionValue(windowId + "|no_contrato_empleado_id", strPno_contrato_empleado_id);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("NEXT_RELATION")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");

      String strInitRecord = vars.getSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.initRecordNumber");
      String strRecordRange = Utility.getContext(this, vars, "#RecordRange", windowId);
      int intRecordRange = strRecordRange.equals("")?0:Integer.parseInt(strRecordRange);
      int initRecord = (strInitRecord.equals("")?0:Integer.parseInt(strInitRecord));
      if (initRecord==0) initRecord=1;
      initRecord += intRecordRange;
      strInitRecord = ((initRecord<0)?"0":Integer.toString(initRecord));
      vars.setSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.initRecordNumber", strInitRecord);
      vars.removeSessionValue(windowId + "|NO_Vacacion_ID");
      vars.setSessionValue(windowId + "|no_contrato_empleado_id", strPno_contrato_empleado_id);
      response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
    } else if (vars.commandIn("FIRST")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");
      
      String strFirst = firstElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strFirst, strPno_contrato_empleado_id, tableSQL);
    } else if (vars.commandIn("LAST_RELATION")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");

      String strLast = lastElement(vars, tableSQL);
      printPageDataSheet(response, vars, strPno_contrato_empleado_id, strLast, tableSQL);
    } else if (vars.commandIn("LAST")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");
      
      String strLast = lastElement(vars, tableSQL);

      printPageEdit(response, request, vars, false, strLast, strPno_contrato_empleado_id, tableSQL);
    } else if (vars.commandIn("SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");
      OBError myError = new OBError();      
      int total = saveRecord(vars, myError, 'I', strPno_contrato_empleado_id);      
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
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");
      String strNO_Vacacion_ID = vars.getRequiredGlobalVariable("inpnoVacacionId", windowId + "|NO_Vacacion_ID");
      OBError myError = new OBError();
      int total = saveRecord(vars, myError, 'U', strPno_contrato_empleado_id);      
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
          String strNext = nextElement(vars, strNO_Vacacion_ID, tableSQL);
          vars.setSessionValue(windowId + "|NO_Vacacion_ID", strNext);
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=EDIT");
        } else response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
      }
    } else if (vars.commandIn("DELETE")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");

      String strNO_Vacacion_ID = vars.getRequiredStringParameter("inpnoVacacionId");
      //Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData data = getEditVariables(vars, strPno_contrato_empleado_id);
      int total = 0;
      OBError myError = null;
      if (org.openbravo.erpCommon.utility.WindowAccessData.hasNotDeleteAccess(this, vars.getRole(), tabId)) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
        vars.setMessage(tabId, myError);
      } else {
        try {
          total = Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.delete(this, strNO_Vacacion_ID, strPno_contrato_empleado_id, Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), Utility.getContext(this, vars, "#User_Org", windowId, accesslevel));
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
        vars.removeSessionValue(windowId + "|noVacacionId");
        vars.setSessionValue(tabId + "|Vacaciones38D375FC19E343C1AD9A1EC73AB0880E.view", "RELATION");
      }
      if (myError==null) {
        myError = Utility.translateError(this, vars, vars.getLanguage(), "@CODE=RowsDeleted");
        myError.setMessage(total + " " + myError.getMessage());
        vars.setMessage(tabId, myError);
      }
      response.sendRedirect(strDireccion + request.getServletPath());

     } else if (vars.commandIn("BUTTONem_ne_procesar3CA9715730B240F0A9BEBE9101F93FF6")) {
        vars.setSessionValue("button3CA9715730B240F0A9BEBE9101F93FF6.stremNeProcesar", vars.getStringParameter("inpemNeProcesar"));
        vars.setSessionValue("button3CA9715730B240F0A9BEBE9101F93FF6.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button3CA9715730B240F0A9BEBE9101F93FF6.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button3CA9715730B240F0A9BEBE9101F93FF6.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("em_ne_procesar", vars.getStringParameter("inpemNeProcesar"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button3CA9715730B240F0A9BEBE9101F93FF6.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "3CA9715730B240F0A9BEBE9101F93FF6", request.getServletPath());    
     } else if (vars.commandIn("BUTTON3CA9715730B240F0A9BEBE9101F93FF6")) {
        String strNO_Vacacion_ID = vars.getGlobalVariable("inpnoVacacionId", windowId + "|NO_Vacacion_ID", "");
        String stremNeProcesar = vars.getSessionValue("button3CA9715730B240F0A9BEBE9101F93FF6.stremNeProcesar");
        String strProcessing = vars.getSessionValue("button3CA9715730B240F0A9BEBE9101F93FF6.strProcessing");
        String strOrg = vars.getSessionValue("button3CA9715730B240F0A9BEBE9101F93FF6.strOrg");
        String strClient = vars.getSessionValue("button3CA9715730B240F0A9BEBE9101F93FF6.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonem_ne_procesar3CA9715730B240F0A9BEBE9101F93FF6(response, vars, strNO_Vacacion_ID, stremNeProcesar, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONem_ne_procesar3CA9715730B240F0A9BEBE9101F93FF6")) {
        String strNO_Vacacion_ID = vars.getGlobalVariable("inpKey", windowId + "|NO_Vacacion_ID", "");
        @SuppressWarnings("unused")
        String stremNeProcesar = vars.getStringParameter("inpemNeProcesar");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "3CA9715730B240F0A9BEBE9101F93FF6", (("NO_Vacacion_ID".equalsIgnoreCase("AD_Language"))?"0":strNO_Vacacion_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          String straccionvacacion = vars.getStringParameter("inpaccionvacacion");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AccionVacacion", straccionvacacion, vars.getClient(), vars.getOrg(), vars.getUser());

          
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






    } else if (vars.commandIn("SAVE_XHR")) {
      String strPno_contrato_empleado_id = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");
      OBError myError = new OBError();
      JSONObject result = new JSONObject();
      String commandType = vars.getStringParameter("inpCommandType");
      char saveType = "NEW".equals(commandType) ? 'I' : 'U';
      try {
        int total = saveRecord(vars, myError, saveType, strPno_contrato_empleado_id);
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
  private Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData getEditVariables(Connection con, VariablesSecureApp vars, String strPno_contrato_empleado_id) throws IOException,ServletException {
    Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData data = new Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData();
    ServletException ex = null;
    try {
   try {   data.emNeLinea = vars.getNumericParameter("inpemNeLinea");  } catch (ServletException paramEx) { ex = paramEx; }     data.emNeFechaInicio = vars.getRequiredStringParameter("inpemNeFechaInicio");     data.emNeFechaFin = vars.getRequiredStringParameter("inpemNeFechaFin");    try {   data.emNeTotalDias = vars.getNumericParameter("inpemNeTotalDias");  } catch (ServletException paramEx) { ex = paramEx; }     data.emNeObservaciones = vars.getStringParameter("inpemNeObservaciones");     data.emNeProcesar = vars.getRequestGlobalVariable("inpemNeProcesar", windowId + "|em_ne_procesar");     data.emNeDocstatus = vars.getRequestGlobalVariable("inpemNeDocstatus", windowId + "|em_ne_docstatus");     data.isactive = vars.getStringParameter("inpisactive", "N");     data.adOrgId = vars.getRequiredGlobalVariable("inpadOrgId", windowId + "|AD_Org_ID");     data.noVacacionId = vars.getRequestGlobalVariable("inpnoVacacionId", windowId + "|NO_Vacacion_ID");     data.noContratoEmpleadoId = vars.getStringParameter("inpnoContratoEmpleadoId");     data.anio = vars.getRequiredStringParameter("inpanio");    try {   data.bonificaciones = vars.getNumericParameter("inpbonificaciones");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.saldo = vars.getNumericParameter("inpsaldo");  } catch (ServletException paramEx) { ex = paramEx; }    try {   data.vacaciones = vars.getNumericParameter("inpvacaciones");  } catch (ServletException paramEx) { ex = paramEx; }     data.cBpartnerId = vars.getStringParameter("inpcBpartnerId");     data.emNeEstado = vars.getRequiredGlobalVariable("inpemNeEstado", windowId + "|em_ne_estado");     data.adClientId = vars.getRequiredGlobalVariable("inpadClientId", windowId + "|AD_Client_ID"); 
      data.createdby = vars.getUser();
      data.updatedby = vars.getUser();
      data.adUserClient = Utility.getContext(this, vars, "#User_Client", windowId, accesslevel);
      data.adOrgClient = Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel);
      data.updatedTimeStamp = vars.getStringParameter("updatedTimestamp");

      data.noContratoEmpleadoId = vars.getGlobalVariable("inpnoContratoEmpleadoId", windowId + "|no_contrato_empleado_id");


    
    

    
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


  private void refreshParentSession(VariablesSecureApp vars, String strPno_contrato_empleado_id) throws IOException,ServletException {
      
      ContratoEmpleado0C51ECCEBC5F448FA60FF1A7DE775ED9Data[] data = ContratoEmpleado0C51ECCEBC5F448FA60FF1A7DE775ED9Data.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPno_contrato_empleado_id, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|docstatus", data[0].docstatus);    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].adOrgId);    vars.setSessionValue(windowId + "|em_ne_is_jornada_parcial", data[0].emNeIsJornadaParcial);    vars.setSessionValue(windowId + "|em_ne_num_horas_parciales", data[0].emNeNumHorasParciales);    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].adClientId);    vars.setSessionValue(windowId + "|NO_Contrato_Empleado_ID", data[0].noContratoEmpleadoId);
      vars.setSessionValue(windowId + "|no_contrato_empleado_id", strPno_contrato_empleado_id); //to ensure key parent is set for EM_* cols

      FieldProvider dataField = null; // Define this so that auxiliar inputs using SQL will work
      
  }
  
  
  private String getParentID(VariablesSecureApp vars, String strNO_Vacacion_ID) throws ServletException {
    String strPno_contrato_empleado_id = Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.selectParentID(this, strNO_Vacacion_ID);
    if (strPno_contrato_empleado_id.equals("")) {
      log4j.error("Parent record not found for id: " + strNO_Vacacion_ID);
      throw new ServletException("Parent record not found");
    }
    return strPno_contrato_empleado_id;
  }

    private void refreshSessionEdit(VariablesSecureApp vars, FieldProvider[] data) {
      if (data==null || data.length==0) return;
          vars.setSessionValue(windowId + "|em_ne_procesar", data[0].getField("emNeProcesar"));    vars.setSessionValue(windowId + "|em_ne_estado", data[0].getField("emNeEstado"));    vars.setSessionValue(windowId + "|AD_Client_ID", data[0].getField("adClientId"));    vars.setSessionValue(windowId + "|em_ne_docstatus", data[0].getField("emNeDocstatus"));    vars.setSessionValue(windowId + "|AD_Org_ID", data[0].getField("adOrgId"));    vars.setSessionValue(windowId + "|NO_Vacacion_ID", data[0].getField("noVacacionId"));
    }

    private void refreshSessionNew(VariablesSecureApp vars, String strPno_contrato_empleado_id) throws IOException,ServletException {
      Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData[] data = Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPno_contrato_empleado_id, vars.getStringParameter("inpnoVacacionId", ""), Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
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

  private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strPno_contrato_empleado_id, String strNO_Vacacion_ID, TableSQLData tableSQL)
    throws IOException, ServletException {
    if (log4j.isDebugEnabled()) log4j.debug("Output: dataSheet");

    String strParamAnio = vars.getSessionValue(tabId + "|paramAnio");
String strParamC_Bpartner_ID = vars.getSessionValue(tabId + "|paramC_Bpartner_ID");
String strParamem_ne_fecha_inicio = vars.getSessionValue(tabId + "|paramem_ne_fecha_inicio");
String strParamem_ne_fecha_inicio_f = vars.getSessionValue(tabId + "|paramem_ne_fecha_inicio_f");

    boolean hasSearchCondition=false;
    vars.removeEditionData(tabId);
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAnio) && ("").equals(strParamC_Bpartner_ID) && ("").equals(strParamem_ne_fecha_inicio) && ("").equals(strParamem_ne_fecha_inicio_f)) || !(("").equals(strParamAnio) || ("%").equals(strParamAnio))  || !(("").equals(strParamC_Bpartner_ID) || ("%").equals(strParamC_Bpartner_ID))  || !(("").equals(strParamem_ne_fecha_inicio) || ("%").equals(strParamem_ne_fecha_inicio))  || !(("").equals(strParamem_ne_fecha_inicio_f) || ("%").equals(strParamem_ne_fecha_inicio_f)) ;
    String strOffset = vars.getSessionValue(tabId + "|offset");
    String selectedRow = "0";
    if (!strNO_Vacacion_ID.equals("")) {
      selectedRow = Integer.toString(getKeyPosition(vars, strNO_Vacacion_ID, tableSQL));
    }

    String[] discard={"isNotFiltered","isNotTest"};
    if (hasSearchCondition) discard[0] = new String("isFiltered");
    if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/atrums/nomina/ContratoEmpleado/Vacaciones38D375FC19E343C1AD9A1EC73AB0880E_Relation", discard).createXmlDocument();

    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    ToolBar toolbar = new ToolBar(this, true, vars.getLanguage(), "Vacaciones38D375FC19E343C1AD9A1EC73AB0880E", false, "document.frmMain.inpnoVacacionId", "grid", "..", "".equals("Y"), "ContratoEmpleado", strReplaceWith, false, false, false, false, !hasReadOnlyAccess);
    toolbar.setTabId(tabId);
    
    toolbar.setDeleteable(true && !hasReadOnlyAccess);
    toolbar.prepareRelationTemplate("N".equals("Y"), hasSearchCondition, !vars.getSessionValue("#ShowTest", "N").equals("Y"), false, Utility.getContext(this, vars, "ShowAudit", windowId).equals("Y"));
    xmlDocument.setParameter("toolbar", toolbar.toString());

    xmlDocument.setParameter("keyParent", strPno_contrato_empleado_id);
    xmlDocument.setParameter("parentFieldName", Utility.getFieldName("F09605EE03714B44B336E5242BB1228D", vars.getLanguage()));


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
    xmlDocument.setParameter("KeyName", "noVacacionId");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("theme", vars.getTheme());
    //xmlDocument.setParameter("buttonReference", Utility.messageBD(this, "Reference", vars.getLanguage()));
    try {
      WindowTabs tabs = new WindowTabs(this, vars, tabId, windowId, false);
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Vacaciones38D375FC19E343C1AD9A1EC73AB0880E_Relation.html", "ContratoEmpleado", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"));
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Vacaciones38D375FC19E343C1AD9A1EC73AB0880E_Relation.html", strReplaceWith);
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
    if (vars.getLanguage().equals("en_US")) xmlDocument.setParameter("parent", Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.selectParent(this, strPno_contrato_empleado_id));
    else xmlDocument.setParameter("parent", Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.selectParentTrl(this, strPno_contrato_empleado_id));

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

  private void printPageEdit(HttpServletResponse response, HttpServletRequest request, VariablesSecureApp vars,boolean boolNew, String strNO_Vacacion_ID, String strPno_contrato_empleado_id, TableSQLData tableSQL)
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
    Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData[] data=null;
    XmlDocument xmlDocument=null;
    FieldProvider dataField = vars.getEditionData(tabId);
    vars.removeEditionData(tabId);
    String strParamAnio = vars.getSessionValue(tabId + "|paramAnio");
String strParamC_Bpartner_ID = vars.getSessionValue(tabId + "|paramC_Bpartner_ID");
String strParamem_ne_fecha_inicio = vars.getSessionValue(tabId + "|paramem_ne_fecha_inicio");
String strParamem_ne_fecha_inicio_f = vars.getSessionValue(tabId + "|paramem_ne_fecha_inicio_f");

    boolean hasSearchCondition=false;
    hasSearchCondition = (tableSQL.hasInternalFilter() && ("").equals(strParamAnio) && ("").equals(strParamC_Bpartner_ID) && ("").equals(strParamem_ne_fecha_inicio) && ("").equals(strParamem_ne_fecha_inicio_f)) || !(("").equals(strParamAnio) || ("%").equals(strParamAnio))  || !(("").equals(strParamC_Bpartner_ID) || ("%").equals(strParamC_Bpartner_ID))  || !(("").equals(strParamem_ne_fecha_inicio) || ("%").equals(strParamem_ne_fecha_inicio))  || !(("").equals(strParamem_ne_fecha_inicio_f) || ("%").equals(strParamem_ne_fecha_inicio_f)) ;

       String strParamSessionDate = vars.getGlobalVariable("inpParamSessionDate", Utility.getTransactionalDate(this, vars, windowId), "");
      String buscador = "";
      String[] discard = {"", "isNotTest"};
      
      if (vars.getSessionValue("#ShowTest", "N").equals("Y")) discard[1] = new String("isTest");
    if (dataField==null) {
      if (!boolNew) {
        discard[0] = new String("newDiscard");
        data = Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.selectEdit(this, vars.getSessionValue("#AD_SqlDateTimeFormat"), vars.getLanguage(), strPno_contrato_empleado_id, strNO_Vacacion_ID, Utility.getContext(this, vars, "#User_Client", windowId), Utility.getContext(this, vars, "#AccessibleOrgTree", windowId, accesslevel));
  
        if (!strNO_Vacacion_ID.equals("") && (data == null || data.length==0)) {
          response.sendRedirect(strDireccion + request.getServletPath() + "?Command=RELATION");
          return;
        }
        refreshSessionEdit(vars, data);
        strCommand = "EDIT";
      }

      if (boolNew || data==null || data.length==0) {
        discard[0] = new String ("editDiscard");
        strCommand = "NEW";
        data = new Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData[0];
      } else {
        discard[0] = new String ("newDiscard");
      }
    } else {
      if (dataField.getField("noVacacionId") == null || dataField.getField("noVacacionId").equals("")) {
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
        refreshSessionNew(vars, strPno_contrato_empleado_id);
        data = Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.set(strPno_contrato_empleado_id, Utility.getDefault(this, vars, "Anio", "", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "Createdby", "", "49994E51AC29466FAEF2F122E3654438", "", dataField), Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.selectDef0909E63254424FA5A6B97C8E9E701409_0(this, Utility.getDefault(this, vars, "Createdby", "", "49994E51AC29466FAEF2F122E3654438", "", dataField)), Utility.getDefault(this, vars, "bonificaciones", "0", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "vacaciones", "15", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "em_ne_docstatus", "BR", "49994E51AC29466FAEF2F122E3654438", "", dataField), Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.selectDef44713C42126B4362A7D9F8040A9124DE(this), Utility.getDefault(this, vars, "em_ne_fecha_inicio", "", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "em_ne_estado", "BR", "49994E51AC29466FAEF2F122E3654438", "", dataField), "Y", "", Utility.getDefault(this, vars, "AD_Client_ID", "@AD_CLIENT_ID@", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "em_ne_fecha_fin", "", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "em_ne_procesar", "PA", "49994E51AC29466FAEF2F122E3654438", "N", dataField), (vars.getLanguage().equals("en_US")?ListData.selectName(this, "D90979EA306F493EBD3EF5D201355C56", Utility.getDefault(this, vars, "em_ne_procesar", "PA", "49994E51AC29466FAEF2F122E3654438", "N", dataField)):ListData.selectNameTrl(this, vars.getLanguage(), "D90979EA306F493EBD3EF5D201355C56", Utility.getDefault(this, vars, "em_ne_procesar", "PA", "49994E51AC29466FAEF2F122E3654438", "N", dataField))), Utility.getDefault(this, vars, "em_ne_observaciones", "", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "saldo", "", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "AD_Org_ID", "@AD_ORG_ID@", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "C_Bpartner_ID", "", "49994E51AC29466FAEF2F122E3654438", "", dataField), Utility.getDefault(this, vars, "Updatedby", "", "49994E51AC29466FAEF2F122E3654438", "", dataField), Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.selectDefDE5682896BC94605947571648B7782BF_1(this, Utility.getDefault(this, vars, "Updatedby", "", "49994E51AC29466FAEF2F122E3654438", "", dataField)), Utility.getDefault(this, vars, "em_ne_total_dias", "", "49994E51AC29466FAEF2F122E3654438", "", dataField));
        
      }
     }
      
    String currentPOrg=ContratoEmpleado0C51ECCEBC5F448FA60FF1A7DE775ED9Data.selectOrg(this, strPno_contrato_empleado_id);
    String currentOrg = (boolNew?"":(dataField!=null?dataField.getField("adOrgId"):data[0].getField("adOrgId")));
    if (!currentOrg.equals("") && !currentOrg.startsWith("'")) currentOrg = "'"+currentOrg+"'";
    String currentClient = (boolNew?"":(dataField!=null?dataField.getField("adClientId"):data[0].getField("adClientId")));
    if (!currentClient.equals("") && !currentClient.startsWith("'")) currentClient = "'"+currentClient+"'";
    
    boolean hasReadOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId);
    boolean editableTab = (!hasReadOnlyAccess && (currentOrg.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),currentOrg)) && (currentClient.equals("") || Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel), currentClient)));
    if (editableTab)
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/atrums/nomina/ContratoEmpleado/Vacaciones38D375FC19E343C1AD9A1EC73AB0880E_Edition",discard).createXmlDocument();
    else
      xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpWindows/com/atrums/nomina/ContratoEmpleado/Vacaciones38D375FC19E343C1AD9A1EC73AB0880E_NonEditable",discard).createXmlDocument();

    xmlDocument.setParameter("tabId", tabId);
    ToolBar toolbar = new ToolBar(this, editableTab, vars.getLanguage(), "Vacaciones38D375FC19E343C1AD9A1EC73AB0880E", (strCommand.equals("NEW") || boolNew || (dataField==null && (data==null || data.length==0))), "document.frmMain.inpnoVacacionId", "", "..", "".equals("Y"), "ContratoEmpleado", strReplaceWith, true, false, false, Utility.hasTabAttachments(this, vars, tabId, strNO_Vacacion_ID), !hasReadOnlyAccess);
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
      // if (!strNO_Vacacion_ID.equals("")) xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  // else xmlDocument.setParameter("childTabContainer", tabs.childTabs(true));
	  xmlDocument.setParameter("childTabContainer", tabs.childTabs(false));
	  String hideBackButton = vars.getGlobalVariable("hideMenu", "#Hide_BackButton", "");
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "Vacaciones38D375FC19E343C1AD9A1EC73AB0880E_Relation.html", "ContratoEmpleado", "W", strReplaceWith, tabs.breadcrumb(), hideBackButton.equals("true"), !concurrentSave);
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "Vacaciones38D375FC19E343C1AD9A1EC73AB0880E_Relation.html", strReplaceWith);
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
xmlDocument.setParameter("buttonem_ne_linea", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("em_ne_fecha_inicio_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("em_ne_fecha_fin_Format", vars.getSessionValue("#AD_SqlDateFormat"));
xmlDocument.setParameter("buttonem_ne_total_dias", Utility.messageBD(this, "Calc", vars.getLanguage()));
xmlDocument.setParameter("em_ne_procesar_BTNname", Utility.getButtonName(this, vars, "D90979EA306F493EBD3EF5D201355C56", (dataField==null?data[0].getField("emNeProcesar"):dataField.getField("emNeProcesar")), "em_ne_procesar_linkBTN", usedButtonShortCuts, reservedButtonShortCuts));boolean modalem_ne_procesar = org.openbravo.erpCommon.utility.Utility.isModalProcess("3CA9715730B240F0A9BEBE9101F93FF6"); 
xmlDocument.setParameter("em_ne_procesar_Modal", modalem_ne_procesar?"true":"false");
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

    private void printPageButtonem_ne_procesar3CA9715730B240F0A9BEBE9101F93FF6(HttpServletResponse response, VariablesSecureApp vars, String strNO_Vacacion_ID, String stremNeProcesar, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3CA9715730B240F0A9BEBE9101F93FF6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/em_ne_procesar3CA9715730B240F0A9BEBE9101F93FF6", discard).createXmlDocument();
      xmlDocument.setParameter("key", strNO_Vacacion_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Vacaciones38D375FC19E343C1AD9A1EC73AB0880E_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "3CA9715730B240F0A9BEBE9101F93FF6");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("3CA9715730B240F0A9BEBE9101F93FF6");
        vars.removeMessage("3CA9715730B240F0A9BEBE9101F93FF6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AccionVacacion", "");
    comboTableData = new ComboTableData(vars, this, "17", "AccionVacacion", "D90979EA306F493EBD3EF5D201355C56", "69D9808AB1164161B5A9B0CB7EFB54EF", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button3CA9715730B240F0A9BEBE9101F93FF6.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportAccionVacacion", "liststructure", comboTableData.select(false));
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
  
  private int saveRecord(VariablesSecureApp vars, OBError myError, char type, String strPno_contrato_empleado_id) throws IOException, ServletException {
    Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData data = null;
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
            data = getEditVariables(con, vars, strPno_contrato_empleado_id);
            data.dateTimeFormat = vars.getSessionValue("#AD_SqlDateTimeFormat");            
            String strSequence = "";
            if(type == 'I') {                
        strSequence = SequenceIdData.getUUID();
                if(log4j.isDebugEnabled()) log4j.debug("Sequence: " + strSequence);
                data.noVacacionId = strSequence;  
            }
            if (Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),data.adClientId)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),data.adOrgId)){
		     if(type == 'I') {
		       total = data.insert(con, this);
		     } else {
		       //Check the version of the record we are saving is the one in DB
		       if (Vacaciones38D375FC19E343C1AD9A1EC73AB0880EData.getCurrentDBTimestamp(this, data.noVacacionId).equals(
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
                    data.noVacacionId = "";
                }
                else {                    
                    
                        //BUTTON TEXT FILLING
                    data.emNeProcesarBtn = ActionButtonDefaultData.getText(this, vars.getLanguage(), "D90979EA306F493EBD3EF5D201355C56", data.getField("em_ne_procesar"));
                    
                }
                vars.setEditionData(tabId, data);
            }            	
        }
        else {
            vars.setSessionValue(windowId + "|NO_Vacacion_ID", data.noVacacionId);
        }
    }
    return total;
  }

  public String getServletInfo() {
    return "Servlet Vacaciones38D375FC19E343C1AD9A1EC73AB0880E. This Servlet was made by Wad constructor";
  } // End of getServletInfo() method
}
