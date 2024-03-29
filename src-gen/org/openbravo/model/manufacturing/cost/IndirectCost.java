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
package org.openbravo.model.manufacturing.cost;

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
import org.openbravo.model.manufacturing.processplan.OperationIndirectCost;
import org.openbravo.model.manufacturing.transaction.ProductionRunIndirectCosts;
/**
 * Entity class for entity ManufacturingIndirectCost (stored in table MA_Indirect_Cost).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class IndirectCost extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_Indirect_Cost";
    public static final String ENTITY_NAME = "ManufacturingIndirectCost";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_COSTTYPE = "costType";
    public static final String PROPERTY_MANUFACTURINGCOSTCENTERICLIST = "manufacturingCostcenterICList";
    public static final String PROPERTY_MANUFACTURINGINDIRECTCOSTVALUELIST = "manufacturingIndirectCostValueList";
    public static final String PROPERTY_MANUFACTURINGOPERATIONINDIRECTCOSTLIST = "manufacturingOperationIndirectCostList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNINDIRECTCOSTSLIST = "manufacturingProductionRunIndirectCostsList";

    public IndirectCost() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_MANUFACTURINGCOSTCENTERICLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGINDIRECTCOSTVALUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGOPERATIONINDIRECTCOSTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNINDIRECTCOSTSLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getCostType() {
        return (String) get(PROPERTY_COSTTYPE);
    }

    public void setCostType(String costType) {
        set(PROPERTY_COSTTYPE, costType);
    }

    @SuppressWarnings("unchecked")
    public List<CostcenterIC> getManufacturingCostcenterICList() {
      return (List<CostcenterIC>) get(PROPERTY_MANUFACTURINGCOSTCENTERICLIST);
    }

    public void setManufacturingCostcenterICList(List<CostcenterIC> manufacturingCostcenterICList) {
        set(PROPERTY_MANUFACTURINGCOSTCENTERICLIST, manufacturingCostcenterICList);
    }

    @SuppressWarnings("unchecked")
    public List<IndirectCostValue> getManufacturingIndirectCostValueList() {
      return (List<IndirectCostValue>) get(PROPERTY_MANUFACTURINGINDIRECTCOSTVALUELIST);
    }

    public void setManufacturingIndirectCostValueList(List<IndirectCostValue> manufacturingIndirectCostValueList) {
        set(PROPERTY_MANUFACTURINGINDIRECTCOSTVALUELIST, manufacturingIndirectCostValueList);
    }

    @SuppressWarnings("unchecked")
    public List<OperationIndirectCost> getManufacturingOperationIndirectCostList() {
      return (List<OperationIndirectCost>) get(PROPERTY_MANUFACTURINGOPERATIONINDIRECTCOSTLIST);
    }

    public void setManufacturingOperationIndirectCostList(List<OperationIndirectCost> manufacturingOperationIndirectCostList) {
        set(PROPERTY_MANUFACTURINGOPERATIONINDIRECTCOSTLIST, manufacturingOperationIndirectCostList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunIndirectCosts> getManufacturingProductionRunIndirectCostsList() {
      return (List<ProductionRunIndirectCosts>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNINDIRECTCOSTSLIST);
    }

    public void setManufacturingProductionRunIndirectCostsList(List<ProductionRunIndirectCosts> manufacturingProductionRunIndirectCostsList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNINDIRECTCOSTSLIST, manufacturingProductionRunIndirectCostsList);
    }

}
