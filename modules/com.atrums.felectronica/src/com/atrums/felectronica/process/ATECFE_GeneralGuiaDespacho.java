package com.atrums.felectronica.process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.shipping.ShippingCompany;

public class ATECFE_GeneralGuiaDespacho {

  ATECFE_Funciones_Aux ope = new ATECFE_Funciones_Aux();

  public void generarFacturaXMLGre(String strMInout, ConnectionProvider conn, String strUser,
      OBError msg) throws Exception {

    Hashtable<String, String> hstClave = new Hashtable<String, String>();

    ShipmentInOut spiDato = OBDal.getInstance().get(ShipmentInOut.class, strMInout);

    File flXml = File.createTempFile("documento.xml", null);
    flXml.deleteOnExit();

    /*
     * Crea documento XML
     */
    Document docXML = DocumentHelper.createDocument();

    /*
     * Formato del documento XML
     */
    final OutputFormat ofFormat = OutputFormat.createPrettyPrint();
    ofFormat.setEncoding("utf-8");
    ofFormat.setTrimText(true);

    /*
     * Creando nodo y agregando al documento XML
     */
    final Element elmgre = docXML.addElement("guiaRemision");
    elmgre.addAttribute("id", "comprobante");
    elmgre.addAttribute("version", "1.0.0");

    /*
     * Agregando la información tributaria al documento
     */
    final Element elminftri = elmgre.addElement("infoTributaria");

    if (spiDato.getClient() != null) {
      Client cltDato = OBDal.getInstance().get(Client.class, spiDato.getClient().getId());

      /*
       * Creando formatos para las fechas de la factura
       */
      Date cldFechaIn = spiDato.getMovementDate();
      SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat sdfFormatoClave = new SimpleDateFormat("ddMMyyyy");
      SimpleDateFormat sdfFormatoPerido = new SimpleDateFormat("MM/yyyy");

      /*
       * Realizando una consulta del tipo de documento
       */
      DocumentType dctDato = OBDal.getInstance().get(DocumentType.class,
          spiDato.getDocumentType().getId());

      /*
       * Realizando una consulta de la organización
       */
      OrganizationInformation oriDato = OBDal.getInstance().get(OrganizationInformation.class,
          spiDato.getOrganization().getId());

      /*
       * Realizando una consulta del tercero de la factura
       */
      ShippingCompany shcDato = OBDal.getInstance().get(ShippingCompany.class,
          spiDato.getShippingCompany().getId());

      BusinessPartner bspDato = OBDal.getInstance().get(BusinessPartner.class,
          shcDato.getBusinessPartner().getId());

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
      ATECFEGenerarXmlData[] axmlDirec = ATECFEGenerarXmlData.methodSeleccionarDirec(conn, spiDato
          .getOrganization().getId());

      String strDir = "";
      if (axmlDirec != null && axmlDirec.length == 1) {
        strDir = axmlDirec[0].dato1;
      }

      Organization orgDato = OBDal.getInstance().get(Organization.class,
          spiDato.getOrganization().getId());

      if (ope.generarCabecera(elminftri, cltDato.getAtecfeTipoambiente(), dctDato
          .getCoTipoComprobanteAutorizadorSRI().toString(), oriDato.getTaxID(), orgDato
          .getCoNroEstab(), orgDato.getCoPuntoEmision(), spiDato.getDocumentNo(), cltDato
          .getAtecfeCodinumerico(), cltDato.getAtecfeTipoemisi(), sdfFormatoClave
          .format(cldFechaIn), strDirMat, cltDato.getName(), cltDato.getName(), msg, hstClave)
          && dctDato != null && oriDato != null && bspDato != null) {

        /*
         * Agregando la información de la retencion al documento
         */
        final Element elmcomgre = elmgre.addElement("infoGuiaRemision");

        /*
         * Agregando la dirección del Establecimiento
         */
        if (!strDir.equals("")) {
          elmcomgre.addElement("dirEstablecimiento").addText(strDir);
        }

        /*
         * Agregando la dirección del Establecimiento
         */
        if (!strDir.equals("")) {
          elmcomgre.addElement("dirPartida").addText(strDir);
        }

        /*
         * Agregando la razon social
         */
        if (bspDato.getName2() != null) {
          elmcomgre.addElement("razonSocialTransportista").addText(bspDato.getName2());
        } else if (bspDato.getName() != null) {
          elmcomgre.addElement("razonSocialTransportista").addText(bspDato.getName());
        } else {
          msg.setType("Error");
          msg.setMessage("Es necesaria la razón social del comprador");
          msg.setTitle("@Error@");
          return;
        }

        if (bspDato.getCOTipoIdentificacion() != null) {
          String strTipoIden = bspDato.getCOTipoIdentificacion();
        }

        /*
         * Agregando la identificación del comprador
         */
        if (bspDato.getTaxID() != null) {
          elmcomgre.addElement("rucTransportista").addText(bspDato.getTaxID());
        } else {
          msg.setType("Error");
          msg.setMessage("Es necesaria la CI/RUC");
          msg.setTitle("@Error@");
          return;
        }

      } else {
        return;
      }
    }
    msg.setType("Error");
    msg.setMessage("No hay un tercero en la retención");
    msg.setTitle("@Error@");
    return;
  }
}
