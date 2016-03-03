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
package com.atrums.contabilidad.data;

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
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.financialmgmt.calendar.Period;
/**
 * Entity class for entity CO_RETENCION_VENTA (stored in table CO_RETENCION_VENTA).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CO_RETENCION_VENTA extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "CO_RETENCION_VENTA";
    public static final String ENTITY_NAME = "CO_RETENCION_VENTA";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_INVOICE = "invoice";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_NOAUTORIZACIN = "noAutorizacin";
    public static final String PROPERTY_FECHADEEMISIN = "fechaDeEmisin";
    public static final String PROPERTY_TIPODECOMPROBANTEDEVENTA = "tipoDeComprobanteDeVenta";
    public static final String PROPERTY_NOCOMPROBANTEDEVENTA = "noComprobanteDeVenta";
    public static final String PROPERTY_PERIODO = "periodo";
    public static final String PROPERTY_TOTAL = "total";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_NOESTABLECIMIENTO = "noEstablecimiento";
    public static final String PROPERTY_PUNTOEMISIN = "puntoEmisin";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_DOCACTIONRE = "docactionre";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_TRANSACTIONDOCUMENT = "transactionDocument";
    public static final String PROPERTY_CORETENCIONVENTALINEALIST = "cORETENCIONVENTALINEAList";

    public CO_RETENCION_VENTA() {
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "BR");
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_DOCACTIONRE, "CO");
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_CORETENCIONVENTALINEALIST, new ArrayList<Object>());
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

    public Invoice getInvoice() {
        return (Invoice) get(PROPERTY_INVOICE);
    }

    public void setInvoice(Invoice invoice) {
        set(PROPERTY_INVOICE, invoice);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public String getNoAutorizacin() {
        return (String) get(PROPERTY_NOAUTORIZACIN);
    }

    public void setNoAutorizacin(String noAutorizacin) {
        set(PROPERTY_NOAUTORIZACIN, noAutorizacin);
    }

    public Date getFechaDeEmisin() {
        return (Date) get(PROPERTY_FECHADEEMISIN);
    }

    public void setFechaDeEmisin(Date fechaDeEmisin) {
        set(PROPERTY_FECHADEEMISIN, fechaDeEmisin);
    }

    public String getTipoDeComprobanteDeVenta() {
        return (String) get(PROPERTY_TIPODECOMPROBANTEDEVENTA);
    }

    public void setTipoDeComprobanteDeVenta(String tipoDeComprobanteDeVenta) {
        set(PROPERTY_TIPODECOMPROBANTEDEVENTA, tipoDeComprobanteDeVenta);
    }

    public String getNoComprobanteDeVenta() {
        return (String) get(PROPERTY_NOCOMPROBANTEDEVENTA);
    }

    public void setNoComprobanteDeVenta(String noComprobanteDeVenta) {
        set(PROPERTY_NOCOMPROBANTEDEVENTA, noComprobanteDeVenta);
    }

    public Period getPeriodo() {
        return (Period) get(PROPERTY_PERIODO);
    }

    public void setPeriodo(Period periodo) {
        set(PROPERTY_PERIODO, periodo);
    }

    public BigDecimal getTotal() {
        return (BigDecimal) get(PROPERTY_TOTAL);
    }

    public void setTotal(BigDecimal total) {
        set(PROPERTY_TOTAL, total);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public String getNoEstablecimiento() {
        return (String) get(PROPERTY_NOESTABLECIMIENTO);
    }

    public void setNoEstablecimiento(String noEstablecimiento) {
        set(PROPERTY_NOESTABLECIMIENTO, noEstablecimiento);
    }

    public String getPuntoEmisin() {
        return (String) get(PROPERTY_PUNTOEMISIN);
    }

    public void setPuntoEmisin(String puntoEmisin) {
        set(PROPERTY_PUNTOEMISIN, puntoEmisin);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public String getPosted() {
        return (String) get(PROPERTY_POSTED);
    }

    public void setPosted(String posted) {
        set(PROPERTY_POSTED, posted);
    }

    public String getDocactionre() {
        return (String) get(PROPERTY_DOCACTIONRE);
    }

    public void setDocactionre(String docactionre) {
        set(PROPERTY_DOCACTIONRE, docactionre);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public DocumentType getTransactionDocument() {
        return (DocumentType) get(PROPERTY_TRANSACTIONDOCUMENT);
    }

    public void setTransactionDocument(DocumentType transactionDocument) {
        set(PROPERTY_TRANSACTIONDOCUMENT, transactionDocument);
    }

    @SuppressWarnings("unchecked")
    public List<Co_Retencion_Venta_Linea> getCORETENCIONVENTALINEAList() {
      return (List<Co_Retencion_Venta_Linea>) get(PROPERTY_CORETENCIONVENTALINEALIST);
    }

    public void setCORETENCIONVENTALINEAList(List<Co_Retencion_Venta_Linea> cORETENCIONVENTALINEAList) {
        set(PROPERTY_CORETENCIONVENTALINEALIST, cORETENCIONVENTALINEAList);
    }

}
