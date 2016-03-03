package com.atrums.felectronica.process;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.javasign.EnumFormatoFirma;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;

public class ATECFE_XAdESBESSignature extends ATECFE_GenericXMLSignature {

  private File flXmlDoc;

  public ATECFE_XAdESBESSignature(File flDoc) {
    super();
    this.flXmlDoc = flDoc;
  }

  public static File firmar(File flDocumento, String strPathsignature, String strPassSignature)
      throws Exception {

    /*
     * Pasando los parametros al signature
     */
    ATECFE_XAdESBESSignature signature = new ATECFE_XAdESBESSignature(flDocumento);
    signature.setPassSignature(strPassSignature);
    signature.setPathSignature(strPathsignature);
    signature.execute();

    Document docRe = signature.getDocSigned();

    File flDoc = File.createTempFile("documento", ".xml", null);
    flDoc.deleteOnExit();

    DOMSource source = new DOMSource(docRe);
    StreamResult result = new StreamResult(flDoc);

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");
    transformer.transform(source, result);

    return flDoc;
  }

  @Override
  protected DataToSign createDataToSign() throws ParserConfigurationException, SAXException,
      IOException {
    // TODO Auto-generated method stub

    /*
     * Parametrizando datos para la generación de la firma
     */

    /*
     * DataToSign dtsDocumentoAFirmar = new DataToSign();
     * dtsDocumentoAFirmar.setXadesFormat(EnumFormatoFirma.XAdES_BES);
     * dtsDocumentoAFirmar.setEsquema(XAdESSchemas.XAdES_132);
     * dtsDocumentoAFirmar.setXMLEncoding("UTF-8"); dtsDocumentoAFirmar.setEnveloped(true); Document
     * docToSign = getDocument(flXmlDoc); dtsDocumentoAFirmar.setDocument(docToSign);
     * dtsDocumentoAFirmar.addObject(new ObjectToSign(new InternObjectToSign("comprobante"),
     * "contenido comprobante", null, "text/xml", null));
     * dtsDocumentoAFirmar.setParentSignNode("comprobante");
     */

    // dtsDocumentoAFirmar.addObject(new ObjectToSign(new AllXMLToSign(), "contenido comprobante",
    // null, "text/xml", null));

    DataToSign dtsDocumentoAFirmar = new DataToSign();
    dtsDocumentoAFirmar.setXadesFormat(EnumFormatoFirma.XAdES_BES);

    dtsDocumentoAFirmar.setEsquema(XAdESSchemas.XAdES_132);
    dtsDocumentoAFirmar.setXMLEncoding("UTF-8");
    dtsDocumentoAFirmar.setEnveloped(true);

    dtsDocumentoAFirmar.addObject(new ObjectToSign(new InternObjectToSign("comprobante"),
        "contenido comprobante", null, "text/xml", null));

    dtsDocumentoAFirmar.setParentSignNode("comprobante");

    Document docToSign = getDocument(flXmlDoc);
    dtsDocumentoAFirmar.setDocument(docToSign);

    return dtsDocumentoAFirmar;
  }
}
