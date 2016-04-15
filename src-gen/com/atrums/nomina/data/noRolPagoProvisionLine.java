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
package com.atrums.nomina.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
/**
 * Entity class for entity NO_rol_pago_provision_line (stored in table no_rol_pago_provision_line).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noRolPagoProvisionLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_rol_pago_provision_line";
    public static final String ENTITY_NAME = "NO_rol_pago_provision_line";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ROLPAGOPROVISIONID = "rolPagoProvisionID";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_RUBRO = "rubro";
    public static final String PROPERTY_VALOR = "valor";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_FECHADEINICIO = "fechaDeInicio";
    public static final String PROPERTY_FECHAFINAL = "fechaFinal";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_FINANCIALACCOUNT = "financialAccount";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_DOCSTATUS = "docstatus";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentno";
    public static final String PROPERTY_DOCACCIONNO = "docaccionno";
    public static final String PROPERTY_PROCESARPAGO = "procesarPago";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_NETIPORUBRO = "neTipoRubro";
    public static final String PROPERTY_NEOBSERVACION = "neObservacion";
    public static final String PROPERTY_GENERALLEDGER = "generalLedger";
    public static final String PROPERTY_CUENTADELEGRESO = "cuentaDelEgreso";
    public static final String PROPERTY_CUENTADELINGRESO = "cuentaDelIngreso";
    public static final String PROPERTY_NOROLPROVISIONLINEMESLIST = "noRolProvisionLineMesList";

    public noRolPagoProvisionLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_VALOR, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_DOCSTATUS, "BR");
        setDefaultValue(PROPERTY_DOCACCIONNO, "CO");
        setDefaultValue(PROPERTY_PROCESARPAGO, false);
        setDefaultValue(PROPERTY_PAYMENT, false);
        setDefaultValue(PROPERTY_NOROLPROVISIONLINEMESLIST, new ArrayList<Object>());
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

    public noRolPagoProvision getRolPagoProvisionID() {
        return (noRolPagoProvision) get(PROPERTY_ROLPAGOPROVISIONID);
    }

    public void setRolPagoProvisionID(noRolPagoProvision rolPagoProvisionID) {
        set(PROPERTY_ROLPAGOPROVISIONID, rolPagoProvisionID);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public NoTipoIngresoEgreso getRubro() {
        return (NoTipoIngresoEgreso) get(PROPERTY_RUBRO);
    }

    public void setRubro(NoTipoIngresoEgreso rubro) {
        set(PROPERTY_RUBRO, rubro);
    }

    public BigDecimal getValor() {
        return (BigDecimal) get(PROPERTY_VALOR);
    }

    public void setValor(BigDecimal valor) {
        set(PROPERTY_VALOR, valor);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Date getFechaDeInicio() {
        return (Date) get(PROPERTY_FECHADEINICIO);
    }

    public void setFechaDeInicio(Date fechaDeInicio) {
        set(PROPERTY_FECHADEINICIO, fechaDeInicio);
    }

    public Date getFechaFinal() {
        return (Date) get(PROPERTY_FECHAFINAL);
    }

    public void setFechaFinal(Date fechaFinal) {
        set(PROPERTY_FECHAFINAL, fechaFinal);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public FIN_FinancialAccount getFinancialAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_FINANCIALACCOUNT);
    }

    public void setFinancialAccount(FIN_FinancialAccount financialAccount) {
        set(PROPERTY_FINANCIALACCOUNT, financialAccount);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getDocstatus() {
        return (String) get(PROPERTY_DOCSTATUS);
    }

    public void setDocstatus(String docstatus) {
        set(PROPERTY_DOCSTATUS, docstatus);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentno() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentno(String documentno) {
        set(PROPERTY_DOCUMENTNO, documentno);
    }

    public String getDocaccionno() {
        return (String) get(PROPERTY_DOCACCIONNO);
    }

    public void setDocaccionno(String docaccionno) {
        set(PROPERTY_DOCACCIONNO, docaccionno);
    }

    public Boolean isProcesarPago() {
        return (Boolean) get(PROPERTY_PROCESARPAGO);
    }

    public void setProcesarPago(Boolean procesarPago) {
        set(PROPERTY_PROCESARPAGO, procesarPago);
    }

    public Boolean isPayment() {
        return (Boolean) get(PROPERTY_PAYMENT);
    }

    public void setPayment(Boolean payment) {
        set(PROPERTY_PAYMENT, payment);
    }

    public String getNeTipoRubro() {
        return (String) get(PROPERTY_NETIPORUBRO);
    }

    public void setNeTipoRubro(String neTipoRubro) {
        set(PROPERTY_NETIPORUBRO, neTipoRubro);
    }

    public String getNeObservacion() {
        return (String) get(PROPERTY_NEOBSERVACION);
    }

    public void setNeObservacion(String neObservacion) {
        set(PROPERTY_NEOBSERVACION, neObservacion);
    }

    public AcctSchema getGeneralLedger() {
        return (AcctSchema) get(PROPERTY_GENERALLEDGER);
    }

    public void setGeneralLedger(AcctSchema generalLedger) {
        set(PROPERTY_GENERALLEDGER, generalLedger);
    }

    public AccountingCombination getCuentaDelEgreso() {
        return (AccountingCombination) get(PROPERTY_CUENTADELEGRESO);
    }

    public void setCuentaDelEgreso(AccountingCombination cuentaDelEgreso) {
        set(PROPERTY_CUENTADELEGRESO, cuentaDelEgreso);
    }

    public AccountingCombination getCuentaDelIngreso() {
        return (AccountingCombination) get(PROPERTY_CUENTADELINGRESO);
    }

    public void setCuentaDelIngreso(AccountingCombination cuentaDelIngreso) {
        set(PROPERTY_CUENTADELINGRESO, cuentaDelIngreso);
    }

    @SuppressWarnings("unchecked")
    public List<noRolProvisionLineMes> getNoRolProvisionLineMesList() {
      return (List<noRolProvisionLineMes>) get(PROPERTY_NOROLPROVISIONLINEMESLIST);
    }

    public void setNoRolProvisionLineMesList(List<noRolProvisionLineMes> noRolProvisionLineMesList) {
        set(PROPERTY_NOROLPROVISIONLINEMESLIST, noRolProvisionLineMesList);
    }

}
