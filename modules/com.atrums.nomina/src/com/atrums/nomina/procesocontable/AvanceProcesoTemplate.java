package com.atrums.nomina.procesocontable;

import java.sql.Connection;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.ad_forms.AcctSchema;
import org.openbravo.erpCommon.ad_forms.Fact;

public abstract class AvanceProcesoTemplate {
  private static final long serialVersionUID = 1L;
  private static final Logger log4jDocFINFinAccTransactionTemplate = Logger
      .getLogger(AvanceProcesoTemplate.class);

  public AvanceProcesoTemplate() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Create Facts (the accounting logic) for
   * 
   * @param as
   *          accounting schema
   * @return Fact
   */
  public abstract Fact createFact(AvanceProcesoTemplate docFAT, AcctSchema as,
      ConnectionProvider conn, Connection con, VariablesSecureApp vars) throws ServletException;

  public String getServletInfo() {
    return "Servlet for the accounting";
  } // end of getServletInfo() method

}
