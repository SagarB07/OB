
package org.openbravo.erpCommon.ad_actionButton;


import org.openbravo.erpCommon.utility.*;
import org.openbravo.erpCommon.reference.*;
import org.openbravo.utils.Replace;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessRunner;
import org.openbravo.xmlEngine.XmlDocument;
import org.openbravo.database.SessionInfo;
import org.openbravo.erpCommon.obps.ActivationKey;
import org.openbravo.erpCommon.obps.ActivationKey.FeatureRestriction;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.ui.Process;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ActionButton_Responser extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  protected static final String windowId = "ActionButtonResponser";
  
  public void init (ServletConfig config) {
    super.init(config);
    boolHist = false;
  }
  
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    String strProcessId = getProcessId(vars);

    // set process type and id for audit
    SessionInfo.setProcessType("P");
    SessionInfo.setProcessId(strProcessId);
    SessionInfo.setUserId(vars.getSessionValue("#AD_User_ID"));
    SessionInfo.setSessionId(vars.getSessionValue("#AD_Session_ID"));

    try {
      OBContext.setAdminMode();
      Process process = OBDal.getInstance().get(Process.class, strProcessId);
      if (process != null) {
        SessionInfo.setModuleId(process.getModule().getId());
      }
    } finally {
      OBContext.restorePreviousMode();
    }
    super.service(request, response);
  }

  private String getProcessId(VariablesSecureApp vars) throws ServletException {
    String command = vars.getCommand();
    if (command.equals("DEFAULT")) {
      return vars.getRequiredStringParameter("inpadProcessId");
    } else if (command.startsWith("BUTTON")) {
      return command.substring("BUTTON".length());
    } else if (command.startsWith("FRAMES")) {
      return command.substring("FRAMES".length());
    } else if (command.startsWith("SAVE_BUTTONActionButton")) {
      return command.substring("SAVE_BUTTONActionButton".length());
    }
    return null;
  }

  public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    String strProcessId = getProcessId(vars);

    if (vars.getCommand().startsWith("FRAMES")) {
      printPageFrames(response, vars, strProcessId);
    }
    
    if (!vars.commandIn("DEFAULT")) {
      //Check access
      FeatureRestriction featureRestriction = ActivationKey.getInstance().hasLicenseAccess("P",
          strProcessId);
      if (featureRestriction != FeatureRestriction.NO_RESTRICTION) {
        licenseError("P", strProcessId, featureRestriction, response, request, vars, true);
      }
      if (!hasGeneralAccess(vars, "P", strProcessId)) {
        bdErrorGeneralPopUp(request, response,
            Utility.messageBD(this, "Error", vars.getLanguage()), Utility.messageBD(this,
                "AccessTableNoView", vars.getLanguage()));
      }
    }
    
      
    if (vars.commandIn("DEFAULT")) {
      printPageDefault(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON172")) {
        
        printPageButton172(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800087")) {
        
        printPageButton800087(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON0586F175A22647FAB964D16B85971CA7")) {
        
        printPageButton0586F175A22647FAB964D16B85971CA7(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON524328A1ADF3423F8DD5762469D50641")) {
        
        printPageButton524328A1ADF3423F8DD5762469D50641(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800075")) {
        
        printPageButton800075(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON119")) {
        
        printPageButton119(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON193")) {
        
        printPageButton193(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON185")) {
        
        printPageButton185(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON175")) {
        
        printPageButton175(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800085")) {
        
        printPageButton800085(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800130")) {
        
        printPageButton800130(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800151")) {
        
        printPageButton800151(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800109")) {
        
        printPageButton800109(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF03D64524CD642EFA6DDC11150EDC498")) {
        
        printPageButtonF03D64524CD642EFA6DDC11150EDC498(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2A6B08C238B24CC1A483C84AB02DFBC2")) {
        
        printPageButton2A6B08C238B24CC1A483C84AB02DFBC2(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7CDAA4521FBE43FD84C7E710C91CD024")) {
        
        printPageButton7CDAA4521FBE43FD84C7E710C91CD024(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON96D36A8DA2774B2FA817E017DA865C3B")) {
        
        printPageButton96D36A8DA2774B2FA817E017DA865C3B(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON98221DB2679844F8A8BB657B3E9F6474")) {
        
        printPageButton98221DB2679844F8A8BB657B3E9F6474(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON98C3C5AF4E684414AA9E5072EFB0534D")) {
        
        printPageButton98C3C5AF4E684414AA9E5072EFB0534D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA5EE7B720325485C8CF63DB7871A7101")) {
        
        printPageButtonA5EE7B720325485C8CF63DB7871A7101(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA83AA7DCC6F649ECA0153CA233E0F791")) {
        
        printPageButtonA83AA7DCC6F649ECA0153CA233E0F791(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA8B66D431BCF4382B901F8B216F48840")) {
        
        printPageButtonA8B66D431BCF4382B901F8B216F48840(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND5B93E9290B74555AD68BED6BEE0AC14")) {
        
        printPageButtonD5B93E9290B74555AD68BED6BEE0AC14(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND91416F3FE5B4CF39729BE6E0CC7C81A")) {
        
        printPageButtonD91416F3FE5B4CF39729BE6E0CC7C81A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF43E34AAA4BA4B0A8EB1CFD9C7DE75DF")) {
        
        printPageButtonF43E34AAA4BA4B0A8EB1CFD9C7DE75DF(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7ADEF4948DD34B6DA43F75582C166396")) {
        
        printPageButton7ADEF4948DD34B6DA43F75582C166396(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB670ED126EC0470A966FB31C6EEB8647")) {
        
        printPageButtonB670ED126EC0470A966FB31C6EEB8647(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONBB3C25ED65BC4816A097E6F4E6E179B8")) {
        
        printPageButtonBB3C25ED65BC4816A097E6F4E6E179B8(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB98DC338B0CD46F9B0C1310DE8A92477")) {
        
        printPageButtonB98DC338B0CD46F9B0C1310DE8A92477(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF69FF1B8F38A41D7BFC95CCFDFEDD94A")) {
        
        printPageButtonF69FF1B8F38A41D7BFC95CCFDFEDD94A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800171")) {
        
        printPageButton800171(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND234AE084F7040DCB66E281A4237FF99")) {
        
        printPageButtonD234AE084F7040DCB66E281A4237FF99(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON01FB4F522F9D4AAD9304B0BEDD2F1D56")) {
        
        printPageButton01FB4F522F9D4AAD9304B0BEDD2F1D56(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON12FE28B8C0554C1AB8B5CA3C0BAFC35E")) {
        
        printPageButton12FE28B8C0554C1AB8B5CA3C0BAFC35E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1F14F99937B04350B867CDF15E48BE93")) {
        
        printPageButton1F14F99937B04350B867CDF15E48BE93(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON904509557496454CBD4C00265FBEA190")) {
        
        printPageButton904509557496454CBD4C00265FBEA190(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2B31A05A278740ADA7D50686049E0D34")) {
        
        printPageButton2B31A05A278740ADA7D50686049E0D34(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON38854597C4824AC0A6364248D9C5530C")) {
        
        printPageButton38854597C4824AC0A6364248D9C5530C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON47FE2C3B551047D09A658D56B29AF777")) {
        
        printPageButton47FE2C3B551047D09A658D56B29AF777(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON4E8BB4D0A9304152BBF0D79DDD89FAC3")) {
        
        printPageButton4E8BB4D0A9304152BBF0D79DDD89FAC3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON5BE328C8AB9743B6A8CEACB1ED45CBD3")) {
        
        printPageButton5BE328C8AB9743B6A8CEACB1ED45CBD3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON61B3BD10ED0C4A37950A9269A07AF344")) {
        
        printPageButton61B3BD10ED0C4A37950A9269A07AF344(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6A1843AA112E43F4B6D18D4B18423080")) {
        
        printPageButton6A1843AA112E43F4B6D18D4B18423080(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON85C377C58D754187872EC59811012A71")) {
        
        printPageButton85C377C58D754187872EC59811012A71(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON951CA29589754DBCA996D47C5286EDB5")) {
        
        printPageButton951CA29589754DBCA996D47C5286EDB5(response, vars, strProcessId);

    } else if (vars.commandIn("SAVE_BUTTONActionButton172")) {
       process172(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800087")) {
       process800087(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton0586F175A22647FAB964D16B85971CA7")) {
       process0586F175A22647FAB964D16B85971CA7(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton524328A1ADF3423F8DD5762469D50641")) {
       process524328A1ADF3423F8DD5762469D50641(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800075")) {
       process800075(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton119")) {
       process119(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton193")) {
       process193(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton185")) {
       process185(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton175")) {
       process175(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800085")) {
       process800085(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800130")) {
       process800130(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800151")) {
       process800151(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800109")) {
       process800109(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF03D64524CD642EFA6DDC11150EDC498")) {
       processF03D64524CD642EFA6DDC11150EDC498(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton2A6B08C238B24CC1A483C84AB02DFBC2")) {
       process2A6B08C238B24CC1A483C84AB02DFBC2(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton7CDAA4521FBE43FD84C7E710C91CD024")) {
       process7CDAA4521FBE43FD84C7E710C91CD024(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton96D36A8DA2774B2FA817E017DA865C3B")) {
       process96D36A8DA2774B2FA817E017DA865C3B(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton98221DB2679844F8A8BB657B3E9F6474")) {
       process98221DB2679844F8A8BB657B3E9F6474(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton98C3C5AF4E684414AA9E5072EFB0534D")) {
       process98C3C5AF4E684414AA9E5072EFB0534D(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA5EE7B720325485C8CF63DB7871A7101")) {
       processA5EE7B720325485C8CF63DB7871A7101(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA83AA7DCC6F649ECA0153CA233E0F791")) {
       processA83AA7DCC6F649ECA0153CA233E0F791(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA8B66D431BCF4382B901F8B216F48840")) {
       processA8B66D431BCF4382B901F8B216F48840(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD5B93E9290B74555AD68BED6BEE0AC14")) {
       processD5B93E9290B74555AD68BED6BEE0AC14(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD91416F3FE5B4CF39729BE6E0CC7C81A")) {
       processD91416F3FE5B4CF39729BE6E0CC7C81A(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF43E34AAA4BA4B0A8EB1CFD9C7DE75DF")) {
       processF43E34AAA4BA4B0A8EB1CFD9C7DE75DF(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton7ADEF4948DD34B6DA43F75582C166396")) {
       process7ADEF4948DD34B6DA43F75582C166396(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB670ED126EC0470A966FB31C6EEB8647")) {
       processB670ED126EC0470A966FB31C6EEB8647(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonBB3C25ED65BC4816A097E6F4E6E179B8")) {
       processBB3C25ED65BC4816A097E6F4E6E179B8(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB98DC338B0CD46F9B0C1310DE8A92477")) {
       processB98DC338B0CD46F9B0C1310DE8A92477(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF69FF1B8F38A41D7BFC95CCFDFEDD94A")) {
       processF69FF1B8F38A41D7BFC95CCFDFEDD94A(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800171")) {
       process800171(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD234AE084F7040DCB66E281A4237FF99")) {
       processD234AE084F7040DCB66E281A4237FF99(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton01FB4F522F9D4AAD9304B0BEDD2F1D56")) {
       process01FB4F522F9D4AAD9304B0BEDD2F1D56(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton12FE28B8C0554C1AB8B5CA3C0BAFC35E")) {
       process12FE28B8C0554C1AB8B5CA3C0BAFC35E(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton1F14F99937B04350B867CDF15E48BE93")) {
       process1F14F99937B04350B867CDF15E48BE93(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton904509557496454CBD4C00265FBEA190")) {
       process904509557496454CBD4C00265FBEA190(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton2B31A05A278740ADA7D50686049E0D34")) {
       process2B31A05A278740ADA7D50686049E0D34(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton38854597C4824AC0A6364248D9C5530C")) {
       process38854597C4824AC0A6364248D9C5530C(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton47FE2C3B551047D09A658D56B29AF777")) {
       process47FE2C3B551047D09A658D56B29AF777(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton4E8BB4D0A9304152BBF0D79DDD89FAC3")) {
       process4E8BB4D0A9304152BBF0D79DDD89FAC3(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton5BE328C8AB9743B6A8CEACB1ED45CBD3")) {
       process5BE328C8AB9743B6A8CEACB1ED45CBD3(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton61B3BD10ED0C4A37950A9269A07AF344")) {
       process61B3BD10ED0C4A37950A9269A07AF344(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton6A1843AA112E43F4B6D18D4B18423080")) {
       process6A1843AA112E43F4B6D18D4B18423080(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton85C377C58D754187872EC59811012A71")) {
       process85C377C58D754187872EC59811012A71(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton951CA29589754DBCA996D47C5286EDB5")) {
       process951CA29589754DBCA996D47C5286EDB5(vars, request, response);    

    } else pageErrorPopUp(response);
  }

  void printPageDefault(HttpServletResponse response, VariablesSecureApp vars, String strProcessId) throws IOException, ServletException {
    log4j.debug("Output: Default");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDefault").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
	xmlDocument.setParameter("trlFormType", "PROCESS");
	xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    out.println(xmlDocument.print());
    out.close();
  }
  
  void printPageFrames(HttpServletResponse response, VariablesSecureApp vars, String strProcessId) throws IOException, ServletException {
    log4j.debug("Output: Default");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDefaultFrames").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
    xmlDocument.setParameter("trlFormType", "PROCESS");
    xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    out.println(xmlDocument.print());
    out.close();
  }

    void printPageButton172(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 172");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton172", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("172");
        vars.removeMessage("172");
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
    void printPageButton800087(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800087");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800087", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800087");
        vars.removeMessage("800087");
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
    void printPageButton0586F175A22647FAB964D16B85971CA7(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0586F175A22647FAB964D16B85971CA7");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton0586F175A22647FAB964D16B85971CA7", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("0586F175A22647FAB964D16B85971CA7");
        vars.removeMessage("0586F175A22647FAB964D16B85971CA7");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AccionPedido", "");
    comboTableData = new ComboTableData(vars, this, "17", "AccionPedido", "58F1F6B9031E42958986214558A889C2", "C691E7A463974E77BDB1D927FBCD0443", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAccionPedido", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton524328A1ADF3423F8DD5762469D50641(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 524328A1ADF3423F8DD5762469D50641");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton524328A1ADF3423F8DD5762469D50641", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("524328A1ADF3423F8DD5762469D50641");
        vars.removeMessage("524328A1ADF3423F8DD5762469D50641");
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
    void printPageButton800075(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800075");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800075", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800075");
        vars.removeMessage("800075");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("M_Warehouse_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "M_Warehouse_ID", "197", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_Warehouse_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_BPartner_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_BPartner_ID", "192", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_BPartner_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("ReferenceNo", "");
    xmlDocument.setParameter("DateInvoiced", "");
    xmlDocument.setParameter("DateInvoiced_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton119(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 119");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton119", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("119");
        vars.removeMessage("119");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateInvoiced", "");
    xmlDocument.setParameter("DateInvoiced_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("AD_Org_ID", Utility.getContext(this, vars, "#AD_Org_ID", windowId));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "130", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "#AD_Org_ID", windowId));
    xmlDocument.setData("reportAD_Org_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Order_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Order_ID", "", "134", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Order_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_BPartner_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_BPartner_ID", "", "135", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_BPartner_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("InvoiceToDate", "");
    xmlDocument.setParameter("InvoiceToDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton193(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 193");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton193", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("193");
        vars.removeMessage("193");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateOrdered", "");
    xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("C_BPartner_ID", "");
    xmlDocument.setParameter("C_BPartner_IDR", "");
    xmlDocument.setParameter("Vendor_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "Vendor_ID", "192", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportVendor_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Order_ID", "");
    xmlDocument.setParameter("C_Order_IDR", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton185(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 185");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton185", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("185");
        vars.removeMessage("185");
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
    void printPageButton175(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 175");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton175", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("175");
        vars.removeMessage("175");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Client_ID", Utility.getContext(this, vars, "AD_Client_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "103", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_Client_ID", ""));
    xmlDocument.setData("reportAD_Client_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("AD_Table_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "AD_Table_ID", "800022", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Table_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton800085(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800085");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800085", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800085");
        vars.removeMessage("800085");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("IsIncremental", "N");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton800130(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800130");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800130", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800130");
        vars.removeMessage("800130");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Initdate", DateTimeData.today(this));
    xmlDocument.setParameter("Initdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("EndDate", "");
    xmlDocument.setParameter("EndDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton800151(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800151");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800151", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800151");
        vars.removeMessage("800151");
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
    void printPageButton800109(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800109");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800109", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800109");
        vars.removeMessage("800109");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("C_Budget_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Budget_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Budget_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("MA_Processplan_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "MA_Processplan_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportMA_Processplan_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("CalcDate", "");
    xmlDocument.setParameter("CalcDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF03D64524CD642EFA6DDC11150EDC498(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F03D64524CD642EFA6DDC11150EDC498");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF03D64524CD642EFA6DDC11150EDC498", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F03D64524CD642EFA6DDC11150EDC498");
        vars.removeMessage("F03D64524CD642EFA6DDC11150EDC498");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("c_period_id", ActionButtonSQLDefaultData.selectActPF03D64524CD642EFA6DDC11150EDC498_c_period_id(this, Utility.getContext(this, vars, "AD_ORG_ID", ""), Utility.getContext(this, vars, "AD_CLIENT_ID", "")));
    comboTableData = new ComboTableData(vars, this, "18", "c_period_id", "275", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, ActionButtonSQLDefaultData.selectActPF03D64524CD642EFA6DDC11150EDC498_c_period_id(this, Utility.getContext(this, vars, "AD_ORG_ID", ""), Utility.getContext(this, vars, "AD_CLIENT_ID", "")));
    xmlDocument.setData("reportc_period_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton2A6B08C238B24CC1A483C84AB02DFBC2(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2A6B08C238B24CC1A483C84AB02DFBC2");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2A6B08C238B24CC1A483C84AB02DFBC2", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2A6B08C238B24CC1A483C84AB02DFBC2");
        vars.removeMessage("2A6B08C238B24CC1A483C84AB02DFBC2");
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
    void printPageButton7CDAA4521FBE43FD84C7E710C91CD024(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7CDAA4521FBE43FD84C7E710C91CD024");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7CDAA4521FBE43FD84C7E710C91CD024", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7CDAA4521FBE43FD84C7E710C91CD024");
        vars.removeMessage("7CDAA4521FBE43FD84C7E710C91CD024");
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
    void printPageButton96D36A8DA2774B2FA817E017DA865C3B(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 96D36A8DA2774B2FA817E017DA865C3B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton96D36A8DA2774B2FA817E017DA865C3B", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("96D36A8DA2774B2FA817E017DA865C3B");
        vars.removeMessage("96D36A8DA2774B2FA817E017DA865C3B");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton98221DB2679844F8A8BB657B3E9F6474(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 98221DB2679844F8A8BB657B3E9F6474");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton98221DB2679844F8A8BB657B3E9F6474", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("98221DB2679844F8A8BB657B3E9F6474");
        vars.removeMessage("98221DB2679844F8A8BB657B3E9F6474");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("Bpartner", "");
    xmlDocument.setParameter("BpartnerR", "");
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton98C3C5AF4E684414AA9E5072EFB0534D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 98C3C5AF4E684414AA9E5072EFB0534D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton98C3C5AF4E684414AA9E5072EFB0534D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("98C3C5AF4E684414AA9E5072EFB0534D");
        vars.removeMessage("98C3C5AF4E684414AA9E5072EFB0534D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA5EE7B720325485C8CF63DB7871A7101(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A5EE7B720325485C8CF63DB7871A7101");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA5EE7B720325485C8CF63DB7871A7101", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A5EE7B720325485C8CF63DB7871A7101");
        vars.removeMessage("A5EE7B720325485C8CF63DB7871A7101");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ANIO", "");
    comboTableData = new ComboTableData(vars, this, "18", "ANIO", "A18160D7A6D247C8AF5904F61A8BEEF2", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportANIO", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("VALOR_SALARIO", "");
    xmlDocument.setParameter("outputType", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA83AA7DCC6F649ECA0153CA233E0F791(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A83AA7DCC6F649ECA0153CA233E0F791");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA83AA7DCC6F649ECA0153CA233E0F791", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A83AA7DCC6F649ECA0153CA233E0F791");
        vars.removeMessage("A83AA7DCC6F649ECA0153CA233E0F791");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("CUENTA_BANCO", "");
    comboTableData = new ComboTableData(vars, this, "18", "CUENTA_BANCO", "DF1CEA94B3564A33AFDB37C07E1CE353", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportCUENTA_BANCO", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("FECHA_DESDE", "");
    xmlDocument.setParameter("FECHA_DESDE_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("FECHA_HASTA", "");
    xmlDocument.setParameter("FECHA_HASTA_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA8B66D431BCF4382B901F8B216F48840(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A8B66D431BCF4382B901F8B216F48840");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA8B66D431BCF4382B901F8B216F48840", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A8B66D431BCF4382B901F8B216F48840");
        vars.removeMessage("A8B66D431BCF4382B901F8B216F48840");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("valor_min", "");
    xmlDocument.setParameter("valor_max", "");
    xmlDocument.setParameter("outputtype", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD5B93E9290B74555AD68BED6BEE0AC14(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D5B93E9290B74555AD68BED6BEE0AC14");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD5B93E9290B74555AD68BED6BEE0AC14", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D5B93E9290B74555AD68BED6BEE0AC14");
        vars.removeMessage("D5B93E9290B74555AD68BED6BEE0AC14");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DATE_FROM", "");
    xmlDocument.setParameter("DATE_FROM_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DATE_TO", "");
    xmlDocument.setParameter("DATE_TO_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("CATEGORIA", "");
    comboTableData = new ComboTableData(vars, this, "18", "CATEGORIA", "163", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportCATEGORIA", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("PRODUCTO", "");
    xmlDocument.setParameter("PRODUCTOR", "");
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD91416F3FE5B4CF39729BE6E0CC7C81A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D91416F3FE5B4CF39729BE6E0CC7C81A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD91416F3FE5B4CF39729BE6E0CC7C81A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D91416F3FE5B4CF39729BE6E0CC7C81A");
        vars.removeMessage("D91416F3FE5B4CF39729BE6E0CC7C81A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF43E34AAA4BA4B0A8EB1CFD9C7DE75DF(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F43E34AAA4BA4B0A8EB1CFD9C7DE75DF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF43E34AAA4BA4B0A8EB1CFD9C7DE75DF", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F43E34AAA4BA4B0A8EB1CFD9C7DE75DF");
        vars.removeMessage("F43E34AAA4BA4B0A8EB1CFD9C7DE75DF");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("salesrep_id", "");
    comboTableData = new ComboTableData(vars, this, "18", "salesrep_id", "78DB616C4DC2489EB6FFA164B282C619", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportsalesrep_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Region_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_Region_ID", "C9C4B1804C7A4A2AB624642C4481EFBE", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Region_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Zona_ID", "");
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton7ADEF4948DD34B6DA43F75582C166396(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7ADEF4948DD34B6DA43F75582C166396");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7ADEF4948DD34B6DA43F75582C166396", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7ADEF4948DD34B6DA43F75582C166396");
        vars.removeMessage("7ADEF4948DD34B6DA43F75582C166396");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("year", "");
    comboTableData = new ComboTableData(vars, this, "18", "year", "A18160D7A6D247C8AF5904F61A8BEEF2", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportyear", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("ad_ref", "");
    comboTableData = new ComboTableData(vars, this, "17", "ad_ref", "D782279EDFB144A2914AC7C7C8036C19", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_ref", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_period_id", "");
    comboTableData = new ComboTableData(vars, this, "18", "c_period_id", "275", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportc_period_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB670ED126EC0470A966FB31C6EEB8647(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B670ED126EC0470A966FB31C6EEB8647");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB670ED126EC0470A966FB31C6EEB8647", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B670ED126EC0470A966FB31C6EEB8647");
        vars.removeMessage("B670ED126EC0470A966FB31C6EEB8647");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("c_period_id", ActionButtonSQLDefaultData.selectActPB670ED126EC0470A966FB31C6EEB8647_c_period_id(this, Utility.getContext(this, vars, "AD_ORG_ID", ""), Utility.getContext(this, vars, "AD_CLIENT_ID", "")));
    comboTableData = new ComboTableData(vars, this, "19", "c_period_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, ActionButtonSQLDefaultData.selectActPB670ED126EC0470A966FB31C6EEB8647_c_period_id(this, Utility.getContext(this, vars, "AD_ORG_ID", ""), Utility.getContext(this, vars, "AD_CLIENT_ID", "")));
    xmlDocument.setData("reportc_period_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonBB3C25ED65BC4816A097E6F4E6E179B8(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process BB3C25ED65BC4816A097E6F4E6E179B8");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonBB3C25ED65BC4816A097E6F4E6E179B8", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("BB3C25ED65BC4816A097E6F4E6E179B8");
        vars.removeMessage("BB3C25ED65BC4816A097E6F4E6E179B8");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("c_period_id", ActionButtonSQLDefaultData.selectActPBB3C25ED65BC4816A097E6F4E6E179B8_c_period_id(this, Utility.getContext(this, vars, "AD_ORG_ID", ""), Utility.getContext(this, vars, "AD_CLIENT_ID", "")));
    comboTableData = new ComboTableData(vars, this, "19", "c_period_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, ActionButtonSQLDefaultData.selectActPBB3C25ED65BC4816A097E6F4E6E179B8_c_period_id(this, Utility.getContext(this, vars, "AD_ORG_ID", ""), Utility.getContext(this, vars, "AD_CLIENT_ID", "")));
    xmlDocument.setData("reportc_period_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "B80E53C3F99349B69AE340818093FC5A", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    comboTableData = new ComboTableData(vars, this, "18", "c_bpartner_id", "252", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportc_bpartner_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB98DC338B0CD46F9B0C1310DE8A92477(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B98DC338B0CD46F9B0C1310DE8A92477");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB98DC338B0CD46F9B0C1310DE8A92477", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B98DC338B0CD46F9B0C1310DE8A92477");
        vars.removeMessage("B98DC338B0CD46F9B0C1310DE8A92477");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DATE_FROM", "");
    xmlDocument.setParameter("DATE_FROM_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DATE_TO", "");
    xmlDocument.setParameter("DATE_TO_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF69FF1B8F38A41D7BFC95CCFDFEDD94A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F69FF1B8F38A41D7BFC95CCFDFEDD94A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF69FF1B8F38A41D7BFC95CCFDFEDD94A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F69FF1B8F38A41D7BFC95CCFDFEDD94A");
        vars.removeMessage("F69FF1B8F38A41D7BFC95CCFDFEDD94A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DATE_FROM", "");
    xmlDocument.setParameter("DATE_FROM_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DATE_TO", "");
    xmlDocument.setParameter("DATE_TO_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DOCSTATUS", "");
    comboTableData = new ComboTableData(vars, this, "17", "DOCSTATUS", "8C83DEB7CA664B359FD14679B64236A0", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportDOCSTATUS", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputtype", "");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton800171(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800171");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800171", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800171");
        vars.removeMessage("800171");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("C_BPartner_ID", "");
    xmlDocument.setParameter("C_BPartner_IDR", "");
    xmlDocument.setParameter("C_Currency_ID", Utility.getContext(this, vars, "C_Currency_ID", "") );
    comboTableData = new ComboTableData(vars, this, "19", "C_Currency_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "C_Currency_ID", "") );
    xmlDocument.setData("reportC_Currency_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DateFrom", DateTimeData.today(this));
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("C_Project_ID", "");
    xmlDocument.setParameter("C_Project_IDR", "");
    xmlDocument.setParameter("M_Warehouse_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_Warehouse_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD234AE084F7040DCB66E281A4237FF99(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D234AE084F7040DCB66E281A4237FF99");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD234AE084F7040DCB66E281A4237FF99", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D234AE084F7040DCB66E281A4237FF99");
        vars.removeMessage("D234AE084F7040DCB66E281A4237FF99");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Org_ID", Utility.getContext(this, vars, "AD_Org_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "0C754881EAD94243A161111916E9B9C6", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_Org_ID", ""));
    xmlDocument.setData("reportAD_Org_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_AcctSchema_ID", Utility.getContext(this, vars, "C_AcctSchema_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "C_AcctSchema_ID", ""));
    xmlDocument.setData("reportC_AcctSchema_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_BPartner_ID", Utility.getContext(this, vars, "C_BPartner_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "C_BPartner_ID", "", "95548E7077124EB7A83F85A000CB2350", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "C_BPartner_ID", ""));
    xmlDocument.setData("reportC_BPartner_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton01FB4F522F9D4AAD9304B0BEDD2F1D56(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 01FB4F522F9D4AAD9304B0BEDD2F1D56");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton01FB4F522F9D4AAD9304B0BEDD2F1D56", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("01FB4F522F9D4AAD9304B0BEDD2F1D56");
        vars.removeMessage("01FB4F522F9D4AAD9304B0BEDD2F1D56");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DATE_TO", "");
    xmlDocument.setParameter("DATE_TO_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton12FE28B8C0554C1AB8B5CA3C0BAFC35E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 12FE28B8C0554C1AB8B5CA3C0BAFC35E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton12FE28B8C0554C1AB8B5CA3C0BAFC35E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("12FE28B8C0554C1AB8B5CA3C0BAFC35E");
        vars.removeMessage("12FE28B8C0554C1AB8B5CA3C0BAFC35E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("Bpartner", "");
    xmlDocument.setParameter("BpartnerR", "");
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1F14F99937B04350B867CDF15E48BE93(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1F14F99937B04350B867CDF15E48BE93");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1F14F99937B04350B867CDF15E48BE93", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1F14F99937B04350B867CDF15E48BE93");
        vars.removeMessage("1F14F99937B04350B867CDF15E48BE93");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("Bpartner", "");
    xmlDocument.setParameter("BpartnerR", "");
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton904509557496454CBD4C00265FBEA190(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 904509557496454CBD4C00265FBEA190");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton904509557496454CBD4C00265FBEA190", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("904509557496454CBD4C00265FBEA190");
        vars.removeMessage("904509557496454CBD4C00265FBEA190");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("PERIODO", "");
    comboTableData = new ComboTableData(vars, this, "18", "PERIODO", "275", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportPERIODO", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton2B31A05A278740ADA7D50686049E0D34(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2B31A05A278740ADA7D50686049E0D34");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2B31A05A278740ADA7D50686049E0D34", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2B31A05A278740ADA7D50686049E0D34");
        vars.removeMessage("2B31A05A278740ADA7D50686049E0D34");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DATE_TO", "");
    xmlDocument.setParameter("DATE_TO_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton38854597C4824AC0A6364248D9C5530C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 38854597C4824AC0A6364248D9C5530C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton38854597C4824AC0A6364248D9C5530C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("38854597C4824AC0A6364248D9C5530C");
        vars.removeMessage("38854597C4824AC0A6364248D9C5530C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton47FE2C3B551047D09A658D56B29AF777(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 47FE2C3B551047D09A658D56B29AF777");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton47FE2C3B551047D09A658D56B29AF777", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("47FE2C3B551047D09A658D56B29AF777");
        vars.removeMessage("47FE2C3B551047D09A658D56B29AF777");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DATE_FROM", "");
    xmlDocument.setParameter("DATE_FROM_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DATE_TO", "");
    xmlDocument.setParameter("DATE_TO_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("CATEGORIA", "");
    comboTableData = new ComboTableData(vars, this, "18", "CATEGORIA", "163", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportCATEGORIA", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("PRODUCTO", "");
    xmlDocument.setParameter("PRODUCTOR", "");
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton4E8BB4D0A9304152BBF0D79DDD89FAC3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4E8BB4D0A9304152BBF0D79DDD89FAC3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton4E8BB4D0A9304152BBF0D79DDD89FAC3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("4E8BB4D0A9304152BBF0D79DDD89FAC3");
        vars.removeMessage("4E8BB4D0A9304152BBF0D79DDD89FAC3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("BODEGA_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "BODEGA_ID", "197", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportBODEGA_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DATE_TO", "");
    xmlDocument.setParameter("DATE_TO_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton5BE328C8AB9743B6A8CEACB1ED45CBD3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 5BE328C8AB9743B6A8CEACB1ED45CBD3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton5BE328C8AB9743B6A8CEACB1ED45CBD3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("5BE328C8AB9743B6A8CEACB1ED45CBD3");
        vars.removeMessage("5BE328C8AB9743B6A8CEACB1ED45CBD3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("Bpartner", "");
    xmlDocument.setParameter("BpartnerR", "");
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton61B3BD10ED0C4A37950A9269A07AF344(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 61B3BD10ED0C4A37950A9269A07AF344");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton61B3BD10ED0C4A37950A9269A07AF344", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("61B3BD10ED0C4A37950A9269A07AF344");
        vars.removeMessage("61B3BD10ED0C4A37950A9269A07AF344");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ANIO", "");
    comboTableData = new ComboTableData(vars, this, "18", "ANIO", "A18160D7A6D247C8AF5904F61A8BEEF2", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportANIO", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("VALOR_UTILIDADES", "");
    xmlDocument.setParameter("outputType", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6A1843AA112E43F4B6D18D4B18423080(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6A1843AA112E43F4B6D18D4B18423080");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6A1843AA112E43F4B6D18D4B18423080", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6A1843AA112E43F4B6D18D4B18423080");
        vars.removeMessage("6A1843AA112E43F4B6D18D4B18423080");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("empleado", "");
    xmlDocument.setParameter("empleadoR", "");
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton85C377C58D754187872EC59811012A71(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 85C377C58D754187872EC59811012A71");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton85C377C58D754187872EC59811012A71", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("85C377C58D754187872EC59811012A71");
        vars.removeMessage("85C377C58D754187872EC59811012A71");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DATE_FROM", "");
    xmlDocument.setParameter("DATE_FROM_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DATE_TO", "");
    xmlDocument.setParameter("DATE_TO_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("VENDEDOR", "");
    comboTableData = new ComboTableData(vars, this, "18", "VENDEDOR", "E605E99712A245CBB6D7610595BFB017", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportVENDEDOR", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("ZONA", "");
    xmlDocument.setParameter("outputtype", "xls");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "xls");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton951CA29589754DBCA996D47C5286EDB5(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 951CA29589754DBCA996D47C5286EDB5");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton951CA29589754DBCA996D47C5286EDB5", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("951CA29589754DBCA996D47C5286EDB5");
        vars.removeMessage("951CA29589754DBCA996D47C5286EDB5");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("cuentaCosto", "");
    comboTableData = new ComboTableData(vars, this, "18", "cuentaCosto", "0B9B138495E141C8A3C2BFDA4BEE3B4B", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportcuentaCosto", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputformat", "html");
    comboTableData = new ComboTableData(vars, this, "17", "outputformat", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "html");
    xmlDocument.setData("reportoutputformat", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }



    private void process172(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "172", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800087(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800087", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process0586F175A22647FAB964D16B85971CA7(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "0586F175A22647FAB964D16B85971CA7", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String straccionpedido = vars.getStringParameter("inpaccionpedido");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AccionPedido", straccionpedido, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process524328A1ADF3423F8DD5762469D50641(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "524328A1ADF3423F8DD5762469D50641", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800075(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800075", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String strmWarehouseId = vars.getStringParameter("inpmWarehouseId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "M_Warehouse_ID", strmWarehouseId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strreferenceno = vars.getStringParameter("inpreferenceno");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "ReferenceNo", strreferenceno, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateinvoiced = vars.getStringParameter("inpdateinvoiced");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "60", "DateInvoiced", strdateinvoiced, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process119(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "119", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateinvoiced = vars.getStringParameter("inpdateinvoiced");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateInvoiced", strdateinvoiced, vars.getClient(), vars.getOrg(), vars.getUser());
String stradOrgId = vars.getStringParameter("inpadOrgId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "15", "AD_Org_ID", stradOrgId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcOrderId = vars.getStringParameter("inpcOrderId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "C_Order_ID", strcOrderId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strinvoicetodate = vars.getStringParameter("inpinvoicetodate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "InvoiceToDate", strinvoicetodate, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process193(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "193", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateordered = vars.getStringParameter("inpdateordered");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateOrdered", strdateordered, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strvendorId = vars.getStringParameter("inpvendorId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "Vendor_ID", strvendorId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcOrderId = vars.getStringParameter("inpcOrderId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "C_Order_ID", strcOrderId, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process185(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "185", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process175(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "175", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AD_Client_ID", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String stradTableId = vars.getStringParameter("inpadTableId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "AD_Table_ID", stradTableId, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800085(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800085", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strisincremental = vars.getStringParameter("inpisincremental", "N");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "IsIncremental", strisincremental, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800130(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800130", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strinitdate = vars.getStringParameter("inpinitdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "Initdate", strinitdate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "EndDate", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800151(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800151", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800109(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800109", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcBudgetId = vars.getStringParameter("inpcBudgetId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "C_Budget_ID", strcBudgetId, vars.getClient(), vars.getOrg(), vars.getUser());
String strmaProcessplanId = vars.getStringParameter("inpmaProcessplanId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "MA_Processplan_ID", strmaProcessplanId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcalcdate = vars.getStringParameter("inpcalcdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "CalcDate", strcalcdate, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processF03D64524CD642EFA6DDC11150EDC498(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "F03D64524CD642EFA6DDC11150EDC498", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcPeriodId = vars.getStringParameter("inpcPeriodId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "c_period_id", strcPeriodId, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process2A6B08C238B24CC1A483C84AB02DFBC2(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "2A6B08C238B24CC1A483C84AB02DFBC2", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process7CDAA4521FBE43FD84C7E710C91CD024(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "7CDAA4521FBE43FD84C7E710C91CD024", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process96D36A8DA2774B2FA817E017DA865C3B(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "96D36A8DA2774B2FA817E017DA865C3B", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process98221DB2679844F8A8BB657B3E9F6474(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "98221DB2679844F8A8BB657B3E9F6474", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String strbpartner = vars.getStringParameter("inpbpartner");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "Bpartner", strbpartner, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process98C3C5AF4E684414AA9E5072EFB0534D(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "98C3C5AF4E684414AA9E5072EFB0534D", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processA5EE7B720325485C8CF63DB7871A7101(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "A5EE7B720325485C8CF63DB7871A7101", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stranio = vars.getStringParameter("inpanio");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ANIO", stranio, vars.getClient(), vars.getOrg(), vars.getUser());
String strvalorSalario = vars.getNumericParameter("inpvalorSalario");
PInstanceProcessData.insertPInstanceParamNumber(this, pinstance, "20", "VALOR_SALARIO", strvalorSalario, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processA83AA7DCC6F649ECA0153CA233E0F791(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "A83AA7DCC6F649ECA0153CA233E0F791", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcuentaBanco = vars.getStringParameter("inpcuentaBanco");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "CUENTA_BANCO", strcuentaBanco, vars.getClient(), vars.getOrg(), vars.getUser());
String strfechaDesde = vars.getStringParameter("inpfechaDesde");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "FECHA_DESDE", strfechaDesde, vars.getClient(), vars.getOrg(), vars.getUser());
String strfechaHasta = vars.getStringParameter("inpfechaHasta");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "FECHA_HASTA", strfechaHasta, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processA8B66D431BCF4382B901F8B216F48840(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "A8B66D431BCF4382B901F8B216F48840", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strvalorMin = vars.getStringParameter("inpvalorMin");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "valor_min", strvalorMin, vars.getClient(), vars.getOrg(), vars.getUser());
String strvalorMax = vars.getStringParameter("inpvalorMax");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "valor_max", strvalorMax, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processD5B93E9290B74555AD68BED6BEE0AC14(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "D5B93E9290B74555AD68BED6BEE0AC14", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateFrom = vars.getStringParameter("inpdateFrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DATE_FROM", strdateFrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateTo = vars.getStringParameter("inpdateTo");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DATE_TO", strdateTo, vars.getClient(), vars.getOrg(), vars.getUser());
String strcategoria = vars.getStringParameter("inpcategoria");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "CATEGORIA", strcategoria, vars.getClient(), vars.getOrg(), vars.getUser());
String strproducto = vars.getStringParameter("inpproducto");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "PRODUCTO", strproducto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processD91416F3FE5B4CF39729BE6E0CC7C81A(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "D91416F3FE5B4CF39729BE6E0CC7C81A", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processF43E34AAA4BA4B0A8EB1CFD9C7DE75DF(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "F43E34AAA4BA4B0A8EB1CFD9C7DE75DF", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String strsalesrepId = vars.getStringParameter("inpsalesrepId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "25", "salesrep_id", strsalesrepId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcRegionId = vars.getStringParameter("inpcRegionId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "C_Region_ID", strcRegionId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcZonaId = vars.getStringParameter("inpcZonaId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "35", "C_Zona_ID", strcZonaId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process7ADEF4948DD34B6DA43F75582C166396(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "7ADEF4948DD34B6DA43F75582C166396", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stryear = vars.getStringParameter("inpyear");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "year", stryear, vars.getClient(), vars.getOrg(), vars.getUser());
String stradRef = vars.getStringParameter("inpadRef");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "ad_ref", stradRef, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());
String strcPeriodId = vars.getStringParameter("inpcPeriodId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "c_period_id", strcPeriodId, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processB670ED126EC0470A966FB31C6EEB8647(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "B670ED126EC0470A966FB31C6EEB8647", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcPeriodId = vars.getStringParameter("inpcPeriodId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "c_period_id", strcPeriodId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processBB3C25ED65BC4816A097E6F4E6E179B8(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "BB3C25ED65BC4816A097E6F4E6E179B8", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcPeriodId = vars.getStringParameter("inpcPeriodId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "c_period_id", strcPeriodId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processB98DC338B0CD46F9B0C1310DE8A92477(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "B98DC338B0CD46F9B0C1310DE8A92477", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateFrom = vars.getStringParameter("inpdateFrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DATE_FROM", strdateFrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateTo = vars.getStringParameter("inpdateTo");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DATE_TO", strdateTo, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processF69FF1B8F38A41D7BFC95CCFDFEDD94A(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "F69FF1B8F38A41D7BFC95CCFDFEDD94A", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateFrom = vars.getStringParameter("inpdateFrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DATE_FROM", strdateFrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateTo = vars.getStringParameter("inpdateTo");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DATE_TO", strdateTo, vars.getClient(), vars.getOrg(), vars.getUser());
String strdocstatus = vars.getStringParameter("inpdocstatus");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "25", "DOCSTATUS", strdocstatus, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800171(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800171", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcCurrencyId = vars.getStringParameter("inpcCurrencyId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "15", "C_Currency_ID", strcCurrencyId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String strcProjectId = vars.getStringParameter("inpcProjectId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "C_Project_ID", strcProjectId, vars.getClient(), vars.getOrg(), vars.getUser());
String strmWarehouseId = vars.getStringParameter("inpmWarehouseId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "60", "M_Warehouse_ID", strmWarehouseId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "70", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processD234AE084F7040DCB66E281A4237FF99(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "D234AE084F7040DCB66E281A4237FF99", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradOrgId = vars.getStringParameter("inpadOrgId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AD_Org_ID", stradOrgId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcAcctschemaId = vars.getStringParameter("inpcAcctschemaId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "C_AcctSchema_ID", strcAcctschemaId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "50", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "60", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process01FB4F522F9D4AAD9304B0BEDD2F1D56(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "01FB4F522F9D4AAD9304B0BEDD2F1D56", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateTo = vars.getStringParameter("inpdateTo");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DATE_TO", strdateTo, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process12FE28B8C0554C1AB8B5CA3C0BAFC35E(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "12FE28B8C0554C1AB8B5CA3C0BAFC35E", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String strbpartner = vars.getStringParameter("inpbpartner");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "Bpartner", strbpartner, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process1F14F99937B04350B867CDF15E48BE93(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "1F14F99937B04350B867CDF15E48BE93", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String strbpartner = vars.getStringParameter("inpbpartner");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "Bpartner", strbpartner, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process904509557496454CBD4C00265FBEA190(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "904509557496454CBD4C00265FBEA190", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strperiodo = vars.getStringParameter("inpperiodo");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "PERIODO", strperiodo, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process2B31A05A278740ADA7D50686049E0D34(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "2B31A05A278740ADA7D50686049E0D34", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateTo = vars.getStringParameter("inpdateTo");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DATE_TO", strdateTo, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process38854597C4824AC0A6364248D9C5530C(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "38854597C4824AC0A6364248D9C5530C", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process47FE2C3B551047D09A658D56B29AF777(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "47FE2C3B551047D09A658D56B29AF777", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateFrom = vars.getStringParameter("inpdateFrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DATE_FROM", strdateFrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateTo = vars.getStringParameter("inpdateTo");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DATE_TO", strdateTo, vars.getClient(), vars.getOrg(), vars.getUser());
String strcategoria = vars.getStringParameter("inpcategoria");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "CATEGORIA", strcategoria, vars.getClient(), vars.getOrg(), vars.getUser());
String strproducto = vars.getStringParameter("inpproducto");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "PRODUCTO", strproducto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process4E8BB4D0A9304152BBF0D79DDD89FAC3(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "4E8BB4D0A9304152BBF0D79DDD89FAC3", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strbodegaId = vars.getStringParameter("inpbodegaId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "5", "BODEGA_ID", strbodegaId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateTo = vars.getStringParameter("inpdateTo");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DATE_TO", strdateTo, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process5BE328C8AB9743B6A8CEACB1ED45CBD3(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "5BE328C8AB9743B6A8CEACB1ED45CBD3", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String strbpartner = vars.getStringParameter("inpbpartner");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "Bpartner", strbpartner, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process61B3BD10ED0C4A37950A9269A07AF344(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "61B3BD10ED0C4A37950A9269A07AF344", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stranio = vars.getStringParameter("inpanio");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ANIO", stranio, vars.getClient(), vars.getOrg(), vars.getUser());
String strvalorUtilidades = vars.getNumericParameter("inpvalorUtilidades");
PInstanceProcessData.insertPInstanceParamNumber(this, pinstance, "20", "VALOR_UTILIDADES", strvalorUtilidades, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process6A1843AA112E43F4B6D18D4B18423080(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "6A1843AA112E43F4B6D18D4B18423080", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strempleado = vars.getStringParameter("inpempleado");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "empleado", strempleado, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process85C377C58D754187872EC59811012A71(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "85C377C58D754187872EC59811012A71", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateFrom = vars.getStringParameter("inpdateFrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DATE_FROM", strdateFrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateTo = vars.getStringParameter("inpdateTo");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DATE_TO", strdateTo, vars.getClient(), vars.getOrg(), vars.getUser());
String strvendedor = vars.getStringParameter("inpvendedor");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "VENDEDOR", strvendedor, vars.getClient(), vars.getOrg(), vars.getUser());
String strzona = vars.getStringParameter("inpzona");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "ZONA", strzona, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process951CA29589754DBCA996D47C5286EDB5(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "951CA29589754DBCA996D47C5286EDB5", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcuentacosto = vars.getStringParameter("inpcuentacosto");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "cuentaCosto", strcuentacosto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputformat = vars.getStringParameter("inpoutputformat");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputformat", stroutputformat, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }


  public String getServletInfo() {
    return "Servlet ActionButton_Responser. This Servlet was made by Wad constructor";
  } // end of the getServletInfo() method

  private void processButtonHelper(HttpServletRequest request, HttpServletResponse response, VariablesSecureApp vars, String pinstance) 
     throws ServletException, IOException {
      OBError myMessage;
      try {
        PInstanceProcessData[] pinstanceData = PInstanceProcessData.select(this, pinstance);
      myMessage = Utility.getProcessInstanceMessage(this, vars, pinstanceData);
      } catch (Exception e) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), e.getMessage());
          e.printStackTrace();
          log4j.warn("Error");
      }
      advisePopUp(request, response, myMessage.getType(), myMessage.getTitle(), myMessage.getMessage());
  }
}
