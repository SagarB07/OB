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
package com.atrums.centrocostos.data;

import com.atrums.nomina.data.noRolPagoProvision;
import com.atrums.nomina.data.noRolProvisionLineMes;

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
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.payment.FIN_Payment;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
/**
 * Entity class for entity cco_registra_costos (stored in table cco_registra_costos).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class cco_registra_costos extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "cco_registra_costos";
    public static final String ENTITY_NAME = "cco_registra_costos";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CUENTACENTROCOSTOS = "cuentaCentroCostos";
    public static final String PROPERTY_INVOICE = "invoice";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_VALOR = "valor";
    public static final String PROPERTY_PORCENTAJE = "porcentaje";
    public static final String PROPERTY_NOMBRE = "nombre";
    public static final String PROPERTY_DETALLE = "detalle";
    public static final String PROPERTY_ROLPAGOPROVISION = "rolPagoProvision";
    public static final String PROPERTY_PAYMENT = "payment";
    public static final String PROPERTY_GOODSSHIPMENT = "goodsShipment";
    public static final String PROPERTY_ROLPROVISIONLINEMES = "rolProvisionLineMes";
    public static final String PROPERTY_IMPESFACTURAIMP = "impEsfacturaimp";

    public cco_registra_costos() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_VALOR, new BigDecimal(0));
        setDefaultValue(PROPERTY_IMPESFACTURAIMP, false);
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

    public cco_cuenta_costos getCuentaCentroCostos() {
        return (cco_cuenta_costos) get(PROPERTY_CUENTACENTROCOSTOS);
    }

    public void setCuentaCentroCostos(cco_cuenta_costos cuentaCentroCostos) {
        set(PROPERTY_CUENTACENTROCOSTOS, cuentaCentroCostos);
    }

    public Invoice getInvoice() {
        return (Invoice) get(PROPERTY_INVOICE);
    }

    public void setInvoice(Invoice invoice) {
        set(PROPERTY_INVOICE, invoice);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public BigDecimal getValor() {
        return (BigDecimal) get(PROPERTY_VALOR);
    }

    public void setValor(BigDecimal valor) {
        set(PROPERTY_VALOR, valor);
    }

    public BigDecimal getPorcentaje() {
        return (BigDecimal) get(PROPERTY_PORCENTAJE);
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        set(PROPERTY_PORCENTAJE, porcentaje);
    }

    public String getNombre() {
        return (String) get(PROPERTY_NOMBRE);
    }

    public void setNombre(String nombre) {
        set(PROPERTY_NOMBRE, nombre);
    }

    public String getDetalle() {
        return (String) get(PROPERTY_DETALLE);
    }

    public void setDetalle(String detalle) {
        set(PROPERTY_DETALLE, detalle);
    }

    public noRolPagoProvision getRolPagoProvision() {
        return (noRolPagoProvision) get(PROPERTY_ROLPAGOPROVISION);
    }

    public void setRolPagoProvision(noRolPagoProvision rolPagoProvision) {
        set(PROPERTY_ROLPAGOPROVISION, rolPagoProvision);
    }

    public FIN_Payment getPayment() {
        return (FIN_Payment) get(PROPERTY_PAYMENT);
    }

    public void setPayment(FIN_Payment payment) {
        set(PROPERTY_PAYMENT, payment);
    }

    public ShipmentInOut getGoodsShipment() {
        return (ShipmentInOut) get(PROPERTY_GOODSSHIPMENT);
    }

    public void setGoodsShipment(ShipmentInOut goodsShipment) {
        set(PROPERTY_GOODSSHIPMENT, goodsShipment);
    }

    public noRolProvisionLineMes getRolProvisionLineMes() {
        return (noRolProvisionLineMes) get(PROPERTY_ROLPROVISIONLINEMES);
    }

    public void setRolProvisionLineMes(noRolProvisionLineMes rolProvisionLineMes) {
        set(PROPERTY_ROLPROVISIONLINEMES, rolProvisionLineMes);
    }

    public Boolean isImpEsfacturaimp() {
        return (Boolean) get(PROPERTY_IMPESFACTURAIMP);
    }

    public void setImpEsfacturaimp(Boolean impEsfacturaimp) {
        set(PROPERTY_IMPESFACTURAIMP, impEsfacturaimp);
    }

}
