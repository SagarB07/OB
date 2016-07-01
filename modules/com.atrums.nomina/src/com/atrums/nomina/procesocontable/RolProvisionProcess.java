package com.atrums.nomina.procesocontable;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.ad_forms.Account;
import org.openbravo.erpCommon.ad_forms.AcctSchema;
import org.openbravo.erpCommon.ad_forms.AcctServer;
import org.openbravo.erpCommon.ad_forms.DocFINFinAccTransaction;
import org.openbravo.erpCommon.ad_forms.Fact;
import org.openbravo.erpCommon.utility.FieldProviderFactory;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.FIN_FinancialAccountAccounting;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;

import com.atrums.nomina.data.noRolPagoProvision;
import com.atrums.nomina.data.noRolPagoProvisionLine;
import com.atrums.nomina.data.noRolProvisionLineMes;

//import com.atrums.nomina.data;

public class RolProvisionProcess extends AcctServer {

  public static final String DOCTYPE_RolPago = "NO_RPLM";

  private static final long serialVersionUID = 1L;
  private static final Logger log4j = Logger.getLogger(DocFINFinAccTransaction.class);

  String SeqNo = "0";
  BigDecimal totalAmount = BigDecimal.ZERO;

  public RolProvisionProcess() {
    // TODO Auto-generated constructor stub
  }

  public RolProvisionProcess(String AD_Client_ID, String AD_Org_ID,
      ConnectionProvider connectionProvider) {
    super(AD_Client_ID, AD_Org_ID, connectionProvider);
  }

  @Override
  public void loadObjectFieldProvider(ConnectionProvider conn, String AD_Client_ID, String Id)
      throws ServletException {

    // instancia objeto del tipo noRolPagoProvision
    noRolProvisionLineMes rolPagoMes = OBDal.getInstance().get(noRolProvisionLineMes.class,
        Record_ID);

    FieldProviderFactory[] data = new FieldProviderFactory[1];
    OBContext.setAdminMode();

    try {
      data[0] = new FieldProviderFactory(null);
      FieldProviderFactory.setField(data[0], "AD_Client_ID", rolPagoMes.getClient().getId());
      FieldProviderFactory.setField(data[0], "AD_Org_ID", rolPagoMes.getOrganization().getId());
      FieldProviderFactory.setField(data[0], "AD_OrgTrx_ID", rolPagoMes.getOrganization().getId());
      FieldProviderFactory.setField(data[0], "No_Rol_Provision_Line_Mes_ID", rolPagoMes.getId());
      FieldProviderFactory.setField(data[0], "C_Currency_ID", rolPagoMes.getRolPagoProvisionLine()
          .getCurrency().getId());
      FieldProviderFactory.setField(data[0], "C_BPartner_ID", rolPagoMes.getRolPagoProvisionLine()
          .getRolPagoProvisionID().getBusinessPartner().getId());
      FieldProviderFactory.setField(data[0], "C_Doctype_ID", rolPagoMes.getDocumentType().getId());
      FieldProviderFactory.setField(data[0], "DocumentNo", rolPagoMes.getDocumentno());
      String dateFormat = OBPropertiesProvider.getInstance().getOpenbravoProperties()
          .getProperty("dateFormat.java");
      SimpleDateFormat outputFormat = new SimpleDateFormat(dateFormat);
      FieldProviderFactory.setField(data[0], "DateAcct",
          outputFormat.format(rolPagoMes.getDateacct()));
      FieldProviderFactory.setField(data[0], "Posted", rolPagoMes.getPosted().toString());
      FieldProviderFactory.setField(data[0], "Processed", rolPagoMes.isProcessed() ? "Y" : "N");
      FieldProviderFactory.setField(data[0], "Processing", "N");

    } finally {
      OBContext.restorePreviousMode();

    }
    setObjectFieldProvider(data);
  }

  @Override
  public boolean loadDocumentDetails(FieldProvider[] data, ConnectionProvider conn) {
    DocumentType = DOCTYPE_RolPago;
    DateDoc = data[0].getField("statementDate");
    C_DocType_ID = data[0].getField("C_Doctype_ID");
    DocumentNo = data[0].getField("DocumentNo");
    OBContext.setAdminMode();
    try {
      noRolProvisionLineMes noRolPMes = OBDal.getInstance().get(noRolProvisionLineMes.class,
          Record_ID);

      totalAmount = noRolPMes.getValor();
      Amounts[0] = totalAmount.toString();
    } finally {
      OBContext.restorePreviousMode();
    }
    loadDocumentType();
    return true;
  }

  @Override
  public BigDecimal getBalance() {
    BigDecimal retValue = ZERO;
    return retValue;
  }

