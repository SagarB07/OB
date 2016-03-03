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
 * Entity class for entity CO_RETENCION_VENTA_LINEA (stored in table CO_RETENCION_VENTA_LINEA).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Co_Retencion_Venta_Linea extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "CO_RETENCION_VENTA_LINEA";
    public static final String ENTITY_NAME = "CO_RETENCION_VENTA_LINEA";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RETENCIONVENTA = "retencionVenta";
    public static final String PROPERTY_LINE = "line";
    public static final String PROPERTY_TIPODERETENCIN = "tipoDeRetencin";
    public static final String PROPERTY_NODECHEQUE = "noDeCheque";
    public static final String PROPERTY_BASEIMPONIBLEDERETENCIN = "baseImponibleDeRetencin";
    public static final String PROPERTY_RETENCINDEVENTA = "retencinDeVenta";
    public static final String PROPERTY_VALORDELARETENCIN = "valorDeLaRetencin";
    public static final String PROPERTY_ACTIVE = "active";

    public Co_Retencion_Venta_Linea() {
        setDefaultValue(PROPERTY_VALORDELARETENCIN, new BigDecimal(0));
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

    public CO_RETENCION_VENTA getRetencionVenta() {
        return (CO_RETENCION_VENTA) get(PROPERTY_RETENCIONVENTA);
    }

    public void setRetencionVenta(CO_RETENCION_VENTA retencionVenta) {
        set(PROPERTY_RETENCIONVENTA, retencionVenta);
    }

    public Long getLine() {
        return (Long) get(PROPERTY_LINE);
    }

    public void setLine(Long line) {
        set(PROPERTY_LINE, line);
    }

    public String getTipoDeRetencin() {
        return (String) get(PROPERTY_TIPODERETENCIN);
    }

    public void setTipoDeRetencin(String tipoDeRetencin) {
        set(PROPERTY_TIPODERETENCIN, tipoDeRetencin);
    }

    public String getNoDeCheque() {
        return (String) get(PROPERTY_NODECHEQUE);
    }

    public void setNoDeCheque(String noDeCheque) {
        set(PROPERTY_NODECHEQUE, noDeCheque);
    }

    public BigDecimal getBaseImponibleDeRetencin() {
        return (BigDecimal) get(PROPERTY_BASEIMPONIBLEDERETENCIN);
    }

    public void setBaseImponibleDeRetencin(BigDecimal baseImponibleDeRetencin) {
        set(PROPERTY_BASEIMPONIBLEDERETENCIN, baseImponibleDeRetencin);
    }

    public BpRetencionVenta getRetencinDeVenta() {
        return (BpRetencionVenta) get(PROPERTY_RETENCINDEVENTA);
    }

    public void setRetencinDeVenta(BpRetencionVenta retencinDeVenta) {
        set(PROPERTY_RETENCINDEVENTA, retencinDeVenta);
    }

    public BigDecimal getValorDeLaRetencin() {
        return (BigDecimal) get(PROPERTY_VALORDELARETENCIN);
    }

    public void setValorDeLaRetencin(BigDecimal valorDeLaRetencin) {
        set(PROPERTY_VALORDELARETENCIN, valorDeLaRetencin);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

}
