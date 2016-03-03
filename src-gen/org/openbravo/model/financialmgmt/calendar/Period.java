/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2008-2014 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
*/
package org.openbravo.model.financialmgmt.calendar;

import com.atrums.centrocostos.data.ccoCostosNomina;
import com.atrums.centrocostos.data.cco_registra_costos;
import com.atrums.contabilidad.data.CO_RETENCION_VENTA;
import com.atrums.contabilidad.data.CO_Retencion_Compra;
import com.atrums.declaraciontributaria.ecuador.data.atsProcesaAnexo;
import com.atrums.nomina.data.NO_Registro_Hora_Extra;
import com.atrums.nomina.data.noLiquidacionEmpleado;
import com.atrums.nomina.data.noNovedad;
import com.atrums.nomina.data.noPagoCabecera;
import com.atrums.nomina.data.noPrestamo;
import com.atrums.nomina.data.noReComision;
import com.atrums.nomina.data.noRegistraQuincena;
import com.atrums.nomina.data.noRegistroGasto;
import com.atrums.nomina.data.noRolPagoProvision;
import com.atrums.nomina.data.noRolProvisionLineMes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.dataimport.BudgetLine;
import org.openbravo.model.dataimport.GLJournal;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.gl.GLBatch;
/**
 * Entity class for entity FinancialMgmtPeriod (stored in table C_Period).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Period extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Period";
    public static final String ENTITY_NAME = "FinancialMgmtPeriod";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PERIODNO = "periodNo";
    public static final String PROPERTY_YEAR = "year";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_PERIODTYPE = "periodType";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_CLOSINGFACTACCTGROUP = "closingFactAcctGroup";
    public static final String PROPERTY_REGULARIZATIONFACTACCTGROUP = "regularizationFactAcctGroup";
    public static final String PROPERTY_DIVIDEUPFACTACCTGROUP = "divideupFactAcctGroup";
    public static final String PROPERTY_OPENFACTACCTGROUP = "openFactAcctGroup";
    public static final String PROPERTY_OPENCLOSE = "openClose";
    public static final String PROPERTY__COMPUTEDCOLUMNS = "_computedColumns";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_ATSPROCESAANEXOLIST = "aTSPROCESAANEXOList";
    public static final String PROPERTY_RETENCIONVENTALIST = "rETENCIONVENTAList";
    public static final String PROPERTY_RETENCIONCOMPRALIST = "retencionCompraList";
    public static final String PROPERTY_DATAIMPORTBUDGETLINELIST = "dataImportBudgetLineList";
    public static final String PROPERTY_DATAIMPORTGLJOURNALLIST = "dataImportGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMALIST = "financialMgmtAcctSchemaList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_FINANCIALMGMTGLBATCHLIST = "financialMgmtGLBatchList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLIST = "financialMgmtGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTPERIODCONTROLLIST = "financialMgmtPeriodControlList";
    public static final String PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST = "financialMgmtPeriodControlVList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_RECOMISIONESLIST = "reComisionesList";
    public static final String PROPERTY_REGISTROHORAEXTRALIST = "registroHoraExtraList";
    public static final String PROPERTY_ROLPROVISIONLINEMESLIST = "rolProvisionLineMesList";
    public static final String PROPERTY_ROLPAGOPROVISIONLIST = "rolPagoProvisionList";
    public static final String PROPERTY_PERIODCONTROLLOGPERIODNOLIST = "periodControlLogPeriodNoList";
    public static final String PROPERTY_PERIODCONTROLLOGLIST = "periodControlLogList";
    public static final String PROPERTY_CCOCOSTOSNOMINALIST = "ccoCostosNominaList";
    public static final String PROPERTY_CCOREGISTRACOSTOSLIST = "ccoRegistraCostosList";
    public static final String PROPERTY_LIQUIDACIONEMPLEADOLIST = "liquidacionEmpleadoList";
    public static final String PROPERTY_NOVEDADLIST = "novedadList";
    public static final String PROPERTY_PAGOCABECERALIST = "pagoCabeceraList";
    public static final String PROPERTY_PRESTAMOLIST = "prestamoList";
    public static final String PROPERTY_REGISTRAQUINCENALIST = "registraQuincenaList";
    public static final String PROPERTY_REGISTROGASTOLIST = "registroGastoList";


    // Computed columns properties, these properties cannot be directly accessed, they need
    // to be read through _commputedColumns proxy. They cannot be directly used in HQL, OBQuery
    // nor OBCriteria. 
    public static final String COMPUTED_COLUMN_STATUS = "status";

    public Period() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PERIODTYPE, "S");
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_OPENCLOSE, "O");
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ATSPROCESAANEXOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETENCIONVENTALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETENCIONCOMPRALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLBATCHLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPERIODCONTROLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RECOMISIONESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REGISTROHORAEXTRALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ROLPROVISIONLINEMESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ROLPAGOPROVISIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PERIODCONTROLLOGPERIODNOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PERIODCONTROLLOGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CCOCOSTOSNOMINALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CCOREGISTRACOSTOSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LIQUIDACIONEMPLEADOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOVEDADLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PAGOCABECERALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRESTAMOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REGISTRAQUINCENALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REGISTROGASTOLIST, new ArrayList<Object>());
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Long getPeriodNo() {
        return (Long) get(PROPERTY_PERIODNO);
    }

    public void setPeriodNo(Long periodNo) {
        set(PROPERTY_PERIODNO, periodNo);
    }

    public Year getYear() {
        return (Year) get(PROPERTY_YEAR);
    }

    public void setYear(Year year) {
        set(PROPERTY_YEAR, year);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public String getPeriodType() {
        return (String) get(PROPERTY_PERIODTYPE);
    }

    public void setPeriodType(String periodType) {
        set(PROPERTY_PERIODTYPE, periodType);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public String getClosingFactAcctGroup() {
        return (String) get(PROPERTY_CLOSINGFACTACCTGROUP);
    }

    public void setClosingFactAcctGroup(String closingFactAcctGroup) {
        set(PROPERTY_CLOSINGFACTACCTGROUP, closingFactAcctGroup);
    }

    public String getRegularizationFactAcctGroup() {
        return (String) get(PROPERTY_REGULARIZATIONFACTACCTGROUP);
    }

    public void setRegularizationFactAcctGroup(String regularizationFactAcctGroup) {
        set(PROPERTY_REGULARIZATIONFACTACCTGROUP, regularizationFactAcctGroup);
    }

    public String getDivideupFactAcctGroup() {
        return (String) get(PROPERTY_DIVIDEUPFACTACCTGROUP);
    }

    public void setDivideupFactAcctGroup(String divideupFactAcctGroup) {
        set(PROPERTY_DIVIDEUPFACTACCTGROUP, divideupFactAcctGroup);
    }

    public String getOpenFactAcctGroup() {
        return (String) get(PROPERTY_OPENFACTACCTGROUP);
    }

    public void setOpenFactAcctGroup(String openFactAcctGroup) {
        set(PROPERTY_OPENFACTACCTGROUP, openFactAcctGroup);
    }

    public String getOpenClose() {
        return (String) get(PROPERTY_OPENCLOSE);
    }

    public void setOpenClose(String openClose) {
        set(PROPERTY_OPENCLOSE, openClose);
    }

    public String getStatus() {
        return (String) get(COMPUTED_COLUMN_STATUS);
    }

    public void setStatus(String status) {
        set(COMPUTED_COLUMN_STATUS, status);
    }

    public Period_ComputedColumns get_computedColumns() {
        return (Period_ComputedColumns) get(PROPERTY__COMPUTEDCOLUMNS);
    }

    public void set_computedColumns(Period_ComputedColumns _computedColumns) {
        set(PROPERTY__COMPUTEDCOLUMNS, _computedColumns);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
      return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
      return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<atsProcesaAnexo> getATSPROCESAANEXOList() {
      return (List<atsProcesaAnexo>) get(PROPERTY_ATSPROCESAANEXOLIST);
    }

    public void setATSPROCESAANEXOList(List<atsProcesaAnexo> aTSPROCESAANEXOList) {
        set(PROPERTY_ATSPROCESAANEXOLIST, aTSPROCESAANEXOList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_RETENCION_VENTA> getRETENCIONVENTAList() {
      return (List<CO_RETENCION_VENTA>) get(PROPERTY_RETENCIONVENTALIST);
    }

    public void setRETENCIONVENTAList(List<CO_RETENCION_VENTA> rETENCIONVENTAList) {
        set(PROPERTY_RETENCIONVENTALIST, rETENCIONVENTAList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_Retencion_Compra> getRetencionCompraList() {
      return (List<CO_Retencion_Compra>) get(PROPERTY_RETENCIONCOMPRALIST);
    }

    public void setRetencionCompraList(List<CO_Retencion_Compra> retencionCompraList) {
        set(PROPERTY_RETENCIONCOMPRALIST, retencionCompraList);
    }

    @SuppressWarnings("unchecked")
    public List<BudgetLine> getDataImportBudgetLineList() {
      return (List<BudgetLine>) get(PROPERTY_DATAIMPORTBUDGETLINELIST);
    }

    public void setDataImportBudgetLineList(List<BudgetLine> dataImportBudgetLineList) {
        set(PROPERTY_DATAIMPORTBUDGETLINELIST, dataImportBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getDataImportGLJournalList() {
      return (List<GLJournal>) get(PROPERTY_DATAIMPORTGLJOURNALLIST);
    }

    public void setDataImportGLJournalList(List<GLJournal> dataImportGLJournalList) {
        set(PROPERTY_DATAIMPORTGLJOURNALLIST, dataImportGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
      return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchema> getFinancialMgmtAcctSchemaList() {
      return (List<AcctSchema>) get(PROPERTY_FINANCIALMGMTACCTSCHEMALIST);
    }

    public void setFinancialMgmtAcctSchemaList(List<AcctSchema> financialMgmtAcctSchemaList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMALIST, financialMgmtAcctSchemaList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.financialmgmt.accounting.BudgetLine> getFinancialMgmtBudgetLineList() {
      return (List<org.openbravo.model.financialmgmt.accounting.BudgetLine>) get(PROPERTY_FINANCIALMGMTBUDGETLINELIST);
    }

    public void setFinancialMgmtBudgetLineList(List<org.openbravo.model.financialmgmt.accounting.BudgetLine> financialMgmtBudgetLineList) {
        set(PROPERTY_FINANCIALMGMTBUDGETLINELIST, financialMgmtBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<GLBatch> getFinancialMgmtGLBatchList() {
      return (List<GLBatch>) get(PROPERTY_FINANCIALMGMTGLBATCHLIST);
    }

    public void setFinancialMgmtGLBatchList(List<GLBatch> financialMgmtGLBatchList) {
        set(PROPERTY_FINANCIALMGMTGLBATCHLIST, financialMgmtGLBatchList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.financialmgmt.gl.GLJournal> getFinancialMgmtGLJournalList() {
      return (List<org.openbravo.model.financialmgmt.gl.GLJournal>) get(PROPERTY_FINANCIALMGMTGLJOURNALLIST);
    }

    public void setFinancialMgmtGLJournalList(List<org.openbravo.model.financialmgmt.gl.GLJournal> financialMgmtGLJournalList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLIST, financialMgmtGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControl> getFinancialMgmtPeriodControlList() {
      return (List<PeriodControl>) get(PROPERTY_FINANCIALMGMTPERIODCONTROLLIST);
    }

    public void setFinancialMgmtPeriodControlList(List<PeriodControl> financialMgmtPeriodControlList) {
        set(PROPERTY_FINANCIALMGMTPERIODCONTROLLIST, financialMgmtPeriodControlList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControlV> getFinancialMgmtPeriodControlVList() {
      return (List<PeriodControlV>) get(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST);
    }

    public void setFinancialMgmtPeriodControlVList(List<PeriodControlV> financialMgmtPeriodControlVList) {
        set(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST, financialMgmtPeriodControlVList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
      return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<noReComision> getReComisionesList() {
      return (List<noReComision>) get(PROPERTY_RECOMISIONESLIST);
    }

    public void setReComisionesList(List<noReComision> reComisionesList) {
        set(PROPERTY_RECOMISIONESLIST, reComisionesList);
    }

    @SuppressWarnings("unchecked")
    public List<NO_Registro_Hora_Extra> getRegistroHoraExtraList() {
      return (List<NO_Registro_Hora_Extra>) get(PROPERTY_REGISTROHORAEXTRALIST);
    }

    public void setRegistroHoraExtraList(List<NO_Registro_Hora_Extra> registroHoraExtraList) {
        set(PROPERTY_REGISTROHORAEXTRALIST, registroHoraExtraList);
    }

    @SuppressWarnings("unchecked")
    public List<noRolProvisionLineMes> getRolProvisionLineMesList() {
      return (List<noRolProvisionLineMes>) get(PROPERTY_ROLPROVISIONLINEMESLIST);
    }

    public void setRolProvisionLineMesList(List<noRolProvisionLineMes> rolProvisionLineMesList) {
        set(PROPERTY_ROLPROVISIONLINEMESLIST, rolProvisionLineMesList);
    }

    @SuppressWarnings("unchecked")
    public List<noRolPagoProvision> getRolPagoProvisionList() {
      return (List<noRolPagoProvision>) get(PROPERTY_ROLPAGOPROVISIONLIST);
    }

    public void setRolPagoProvisionList(List<noRolPagoProvision> rolPagoProvisionList) {
        set(PROPERTY_ROLPAGOPROVISIONLIST, rolPagoProvisionList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControlLog> getPeriodControlLogPeriodNoList() {
      return (List<PeriodControlLog>) get(PROPERTY_PERIODCONTROLLOGPERIODNOLIST);
    }

    public void setPeriodControlLogPeriodNoList(List<PeriodControlLog> periodControlLogPeriodNoList) {
        set(PROPERTY_PERIODCONTROLLOGPERIODNOLIST, periodControlLogPeriodNoList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControlLog> getPeriodControlLogList() {
      return (List<PeriodControlLog>) get(PROPERTY_PERIODCONTROLLOGLIST);
    }

    public void setPeriodControlLogList(List<PeriodControlLog> periodControlLogList) {
        set(PROPERTY_PERIODCONTROLLOGLIST, periodControlLogList);
    }

    @SuppressWarnings("unchecked")
    public List<ccoCostosNomina> getCcoCostosNominaList() {
      return (List<ccoCostosNomina>) get(PROPERTY_CCOCOSTOSNOMINALIST);
    }

    public void setCcoCostosNominaList(List<ccoCostosNomina> ccoCostosNominaList) {
        set(PROPERTY_CCOCOSTOSNOMINALIST, ccoCostosNominaList);
    }

    @SuppressWarnings("unchecked")
    public List<cco_registra_costos> getCcoRegistraCostosList() {
      return (List<cco_registra_costos>) get(PROPERTY_CCOREGISTRACOSTOSLIST);
    }

    public void setCcoRegistraCostosList(List<cco_registra_costos> ccoRegistraCostosList) {
        set(PROPERTY_CCOREGISTRACOSTOSLIST, ccoRegistraCostosList);
    }

    @SuppressWarnings("unchecked")
    public List<noLiquidacionEmpleado> getLiquidacionEmpleadoList() {
      return (List<noLiquidacionEmpleado>) get(PROPERTY_LIQUIDACIONEMPLEADOLIST);
    }

    public void setLiquidacionEmpleadoList(List<noLiquidacionEmpleado> liquidacionEmpleadoList) {
        set(PROPERTY_LIQUIDACIONEMPLEADOLIST, liquidacionEmpleadoList);
    }

    @SuppressWarnings("unchecked")
    public List<noNovedad> getNovedadList() {
      return (List<noNovedad>) get(PROPERTY_NOVEDADLIST);
    }

    public void setNovedadList(List<noNovedad> novedadList) {
        set(PROPERTY_NOVEDADLIST, novedadList);
    }

    @SuppressWarnings("unchecked")
    public List<noPagoCabecera> getPagoCabeceraList() {
      return (List<noPagoCabecera>) get(PROPERTY_PAGOCABECERALIST);
    }

    public void setPagoCabeceraList(List<noPagoCabecera> pagoCabeceraList) {
        set(PROPERTY_PAGOCABECERALIST, pagoCabeceraList);
    }

    @SuppressWarnings("unchecked")
    public List<noPrestamo> getPrestamoList() {
      return (List<noPrestamo>) get(PROPERTY_PRESTAMOLIST);
    }

    public void setPrestamoList(List<noPrestamo> prestamoList) {
        set(PROPERTY_PRESTAMOLIST, prestamoList);
    }

    @SuppressWarnings("unchecked")
    public List<noRegistraQuincena> getRegistraQuincenaList() {
      return (List<noRegistraQuincena>) get(PROPERTY_REGISTRAQUINCENALIST);
    }

    public void setRegistraQuincenaList(List<noRegistraQuincena> registraQuincenaList) {
        set(PROPERTY_REGISTRAQUINCENALIST, registraQuincenaList);
    }

    @SuppressWarnings("unchecked")
    public List<noRegistroGasto> getRegistroGastoList() {
      return (List<noRegistroGasto>) get(PROPERTY_REGISTROGASTOLIST);
    }

    public void setRegistroGastoList(List<noRegistroGasto> registroGastoList) {
        set(PROPERTY_REGISTROGASTOLIST, registroGastoList);
    }


    @Override
    public Object get(String propName) {
      if (COMPUTED_COLUMN_STATUS.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getStatus();
      }
    
      return super.get(propName);
    }
}
