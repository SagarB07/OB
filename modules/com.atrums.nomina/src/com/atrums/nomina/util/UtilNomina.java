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

import org.openbravo.database.ConnectionProvider;
import org.openbravo.exception.NoConnectionAvailableException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class UtilNomina {

	public static String basedesign(String strReporte) {
		
		String strDirectorio = UtilNomina.class.getResource("/").getPath();
		Integer value = strDirectorio.indexOf("/build/classes/");
		strDirectorio = strDirectorio.substring(1, value+1);
		String strBaseDesign = strReporte.replaceAll("@basedesign@", (strDirectorio + "WebContent/src-loc/design"));

		File flRep = new File(strBaseDesign);

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
}
