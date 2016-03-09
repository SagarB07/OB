package org.openbravo.erpCommon.ad_process;

import org.apache.log4j.Logger;
import org.openbravo.data.FieldProvider;

public class ImportIdtNovedadData implements FieldProvider  {
	static Logger log4j = Logger.getLogger(ImportIdtNovedadData.class);
	public String cedula;
	public String rubro;
	public String periodo;
	public String valor;
	
	@Override
	public String getField(String fieldName) {
		if (fieldName.equalsIgnoreCase("rubro") || fieldName.equals("no_tipo_ingreso_egreso_id"))
			return rubro; 
		else {
			log4j.debug("Field does not exist: " + fieldName);
			     return null;
			  }
	}
	
	
	
}
