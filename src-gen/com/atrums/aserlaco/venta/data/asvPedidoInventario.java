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
package com.atrums.aserlaco.venta.data;

import com.atrums.nomina.data.noAreaEmpresa;

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
import org.openbravo.model.common.enterprise.Warehouse;
/**
 * Entity class for entity asv_pedido_inventario (stored in table asv_pedido_inventario).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class asvPedidoInventario extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "asv_pedido_inventario";
    public static final String ENTITY_NAME = "asv_pedido_inventario";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_FECHA = "fecha";
    public static final String PROPERTY_AREAEMPRESA = "areaEmpresa";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_EMPLEADO = "empleado";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_PROCESAR = "procesar";
    public static final String PROPERTY_ASVPEDIDODETALLELIST = "asvPedidoDetalleList";

    public asvPedidoInventario() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "BR");
        setDefaultValue(PROPERTY_ASVPEDIDODETALLELIST, new ArrayList<Object>());
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

    public Date getFecha() {
        return (Date) get(PROPERTY_FECHA);
    }

    public void setFecha(Date fecha) {
        set(PROPERTY_FECHA, fecha);
    }

    public noAreaEmpresa getAreaEmpresa() {
        return (noAreaEmpresa) get(PROPERTY_AREAEMPRESA);
    }

    public void setAreaEmpresa(noAreaEmpresa areaEmpresa) {
        set(PROPERTY_AREAEMPRESA, areaEmpresa);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public BusinessPartner getEmpleado() {
        return (BusinessPartner) get(PROPERTY_EMPLEADO);
    }

    public void setEmpleado(BusinessPartner empleado) {
        set(PROPERTY_EMPLEADO, empleado);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getProcesar() {
        return (String) get(PROPERTY_PROCESAR);
    }

    public void setProcesar(String procesar) {
        set(PROPERTY_PROCESAR, procesar);
    }

    @SuppressWarnings("unchecked")
    public List<asvPedidoDetalle> getAsvPedidoDetalleList() {
      return (List<asvPedidoDetalle>) get(PROPERTY_ASVPEDIDODETALLELIST);
    }

    public void setAsvPedidoDetalleList(List<asvPedidoDetalle> asvPedidoDetalleList) {
        set(PROPERTY_ASVPEDIDODETALLELIST, asvPedidoDetalleList);
    }

}
