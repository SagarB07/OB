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
/**
 * Entity class for entity CO_Aut_Sri (stored in table co_aut_sri).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CO_Aut_Sri extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "co_aut_sri";
    public static final String ENTITY_NAME = "CO_Aut_Sri";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NROAUTSRI = "nroAutSri";
    public static final String PROPERTY_FECHAAUTSRI = "fechaAutSri";
    public static final String PROPERTY_VENCIMIENTOAUTSRI = "vencimientoAutSri";
    public static final String PROPERTY_INICIOSECUENCIA = "inicioSecuencia";
    public static final String PROPERTY_FINSECUENCIA = "finSecuencia";
    public static final String PROPERTY_COMPAUTORIZACION = "compAutorizacion";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";

    public CO_Aut_Sri() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public String getNroAutSri() {
        return (String) get(PROPERTY_NROAUTSRI);
    }

    public void setNroAutSri(String nroAutSri) {
        set(PROPERTY_NROAUTSRI, nroAutSri);
    }

    public Date getFechaAutSri() {
        return (Date) get(PROPERTY_FECHAAUTSRI);
    }

    public void setFechaAutSri(Date fechaAutSri) {
        set(PROPERTY_FECHAAUTSRI, fechaAutSri);
    }

    public Date getVencimientoAutSri() {
        return (Date) get(PROPERTY_VENCIMIENTOAUTSRI);
    }

    public void setVencimientoAutSri(Date vencimientoAutSri) {
        set(PROPERTY_VENCIMIENTOAUTSRI, vencimientoAutSri);
    }

    public String getInicioSecuencia() {
        return (String) get(PROPERTY_INICIOSECUENCIA);
    }

    public void setInicioSecuencia(String inicioSecuencia) {
        set(PROPERTY_INICIOSECUENCIA, inicioSecuencia);
    }

    public String getFinSecuencia() {
        return (String) get(PROPERTY_FINSECUENCIA);
    }

    public void setFinSecuencia(String finSecuencia) {
        set(PROPERTY_FINSECUENCIA, finSecuencia);
    }

    public String getCompAutorizacion() {
        return (String) get(PROPERTY_COMPAUTORIZACION);
    }

    public void setCompAutorizacion(String compAutorizacion) {
        set(PROPERTY_COMPAUTORIZACION, compAutorizacion);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

}
