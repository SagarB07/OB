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
package com.atrums.importaciondatos;

import com.atrums.nomina.rrhh.data.AtnorhCargo;

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
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
/**
 * Entity class for entity IDT_contrato (stored in table IDT_contrato).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class IdtContrato extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "IDT_contrato";
    public static final String ENTITY_NAME = "IDT_contrato";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ATNORHCARGO = "atnorhCargo";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_EMPLEADO = "empleado";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_FINANCIALACCOUNT = "financialAccount";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_FECHAINICIO = "fechaInicio";
    public static final String PROPERTY_FECHAFIN = "fechaFin";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_SALARIO = "salario";
    public static final String PROPERTY_TIPOCONTRATO = "tipoContrato";
    public static final String PROPERTY_PAGOFONDORESERVA = "pagofondoreserva";
    public static final String PROPERTY_IMPUESTOASUMIDO = "impuestoAsumido";
    public static final String PROPERTY_APLICAUTILIDAD = "aplicaUtilidad";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_PROCESADO = "procesado";
    public static final String PROPERTY_BTNPROCESO = "btnproceso";
    public static final String PROPERTY_SISSALNET = "sissalnet";
    public static final String PROPERTY_MOTIVOSALIDA = "motivoSalida";
    public static final String PROPERTY_ISJORNADAPARCIAL = "isJornadaParcial";
    public static final String PROPERTY_NUMHORASPARCIALES = "numHorasParciales";
    public static final String PROPERTY_VACACIONPROP = "vacacionProp";
    public static final String PROPERTY_VACACIONTOM = "vacacionTom";
    public static final String PROPERTY_VACACIONRES = "vacacionRes";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_OBSERVACIONES = "observaciones";

    public IdtContrato() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PAGOFONDORESERVA, false);
        setDefaultValue(PROPERTY_IMPUESTOASUMIDO, false);
        setDefaultValue(PROPERTY_APLICAUTILIDAD, true);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
        setDefaultValue(PROPERTY_PROCESADO, false);
        setDefaultValue(PROPERTY_BTNPROCESO, false);
        setDefaultValue(PROPERTY_ISJORNADAPARCIAL, false);
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

    public AtnorhCargo getAtnorhCargo() {
        return (AtnorhCargo) get(PROPERTY_ATNORHCARGO);
    }

    public void setAtnorhCargo(AtnorhCargo atnorhCargo) {
        set(PROPERTY_ATNORHCARGO, atnorhCargo);
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

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public BusinessPartner getEmpleado() {
        return (BusinessPartner) get(PROPERTY_EMPLEADO);
    }

    public void setEmpleado(BusinessPartner empleado) {
        set(PROPERTY_EMPLEADO, empleado);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public FIN_FinancialAccount getFinancialAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_FINANCIALACCOUNT);
    }

    public void setFinancialAccount(FIN_FinancialAccount financialAccount) {
        set(PROPERTY_FINANCIALACCOUNT, financialAccount);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
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

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public Date getFechaInicio() {
        return (Date) get(PROPERTY_FECHAINICIO);
    }

    public void setFechaInicio(Date fechaInicio) {
        set(PROPERTY_FECHAINICIO, fechaInicio);
    }

    public Date getFechaFin() {
        return (Date) get(PROPERTY_FECHAFIN);
    }

    public void setFechaFin(Date fechaFin) {
        set(PROPERTY_FECHAFIN, fechaFin);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Long getSalario() {
        return (Long) get(PROPERTY_SALARIO);
    }

    public void setSalario(Long salario) {
        set(PROPERTY_SALARIO, salario);
    }

    public String getTipoContrato() {
        return (String) get(PROPERTY_TIPOCONTRATO);
    }

    public void setTipoContrato(String tipoContrato) {
        set(PROPERTY_TIPOCONTRATO, tipoContrato);
    }

    public Boolean isPagofondoreserva() {
        return (Boolean) get(PROPERTY_PAGOFONDORESERVA);
    }

    public void setPagofondoreserva(Boolean pagofondoreserva) {
        set(PROPERTY_PAGOFONDORESERVA, pagofondoreserva);
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

    public String getImportErrorMessage() {
        return (String) get(PROPERTY_IMPORTERRORMESSAGE);
    }

    public void setImportErrorMessage(String importErrorMessage) {
        set(PROPERTY_IMPORTERRORMESSAGE, importErrorMessage);
    }

    public Boolean isImportProcessComplete() {
        return (Boolean) get(PROPERTY_IMPORTPROCESSCOMPLETE);
    }

    public void setImportProcessComplete(Boolean importProcessComplete) {
        set(PROPERTY_IMPORTPROCESSCOMPLETE, importProcessComplete);
    }

    public Boolean isProcesado() {
        return (Boolean) get(PROPERTY_PROCESADO);
    }

    public void setProcesado(Boolean procesado) {
        set(PROPERTY_PROCESADO, procesado);
    }

    public Boolean isBtnproceso() {
        return (Boolean) get(PROPERTY_BTNPROCESO);
    }

    public void setBtnproceso(Boolean btnproceso) {
        set(PROPERTY_BTNPROCESO, btnproceso);
    }

    public String getSissalnet() {
        return (String) get(PROPERTY_SISSALNET);
    }

    public void setSissalnet(String sissalnet) {
        set(PROPERTY_SISSALNET, sissalnet);
    }

    public String getMotivoSalida() {
        return (String) get(PROPERTY_MOTIVOSALIDA);
    }

    public void setMotivoSalida(String motivoSalida) {
        set(PROPERTY_MOTIVOSALIDA, motivoSalida);
    }

    public Boolean isJornadaParcial() {
        return (Boolean) get(PROPERTY_ISJORNADAPARCIAL);
    }

    public void setJornadaParcial(Boolean isJornadaParcial) {
        set(PROPERTY_ISJORNADAPARCIAL, isJornadaParcial);
    }

    public Long getNumHorasParciales() {
        return (Long) get(PROPERTY_NUMHORASPARCIALES);
    }

    public void setNumHorasParciales(Long numHorasParciales) {
        set(PROPERTY_NUMHORASPARCIALES, numHorasParciales);
    }

    public BigDecimal getVacacionProp() {
        return (BigDecimal) get(PROPERTY_VACACIONPROP);
    }

    public void setVacacionProp(BigDecimal vacacionProp) {
        set(PROPERTY_VACACIONPROP, vacacionProp);
    }

    public BigDecimal getVacacionTom() {
        return (BigDecimal) get(PROPERTY_VACACIONTOM);
    }

    public void setVacacionTom(BigDecimal vacacionTom) {
        set(PROPERTY_VACACIONTOM, vacacionTom);
    }

    public BigDecimal getVacacionRes() {
        return (BigDecimal) get(PROPERTY_VACACIONRES);
    }

    public void setVacacionRes(BigDecimal vacacionRes) {
        set(PROPERTY_VACACIONRES, vacacionRes);
    }

    public String getRegion() {
        return (String) get(PROPERTY_REGION);
    }

    public void setRegion(String region) {
        set(PROPERTY_REGION, region);
    }

    public String getObservaciones() {
        return (String) get(PROPERTY_OBSERVACIONES);
    }

    public void setObservaciones(String observaciones) {
        set(PROPERTY_OBSERVACIONES, observaciones);
    }

}
