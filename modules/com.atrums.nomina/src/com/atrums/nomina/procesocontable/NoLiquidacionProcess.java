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

import com.atrums.nomina.data.noLiquidaEmpleadoLine;
import com.atrums.nomina.data.noLiquidacionEmpleado;
import com.atrums.nomina.data.no_tipo_rub_liqu_emp_acct;

public class NoLiquidacionProcess extends AcctServer {

  public static final String DOCTYPE_RetencionCompra = "NO_NL";

  private static final long serialVersionUID = 1L;
  private static final Logger log4j = Logger.getLogger(DocFINFinAccTransaction.class);

  String SeqNo = "0";
  BigDecimal totalAmount = BigDecimal.ZERO;

  public NoLiquidacionProcess() {
  }

  public NoLiquidacionProcess(String AD_Client_ID, String AD_Org_ID,
      ConnectionProvider connectionProvider) {
    super(AD_Client_ID, AD_Org_ID, connectionProvider);
  }

  @Override
  public void loadObjectFieldProvider(ConnectionProvider conn, String AD_Client_ID, String Id)
      throws ServletException {
    noLiquidacionEmpleado noLiqEmp = OBDal.getInstance()
        .get(noLiquidacionEmpleado.class, Record_ID);

    FieldProviderFactory[] data = new FieldProviderFactory[1];
    OBContext.setAdminMode();

    try {
      data[0] = new FieldProviderFactory(null);
      FieldProviderFactory.setField(data[0], "AD_Client_ID", noLiqEmp.getClient().getId());
      FieldProviderFactory.setField(data[0], "AD_Org_ID", noLiqEmp.getOrganization().getId());
      FieldProviderFactory.setField(data[0], "AD_OrgTrx_ID", noLiqEmp.getOrganization().getId());
      FieldProviderFactory.setField(data[0], "NO_liquidacion_empleado_ID", noLiqEmp.getId());
      FieldProviderFactory.setField(data[0], "C_Currency_ID", noLiqEmp.getCurrency().getId());
      FieldProviderFactory.setField(data[0], "C_BPartner_ID", noLiqEmp.getEmpleado().getId());
      FieldProviderFactory.setField(data[0], "C_Doctype_ID", noLiqEmp.getDocumentType().getId());
      FieldProviderFactory.setField(data[0], "DocumentNo", noLiqEmp.getDocumentNo());
      String dateFormat = OBPropertiesProvider.getInstance().getOpenbravoProperties()
          .getProperty("dateFormat.java");
      SimpleDateFormat outputFormat = new SimpleDateFormat(dateFormat);
      FieldProviderFactory.setField(data[0], "statementDate",
          outputFormat.format(noLiqEmp.getDateacct()));
      FieldProviderFactory.setField(data[0], "Posted", (noLiqEmp.getPosted().toString()));
      FieldProviderFactory.setField(data[0], "Processed", noLiqEmp.isProcessed() ? "Y" : "N");
      FieldProviderFactory.setField(data[0], "Processing", "N");

    } finally {
      OBContext.restorePreviousMode();

    }
    setObjectFieldProvider(data);
  }

  @Override
  public boolean loadDocumentDetails(FieldProvider[] data, ConnectionProvider conn) {
    DocumentType = DOCTYPE_RetencionCompra;
    DateDoc = data[0].getField("statementDate");
    C_DocType_ID = data[0].getField("C_Doctype_ID");
    DocumentNo = data[0].getField("DocumentNo");
    OBContext.setAdminMode();
    try {
      noLiquidacionEmpleado noLiqEmp = OBDal.getInstance().get(noLiquidacionEmpleado.class,
          Record_ID);

      totalAmount = noLiqEmp.getTotalNeto();
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

    StringBuilder whereClause2;
    StringBuilder whereClause4 = new StringBuilder();
    final StringBuilder whereClause3 = new StringBuilder();

    Fact fact = new Fact(this, as, Fact.POST_Actual);

    String Fact_Acct_Group_ID = SequenceIdData.getUUID();

    OBContext.setAdminMode();
    try {

      noLiquidacionEmpleado noLiqEmp = OBDal.getInstance().get(noLiquidacionEmpleado.class,
          Record_ID);

      whereClause4.append(" as nlel");
      whereClause4.append(" where nlel.iDLiquidacionEmpleado.id = '" + noLiqEmp.getId() + "'");
      whereClause4.append(" order by nlel.iDTipoRubro.pago desc");
      final OBQuery<noLiquidaEmpleadoLine> noLiqEmpLinea = OBDal.getInstance().createQuery(
          noLiquidaEmpleadoLine.class, whereClause4.toString());
      final List<noLiquidaEmpleadoLine> noLiqEmpLineaList = noLiqEmpLinea.list();

      for (noLiquidaEmpleadoLine liqEmpLiAux : noLiqEmpLineaList) {
        whereClause2 = new StringBuilder();
        whereClause2.append(" as tra, no_liquida_empleado_line as nlel ");
        whereClause2.append(" where nlel.iDTipoRubro.id = tra.tipoRubroLiquEmp.id ");
        whereClause2.append(" and tra.acctschema.id = '" + as.m_C_AcctSchema_ID + "'");
        whereClause2.append(" and tra.tipoRubroLiquEmp.id = '"
            + liqEmpLiAux.getIDTipoRubro().getId() + "'");

        final OBQuery<no_tipo_rub_liqu_emp_acct> obqParameters = OBDal.getInstance().createQuery(
            no_tipo_rub_liqu_emp_acct.class, whereClause2.toString());

        if (liqEmpLiAux.getIDTipoRubro().isPago()) {
          fact.createLine(null,
              Account.getAccount(conn, obqParameters.list().get(0).getCuentaRubroHaber().getId()),
              C_Currency_ID, ZERO.toString(), liqEmpLiAux.getValor().abs().toString(),
              Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);

        } else {

          fact.createLine(null,
              Account.getAccount(conn, obqParameters.list().get(0).getCuentaRubroDebe().getId()),
              C_Currency_ID, liqEmpLiAux.getValor().abs().toString(), ZERO.toString(),
              Fact_Acct_Group_ID, nextSeqNo(SeqNo), DocumentType, conn);

        }

      }

      whereClause3.append(" as ea where ea.businessPartner = '" + noLiqEmp.getEmpleado().getId()
          + "'");
      whereClause3.append(" and ea.accountingSchema = '" + as.m_C_AcctSchema_ID + "'");

      final OBQuery<EmployeeAccounts> obqParamAcct = OBDal.getInstance().createQuery(
          EmployeeAccounts.class, whereClause3.toString());

      fact.createLine(null,
          Account.getAccount(conn, obqParamAcct.list().get(0).getNoCuentaPago().getId()),
          C_Currency_ID, noLiqEmp.getTotalNeto().abs().toString(), ZERO.toString(),
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

    // CO_Retencion_Compra coRetCompra = OBDal.getInstance().get(CO_Retencion_Compra.class,
    // strRecordId);

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
