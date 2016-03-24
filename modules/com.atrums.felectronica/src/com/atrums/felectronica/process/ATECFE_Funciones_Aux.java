package com.atrums.felectronica.process;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import javax.servlet.ServletException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcClient;
import org.dom4j.Element;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.email.EmailUtils;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.poc.EmailManager;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.EmailServerConfiguration;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.utils.FormatUtilities;

import com.atrums.felectronica.data.ATECFE_conf_firma;

public class ATECFE_Funciones_Aux {

  static Logger log4j = Logger.getLogger(ATECFE_Funciones_Aux.class);

  /*
   * Función para transformar de file a byte 64
   */
  public byte[] filetobyte(File flFile) throws IOException {
    byte[] bytes = new byte[(int) flFile.length()];

    FileInputStream is = new FileInputStream(flFile);

    is.read(bytes);
    is.close();

    byte[] bytesen = Base64.encodeBase64(bytes);
    return bytesen;
  }

  /*
   * Función para transformar de byte 64 a file
   */
  public File bytetofile(byte[] bytes) throws IOException {
    File fldocumen = File.createTempFile("documento", ".xml", null);
    fldocumen.deleteOnExit();
    byte[] bytesde = Base64.decodeBase64(bytes);

    FileOutputStream os = new FileOutputStream(fldocumen);

    os.write(bytesde);
    os.close();

    return fldocumen;
  }

  /*
   * Función para generar clave de acceso mediante el algoritmo del modulo11
   */
  public String generarclaveacceso(String strFecha, String strTipoComprobante, String strRuc,
      String strTipoAmbiente, String strSerie, String strSecuencial, String strCodigoNum,
      String strTipoEmision) {

    /*
     * Armando la clave de acceso con los datos menos el digito verificador
     */
    String strClave = strFecha + strTipoComprobante + strRuc + strTipoAmbiente + strSerie
        + strSecuencial + strCodigoNum + strTipoEmision;

    /*
     * Generando el numero verificador
     */
    int[] intClaveAux = new int[48];

    for (int i = 0; i < strClave.length(); i++) {
      intClaveAux[47 - i] = Integer.parseInt(strClave.substring(i, i + 1));
    }

    int intAcumulado = 0;
    int j = 2;

    for (int i = 0; i < intClaveAux.length; i++) {
      intAcumulado = intAcumulado + (intClaveAux[i] * j);
      if (j == 7) {
        j = 1;
      }
      j++;
    }

    Integer strNumVer = 11 - (intAcumulado % 11);

    if (strNumVer >= 10)
      strNumVer = 11 - strNumVer;

    /*
     * Incluyendo el número verificador a la clave de acceso
     */
    strClave = strClave + strNumVer.toString();

    return strClave;
  }

  public boolean enviarCorreo(final String recipient, final String subject, final String body,
      final String type, final List<File> attachments) {
    try {
      Organization currenctOrg = OBContext.getOBContext().getCurrentOrganization();
      final EmailServerConfiguration mailConfig = EmailUtils.getEmailConfiguration(currenctOrg);

      if (mailConfig == null) {
        return false;
      }

      final String username = mailConfig.getSmtpServerAccount();
      final String password = FormatUtilities.encryptDecrypt(mailConfig.getSmtpServerPassword(),
          false);
      final String connSecurity = mailConfig.getSmtpConnectionSecurity();
      final int port = mailConfig.getSmtpPort().intValue();
      final String senderAddress = mailConfig.getSmtpServerSenderAddress();
      final String host = mailConfig.getSmtpServer();
      final boolean auth = mailConfig.isSMTPAuthentification();

      EmailManager.sendEmail(host, auth, username, password, connSecurity, port, senderAddress,
          recipient, null, null, null, subject, body, type, attachments, null, null);
      return true;
    } catch (Exception e) {
      // TODO: handle exception
      return false;
    }
  }