  @Override
  public Fact createFact(AcctSchema as, ConnectionProvider conn, Connection con,
      VariablesSecureApp vars) throws ServletException {

    // Select specific definition
    String strClassname = "";
    final StringBuilder whereClause = new StringBuilder();
    StringBuilder whereClause2;
    final StringBuilder whereClause3 = new StringBuilder();

    Fact fact = new Fact(this, as, Fact.POST_Actual);

    final OBQuery<AccountingFact> objFacAcct = OBDal.getInstance().createQuery(
        AccountingFact.class, whereClause.toString());
    String Fact_Acct_Group_ID = SequenceIdData.getUUID();

    OBContext.setAdminMode();
    try {

      noRolProvisionLineMes noRolpMes = OBDal.getInstance().get(noRolProvisionLineMes.class,
          Record_ID);

      Fact_Acct_Group_ID = SequenceIdData.getUUID();

      // lista noRolPagoProvisionLine
      final noRolPagoProvisionLine noRolPagoProvisionLine = noRolpMes.getRolPagoProvisionLine();
      final noRolPagoProvision noRolProv = noRolPagoProvisionLine.getRolPagoProvisionID();

      whereClause2 = new StringBuilder();
      whereClause2.append(" as cbea where cbea.accountingSchema.id = '" + as.m_C_AcctSchema_ID
          + "'");
      whereClause2.append(" and cbea.rolPagoProvisionLine.rubro.id   = '"+ noRolPagoProvisionLine.getRubro().getId() + "'");
      whereClause2.append(" and cbea.rolPagoProvisionLine.rolPagoProvisionID.businessPartner.id = '"+ noRolProv.getBusinessPartner().getId() + "'");

      // Desde no_cb_empleado_acc
      OBQuery<noRolProvisionLineMes > obqParameters = OBDal.getInstance().createQuery( //
    		  noRolProvisionLineMes.class, whereClause2.toString());

      // Cuenta de Ingreso
      if (obqParameters.list().size()>0 && obqParameters != null && obqParameters.list() != null && obqParameters.list().get(0).getCuentaDelIngreso()!=null){
      fact.createLine(null,
          Account.getAccount(conn, obqParameters.list().get(0).getCuentaDelIngreso().getId()),
          C_Currency_ID, noRolpMes.getValor().abs().toString(), ZERO.toString(),
          Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);
      }
      // Cuenta de Egreso
      if (obqParameters.list().size()>0 && obqParameters.list().get(0).getCuentaDelEgreso()!=null){
      fact.createLine(null,
          Account.getAccount(conn, obqParameters.list().get(0).getCuentaDelEgreso().getId()),
          C_Currency_ID, ZERO.toString(), noRolpMes.getValor().abs().toString(),
          Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);
      }

    } finally {
      OBContext.restorePreviousMode();
    }

    return fact;
  }

  @Override
  public boolean getDocumentConfirmation(ConnectionProvider conn, String strRecordId) {

    int intCountTR = 0;
    int intCountConfig = 0;

    noRolPagoProvision noRolPago = OBDal.getInstance().get(noRolPagoProvision.class, strRecordId);

    final OBQuery<org.openbravo.model.financialmgmt.accounting.coa.AcctSchema> accs = OBDal
        .getInstance().createQuery(
            org.openbravo.model.financialmgmt.accounting.coa.AcctSchema.class,
            "as e where e.client.id = '" + AD_Client_ID + "'");
    final List<org.openbravo.model.financialmgmt.accounting.coa.AcctSchema> acctSchema = accs
        .list();
    // --
    for (org.openbravo.model.financialmgmt.accounting.coa.AcctSchema acSc : acctSchema) {

      // intCountTR = noRolPago.getNORolPagoProvisionLineList().size();
      /*
       * for (noRolPagoProvisionLine crl : noRolPago.getNORolPagoProvisionLineList()) { final
       * OBQuery<noCbEmpleadoAcct> tra = OBDal.getInstance().createQuery( noCbEmpleadoAcct.class,
       * " as e where e.accountingSchema.id ='" + acSc.getId() + "'" + " and e.client.id = '" +
       * AD_Client_ID + "'" + " and e..id = '" + crl.getRolPagoProvisionID().getId() + "'");
       * 
       * if (tra.list().size() == 0) { intCountConfig++; } }
       */

      if (intCountConfig != 0)
        if (intCountConfig < intCountTR) {
          setStatus(STATUS_DocumentDisabled);
          return false;
        }
    }
    return true;
  }

  public String nextSeqNo(String oldSeqNo) {
    BigDecimal seqNo = new BigDecimal(oldSeqNo);
    SeqNo = (seqNo.add(new BigDecimal("10"))).toString();
    return SeqNo;
  }

  public Account getAccount(ConnectionProvider conn, FIN_FinancialAccount finAccount,
      AcctSchema as, boolean isTransitAccount) throws ServletException {
    String strValidCombinationId = "";
    OBContext.setAdminMode();
    try {
      //
      OBCriteria<FIN_FinancialAccountAccounting> accounts = OBDal.getInstance().createCriteria(
          FIN_FinancialAccountAccounting.class);
      accounts.add(Restrictions.eq(FIN_FinancialAccountAccounting.PROPERTY_ACCOUNT, finAccount));
      accounts.add(Restrictions.eq(FIN_FinancialAccountAccounting.PROPERTY_ACCOUNTINGSCHEMA, OBDal.getInstance().get(org.openbravo.model.financialmgmt.accounting.coa.AcctSchema.class,as.m_C_AcctSchema_ID)));
      accounts.add(Restrictions.eq(FIN_FinancialAccountAccounting.PROPERTY_ACTIVE, true));
      accounts.setFilterOnReadableClients(false);
      accounts.setFilterOnReadableOrganization(false);
      List<FIN_FinancialAccountAccounting> accountList = accounts.list();

      if (accountList == null || accountList.size() == 0)
        return null;
      if (isTransitAccount)
        strValidCombinationId = accountList.get(0).getFINTransitoryAcct() == null ? ""
            : accountList.get(0).getFINTransitoryAcct().getId();
      else
        strValidCombinationId = accountList.get(0).getFINAssetAcct() == null ? "" : accountList
            .get(0).getFINAssetAcct().getId();
      if (strValidCombinationId.equals(""))
        return null;
    } finally {
      OBContext.restorePreviousMode();
    }
    return new Account(conn, strValidCombinationId);
  }
}
