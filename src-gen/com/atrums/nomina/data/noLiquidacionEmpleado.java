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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
/**
 * Entity class for entity no_liquidacion_empleado (stored in table no_liquidacion_empleado).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noLiquidacionEmpleado extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_liquidacion_empleado";
    public static final String ENTITY_NAME = "no_liquidacion_empleado";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLEADO = "empleado";
    public static final String PROPERTY_TIPODELIQUIDACION = "tipoDeLiquidacion";
    public static final String PROPERTY_FECHADEINICIO = "fechaDeInicio";
    public static final String PROPERTY_FECHAFINAL = "fechaFinal";
    public static final String PROPERTY_DOCSTATUS = "docstatus";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_FINFINANCIALACCOUNT = "fINFinancialAccount";
    public static final String PROPERTY_FINPAYMENTMETHOD = "fINPaymentmethod";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_TOTALDEINGRESO = "totalDeIngreso";
    public static final String PROPERTY_TOTALDEEGRESO = "totalDeEgreso";
    public static final String PROPERTY_TOTALNETO = "totalNeto";
    public static final String PROPERTY_DOCACCIONNO = "docaccionno";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_DATEACCT = "dateacct";
    public static final String PROPERTY_GENERARPAGOS = "generarpagos";
    public static final String PROPERTY_NOLIQUIDAEMPLEADOLINELIST = "noLiquidaEmpleadoLineList";

    public noLiquidacionEmpleado() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCSTATUS, "BR");
        setDefaultValue(PROPERTY_PAYMENT, false);
        setDefaultValue(PROPERTY_TOTALDEINGRESO, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALDEEGRESO, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALNETO, new BigDecimal(0));
        setDefaultValue(PROPERTY_DOCACCIONNO, "CO");
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_GENERARPAGOS, false);
        setDefaultValue(PROPERTY_NOLIQUIDAEMPLEADOLINELIST, new ArrayList<Object>());
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

    public BusinessPartner getEmpleado() {
        return (BusinessPartner) get(PROPERTY_EMPLEADO);
    }

    public void setEmpleado(BusinessPartner empleado) {
        set(PROPERTY_EMPLEADO, empleado);
    }

    public String getTipoDeLiquidacion() {
        return (String) get(PROPERTY_TIPODELIQUIDACION);
    }

    public void setTipoDeLiquidacion(String tipoDeLiquidacion) {
        set(PROPERTY_TIPODELIQUIDACION, tipoDeLiquidacion);
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

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public FIN_FinancialAccount getFINFinancialAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_FINFINANCIALACCOUNT);
    }

    public void setFINFinancialAccount(FIN_FinancialAccount fINFinancialAccount) {
        set(PROPERTY_FINFINANCIALACCOUNT, fINFinancialAccount);
    }

    public FIN_PaymentMethod getFINPaymentmethod() {
        return (FIN_PaymentMethod) get(PROPERTY_FINPAYMENTMETHOD);
    }

    public void setFINPaymentmethod(FIN_PaymentMethod fINPaymentmethod) {
        set(PROPERTY_FINPAYMENTMETHOD, fINPaymentmethod);
    }

    public Boolean isPayment() {
        return (Boolean) get(PROPERTY_PAYMENT);
    }

    public void setPayment(Boolean payment) {
        set(PROPERTY_PAYMENT, payment);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public BigDecimal getTotalDeIngreso() {
        return (BigDecimal) get(PROPERTY_TOTALDEINGRESO);
    }

    public void setTotalDeIngreso(BigDecimal totalDeIngreso) {
        set(PROPERTY_TOTALDEINGRESO, totalDeIngreso);
    }

    public BigDecimal getTotalDeEgreso() {
        return (BigDecimal) get(PROPERTY_TOTALDEEGRESO);
    }

    public void setTotalDeEgreso(BigDecimal totalDeEgreso) {
        set(PROPERTY_TOTALDEEGRESO, totalDeEgreso);
    }

    public BigDecimal getTotalNeto() {
        return (BigDecimal) get(PROPERTY_TOTALNETO);
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        set(PROPERTY_TOTALNETO, totalNeto);
    }

    public String getDocaccionno() {
        return (String) get(PROPERTY_DOCACCIONNO);
    }

    public void setDocaccionno(String docaccionno) {
        set(PROPERTY_DOCACCIONNO, docaccionno);
    }

    public String getPosted() {
        return (String) get(PROPERTY_POSTED);
    }

    public void setPosted(String posted) {
        set(PROPERTY_POSTED, posted);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Date getDateacct() {
        return (Date) get(PROPERTY_DATEACCT);
    }

    public void setDateacct(Date dateacct) {
        set(PROPERTY_DATEACCT, dateacct);
    }

    public Boolean isGenerarpagos() {
        return (Boolean) get(PROPERTY_GENERARPAGOS);
    }

    public void setGenerarpagos(Boolean generarpagos) {
        set(PROPERTY_GENERARPAGOS, generarpagos);
    }

    @SuppressWarnings("unchecked")
    public List<noLiquidaEmpleadoLine> getNoLiquidaEmpleadoLineList() {
      return (List<noLiquidaEmpleadoLine>) get(PROPERTY_NOLIQUIDAEMPLEADOLINELIST);
    }

    public void setNoLiquidaEmpleadoLineList(List<noLiquidaEmpleadoLine> noLiquidaEmpleadoLineList) {
        set(PROPERTY_NOLIQUIDAEMPLEADOLINELIST, noLiquidaEmpleadoLineList);
    }

}
