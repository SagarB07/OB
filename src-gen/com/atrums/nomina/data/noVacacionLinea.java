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
 * Entity class for entity no_vacacion_linea (stored in table no_vacacion_linea).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noVacacionLinea extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_vacacion_linea";
    public static final String ENTITY_NAME = "no_vacacion_linea";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_IDVACACION = "iDVacacion";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_FECHADEINICIO = "fechaDeInicio";
    public static final String PROPERTY_FECHAFINAL = "fechaFinal";
    public static final String PROPERTY_ESTADO = "estado";
    public static final String PROPERTY_PROCESADO = "procesado";
    public static final String PROPERTY_PROCESAR = "procesar";
    public static final String PROPERTY_DASDEPERMISO = "dasDePermiso";

    public noVacacionLinea() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ESTADO, "BO");
        setDefaultValue(PROPERTY_PROCESADO, false);
        setDefaultValue(PROPERTY_PROCESAR, false);
        setDefaultValue(PROPERTY_DASDEPERMISO, (long) 0);
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

    public noVacacion getIDVacacion() {
        return (noVacacion) get(PROPERTY_IDVACACION);
    }

    public void setIDVacacion(noVacacion iDVacacion) {
        set(PROPERTY_IDVACACION, iDVacacion);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Date getFechaDeInicio() {
        return (Date) get(PROPERTY_FECHADEINICIO);
    }

    public void setFechaDeInicio(Date fechaDeInicio) {
        set(PROPERTY_FECHADEINICIO, fechaDeInicio);
    }

    public Date getFechaFinal() {
        return (Date) get(PROPERTY_FECHAFINAL);
    }

    public void setFechaFinal(Date fechaFinal) {
        set(PROPERTY_FECHAFINAL, fechaFinal);
    }

    public String getEstado() {
        return (String) get(PROPERTY_ESTADO);
    }

    public void setEstado(String estado) {
        set(PROPERTY_ESTADO, estado);
    }

    public Boolean isProcesado() {
        return (Boolean) get(PROPERTY_PROCESADO);
    }

    public void setProcesado(Boolean procesado) {
        set(PROPERTY_PROCESADO, procesado);
    }

    public Boolean isProcesar() {
        return (Boolean) get(PROPERTY_PROCESAR);
    }

    public void setProcesar(Boolean procesar) {
        set(PROPERTY_PROCESAR, procesar);
    }

    public Long getDasDePermiso() {
        return (Long) get(PROPERTY_DASDEPERMISO);
    }

    public void setDasDePermiso(Long dasDePermiso) {
        set(PROPERTY_DASDEPERMISO, dasDePermiso);
    }

}
