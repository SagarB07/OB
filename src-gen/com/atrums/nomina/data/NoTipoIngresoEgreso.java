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

import com.atrums.importaciondatos.IdtImportacionDatos;
import com.atrums.nomina.empleados.data.nePerfilRubroLine;

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
/**
 * Entity class for entity No_TipoIngresoEgreso (stored in table no_tipo_ingreso_egreso).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class NoTipoIngresoEgreso extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "no_tipo_ingreso_egreso";
    public static final String ENTITY_NAME = "No_TipoIngresoEgreso";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CALCULODERUBRO = "calculoDeRubro";
    public static final String PROPERTY_ESINGRESO = "esIngreso";
    public static final String PROPERTY_NOMBREDELTIPODEINGRESOEGRESO = "nombreDelTipoDeIngresoEgreso";
    public static final String PROPERTY_SUMAALINGRESO = "sumaAlIngreso";
    public static final String PROPERTY_ESPARAPROVISIN = "esParaProvisin";
    public static final String PROPERTY_TIPORUBRO = "tipoRubro";
    public static final String PROPERTY_MESDECALCULO = "mesDeCalculo";
    public static final String PROPERTY_MESDELPAGO = "mesDelPago";
    public static final String PROPERTY_DADEPAGO = "daDePago";
    public static final String PROPERTY_MAXHEXTRA = "mAXHExtra";
    public static final String PROPERTY_AVANCE = "avance";
    public static final String PROPERTY_PROVISINMENSUAL = "provisinMensual";
    public static final String PROPERTY_ESDECIMOTERCERO = "esDecimoTercero";
    public static final String PROPERTY_ESDECIMOCUARTO = "esDecimoCuarto";
    public static final String PROPERTY_ESINGRESOVARIABLE = "esIngresoVariable";
    public static final String PROPERTY_ESFONDORESERVA = "esFondoReserva";
    public static final String PROPERTY_ESSUELDO = "esSueldo";
    public static final String PROPERTY_IDTNOVEDADLIST = "iDTNovedadList";
    public static final String PROPERTY_NOROLPAGOPROVISIONLINELIST = "nORolPagoProvisionLineList";
    public static final String PROPERTY_NOCBEMPLEADOACCTLIST = "noCbEmpleadoAcctList";
    public static final String PROPERTY_NOEMPLEADOINGEGRLIST = "noEmpleadoIngEgrList";
    public static final String PROPERTY_NEPERFILRUBROLINELIST = "nePerfilRubroLineList";
    public static final String PROPERTY_NONOVEDADLIST = "noNovedadList";
    public static final String PROPERTY_NOPRESTAMOLIST = "noPrestamoList";
    public static final String PROPERTY_NOREGISTRAQUINCLINELIST = "noRegistraQuincLineList";
    public static final String PROPERTY_NOREGISTROGASTOLINELIST = "noRegistroGastoLineList";
    public static final String PROPERTY_NOTIPOINGEGRACCTLIST = "noTipoIngEgrAcctList";

    public NoTipoIngresoEgreso() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ESINGRESO, false);
        setDefaultValue(PROPERTY_SUMAALINGRESO, false);
        setDefaultValue(PROPERTY_ESPARAPROVISIN, false);
        setDefaultValue(PROPERTY_DADEPAGO, (long) 0);
        setDefaultValue(PROPERTY_AVANCE, false);
        setDefaultValue(PROPERTY_PROVISINMENSUAL, false);
        setDefaultValue(PROPERTY_ESDECIMOTERCERO, false);
        setDefaultValue(PROPERTY_ESDECIMOCUARTO, false);
        setDefaultValue(PROPERTY_ESINGRESOVARIABLE, false);
        setDefaultValue(PROPERTY_ESFONDORESERVA, false);
        setDefaultValue(PROPERTY_ESSUELDO, false);
        setDefaultValue(PROPERTY_IDTNOVEDADLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOROLPAGOPROVISIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOCBEMPLEADOACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOEMPLEADOINGEGRLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NEPERFILRUBROLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NONOVEDADLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOPRESTAMOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOREGISTRAQUINCLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOREGISTROGASTOLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOTIPOINGEGRACCTLIST, new ArrayList<Object>());
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

    public noCalculaRubro getCalculoDeRubro() {
        return (noCalculaRubro) get(PROPERTY_CALCULODERUBRO);
    }

    public void setCalculoDeRubro(noCalculaRubro calculoDeRubro) {
        set(PROPERTY_CALCULODERUBRO, calculoDeRubro);
    }

    public Boolean isEsIngreso() {
        return (Boolean) get(PROPERTY_ESINGRESO);
    }

    public void setEsIngreso(Boolean esIngreso) {
        set(PROPERTY_ESINGRESO, esIngreso);
    }

    public String getNombreDelTipoDeIngresoEgreso() {
        return (String) get(PROPERTY_NOMBREDELTIPODEINGRESOEGRESO);
    }

    public void setNombreDelTipoDeIngresoEgreso(String nombreDelTipoDeIngresoEgreso) {
        set(PROPERTY_NOMBREDELTIPODEINGRESOEGRESO, nombreDelTipoDeIngresoEgreso);
    }

    public Boolean isSumaAlIngreso() {
        return (Boolean) get(PROPERTY_SUMAALINGRESO);
    }

    public void setSumaAlIngreso(Boolean sumaAlIngreso) {
        set(PROPERTY_SUMAALINGRESO, sumaAlIngreso);
    }

    public Boolean isEsParaProvisin() {
        return (Boolean) get(PROPERTY_ESPARAPROVISIN);
    }

    public void setEsParaProvisin(Boolean esParaProvisin) {
        set(PROPERTY_ESPARAPROVISIN, esParaProvisin);
    }

    public String getTipoRubro() {
        return (String) get(PROPERTY_TIPORUBRO);
    }

    public void setTipoRubro(String tipoRubro) {
        set(PROPERTY_TIPORUBRO, tipoRubro);
    }

    public String getMesDeCalculo() {
        return (String) get(PROPERTY_MESDECALCULO);
    }

    public void setMesDeCalculo(String mesDeCalculo) {
        set(PROPERTY_MESDECALCULO, mesDeCalculo);
    }

    public String getMesDelPago() {
        return (String) get(PROPERTY_MESDELPAGO);
    }

    public void setMesDelPago(String mesDelPago) {
        set(PROPERTY_MESDELPAGO, mesDelPago);
    }

    public Long getDaDePago() {
        return (Long) get(PROPERTY_DADEPAGO);
    }

    public void setDaDePago(Long daDePago) {
        set(PROPERTY_DADEPAGO, daDePago);
    }

    public Long getMAXHExtra() {
        return (Long) get(PROPERTY_MAXHEXTRA);
    }

    public void setMAXHExtra(Long mAXHExtra) {
        set(PROPERTY_MAXHEXTRA, mAXHExtra);
    }

    public Boolean isAvance() {
        return (Boolean) get(PROPERTY_AVANCE);
    }

    public void setAvance(Boolean avance) {
        set(PROPERTY_AVANCE, avance);
    }

    public Boolean isProvisinMensual() {
        return (Boolean) get(PROPERTY_PROVISINMENSUAL);
    }

    public void setProvisinMensual(Boolean provisinMensual) {
        set(PROPERTY_PROVISINMENSUAL, provisinMensual);
    }

    public Boolean isEsDecimoTercero() {
        return (Boolean) get(PROPERTY_ESDECIMOTERCERO);
    }

    public void setEsDecimoTercero(Boolean esDecimoTercero) {
        set(PROPERTY_ESDECIMOTERCERO, esDecimoTercero);
    }

    public Boolean isEsDecimoCuarto() {
        return (Boolean) get(PROPERTY_ESDECIMOCUARTO);
    }

    public void setEsDecimoCuarto(Boolean esDecimoCuarto) {
        set(PROPERTY_ESDECIMOCUARTO, esDecimoCuarto);
    }

    public Boolean isEsIngresoVariable() {
        return (Boolean) get(PROPERTY_ESINGRESOVARIABLE);
    }

    public void setEsIngresoVariable(Boolean esIngresoVariable) {
        set(PROPERTY_ESINGRESOVARIABLE, esIngresoVariable);
    }

    public Boolean isEsFondoReserva() {
        return (Boolean) get(PROPERTY_ESFONDORESERVA);
    }

    public void setEsFondoReserva(Boolean esFondoReserva) {
        set(PROPERTY_ESFONDORESERVA, esFondoReserva);
    }

    public Boolean isEsSueldo() {
        return (Boolean) get(PROPERTY_ESSUELDO);
    }

    public void setEsSueldo(Boolean esSueldo) {
        set(PROPERTY_ESSUELDO, esSueldo);
    }

    @SuppressWarnings("unchecked")
    public List<IdtImportacionDatos> getIDTNovedadList() {
      return (List<IdtImportacionDatos>) get(PROPERTY_IDTNOVEDADLIST);
    }

    public void setIDTNovedadList(List<IdtImportacionDatos> iDTNovedadList) {
        set(PROPERTY_IDTNOVEDADLIST, iDTNovedadList);
    }

    @SuppressWarnings("unchecked")
    public List<noRolPagoProvisionLine> getNORolPagoProvisionLineList() {
      return (List<noRolPagoProvisionLine>) get(PROPERTY_NOROLPAGOPROVISIONLINELIST);
    }

    public void setNORolPagoProvisionLineList(List<noRolPagoProvisionLine> nORolPagoProvisionLineList) {
        set(PROPERTY_NOROLPAGOPROVISIONLINELIST, nORolPagoProvisionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<noCbEmpleadoAcct> getNoCbEmpleadoAcctList() {
      return (List<noCbEmpleadoAcct>) get(PROPERTY_NOCBEMPLEADOACCTLIST);
    }

    public void setNoCbEmpleadoAcctList(List<noCbEmpleadoAcct> noCbEmpleadoAcctList) {
        set(PROPERTY_NOCBEMPLEADOACCTLIST, noCbEmpleadoAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<NoEmpleadoIngresoEgreso> getNoEmpleadoIngEgrList() {
      return (List<NoEmpleadoIngresoEgreso>) get(PROPERTY_NOEMPLEADOINGEGRLIST);
    }

    public void setNoEmpleadoIngEgrList(List<NoEmpleadoIngresoEgreso> noEmpleadoIngEgrList) {
        set(PROPERTY_NOEMPLEADOINGEGRLIST, noEmpleadoIngEgrList);
    }

    @SuppressWarnings("unchecked")
    public List<nePerfilRubroLine> getNePerfilRubroLineList() {
      return (List<nePerfilRubroLine>) get(PROPERTY_NEPERFILRUBROLINELIST);
    }

    public void setNePerfilRubroLineList(List<nePerfilRubroLine> nePerfilRubroLineList) {
        set(PROPERTY_NEPERFILRUBROLINELIST, nePerfilRubroLineList);
    }

    @SuppressWarnings("unchecked")
    public List<noNovedad> getNoNovedadList() {
      return (List<noNovedad>) get(PROPERTY_NONOVEDADLIST);
    }

    public void setNoNovedadList(List<noNovedad> noNovedadList) {
        set(PROPERTY_NONOVEDADLIST, noNovedadList);
    }

    @SuppressWarnings("unchecked")
    public List<noPrestamo> getNoPrestamoList() {
      return (List<noPrestamo>) get(PROPERTY_NOPRESTAMOLIST);
    }

    public void setNoPrestamoList(List<noPrestamo> noPrestamoList) {
        set(PROPERTY_NOPRESTAMOLIST, noPrestamoList);
    }

    @SuppressWarnings("unchecked")
    public List<noRegistraQuincLine> getNoRegistraQuincLineList() {
      return (List<noRegistraQuincLine>) get(PROPERTY_NOREGISTRAQUINCLINELIST);
    }

    public void setNoRegistraQuincLineList(List<noRegistraQuincLine> noRegistraQuincLineList) {
        set(PROPERTY_NOREGISTRAQUINCLINELIST, noRegistraQuincLineList);
    }

    @SuppressWarnings("unchecked")
    public List<noRegistroGastoLine> getNoRegistroGastoLineList() {
      return (List<noRegistroGastoLine>) get(PROPERTY_NOREGISTROGASTOLINELIST);
    }

    public void setNoRegistroGastoLineList(List<noRegistroGastoLine> noRegistroGastoLineList) {
        set(PROPERTY_NOREGISTROGASTOLINELIST, noRegistroGastoLineList);
    }

    @SuppressWarnings("unchecked")
    public List<noTipoIngEgrAcct> getNoTipoIngEgrAcctList() {
      return (List<noTipoIngEgrAcct>) get(PROPERTY_NOTIPOINGEGRACCTLIST);
    }

    public void setNoTipoIngEgrAcctList(List<noTipoIngEgrAcct> noTipoIngEgrAcctList) {
        set(PROPERTY_NOTIPOINGEGRACCTLIST, noTipoIngEgrAcctList);
    }

}
