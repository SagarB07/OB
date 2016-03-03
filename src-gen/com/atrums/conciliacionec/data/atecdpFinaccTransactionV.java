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
package com.atrums.conciliacionec.data;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_Reconciliation;
/**
 * Entity class for entity atecdp_finacc_transaction_v (stored in table atecdp_finacc_transaction_v).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class atecdpFinaccTransactionV extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "atecdp_finacc_transaction_v";
    public static final String ENTITY_NAME = "atecdp_finacc_transaction_v";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_DEPOSITO = "deposito";
    public static final String PROPERTY_TRANSACTIONDATE = "transactionDate";
    public static final String PROPERTY_VALORDEPOSITADO = "valorDepositado";
    public static final String PROPERTY_VALORPAGADO = "valorPagado";
    public static final String PROPERTY_RECEIPT = "receipt";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_FINANCIALACCOUNT = "financialAccount";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_RECONCILIATION = "reconciliation";
    public static final String PROPERTY_ISPAYMENTRECONCILE = "ispaymentreconcile";

    public atecdpFinaccTransactionV() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_RECEIPT, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_ISPAYMENTRECONCILE, false);
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public String getDeposito() {
        return (String) get(PROPERTY_DEPOSITO);
    }

    public void setDeposito(String deposito) {
        set(PROPERTY_DEPOSITO, deposito);
    }

    public Date getTransactionDate() {
        return (Date) get(PROPERTY_TRANSACTIONDATE);
    }

    public void setTransactionDate(Date transactionDate) {
        set(PROPERTY_TRANSACTIONDATE, transactionDate);
    }

    public BigDecimal getValorDepositado() {
        return (BigDecimal) get(PROPERTY_VALORDEPOSITADO);
    }

    public void setValorDepositado(BigDecimal valorDepositado) {
        set(PROPERTY_VALORDEPOSITADO, valorDepositado);
    }

    public BigDecimal getValorPagado() {
        return (BigDecimal) get(PROPERTY_VALORPAGADO);
    }

    public void setValorPagado(BigDecimal valorPagado) {
        set(PROPERTY_VALORPAGADO, valorPagado);
    }

    public Boolean isReceipt() {
        return (Boolean) get(PROPERTY_RECEIPT);
    }

    public void setReceipt(Boolean receipt) {
        set(PROPERTY_RECEIPT, receipt);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public FIN_FinancialAccount getFinancialAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_FINANCIALACCOUNT);
    }

    public void setFinancialAccount(FIN_FinancialAccount financialAccount) {
        set(PROPERTY_FINANCIALACCOUNT, financialAccount);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public FIN_Reconciliation getReconciliation() {
        return (FIN_Reconciliation) get(PROPERTY_RECONCILIATION);
    }

    public void setReconciliation(FIN_Reconciliation reconciliation) {
        set(PROPERTY_RECONCILIATION, reconciliation);
    }

    public Boolean isPaymentreconcile() {
        return (Boolean) get(PROPERTY_ISPAYMENTRECONCILE);
    }

    public void setPaymentreconcile(Boolean ispaymentreconcile) {
        set(PROPERTY_ISPAYMENTRECONCILE, ispaymentreconcile);
    }

}
