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

import com.atrums.centrocostos.data.cco_registra_costos;

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
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.calendar.Period;
/**
 * Entity class for entity No_Rol_Pago_Provision (stored in table no_rol_pago_provision).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noRolPagoProvision extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_rol_pago_provision";
    public static final String ENTITY_NAME = "No_Rol_Pago_Provision";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentno";
    public static final String PROPERTY_TOTALDEINGRESO = "totalDeIngreso";
    public static final String PROPERTY_TOTALDEEGRESO = "totalDeEgreso";
    public static final String PROPERTY_TOTALNETO = "totalNeto";
    public static final String PROPERTY_DOCSTATUS = "docstatus";
    public static final String PROPERTY_ISPAGO = "ispago";
    public static final String PROPERTY_PROCESAR = "procesar";
    public static final String PROPERTY_GENERARPAGO = "generarPago";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_REAEMPRESA = "reaEmpresa";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_DOCACCIONNO = "docaccionno";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_DATEACCT = "dateacct";
    public static final String PROPERTY_CDOCTYPE = "cDoctype";
    public static final String PROPERTY_ENVIOMAIL = "enviomail";
    public static final String PROPERTY_NENUMCONTRATO = "neNumContrato";
    public static final String PROPERTY_NEOBSERVACION = "neObservacion";
    public static final String PROPERTY_NOROLPAGOPROVISIONLINELIST = "nORolPagoProvisionLineList";
    public static final String PROPERTY_CCOREGISTRACOSTOSLIST = "ccoRegistraCostosList";

    public noRolPagoProvision() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TOTALDEINGRESO, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALDEEGRESO, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALNETO, new BigDecimal(0));
        setDefaultValue(PROPERTY_DOCSTATUS, "BR");
        setDefaultValue(PROPERTY_PROCESAR, false);
        setDefaultValue(PROPERTY_GENERARPAGO, false);
        setDefaultValue(PROPERTY_PAYMENT, false);
        setDefaultValue(PROPERTY_DOCACCIONNO, "CO");
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_ENVIOMAIL, false);
        setDefaultValue(PROPERTY_NOROLPAGOPROVISIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CCOREGISTRACOSTOSLIST, new ArrayList<Object>());
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

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
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

    public String getDocstatus() {
        return (String) get(PROPERTY_DOCSTATUS);
    }

    public void setDocstatus(String docstatus) {
        set(PROPERTY_DOCSTATUS, docstatus);
    }

    public Boolean isPago() {
        return (Boolean) get(PROPERTY_ISPAGO);
    }

    public void setPago(Boolean ispago) {
        set(PROPERTY_ISPAGO, ispago);
    }

    public Boolean isProcesar() {
        return (Boolean) get(PROPERTY_PROCESAR);
    }

    public void setProcesar(Boolean procesar) {
        set(PROPERTY_PROCESAR, procesar);
    }

    public Boolean isGenerarPago() {
        return (Boolean) get(PROPERTY_GENERARPAGO);
    }

    public void setGenerarPago(Boolean generarPago) {
        set(PROPERTY_GENERARPAGO, generarPago);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public noAreaEmpresa getReaEmpresa() {
        return (noAreaEmpresa) get(PROPERTY_REAEMPRESA);
    }

    public void setReaEmpresa(noAreaEmpresa reaEmpresa) {
        set(PROPERTY_REAEMPRESA, reaEmpresa);
    }

    public Boolean isPayment() {
        return (Boolean) get(PROPERTY_PAYMENT);
    }

    public void setPayment(Boolean payment) {
        set(PROPERTY_PAYMENT, payment);
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

    public DocumentType getCDoctype() {
        return (DocumentType) get(PROPERTY_CDOCTYPE);
    }

    public void setCDoctype(DocumentType cDoctype) {
        set(PROPERTY_CDOCTYPE, cDoctype);
    }

    public Boolean isEnviomail() {
        return (Boolean) get(PROPERTY_ENVIOMAIL);
    }

    public void setEnviomail(Boolean enviomail) {
        set(PROPERTY_ENVIOMAIL, enviomail);
    }

    public String getNeNumContrato() {
        return (String) get(PROPERTY_NENUMCONTRATO);
    }

    public void setNeNumContrato(String neNumContrato) {
        set(PROPERTY_NENUMCONTRATO, neNumContrato);
    }

    public String getNeObservacion() {
        return (String) get(PROPERTY_NEOBSERVACION);
    }

    public void setNeObservacion(String neObservacion) {
        set(PROPERTY_NEOBSERVACION, neObservacion);
    }

    @SuppressWarnings("unchecked")
    public List<noRolPagoProvisionLine> getNORolPagoProvisionLineList() {
      return (List<noRolPagoProvisionLine>) get(PROPERTY_NOROLPAGOPROVISIONLINELIST);
    }

    public void setNORolPagoProvisionLineList(List<noRolPagoProvisionLine> nORolPagoProvisionLineList) {
        set(PROPERTY_NOROLPAGOPROVISIONLINELIST, nORolPagoProvisionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<cco_registra_costos> getCcoRegistraCostosList() {
      return (List<cco_registra_costos>) get(PROPERTY_CCOREGISTRACOSTOSLIST);
    }

    public void setCcoRegistraCostosList(List<cco_registra_costos> ccoRegistraCostosList) {
        set(PROPERTY_CCOREGISTRACOSTOSLIST, ccoRegistraCostosList);
    }

}
