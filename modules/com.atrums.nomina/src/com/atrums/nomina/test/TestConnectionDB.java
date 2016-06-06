package com.atrums.nomina.test;

import org.apache.log4j.Logger;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.database.ConnectionProviderImpl;
import org.openbravo.exception.PoolNotFoundException;

import com.atrums.nomina.util.UtilNomina;

public class TestConnectionDB {
	private static final Logger log = Logger.getLogger(TestConnectionDB.class);
	public ConnectionProvider conn = null;
	
	public TestConnectionDB() throws PoolNotFoundException{
		String strDirectorio = UtilNomina.class.getResource("/").getPath();
		Integer value = strDirectorio.indexOf("/src-core/build/classes/");
		try{
		if (value>0){
			strDirectorio = strDirectorio.substring(1, value+1);	
			strDirectorio= strDirectorio +"config/";
		}else{
			 value = strDirectorio.indexOf("/build/classes/");
			 if (value>0){
				 strDirectorio = strDirectorio.substring(1, value+1);
				 strDirectorio= strDirectorio +"config/";
			 } else{
				 value = strDirectorio.indexOf("/WEB-INF/classes/");
				 log.info("strDirectorio " + value);
				 if (value>0){
					 strDirectorio = strDirectorio.substring(1, value+1);
					 strDirectorio = "/"+strDirectorio+""+"WEB-INF/";
					 log.info(strDirectorio);
				 }else{
					 log.info("Problema en el Path " + value);
					 strDirectorio = "/var/lib/tomcat6/webapps/openbravo/WEB-INF/";
				 }
				
			 }
				
		}
		}catch (Exception e){
			log.error(e.toString() +" --------------"+strDirectorio );
		}
		conn = new ConnectionProviderImpl(strDirectorio + "/Openbravo.properties");
		
		
	}
	
}
