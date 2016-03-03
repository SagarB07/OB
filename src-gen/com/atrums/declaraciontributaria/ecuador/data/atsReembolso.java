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
package com.atrums.declaraciontributaria.ecuador.data;

import java.math.BigDecimal;
import java.util.Date;

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
/**
 * Entity class for entity ATS_REEMBOLSO (stored in table ats_reembolso).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class atsReembolso extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "ats_reembolso";
    public static final String ENTITY_NAME = "ATS_REEMBOLSO";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_TIPOIDENTIFICACION = "tipoIdentificacion";
    public static final String PROPERTY_IDENTIFICACION = "identificacion";
    public static final String PROPERTY_ESTABLECIMIENTO = "establecimiento";
    public static final String PROPERTY_EMISION = "emision";
    public static final String PROPERTY_SECUENCIAL = "secuencial";
    public static final String PROPERTY_FECHAEMISION = "fechaEmision";
    public static final String PROPERTY_AUTORIZACIONSRI = "autorizacionSri";
    public static final String PROPERTY_BASE0 = "base0";
    public static final String PROPERTY_BASE12 = "base12";
    public static final String PROPERTY_BASEEXENTO = "baseExento";
    public static final String PROPERTY_VALORICE = "valorIce";
    public static final String PROPERTY_VALORIVA = "valorIva";
    public static final String PROPERTY_INVOICE = "invoice";

    public atsReembolso() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BASE0, new BigDecimal(0));
        setDefaultValue(PROPERTY_BASE12, new BigDecimal(0));
        setDefaultValue(PROPERTY_BASEEXENTO, new BigDecimal(0));
        setDefaultValue(PROPERTY_VALORICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_VALORIVA, new BigDecimal(0));
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

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getTipoIdentificacion() {
        return (String) get(PROPERTY_TIPOIDENTIFICACION);
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        set(PROPERTY_TIPOIDENTIFICACION, tipoIdentificacion);
    }

    public String getIdentificacion() {
        return (String) get(PROPERTY_IDENTIFICACION);
    }

    public void setIdentificacion(String identificacion) {
        set(PROPERTY_IDENTIFICACION, identificacion);
    }

    public String getEstablecimiento() {
        return (String) get(PROPERTY_ESTABLECIMIENTO);
    }

    public void setEstablecimiento(String establecimiento) {
        set(PROPERTY_ESTABLECIMIENTO, establecimiento);
    }

    public String getEmision() {
        return (String) get(PROPERTY_EMISION);
    }

    public void setEmision(String emision) {
        set(PROPERTY_EMISION, emision);
    }

    public String getSecuencial() {
        return (String) get(PROPERTY_SECUENCIAL);
    }

    public void setSecuencial(String secuencial) {
        set(PROPERTY_SECUENCIAL, secuencial);
    }

    public Date getFechaEmision() {
        return (Date) get(PROPERTY_FECHAEMISION);
    }

    public void setFechaEmision(Date fechaEmision) {
        set(PROPERTY_FECHAEMISION, fechaEmision);
    }

    public String getAutorizacionSri() {
        return (String) get(PROPERTY_AUTORIZACIONSRI);
    }

    public void setAutorizacionSri(String autorizacionSri) {
        set(PROPERTY_AUTORIZACIONSRI, autorizacionSri);
    }

    public BigDecimal getBase0() {
        return (BigDecimal) get(PROPERTY_BASE0);
    }

    public void setBase0(BigDecimal base0) {
        set(PROPERTY_BASE0, base0);
    }

    public BigDecimal getBase12() {
        return (BigDecimal) get(PROPERTY_BASE12);
    }

    public void setBase12(BigDecimal base12) {
        set(PROPERTY_BASE12, base12);
    }

    public BigDecimal getBaseExento() {
        return (BigDecimal) get(PROPERTY_BASEEXENTO);
    }

    public void setBaseExento(BigDecimal baseExento) {
        set(PROPERTY_BASEEXENTO, baseExento);
    }

    public BigDecimal getValorIce() {
        return (BigDecimal) get(PROPERTY_VALORICE);
    }

    public void setValorIce(BigDecimal valorIce) {
        set(PROPERTY_VALORICE, valorIce);
    }

    public BigDecimal getValorIva() {
        return (BigDecimal) get(PROPERTY_VALORIVA);
    }

    public void setValorIva(BigDecimal valorIva) {
        set(PROPERTY_VALORIVA, valorIva);
    }

    public Invoice getInvoice() {
        return (Invoice) get(PROPERTY_INVOICE);
    }

    public void setInvoice(Invoice invoice) {
        set(PROPERTY_INVOICE, invoice);
    }

}
