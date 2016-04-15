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
import java.util.Date;

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
 * Entity class for entity No_Permiso (stored in table no_permiso).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class NoPermiso extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_permiso";
    public static final String ENTITY_NAME = "No_Permiso";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_TIPODEPERMISO = "tipoDePermiso";
    public static final String PROPERTY_DASDEPERMISO = "dasDePermiso";
    public static final String PROPERTY_HORASDEPERMISO = "horasDePermiso";
    public static final String PROPERTY_FECHADELPERMISO = "fechaDelPermiso";
    public static final String PROPERTY_ESTADO = "estado";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PROCESADO = "procesado";
    public static final String PROPERTY_MOTIVOPERMISO = "motivoPermiso";
    public static final String PROPERTY_NEESTADO = "neEstado";
    public static final String PROPERTY_NEOBSERVACION = "neObservacion";
    public static final String PROPERTY_NEPROCESAR = "neProcesar";
    public static final String PROPERTY_IDCONTRATOEMPLEADO = "iDContratoEmpleado";

    public NoPermiso() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ESTADO, "BR");
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PROCESADO, false);
        setDefaultValue(PROPERTY_NEESTADO, "BR");
        setDefaultValue(PROPERTY_NEPROCESAR, "PA");
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

    public String getTipoDePermiso() {
        return (String) get(PROPERTY_TIPODEPERMISO);
    }

    public void setTipoDePermiso(String tipoDePermiso) {
        set(PROPERTY_TIPODEPERMISO, tipoDePermiso);
    }

    public BigDecimal getDasDePermiso() {
        return (BigDecimal) get(PROPERTY_DASDEPERMISO);
    }

    public void setDasDePermiso(BigDecimal dasDePermiso) {
        set(PROPERTY_DASDEPERMISO, dasDePermiso);
    }

    public BigDecimal getHorasDePermiso() {
        return (BigDecimal) get(PROPERTY_HORASDEPERMISO);
    }

    public void setHorasDePermiso(BigDecimal horasDePermiso) {
        set(PROPERTY_HORASDEPERMISO, horasDePermiso);
    }

    public Date getFechaDelPermiso() {
        return (Date) get(PROPERTY_FECHADELPERMISO);
    }

    public void setFechaDelPermiso(Date fechaDelPermiso) {
        set(PROPERTY_FECHADELPERMISO, fechaDelPermiso);
    }

    public String getEstado() {
        return (String) get(PROPERTY_ESTADO);
    }

    public void setEstado(String estado) {
        set(PROPERTY_ESTADO, estado);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isProcesado() {
        return (Boolean) get(PROPERTY_PROCESADO);
    }

    public void setProcesado(Boolean procesado) {
        set(PROPERTY_PROCESADO, procesado);
    }

    public String getMotivoPermiso() {
        return (String) get(PROPERTY_MOTIVOPERMISO);
    }

    public void setMotivoPermiso(String motivoPermiso) {
        set(PROPERTY_MOTIVOPERMISO, motivoPermiso);
    }

    public String getNeEstado() {
        return (String) get(PROPERTY_NEESTADO);
    }

    public void setNeEstado(String neEstado) {
        set(PROPERTY_NEESTADO, neEstado);
    }

    public String getNeObservacion() {
        return (String) get(PROPERTY_NEOBSERVACION);
    }

    public void setNeObservacion(String neObservacion) {
        set(PROPERTY_NEOBSERVACION, neObservacion);
    }

    public String getNeProcesar() {
        return (String) get(PROPERTY_NEPROCESAR);
    }

    public void setNeProcesar(String neProcesar) {
        set(PROPERTY_NEPROCESAR, neProcesar);
    }

    public noContratoEmpleado getIDContratoEmpleado() {
        return (noContratoEmpleado) get(PROPERTY_IDCONTRATOEMPLEADO);
    }

    public void setIDContratoEmpleado(noContratoEmpleado iDContratoEmpleado) {
        set(PROPERTY_IDCONTRATOEMPLEADO, iDContratoEmpleado);
    }

}
