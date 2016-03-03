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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity CO_TipoRetencion (stored in table co_tipo_retencion).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CO_TipoRetencion extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "co_tipo_retencion";
    public static final String ENTITY_NAME = "CO_TipoRetencion";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_TIPO = "tipo";
    public static final String PROPERTY_PORCENTAJE = "porcentaje";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_TIPODERETENCINIVA = "tipoDeRetencinIVA";
    public static final String PROPERTY_CONCEPTORETENCINFUENTE = "conceptoRetencinFuente";
    public static final String PROPERTY_COBPRETENCIONVENTALIST = "cOBPRETENCIONVENTAList";
    public static final String PROPERTY_COBPRETENCIONCOMPRALIST = "cOBpRetencionCompraList";
    public static final String PROPERTY_COTIPORETENCIONACCTLIST = "cOTipoRetencionAcctList";

    public CO_TipoRetencion() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_COBPRETENCIONVENTALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COBPRETENCIONCOMPRALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COTIPORETENCIONACCTLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getTipo() {
        return (String) get(PROPERTY_TIPO);
    }

    public void setTipo(String tipo) {
        set(PROPERTY_TIPO, tipo);
    }

    public BigDecimal getPorcentaje() {
        return (BigDecimal) get(PROPERTY_PORCENTAJE);
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        set(PROPERTY_PORCENTAJE, porcentaje);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public String getTipoDeRetencinIVA() {
        return (String) get(PROPERTY_TIPODERETENCINIVA);
    }

    public void setTipoDeRetencinIVA(String tipoDeRetencinIVA) {
        set(PROPERTY_TIPODERETENCINIVA, tipoDeRetencinIVA);
    }

    public String getConceptoRetencinFuente() {
        return (String) get(PROPERTY_CONCEPTORETENCINFUENTE);
    }

    public void setConceptoRetencinFuente(String conceptoRetencinFuente) {
        set(PROPERTY_CONCEPTORETENCINFUENTE, conceptoRetencinFuente);
    }

    @SuppressWarnings("unchecked")
    public List<BpRetencionVenta> getCOBPRETENCIONVENTAList() {
      return (List<BpRetencionVenta>) get(PROPERTY_COBPRETENCIONVENTALIST);
    }

    public void setCOBPRETENCIONVENTAList(List<BpRetencionVenta> cOBPRETENCIONVENTAList) {
        set(PROPERTY_COBPRETENCIONVENTALIST, cOBPRETENCIONVENTAList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_BpRetencionCompra> getCOBpRetencionCompraList() {
      return (List<CO_BpRetencionCompra>) get(PROPERTY_COBPRETENCIONCOMPRALIST);
    }

    public void setCOBpRetencionCompraList(List<CO_BpRetencionCompra> cOBpRetencionCompraList) {
        set(PROPERTY_COBPRETENCIONCOMPRALIST, cOBpRetencionCompraList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_TipoRetencionAcct> getCOTipoRetencionAcctList() {
      return (List<CO_TipoRetencionAcct>) get(PROPERTY_COTIPORETENCIONACCTLIST);
    }

    public void setCOTipoRetencionAcctList(List<CO_TipoRetencionAcct> cOTipoRetencionAcctList) {
        set(PROPERTY_COTIPORETENCIONACCTLIST, cOTipoRetencionAcctList);
    }

}