  /*
   * Función que va a enviar el documento al SRI y lo retorna autorizado o no
   */
  public boolean enviarDocSRI(String strDocXml, Client adClient, String strClaveAcc,
      Hashtable<String, String> hstResult, Hashtable<String, String> hstDatos,
      ConnectionProvider conn) {

    try {
      /*
       * Realizar una consulta de la dirección del servidor de transacciones
       */
      ATECFEGenerarXmlData[] axmlConSer = ATECFEGenerarXmlData.methodSeleccionarConfiguracionTrasn(
          conn, adClient.getId());

      if (axmlConSer != null && axmlConSer.length > 0) {
        /*
         * Parametrizando el servidor al cual va hacer la consulta el cliente
         */
        XmlRpcClient clientRPC = new XmlRpcClient(axmlConSer[0].dato1);

        /*
         * Creando un vector para que contenga los parametros para la consulta
         */
        Vector<String> params = new Vector<String>();
        params.addElement(new String(strDocXml));
        params.addElement(new String(adClient.getId()));
        params.addElement(new String(strClaveAcc));
        params.addElement(new String(hstDatos.get("id"))); // invDato.getId()));
        params.addElement(new String(hstDatos.get("docid"))); // invDato.getTransactionDocument().getId()));
        params.addElement(new String(hstDatos.get("atecdocsts")));// invDato.getAtecfeDocstatus()));

        /*
         * Ejecutando la consulta con el documento y el id del cliente en el servidor
         */
        Object objResult = clientRPC.execute("operacionesServer.documentoSRI", params);

        Hashtable<String, String> hstAux = new Hashtable<String, String>();
        hstAux = (Hashtable<String, String>) objResult;
        hstResult.put("doc", hstAux.get("doc"));
        hstResult.put("mens", new String(hstAux.get("mens").getBytes("ISO-8859-1"), "utf-8"));
        hstResult.put("std", hstAux.get("std"));
        hstResult.put("dstd", hstAux.get("dstd"));
        if (hstAux.containsKey("numaut")) {
          hstResult.put("numaut", hstAux.get("numaut"));
        } else {
          hstResult.put("numaut", "");
        }

        if (hstAux.containsKey("fecaut")) {
          hstResult.put("fecaut", hstAux.get("fecaut"));
        } else {
          hstResult.put("fecaut", "");
        }

        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      // TODO: handle exception
      return false;
    }
  }

  /*
   * Función para generar la cabecera de los documentos
   */
  public boolean generarCabecera(Element elmNodo, String strAmbiente, String strCompAutoSRI,
      String strRu, String NumEst, String strPunEmis, String strSecu, String strCodNum,
      String strTipEm, String strFecha, String strDireccion, String strRazonSoc,
      String strNombrCom, OBError msg, Hashtable<String, String> hstClave) {

    if (strAmbiente != null) {
      if (strAmbiente.length() == 1) {
        elmNodo.addElement("ambiente").addText(strAmbiente);
      } else {
        msg.setType("Error");
        msg.setMessage("El ambiente para el documento tiene que ser <1> de pruebas  o <2> de producción");
        msg.setTitle("@Error@");
        return false;
      }
    } else {
      msg.setType("Error");
      msg.setMessage("El ambiente para el documento tiene que ser <1> de pruebas  o <2> de producción");
      msg.setTitle("@Error@");
      return false;
    }

    String strTipoComp = "";

    if (strCompAutoSRI != null) {
      if (strCompAutoSRI.length() <= 2) {
        strTipoComp = strCompAutoSRI.toString();
        if (strTipoComp.length() == 1) {
          strTipoComp = "0" + strTipoComp;
        }
      } else {
        msg.setType("Error");
        msg.setMessage("El tipo de comprobante es de 2 dígitos");
        msg.setTitle("@Error@");
        return false;
      }

    } else {
      msg.setType("Error");
      msg.setMessage("El tipo de comprobante es de 2 dígitos");
      msg.setTitle("@Error@");
      return false;
    }

    String strRuc = "";

    if (strRu != null) {
      if (strRu.length() == 13) {
        strRuc = strRu;
      } else {
        msg.setType("Error");
        msg.setMessage("El ruc es de 13 dígitos");
        msg.setTitle("@Error@");
        return false;
      }
    } else {
      msg.setType("Error");
      msg.setMessage("El ruc es de 13 dígitos");
      msg.setTitle("@Error@");
      return false;
    }

    String strSerie = "";

    if (NumEst != null && strPunEmis != null) {
      if (NumEst.length() <= 3 && strPunEmis.length() <= 3) {

        for (int i = 0; i < (3 - NumEst.length()); i++) {
          strSerie = strSerie + "0";
        }

        strSerie = strSerie + NumEst;

        for (int i = 0; i < (3 - strPunEmis.length()); i++) {
          strSerie = strSerie + "0";
        }

        strSerie = strSerie + strPunEmis;

      } else {
        msg.setType("Error");
        msg.setMessage("El punto de emisión y establecimiento son de 3 dígitos");
        msg.setTitle("@Error@");
        return false;
      }
    } else {
      msg.setType("Error");
      msg.setMessage("El punto de emisión y establecimiento son de 3 dígitos");
      msg.setTitle("@Error@");
      return false;
    }

    String strSecuencial = "";

    if (strSecu != null) {
      if (strSecu.length() <= 9) {
        for (int i = 0; i < (9 - strSecu.length()); i++) {
          strSecuencial = strSecuencial + "0";
        }
        strSecuencial = strSecuencial + strSecu;
      } else {
        msg.setType("Error");
        msg.setMessage("El secuencial es de 9 dígitos");
        msg.setTitle("@Error@");
        return false;
      }
    } else {
      msg.setType("Error");
      msg.setMessage("El secuencial es de 9 dígitos");
      msg.setTitle("@Error@");
      return false;
    }

    String strCodigoNum = "";

    if (strCodNum != null) {
      if (strCodNum.length() <= 8) {
        for (int i = 0; i < (8 - strCodNum.length()); i++) {
          strCodigoNum = strCodigoNum + "0";
        }
        strCodigoNum = strCodigoNum + strCodNum;
      } else {
        msg.setType("Error");
        msg.setMessage("El código numerico es de 8 dígitos");
        msg.setTitle("@Error@");
        return false;
      }
    } else {
      msg.setType("Error");
      msg.setMessage("El código numerico es de 8 dígitos");
      msg.setTitle("@Error@");
      return false;
    }

    if (strTipEm != null) {
      if (strTipEm.length() == 1) {
        elmNodo.addElement("tipoEmision").addText(strTipEm);
      } else {
        msg.setType("Error");
        msg.setMessage("El tipo de emisión es de 1 solo dígito");
        msg.setTitle("@Error@");
        return false;
      }
    } else {
      msg.setType("Error");
      msg.setMessage("El tipo de emisión es de 1 solo dígito");
      msg.setTitle("@Error@");
      return false;
    }

    /*
     * Verificando fecha para generar la clave de acceso para el documento
     */
    if (strFecha != null) {
      if (strFecha.length() == 8) {
        hstClave.put(
            "claveacc",
            generarclaveacceso(strFecha, strTipoComp, strRuc, strAmbiente, strSerie, strSecuencial,
                strCodigoNum, strTipEm));
      } else {
        msg.setType("Error");
        msg.setMessage("El documento tiene que tener una fecha de emisión");
        msg.setTitle("@Error@");
        return false;
      }
    } else {
      msg.setType("Error");
      msg.setMessage("El documento tiene que tener una fecha de emisión");
      msg.setTitle("@Error@");
      return false;
    }

    elmNodo.addElement("razonSocial").addText(strRazonSoc);

    if (!strNombrCom.equals("")) {
      elmNodo.addElement("nombreComercial").addText(strNombrCom);
    }

    elmNodo.addElement("ruc").addText(strRuc);
    elmNodo.addElement("claveAcceso").addText(hstClave.get("claveacc"));
    elmNodo.addElement("codDoc").addText(strTipoComp);
    elmNodo.addElement("estab").addText(NumEst);
    elmNodo.addElement("ptoEmi").addText(strPunEmis);
    elmNodo.addElement("secuencial").addText(strSecuencial);
    elmNodo.addElement("dirMatriz").addText(strDireccion);

    return true;
  }

  public void addCamposAdic(Element elmNodo, ConnectionProvider conn, String strAdClient)
      throws ServletException, UnsupportedEncodingException {
    String strDato = "dato";

    ATECFEGenerarXmlData[] axmlInfAdici = ATECFEGenerarXmlData.methodSeleccionarAdicional(conn,
        strAdClient);

    if (axmlInfAdici != null && axmlInfAdici.length > 0) {
      final Element elminfAdc = elmNodo.addElement("infoAdicional");

      for (int i = 1; i <= Integer.parseInt(axmlInfAdici[0].dato1); i++) {

        String utfCampoAdicional = new String(axmlInfAdici[0].getField(strDato + (i * 2)).getBytes(
            "ISO-8859-1"), "utf-8");

        String utfInfo = new String(axmlInfAdici[0].getField(strDato + ((i * 2) + 1)).getBytes(
            "ISO-8859-1"), "utf-8");

        elminfAdc.addElement("campoAdicional")
            .addAttribute("nombre", normalizacionPalabras(utfCampoAdicional))
            .addText(normalizacionPalabras(utfInfo));
      }
    }
  }

  /*
   * Función para firmar el documento electronicamente
   */
  public File firmarDocumento(File flDoc, ConnectionProvider conn, String strUse, OBError msg)
      throws Exception {
    File flDocumentoFirmado = null;

    /*
     * Realizar una consulta de la firmas electronicas que estan activas
     */
    ATECFEGenerarXmlData[] axmlConFir = ATECFEGenerarXmlData.methodSeleccionarFirma(conn, strUse);

    /*
     * Tomando la primera firma del documento para realizar la firma
     */
    if (axmlConFir != null && axmlConFir.length > 0) {
      ATECFE_conf_firma acfData = OBDal.getInstance().get(ATECFE_conf_firma.class,
          axmlConFir[0].dato1);

      String strPass = FormatUtilities.encryptDecrypt(acfData.getClaveContra(), false);
      String strDire = acfData.getDIRArchivoFirma();
      String strNArc = acfData.getNombreArchivo();

      /*
       * Enviando documento para realizar la firma
       */
      flDocumentoFirmado = ATECFE_XAdESBESSignature.firmar(flDoc, strDire + File.separator
          + strNArc, strPass);

      /*
       * Devolviendo documento firmado
       */
      return flDocumentoFirmado;
    } else {
      msg.setType("Error");
      msg.setMessage("El usuario no tiene permisos para firmar digitalmente el documento");
      msg.setTitle("@Error@");
      return null;
    }
  }

  public void pintance_facInv(String strCinvoice, String strAdUser, String strAdCli,
      String strAdOrg, ConnectionProvider conn) throws ServletException {
    String strpInstaID = UUID.randomUUID().toString().replace("-", "").toUpperCase();

    ATECFEGenerarXmlData[] axmlPro = ATECFEGenerarXmlData.methodSeleccionarProcess(conn,
        "c_invoice_post0", "Process Invoice");

    ATECFEGenerarXmlData.methodInsertarPInst(conn, strpInstaID, axmlPro[0].dato1, strCinvoice,
        strAdUser, strAdCli, strAdOrg);

    ATECFEGenerarXmlData.methodEjecutarProcessInvo(conn, strpInstaID, strCinvoice, strCinvoice);
  }

  public void pintance_facRet(String strCoRetenId, String strAdUser, String strAdCli,
      String strAdOrg, ConnectionProvider conn) throws ServletException {
    String strpInstaID = UUID.randomUUID().toString().replace("-", "").toUpperCase();

    ATECFEGenerarXmlData[] axmlPro = ATECFEGenerarXmlData.methodSeleccionarProcess(conn,
        "co_ejecuta_retencion_compra", "Completar Retención Compra");

    ATECFEGenerarXmlData.methodInsertarPInst(conn, strpInstaID, axmlPro[0].dato1, strCoRetenId,
        strAdUser, strAdCli, strAdOrg);

    ATECFEGenerarXmlData.methodEjecutarProcessInsepInpara(conn, strpInstaID, strpInstaID);

    ATECFEGenerarXmlData.methodEjecutarProcessRet(conn, strpInstaID, strCoRetenId);
  }

  public String basedesign(String strReporte) {

    String strDirectorio = ATECFE_Funciones_Aux.class.getResource("/").getPath();

    if (strDirectorio.indexOf("/C:") != -1 || strDirectorio.indexOf("/c:") != -1) {
      int intPos = strDirectorio.indexOf("/");

      if (intPos != -1) {
        strDirectorio = strDirectorio.substring(strDirectorio.indexOf("/") + 1);
      }
    }

    String strDirectorioInv = "";

    for (int i = 0; i < strDirectorio.length(); i++) {
      strDirectorioInv = strDirectorio.charAt(i) + strDirectorioInv;
    }

    int intPosicion = strDirectorioInv.indexOf("sessalc/dliub");

    if (intPosicion != -1) {
      strDirectorioInv = strDirectorioInv.substring(intPosicion + 13);
    } else {
      intPosicion = strDirectorioInv.indexOf("sessalc/FNI-BEW");
      if (intPosicion != -1) {
        strDirectorioInv = strDirectorioInv.substring(intPosicion + 15);
      } else {
        return "";
      }
    }

    strDirectorio = "";

    for (int i = 0; i < strDirectorioInv.length(); i++) {
      strDirectorio = strDirectorioInv.charAt(i) + strDirectorio;
    }

    String strBaseDesign = strReporte.replaceAll("@basedesign@",
        (strDirectorio + "WebContent/src-loc/design"));

    File flRep = new File(strBaseDesign);

    if (flRep.exists()) {
      return strDirectorio + "WebContent/src-loc/design";
    } else {
      strBaseDesign = strReporte.replaceAll("@basedesign@", (strDirectorio + "src-loc/design"));

      flRep = new File(strBaseDesign);

      if (flRep.exists()) {
        return strDirectorio + "src-loc/design";
      } else {
        return "";
      }
    }
  }

  public File generarPDF(ConnectionProvider conn, String strBaseDesin, String strNombre,
      String strEntiID) throws ServletException {

    File flTemp = null;

    try {

      String strBaseDesign = strBaseDesin;

      if (!basedesign(strBaseDesign).equals("")) {

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("DOCUMENT_ID", strEntiID);
        parameters.put("BASE_DESIGN", basedesign(strBaseDesign));

        strBaseDesign = strBaseDesign.replaceAll("@basedesign@", basedesign(strBaseDesign));

        JasperReport jasperRepo = JasperCompileManager.compileReport(strBaseDesign);

        Connection con = conn.getTransactionConnection();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperRepo, parameters, con);

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String strFecha = ft.format(date);
        String strNombreArch = strNombre + "-" + strFecha;
        OutputStream out = null;

        flTemp = File.createTempFile(strNombreArch, ".pdf", null);

        out = new FileOutputStream(flTemp);
        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        out.write(byteout.toByteArray());
        out.flush();
        out.close();
      }

    } catch (JRException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoConnectionAvailableException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return flTemp;
  }

  public String normalizacionPalabras(String strPalabras) {

    String strPalabraAux = "";

    strPalabraAux = strPalabras.replaceAll("á", "a");
    strPalabraAux = strPalabraAux.replaceAll("é", "e");
    strPalabraAux = strPalabraAux.replaceAll("í", "i");
    strPalabraAux = strPalabraAux.replaceAll("ó", "o");
    strPalabraAux = strPalabraAux.replaceAll("ú", "u");
    return strPalabraAux;
  }
}