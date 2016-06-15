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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.FIN_FinancialAccountAccounting;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;

import com.atrums.nomina.data.noRolPagoProvision;
import com.atrums.nomina.data.noRolPagoProvisionLine;

//import com.atrums.nomina.data;

public class RolPagoProceso extends AcctServer {

  public static final String DOCTYPE_RolPago = "NO_NRP";

  private static final long serialVersionUID = 1L;
  private static final Logger log4j = Logger.getLogger(DocFINFinAccTransaction.class);

  String SeqNo = "0";
  BigDecimal totalAmount = BigDecimal.ZERO;

  public RolPagoProceso() {
    // TODO Auto-generated constructor stub
  }

  public RolPagoProceso(String AD_Client_ID, String AD_Org_ID, ConnectionProvider connectionProvider) {
    super(AD_Client_ID, AD_Org_ID, connectionProvider);
  }

  @Override
  public void loadObjectFieldProvider(ConnectionProvider conn, String AD_Client_ID, String Id)
      throws ServletException {
    // instancia objeto del tipo noRolPagoProvision
    noRolPagoProvision noRolPago = OBDal.getInstance().get(noRolPagoProvision.class, Record_ID);

    FieldProviderFactory[] data = new FieldProviderFactory[1];
    OBContext.setAdminMode();

    try {
      data[0] = new FieldProviderFactory(null);
      FieldProviderFactory.setField(data[0], "AD_Client_ID", noRolPago.getClient().getId());
      FieldProviderFactory.setField(data[0], "AD_Org_ID", noRolPago.getOrganization().getId());
      FieldProviderFactory.setField(data[0], "AD_OrgTrx_ID", noRolPago.getOrganization().getId());
      FieldProviderFactory.setField(data[0], "No_Rol_Pago_Provision_ID", noRolPago.getId());
      FieldProviderFactory.setField(data[0], "C_Currency_ID", noRolPago.getBusinessPartner()
          .getContratoEmpleadoList().get(0).getCurrency().getId());
      FieldProviderFactory.setField(data[0], "C_BPartner_ID", noRolPago.getBusinessPartner()
          .getId());
      FieldProviderFactory.setField(data[0], "C_Doctype_ID", noRolPago.getDocumentType().getId());
      FieldProviderFactory.setField(data[0], "DocumentNo", noRolPago.getDocumentno());
      String dateFormat = OBPropertiesProvider.getInstance().getOpenbravoProperties()
          .getProperty("dateFormat.java");
      SimpleDateFormat outputFormat = new SimpleDateFormat(dateFormat);
      FieldProviderFactory.setField(data[0], "statementDate",
          outputFormat.format(noRolPago.getDateacct()));
      FieldProviderFactory.setField(data[0], "Posted", noRolPago.getPosted().toString());
      FieldProviderFactory.setField(data[0], "Processed", noRolPago.isGenerarPago() ? "Y" : "N");
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
      noRolPagoProvision noRolPago = OBDal.getInstance().get(noRolPagoProvision.class, Record_ID);

      totalAmount = noRolPago.getTotalNeto();
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

      noRolPagoProvision noRolPago = OBDal.getInstance().get(noRolPagoProvision.class, Record_ID);
      Fact_Acct_Group_ID = SequenceIdData.getUUID();

      // lista noRolPagoProvisionLine
      final List<noRolPagoProvisionLine> noRolPagoProvisionLineList = noRolPago
          .getNORolPagoProvisionLineList();

      for (noRolPagoProvisionLine list : noRolPagoProvisionLineList) {
        whereClause2 = new StringBuilder();
        whereClause2.append(" as cbea where cbea.accountingSchema.id = '" + as.m_C_AcctSchema_ID
            + "'");
        whereClause2.append(" and cbea.rubro.id = '" + list.getRubro().getId() + "'");
//        whereClause2.append(" and cbea.businessPartner.id = '" + noRolPago.getBusinessPartner().getId() + "'");

        // Desde no_cb_empleado_acc
        OBQuery<noRolPagoProvisionLine> obqParameters = OBDal.getInstance().createQuery( //
            noRolPagoProvisionLine.class, whereClause2.toString());

        if (list.getRubro().isEsIngreso()) {

          if (!list.getRubro().isAvance())
            // Cuenta de Ingreso
        	  try{
            fact.createLine(
                null,
                Account.getAccount(conn, obqParameters.list().get(0).getCuentaDelIngreso().getId()),
                C_Currency_ID, list.getValor().abs().toString(), ZERO.toString(),
                Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);
            }catch (Exception ex){
            	System.out.print(ex);
            }
          else
            // Egreso
            fact.createLine(null,
                Account.getAccount(conn, obqParameters.list().get(0).getCuentaDelEgreso().getId()),
                C_Currency_ID, ZERO.toString(), list.getValor().abs().toString(),
                Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);
        } else
          // Cuenta de Egresos
          fact.createLine(null,
              Account.getAccount(conn, obqParameters.list().get(0).getCuentaDelEgreso().getId()),
              C_Currency_ID, ZERO.toString(), list.getValor().abs().toString(), Fact_Acct_Group_ID,
              nextSeqNo(SeqNo), DocumentType, conn);
      }

      whereClause3.append(" as cbp where cbp.id = '"
          + noRolPago.getBusinessPartner().getId() + "'");
      whereClause3.append(" and cbp.neCAcctschema.id = '" + as.m_C_AcctSchema_ID + "'");

      final OBQuery<BusinessPartner> obqParamAcct = OBDal.getInstance().createQuery(
    		  BusinessPartner.class, whereClause3.toString());

      if (obqParamAcct.list().get(0).getNoPagoAcct() != null){
      fact.createLine(null,
          Account.getAccount(conn, obqParamAcct.list().get(0).getNoPagoAcct().getId()) ,
          C_Currency_ID, ZERO.toString(), noRolPago.getTotalNeto().abs().toString(),
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

    for (org.openbravo.model.financialmgmt.accounting.coa.AcctSchema acSc : acctSchema) {

      intCountTR = noRolPago.getNORolPagoProvisionLineList().size();
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
      accounts.add(Restrictions.eq(
          FIN_FinancialAccountAccounting.PROPERTY_ACCOUNTINGSCHEMA,
          OBDal.getInstance().get(
              org.openbravo.model.financialmgmt.accounting.coa.AcctSchema.class,
              as.m_C_AcctSchema_ID)));
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
