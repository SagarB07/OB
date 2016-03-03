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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.calendar.Year;
/**
 * Entity class for entity no_salario_digno (stored in table no_salario_digno).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noSalarioDigno extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_salario_digno";
    public static final String ENTITY_NAME = "no_salario_digno";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_YEAR = "year";
    public static final String PROPERTY_SALARIODIGNOMENSUAL = "salarioDignoMensual";
    public static final String PROPERTY_DESCRIPCIN = "descripcin";
    public static final String PROPERTY_PROCESAR = "procesar";
    public static final String PROPERTY_DOCSTATUS = "docstatus";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_GENERADATOS = "generaDatos";
    public static final String PROPERTY_GENERACSV = "generaCsv";
    public static final String PROPERTY_NOSALARIODIGNOLINEALIST = "noSalarioDignoLineaList";

    public noSalarioDigno() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SALARIODIGNOMENSUAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESAR, false);
        setDefaultValue(PROPERTY_DOCSTATUS, "DR");
        setDefaultValue(PROPERTY_DOCUMENTACTION, "CO");
        setDefaultValue(PROPERTY_GENERADATOS, false);
        setDefaultValue(PROPERTY_GENERACSV, false);
        setDefaultValue(PROPERTY_NOSALARIODIGNOLINEALIST, new ArrayList<Object>());
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

    public Year getYear() {
        return (Year) get(PROPERTY_YEAR);
    }

    public void setYear(Year year) {
        set(PROPERTY_YEAR, year);
    }

    public BigDecimal getSalarioDignoMensual() {
        return (BigDecimal) get(PROPERTY_SALARIODIGNOMENSUAL);
    }

    public void setSalarioDignoMensual(BigDecimal salarioDignoMensual) {
        set(PROPERTY_SALARIODIGNOMENSUAL, salarioDignoMensual);
    }

    public String getDescripcin() {
        return (String) get(PROPERTY_DESCRIPCIN);
    }

    public void setDescripcin(String descripcin) {
        set(PROPERTY_DESCRIPCIN, descripcin);
    }

    public Boolean isProcesar() {
        return (Boolean) get(PROPERTY_PROCESAR);
    }

    public void setProcesar(Boolean procesar) {
        set(PROPERTY_PROCESAR, procesar);
    }

    public String getDocstatus() {
        return (String) get(PROPERTY_DOCSTATUS);
    }

    public void setDocstatus(String docstatus) {
        set(PROPERTY_DOCSTATUS, docstatus);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public Boolean isGeneraDatos() {
        return (Boolean) get(PROPERTY_GENERADATOS);
    }

    public void setGeneraDatos(Boolean generaDatos) {
        set(PROPERTY_GENERADATOS, generaDatos);
    }

    public Boolean isGeneraCsv() {
        return (Boolean) get(PROPERTY_GENERACSV);
    }

    public void setGeneraCsv(Boolean generaCsv) {
        set(PROPERTY_GENERACSV, generaCsv);
    }

    @SuppressWarnings("unchecked")
    public List<noSalarioDignoLinea> getNoSalarioDignoLineaList() {
      return (List<noSalarioDignoLinea>) get(PROPERTY_NOSALARIODIGNOLINEALIST);
    }

    public void setNoSalarioDignoLineaList(List<noSalarioDignoLinea> noSalarioDignoLineaList) {
        set(PROPERTY_NOSALARIODIGNOLINEALIST, noSalarioDignoLineaList);
    }

}
