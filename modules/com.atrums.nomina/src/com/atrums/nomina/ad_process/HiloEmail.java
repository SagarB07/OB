package com.atrums.nomina.ad_process;

import javax.servlet.ServletException;

import org.openbravo.database.ConnectionProvider;

public class HiloEmail extends Thread {
	private NOCBPartnerData[] data = null;
	private ConnectionProvider connectionProvider;
	private NOCBPartner nocbPartner;

	public HiloEmail(NOCBPartnerData[] data, ConnectionProvider connectionProvider) throws ServletException {
		this.data = data;
		this.connectionProvider = connectionProvider;
		//nocbPartner = new NOCBPartner(getConnectionProvider(), NOCBPartner.obtenerMailTerceros(getConnectionProvider(), "Y"));
	}

	
	public void run() {
		try {
			try {
				System.out.println("**************");
				System.out.println(getData());
				System.out.println("---------------");
				//System.out.println(getConnectionProvider());
				//nocbPartner.enviarMail(getData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; 100 > i; i++) {
				Thread.sleep(500);
				System.out.println("EJECUTANDO...." + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public NOCBPartnerData[] getData() {
		return data;
	}

	public void setData(NOCBPartnerData[] data) {
		this.data = data;
	}

	public ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public void setConnectionProvider(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

}
