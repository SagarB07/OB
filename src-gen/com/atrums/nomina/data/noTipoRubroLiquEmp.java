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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity no_tipo_rubro_liqu_emp (stored in table no_tipo_rubro_liqu_emp).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noTipoRubroLiquEmp extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_tipo_rubro_liqu_emp";
    public static final String ENTITY_NAME = "no_tipo_rubro_liqu_emp";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_TIPODERUBRO = "tipoDeRubro";
    public static final String PROPERTY_PAGO = "pago";
    public static final String PROPERTY_NOLIQUIDAEMPLEADOLINELIST = "noLiquidaEmpleadoLineList";
    public static final String PROPERTY_NOTIPORUBLIQUEMPACCTLIST = "noTipoRubLiquEmpAcctList";

    public noTipoRubroLiquEmp() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PAGO, true);
        setDefaultValue(PROPERTY_NOLIQUIDAEMPLEADOLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOTIPORUBLIQUEMPACCTLIST, new ArrayList<Object>());
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

    public String getTipoDeRubro() {
        return (String) get(PROPERTY_TIPODERUBRO);
    }

    public void setTipoDeRubro(String tipoDeRubro) {
        set(PROPERTY_TIPODERUBRO, tipoDeRubro);
    }

    public Boolean isPago() {
        return (Boolean) get(PROPERTY_PAGO);
    }

    public void setPago(Boolean pago) {
        set(PROPERTY_PAGO, pago);
    }

    @SuppressWarnings("unchecked")
    public List<noLiquidaEmpleadoLine> getNoLiquidaEmpleadoLineList() {
      return (List<noLiquidaEmpleadoLine>) get(PROPERTY_NOLIQUIDAEMPLEADOLINELIST);
    }

    public void setNoLiquidaEmpleadoLineList(List<noLiquidaEmpleadoLine> noLiquidaEmpleadoLineList) {
        set(PROPERTY_NOLIQUIDAEMPLEADOLINELIST, noLiquidaEmpleadoLineList);
    }

    @SuppressWarnings("unchecked")
    public List<no_tipo_rub_liqu_emp_acct> getNoTipoRubLiquEmpAcctList() {
      return (List<no_tipo_rub_liqu_emp_acct>) get(PROPERTY_NOTIPORUBLIQUEMPACCTLIST);
    }

    public void setNoTipoRubLiquEmpAcctList(List<no_tipo_rub_liqu_emp_acct> noTipoRubLiquEmpAcctList) {
        set(PROPERTY_NOTIPORUBLIQUEMPACCTLIST, noTipoRubLiquEmpAcctList);
    }

}
