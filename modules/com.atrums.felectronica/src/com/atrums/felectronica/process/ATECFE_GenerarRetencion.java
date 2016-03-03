package com.atrums.felectronica.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.common.invoice.Invoice;

import com.atrums.contabilidad.data.CO_Retencion_Compra;

public class ATECFE_GenerarRetencion {

  ATECFE_Funciones_Aux ope = new ATECFE_Funciones_Aux();

  public boolean generarFacturaXMLRet(String strCoRetecionCompra, ConnectionProvider conn,
      String strUser, OBError msg) throws Exception {

    Hashtable<String, String> hstClave = new Hashtable<String, String>();

    CO_Retencion_Compra crcDato = OBDal.getInstance().get(CO_Retencion_Compra.class,
        strCoRetecionCompra);

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
    final Element elmret = docXML.addElement("comprobanteRetencion");
    elmret.addAttribute("id", "comprobante");
    elmret.addAttribute("version", "1.0.0");

    /*
     * Agregando la información tributaria al documento
     */
    final Element elminftri = elmret.addElement("infoTributaria");

    if (crcDato.getClient() != null) {
      Client cltDato = OBDal.getInstance().get(Client.class, crcDato.getClient().getId());

      /*
       * Creando formatos para las fechas de la factura
       */
      Date cldFechaIn = crcDato.getFechaEmisin();
      SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat sdfFormatoClave = new SimpleDateFormat("ddMMyyyy");
      SimpleDateFormat sdfFormatoPerido = new SimpleDateFormat("MM/yyyy");

      /*
       * Realizando una consulta del tipo de documento
       */
      DocumentType dctDato = OBDal.getInstance().get(DocumentType.class,
          crcDato.getDocumentType().getId());

      /*
       * Realizando una consulta de la organización
       */
      OrganizationInformation oriDato = OBDal.getInstance().get(OrganizationInformation.class,
          crcDato.getOrganization().getId());

      /*
       * Realizando una consulta del tercero de la factura
       */
      BusinessPartner bspDato = OBDal.getInstance().get(BusinessPartner.class,
          crcDato.getBpartner().getId());

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
      ATECFEGenerarXmlData[] axmlDirec = ATECFEGenerarXmlData.methodSeleccionarDirec(conn, crcDato
          .getOrganization().getId());

      String strDir = "";
      if (axmlDirec != null && axmlDirec.length == 1) {
        strDir = axmlDirec[0].dato1;
      }

      Invoice invDato = OBDal.getInstance().get(Invoice.class, crcDato.getInvoice().getId());

      Organization oriDatoAux = OBDal.getInstance().get(Organization.class,
          crcDato.getOrganization().getId());

      if (ope.generarCabecera(elminftri, cltDato.getAtecfeTipoambiente(), dctDato
          .getCoTipoComprobanteAutorizadorSRI().toString(), oriDato.getTaxID(), oriDatoAux
          .getCoNroEstab(), oriDatoAux.getCoPuntoEmision(), crcDato.getDocumentNo(), cltDato
          .getAtecfeCodinumerico(), cltDato.getAtecfeTipoemisi(), sdfFormatoClave
          .format(cldFechaIn), ope.normalizacionPalabras(strDirMat), ope
          .normalizacionPalabras(cltDato.getName()), ope.normalizacionPalabras(cltDato.getName()),
          msg, hstClave)
          && dctDato != null && oriDato != null && bspDato != null) {

        /*
         * Agregando la información de la retencion al documento
         */
        final Element elmcomret = elmret.addElement("infoCompRetencion");

        /*
         * Agregando la fecha de emision de la factura
         */
        elmcomret.addElement("fechaEmision").addText(sdfFormato.format(cldFechaIn));

        /*
         * Agregando la dirección del Establecimiento
         */
        if (!strDir.equals("")) {
          elmcomret.addElement("dirEstablecimiento").addText(ope.normalizacionPalabras(strDir));
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
            elmcomret.addElement("contribuyenteEspecial").addText(strNumeReso);
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
          elmcomret.addElement("obligadoContabilidad").addText("SI");
        } else {
          elmcomret.addElement("obligadoContabilidad").addText("NO");
        }

        /*
         * Agregando el tipo de identificación del comprador
         */

        String strIden = null;

        if (bspDato.getCOTipoIdentificacion().toString().equals("01")
            || bspDato.getCOTipoIdentificacion().toString().equals("1")) {
          elmcomret.addElement("tipoIdentificacionSujetoRetenido").addText("04");
        } else if (bspDato.getCOTipoIdentificacion().toString().equals("02")
            || bspDato.getCOTipoIdentificacion().toString().equals("2")) {
          elmcomret.addElement("tipoIdentificacionSujetoRetenido").addText("05");
        } else if (bspDato.getCOTipoIdentificacion().toString().equals("03")
            || bspDato.getCOTipoIdentificacion().toString().equals("3")) {
          elmcomret.addElement("tipoIdentificacionSujetoRetenido").addText("09");
        } else if (bspDato.getCOTipoIdentificacion().toString().equals("07")
            || bspDato.getCOTipoIdentificacion().toString().equals("7")) {
          elmcomret.addElement("tipoIdentificacionSujetoRetenido").addText("07");
          strIden = "9999999999999";
        } else {
          msg.setType("Error");
          msg.setMessage("El cliente tiene que tener un tipo de identificacion valido ");
          msg.setTitle("@Error@");
          return false;
        }

        /*
         * Agregando la razon social
         */
        if (bspDato.getName2() != null) {
          elmcomret.addElement("razonSocialSujetoRetenido").addText(
              ope.normalizacionPalabras(bspDato.getName2()));
        } else if (bspDato.getName() != null) {
          elmcomret.addElement("razonSocialSujetoRetenido").addText(
              ope.normalizacionPalabras(bspDato.getName()));
        } else {
          msg.setType("Error");
          msg.setMessage("Es necesaria la razón social del comprador");
          msg.setTitle("@Error@");
          return false;
        }

        /*
         * Agregando la identificación del comprador
         */
        if (bspDato.getTaxID() != null) {

          if (strIden == null) {
            strIden = bspDato.getTaxID();
          }

          elmcomret.addElement("identificacionSujetoRetenido").addText(strIden);
        } else {
          msg.setType("Error");
          msg.setMessage("Es necesaria la CI/RUC/Pasaporte");
          msg.setTitle("@Error@");
          return false;
        }

        if (crcDato.getFechaEmisin() != null) {
          elmcomret.addElement("periodoFiscal").addText(
              sdfFormatoPerido.format(crcDato.getFechaEmisin()));
        } else {
          msg.setType("Error");
          msg.setMessage("La retencion no está en un periodo fiscal");
          msg.setTitle("@Error@");
          return false;
        }

        /*
         * Agregando la información de la retencion al documento
         */
        final Element elmimps = elmret.addElement("impuestos");

        /*
         * Realizando una consulta de los impuestos de la factura
         */

        ATECFEGenerarXmlData[] axmlImpuestos = ATECFEGenerarXmlData.methodSeleccionarImpuesReten(
            conn, strCoRetecionCompra);

        String strNumDocSus = "";

        for (int i = 0; i < (3 - invDato.getCoNroEstab().length()); i++) {
          strNumDocSus = strNumDocSus + "0";
        }

        strNumDocSus = strNumDocSus + invDato.getCoNroEstab();

        for (int i = 0; i < (3 - invDato.getCoPuntoEmision().length()); i++) {
          strNumDocSus = strNumDocSus + "0";
        }

        strNumDocSus = strNumDocSus + invDato.getCoPuntoEmision();

        for (int i = 0; i < (9 - invDato.getDocumentNo().length()); i++) {
          strNumDocSus = strNumDocSus + "0";
        }

        strNumDocSus = strNumDocSus + invDato.getDocumentNo();

        /*
         * Verificando si hay impuestos para agregarlos al documento
         */
        if (axmlImpuestos != null && axmlImpuestos.length > 0) {

          for (int i = 0; i < axmlImpuestos.length; i++) {
            Element elmtolimp = elmimps.addElement("impuesto");

            if (!axmlImpuestos[i].dato1.equals("")) {
              elmtolimp.addElement("codigo").addText(axmlImpuestos[i].dato1);
            } else {
              msg.setType("Error");
              msg.setMessage("El tipo de retención tiene que tener un código");
              msg.setTitle("@Error@");
              return false;
            }

            if (!axmlImpuestos[i].dato2.equals("")) {
              elmtolimp.addElement("codigoRetencion").addText(axmlImpuestos[i].dato2);
            } else {
              msg.setType("Error");
              msg.setMessage("La retención tiene que tener un código");
              msg.setTitle("@Error@");
              return false;
            }

            elmtolimp.addElement("baseImponible").addText(axmlImpuestos[i].dato3);
            elmtolimp.addElement("porcentajeRetener").addText(axmlImpuestos[i].dato4);
            elmtolimp.addElement("valorRetenido").addText(axmlImpuestos[i].dato5);
            elmtolimp.addElement("codDocSustento").addText(axmlImpuestos[i].dato6);
            elmtolimp.addElement("numDocSustento").addText(strNumDocSus);
            elmtolimp.addElement("fechaEmisionDocSustento").addText(
                sdfFormato.format(invDato.getInvoiceDate()));
          }

        } else {
          msg.setType("Error");
          msg.setMessage("La retención tiene que tener detalles");
          msg.setTitle("@Error@");
          return false;
        }

        /*
         * Añadiendo campos adicionales
         */
        ope.addCamposAdic(elmret, conn, invDato.getClient().getId());

        /*
         * Guardando informacion en el documento xml
         */

        // new XMLWriter(new FileWriter(flXml), ofFormat);
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

          hstInvDato.put("id", crcDato.getId());
          hstInvDato.put("docid", crcDato.getDocumentType().getId());
          hstInvDato.put("atecdocsts", crcDato.getATECFEEstadoSRI());

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

            if (ATECFEGenerarXmlData.methodActualizarReten(conn, hstClave.get("claveacc"),
                hstResult.get("doc"), hstResult.get("mens"), hstResult.get("std"),
                hstResult.get("dstd"), hstResult.get("numaut"), hstResult.get("fecaut"),
                strCoRetecionCompra) == 1) {

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

              if (dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("7")
                  || dctDato.getCoTipoComprobanteAutorizadorSRI().toString().equals("07")) {
                flPdf = ope.generarPDF(conn,
                    "@basedesign@/com/atrums/felectronica/erpReport/Rpt_Retenciones.jrxml",
                    "Retencion", crcDato.getId());
              }

              if (flPdf != null)
                lisdoc.add(flPdf);

              if (hstResult.get("dstd").toString().equals("RZ")) {
                msg.setType("Error");
                msg.setMessage("Su documento tiene el siguiente error: " + hstResult.get("mens"));
                msg.setTitle("@Error@");
                return false;
              }

              ATECFEGenerarXmlData[] axmlEmail = ATECFEGenerarXmlData.methodSeleccionarEmail(conn,
                  invDato.getBusinessPartner().getId());

              if (hstResult.get("dstd").toString().equals("AP")
                  || hstResult.get("dstd").toString().equals("RC")
                  || hstResult.get("dstd").toString().equals("RG")
                  || hstResult.get("dstd").toString().equals("PD")) {
                String strSubject = "Documento electrónico de " + axmlEmail[0].dato2;

                String strContenido = "Señor/a\n" + axmlEmail[0].dato3
                    + "\n\nUd tiene 1 factura que puede ser consultada en: " + axmlEmail[0].dato4
                    + "\nCon los credeciales: \n\n -Usuario: " + axmlEmail[0].dato5
                    + "\n -Contraseña: " + axmlEmail[0].dato5 + "\n\n\nAtentamente "
                    + axmlEmail[0].dato2;

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
                  return true;
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
    msg.setMessage("No hay un tercero en la retención");
    msg.setTitle("@Error@");
    return false;
  }

}
