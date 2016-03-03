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

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity asv_pedido_detalle (stored in table asv_pedido_detalle).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class asvPedidoDetalle extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "asv_pedido_detalle";
    public static final String ENTITY_NAME = "asv_pedido_detalle";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_ASVPEDIDOINVENTARIO = "aSVPedidoInventario";
    public static final String PROPERTY_INVENTARIOANTERIOR = "inventarioAnterior";
    public static final String PROPERTY_INVENTARIOACTUAL = "inventarioActual";
    public static final String PROPERTY_DADOBAJA = "dadoBaja";
    public static final String PROPERTY_DIFERENCIA = "diferencia";
    public static final String PROPERTY_PEDIDO = "pedido";
    public static final String PROPERTY_ENVIADO = "enviado";
    public static final String PROPERTY_RECIBIDO = "recibido";
    public static final String PROPERTY_RECHAZO = "rechazo";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_CHKENVIADO = "cHKEnviado";
    public static final String PROPERTY_CHKRECIBIDO = "cHKRecibido";
    public static final String PROPERTY_OBSERVACION = "observacion";
    public static final String PROPERTY_CHKRECHAZO = "chkRechazo";

    public asvPedidoDetalle() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DIFERENCIA, new BigDecimal(0));
        setDefaultValue(PROPERTY_ENVIADO, new BigDecimal(0));
        setDefaultValue(PROPERTY_RECIBIDO, new BigDecimal(0));
        setDefaultValue(PROPERTY_RECHAZO, new BigDecimal(0));
        setDefaultValue(PROPERTY_CHKENVIADO, false);
        setDefaultValue(PROPERTY_CHKRECIBIDO, false);
        setDefaultValue(PROPERTY_CHKRECHAZO, false);
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

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public asvPedidoInventario getASVPedidoInventario() {
        return (asvPedidoInventario) get(PROPERTY_ASVPEDIDOINVENTARIO);
    }

    public void setASVPedidoInventario(asvPedidoInventario aSVPedidoInventario) {
        set(PROPERTY_ASVPEDIDOINVENTARIO, aSVPedidoInventario);
    }

    public BigDecimal getInventarioAnterior() {
        return (BigDecimal) get(PROPERTY_INVENTARIOANTERIOR);
    }

    public void setInventarioAnterior(BigDecimal inventarioAnterior) {
        set(PROPERTY_INVENTARIOANTERIOR, inventarioAnterior);
    }

    public BigDecimal getInventarioActual() {
        return (BigDecimal) get(PROPERTY_INVENTARIOACTUAL);
    }

    public void setInventarioActual(BigDecimal inventarioActual) {
        set(PROPERTY_INVENTARIOACTUAL, inventarioActual);
    }

    public BigDecimal getDadoBaja() {
        return (BigDecimal) get(PROPERTY_DADOBAJA);
    }

    public void setDadoBaja(BigDecimal dadoBaja) {
        set(PROPERTY_DADOBAJA, dadoBaja);
    }

    public BigDecimal getDiferencia() {
        return (BigDecimal) get(PROPERTY_DIFERENCIA);
    }

    public void setDiferencia(BigDecimal diferencia) {
        set(PROPERTY_DIFERENCIA, diferencia);
    }

    public BigDecimal getPedido() {
        return (BigDecimal) get(PROPERTY_PEDIDO);
    }

    public void setPedido(BigDecimal pedido) {
        set(PROPERTY_PEDIDO, pedido);
    }

    public BigDecimal getEnviado() {
        return (BigDecimal) get(PROPERTY_ENVIADO);
    }

    public void setEnviado(BigDecimal enviado) {
        set(PROPERTY_ENVIADO, enviado);
    }

    public BigDecimal getRecibido() {
        return (BigDecimal) get(PROPERTY_RECIBIDO);
    }

    public void setRecibido(BigDecimal recibido) {
        set(PROPERTY_RECIBIDO, recibido);
    }

    public BigDecimal getRechazo() {
        return (BigDecimal) get(PROPERTY_RECHAZO);
    }

    public void setRechazo(BigDecimal rechazo) {
        set(PROPERTY_RECHAZO, rechazo);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public Boolean isCHKEnviado() {
        return (Boolean) get(PROPERTY_CHKENVIADO);
    }

    public void setCHKEnviado(Boolean cHKEnviado) {
        set(PROPERTY_CHKENVIADO, cHKEnviado);
    }

    public Boolean isCHKRecibido() {
        return (Boolean) get(PROPERTY_CHKRECIBIDO);
    }

    public void setCHKRecibido(Boolean cHKRecibido) {
        set(PROPERTY_CHKRECIBIDO, cHKRecibido);
    }

    public String getObservacion() {
        return (String) get(PROPERTY_OBSERVACION);
    }

    public void setObservacion(String observacion) {
        set(PROPERTY_OBSERVACION, observacion);
    }

    public Boolean isChkRechazo() {
        return (Boolean) get(PROPERTY_CHKRECHAZO);
    }

    public void setChkRechazo(Boolean chkRechazo) {
        set(PROPERTY_CHKRECHAZO, chkRechazo);
    }

}
