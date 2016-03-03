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
package com.atrums.importaciones;

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
 * Entity class for entity imp_reporte_facturas_v (stored in table imp_reporte_facturas_v).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class imReporteFacturasV extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "imp_reporte_facturas_v";
    public static final String ENTITY_NAME = "imp_reporte_facturas_v";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CODIMPORTACION = "codimportacion";
    public static final String PROPERTY_IMPORTACION = "importacion";
    public static final String PROPERTY_DESCRIPCIONIMP = "descripcionimp";
    public static final String PROPERTY_FACTURA = "factura";
    public static final String PROPERTY_FECHA = "fecha";
    public static final String PROPERTY_TERCERO = "tercero";
    public static final String PROPERTY_DESCRIPCION = "descripcion";
    public static final String PROPERTY_TOTALFACTURA = "totalfactura";
    public static final String PROPERTY_PORCENTAJEIMP = "porcentajeimp";
    public static final String PROPERTY_VALORIMPORTACION = "valorimportacion";
    public static final String PROPERTY_FACTCOMPRA = "factcompra";
    public static final String PROPERTY_DETALLE = "detalle";
    public static final String PROPERTY_FOB = "fob";
    public static final String PROPERTY_GASTOS = "gastos";

    public imReporteFacturasV() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_FACTCOMPRA, false);
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

    public String getCodimportacion() {
        return (String) get(PROPERTY_CODIMPORTACION);
    }

    public void setCodimportacion(String codimportacion) {
        set(PROPERTY_CODIMPORTACION, codimportacion);
    }

    public String getImportacion() {
        return (String) get(PROPERTY_IMPORTACION);
    }

    public void setImportacion(String importacion) {
        set(PROPERTY_IMPORTACION, importacion);
    }

    public String getDescripcionimp() {
        return (String) get(PROPERTY_DESCRIPCIONIMP);
    }

    public void setDescripcionimp(String descripcionimp) {
        set(PROPERTY_DESCRIPCIONIMP, descripcionimp);
    }

    public String getFactura() {
        return (String) get(PROPERTY_FACTURA);
    }

    public void setFactura(String factura) {
        set(PROPERTY_FACTURA, factura);
    }

    public Date getFecha() {
        return (Date) get(PROPERTY_FECHA);
    }

    public void setFecha(Date fecha) {
        set(PROPERTY_FECHA, fecha);
    }

    public String getTercero() {
        return (String) get(PROPERTY_TERCERO);
    }

    public void setTercero(String tercero) {
        set(PROPERTY_TERCERO, tercero);
    }

    public String getDescripcion() {
        return (String) get(PROPERTY_DESCRIPCION);
    }

    public void setDescripcion(String descripcion) {
        set(PROPERTY_DESCRIPCION, descripcion);
    }

    public BigDecimal getTotalfactura() {
        return (BigDecimal) get(PROPERTY_TOTALFACTURA);
    }

    public void setTotalfactura(BigDecimal totalfactura) {
        set(PROPERTY_TOTALFACTURA, totalfactura);
    }

    public BigDecimal getPorcentajeimp() {
        return (BigDecimal) get(PROPERTY_PORCENTAJEIMP);
    }

    public void setPorcentajeimp(BigDecimal porcentajeimp) {
        set(PROPERTY_PORCENTAJEIMP, porcentajeimp);
    }

    public BigDecimal getValorimportacion() {
        return (BigDecimal) get(PROPERTY_VALORIMPORTACION);
    }

    public void setValorimportacion(BigDecimal valorimportacion) {
        set(PROPERTY_VALORIMPORTACION, valorimportacion);
    }

    public Boolean isFactcompra() {
        return (Boolean) get(PROPERTY_FACTCOMPRA);
    }

    public void setFactcompra(Boolean factcompra) {
        set(PROPERTY_FACTCOMPRA, factcompra);
    }

    public String getDetalle() {
        return (String) get(PROPERTY_DETALLE);
    }

    public void setDetalle(String detalle) {
        set(PROPERTY_DETALLE, detalle);
    }

    public BigDecimal getFob() {
        return (BigDecimal) get(PROPERTY_FOB);
    }

    public void setFob(BigDecimal fob) {
        set(PROPERTY_FOB, fob);
    }

    public BigDecimal getGastos() {
        return (BigDecimal) get(PROPERTY_GASTOS);
    }

    public void setGastos(BigDecimal gastos) {
        set(PROPERTY_GASTOS, gastos);
    }

}
