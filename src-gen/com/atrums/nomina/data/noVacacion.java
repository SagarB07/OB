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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity no_vacacion (stored in table no_vacacion).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noVacacion extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_vacacion";
    public static final String ENTITY_NAME = "no_vacacion";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_AO = "ao";
    public static final String PROPERTY_BONIFICACIONES = "bonificaciones";
    public static final String PROPERTY_NEESTADO = "neEstado";
    public static final String PROPERTY_NEFECHAFIN = "neFechaFin";
    public static final String PROPERTY_NEFECHAINICIO = "neFechaInicio";
    public static final String PROPERTY_NELINEA = "neLinea";
    public static final String PROPERTY_NETOTALDIAS = "neTotalDias";
    public static final String PROPERTY_IDCONTRATOEMPLEADO = "iDContratoEmpleado";
    public static final String PROPERTY_SALDO = "saldo";
    public static final String PROPERTY_VACACIONES = "vacaciones";
    public static final String PROPERTY__COMPUTEDCOLUMNS = "_computedColumns";
    public static final String PROPERTY_NOVACACIONLINEALIST = "noVacacionLineaList";


    // Computed columns properties, these properties cannot be directly accessed, they need
    // to be read through _commputedColumns proxy. They cannot be directly used in HQL, OBQuery
    // nor OBCriteria. 
    public static final String COMPUTED_COLUMN_DRESTANTES = "drestantes";

    public noVacacion() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BONIFICACIONES, (long) 0);
        setDefaultValue(PROPERTY_VACACIONES, (long) 15);
        setDefaultValue(PROPERTY_NOVACACIONLINEALIST, new ArrayList<Object>());
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

    public String getAo() {
        return (String) get(PROPERTY_AO);
    }

    public void setAo(String ao) {
        set(PROPERTY_AO, ao);
    }

    public Long getBonificaciones() {
        return (Long) get(PROPERTY_BONIFICACIONES);
    }

    public void setBonificaciones(Long bonificaciones) {
        set(PROPERTY_BONIFICACIONES, bonificaciones);
    }

    public Long getDrestantes() {
        return (Long) get(COMPUTED_COLUMN_DRESTANTES);
    }

    public void setDrestantes(Long drestantes) {
        set(COMPUTED_COLUMN_DRESTANTES, drestantes);
    }

    public String getNeEstado() {
        return (String) get(PROPERTY_NEESTADO);
    }

    public void setNeEstado(String neEstado) {
        set(PROPERTY_NEESTADO, neEstado);
    }

    public Date getNeFechaFin() {
        return (Date) get(PROPERTY_NEFECHAFIN);
    }

    public void setNeFechaFin(Date neFechaFin) {
        set(PROPERTY_NEFECHAFIN, neFechaFin);
    }

    public Date getNeFechaInicio() {
        return (Date) get(PROPERTY_NEFECHAINICIO);
    }

    public void setNeFechaInicio(Date neFechaInicio) {
        set(PROPERTY_NEFECHAINICIO, neFechaInicio);
    }

    public BigDecimal getNeLinea() {
        return (BigDecimal) get(PROPERTY_NELINEA);
    }

    public void setNeLinea(BigDecimal neLinea) {
        set(PROPERTY_NELINEA, neLinea);
    }

    public BigDecimal getNeTotalDias() {
        return (BigDecimal) get(PROPERTY_NETOTALDIAS);
    }

    public void setNeTotalDias(BigDecimal neTotalDias) {
        set(PROPERTY_NETOTALDIAS, neTotalDias);
    }

    public noContratoEmpleado getIDContratoEmpleado() {
        return (noContratoEmpleado) get(PROPERTY_IDCONTRATOEMPLEADO);
    }

    public void setIDContratoEmpleado(noContratoEmpleado iDContratoEmpleado) {
        set(PROPERTY_IDCONTRATOEMPLEADO, iDContratoEmpleado);
    }

    public Long getSaldo() {
        return (Long) get(PROPERTY_SALDO);
    }

    public void setSaldo(Long saldo) {
        set(PROPERTY_SALDO, saldo);
    }

    public Long getVacaciones() {
        return (Long) get(PROPERTY_VACACIONES);
    }

    public void setVacaciones(Long vacaciones) {
        set(PROPERTY_VACACIONES, vacaciones);
    }

    public noVacacion_ComputedColumns get_computedColumns() {
        return (noVacacion_ComputedColumns) get(PROPERTY__COMPUTEDCOLUMNS);
    }

    public void set_computedColumns(noVacacion_ComputedColumns _computedColumns) {
        set(PROPERTY__COMPUTEDCOLUMNS, _computedColumns);
    }

    @SuppressWarnings("unchecked")
    public List<noVacacionLinea> getNoVacacionLineaList() {
      return (List<noVacacionLinea>) get(PROPERTY_NOVACACIONLINEALIST);
    }

    public void setNoVacacionLineaList(List<noVacacionLinea> noVacacionLineaList) {
        set(PROPERTY_NOVACACIONLINEALIST, noVacacionLineaList);
    }


    @Override
    public Object get(String propName) {
      if (COMPUTED_COLUMN_DRESTANTES.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getDrestantes();
      }
    
      return super.get(propName);
    }
}
