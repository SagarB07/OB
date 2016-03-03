package com.atrums.felectronica.process;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.scheduling.Process;
import org.openbravo.scheduling.ProcessBundle;

import com.atrums.contabilidad.data.CO_Retencion_Compra;

public class ATECFE_GenerarXml implements Process {

  private static final Logger log = Logger.getLogger(ATECFE_GenerarXml.class);
  ATECFE_GenerarFactura opeF = new ATECFE_GenerarFactura();
  ATECFE_GenerarRetencion opeR = new ATECFE_GenerarRetencion();
  ATECFE_GeneralGuiaDespacho opeM = new ATECFE_GeneralGuiaDespacho();
  ATECFE_Funciones_Aux ope = new ATECFE_Funciones_Aux();
  final OBError msg = new OBError();
  private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  public static final String EVT_MENSAJE = "mensaje";

  static Logger log4j = Logger.getLogger(ATECFE_GenerarXml.class);

  @Override
  public void execute(ProcessBundle bundle) throws Exception {
    // TODO Auto-generated method stub

    ConnectionProvider conn = bundle.getConnection();
    VariablesSecureApp varsAux = bundle.getContext().toVars();

    OBContext.setOBContext(varsAux.getUser(), varsAux.getRole(), varsAux.getClient(),
        varsAux.getOrg());

    try {

      /*
       * Capturando el C_invoice_id que el proceso va a general el documento XML
       */
      String strCinvoiceID = "";
      String strCoRetencionCompraID = "";
      String strMinoutID = "";

      if (bundle.getParams().get("C_Invoice_ID") != null) {
        strCinvoiceID = bundle.getParams().get("C_Invoice_ID").toString();
      }

      if (bundle.getParams().get("CO_Retencion_Compra_ID") != null) {
        strCoRetencionCompraID = bundle.getParams().get("CO_Retencion_Compra_ID").toString();
      }

      if (bundle.getParams().get("M_InOut_ID") != null) {
        strMinoutID = bundle.getParams().get("M_InOut_ID").toString();
      }

      if (!strCinvoiceID.equals("")) {

        ATECFEGenerarXmlData[] axmlDet = ATECFEGenerarXmlData.methodSeleccionarDetalles(conn,
            strCinvoiceID);

        if (!(axmlDet.length > 0)) {
          msg.setType("Error");
          msg.setMessage("Las facturas necesitan tener productos o impuestos para procesar");
          msg.setTitle("@Error@");
        }

        Invoice invDato = OBDal.getInstance().get(Invoice.class, strCinvoiceID);
        ATECFEGenerarXmlData[] axmlDat = ATECFEGenerarXmlData.methodFacturaElect(conn, invDato
            .getTransactionDocument().getId());

        /*
         * Enviando datos al documento XML
         */
        if (invDato.getInvoiceDate() == null) {
          msg.setType("Error");
          msg.setMessage("No tiene fecha de factura el documento");
          msg.setTitle("@Error@");
          OBDal.getInstance().rollbackAndClose();
          bundle.setResult(msg);
          return;
        }

        if (!invDato.getDocumentNo().toString().matches("[0-9]*")) {
          msg.setType("Error");
          msg.setMessage("El N° del documento solo tienen que contener números");
          msg.setTitle("@Error@");
          OBDal.getInstance().rollbackAndClose();
          bundle.setResult(msg);
          return;
        }

        if (axmlDat[0].dato1.equals("Y")) {
          if (verificarConfiguración(strCinvoiceID, strCoRetencionCompraID, strMinoutID, conn)) {
            if (opeF.generarFacturaXML(strCinvoiceID, conn, varsAux.getUser(), msg)) {
              ope.pintance_facInv(invDato.getId(), invDato.getCreatedBy().getId(), invDato
                  .getClient().getId(), invDato.getOrganization().getId(), conn);
            }
          }
        } else {
          Calendar cldFecha = new GregorianCalendar();
          SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy");
          String strFechaAut = "";

          if (invDato.getCoVencimientoAutSri() == null) {
            strFechaAut = sdfFormato.format(cldFecha.getTime());
          } else {
            strFechaAut = sdfFormato.format(invDato.getCoVencimientoAutSri());
          }

          ATECFEGenerarXmlData.methodActualizarInvo(conn, null, null, null, "PD", "NE",
              invDato.getCoNroAutSri(), "", strFechaAut, strCinvoiceID);
          ope.pintance_facInv(invDato.getId(), invDato.getCreatedBy().getId(), invDato.getClient()
              .getId(), invDato.getOrganization().getId(), conn);
        }

      } else if (!strCoRetencionCompraID.equals("")) {

        CO_Retencion_Compra crcDato = OBDal.getInstance().get(CO_Retencion_Compra.class,
            strCoRetencionCompraID);
        ATECFEGenerarXmlData[] axmlDat = ATECFEGenerarXmlData.methodFacturaElect(conn, crcDato
            .getDocumentType().getId());
        /*
         * Enviando datos al documento XML
         */

        Invoice invDato = OBDal.getInstance().get(Invoice.class, crcDato.getInvoice().getId());

        if (!crcDato.getDocumentNo().toString().matches("[0-9]*")) {
          msg.setType("Error");
          msg.setMessage("El N° del documento solo tienen que contener números");
          msg.setTitle("@Error@");
          OBDal.getInstance().rollbackAndClose();
          bundle.setResult(msg);
          return;
        }

        if (!invDato.getDocumentNo().toString().matches("[0-9]*")) {
          msg.setType("Error");
          msg.setMessage("El N° de la factura solo tiene que contener números");
          msg.setTitle("@Error@");
          OBDal.getInstance().rollbackAndClose();
          bundle.setResult(msg);
          return;
        }

        if (invDato.getDocumentStatus().equals("CO")) {
          if (axmlDat[0].dato1.equals("Y")) {
            if (verificarConfiguración(strCinvoiceID, strCoRetencionCompraID, strMinoutID, conn)) {
              if (opeR.generarFacturaXMLRet(strCoRetencionCompraID, conn, varsAux.getUser(), msg)) {
                ope.pintance_facRet(crcDato.getId(), crcDato.getCreatedBy().getId(), crcDato
                    .getClient().getId(), crcDato.getOrganization().getId(), conn);
              }
            }
          } else {
            ATECFEGenerarXmlData.methodActualizarReten(conn, null, null, null, "PD", "NE",
                crcDato.getNoAutorizacin(), crcDato.getAtecfeFechaAutori(), strCoRetencionCompraID);
            ope.pintance_facRet(crcDato.getId(), crcDato.getCreatedBy().getId(), crcDato
                .getClient().getId(), crcDato.getOrganization().getId(), conn);
          }
        } else {
          msg.setType("Error");
          msg.setMessage("Primero tiene que completar la factura para completar la retención");
          msg.setTitle("@Error@");
          OBDal.getInstance().rollbackAndClose();
          bundle.setResult(msg);
          return;
        }
      } else if (!strMinoutID.equals("")) {

        ShipmentInOut spiDato = OBDal.getInstance().get(ShipmentInOut.class, strMinoutID);
        ATECFEGenerarXmlData[] axmlDat = ATECFEGenerarXmlData.methodFacturaElect(conn, spiDato
            .getDocumentType().getId());

        /*
         * Enviando datos al documento XML
         */
        if (axmlDat[0].dato1.equals("Y")) {
          if (verificarConfiguración(strCinvoiceID, strCoRetencionCompraID, strMinoutID, conn)) {
            opeM.generarFacturaXMLGre(strMinoutID, conn, varsAux.getUser(), msg);
          }
        }
      }

      /*
       * Imprimiendo mensaje a la pantalla de openbravo
       */
      OBDal.getInstance().rollbackAndClose();
      bundle.setResult(msg);

    } catch (final OBException e) {
      // TODO: handle exception
      msg.setType("Error");
      msg.setMessage(e.getMessage());
      msg.setTitle("@Error@");
      OBDal.getInstance().rollbackAndClose();
      bundle.setResult(msg);
    }
  }

