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
package com.atrums.nomina.rrhh.data;

import com.atrums.nomina.data.noContratoEmpleado;

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
 * Entity class for entity atnorh_cargo (stored in table atnorh_cargo).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class AtnorhCargo extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "atnorh_cargo";
    public static final String ENTITY_NAME = "atnorh_cargo";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_INSTRUCCIONFORMAL = "instruccionFormal";
    public static final String PROPERTY_FORMACION = "formacion";
    public static final String PROPERTY_EXPERIENCIA = "experiencia";
    public static final String PROPERTY_HABILIDAD = "habilidad";
    public static final String PROPERTY_REQ = "req";
    public static final String PROPERTY_ISPERITO = "isperito";
    public static final String PROPERTY_NOCONTRATOEMPLEADOEMATNORHCARGOIDLIST = "noContratoEmpleadoEMAtnorhCargoIDList";

    public AtnorhCargo() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISPERITO, true);
        setDefaultValue(PROPERTY_NOCONTRATOEMPLEADOEMATNORHCARGOIDLIST, new ArrayList<Object>());
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

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
    }

    public String getInstruccionFormal() {
        return (String) get(PROPERTY_INSTRUCCIONFORMAL);
    }

    public void setInstruccionFormal(String instruccionFormal) {
        set(PROPERTY_INSTRUCCIONFORMAL, instruccionFormal);
    }

    public String getFormacion() {
        return (String) get(PROPERTY_FORMACION);
    }

    public void setFormacion(String formacion) {
        set(PROPERTY_FORMACION, formacion);
    }

    public String getExperiencia() {
        return (String) get(PROPERTY_EXPERIENCIA);
    }

    public void setExperiencia(String experiencia) {
        set(PROPERTY_EXPERIENCIA, experiencia);
    }

    public String getHabilidad() {
        return (String) get(PROPERTY_HABILIDAD);
    }

    public void setHabilidad(String habilidad) {
        set(PROPERTY_HABILIDAD, habilidad);
    }

    public String getReq() {
        return (String) get(PROPERTY_REQ);
    }

    public void setReq(String req) {
        set(PROPERTY_REQ, req);
    }

    public Boolean isPerito() {
        return (Boolean) get(PROPERTY_ISPERITO);
    }

    public void setPerito(Boolean isperito) {
        set(PROPERTY_ISPERITO, isperito);
    }

    @SuppressWarnings("unchecked")
    public List<noContratoEmpleado> getNoContratoEmpleadoEMAtnorhCargoIDList() {
      return (List<noContratoEmpleado>) get(PROPERTY_NOCONTRATOEMPLEADOEMATNORHCARGOIDLIST);
    }

    public void setNoContratoEmpleadoEMAtnorhCargoIDList(List<noContratoEmpleado> noContratoEmpleadoEMAtnorhCargoIDList) {
        set(PROPERTY_NOCONTRATOEMPLEADOEMATNORHCARGOIDLIST, noContratoEmpleadoEMAtnorhCargoIDList);
    }

}