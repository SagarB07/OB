package com.atrums.contabilidad.procesocontable;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
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
import org.openbravo.model.common.businesspartner.CustomerAccounts;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.FIN_FinancialAccountAccounting;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;

import com.atrums.contabilidad.data.CO_RETENCION_VENTA;
import com.atrums.contabilidad.data.CO_TipoRetencionAcct;
import com.atrums.contabilidad.data.Co_Retencion_Venta_Linea;

public class RetVentaProcess extends AcctServer {

  public static final String DOCTYPE_RetencionVenta = "CO_RV";

  private static final long serialVersionUID = 1L;
  private static final Logger log4j = Logger.getLogger(DocFINFinAccTransaction.class);

  String SeqNo = "0";
  BigDecimal totalAmount = BigDecimal.ZERO;

  public RetVentaProcess() {
  }

  public RetVentaProcess(String AD_Client_ID, String AD_Org_ID,
      ConnectionProvider connectionProvider) {
    super(AD_Client_ID, AD_Org_ID, connectionProvider);
  }

  @Override
  public void loadObjectFieldProvider(ConnectionProvider conn, String AD_Client_ID, String Id)
      throws ServletException {
    CO_RETENCION_VENTA coRetVenta = OBDal.getInstance().get(CO_RETENCION_VENTA.class, Record_ID);

    FieldProviderFactory[] data = new FieldProviderFactory[1];
    OBContext.setAdminMode();

    try {
      data[0] = new FieldProviderFactory(null);
      FieldProviderFactory.setField(data[0], "AD_Client_ID", coRetVenta.getClient().getId());
      FieldProviderFactory.setField(data[0], "AD_Org_ID", coRetVenta.getOrganization().getId());
      FieldProviderFactory.setField(data[0], "AD_OrgTrx_ID", coRetVenta.getOrganization().getId());
      FieldProviderFactory.setField(data[0], "Co_Retencion_Venta_ID", coRetVenta.getId());
      FieldProviderFactory.setField(data[0], "C_Currency_ID", coRetVenta.getInvoice().getCurrency()
          .getId());
      FieldProviderFactory.setField(data[0], "C_BPartner_ID", coRetVenta.getInvoice()
          .getBusinessPartner().getId());
      FieldProviderFactory.setField(data[0], "C_Doctype_ID", coRetVenta.getDocumentType().getId());
      FieldProviderFactory.setField(data[0], "DocumentNo", coRetVenta.getDocumentNo());
      String dateFormat = OBPropertiesProvider.getInstance().getOpenbravoProperties()
          .getProperty("dateFormat.java");
      SimpleDateFormat outputFormat = new SimpleDateFormat(dateFormat);
      FieldProviderFactory.setField(data[0], "statementDate",
          outputFormat.format(coRetVenta.getAccountingDate()));
      FieldProviderFactory.setField(data[0], "Posted", coRetVenta.getPosted());
      FieldProviderFactory.setField(data[0], "Processed", coRetVenta.isProcessed() ? "Y" : "N");
      FieldProviderFactory.setField(data[0], "Processing", "N");

    } finally {
      OBContext.restorePreviousMode();

    }
    setObjectFieldProvider(data);
  }

