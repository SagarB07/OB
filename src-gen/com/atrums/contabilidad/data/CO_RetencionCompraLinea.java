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
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity CO_RetencionCompraLinea (stored in table co_retencion_compra_linea).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CO_RetencionCompraLinea extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "co_retencion_compra_linea";
    public static final String ENTITY_NAME = "CO_RetencionCompraLinea";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RETENCIONCOMPRA = "retencionCompra";
    public static final String PROPERTY_LINE = "line";
    public static final String PROPERTY_TIPO = "tipo";
    public static final String PROPERTY_CHEQUE = "cheque";
    public static final String PROPERTY_BASEIMPRETENCION = "baseImpRetencion";
    public static final String PROPERTY_BPRETENCIONCOMPRA = "bpRetencionCompra";
    public static final String PROPERTY_VALORRETENCION = "valorRetencion";
    public static final String PROPERTY_ACTIVE = "active";

    public CO_RetencionCompraLinea() {
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

    public CO_Retencion_Compra getRetencionCompra() {
        return (CO_Retencion_Compra) get(PROPERTY_RETENCIONCOMPRA);
    }

    public void setRetencionCompra(CO_Retencion_Compra retencionCompra) {
        set(PROPERTY_RETENCIONCOMPRA, retencionCompra);
    }

    public Long getLine() {
        return (Long) get(PROPERTY_LINE);
    }

    public void setLine(Long line) {
        set(PROPERTY_LINE, line);
    }

    public String getTipo() {
        return (String) get(PROPERTY_TIPO);
    }

    public void setTipo(String tipo) {
        set(PROPERTY_TIPO, tipo);
    }

    public String getCheque() {
        return (String) get(PROPERTY_CHEQUE);
    }

    public void setCheque(String cheque) {
        set(PROPERTY_CHEQUE, cheque);
    }

    public BigDecimal getBaseImpRetencion() {
        return (BigDecimal) get(PROPERTY_BASEIMPRETENCION);
    }

    public void setBaseImpRetencion(BigDecimal baseImpRetencion) {
        set(PROPERTY_BASEIMPRETENCION, baseImpRetencion);
    }

    public CO_BpRetencionCompra getBpRetencionCompra() {
        return (CO_BpRetencionCompra) get(PROPERTY_BPRETENCIONCOMPRA);
    }

    public void setBpRetencionCompra(CO_BpRetencionCompra bpRetencionCompra) {
        set(PROPERTY_BPRETENCIONCOMPRA, bpRetencionCompra);
    }

    public BigDecimal getValorRetencion() {
        return (BigDecimal) get(PROPERTY_VALORRETENCION);
    }

    public void setValorRetencion(BigDecimal valorRetencion) {
        set(PROPERTY_VALORRETENCION, valorRetencion);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

}