  /*
   * Función para verificar la configuración del servidor de consultas y transacciones, y el CI/RUC
   * y email del cliente para enviar la factura al cliente
   */
  private boolean verificarConfiguración(String strCinvoice, String strCoRetencionCompra,
      String strMinout, ConnectionProvider conn) throws ServletException {

    ATECFEGenerarXmlData[] axmlConf = null;

    if (!strCinvoice.equals("")) {
      axmlConf = ATECFEGenerarXmlData.methodSeleccionarConfiguracion(conn, strCinvoice);
    } else if (!strCoRetencionCompra.equals("")) {
      axmlConf = ATECFEGenerarXmlData.methodSeleccionarConfiguracionReCompra(conn,
          strCoRetencionCompra);
    } else if (!strMinout.equals("")) {
      axmlConf = ATECFEGenerarXmlData.methodSeleccionarConfiguracionMinout(conn, strMinout);
    }

    if (axmlConf != null && axmlConf.length == 1) {
      if (axmlConf[0].dato1 == null || axmlConf[0].dato1.equals("")) {
        msg.setType("Error");
        msg.setMessage("No hay dirección del servidor de transacciones");
        msg.setTitle("@Error@");
        return false;
      } else if (axmlConf[0].dato2 == null || axmlConf[0].dato2.equals("")) {
        msg.setType("Error");
        msg.setMessage("No hay dirección del servidor de consultas");
        msg.setTitle("@Error@");
        return false;
      } else if (axmlConf[0].dato3 == null || axmlConf[0].dato3.equals("")) {
        msg.setType("Error");
        msg.setMessage("No hay un nombre de la base de datos del servidor de consultas");
        msg.setTitle("@Error@");
        return false;
      } else if (axmlConf[0].dato4 == null || axmlConf[0].dato4.equals("")) {
        msg.setType("Error");
        msg.setMessage("No hay un puerto de la base de datos del servidor de consultas");
        msg.setTitle("@Error@");
        return false;
      } else if (axmlConf[0].dato5 == null || axmlConf[0].dato5.equals("")) {
        msg.setType("Error");
        msg.setMessage("No hay un usuario de la base de datos del servidor de consultas");
        msg.setTitle("@Error@");
        return false;
      } else if (axmlConf[0].dato6 == null || axmlConf[0].dato6.equals("")) {
        msg.setType("Error");
        msg.setMessage("No hay una contraseña de la base de daros del servidor de consultas");
        msg.setTitle("@Error@");
        return false;
      } else if (axmlConf[0].dato7 == null || axmlConf[0].dato7.equals("")) {
        msg.setType("Error");
        msg.setMessage("No hay un CI/RUC del cliente de la factura");
        msg.setTitle("@Error@");
        return false;
      } else if (axmlConf[0].dato8 == null || axmlConf[0].dato8.equals("")) {
        msg.setType("Error");
        msg.setMessage("No hay un email del cliente de la factura, para enviar la factura al cliente");
        msg.setTitle("@Error@");
        return false;
      } else if (validarEmail(axmlConf[0].dato8)) {
        msg.setType("Error");
        msg.setMessage("El email del cliente no es valido");
        msg.setTitle("@Error@");
        return false;
      } else {
        return true;
      }
    } else {
      msg.setType("Error");
      msg.setMessage("No se ha encontrado una configuración del servidor de facturación electrónica");
      msg.setTitle("@Error@");
      return false;
    }
  }

  /*
   * Frunción para verificar el email del cliente
   */
  private boolean validarEmail(String strEmail) {
    Pattern pattern = Pattern.compile(PATTERN_EMAIL);
    Matcher matcher = pattern.matcher(strEmail);

    return !matcher.matches();
  }

}
