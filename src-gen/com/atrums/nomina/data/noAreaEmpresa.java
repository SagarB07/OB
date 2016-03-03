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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity no_area_empresa (stored in table no_area_empresa).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noAreaEmpresa extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_area_empresa";
    public static final String ENTITY_NAME = "no_area_empresa";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NOMBRE = "nombre";
    public static final String PROPERTY_DESCRIPCIN = "descripcin";
    public static final String PROPERTY_BUSINESSPARTNEREMNOAREAEMPRESAIDLIST = "businessPartnerEmNoAreaEmpresaIdList";
    public static final String PROPERTY_NOROLPAGOPROVISIONLIST = "noRolPagoProvisionList";
    public static final String PROPERTY_NOCONTRATOEMPLEADOEMNEAREAEMPRESAIDLIST = "noContratoEmpleadoEmNeAreaEmpresaIdList";
    public static final String PROPERTY_NOPAGOCABECERALIST = "noPagoCabeceraList";

    public noAreaEmpresa() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BUSINESSPARTNEREMNOAREAEMPRESAIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOROLPAGOPROVISIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOCONTRATOEMPLEADOEMNEAREAEMPRESAIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOPAGOCABECERALIST, new ArrayList<Object>());
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

    public String getNombre() {
        return (String) get(PROPERTY_NOMBRE);
    }

    public void setNombre(String nombre) {
        set(PROPERTY_NOMBRE, nombre);
    }

    public String getDescripcin() {
        return (String) get(PROPERTY_DESCRIPCIN);
    }

    public void setDescripcin(String descripcin) {
        set(PROPERTY_DESCRIPCIN, descripcin);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerEmNoAreaEmpresaIdList() {
      return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNEREMNOAREAEMPRESAIDLIST);
    }

    public void setBusinessPartnerEmNoAreaEmpresaIdList(List<BusinessPartner> businessPartnerEmNoAreaEmpresaIdList) {
        set(PROPERTY_BUSINESSPARTNEREMNOAREAEMPRESAIDLIST, businessPartnerEmNoAreaEmpresaIdList);
    }

    @SuppressWarnings("unchecked")
    public List<noRolPagoProvision> getNoRolPagoProvisionList() {
      return (List<noRolPagoProvision>) get(PROPERTY_NOROLPAGOPROVISIONLIST);
    }

    public void setNoRolPagoProvisionList(List<noRolPagoProvision> noRolPagoProvisionList) {
        set(PROPERTY_NOROLPAGOPROVISIONLIST, noRolPagoProvisionList);
    }

    @SuppressWarnings("unchecked")
    public List<noContratoEmpleado> getNoContratoEmpleadoEmNeAreaEmpresaIdList() {
      return (List<noContratoEmpleado>) get(PROPERTY_NOCONTRATOEMPLEADOEMNEAREAEMPRESAIDLIST);
    }

    public void setNoContratoEmpleadoEmNeAreaEmpresaIdList(List<noContratoEmpleado> noContratoEmpleadoEmNeAreaEmpresaIdList) {
        set(PROPERTY_NOCONTRATOEMPLEADOEMNEAREAEMPRESAIDLIST, noContratoEmpleadoEmNeAreaEmpresaIdList);
    }

    @SuppressWarnings("unchecked")
    public List<noPagoCabecera> getNoPagoCabeceraList() {
      return (List<noPagoCabecera>) get(PROPERTY_NOPAGOCABECERALIST);
    }

    public void setNoPagoCabeceraList(List<noPagoCabecera> noPagoCabeceraList) {
        set(PROPERTY_NOPAGOCABECERALIST, noPagoCabeceraList);
    }

}
