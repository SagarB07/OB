/*
 ******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is                  Compiere  ERP & CRM  Business Solution
 * The Initial Developer of the Original Code is Jorg Janke  and ComPiere, Inc.
 * Portions created by Jorg Janke are Copyright (C) 1999-2001 Jorg Janke, parts
 * created by ComPiere are Copyright (C) ComPiere, Inc.;   All Rights Reserved.
 * Contributor(s): Openbravo SLU
 * Contributions are Copyright (C) 2001-2006 Openbravo S.L.U.
 ******************************************************************************
 */
package org.openbravo.erpCommon.ad_process;

import java.sql.Connection;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.ad_process.ImportBPartnerData;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.erpCommon.utility.Utility;

public class ImportBPartner extends ImportProcess {
  static Logger log4j = Logger.getLogger(ImportBPartner.class);

  private String m_AD_Process_ID = "";
  private String m_Record_ID = "";
  private boolean m_deleteOldImported;

  public ImportBPartner(ConnectionProvider conn, String AD_Process_ID, String recordId,boolean deleteOld) {
    super(conn);
    m_AD_Process_ID = AD_Process_ID;
    m_Record_ID = recordId;
    m_deleteOldImported = deleteOld;
  }

  protected String getAD_Process_ID() {
    return m_AD_Process_ID;
  }

  protected String getRecord_ID() {
    return m_Record_ID;
  }

  protected void createInstanceParams(VariablesSecureApp vars) throws ServletException {
    if (log4j.isDebugEnabled())
      log4j.debug("Creating parameters");
  }

