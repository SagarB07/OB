package com.atrums.felectronica.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.FirmaXML;

public abstract class ATECFE_GenericXMLSignature {

  private String pathSignature;
  private String passSignature;
  private Document docSigned;

  protected void execute() throws Exception {
    KeyStore kstStore = getKeyStore();

    if (kstStore != null) {
      String strAlias = getAlias(kstStore);

      X509Certificate xctCertificado = null;

      xctCertificado = (X509Certificate) kstStore.getCertificate(strAlias);
      if (xctCertificado != null) {
        PrivateKey prkClavePrivada = null;
        KeyStore kstTemp = kstStore;

        prkClavePrivada = (PrivateKey) kstTemp.getKey(strAlias, this.passSignature.toCharArray());

        Provider prdProvi = kstStore.getProvider();

        DataToSign dtsDatos = createDataToSign();

        FirmaXML frxml = new FirmaXML();

        Object[] res = frxml.signFile(xctCertificado, dtsDatos, prkClavePrivada, prdProvi);

        setDocSigned((Document) res[0]);
      }
    }

  }

  protected abstract DataToSign createDataToSign() throws ParserConfigurationException,
      SAXException, IOException;

  private KeyStore getKeyStore() throws KeyStoreException, NoSuchAlgorithmException,
      CertificateException, FileNotFoundException, IOException {
    KeyStore kstKeys = null;
    kstKeys = KeyStore.getInstance("PKCS12");
    kstKeys.load(new FileInputStream(pathSignature), passSignature.toCharArray());

    return kstKeys;
  }

  private static String getAlias(KeyStore kstStore) throws KeyStoreException {
    String strAlias = null;
    Enumeration entNombres = kstStore.aliases();

    while (entNombres.hasMoreElements()) {
      String tmpAlias = (String) entNombres.nextElement();
      if (kstStore.isKeyEntry(tmpAlias))
        strAlias = tmpAlias;
    }

    return strAlias;
  }

  protected Document getDocument(File flXmldocumento) throws ParserConfigurationException,
      SAXException, IOException {
    Document docDoc = null;
    DocumentBuilderFactory dbfDoc = DocumentBuilderFactory.newInstance();
    dbfDoc.setNamespaceAware(true);
    File file = new File(flXmldocumento.getPath());

    DocumentBuilder db = dbfDoc.newDocumentBuilder();
    /*
     * InputStream in = new FileInputStream(flXmldocumento); Reader rd = new InputStreamReader(in,
     * "UTF-8"); InputSource is = new InputSource(rd); is.setEncoding("UTF-8"); docDoc =
     * db.parse(is);
     */

    docDoc = db.parse(file);

    return docDoc;
  }

  public String getPathSignature() {
    return pathSignature;
  }

  public void setPathSignature(String pathSignature) {
    this.pathSignature = pathSignature;
  }

  public String getPassSignature() {
    return passSignature;
  }

  public void setPassSignature(String passSignature) {
    this.passSignature = passSignature;
  }

  public Document getDocSigned() {
    return docSigned;
  }

  public void setDocSigned(Document docSigned) {
    this.docSigned = docSigned;
  }
}
