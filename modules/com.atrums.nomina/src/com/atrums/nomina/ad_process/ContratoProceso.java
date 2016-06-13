package com.atrums.nomina.ad_process;

import javax.servlet.ServletException;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.database.ConnectionProvider;

public class ContratoProceso  extends HttpSecureAppServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer actualizarContrato (ConnectionProvider connectionProvider, String filtro ) throws ServletException{
		Integer filasActualizadas;
		NOContratoEmpleadoData contratoData = new NOContratoEmpleadoData();
		contratoData.noContratoEmpleadoId = filtro;
		filasActualizadas = contratoData.actualizarContrato(connectionProvider);
		return filasActualizadas;
	}

	
	public Integer actualizarContrato ( String filtro ) throws ServletException{
		Integer filasActualizadas;
		NOContratoEmpleadoData contratoData = new NOContratoEmpleadoData();
		contratoData.noContratoEmpleadoId = filtro;
		filasActualizadas = contratoData.actualizarContrato(this);
		return filasActualizadas;
	}

	
	
}
