package com.atrums.nomina.ad_process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.openbravo.dal.core.SessionHandler;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.model.common.enterprise.EmailServerConfiguration;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.utils.FormatUtilities;

import com.atrums.nomina.util.EmailManager;
import com.atrums.nomina.util.UtilNomina;

public class NOCBPartner {
	private static final Logger log = Logger.getLogger(NOCBPartner.class);
	public static NOCBPartnerData[] emailTercero = null;
	private static NOCBPartnerData[] data = null;
	private static ConnectionProvider connectionProvider;

	public NOCBPartner(ConnectionProvider connectionProvider, NOCBPartnerData[] data) {
		setConnectionProvider(connectionProvider);
		setData(data);
	}
	
	public NOCBPartner(ConnectionProvider connectionProvider) {
		setConnectionProvider(connectionProvider);
	}
	
	public void testEnvios () throws Exception{
		enviarMail(getData());
	}

	public static  NOCBPartnerData[] obtenerMailTerceros( String idTercero) throws ServletException {
		NOCBPartnerData[] datos = null;
		datos = NOCBPartnerData.select(getConnectionProvider(), idTercero);
		 return datos;
	}

	@SuppressWarnings("unused")
	public static void enviarMail(NOCBPartnerData[] datosEmail)throws Exception {
		String strBaseDesin = "@basedesign@/com/atrums/nomina/ad_reports/RPT_Rol_Pagos_Individual.jrxml";
		try{
		for (int i = 0; datosEmail.length > i; i++) {
			Thread.sleep(4000);
			String idOrg = datosEmail[i].getField("organizacion");
			String recipient = datosEmail[i].getField("email");
			Organization currenctOrg = obtenerOrganizacion(idOrg);
			EmailServerConfiguration mailConfig = obtenerConfiguracionesMail(idOrg);
			log.info(mailConfig);
			String username = mailConfig.getSmtpServerAccount();
			//log.info(username);
			String password = FormatUtilities.encryptDecrypt(mailConfig.getSmtpServerPassword(), false);
			//log.info(password);
			String connSecurity = mailConfig.getSmtpConnectionSecurity();
			log.info(connSecurity);
			int port = mailConfig.getSmtpPort().intValue();
			log.info(port);
			String senderAddress = mailConfig.getSmtpServerSenderAddress();
			String host = "smtp.gmail.com";
			boolean auth = mailConfig.isSMTPAuthentification();
			List<File> lisdoc = new ArrayList<File>();
			log.info( "Fin de configuraciones");
			File flPdf = UtilNomina.generarPDF(connectionProvider, strBaseDesin, "rolpago",
					datosEmail[i].getField("rolpago"));
			lisdoc.add(flPdf);
			EmailManager.sendEmail(host, auth, username, password, connSecurity, port, senderAddress, recipient, null,
					null, null, "Rol de pagos", "Adjunto rol de pagos, Atentamente Recursos Humanos", null, lisdoc, null, null);
		}
		}catch (Exception e){
			log.error(e.toString());
		}

	}

	private static EmailServerConfiguration obtenerConfiguracionesMail(String organizacionId) {
		final EmailServerConfiguration o = getOne(EmailServerConfiguration.class,
				"select r from " + EmailServerConfiguration.class.getName() + " r");
		return o;
	}

	private static Organization obtenerOrganizacion(String organizacionId) {
		final Organization o = getOne(Organization.class, "select r from " + Organization.class.getName() + " r where "
				+ " r." + Organization.PROPERTY_ID + "='" + organizacionId + "'");
		return o;
	}

	private static <T extends Object> T getOne(Class<T> clz, String qryStr) {
		return getOne(clz, qryStr, true);
	}

	@SuppressWarnings({ "unchecked" })
	private static <T extends Object> T getOne(Class<T> clz, String qryStr, boolean doCheck) {
		final Query qry = SessionHandler.getInstance().createQuery(qryStr);
		qry.setMaxResults(1);
		final List<?> result = qry.list();
		if (doCheck && result.size() != 1) {
			log.error("The query '" + qryStr + "' returned " + result.size()
					+ " results while only 1 result was expected");
		}
		if (result.size() == 0) {
			return null;
		}
		return (T) result.get(0);
	}

	public static NOCBPartnerData[] getData() {
		return data;
	}

	public static void setData(NOCBPartnerData[] data) {
		NOCBPartner.data = data;
	}

	public static ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public static void setConnectionProvider(ConnectionProvider connectionProvider) {
		NOCBPartner.connectionProvider = connectionProvider;
	}
}
