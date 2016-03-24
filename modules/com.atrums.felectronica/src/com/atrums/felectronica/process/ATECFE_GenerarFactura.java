package com.atrums.felectronica.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.common.invoice.Invoice;

public class ATECFE_GenerarFactura {

  ATECFE_Funciones_Aux ope = new ATECFE_Funciones_Aux();

  public boolean generarFacturaXML(String strCinvoice, ConnectionProvider conn, String strUser,
      OBError msg) throws Exception {

    Hashtable<String, String> hstClave = new Hashtable<String, String>();

    /*
     * Realizando la consulta del c_invoice
     */
    Invoice invDato = OBDal.getInstance().get(Invoice.class, strCinvoice);

    File flXml = File.createTempFile("documento", ".xml", null);
    flXml.deleteOnExit();

    /*
     * Crea documento XML
     */
    Document docXML = DocumentHelper.createDocument();

    /*
     * Formato del documento XML
     */
    final OutputFormat ofFormat = OutputFormat.createPrettyPrint();

    /*
     * Creando nodo y agregando al documento XML
     */

    /*
     * Realizando una consulta del tipo de documento
     */
    DocumentType dctDato = OBDal.getInstance().get(DocumentType.class,
        invDato.getTransactionDocument().getId());

    Element elmfac = null;

    if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
        || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
      elmfac = docXML.addElement("factura");

      elmfac.addAttribute("id", "comprobante");
      elmfac.addAttribute("version", "1.1.0");

    } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
        || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
      elmfac = docXML.addElement("notaCredito");

