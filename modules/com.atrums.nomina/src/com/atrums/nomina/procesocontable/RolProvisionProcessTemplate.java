package com.atrums.nomina.procesocontable;

import org.apache.log4j.Logger;

public abstract class RolProvisionProcessTemplate {

  private static final long serialVersionUID = 1L;
  private static final Logger log4jDocFINFinAccTransactionTemplate = Logger
      .getLogger(RolProvisionProcessTemplate.class);

  public RolProvisionProcessTemplate() {
    // TODO Auto-generated constructor stub
  }

  // public abstract Fact createFact(RolPagoProceso docFAT, AcctSchema as, ConnectionProvider conn,
  // Connection con, VariablesSecureApp vars) throws ServletException;

  public String getServletInfo() {
    return "Servlet for the accounting";
  } // end of getServletInfo() method

}