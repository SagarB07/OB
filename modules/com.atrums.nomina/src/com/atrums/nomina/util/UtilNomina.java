package com.atrums.nomina.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.exception.NoConnectionAvailableException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class UtilNomina {
	private static final Logger log = Logger.getLogger(UtilNomina.class);
	public static String basedesign(String strReporte) {
		File flRep = null;
		String strBaseDesign = "";
		String strDirectorio = UtilNomina.class.getResource("/").getPath();
		try{
	
			Integer value = strDirectorio.indexOf("/src-core/build/classes/");
			log.info("Valor: strDirectorio  "+value);
		
		if (value>0){
			log.info("Valor: strDirectorio  "+value);
			strDirectorio = strDirectorio.substring(1, value+1);	
		}else{

			value = strDirectorio.indexOf("/build/classes/");
			log.info("Valor: strDirectorio  "+value);
			if (value>0){
				strDirectorio = strDirectorio.substring(1, value+1);
				 strBaseDesign = strReporte.replaceAll("@basedesign@", (strDirectorio + "WebContent/src-loc/design"));
			 } else{
				 value = strDirectorio.indexOf("/WEB-INF/classes/");
				 log.info("Valor: strDirectorio  "+value);
				 strDirectorio = strDirectorio.substring(1, value+1);
				// strDirectorio = "/var/lib/tomcat6/webapps/openbravo/WEB-INF/";
				 strBaseDesign = strReporte.replaceAll("@basedesign@", ("/"+strDirectorio + "src-loc/design"));
				 flRep = new File(strBaseDesign);
				 if (flRep.exists()){
					 log.info("Existe el Archivo: " +flRep );
					 return "/"+strDirectorio + "src-loc/design";
				 }else{
					 log.info("NO Existe el Archio: " +flRep );
				 }
				 
				 log.info("Valor: strBaseDesign  "+strBaseDesign);
			 }
		}
		

		}catch (Exception x ){
			log.error(x);
		}
		
		
		flRep = new File(strBaseDesign);

		if (flRep.exists()) {
			return strDirectorio + "WebContent/src-loc/design";
		} else {
			strBaseDesign = strReporte.replaceAll("@basedesign@", (strDirectorio + "src-loc/design"));
			if (flRep.exists()) {
				return strDirectorio + "src-loc/design";
			} else {
				return "";
			}
		}
		
	}

	public static File generarPDF(ConnectionProvider conn, String strBaseDesin, String strNombre, String strEntiID)	throws ServletException {
		File flTemp = null;
		try {
			String strBaseDesign = strBaseDesin;
			if (!basedesign(strBaseDesign).equals("")) {
				   Map<String, String> parameters = new HashMap<String, String>();
				   parameters.put("DOCUMENT_ID", strEntiID);
			        parameters.put("BASE_DESIGN", basedesign(strBaseDesign));
			        strBaseDesign = strBaseDesign.replaceAll("@basedesign@", basedesign(strBaseDesign));
			        log.info("funion generarPDF "+strBaseDesign);
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
			log.error(e.toString());
			//e.printStackTrace();
		} catch (NoConnectionAvailableException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.toString());
			e.printStackTrace();
		}
		return flTemp;
	}
}