  @SuppressWarnings("unused")
protected OBError doIt(VariablesSecureApp vars) throws ServletException {
    int no = 0;
    String causaProblema = "";
    String personaProblema = "";
    ConnectionProvider conn = null;
    Connection con = null;
    OBError myError = new OBError();
    

    try {
      conn = getConnection();
      con = conn.getTransactionConnection();
      // Delete imported
      if (m_deleteOldImported) {
        no = ImportBPartnerData.deleteOld(con, conn, getAD_Client_ID());
        if (log4j.isDebugEnabled())
          log4j.debug("Delete Old Imported = " + no);
      }
      // Set Client, Org, IaActive, Created/Updated, ProductType
      no = ImportBPartnerData.updateRecords(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner Reset = " + no);

      // Set BPGroup
      no = ImportBPartnerData.updateBPGroupDefault(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner BPGroup = " + no);
      no = ImportBPartnerData.updateBPGroupId(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner BPGroupId = " + no);
      no = ImportBPartnerData.updateGroupError(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("Invalid BPartner group = " + no);

      // Country
      no = ImportBPartnerData.updateCountryCodeDefault(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner CountryCode = " + no);
      no = ImportBPartnerData.updateCountryId(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner CountryId = " + no);
      no = ImportBPartnerData.updateCountryError(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("Invalid Country = " + no);

      // Region
      no = ImportBPartnerData.updateRegionDefault(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner Region = " + no);
      no = ImportBPartnerData.updateRegionId(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner RegionId = " + no);

      /*
       * Prevents Region validation check the failed smoke test:
       * http://builds.openbravo.com/job/erp_devel_int-pgsql-smoke-test/141/
       * 
       * Change requested by QA team
       * 
       * no = ImportBPartnerData.updateRegionError(con, conn, getAD_Client_ID()); if
       * (log4j.isDebugEnabled()) log4j.debug("Invalid Region = " + no);
       */

      // Greeting
      no = ImportBPartnerData.updateGreetingId(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner GreetingId = " + no);
      no = ImportBPartnerData.updateGreetingError(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("Invalid Greeting = " + no);

      // BPartner
      no = ImportBPartnerData.updateBPartnerId(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner BPartnerId = " + no);

      // ADUser
      no = ImportBPartnerData.updateADUserId(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner ADUserId = " + no);

      // Location
      no = ImportBPartnerData.updateLocationId(con, conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("ImportBPartner LocationId = " + no);

      conn.releaseCommitConnection(con);
    } catch (Exception se) {
      try {
        conn.releaseRollbackConnection(con);
      } catch (Exception ignored) {
      }
      se.printStackTrace();
      addLog(Utility.messageBD(conn, "ProcessRunError", vars.getLanguage()));
      myError.setType("Error");
      myError.setTitle(Utility.messageBD(conn, "Error", vars.getLanguage()));
      myError.setMessage(Utility.messageBD(conn, "ProcessRunError", vars.getLanguage()));
      return myError;
    }

    // till here, the edition of the I_BPARTNER table
    // now, the insertion from I_BPARTNER table in C_BPartner...

    int noInsert = 0;
    int noUpdate = 0;
    int noBPartnerError = 0;

    try {
      // Go through Records
      ImportBPartnerData[] data = ImportBPartnerData.select(conn, getAD_Client_ID());
      if (log4j.isDebugEnabled())
        log4j.debug("Going through " + data.length + " records");
      for (int i = 0; i < data.length; i++) {
        String I_BPartner_ID = data[i].iBpartnerId;
        String C_BPartner_ID = data[i].cBpartnerId;
        boolean newBPartner = (C_BPartner_ID == null || C_BPartner_ID.equals(""));
        String C_BPartner_Location_ID = data[i].cBpartnerLocationId;
        boolean newLocation = data[i].addr != null;
        String AD_User_ID = data[i].adUserId;
        boolean newContact = data[i].contactname != null;

        con = conn.getTransactionConnection();
 
        
        // create/update BPartner
        if (newBPartner) { // Insert new BPartner
          C_BPartner_ID = SequenceIdData.getUUID();
          try {
            no = ImportBPartnerData.insertBPartner(con, conn, C_BPartner_ID, I_BPartner_ID);
            if (log4j.isDebugEnabled())
              log4j.debug("Insert BPartner = " + no);
            noInsert++;
          } catch (ServletException ex) {
            if (log4j.isDebugEnabled())
              log4j.debug("Insert BPartner - " + ex.toString());
            conn.releaseRollbackConnection(con);
            no = ImportBPartnerData.insertBPartnerError(conn, ex.toString(), I_BPartner_ID);
            continue;
          }
        } else { // Update existing BPartner
          try {
            no = ImportBPartnerData.updateBPartner(con, conn, I_BPartner_ID, C_BPartner_ID);
            if (log4j.isDebugEnabled())
              log4j.debug("Update BPartner = " + no);
            noUpdate++;
          } catch (ServletException ex) {
            if (log4j.isDebugEnabled())
              log4j.debug("Update BPartner - " + ex.toString());
            conn.releaseRollbackConnection(con);
            no = ImportBPartnerData.updateBPartnerError(conn, ex.toString(), I_BPartner_ID);
            continue;
          }
        }

        // create/update BPartner Location
        if (!C_BPartner_Location_ID.equals("")) { // Update Location
        } else if (newLocation) { // New Location
          String C_Location_ID = SequenceIdData.getUUID();
          try {
            no = ImportBPartnerData.insertLocation(con, conn, C_Location_ID, I_BPartner_ID);
            if (log4j.isDebugEnabled())
              log4j.debug("Insert Location = " + no);
          } catch (ServletException ex) {
            if (log4j.isDebugEnabled())
              log4j.debug("Insert Location - " + ex.toString());
            noInsert--;
            conn.releaseRollbackConnection(con);
            no = ImportBPartnerData.insertLocationError(conn, ex.toString(), I_BPartner_ID);
            continue;
          }
          C_BPartner_Location_ID = SequenceIdData.getUUID();
          try {
            String locationName = parseAddressName(data[i]);

            no = ImportBPartnerData.insertBPLocation(con, conn, C_BPartner_Location_ID,
                locationName, C_BPartner_ID, C_Location_ID, I_BPartner_ID);
            if (log4j.isDebugEnabled())
              log4j.debug("Insert BP Location = " + no);
          } catch (ServletException ex) {
            if (log4j.isDebugEnabled())
              log4j.debug("Insert BP Location - " + ex.toString());
            noInsert--;
            conn.releaseRollbackConnection(con);
            no = ImportBPartnerData.insertBPLocationError(conn, ex.toString(), I_BPartner_ID);
            continue;
          }
        }

        // Create/Update Contact
        if (!AD_User_ID.equals("")) {
          try {
            // Update existing contact
            no = ImportBPartnerData.updateBPContact(con, conn, I_BPartner_ID, AD_User_ID);
            if (log4j.isDebugEnabled())
              log4j.debug("Update BP Contact = " + no);
          } catch (ServletException ex) {
            if (log4j.isDebugEnabled())
              log4j.debug("Update BP Contact - " + ex.toString());
            noUpdate--;
            conn.releaseRollbackConnection(con);
            no = ImportBPartnerData.updateBPContactError(conn, ex.toString(), I_BPartner_ID);
            continue;
          }
        } else if (newContact) { // New Contact
          AD_User_ID = SequenceIdData.getUUID();
          try {
            if (data[i].contactname != null && !data[i].contactname.equals("")) {
              no = ImportBPartnerData.insertBPContact(con, conn, AD_User_ID, C_BPartner_ID,
                  (C_BPartner_Location_ID.equals("0")) ? "NULL" : C_BPartner_Location_ID,
                  I_BPartner_ID);
              if (log4j.isDebugEnabled())
                log4j.debug("Insert BP Contact = " + no);
            } else {
              AD_User_ID = "0";
            }
          } catch (ServletException ex) {
            if (log4j.isDebugEnabled())
              log4j.debug("Insert BP Contact - " + ex.toString());
            noInsert--;
            conn.releaseRollbackConnection(con);
            no = ImportBPartnerData.insertBPContactError(conn, ex.toString(), I_BPartner_ID);
            continue;
          }
        }

        // Update I_BPARTNER
        try {
            if (!validarTipoPersona(data[i].tipoPersona)){
            	causaProblema = "invalid_tpersona";
            	personaProblema =data[i].numeroIdentificacion;
            	int var = 10/0;
            }
            
            if (validarDocumento(data[i].numeroIdentificacion,data[i].tipoIdentifiacion)  != "VALIDO" ){
            	causaProblema = "invalid_tIdentificacion";
            	personaProblema =validarDocumento(data[i].numeroIdentificacion,data[i].tipoIdentifiacion) +": " +data[i].numeroIdentificacion  ;
            	int var = 10/0;
            }	
        	
          no = ImportBPartnerData.setImported(con, conn, C_BPartner_ID, (C_BPartner_Location_ID.equals("0")) ? "" : C_BPartner_Location_ID, (AD_User_ID.equals("0")) ? "": AD_User_ID, I_BPartner_ID);
          conn.releaseCommitConnection(con);
        } catch (ServletException ex) {
          if (log4j.isDebugEnabled())
            log4j.debug("Update Imported - " + ex.toString());
          noInsert--;
          conn.releaseRollbackConnection(con);
          no = ImportBPartnerData.updateSetImportedError(conn, ex.toString(), I_BPartner_ID);
          continue;
        }catch (Exception se){
        	log4j.debug("Update Imported - " + se.toString());
            noInsert--;
//            conn.releaseRollbackConnection(con);
//            no = ImportBPartnerData.updateSetImportedError(conn, se.toString(), I_BPartner_ID);
  
            if (causaProblema.equals("invalid_tpersona")){
                conn.releaseRollbackConnection(con);
                no = ImportBPartnerData.updateSetImportedError(conn, "El dato en el campo TIPO PERSONA es invalido", I_BPartner_ID);
              }else {
        	       if (causaProblema.equals("invalid_tIdentificacion")){
        	    	  conn.releaseRollbackConnection(con);
        	    	  no = ImportBPartnerData.updateSetImportedError(conn, "Error de verificación de campo cédula", I_BPartner_ID);
        	      }else
        	    	  {
        		      se.printStackTrace();
        		      addLog(Utility.messageBD(conn, "ProcessRunError", vars.getLanguage()));
        		      conn.releaseRollbackConnection(con);
        	    	  no = ImportBPartnerData.updateSetImportedError(conn, "ProcessRunError", I_BPartner_ID);
        			   
        		      }
            	  }
              
            
            continue;
            
            
            
            
        	
        }
        
      }

      
      
      
      
      // Set Error to indicator to not imported
      noBPartnerError = ImportBPartnerData.updateNotImported(conn, getAD_Client_ID());
    } catch (Exception se) {
      if (causaProblema.equals("invalid_tpersona")){
    	  myError.setType("Error");
    	  myError.setTitle("Error de verificación para le persona con número de identificación:   "+personaProblema);
    	  myError.setMessage("El dato en el campo TIPO PERSONA es invalido" );
    	  
    	  
      }else {
	       if (causaProblema.equals("invalid_tIdentificacion")){
	    	  myError.setType("Error");
	    	  myError.setTitle("Error de verificación de campo cédula");
	    	  myError.setMessage(personaProblema);
	      }else
	    	  {
		      se.printStackTrace();
		      addLog(Utility.messageBD(conn, "ProcessRunError", vars.getLanguage()));
		      myError.setType("Error");
		      myError.setTitle(Utility.messageBD(conn, "Error", vars.getLanguage()));
		      myError.setMessage(Utility.messageBD(conn, "ProcessRunError", vars.getLanguage()));
			      try {
			        conn.releaseCommitConnection(con);
			      } catch (Exception e) {
		      }
    	  }
      }
      return myError;
    }
    addLog(Utility.messageBD(conn, "Business partners not imported", vars.getLanguage()) + ": "+ noBPartnerError + "; ");
    addLog("Business partners inserted: " + noInsert + "; ");
    addLog("Business partners updated: " + noUpdate);

    if (noBPartnerError == 0) {
      myError.setType("Success");
      myError.setTitle(Utility.messageBD(conn, "Success", vars.getLanguage()));
    } else if (noInsert > 0 || noUpdate > 0) {
      myError.setType("Warning");
      myError.setTitle(Utility.messageBD(conn, "Some business partners could not be imported", vars
          .getLanguage()));
    } else {
      myError.setType("Error");
      myError.setTitle(Utility.messageBD(conn, " No business partners could be imported", vars
          .getLanguage()));
    }
    myError.setMessage(Utility.messageBD(conn, getLog(), vars.getLanguage()));
    return myError;

  } // doIt

@SuppressWarnings("unused")
private String validarDocumento(String numero, String tipoIdentificacion) {
	    boolean valor = true;
	    String mensaje = "VALIDO";
	    
	    if (tipoIdentificacion.equals("01")||tipoIdentificacion.equals("02")||tipoIdentificacion.equals("03")||tipoIdentificacion.equals("07") ){
	    	
	    if (tipoIdentificacion.equals("02") || tipoIdentificacion.equals("01")) {

	      try {
	        int suma = 0;
	        int residuo = 0;
	        boolean privada = false;
	        boolean publica = false;
	        boolean natural = false;
	        int numeroProvincias = 24;
	        int digitoVerificador = 0;
	        int modulo = 11;

	        int d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;
	        int p1, p2, p3, p4, p5, p6, p7, p8, p9;

	        d1 = d2 = d3 = d4 = d5 = d6 = d7 = d8 = d9 = d10 = 0;
	        p1 = p2 = p3 = p4 = p5 = p6 = p7 = p8 = p9 = 0;

	        if (numero.length() < 10) {
	          // mensaje = "  No es válido";
	          mensaje = " CI/RUC no válido";

	          valor = false;
	        }

	        // Los primeros dos digitos corresponden al codigo de la
	        // provincia
	        int provincia = Integer.parseInt(numero.substring(0, 2));

	        if (provincia <= 0 || provincia > numeroProvincias) {
	          mensaje = " CI/RUC no válido";
	          // JOptionPane
	          // .showMessageDialog(Motor.getVentana(), "El c" + Motor.o
	          // + "digo de la provincia (dos primeros d" + Motor.i +
	          // "gitos) es inv" + Motor.a
	          // + "lido");
	          valor = false;
	        }

	        // Aqui almacenamos los digitos de la cedula en variables.
	        d1 = Integer.parseInt(numero.substring(0, 1));
	        d2 = Integer.parseInt(numero.substring(1, 2));
	        d3 = Integer.parseInt(numero.substring(2, 3));
	        d4 = Integer.parseInt(numero.substring(3, 4));
	        d5 = Integer.parseInt(numero.substring(4, 5));
	        d6 = Integer.parseInt(numero.substring(5, 6));
	        d7 = Integer.parseInt(numero.substring(6, 7));
	        d8 = Integer.parseInt(numero.substring(7, 8));
	        d9 = Integer.parseInt(numero.substring(8, 9));
	        d10 = Integer.parseInt(numero.substring(9, 10));

	        // El tercer digito es:
	        // 9 para sociedades privadas y extranjeros
	        // 6 para sociedades publicas
	        // menor que 6 (0,1,2,3,4,5) para personas naturales
	        if (d3 == 7 || d3 == 8) {
	          mensaje = " CI/RUC no válido";
	          // JOptionPane.showMessageDialog(Motor.getVentana(),
	          // "El tercer d" + Motor.i
	          // + "gito ingresado es inv" + Motor.a + "lido");
	          valor = false;
	        }

	        // Solo para personas naturales (modulo 10)
	        if (d3 < 6) {
	          natural = true;
	          modulo = 10;
	          p1 = d1 * 2;
	          if (p1 >= 10)
	            p1 -= 9;
	          p2 = d2 * 1;
	          if (p2 >= 10)
	            p2 -= 9;
	          p3 = d3 * 2;
	          if (p3 >= 10)
	            p3 -= 9;
	          p4 = d4 * 1;
	          if (p4 >= 10)
	            p4 -= 9;
	          p5 = d5 * 2;
	          if (p5 >= 10)
	            p5 -= 9;
	          p6 = d6 * 1;
	          if (p6 >= 10)
	            p6 -= 9;
	          p7 = d7 * 2;
	          if (p7 >= 10)
	            p7 -= 9;
	          p8 = d8 * 1;
	          if (p8 >= 10)
	            p8 -= 9;
	          p9 = d9 * 2;
	          if (p9 >= 10)
	            p9 -= 9;
	        }

	        // Solo para sociedades publicas (modulo 11)
	        // Aqui el digito verficador esta en la posicion 9, en las otras
	        // 2
	        // en la pos. 10
	        if (d3 == 6) {
	          publica = true;
	          p1 = d1 * 3;
	          p2 = d2 * 2;
	          p3 = d3 * 7;
	          p4 = d4 * 6;
	          p5 = d5 * 5;
	          p6 = d6 * 4;
	          p7 = d7 * 3;
	          p8 = d8 * 2;
	          p9 = 0;
	        }

	        /* Solo para entidades privadas (modulo 11) */
	        if (d3 == 9) {
	          privada = true;
	          p1 = d1 * 4;
	          p2 = d2 * 3;
	          p3 = d3 * 2;
	          p4 = d4 * 7;
	          p5 = d5 * 6;
	          p6 = d6 * 5;
	          p7 = d7 * 4;
	          p8 = d8 * 3;
	          p9 = d9 * 2;
	        }

	        suma = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;
	        residuo = suma % modulo;

	        // Si residuo=0, dig.ver.=0, caso contrario 10 - residuo
	        digitoVerificador = residuo == 0 ? 0 : modulo - residuo;
	        int longitud = numero.length(); // Longitud del string

	        // ahora comparamos el elemento de la posicion 10 con el dig.
	        // ver.

	        if (publica == true) {
	          if (digitoVerificador != d9) {
	            mensaje = "  El RUC de la empresa del sector público es incorrecto";
	            valor = false;
	          }
	          if (!numero.substring(9, longitud).equals("0001")) {
	            mensaje = "  El RUC de la empresa del sector público debe terminar con 0001";
	            valor = false;
	          }
	          if (!tipoIdentificacion.equals("01")) {
	            mensaje = "Tipo identificacion incorrecto";
	            valor = false;
	          }
	        }

	        if (privada == true) {
	          if (digitoVerificador != d10) {
	            mensaje = "  El RUC de la empresa del sector privado es incorrecto";
	            valor = false;
	          }
	          if (!numero.substring(10, longitud).equals("001")) {
	            mensaje = "  El RUC de la empresa del sector privado debe terminar con 001";
	            valor = false;
	          }
	          if (!tipoIdentificacion.equals("01")) {
	            mensaje = "Tipo identificacion incorrecto";
	            valor = false;
	          }
	        }

	        if (natural == true) {
	          if (digitoVerificador != d10) {

	            mensaje = "  El número de cédula de la persona natural es incorrecto";
	            valor = false;
	          }
	          if (numero.length() > 10 && !numero.substring(10, longitud).equals("001")) {

	            mensaje = "  El ruc de la persona natural debe terminar con 001";
	            valor = false;
	          }

	          if (mensaje.equals("VALIDO")) {

	            if (numero.length() > 10 && tipoIdentificacion.equals("02")) {
	              mensaje = "Tipo identificacion incorrecto";
	              valor = false;
	            } else if (numero.length() == 10 && tipoIdentificacion.equals("01")) {
	              mensaje = "Tipo identificacion incorrecto";
	              valor = false;
	            }

	          }

	        }
	      } catch (Exception e) {
	        valor = false;
	        mensaje = " CI/RUC no válido";
	      }
	    }
	    return mensaje;
	    }else {
	    	
	    	return "TIPO DE IDENTIFICACIÓN INVALIDO";
	    }
	  }
  
  
  private Boolean validarTipoPersona (String tipoPersona){
	  if (tipoPersona.equals("PN")){
		  return true;
	  }
	  if (tipoPersona.equals("PJ")){
		  return true;
	  }
	  else{
		  return false;
	  }
	  
  }
  
  private String parseAddressName(ImportBPartnerData addressData) {
    String result = ".";

    String locationNameA = "";
    if (!addressData.city.equals("") && addressData.city != null) {
      locationNameA = addressData.city;
    } else if (!addressData.regionname.equals("") && addressData.regionname != null) {
      locationNameA = addressData.regionname;
    } else if (!addressData.postal.equals("") && addressData.postal != null) {
      locationNameA = addressData.postal;
    }

    String locationNameB = "";
    if (!addressData.address1.equals("") && addressData.address1 != null)
      locationNameB = addressData.address1;
    else if (!addressData.address2.equals("") && addressData.address2 != null)
      locationNameB = addressData.address2;

    StringBuffer locationName = new StringBuffer();
    if (!locationNameA.equals("")) {
      locationName.append(locationNameA);
      if (!locationNameB.equals("")) {
        locationName.append(", " + locationNameB);
        result = result + locationName.toString();
      } else
        result = result + locationName.toString();
    } else if (!locationNameB.equals("")) {
      locationName.append(locationNameB);
      result = result + locationName.toString();
    }
    return result;
  }

}
