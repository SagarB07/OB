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
package org.openbravo.model.common.enterprise;

import com.atrums.contabilidad.Co_Sustento_Doc;
import com.atrums.contabilidad.data.CO_Aut_Sri;
import com.atrums.contabilidad.data.CO_RETENCION_VENTA;
import com.atrums.contabilidad.data.CO_Retencion_Compra;
import com.atrums.declaraciontributaria.ecuador.data.Ats_Sustento_Doc;
import com.atrums.declaraciontributaria.ecuador.data.atsReembolso;
import com.atrums.importaciondatos.IdtContrato;
import com.atrums.nomina.data.noContratoEmpleado;
import com.atrums.nomina.data.noLiquidacionEmpleado;
import com.atrums.nomina.data.noPagoCabecera;
import com.atrums.nomina.data.noPagoLine;
import com.atrums.nomina.data.noRegistraQuincLine;
import com.atrums.nomina.data.noRolPagoProvision;
import com.atrums.nomina.data.noRolPagoProvisionLine;
import com.atrums.nomina.data.noRolProvisionLineMes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_Reconciliation_v;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.common.invoice.InvoiceV2;
import org.openbravo.model.dataimport.GLJournal;
import org.openbravo.model.dataimport.Invoice;
import org.openbravo.model.dataimport.Order;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.gl.GLCategory;
import org.openbravo.model.financialmgmt.payment.DPManagement;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebt;
import org.openbravo.model.financialmgmt.payment.FIN_BankStatement;
import org.openbravo.model.financialmgmt.payment.FIN_Payment;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentProposal;
import org.openbravo.model.financialmgmt.payment.FIN_Reconciliation;
import org.openbravo.model.financialmgmt.payment.Settlement;
import org.openbravo.model.financialmgmt.tax.TaxRegisterTypeLines;
import org.openbravo.model.manufacturing.transaction.WorkRequirement;
import org.openbravo.model.materialmgmt.transaction.InternalMovement;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.pos.ExternalPOS;
/**
 * Entity class for entity DocumentType (stored in table C_DocType).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class DocumentType extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_DocType";
    public static final String ENTITY_NAME = "DocumentType";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PRINTTEXT = "printText";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_DOCUMENTCATEGORY = "documentCategory";
    public static final String PROPERTY_SALESTRANSACTION = "salesTransaction";
    public static final String PROPERTY_SOSUBTYPE = "sOSubType";
    public static final String PROPERTY_DOCUMENTTYPEFORSHIPMENT = "documentTypeForShipment";
    public static final String PROPERTY_DOCUMENTTYPEFORINVOICE = "documentTypeForInvoice";
    public static final String PROPERTY_SEQUENCEDDOCUMENT = "sequencedDocument";
    public static final String PROPERTY_DOCUMENTSEQUENCE = "documentSequence";
    public static final String PROPERTY_GLCATEGORY = "gLCategory";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_NUMBEROFCOPIES = "numberOfCopies";
    public static final String PROPERTY_TABLE = "table";
    public static final String PROPERTY_FILTERBYORGANIZATION = "filterByOrganization";
    public static final String PROPERTY_DOCUMENTCANCELLED = "documentCancelled";
    public static final String PROPERTY_EXPENSE = "expense";
    public static final String PROPERTY_REVERSAL = "reversal";
    public static final String PROPERTY_RETURN = "return";
    public static final String PROPERTY_DOCUMENTTYPEFORORDER = "documentTypeForOrder";
    public static final String PROPERTY_ATECFEFACELEC = "atecfeFacElec";
    public static final String PROPERTY_COTIPOCOMPROBANTEAUTORIZADORSRI = "coTipoComprobanteAutorizadorSRI";
    public static final String PROPERTY_APRMRECONCILIATIONLIST = "aPRMReconciliationList";
    public static final String PROPERTY_ATSREEMBOLSOLIST = "aTSREEMBOLSOList";
    public static final String PROPERTY_AUTSRILIST = "autSriList";
    public static final String PROPERTY_RETENCIONVENTALIST = "rETENCIONVENTAList";
    public static final String PROPERTY_RETENCIONVENTATRANSACTIONDOCUMENTLIST = "rETENCIONVENTATransactionDocumentList";
    public static final String PROPERTY_RETENCIONCOMPRALIST = "retencionCompraList";
    public static final String PROPERTY_RETENCIONCOMPRATRANSACTIONDOCUMENTLIST = "retencionCompraTransactionDocumentList";
    public static final String PROPERTY_DATAIMPORTGLJOURNALLIST = "dataImportGLJournalList";
    public static final String PROPERTY_DATAIMPORTINVOICELIST = "dataImportInvoiceList";
    public static final String PROPERTY_DATAIMPORTORDERLIST = "dataImportOrderList";
    public static final String PROPERTY_DOCUMENTTEMPLATELIST = "documentTemplateList";
    public static final String PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORSHIPMENTLIST = "documentTypeDocumentTypeForShipmentList";
    public static final String PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORINVOICELIST = "documentTypeDocumentTypeForInvoiceList";
    public static final String PROPERTY_DOCUMENTTYPEDOCUMENTCANCELLEDLIST = "documentTypeDocumentCancelledList";
    public static final String PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORORDERLIST = "documentTypeDocumentTypeForOrderList";
    public static final String PROPERTY_DOCUMENTTYPETRLLIST = "documentTypeTrlList";
    public static final String PROPERTY_EXTERNALPOSLIST = "externalPOSList";
    public static final String PROPERTY_FINBANKSTATEMENTLIST = "fINBankStatementList";
    public static final String PROPERTY_FINDOUBTFULDEBTLIST = "fINDoubtfulDebtList";
    public static final String PROPERTY_FINPAYMENTLIST = "fINPaymentList";
    public static final String PROPERTY_FINPAYMENTPROPOSALLIST = "fINPaymentProposalList";
    public static final String PROPERTY_FINRECONCILIATIONLIST = "fINReconciliationList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTDPMANAGEMENTLIST = "financialMgmtDPManagementList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLIST = "financialMgmtGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTSETTLEMENTLIST = "financialMgmtSettlementList";
    public static final String PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST = "financialMgmtTaxRegisterTypeLinesList";
    public static final String PROPERTY_IDTCONTRATOLIST = "iDTContratoList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_INVOICETRANSACTIONDOCUMENTLIST = "invoiceTransactionDocumentList";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_INVOICEV2TRANSACTIONDOCUMENTLIST = "invoiceV2TransactionDocumentList";
    public static final String PROPERTY_MANUFACTURINGWORKREQUIREMENTLIST = "manufacturingWorkRequirementList";
    public static final String PROPERTY_MATERIALMGMTINTERNALMOVEMENTEMCODOCTYPEIDLIST = "materialMgmtInternalMovementEMCoDoctypeIDList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST = "materialMgmtShipmentInOutList";
    public static final String PROPERTY_ROLPAGOPROVISIONLINELIST = "rolPagoProvisionLineList";
    public static final String PROPERTY_ROLPROVISIONLINEMESLIST = "rolProvisionLineMesList";
    public static final String PROPERTY_ROLPAGOPROVISIONLIST = "rolPagoProvisionList";
    public static final String PROPERTY_ROLPAGOPROVISIONNOCDOCTYPEIDLIST = "rolPagoProvisionNOCDoctypeIDList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_ORDERTRANSACTIONDOCUMENTLIST = "orderTransactionDocumentList";
    public static final String PROPERTY_ATSSUSTENTODOCLIST = "atsSustentoDocList";
    public static final String PROPERTY_SUSTENTODOCLIST = "sustentoDocList";
    public static final String PROPERTY_CONTRATOEMPLEADOLIST = "contratoEmpleadoList";
    public static final String PROPERTY_LIQUIDACIONEMPLEADOLIST = "liquidacionEmpleadoList";
    public static final String PROPERTY_PAGOCABECERALIST = "pagoCabeceraList";
    public static final String PROPERTY_PAGOCABECERATIPODOCUMENTOPAGOLIST = "pagoCabeceraTipoDocumentoPagoList";
    public static final String PROPERTY_PAGOLINELIST = "pagoLineList";
    public static final String PROPERTY_REGISTRAQUINCLINELIST = "registraQuincLineList";

    public DocumentType() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SALESTRANSACTION, false);
        setDefaultValue(PROPERTY_SEQUENCEDDOCUMENT, true);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_NUMBEROFCOPIES, (long) 1);
        setDefaultValue(PROPERTY_FILTERBYORGANIZATION, false);
        setDefaultValue(PROPERTY_EXPENSE, false);
        setDefaultValue(PROPERTY_REVERSAL, false);
        setDefaultValue(PROPERTY_RETURN, false);
        setDefaultValue(PROPERTY_ATECFEFACELEC, false);
        setDefaultValue(PROPERTY_APRMRECONCILIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ATSREEMBOLSOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AUTSRILIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETENCIONVENTALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETENCIONVENTATRANSACTIONDOCUMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETENCIONCOMPRALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETENCIONCOMPRATRANSACTIONDOCUMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTEMPLATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORSHIPMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPEDOCUMENTCANCELLEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXTERNALPOSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINBANKSTATEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINRECONCILIATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDPMANAGEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTSETTLEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_IDTCONTRATOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICETRANSACTIONDOCUMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2TRANSACTIONDOCUMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGWORKREQUIREMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINTERNALMOVEMENTEMCODOCTYPEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ROLPAGOPROVISIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ROLPROVISIONLINEMESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ROLPAGOPROVISIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ROLPAGOPROVISIONNOCDOCTYPEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERTRANSACTIONDOCUMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ATSSUSTENTODOCLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SUSTENTODOCLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CONTRATOEMPLEADOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LIQUIDACIONEMPLEADOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PAGOCABECERALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PAGOCABECERATIPODOCUMENTOPAGOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PAGOLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REGISTRAQUINCLINELIST, new ArrayList<Object>());
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

    public String getPrintText() {
        return (String) get(PROPERTY_PRINTTEXT);
    }

    public void setPrintText(String printText) {
        set(PROPERTY_PRINTTEXT, printText);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getDocumentCategory() {
        return (String) get(PROPERTY_DOCUMENTCATEGORY);
    }

    public void setDocumentCategory(String documentCategory) {
        set(PROPERTY_DOCUMENTCATEGORY, documentCategory);
    }

    public Boolean isSalesTransaction() {
        return (Boolean) get(PROPERTY_SALESTRANSACTION);
    }

    public void setSalesTransaction(Boolean salesTransaction) {
        set(PROPERTY_SALESTRANSACTION, salesTransaction);
    }

    public String getSOSubType() {
        return (String) get(PROPERTY_SOSUBTYPE);
    }

    public void setSOSubType(String sOSubType) {
        set(PROPERTY_SOSUBTYPE, sOSubType);
    }

    public DocumentType getDocumentTypeForShipment() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPEFORSHIPMENT);
    }

    public void setDocumentTypeForShipment(DocumentType documentTypeForShipment) {
        set(PROPERTY_DOCUMENTTYPEFORSHIPMENT, documentTypeForShipment);
    }

    public DocumentType getDocumentTypeForInvoice() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPEFORINVOICE);
    }

    public void setDocumentTypeForInvoice(DocumentType documentTypeForInvoice) {
        set(PROPERTY_DOCUMENTTYPEFORINVOICE, documentTypeForInvoice);
    }

    public Boolean isSequencedDocument() {
        return (Boolean) get(PROPERTY_SEQUENCEDDOCUMENT);
    }

    public void setSequencedDocument(Boolean sequencedDocument) {
        set(PROPERTY_SEQUENCEDDOCUMENT, sequencedDocument);
    }

    public Sequence getDocumentSequence() {
        return (Sequence) get(PROPERTY_DOCUMENTSEQUENCE);
    }

    public void setDocumentSequence(Sequence documentSequence) {
        set(PROPERTY_DOCUMENTSEQUENCE, documentSequence);
    }

    public GLCategory getGLCategory() {
        return (GLCategory) get(PROPERTY_GLCATEGORY);
    }

    public void setGLCategory(GLCategory gLCategory) {
        set(PROPERTY_GLCATEGORY, gLCategory);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public Long getNumberOfCopies() {
        return (Long) get(PROPERTY_NUMBEROFCOPIES);
    }

    public void setNumberOfCopies(Long numberOfCopies) {
        set(PROPERTY_NUMBEROFCOPIES, numberOfCopies);
    }

    public Table getTable() {
        return (Table) get(PROPERTY_TABLE);
    }

    public void setTable(Table table) {
        set(PROPERTY_TABLE, table);
    }

    public Boolean isFilterByOrganization() {
        return (Boolean) get(PROPERTY_FILTERBYORGANIZATION);
    }

    public void setFilterByOrganization(Boolean filterByOrganization) {
        set(PROPERTY_FILTERBYORGANIZATION, filterByOrganization);
    }

    public DocumentType getDocumentCancelled() {
        return (DocumentType) get(PROPERTY_DOCUMENTCANCELLED);
    }

    public void setDocumentCancelled(DocumentType documentCancelled) {
        set(PROPERTY_DOCUMENTCANCELLED, documentCancelled);
    }

    public Boolean isExpense() {
        return (Boolean) get(PROPERTY_EXPENSE);
    }

    public void setExpense(Boolean expense) {
        set(PROPERTY_EXPENSE, expense);
    }

    public Boolean isReversal() {
        return (Boolean) get(PROPERTY_REVERSAL);
    }

    public void setReversal(Boolean reversal) {
        set(PROPERTY_REVERSAL, reversal);
    }

    public Boolean isReturn() {
        return (Boolean) get(PROPERTY_RETURN);
    }

    public void setReturn(Boolean rturn) {
        set(PROPERTY_RETURN, rturn);
    }

    public DocumentType getDocumentTypeForOrder() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPEFORORDER);
    }

    public void setDocumentTypeForOrder(DocumentType documentTypeForOrder) {
        set(PROPERTY_DOCUMENTTYPEFORORDER, documentTypeForOrder);
    }

    public Boolean isAtecfeFacElec() {
        return (Boolean) get(PROPERTY_ATECFEFACELEC);
    }

    public void setAtecfeFacElec(Boolean atecfeFacElec) {
        set(PROPERTY_ATECFEFACELEC, atecfeFacElec);
    }

    public Long getCoTipoComprobanteAutorizadorSRI() {
        return (Long) get(PROPERTY_COTIPOCOMPROBANTEAUTORIZADORSRI);
    }

    public void setCoTipoComprobanteAutorizadorSRI(Long coTipoComprobanteAutorizadorSRI) {
        set(PROPERTY_COTIPOCOMPROBANTEAUTORIZADORSRI, coTipoComprobanteAutorizadorSRI);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Reconciliation_v> getAPRMReconciliationList() {
      return (List<APRM_Reconciliation_v>) get(PROPERTY_APRMRECONCILIATIONLIST);
    }

    public void setAPRMReconciliationList(List<APRM_Reconciliation_v> aPRMReconciliationList) {
        set(PROPERTY_APRMRECONCILIATIONLIST, aPRMReconciliationList);
    }

    @SuppressWarnings("unchecked")
    public List<atsReembolso> getATSREEMBOLSOList() {
      return (List<atsReembolso>) get(PROPERTY_ATSREEMBOLSOLIST);
    }

    public void setATSREEMBOLSOList(List<atsReembolso> aTSREEMBOLSOList) {
        set(PROPERTY_ATSREEMBOLSOLIST, aTSREEMBOLSOList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_Aut_Sri> getAutSriList() {
      return (List<CO_Aut_Sri>) get(PROPERTY_AUTSRILIST);
    }

    public void setAutSriList(List<CO_Aut_Sri> autSriList) {
        set(PROPERTY_AUTSRILIST, autSriList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_RETENCION_VENTA> getRETENCIONVENTAList() {
      return (List<CO_RETENCION_VENTA>) get(PROPERTY_RETENCIONVENTALIST);
    }

    public void setRETENCIONVENTAList(List<CO_RETENCION_VENTA> rETENCIONVENTAList) {
        set(PROPERTY_RETENCIONVENTALIST, rETENCIONVENTAList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_RETENCION_VENTA> getRETENCIONVENTATransactionDocumentList() {
      return (List<CO_RETENCION_VENTA>) get(PROPERTY_RETENCIONVENTATRANSACTIONDOCUMENTLIST);
    }

    public void setRETENCIONVENTATransactionDocumentList(List<CO_RETENCION_VENTA> rETENCIONVENTATransactionDocumentList) {
        set(PROPERTY_RETENCIONVENTATRANSACTIONDOCUMENTLIST, rETENCIONVENTATransactionDocumentList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_Retencion_Compra> getRetencionCompraList() {
      return (List<CO_Retencion_Compra>) get(PROPERTY_RETENCIONCOMPRALIST);
    }

    public void setRetencionCompraList(List<CO_Retencion_Compra> retencionCompraList) {
        set(PROPERTY_RETENCIONCOMPRALIST, retencionCompraList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_Retencion_Compra> getRetencionCompraTransactionDocumentList() {
      return (List<CO_Retencion_Compra>) get(PROPERTY_RETENCIONCOMPRATRANSACTIONDOCUMENTLIST);
    }

    public void setRetencionCompraTransactionDocumentList(List<CO_Retencion_Compra> retencionCompraTransactionDocumentList) {
        set(PROPERTY_RETENCIONCOMPRATRANSACTIONDOCUMENTLIST, retencionCompraTransactionDocumentList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getDataImportGLJournalList() {
      return (List<GLJournal>) get(PROPERTY_DATAIMPORTGLJOURNALLIST);
    }

    public void setDataImportGLJournalList(List<GLJournal> dataImportGLJournalList) {
        set(PROPERTY_DATAIMPORTGLJOURNALLIST, dataImportGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getDataImportInvoiceList() {
      return (List<Invoice>) get(PROPERTY_DATAIMPORTINVOICELIST);
    }

    public void setDataImportInvoiceList(List<Invoice> dataImportInvoiceList) {
        set(PROPERTY_DATAIMPORTINVOICELIST, dataImportInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getDataImportOrderList() {
      return (List<Order>) get(PROPERTY_DATAIMPORTORDERLIST);
    }

    public void setDataImportOrderList(List<Order> dataImportOrderList) {
        set(PROPERTY_DATAIMPORTORDERLIST, dataImportOrderList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentTemplate> getDocumentTemplateList() {
      return (List<DocumentTemplate>) get(PROPERTY_DOCUMENTTEMPLATELIST);
    }

    public void setDocumentTemplateList(List<DocumentTemplate> documentTemplateList) {
        set(PROPERTY_DOCUMENTTEMPLATELIST, documentTemplateList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentType> getDocumentTypeDocumentTypeForShipmentList() {
      return (List<DocumentType>) get(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORSHIPMENTLIST);
    }

    public void setDocumentTypeDocumentTypeForShipmentList(List<DocumentType> documentTypeDocumentTypeForShipmentList) {
        set(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORSHIPMENTLIST, documentTypeDocumentTypeForShipmentList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentType> getDocumentTypeDocumentTypeForInvoiceList() {
      return (List<DocumentType>) get(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORINVOICELIST);
    }

    public void setDocumentTypeDocumentTypeForInvoiceList(List<DocumentType> documentTypeDocumentTypeForInvoiceList) {
        set(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORINVOICELIST, documentTypeDocumentTypeForInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentType> getDocumentTypeDocumentCancelledList() {
      return (List<DocumentType>) get(PROPERTY_DOCUMENTTYPEDOCUMENTCANCELLEDLIST);
    }

    public void setDocumentTypeDocumentCancelledList(List<DocumentType> documentTypeDocumentCancelledList) {
        set(PROPERTY_DOCUMENTTYPEDOCUMENTCANCELLEDLIST, documentTypeDocumentCancelledList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentType> getDocumentTypeDocumentTypeForOrderList() {
      return (List<DocumentType>) get(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORORDERLIST);
    }

    public void setDocumentTypeDocumentTypeForOrderList(List<DocumentType> documentTypeDocumentTypeForOrderList) {
        set(PROPERTY_DOCUMENTTYPEDOCUMENTTYPEFORORDERLIST, documentTypeDocumentTypeForOrderList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentTypeTrl> getDocumentTypeTrlList() {
      return (List<DocumentTypeTrl>) get(PROPERTY_DOCUMENTTYPETRLLIST);
    }

    public void setDocumentTypeTrlList(List<DocumentTypeTrl> documentTypeTrlList) {
        set(PROPERTY_DOCUMENTTYPETRLLIST, documentTypeTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ExternalPOS> getExternalPOSList() {
      return (List<ExternalPOS>) get(PROPERTY_EXTERNALPOSLIST);
    }

    public void setExternalPOSList(List<ExternalPOS> externalPOSList) {
        set(PROPERTY_EXTERNALPOSLIST, externalPOSList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_BankStatement> getFINBankStatementList() {
      return (List<FIN_BankStatement>) get(PROPERTY_FINBANKSTATEMENTLIST);
    }

    public void setFINBankStatementList(List<FIN_BankStatement> fINBankStatementList) {
        set(PROPERTY_FINBANKSTATEMENTLIST, fINBankStatementList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebt> getFINDoubtfulDebtList() {
      return (List<DoubtfulDebt>) get(PROPERTY_FINDOUBTFULDEBTLIST);
    }

    public void setFINDoubtfulDebtList(List<DoubtfulDebt> fINDoubtfulDebtList) {
        set(PROPERTY_FINDOUBTFULDEBTLIST, fINDoubtfulDebtList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_Payment> getFINPaymentList() {
      return (List<FIN_Payment>) get(PROPERTY_FINPAYMENTLIST);
    }

    public void setFINPaymentList(List<FIN_Payment> fINPaymentList) {
        set(PROPERTY_FINPAYMENTLIST, fINPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentProposal> getFINPaymentProposalList() {
      return (List<FIN_PaymentProposal>) get(PROPERTY_FINPAYMENTPROPOSALLIST);
    }

    public void setFINPaymentProposalList(List<FIN_PaymentProposal> fINPaymentProposalList) {
        set(PROPERTY_FINPAYMENTPROPOSALLIST, fINPaymentProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_Reconciliation> getFINReconciliationList() {
      return (List<FIN_Reconciliation>) get(PROPERTY_FINRECONCILIATIONLIST);
    }

    public void setFINReconciliationList(List<FIN_Reconciliation> fINReconciliationList) {
        set(PROPERTY_FINRECONCILIATIONLIST, fINReconciliationList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
      return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<DPManagement> getFinancialMgmtDPManagementList() {
      return (List<DPManagement>) get(PROPERTY_FINANCIALMGMTDPMANAGEMENTLIST);
    }

    public void setFinancialMgmtDPManagementList(List<DPManagement> financialMgmtDPManagementList) {
        set(PROPERTY_FINANCIALMGMTDPMANAGEMENTLIST, financialMgmtDPManagementList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.financialmgmt.gl.GLJournal> getFinancialMgmtGLJournalList() {
      return (List<org.openbravo.model.financialmgmt.gl.GLJournal>) get(PROPERTY_FINANCIALMGMTGLJOURNALLIST);
    }

    public void setFinancialMgmtGLJournalList(List<org.openbravo.model.financialmgmt.gl.GLJournal> financialMgmtGLJournalList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLIST, financialMgmtGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<Settlement> getFinancialMgmtSettlementList() {
      return (List<Settlement>) get(PROPERTY_FINANCIALMGMTSETTLEMENTLIST);
    }

    public void setFinancialMgmtSettlementList(List<Settlement> financialMgmtSettlementList) {
        set(PROPERTY_FINANCIALMGMTSETTLEMENTLIST, financialMgmtSettlementList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRegisterTypeLines> getFinancialMgmtTaxRegisterTypeLinesList() {
      return (List<TaxRegisterTypeLines>) get(PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST);
    }

    public void setFinancialMgmtTaxRegisterTypeLinesList(List<TaxRegisterTypeLines> financialMgmtTaxRegisterTypeLinesList) {
        set(PROPERTY_FINANCIALMGMTTAXREGISTERTYPELINESLIST, financialMgmtTaxRegisterTypeLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<IdtContrato> getIDTContratoList() {
      return (List<IdtContrato>) get(PROPERTY_IDTCONTRATOLIST);
    }

    public void setIDTContratoList(List<IdtContrato> iDTContratoList) {
        set(PROPERTY_IDTCONTRATOLIST, iDTContratoList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.invoice.Invoice> getInvoiceList() {
      return (List<org.openbravo.model.common.invoice.Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<org.openbravo.model.common.invoice.Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.invoice.Invoice> getInvoiceTransactionDocumentList() {
      return (List<org.openbravo.model.common.invoice.Invoice>) get(PROPERTY_INVOICETRANSACTIONDOCUMENTLIST);
    }

    public void setInvoiceTransactionDocumentList(List<org.openbravo.model.common.invoice.Invoice> invoiceTransactionDocumentList) {
        set(PROPERTY_INVOICETRANSACTIONDOCUMENTLIST, invoiceTransactionDocumentList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2List() {
      return (List<InvoiceV2>) get(PROPERTY_INVOICEV2LIST);
    }

    public void setInvoiceV2List(List<InvoiceV2> invoiceV2List) {
        set(PROPERTY_INVOICEV2LIST, invoiceV2List);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2TransactionDocumentList() {
      return (List<InvoiceV2>) get(PROPERTY_INVOICEV2TRANSACTIONDOCUMENTLIST);
    }

    public void setInvoiceV2TransactionDocumentList(List<InvoiceV2> invoiceV2TransactionDocumentList) {
        set(PROPERTY_INVOICEV2TRANSACTIONDOCUMENTLIST, invoiceV2TransactionDocumentList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkRequirement> getManufacturingWorkRequirementList() {
      return (List<WorkRequirement>) get(PROPERTY_MANUFACTURINGWORKREQUIREMENTLIST);
    }

    public void setManufacturingWorkRequirementList(List<WorkRequirement> manufacturingWorkRequirementList) {
        set(PROPERTY_MANUFACTURINGWORKREQUIREMENTLIST, manufacturingWorkRequirementList);
    }

    @SuppressWarnings("unchecked")
    public List<InternalMovement> getMaterialMgmtInternalMovementEMCoDoctypeIDList() {
      return (List<InternalMovement>) get(PROPERTY_MATERIALMGMTINTERNALMOVEMENTEMCODOCTYPEIDLIST);
    }

    public void setMaterialMgmtInternalMovementEMCoDoctypeIDList(List<InternalMovement> materialMgmtInternalMovementEMCoDoctypeIDList) {
        set(PROPERTY_MATERIALMGMTINTERNALMOVEMENTEMCODOCTYPEIDLIST, materialMgmtInternalMovementEMCoDoctypeIDList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutList() {
      return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST);
    }

    public void setMaterialMgmtShipmentInOutList(List<ShipmentInOut> materialMgmtShipmentInOutList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, materialMgmtShipmentInOutList);
    }

    @SuppressWarnings("unchecked")
    public List<noRolPagoProvisionLine> getRolPagoProvisionLineList() {
      return (List<noRolPagoProvisionLine>) get(PROPERTY_ROLPAGOPROVISIONLINELIST);
    }

    public void setRolPagoProvisionLineList(List<noRolPagoProvisionLine> rolPagoProvisionLineList) {
        set(PROPERTY_ROLPAGOPROVISIONLINELIST, rolPagoProvisionLineList);
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
    public List<noRolPagoProvision> getRolPagoProvisionNOCDoctypeIDList() {
      return (List<noRolPagoProvision>) get(PROPERTY_ROLPAGOPROVISIONNOCDOCTYPEIDLIST);
    }

    public void setRolPagoProvisionNOCDoctypeIDList(List<noRolPagoProvision> rolPagoProvisionNOCDoctypeIDList) {
        set(PROPERTY_ROLPAGOPROVISIONNOCDOCTYPEIDLIST, rolPagoProvisionNOCDoctypeIDList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.order.Order> getOrderList() {
      return (List<org.openbravo.model.common.order.Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<org.openbravo.model.common.order.Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.order.Order> getOrderTransactionDocumentList() {
      return (List<org.openbravo.model.common.order.Order>) get(PROPERTY_ORDERTRANSACTIONDOCUMENTLIST);
    }

    public void setOrderTransactionDocumentList(List<org.openbravo.model.common.order.Order> orderTransactionDocumentList) {
        set(PROPERTY_ORDERTRANSACTIONDOCUMENTLIST, orderTransactionDocumentList);
    }

    @SuppressWarnings("unchecked")
    public List<Ats_Sustento_Doc> getAtsSustentoDocList() {
      return (List<Ats_Sustento_Doc>) get(PROPERTY_ATSSUSTENTODOCLIST);
    }

    public void setAtsSustentoDocList(List<Ats_Sustento_Doc> atsSustentoDocList) {
        set(PROPERTY_ATSSUSTENTODOCLIST, atsSustentoDocList);
    }

    @SuppressWarnings("unchecked")
    public List<Co_Sustento_Doc> getSustentoDocList() {
      return (List<Co_Sustento_Doc>) get(PROPERTY_SUSTENTODOCLIST);
    }

    public void setSustentoDocList(List<Co_Sustento_Doc> sustentoDocList) {
        set(PROPERTY_SUSTENTODOCLIST, sustentoDocList);
    }

    @SuppressWarnings("unchecked")
    public List<noContratoEmpleado> getContratoEmpleadoList() {
      return (List<noContratoEmpleado>) get(PROPERTY_CONTRATOEMPLEADOLIST);
    }

    public void setContratoEmpleadoList(List<noContratoEmpleado> contratoEmpleadoList) {
        set(PROPERTY_CONTRATOEMPLEADOLIST, contratoEmpleadoList);
    }

    @SuppressWarnings("unchecked")
    public List<noLiquidacionEmpleado> getLiquidacionEmpleadoList() {
      return (List<noLiquidacionEmpleado>) get(PROPERTY_LIQUIDACIONEMPLEADOLIST);
    }

    public void setLiquidacionEmpleadoList(List<noLiquidacionEmpleado> liquidacionEmpleadoList) {
        set(PROPERTY_LIQUIDACIONEMPLEADOLIST, liquidacionEmpleadoList);
    }

    @SuppressWarnings("unchecked")
    public List<noPagoCabecera> getPagoCabeceraList() {
      return (List<noPagoCabecera>) get(PROPERTY_PAGOCABECERALIST);
    }

    public void setPagoCabeceraList(List<noPagoCabecera> pagoCabeceraList) {
        set(PROPERTY_PAGOCABECERALIST, pagoCabeceraList);
    }

    @SuppressWarnings("unchecked")
    public List<noPagoCabecera> getPagoCabeceraTipoDocumentoPagoList() {
      return (List<noPagoCabecera>) get(PROPERTY_PAGOCABECERATIPODOCUMENTOPAGOLIST);
    }

    public void setPagoCabeceraTipoDocumentoPagoList(List<noPagoCabecera> pagoCabeceraTipoDocumentoPagoList) {
        set(PROPERTY_PAGOCABECERATIPODOCUMENTOPAGOLIST, pagoCabeceraTipoDocumentoPagoList);
    }

    @SuppressWarnings("unchecked")
    public List<noPagoLine> getPagoLineList() {
      return (List<noPagoLine>) get(PROPERTY_PAGOLINELIST);
    }

    public void setPagoLineList(List<noPagoLine> pagoLineList) {
        set(PROPERTY_PAGOLINELIST, pagoLineList);
    }

    @SuppressWarnings("unchecked")
    public List<noRegistraQuincLine> getRegistraQuincLineList() {
      return (List<noRegistraQuincLine>) get(PROPERTY_REGISTRAQUINCLINELIST);
    }

    public void setRegistraQuincLineList(List<noRegistraQuincLine> registraQuincLineList) {
        set(PROPERTY_REGISTRAQUINCLINELIST, registraQuincLineList);
    }

}