  @Override
  public boolean loadDocumentDetails(FieldProvider[] data, ConnectionProvider conn) {
    DocumentType = DOCTYPE_RetencionVenta;
    DateDoc = data[0].getField("statementDate");
    C_DocType_ID = data[0].getField("C_Doctype_ID");
    DocumentNo = data[0].getField("DocumentNo");
    OBContext.setAdminMode();
    try {
      CO_RETENCION_VENTA coRetVenta = OBDal.getInstance().get(CO_RETENCION_VENTA.class, Record_ID);

      totalAmount = coRetVenta.getTotal();
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
    StringBuilder whereClause2 = new StringBuilder();
    StringBuilder whereClause3 = new StringBuilder();

    Fact fact = new Fact(this, as, Fact.POST_Actual);
    OBContext.setAdminMode();
    try {

      CO_RETENCION_VENTA coRetVenta = OBDal.getInstance().get(CO_RETENCION_VENTA.class, Record_ID);

      whereClause.append(" as e where e.recordID= '" + coRetVenta.getInvoice().getId() + "'");

      final OBQuery<AccountingFact> objFacAcct = OBDal.getInstance().createQuery(
          AccountingFact.class, whereClause.toString());

      String Fact_Acct_Group_ID = ((AccountingFact) objFacAcct.list().get(0)).getGroupID();

      if (Fact_Acct_Group_ID == "" || Fact_Acct_Group_ID == null) {

      }

      if (coRetVenta.getTotal() == ZERO) {
        throw new OBException("La retencion debe ser mayor a 0");
      }

      final List<Co_Retencion_Venta_Linea> retencionVentaLineaList = coRetVenta
          .getCORETENCIONVENTALINEAList();

      for (Co_Retencion_Venta_Linea list : retencionVentaLineaList) {
        whereClause2 = new StringBuilder();
        whereClause2.append(" as tra, CO_RETENCION_VENTA_LINEA  as rvl ");
        whereClause2
            .append(" where rvl.retencinDeVenta.tipoDeRetencin.id = tra.tipoDeRetencin.id ");
        whereClause2.append(" and tra.accountingSchema.id = '" + as.m_C_AcctSchema_ID + "'");
        whereClause2.append(" and tra.tipoDeRetencin.id = '"
            + list.getRetencinDeVenta().getTipoDeRetencin().getId() + "'");

        final OBQuery<CO_TipoRetencionAcct> obqParamAcct = OBDal.getInstance().createQuery(
            CO_TipoRetencionAcct.class, whereClause2.toString());

        fact.createLine(null,
            Account.getAccount(conn, obqParamAcct.list().get(0).getVentaAcct().getId()),
            C_Currency_ID, list.getValorDeLaRetencin().abs().toString(), ZERO.toString(),
            Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);
      }

      whereClause3.append(" as va where va.businessPartner = '"
          + coRetVenta.getInvoice().getBusinessPartner().getId() + "'");
      whereClause3.append(" and va.accountingSchema.id = '" + as.m_C_AcctSchema_ID + "'");

      final OBQuery<CustomerAccounts> obqParameters = OBDal.getInstance().createQuery(
          CustomerAccounts.class, whereClause3.toString());

      fact.createLine(null,
          Account.getAccount(conn, obqParameters.list().get(0).getCustomerReceivablesNo().getId()),
          C_Currency_ID, ZERO.toString(), coRetVenta.getTotal().abs().toString(),
          Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);

    } finally {
      OBContext.restorePreviousMode();
    }

    return fact;
  }

  @Override
  public boolean getDocumentConfirmation(ConnectionProvider conn, String strRecordId) {

    int intCountTR = 0;
    int intCountConfig = 0;

    CO_RETENCION_VENTA coRetVenta = OBDal.getInstance().get(CO_RETENCION_VENTA.class, strRecordId);

    final OBQuery<org.openbravo.model.financialmgmt.accounting.coa.AcctSchema> accs = OBDal
        .getInstance().createQuery(
            org.openbravo.model.financialmgmt.accounting.coa.AcctSchema.class,
            "as e where e.client.id = '" + AD_Client_ID + "'");
    final List<org.openbravo.model.financialmgmt.accounting.coa.AcctSchema> acctSchema = accs
        .list();

    for (org.openbravo.model.financialmgmt.accounting.coa.AcctSchema acSc : acctSchema) {

      intCountTR = coRetVenta.getCORETENCIONVENTALINEAList().size();

      for (Co_Retencion_Venta_Linea crl : coRetVenta.getCORETENCIONVENTALINEAList()) {
        final OBQuery<CO_TipoRetencionAcct> tra = OBDal.getInstance().createQuery(
            CO_TipoRetencionAcct.class,
            " as e where e.accountingSchema.id ='" + acSc.getId() + "'" + " and e.client.id = '"
                + AD_Client_ID + "'" + " and e.tipoDeRetencin.id = '"
                + crl.getRetencinDeVenta().getTipoDeRetencin().getId() + "'");

        if (tra.list().size() == 0) {
          intCountConfig++;
        }
      }

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
