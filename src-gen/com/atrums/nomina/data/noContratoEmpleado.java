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

import com.atrums.centrocostos.data.ccoCostosNomina;
import com.atrums.nomina.rrhh.data.AtnorhCargo;

import java.math.BigDecimal;
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
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
/**
 * Entity class for entity no_contrato_empleado (stored in table no_contrato_empleado).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class noContratoEmpleado extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_contrato_empleado";
    public static final String ENTITY_NAME = "no_contrato_empleado";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_FECHADEINICIO = "fechaDeInicio";
    public static final String PROPERTY_FECHAFINAL = "fechaFinal";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_SALARIO = "salario";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_TIPODECONTRATO = "tipoDeContrato";
    public static final String PROPERTY_FONDODERESERVA = "fondoDeReserva";
    public static final String PROPERTY_FINANCIALACCOUNT = "financialAccount";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_IMPUESTOASUMIDO = "impuestoAsumido";
    public static final String PROPERTY_APLICAUTILIDAD = "aplicaUtilidad";
    public static final String PROPERTY_ATNORHCARGO = "atnorhCargo";
    public static final String PROPERTY_NESISSALNET = "neSissalnet";
    public static final String PROPERTY_NEAREAEMPRESA = "neAreaEmpresa";
    public static final String PROPERTY_NEMOTIVOSALIDA = "neMotivoSalida";
    public static final String PROPERTY_NEISJORNADAPARCIAL = "neIsJornadaParcial";
    public static final String PROPERTY_NENUMHORASPARCIALES = "neNumHorasParciales";
    public static final String PROPERTY_NEVACACIONPROP = "neVacacionProp";
    public static final String PROPERTY_NEVACACIONTOM = "neVacacionTom";
    public static final String PROPERTY_NEVACACIONRES = "neVacacionRes";
    public static final String PROPERTY_NEREGION = "neRegion";
    public static final String PROPERTY_NEOBSERVACIONES = "neObservaciones";
    public static final String PROPERTY_LIQUIDACIONEMPLEADO = "liquidacionEmpleado";
    public static final String PROPERTY_DOCACTIONNO = "docactionno";
    public static final String PROPERTY_DOCSTATUS = "docstatus";
    public static final String PROPERTY__COMPUTEDCOLUMNS = "_computedColumns";
    public static final String PROPERTY_NOPERMISOLIST = "noPermisoList";
    public static final String PROPERTY_CCOCOSTOSNOMINALIST = "ccoCostosNominaList";
    public static final String PROPERTY_NOVACACIONLIST = "noVacacionList";


    // Computed columns properties, these properties cannot be directly accessed, they need
    // to be read through _commputedColumns proxy. They cannot be directly used in HQL, OBQuery
    // nor OBCriteria. 
    public static final String COMPUTED_COLUMN_NEDIAVACACIONRES = "neDiaVacacionRes";

    public noContratoEmpleado() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SALARIO, new BigDecimal(0));
        setDefaultValue(PROPERTY_FONDODERESERVA, false);
        setDefaultValue(PROPERTY_IMPUESTOASUMIDO, false);
        setDefaultValue(PROPERTY_APLICAUTILIDAD, true);
        setDefaultValue(PROPERTY_NESISSALNET, "1");
        setDefaultValue(PROPERTY_NEISJORNADAPARCIAL, false);
        setDefaultValue(PROPERTY_NEREGION, "1");
        setDefaultValue(PROPERTY_LIQUIDACIONEMPLEADO, false);
        setDefaultValue(PROPERTY_DOCSTATUS, "BR");
        setDefaultValue(PROPERTY_NOPERMISOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CCOCOSTOSNOMINALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOVACACIONLIST, new ArrayList<Object>());
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

    public Date getFechaDeInicio() {
        return (Date) get(PROPERTY_FECHADEINICIO);
    }

    public void setFechaDeInicio(Date fechaDeInicio) {
        set(PROPERTY_FECHADEINICIO, fechaDeInicio);
    }

    public Date getFechaFinal() {
        return (Date) get(PROPERTY_FECHAFINAL);
    }

    public void setFechaFinal(Date fechaFinal) {
        set(PROPERTY_FECHAFINAL, fechaFinal);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public BigDecimal getSalario() {
        return (BigDecimal) get(PROPERTY_SALARIO);
    }

    public void setSalario(BigDecimal salario) {
        set(PROPERTY_SALARIO, salario);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public String getTipoDeContrato() {
        return (String) get(PROPERTY_TIPODECONTRATO);
    }

    public void setTipoDeContrato(String tipoDeContrato) {
        set(PROPERTY_TIPODECONTRATO, tipoDeContrato);
    }

    public Boolean isFondoDeReserva() {
        return (Boolean) get(PROPERTY_FONDODERESERVA);
    }

    public void setFondoDeReserva(Boolean fondoDeReserva) {
        set(PROPERTY_FONDODERESERVA, fondoDeReserva);
    }

    public FIN_FinancialAccount getFinancialAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_FINANCIALACCOUNT);
    }

    public void setFinancialAccount(FIN_FinancialAccount financialAccount) {
        set(PROPERTY_FINANCIALACCOUNT, financialAccount);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public Boolean isImpuestoAsumido() {
        return (Boolean) get(PROPERTY_IMPUESTOASUMIDO);
    }

    public void setImpuestoAsumido(Boolean impuestoAsumido) {
        set(PROPERTY_IMPUESTOASUMIDO, impuestoAsumido);
    }

    public Boolean isAplicaUtilidad() {
        return (Boolean) get(PROPERTY_APLICAUTILIDAD);
    }

    public void setAplicaUtilidad(Boolean aplicaUtilidad) {
        set(PROPERTY_APLICAUTILIDAD, aplicaUtilidad);
    }

    public AtnorhCargo getAtnorhCargo() {
        return (AtnorhCargo) get(PROPERTY_ATNORHCARGO);
    }

    public void setAtnorhCargo(AtnorhCargo atnorhCargo) {
        set(PROPERTY_ATNORHCARGO, atnorhCargo);
    }

    public String getNeSissalnet() {
        return (String) get(PROPERTY_NESISSALNET);
    }

    public void setNeSissalnet(String neSissalnet) {
        set(PROPERTY_NESISSALNET, neSissalnet);
    }

    public noAreaEmpresa getNeAreaEmpresa() {
        return (noAreaEmpresa) get(PROPERTY_NEAREAEMPRESA);
    }

    public void setNeAreaEmpresa(noAreaEmpresa neAreaEmpresa) {
        set(PROPERTY_NEAREAEMPRESA, neAreaEmpresa);
    }

    public String getNeMotivoSalida() {
        return (String) get(PROPERTY_NEMOTIVOSALIDA);
    }

    public void setNeMotivoSalida(String neMotivoSalida) {
        set(PROPERTY_NEMOTIVOSALIDA, neMotivoSalida);
    }

    public Boolean isNeIsJornadaParcial() {
        return (Boolean) get(PROPERTY_NEISJORNADAPARCIAL);
    }

    public void setNeIsJornadaParcial(Boolean neIsJornadaParcial) {
        set(PROPERTY_NEISJORNADAPARCIAL, neIsJornadaParcial);
    }

    public BigDecimal getNeNumHorasParciales() {
        return (BigDecimal) get(PROPERTY_NENUMHORASPARCIALES);
    }

    public void setNeNumHorasParciales(BigDecimal neNumHorasParciales) {
        set(PROPERTY_NENUMHORASPARCIALES, neNumHorasParciales);
    }

    public BigDecimal getNeVacacionProp() {
        return (BigDecimal) get(PROPERTY_NEVACACIONPROP);
    }

    public void setNeVacacionProp(BigDecimal neVacacionProp) {
        set(PROPERTY_NEVACACIONPROP, neVacacionProp);
    }

    public BigDecimal getNeVacacionTom() {
        return (BigDecimal) get(PROPERTY_NEVACACIONTOM);
    }

    public void setNeVacacionTom(BigDecimal neVacacionTom) {
        set(PROPERTY_NEVACACIONTOM, neVacacionTom);
    }

    public BigDecimal getNeVacacionRes() {
        return (BigDecimal) get(PROPERTY_NEVACACIONRES);
    }

    public void setNeVacacionRes(BigDecimal neVacacionRes) {
        set(PROPERTY_NEVACACIONRES, neVacacionRes);
    }

    public String getNeRegion() {
        return (String) get(PROPERTY_NEREGION);
    }

    public void setNeRegion(String neRegion) {
        set(PROPERTY_NEREGION, neRegion);
    }

    public String getNeObservaciones() {
        return (String) get(PROPERTY_NEOBSERVACIONES);
    }

    public void setNeObservaciones(String neObservaciones) {
        set(PROPERTY_NEOBSERVACIONES, neObservaciones);
    }

    public Boolean isLiquidacionEmpleado() {
        return (Boolean) get(PROPERTY_LIQUIDACIONEMPLEADO);
    }

    public void setLiquidacionEmpleado(Boolean liquidacionEmpleado) {
        set(PROPERTY_LIQUIDACIONEMPLEADO, liquidacionEmpleado);
    }

    public String getDocactionno() {
        return (String) get(PROPERTY_DOCACTIONNO);
    }

    public void setDocactionno(String docactionno) {
        set(PROPERTY_DOCACTIONNO, docactionno);
    }

    public String getDocstatus() {
        return (String) get(PROPERTY_DOCSTATUS);
    }

    public void setDocstatus(String docstatus) {
        set(PROPERTY_DOCSTATUS, docstatus);
    }

    public BigDecimal getNeDiaVacacionRes() {
        return (BigDecimal) get(COMPUTED_COLUMN_NEDIAVACACIONRES);
    }

    public void setNeDiaVacacionRes(BigDecimal neDiaVacacionRes) {
        set(COMPUTED_COLUMN_NEDIAVACACIONRES, neDiaVacacionRes);
    }

    public noContratoEmpleado_ComputedColumns get_computedColumns() {
        return (noContratoEmpleado_ComputedColumns) get(PROPERTY__COMPUTEDCOLUMNS);
    }

    public void set_computedColumns(noContratoEmpleado_ComputedColumns _computedColumns) {
        set(PROPERTY__COMPUTEDCOLUMNS, _computedColumns);
    }

    @SuppressWarnings("unchecked")
    public List<NoPermiso> getNoPermisoList() {
      return (List<NoPermiso>) get(PROPERTY_NOPERMISOLIST);
    }

    public void setNoPermisoList(List<NoPermiso> noPermisoList) {
        set(PROPERTY_NOPERMISOLIST, noPermisoList);
    }

    @SuppressWarnings("unchecked")
    public List<ccoCostosNomina> getCcoCostosNominaList() {
      return (List<ccoCostosNomina>) get(PROPERTY_CCOCOSTOSNOMINALIST);
    }

    public void setCcoCostosNominaList(List<ccoCostosNomina> ccoCostosNominaList) {
        set(PROPERTY_CCOCOSTOSNOMINALIST, ccoCostosNominaList);
    }

    @SuppressWarnings("unchecked")
    public List<noVacacion> getNoVacacionList() {
      return (List<noVacacion>) get(PROPERTY_NOVACACIONLIST);
    }

    public void setNoVacacionList(List<noVacacion> noVacacionList) {
        set(PROPERTY_NOVACACIONLIST, noVacacionList);
    }


    @Override
    public Object get(String propName) {
      if (COMPUTED_COLUMN_NEDIAVACACIONRES.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getNeDiaVacacionRes();
      }
    
      return super.get(propName);
    }
}
