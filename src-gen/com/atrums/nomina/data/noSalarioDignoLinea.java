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
/**
 * Entity class for entity no_salario_digno_linea (stored in table no_salario_digno_linea).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noSalarioDignoLinea extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_salario_digno_linea";
    public static final String ENTITY_NAME = "no_salario_digno_linea";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SALARIODIGNO = "salarioDigno";
    public static final String PROPERTY_EMPLEADO = "empleado";
    public static final String PROPERTY_SUELDOS = "sueldos";
    public static final String PROPERTY_DECIMOCUARTO = "decimocuarto";
    public static final String PROPERTY_COMISIONES = "comisiones";
    public static final String PROPERTY_UTILIDADES = "utilidades";
    public static final String PROPERTY_ADICIONALES = "adicionales";
    public static final String PROPERTY_FONDORESERVA = "fondoReserva";
    public static final String PROPERTY_DECIMOTERCERO = "decimotercero";
    public static final String PROPERTY_SUMINGRESOS = "sUMIngresos";
    public static final String PROPERTY_DIASLABORADOS = "diasLaborados";
    public static final String PROPERTY_JORNADAPARCIAL = "jornadaParcial";
    public static final String PROPERTY_HORASPARCIALES = "horasParciales";
    public static final String PROPERTY_SALARIODIGNOANUAL = "salarioDignoAnual";
    public static final String PROPERTY_TOTAL = "total";

    public noSalarioDignoLinea() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUELDOS, new BigDecimal(0));
        setDefaultValue(PROPERTY_DECIMOCUARTO, new BigDecimal(0));
        setDefaultValue(PROPERTY_COMISIONES, new BigDecimal(0));
        setDefaultValue(PROPERTY_UTILIDADES, new BigDecimal(0));
        setDefaultValue(PROPERTY_ADICIONALES, new BigDecimal(0));
        setDefaultValue(PROPERTY_FONDORESERVA, new BigDecimal(0));
        setDefaultValue(PROPERTY_DECIMOTERCERO, new BigDecimal(0));
        setDefaultValue(PROPERTY_SUMINGRESOS, new BigDecimal(0));
        setDefaultValue(PROPERTY_DIASLABORADOS, (long) 0);
        setDefaultValue(PROPERTY_HORASPARCIALES, (long) 0);
        setDefaultValue(PROPERTY_TOTAL, new BigDecimal(0));
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

    public noSalarioDigno getSalarioDigno() {
        return (noSalarioDigno) get(PROPERTY_SALARIODIGNO);
    }

    public void setSalarioDigno(noSalarioDigno salarioDigno) {
        set(PROPERTY_SALARIODIGNO, salarioDigno);
    }

    public BusinessPartner getEmpleado() {
        return (BusinessPartner) get(PROPERTY_EMPLEADO);
    }

    public void setEmpleado(BusinessPartner empleado) {
        set(PROPERTY_EMPLEADO, empleado);
    }

    public BigDecimal getSueldos() {
        return (BigDecimal) get(PROPERTY_SUELDOS);
    }

    public void setSueldos(BigDecimal sueldos) {
        set(PROPERTY_SUELDOS, sueldos);
    }

    public BigDecimal getDecimocuarto() {
        return (BigDecimal) get(PROPERTY_DECIMOCUARTO);
    }

    public void setDecimocuarto(BigDecimal decimocuarto) {
        set(PROPERTY_DECIMOCUARTO, decimocuarto);
    }

    public BigDecimal getComisiones() {
        return (BigDecimal) get(PROPERTY_COMISIONES);
    }

    public void setComisiones(BigDecimal comisiones) {
        set(PROPERTY_COMISIONES, comisiones);
    }

    public BigDecimal getUtilidades() {
        return (BigDecimal) get(PROPERTY_UTILIDADES);
    }

    public void setUtilidades(BigDecimal utilidades) {
        set(PROPERTY_UTILIDADES, utilidades);
    }

    public BigDecimal getAdicionales() {
        return (BigDecimal) get(PROPERTY_ADICIONALES);
    }

    public void setAdicionales(BigDecimal adicionales) {
        set(PROPERTY_ADICIONALES, adicionales);
    }

    public BigDecimal getFondoReserva() {
        return (BigDecimal) get(PROPERTY_FONDORESERVA);
    }

    public void setFondoReserva(BigDecimal fondoReserva) {
        set(PROPERTY_FONDORESERVA, fondoReserva);
    }

    public BigDecimal getDecimotercero() {
        return (BigDecimal) get(PROPERTY_DECIMOTERCERO);
    }

    public void setDecimotercero(BigDecimal decimotercero) {
        set(PROPERTY_DECIMOTERCERO, decimotercero);
    }

    public BigDecimal getSUMIngresos() {
        return (BigDecimal) get(PROPERTY_SUMINGRESOS);
    }

    public void setSUMIngresos(BigDecimal sUMIngresos) {
        set(PROPERTY_SUMINGRESOS, sUMIngresos);
    }

    public Long getDiasLaborados() {
        return (Long) get(PROPERTY_DIASLABORADOS);
    }

    public void setDiasLaborados(Long diasLaborados) {
        set(PROPERTY_DIASLABORADOS, diasLaborados);
    }

    public Long getJornadaParcial() {
        return (Long) get(PROPERTY_JORNADAPARCIAL);
    }

    public void setJornadaParcial(Long jornadaParcial) {
        set(PROPERTY_JORNADAPARCIAL, jornadaParcial);
    }

    public Long getHorasParciales() {
        return (Long) get(PROPERTY_HORASPARCIALES);
    }

    public void setHorasParciales(Long horasParciales) {
        set(PROPERTY_HORASPARCIALES, horasParciales);
    }

    public BigDecimal getSalarioDignoAnual() {
        return (BigDecimal) get(PROPERTY_SALARIODIGNOANUAL);
    }

    public void setSalarioDignoAnual(BigDecimal salarioDignoAnual) {
        set(PROPERTY_SALARIODIGNOANUAL, salarioDignoAnual);
    }

    public BigDecimal getTotal() {
        return (BigDecimal) get(PROPERTY_TOTAL);
    }

    public void setTotal(BigDecimal total) {
        set(PROPERTY_TOTAL, total);
    }

}
