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
import org.openbravo.model.financialmgmt.calendar.Period;
/**
 * Entity class for entity no_prestamo (stored in table no_prestamo).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noPrestamo extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_prestamo";
    public static final String ENTITY_NAME = "no_prestamo";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLEADO = "empleado";
    public static final String PROPERTY_RUBRO = "rubro";
    public static final String PROPERTY_VALORPRESTAMO = "valorPrestamo";
    public static final String PROPERTY_MESESCUOTA = "mesesCuota";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_VALORCUOTA = "valorCuota";
    public static final String PROPERTY_PROCESAR = "procesar";
    public static final String PROPERTY_DOCACTIONPRO = "docactionPro";
    public static final String PROPERTY_DESCUENTOIESS = "descuentoIess";
    public static final String PROPERTY_SUELDO = "sueldo";
    public static final String PROPERTY_TOTALARECIBIR = "totalARecibir";

    public noPrestamo() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESAR, false);
        setDefaultValue(PROPERTY_DOCACTIONPRO, "PR");
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

    public BusinessPartner getEmpleado() {
        return (BusinessPartner) get(PROPERTY_EMPLEADO);
    }

    public void setEmpleado(BusinessPartner empleado) {
        set(PROPERTY_EMPLEADO, empleado);
    }

    public NoTipoIngresoEgreso getRubro() {
        return (NoTipoIngresoEgreso) get(PROPERTY_RUBRO);
    }

    public void setRubro(NoTipoIngresoEgreso rubro) {
        set(PROPERTY_RUBRO, rubro);
    }

    public BigDecimal getValorPrestamo() {
        return (BigDecimal) get(PROPERTY_VALORPRESTAMO);
    }

    public void setValorPrestamo(BigDecimal valorPrestamo) {
        set(PROPERTY_VALORPRESTAMO, valorPrestamo);
    }

    public Long getMesesCuota() {
        return (Long) get(PROPERTY_MESESCUOTA);
    }

    public void setMesesCuota(Long mesesCuota) {
        set(PROPERTY_MESESCUOTA, mesesCuota);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public BigDecimal getValorCuota() {
        return (BigDecimal) get(PROPERTY_VALORCUOTA);
    }

    public void setValorCuota(BigDecimal valorCuota) {
        set(PROPERTY_VALORCUOTA, valorCuota);
    }

    public Boolean isProcesar() {
        return (Boolean) get(PROPERTY_PROCESAR);
    }

    public void setProcesar(Boolean procesar) {
        set(PROPERTY_PROCESAR, procesar);
    }

    public String getDocactionPro() {
        return (String) get(PROPERTY_DOCACTIONPRO);
    }

    public void setDocactionPro(String docactionPro) {
        set(PROPERTY_DOCACTIONPRO, docactionPro);
    }

    public BigDecimal getDescuentoIess() {
        return (BigDecimal) get(PROPERTY_DESCUENTOIESS);
    }

    public void setDescuentoIess(BigDecimal descuentoIess) {
        set(PROPERTY_DESCUENTOIESS, descuentoIess);
    }

    public BigDecimal getSueldo() {
        return (BigDecimal) get(PROPERTY_SUELDO);
    }

    public void setSueldo(BigDecimal sueldo) {
        set(PROPERTY_SUELDO, sueldo);
    }

    public BigDecimal getTotalARecibir() {
        return (BigDecimal) get(PROPERTY_TOTALARECIBIR);
    }

    public void setTotalARecibir(BigDecimal totalARecibir) {
        set(PROPERTY_TOTALARECIBIR, totalARecibir);
    }

}