      elmfac.addAttribute("id", "comprobante");
      elmfac.addAttribute("version", "1.0.0");

    } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
        || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
      elmfac = docXML.addElement("notaDebito");

      elmfac.addAttribute("id", "comprobante");
      elmfac.addAttribute("version", "1.0.0");

    } else {
      msg.setType("Error");
      String strMensaje = "No existe ese tipo de documento para el SRI, "
          + "solo existen en está plantilla el "
          + "<1> Factura, "
          + "<4> Nota de Crédito, "
          + "<5>  Nota de Débito, su tipo de documento es: "
          + dctDato.getCoTipoComprobanteAutorizadorSRI()
          + ", por favor cambie el tipo de documento si quiere facturar con el SRI Electrónicamente";
      msg.setMessage(strMensaje);
      msg.setTitle("@Error@");
      return false;
    }

    /*
     * Agregando la información tributaria al documento
     */
    final Element elminftri = elmfac.addElement("infoTributaria");

    if (invDato.getClient() != null) {
      Client cltDato = OBDal.getInstance().get(Client.class, invDato.getClient().getId());

      /*
       * Creando formatos para las fechas de la factura
       */
      Date cldFechaIn = invDato.getInvoiceDate();
      SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat sdfFormatoClave = new SimpleDateFormat("ddMMyyyy");

      /*
       * Realizando una consulta de la organización
       */
      OrganizationInformation oriDato = OBDal.getInstance().get(OrganizationInformation.class,
          invDato.getOrganization().getId());

      /*
       * Realizando una consulta del tercero de la factura
       */
      BusinessPartner bspDato = OBDal.getInstance().get(BusinessPartner.class,
          invDato.getBusinessPartner().getId());

      /*
       * Realizando una consulta de la direccion de la Matriz
       */
      ATECFEGenerarXmlData[] axmlDirMatriz = ATECFEGenerarXmlData.methodSelDirMatriz(conn);

      String strDirMat = "";
      if (axmlDirMatriz != null && axmlDirMatriz.length == 1) {
        strDirMat = axmlDirMatriz[0].dato1;
      }

      /*
       * Realizando una consulta de la direccion de la organización
       */
      ATECFEGenerarXmlData[] axmlDirec = ATECFEGenerarXmlData.methodSeleccionarDirec(conn, invDato
          .getOrganization().getId());

      String strDir = "";
      if (axmlDirec != null && axmlDirec.length == 1) {
        strDir = axmlDirec[0].dato1;
      }

      /*
       * Verificando si se puede crear la cabecera de la factura y la clave de acceso
       */
      if (ope.generarCabecera(elminftri, cltDato.getAtecfeTipoambiente(), dctDato
          .getCoTipoComprobanteAutorizadorSRI().toString(), oriDato.getTaxID(), invDato
          .getCoNroEstab(), invDato.getCoPuntoEmision(), invDato.getDocumentNo(), cltDato
          .getAtecfeCodinumerico(), cltDato.getAtecfeTipoemisi(), sdfFormatoClave
          .format(cldFechaIn), ope.normalizacionPalabras(strDirMat), ope
          .normalizacionPalabras(cltDato.getName()), ope.normalizacionPalabras(cltDato.getName()),
          msg, hstClave)
          && dctDato != null && oriDato != null && bspDato != null) {

        /*
         * Agregando la información de la factura al documento
         */
        Element elminffac = null;

        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
          elminffac = elmfac.addElement("infoFactura");
        } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
          elminffac = elmfac.addElement("infoNotaCredito");
        } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
          elminffac = elmfac.addElement("infoNotaDebito");
        } else {
          msg.setType("Error");
          String strMensaje = "No existe ese tipo de documento para el SRI, "
              + "solo existen en está plantilla el "
              + "<1> Factura, "
              + "<4> Nota de Crédito, "
              + "<5>  Nota de Débito, su tipo de documento es: "
              + dctDato.getCoTipoComprobanteAutorizadorSRI()
              + ", por favor cambie el tipo de documento si quiere facturar con el SRI Electrónicamente";
          msg.setMessage(strMensaje);
          msg.setTitle("@Error@");
          return false;
        }

        /*
         * Agregando la fecha de emision de la factura
         */
        elminffac.addElement("fechaEmision").addText(sdfFormato.format(cldFechaIn));

        /*
         * Agregando la dirección del Establecimiento
         */
        if (!strDir.equals("")) {
          elminffac.addElement("dirEstablecimiento").addText(ope.normalizacionPalabras(strDir));
        }

        String strIden = null;

        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
          if (bspDato.getCOTipoIdentificacion().toString().equals("01")
              || bspDato.getCOTipoIdentificacion().toString().equals("1")) {
            elminffac.addElement("tipoIdentificacionComprador").addText("04");
          } else if (bspDato.getCOTipoIdentificacion().toString().equals("02")
              || bspDato.getCOTipoIdentificacion().toString().equals("2")) {
            elminffac.addElement("tipoIdentificacionComprador").addText("05");
          } else if (bspDato.getCOTipoIdentificacion().toString().equals("03")
              || bspDato.getCOTipoIdentificacion().toString().equals("3")) {
            elminffac.addElement("tipoIdentificacionComprador").addText("06");
          } else if (bspDato.getCOTipoIdentificacion().toString().equals("07")
              || bspDato.getCOTipoIdentificacion().toString().equals("7")) {
            elminffac.addElement("tipoIdentificacionComprador").addText("07");
            strIden = "9999999999999";
          } else {
            msg.setType("Error");
            msg.setMessage("El cliente tiene que tener un tipo de identificacion valido ");
            msg.setTitle("@Error@");
            return false;
          }
        }

        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
          if (bspDato.getName2() != null) {
            elminffac.addElement("razonSocialComprador").addText(
                ope.normalizacionPalabras(bspDato.getName2()));
          } else if (bspDato.getName() != null) {
            elminffac.addElement("razonSocialComprador").addText(
                ope.normalizacionPalabras(bspDato.getName()));
          } else {
            msg.setType("Error");
            msg.setMessage("Es necesaria la razón social del comprador");
            msg.setTitle("@Error@");
            return false;
          }
        }

        /*
         * Agregando la identificación del comprador
         */
        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
          if (bspDato.getTaxID() != null) {

            if (strIden == null) {
              strIden = bspDato.getTaxID();
            }

            elminffac.addElement("identificacionComprador").addText(strIden);
          } else {
            msg.setType("Error");
            msg.setMessage("Es necesaria la CI/RUC/Pasaporte");
            msg.setTitle("@Error@");
            return false;
          }
        }

        /*
         * Agregando el número de contribuyente especial
         */
        if (cltDato.getAtecfeNumresolsri() != null) {
          String strNumeReso = cltDato.getAtecfeNumresolsri();
          for (int i = 0; i < (3 - cltDato.getAtecfeNumresolsri().length()); i++) {
            strNumeReso = "0" + strNumeReso;
          }

          if (strNumeReso.length() >= 3 && strNumeReso.length() <= 5) {
            elminffac.addElement("contribuyenteEspecial").addText(strNumeReso);
          } else {
            msg.setType("Error");
            msg.setMessage("El número de contribuyente es de máximo 5 caracteres");
            msg.setTitle("@Error@");
            return false;
          }
        }

        /*
         * Agregando información de la contabilidad
         */
        if (cltDato.isAtecfeObligcontabi()) {
          elminffac.addElement("obligadoContabilidad").addText("SI");
        } else {
          elminffac.addElement("obligadoContabilidad").addText("NO");
        }

        /*
         * Agregando el tipo de identificación del comprador
         */
        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
          if (bspDato.getCOTipoIdentificacion().toString().equals("01")
              || bspDato.getCOTipoIdentificacion().toString().equals("1")) {
            elminffac.addElement("tipoIdentificacionComprador").addText("04");
          } else if (bspDato.getCOTipoIdentificacion().toString().equals("02")
              || bspDato.getCOTipoIdentificacion().toString().equals("2")) {
            elminffac.addElement("tipoIdentificacionComprador").addText("05");
          } else if (bspDato.getCOTipoIdentificacion().toString().equals("03")
              || bspDato.getCOTipoIdentificacion().toString().equals("3")) {
            elminffac.addElement("tipoIdentificacionComprador").addText("06");
          } else if (bspDato.getCOTipoIdentificacion().toString().equals("07")
              || bspDato.getCOTipoIdentificacion().toString().equals("7")) {
            elminffac.addElement("tipoIdentificacionComprador").addText("07");
            strIden = "9999999999999";
          } else {
            msg.setType("Error");
            msg.setMessage("El cliente tiene que tener un tipo de identificacion valido ");
            msg.setTitle("@Error@");
            return false;
          }
        }

        /*
         * Agregando la guia de remisión
         */
        if (null != null) {
          elminffac.addElement("guiaRemision").addText(null);
        }

        /*
         * Agregando la razon social
         */
        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
          if (bspDato.getName2() != null) {
            elminffac.addElement("razonSocialComprador").addText(
                ope.normalizacionPalabras(bspDato.getName2()));
          } else if (bspDato.getName() != null) {
            elminffac.addElement("razonSocialComprador").addText(
                ope.normalizacionPalabras(bspDato.getName()));
          } else {
            msg.setType("Error");
            msg.setMessage("Es necesaria la razón social del comprador");
            msg.setTitle("@Error@");
            return false;
          }
        }

        /*
         * Agregando la identificación del comprador
         */
        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
          if (bspDato.getTaxID() != null) {

            if (strIden == null) {
              strIden = bspDato.getTaxID();
            }

            elminffac.addElement("identificacionComprador").addText(strIden);
          } else {
            msg.setType("Error");
            msg.setMessage("Es necesaria la CI/RUC/Pasaporte");
            msg.setTitle("@Error@");
            return false;
          }
        }

        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {

          if (invDato.getAtecfeCInvoice() != null) {
            Invoice invDatoAux = OBDal.getInstance().get(Invoice.class,
                invDato.getAtecfeCInvoice().getId());

            DocumentType dctDatoAux = OBDal.getInstance().get(DocumentType.class,
                invDatoAux.getTransactionDocument().getId());

            if (dctDatoAux.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
              elminffac.addElement("codDocModificado").addText("01");
            } else if (dctDatoAux.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
              elminffac.addElement("codDocModificado").addText("04");
            } else if (dctDatoAux.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
              elminffac.addElement("codDocModificado").addText("05");
            } else {
              msg.setType("Error");
              String strMensaje = "No existe ese tipo de documento de la factura relacionada a la Nota de Crédito"
                  + " para el SRI, solo existen en está plantilla el "
                  + "<1> Factura, "
                  + "<4> Nota de Crédito, "
                  + "<5>  Nota de Débito, su tipo de documento es: "
                  + dctDatoAux.getCoTipoComprobanteAutorizadorSRI()
                  + ", por favor cambie el tipo de documento si quiere facturar con el SRI Electrónicamente";
              msg.setMessage(strMensaje);
              msg.setTitle("@Error@");
              return false;
            }

            String strNrEstraAux = invDatoAux.getCoNroEstab();
            String strNrPuntEmAux = invDatoAux.getCoPuntoEmision();
            String strNrDocAux = invDatoAux.getDocumentNo();

            String strSerie = "";

            if (strNrEstraAux.length() <= 3 && strNrPuntEmAux.length() <= 3) {

              for (int i = 0; i < (3 - strNrEstraAux.length()); i++) {
                strSerie = strSerie + "0";
              }

              strSerie = strSerie + strNrEstraAux + "-";

              for (int i = 0; i < (3 - strNrPuntEmAux.length()); i++) {
                strSerie = strSerie + "0";
              }

              strSerie = strSerie + strNrPuntEmAux + "-";
            }

            if (strNrDocAux.length() <= 9) {
              for (int i = 0; i < (9 - strNrDocAux.length()); i++) {
                strSerie = strSerie + "0";
              }
              strSerie = strSerie + strNrDocAux;
            }

            elminffac.addElement("numDocModificado").addText(strSerie);

            elminffac.addElement("fechaEmisionDocSustento").addText(
                sdfFormato.format(invDatoAux.getInvoiceDate()));

          } else {
            msg.setType("Error");
            msg.setMessage("La nota de crédito tiene que estar relacionada a una factura");
            msg.setTitle("@Error@");
            return false;
          }
        }

        /*
         * Agregando información del total sin impuestos
         */
        elminffac.addElement("totalSinImpuestos").addText(invDato.getSummedLineAmount().toString());

        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
          elminffac.addElement("valorModificacion").addText(
              invDato.getGrandTotalAmount().toString());
          elminffac.addElement("moneda").addText("DOLAR");
        }

        Element elmDescTot = null;

        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
          elmDescTot = elminffac.addElement("totalDescuento");
        }

        /*
         * Realizando una consulta de los impuestos de la factura
         */
        ATECFEGenerarXmlData[] axmlImpuestos = ATECFEGenerarXmlData.methodSeleccionarImpues(conn,
            strCinvoice);

        /*
         * Verificando si hay impuestos para agregarlos al documento
         */
        if (axmlImpuestos != null && axmlImpuestos.length > 0) {
          Element elmtolcimp = null;

          if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
              || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")
              || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
              || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
            elmtolcimp = elminffac.addElement("totalConImpuestos");
          } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
              || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
            elmtolcimp = elminffac.addElement("impuestos");
          }

          for (int i = 0; i < axmlImpuestos.length; i++) {

            Element elmtolimp = null;

            if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
              elmtolimp = elmtolcimp.addElement("totalImpuesto");
            } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
              elmtolimp = elmtolcimp.addElement("impuesto");
            }

            if (!axmlImpuestos[i].dato1.equals("")) {
              elmtolimp.addElement("codigo").addText(axmlImpuestos[i].dato1);
            } else {
              msg.setType("Error");
              msg.setMessage("El impuesto tiene que tener un código");
              msg.setTitle("@Error@");
              return false;
            }

            if (!axmlImpuestos[i].dato2.equals("")) {
              elmtolimp.addElement("codigoPorcentaje").addText(axmlImpuestos[i].dato2);
            } else {
              msg.setType("Error");
              msg.setMessage("El impuesto tiene que tener un código porcentaje");
              msg.setTitle("@Error@");
              return false;
            }

            if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
              elmtolimp.addElement("tarifa").addText(axmlImpuestos[i].dato5);
            }

            elmtolimp.addElement("baseImponible").addText(axmlImpuestos[i].dato3);
            elmtolimp.addElement("valor").addText(axmlImpuestos[i].dato4);
          }
        } else {
          msg.setType("Error");
          msg.setMessage("El documento tiene que tener detalles");
          msg.setTitle("@Error@");
          return false;
        }

        /*
         * Agregando datos adicionales
         */

        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
          elminffac.addElement("propina").addText("0.00");
          elminffac.addElement("importeTotal").addText(invDato.getGrandTotalAmount().toString());
          elminffac.addElement("moneda").addText("DOLAR");
        }

        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
          if (invDato.getDescription() != null) {
            if (!invDato.getDescription().equals("")) {
              elminffac.addElement("motivo").addText(
                  ope.normalizacionPalabras(invDato.getDescription()));
            } else {
              msg.setType("Error");
              msg.setMessage("Por favor ingrese el motivo de la nota de crédito en la "
                  + "descripción de la nota de crédito, caso contrario no se "
                  + "podrá autorizar por medio del SRI");
              msg.setTitle("@Error@");
              return false;
            }
          } else {
            msg.setType("Error");
            msg.setMessage("Por favor ingrese el motivo de la nota de crédito en la "
                + "descripción de la nota de crédito, caso contrario no se "
                + "podrá autorizar por medio del SRI");
            msg.setTitle("@Error@");
            return false;
          }
        }

        /*
         * Realizando una consulta de los detalles de la factura
         */
        ATECFEGenerarXmlData[] axmlDetalles = ATECFEGenerarXmlData.methodSeleccionarDetalles(conn,
            strCinvoice);

        double intTotalDesc = 0;

        Element elmdetimps = null;

        /*
         * Agregando los detalles a la factura
         */
        if (axmlDetalles != null && axmlDetalles.length > 0) {
          Element elmdetfac = null;
          if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
              || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")
              || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
              || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
            elmdetfac = elmfac.addElement("detalles");
          } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
              || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
            elmdetfac = elmfac.addElement("motivos");
          }

          for (int i = 0; i < axmlDetalles.length; i++) {
            Element elmdeta = null;

            if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
              elmdeta = elmdetfac.addElement("detalle");
            } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
              elmdeta = elmdetfac.addElement("motivo");
            }

            if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
              if (axmlDetalles[i].dato1.length() > 25) {
                msg.setType("Error");
                msg.setMessage("El identificador del producto tiene que tener máximo 25 caracteres, su identificador de producto es: "
                    + axmlDetalles[i].dato1 + " y longitud de " + axmlDetalles[i].dato1.length());
                msg.setTitle("@Error@");
                return false;
              }
            }

            if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
              elmdeta.addElement("codigoPrincipal").addText(
                  ope.normalizacionPalabras(axmlDetalles[i].dato1));
            } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
              elmdeta.addElement("codigoInterno").addText(
                  ope.normalizacionPalabras(axmlDetalles[i].dato1));
            }

            if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
              if (!axmlDetalles[i].dato2.equals("")) {
                elmdeta.addElement("codigoAuxiliar").addText(
                    ope.normalizacionPalabras(axmlDetalles[i].dato2));
              }
              if (!axmlDetalles[i].dato3.equals("")) {
                elmdeta.addElement("descripcion").addText(
                    ope.normalizacionPalabras(axmlDetalles[i].dato3));
              } else {
                msg.setType("Error");
                msg.setMessage("Los detalles tienen que tener una descripción");
                msg.setTitle("@Error@");
                return false;
              }
            } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
              if (!axmlDetalles[i].dato3.equals("")) {
                elmdeta.addElement("razon").addText(
                    ope.normalizacionPalabras(axmlDetalles[i].dato3));
              }
            }

            /*
             * Agregando información del detalle
             */
            if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
              elmdeta.addElement("cantidad").addText(axmlDetalles[i].dato4);
              elmdeta.addElement("precioUnitario").addText(axmlDetalles[i].dato5);
              elmdeta.addElement("descuento").addText(axmlDetalles[i].dato6);

              intTotalDesc = intTotalDesc + Double.parseDouble(axmlDetalles[i].dato6);

              elmdeta.addElement("precioTotalSinImpuesto").addText(axmlDetalles[i].dato7);

            } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
              elmdeta.addElement("valor").addText(axmlDetalles[i].dato7);
            }

            ATECFEGenerarXmlData[] axmldetImps = ATECFEGenerarXmlData.methodSeleccionarDetalTax(
                conn, axmlDetalles[i].dato8);

            if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
                || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
              elmdetimps = elmdeta.addElement("impuestos");

              if (axmldetImps != null && axmldetImps.length > 0) {

                for (int j = 0; j < axmldetImps.length; j++) {
                  Element elmdetimp = elmdetimps.addElement("impuesto");

                  if (!axmldetImps[j].dato1.equals("")) {
                    elmdetimp.addElement("codigo").addText(axmldetImps[j].dato1);
                  } else {
                    msg.setType("Error");
                    msg.setMessage("El impuesto tiene que tener un código");
                    msg.setTitle("@Error@");
                    return false;
                  }

                  if (!axmldetImps[j].dato2.equals("")) {
                    elmdetimp.addElement("codigoPorcentaje").addText(axmldetImps[j].dato2);
                  } else {
                    msg.setType("Error");
                    msg.setMessage("El impuesto tiene que tener un código de porcentaje");
                    msg.setTitle("@Error@");
                    return false;
                  }

                  if (!axmldetImps[j].dato3.equals("")) {
                    elmdetimp.addElement("tarifa").addText(axmldetImps[j].dato3);
                  } else {
                    msg.setType("Error");
                    msg.setMessage("El impuesto tiene que tener una tarifa");
                    msg.setTitle("@Error@");
                    return false;
                  }

                  elmdetimp.addElement("baseImponible").addText(axmldetImps[j].dato4);
                  elmdetimp.addElement("valor").addText(axmldetImps[j].dato5);
                }
              }
            }
          }

        } else {
          msg.setType("Error");
          msg.setMessage("El documento tiene que tener detalles");
          msg.setTitle("@Error@");
          return false;
        }

        if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
          elmDescTot.setText(String.valueOf(intTotalDesc));
        } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
            || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
          elminffac.addElement("valorTotal").addText(invDato.getGrandTotalAmount().toString());
        }

        /*
         * Añadiendo campos adicionales
         */
        ope.addCamposAdic(elmfac, conn, invDato.getClient().getId());

        /*
         * Guardando informacion en el documento xml
         */

        final XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(flXml),
            "utf-8"), ofFormat);
        writer.write(docXML);
        writer.flush();
        writer.close();

        /*
         * Firmando Digitalmente documento
         */
        flXml = ope.firmarDocumento(flXml, conn, strUser, msg);

        if (flXml != null) {
          /*
           * Convirtiendo archivo en base64 para mandar a aguardar en la base de datos
           */
          byte[] bytes = ope.filetobyte(flXml);
          String encodedString = new String(bytes, "UTF-8");

          /*
           * Creando objeto para que alverge los resultados de enviar el documento al SRI
           */
          final Hashtable<String, String> hstResult = new Hashtable<String, String>();

          /*
           * Mandando Documento al SRI
           */

          Hashtable<String, String> hstInvDato = new Hashtable<String, String>();

          hstInvDato.put("id", invDato.getId());
          hstInvDato.put("docid", invDato.getTransactionDocument().getId());
          hstInvDato.put("atecdocsts", invDato.getAtecfeDocstatus());

          File flFirmado = new File("/opt/OpenbravoERP/xmlsri/" + hstClave.get("claveacc") + ".xml");

          InputStream input = new FileInputStream(flXml);
          OutputStream ouput = new FileOutputStream(flFirmado);

          byte[] buf = new byte[2024];
          int len;

          while ((len = input.read(buf)) > 0) {
            ouput.write(buf, 0, len);
          }

          input.close();
          ouput.close();

          if (ope.enviarDocSRI(encodedString, cltDato, hstClave.get("claveacc"), hstResult,
              hstInvDato, conn)) {

            /*
             * Guardando documento en la base de datos
             */

            Calendar cldFecha = new GregorianCalendar();
            String strFechaAut = "";

            if (hstResult.get("fecaut") == null || hstResult.get("fecaut").equals("")) {
              strFechaAut = sdfFormato.format(cldFecha.getTime());
            } else {
              strFechaAut = hstResult.get("fecaut");
            }

            if (ATECFEGenerarXmlData.methodActualizarInvo(conn, hstClave.get("claveacc"),
                hstResult.get("doc"), hstResult.get("mens"), hstResult.get("std"),
                hstResult.get("dstd"), hstResult.get("numaut"), strFechaAut,
                hstResult.get("fecaut"), strCinvoice) == 1) {

              flXml = ope.bytetofile(hstResult.get("doc").getBytes());

              input = new FileInputStream(flXml);
              ouput = new FileOutputStream(flFirmado);

              buf = new byte[2024];
              len = 0;

              while ((len = input.read(buf)) > 0) {
                ouput.write(buf, 0, len);
              }

              input.close();
              ouput.close();

              List<File> lisdoc = new ArrayList<File>();
              lisdoc.add(flFirmado);

              File flPdf = null;

              if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("1")
                  || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("01")) {
                flPdf = ope.generarPDF(conn,
                    "@basedesign@/com/atrums/felectronica/erpReport/Rpt_Factura.jrxml", "Factura",
                    invDato.getId());
              } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("4")
                  || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("04")) {
                flPdf = ope.generarPDF(conn,
                    "@basedesign@/com/atrums/felectronica/erpReport/Rpt_NotaCredito.jrxml",
                    "Nota_Credito", invDato.getId());
              } else if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("5")
                  || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("05")) {
                flPdf = ope.generarPDF(conn,
                    "@basedesign@/com/atrums/felectronica/erpReport/Rpt_NotaDebito.jrxml",
                    "Nota_Debito", invDato.getId());
              }

              if (flPdf != null)
                lisdoc.add(flPdf);

              if (hstResult.get("dstd").toString().equals("RZ")) {
                msg.setType("Error");
                msg.setMessage("Su documento tiene el siguiente error: " + hstResult.get("mens")
                    + ", corriga el error e intentelo de nuevo");
                msg.setTitle("@Error@");
                return false;
              }

              if (hstResult.get("dstd").toString().equals("AP")
                  || hstResult.get("dstd").toString().equals("RC")
                  || hstResult.get("dstd").toString().equals("RG")
                  || hstResult.get("dstd").toString().equals("PD")) {
                ATECFEGenerarXmlData[] axmlEmail = ATECFEGenerarXmlData.methodSeleccionarEmail(
                    conn, invDato.getBusinessPartner().getId());

                String strSubject = "Factura Electronica de " + axmlEmail[0].dato2;

                String strContenido = "Señor/a\n" + axmlEmail[0].dato3
                    + "\n\nUd tiene un documento electronico que puede ser consultada en: "
                    + axmlEmail[0].dato4 + "\nCon los credeciales: \n\n -Usuario: "
                    + axmlEmail[0].dato5 + "\n -Contraseña: " + axmlEmail[0].dato5
                    + "\n\n\nAtentamente " + axmlEmail[0].dato2;

                String strMensaje = "";
                if (hstResult.get("dstd").toString().equals("RC")) {
                  strMensaje = "Su documento a sido recibido por el SRI su autorización sera tramitada más tarde, ";
                } else if (hstResult.get("dstd").toString().equals("AP")) {
                  strMensaje = "Su documento a sido autorizado por el SRI, ";
                }

                if (ope.enviarCorreo(axmlEmail[0].dato1, strSubject, strContenido, null, lisdoc)) {
                  msg.setType("Success");
                  msg.setTitle("Mensaje");
                  msg.setMessage(strMensaje + "y fue enviado al correo electronico del cliente");
                  flXml.delete();
                  if (hstResult.get("dstd").toString().equals("RC")
                      || hstResult.get("dstd").toString().equals("RG")
                      || hstResult.get("dstd").toString().equals("PD")) {
                    return false;
                  }
                  return true;
                } else {
                  msg.setType("Error");
                  msg.setMessage(strMensaje
                      + "y no se pudo enviar el correo electronico al cliente, revise su configuración de email");
                  msg.setTitle("@Error@");
                  flXml.delete();
                  return false;
                }
              } else {
                msg.setType("Error");
                msg.setMessage("El documento XML ha sido generado pero no se encuentra activo el servicio del SRI, el documento será tramitado automáticamente más tarde");
                msg.setTitle("@Error@");
                flXml.delete();
                return false;
              }
            } else {
              msg.setType("Error");
              msg.setMessage("Documento XML no generado");
              msg.setTitle("@Error@");
              return false;
            }
          } else {
            msg.setType("Error");
            msg.setMessage("Documento XML no autorizado por problemas de comunicación con el servidor de transacciones, revise la configuracion del servidor de facturación electronica"
                + " e intentelo más tarde");
            msg.setTitle("@Error@");
            return false;
          }
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
    msg.setType("Error");
    msg.setMessage("No hay un tercero en el documento");
    msg.setTitle("@Error@");
    return false;
  }
}
