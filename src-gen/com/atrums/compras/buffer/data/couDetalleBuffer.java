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
package com.atrums.compras.buffer.data;

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
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity cou_detalle_buffer (stored in table cou_detalle_buffer).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class couDetalleBuffer extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "cou_detalle_buffer";
    public static final String ENTITY_NAME = "cou_detalle_buffer";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_COUPARAMBUFFER = "cOUParamBuffer";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_MES1 = "mes1";
    public static final String PROPERTY_MES2 = "mes2";
    public static final String PROPERTY_MES3 = "mes3";
    public static final String PROPERTY_MES4 = "mes4";
    public static final String PROPERTY_MES5 = "mes5";
    public static final String PROPERTY_MES6 = "mes6";
    public static final String PROPERTY_CONSUMOHISTRICO = "consumoHistrico";
    public static final String PROPERTY_STOCK = "stock";
    public static final String PROPERTY_NOMBREPRODUCTO = "nombreProducto";

    public couDetalleBuffer() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MES1, new BigDecimal(0));
        setDefaultValue(PROPERTY_MES2, new BigDecimal(0));
        setDefaultValue(PROPERTY_MES3, new BigDecimal(0));
        setDefaultValue(PROPERTY_MES4, new BigDecimal(0));
        setDefaultValue(PROPERTY_MES5, new BigDecimal(0));
        setDefaultValue(PROPERTY_MES6, new BigDecimal(0));
        setDefaultValue(PROPERTY_CONSUMOHISTRICO, new BigDecimal(0));
        setDefaultValue(PROPERTY_STOCK, (long) 0);
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

    public couParamBuffer getCOUParamBuffer() {
        return (couParamBuffer) get(PROPERTY_COUPARAMBUFFER);
    }

    public void setCOUParamBuffer(couParamBuffer cOUParamBuffer) {
        set(PROPERTY_COUPARAMBUFFER, cOUParamBuffer);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BigDecimal getMes1() {
        return (BigDecimal) get(PROPERTY_MES1);
    }

    public void setMes1(BigDecimal mes1) {
        set(PROPERTY_MES1, mes1);
    }

    public BigDecimal getMes2() {
        return (BigDecimal) get(PROPERTY_MES2);
    }

    public void setMes2(BigDecimal mes2) {
        set(PROPERTY_MES2, mes2);
    }

    public BigDecimal getMes3() {
        return (BigDecimal) get(PROPERTY_MES3);
    }

    public void setMes3(BigDecimal mes3) {
        set(PROPERTY_MES3, mes3);
    }

    public BigDecimal getMes4() {
        return (BigDecimal) get(PROPERTY_MES4);
    }

    public void setMes4(BigDecimal mes4) {
        set(PROPERTY_MES4, mes4);
    }

    public BigDecimal getMes5() {
        return (BigDecimal) get(PROPERTY_MES5);
    }

    public void setMes5(BigDecimal mes5) {
        set(PROPERTY_MES5, mes5);
    }

    public BigDecimal getMes6() {
        return (BigDecimal) get(PROPERTY_MES6);
    }

    public void setMes6(BigDecimal mes6) {
        set(PROPERTY_MES6, mes6);
    }

    public BigDecimal getConsumoHistrico() {
        return (BigDecimal) get(PROPERTY_CONSUMOHISTRICO);
    }

    public void setConsumoHistrico(BigDecimal consumoHistrico) {
        set(PROPERTY_CONSUMOHISTRICO, consumoHistrico);
    }

    public Long getStock() {
        return (Long) get(PROPERTY_STOCK);
    }

    public void setStock(Long stock) {
        set(PROPERTY_STOCK, stock);
    }

    public String getNombreProducto() {
        return (String) get(PROPERTY_NOMBREPRODUCTO);
    }

    public void setNombreProducto(String nombreProducto) {
        set(PROPERTY_NOMBREPRODUCTO, nombreProducto);
    }

}
