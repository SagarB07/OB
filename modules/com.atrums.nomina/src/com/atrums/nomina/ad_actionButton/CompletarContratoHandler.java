package com.atrums.nomina.ad_actionButton;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.BaseActionHandler;

public class CompletarContratoHandler extends BaseActionHandler {
	static Logger log4j = Logger.getLogger(CompletarContratoHandler.class);
	
	public String procesarContrato (List<String>lstIdContrato){
		
		//filas = contratoProceso.actulizarContrato(this, iterador);
		return null;
	}

	@Override
	protected JSONObject execute(Map<String, Object> parameters, String content) {
		// TODO Auto-generated method stub
		  JSONObject result = new JSONObject();
		    try {

		      final JSONObject jsonData = new JSONObject(content);
		      final JSONArray contratosIds = jsonData.getJSONArray("roles");
		    }catch(Exception ex){
		    	
		    	log4j.error("Ocurrio un error en el proceso: "+ex);
		    }
		return null;
	}


	
}
