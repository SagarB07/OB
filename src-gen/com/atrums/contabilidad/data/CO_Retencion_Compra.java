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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.financialmgmt.calendar.Period;
/**
 * Entity class for entity CO_Retencion_Compra (stored in table co_retencion_compra).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CO_Retencion_Compra extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "co_retencion_compra";
    public static final String ENTITY_NAME = "CO_Retencion_Compra";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_INVOICE = "invoice";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_NOAUTORIZACIN = "noAutorizacin";
    public static final String PROPERTY_BPARTNER = "bpartner";
    public static final String PROPERTY_RUC = "rUC";
    public static final String PROPERTY_BPARTNERLOCATION = "bpartnerLocation";
    public static final String PROPERTY_FECHAEMISIN = "fechaEmisin";
    public static final String PROPERTY_TIPOCOMPROBANTEVENTA = "tipoComprobanteVenta";
    public static final String PROPERTY_NOCOMPROBANTEVENTA = "noComprobanteVenta";
    public static final String PROPERTY_TOTALRETENCIN = "totalRetencin";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_MES = "mes";
    public static final String PROPERTY_DOCSTATUS = "docstatus";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_DOCACTIONRE = "docactionre";
    public static final String PROPERTY_ATECFEESTADOSRI = "aTECFEEstadoSRI";
    public static final String PROPERTY_TRANSACTIONDOCUMENT = "transactionDocument";
    public static final String PROPERTY_ATECFEDOCACTION = "atecfeDocaction";
    public static final String PROPERTY_ATECFEMENSAJEDELSRI = "aTECFEMensajeDelSRI";
    public static final String PROPERTY_ATECFECDIGODEACCESO = "aTECFECdigoDeAcceso";
    public static final String PROPERTY_ATECFEDOCUMENTOXML = "atecfeDocumentoXml";
    public static final String PROPERTY_ATECFEFECHAAUTORI = "atecfeFechaAutori";
    public static final String PROPERTY_CORETENCIONCOMPRALINEALIST = "cORetencionCompraLineaList";

    public CO_Retencion_Compra() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_DOCSTATUS, "BR");
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_DOCACTIONRE, "CO");
        setDefaultValue(PROPERTY_ATECFEESTADOSRI, "PD");
        setDefaultValue(PROPERTY_ATECFEDOCACTION, "PR");
        setDefaultValue(PROPERTY_CORETENCIONCOMPRALINEALIST, new ArrayList<Object>());
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

    public BusinessPartner getBpartner() {
        return (BusinessPartner) get(PROPERTY_BPARTNER);
    }

    public void setBpartner(BusinessPartner bpartner) {
        set(PROPERTY_BPARTNER, bpartner);
    }

    public String getRUC() {
        return (String) get(PROPERTY_RUC);
    }

    public void setRUC(String rUC) {
        set(PROPERTY_RUC, rUC);
    }

    public Location getBpartnerLocation() {
        return (Location) get(PROPERTY_BPARTNERLOCATION);
    }

    public void setBpartnerLocation(Location bpartnerLocation) {
        set(PROPERTY_BPARTNERLOCATION, bpartnerLocation);
    }

    public Date getFechaEmisin() {
        return (Date) get(PROPERTY_FECHAEMISIN);
    }

    public void setFechaEmisin(Date fechaEmisin) {
        set(PROPERTY_FECHAEMISIN, fechaEmisin);
    }

    public String getTipoComprobanteVenta() {
        return (String) get(PROPERTY_TIPOCOMPROBANTEVENTA);
    }

    public void setTipoComprobanteVenta(String tipoComprobanteVenta) {
        set(PROPERTY_TIPOCOMPROBANTEVENTA, tipoComprobanteVenta);
    }

    public String getNoComprobanteVenta() {
        return (String) get(PROPERTY_NOCOMPROBANTEVENTA);
    }

    public void setNoComprobanteVenta(String noComprobanteVenta) {
        set(PROPERTY_NOCOMPROBANTEVENTA, noComprobanteVenta);
    }

    public BigDecimal getTotalRetencin() {
        return (BigDecimal) get(PROPERTY_TOTALRETENCIN);
    }

    public void setTotalRetencin(BigDecimal totalRetencin) {
        set(PROPERTY_TOTALRETENCIN, totalRetencin);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Period getMes() {
        return (Period) get(PROPERTY_MES);
    }

    public void setMes(Period mes) {
        set(PROPERTY_MES, mes);
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

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
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

    public String getATECFEEstadoSRI() {
        return (String) get(PROPERTY_ATECFEESTADOSRI);
    }

    public void setATECFEEstadoSRI(String aTECFEEstadoSRI) {
        set(PROPERTY_ATECFEESTADOSRI, aTECFEEstadoSRI);
    }

    public DocumentType getTransactionDocument() {
        return (DocumentType) get(PROPERTY_TRANSACTIONDOCUMENT);
    }

    public void setTransactionDocument(DocumentType transactionDocument) {
        set(PROPERTY_TRANSACTIONDOCUMENT, transactionDocument);
    }

    public String getAtecfeDocaction() {
        return (String) get(PROPERTY_ATECFEDOCACTION);
    }

    public void setAtecfeDocaction(String atecfeDocaction) {
        set(PROPERTY_ATECFEDOCACTION, atecfeDocaction);
    }

    public String getATECFEMensajeDelSRI() {
        return (String) get(PROPERTY_ATECFEMENSAJEDELSRI);
    }

    public void setATECFEMensajeDelSRI(String aTECFEMensajeDelSRI) {
        set(PROPERTY_ATECFEMENSAJEDELSRI, aTECFEMensajeDelSRI);
    }

    public String getATECFECdigoDeAcceso() {
        return (String) get(PROPERTY_ATECFECDIGODEACCESO);
    }

    public void setATECFECdigoDeAcceso(String aTECFECdigoDeAcceso) {
        set(PROPERTY_ATECFECDIGODEACCESO, aTECFECdigoDeAcceso);
    }

    public byte[] getAtecfeDocumentoXml() {
        return (byte[]) get(PROPERTY_ATECFEDOCUMENTOXML);
    }

    public void setAtecfeDocumentoXml(byte[] atecfeDocumentoXml) {
        set(PROPERTY_ATECFEDOCUMENTOXML, atecfeDocumentoXml);
    }

    public String getAtecfeFechaAutori() {
        return (String) get(PROPERTY_ATECFEFECHAAUTORI);
    }

    public void setAtecfeFechaAutori(String atecfeFechaAutori) {
        set(PROPERTY_ATECFEFECHAAUTORI, atecfeFechaAutori);
    }

    @SuppressWarnings("unchecked")
    public List<CO_RetencionCompraLinea> getCORetencionCompraLineaList() {
      return (List<CO_RetencionCompraLinea>) get(PROPERTY_CORETENCIONCOMPRALINEALIST);
    }

    public void setCORetencionCompraLineaList(List<CO_RetencionCompraLinea> cORetencionCompraLineaList) {
        set(PROPERTY_CORETENCIONCOMPRALINEALIST, cORetencionCompraLineaList);
    }

}
