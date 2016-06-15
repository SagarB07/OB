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
import org.openbravo.model.common.businesspartner.EmployeeAccounts;
import org.openbravo.model.financialmgmt.accounting.FIN_FinancialAccountAccounting;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;

import com.atrums.nomina.data.noCbEmpleadoAcct;
import com.atrums.nomina.data.noRegistraQuincLine;
import com.atrums.nomina.data.noRegistraQuincena;

public class AvanceProceso extends AcctServer {

  public static final String DOCTYPE_Avance = "NO_AV";

  private static final long serialVersionUID = 1L;
  private static final Logger log4j = Logger.getLogger(DocFINFinAccTransaction.class);

  String SeqNo = "0";
  BigDecimal totalAmount = BigDecimal.ZERO;

  public AvanceProceso() {
  }

  public AvanceProceso(String AD_Client_ID, String AD_Org_ID, ConnectionProvider connectionProvider) {
    super(AD_Client_ID, AD_Org_ID, connectionProvider);
  }

  @Override
  public void loadObjectFieldProvider(ConnectionProvider conn, String AD_Client_ID, String Id)
      throws ServletException {
    noRegistraQuincLine noRegQuincLine = OBDal.getInstance().get(noRegistraQuincLine.class,
        Record_ID);

    FieldProviderFactory[] data = new FieldProviderFactory[1];
    OBContext.setAdminMode();

    try {
      data[0] = new FieldProviderFactory(null);
      FieldProviderFactory.setField(data[0], "AD_Client_ID", noRegQuincLine.getClient().getId());
      FieldProviderFactory.setField(data[0], "AD_Org_ID", noRegQuincLine.getOrganization().getId());
      FieldProviderFactory.setField(data[0], "AD_OrgTrx_ID", noRegQuincLine.getOrganization()
          .getId());
      FieldProviderFactory.setField(data[0], "No_Registra_Quinc_Line_ID", noRegQuincLine.getId());
      FieldProviderFactory.setField(data[0], "C_Currency_ID", noRegQuincLine.getCurrency().getId());
      FieldProviderFactory.setField(data[0], "C_Doctype_ID", noRegQuincLine.getDocumentType()
          .getId());
      FieldProviderFactory.setField(data[0], "C_BPartner_ID", noRegQuincLine.getBusinessPartner()
          .getId());
      FieldProviderFactory.setField(data[0], "DocumentNo", noRegQuincLine.getDocumentNo());
      String dateFormat = OBPropertiesProvider.getInstance().getOpenbravoProperties()
          .getProperty("dateFormat.java");
      SimpleDateFormat outputFormat = new SimpleDateFormat(dateFormat);
      FieldProviderFactory.setField(data[0], "DateAcct",// "statementDate",
          outputFormat.format(noRegQuincLine.getDateacct()));
      FieldProviderFactory.setField(data[0], "Posted", noRegQuincLine.getContabilizar().toString());
      FieldProviderFactory.setField(data[0], "Processed", noRegQuincLine.isProcesado() ? "Y" : "N");
      FieldProviderFactory.setField(data[0], "Processing", "N");

    } finally {
      OBContext.restorePreviousMode();

    }
    setObjectFieldProvider(data);
  }

  @Override
  public boolean loadDocumentDetails(FieldProvider[] data, ConnectionProvider conn) {
    DocumentType = DOCTYPE_Avance;
    DateDoc = data[0].getField("Dateacct");
    C_DocType_ID = data[0].getField("C_Doctype_ID");
    DocumentNo = data[0].getField("DocumentNo");
    OBContext.setAdminMode();
    try {
      noRegistraQuincLine noRegQuincLine = OBDal.getInstance().get(noRegistraQuincLine.class,
          Record_ID);

      totalAmount = noRegQuincLine.getValor();
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
    // String strClassname = "";
    final StringBuilder whereClause = new StringBuilder();
    final StringBuilder whereClause2 = new StringBuilder();

    Fact fact = new Fact(this, as, Fact.POST_Actual);
    OBContext.setAdminMode();
    try {

      noRegistraQuincLine noRegQuincLine = OBDal.getInstance().get(noRegistraQuincLine.class,
          Record_ID);

      String Fact_Acct_Group_ID = SequenceIdData.getUUID();

      whereClause.append(" as nea, no_registra_quinc_line as rql where rql.id = '" + Record_ID
          + "'");
      whereClause.append(" and rql.rubro.id = nea.tipoDeIngresoEgreso.id ");
      whereClause.append(" and nea.accountingSchema.id = '" + as.m_C_AcctSchema_ID + "'");

      final OBQuery<noCbEmpleadoAcct> obqParameters = OBDal.getInstance().createQuery(
          noCbEmpleadoAcct.class, whereClause.toString());

      if (obqParameters.list().size()>0){
      fact.createLine(null,
          Account.getAccount(conn, obqParameters.list().get(0).getCuentaDelIngreso().getId()),
          C_Currency_ID, ZERO.toString(), noRegQuincLine.getValor().abs().toString(),
          Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);
      }

      // Inicio: Esto es sin se necesita que el asiento contable sea con la cuenta del Egreso del
      // rubro
      /*
       * fact.createLine(null, Account.getAccount(conn,
       * obqParameters.list().get(0).getCuentaDelEgreso().getId()), C_Currency_ID,
       * noRegQuincLine.getValor().abs().toString(), ZERO.toString(), Fact_Acct_Group_ID,
       * nextSeqNo(SeqNo), DocumentType, conn);
       */
      // Fin: Esto es sin se necesita que el asiento contable sea con la cuenta del Egreso del rubro

      // Inicio: Cuenta de egreso, aqui esta con la cuenta del pago del empleado
      whereClause2.append(" as ea where ea.businessPartner.id = '"
          + noRegQuincLine.getBusinessPartner().getId() + "'");
      whereClause2.append(" and ea.accountingSchema.id = '" + as.m_C_AcctSchema_ID + "'");

      final OBQuery<EmployeeAccounts> obqParamAcct = OBDal.getInstance().createQuery(
          EmployeeAccounts.class, whereClause2.toString());
      if (obqParamAcct.list() != null && obqParamAcct.list().size()>0 && obqParamAcct.list().get(0) != null && 
    		  obqParamAcct.list().get(0).getNoCuentaPago() != null && obqParamAcct.list().get(0).getNoCuentaPago().getId() != null){
      fact.createLine(null,
          Account.getAccount(conn, obqParamAcct.list().get(0).getNoCuentaPago().getId()),
          C_Currency_ID, noRegQuincLine.getValor().abs().toString(), ZERO.toString(),
          Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);
      }
      // Fin: Cuenta de egreso, aqui esta con la cuenta del pago del empleado
    } finally {
      OBContext.restorePreviousMode();
    }

    return fact;
  }

  @Override
  public boolean getDocumentConfirmation(ConnectionProvider conn, String strRecordId) {

    int intCountTR = 0;
    int intCountConfig = 0;

    noRegistraQuincena noRegQuinc = OBDal.getInstance().get(noRegistraQuincena.class, strRecordId);
    noRegistraQuincLine noRegQuincLine = OBDal.getInstance().get(noRegistraQuincLine.class,
        strRecordId);

    final OBQuery<org.openbravo.model.financialmgmt.accounting.coa.AcctSchema> accs = OBDal
        .getInstance().createQuery(
            org.openbravo.model.financialmgmt.accounting.coa.AcctSchema.class,
            "as e where e.client.id = '" + AD_Client_ID + "'");
    final List<org.openbravo.model.financialmgmt.accounting.coa.AcctSchema> acctSchema = accs
        .list();

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
