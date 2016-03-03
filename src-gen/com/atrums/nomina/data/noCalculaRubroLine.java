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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity no_calcula_rubro_line (stored in table no_calcula_rubro_line).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noCalculaRubroLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_calcula_rubro_line";
    public static final String ENTITY_NAME = "no_calcula_rubro_line";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_IDCALCULARUBRO = "iDCalculaRubro";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_SALARIO = "salario";
    public static final String PROPERTY_PORCENTAJE = "porcentaje";
    public static final String PROPERTY_VALOR = "valor";
    public static final String PROPERTY_PROCEDURE = "procedure";
    public static final String PROPERTY_CALCULARCONPROCEDURE = "calcularConProcedure";

    public noCalculaRubroLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SALARIO, true);
        setDefaultValue(PROPERTY_PORCENTAJE, true);
        setDefaultValue(PROPERTY_VALOR, new BigDecimal(0));
        setDefaultValue(PROPERTY_CALCULARCONPROCEDURE, false);
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

    public noCalculaRubro getIDCalculaRubro() {
        return (noCalculaRubro) get(PROPERTY_IDCALCULARUBRO);
    }

    public void setIDCalculaRubro(noCalculaRubro iDCalculaRubro) {
        set(PROPERTY_IDCALCULARUBRO, iDCalculaRubro);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Boolean isSalario() {
        return (Boolean) get(PROPERTY_SALARIO);
    }

    public void setSalario(Boolean salario) {
        set(PROPERTY_SALARIO, salario);
    }

    public Boolean isPorcentaje() {
        return (Boolean) get(PROPERTY_PORCENTAJE);
    }

    public void setPorcentaje(Boolean porcentaje) {
        set(PROPERTY_PORCENTAJE, porcentaje);
    }

    public BigDecimal getValor() {
        return (BigDecimal) get(PROPERTY_VALOR);
    }

    public void setValor(BigDecimal valor) {
        set(PROPERTY_VALOR, valor);
    }

    public String getProcedure() {
        return (String) get(PROPERTY_PROCEDURE);
    }

    public void setProcedure(String procedure) {
        set(PROPERTY_PROCEDURE, procedure);
    }

    public Boolean isCalcularConProcedure() {
        return (Boolean) get(PROPERTY_CALCULARCONPROCEDURE);
    }

    public void setCalcularConProcedure(Boolean calcularConProcedure) {
        set(PROPERTY_CALCULARCONPROCEDURE, calcularConProcedure);
    }

}
