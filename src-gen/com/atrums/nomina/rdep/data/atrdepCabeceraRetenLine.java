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
package com.atrums.nomina.rdep.data;

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
 * Entity class for entity atrdep_cabecera_reten_line (stored in table atrdep_cabecera_reten_line).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class atrdepCabeceraRetenLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "atrdep_cabecera_reten_line";
    public static final String ENTITY_NAME = "atrdep_cabecera_reten_line";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ATRDEPCABECERARETEN = "atrdepCabeceraReten";
    public static final String PROPERTY_EMPLEADO = "empleado";
    public static final String PROPERTY_NUMRUC = "numruc";
    public static final String PROPERTY_TIPIDRET = "tipidret";
    public static final String PROPERTY_IDRET = "idret";
    public static final String PROPERTY_DIRCAL = "dircal";
    public static final String PROPERTY_DIRNUM = "dirnum";
    public static final String PROPERTY_DIRCIU = "dirciu";
    public static final String PROPERTY_DIRPROV = "dirprov";
    public static final String PROPERTY_TEL = "tel";
    public static final String PROPERTY_SISSALNET = "sissalnet";
    public static final String PROPERTY_SUELSAL = "suelsal";
    public static final String PROPERTY_SOBSUELCOMREMU = "sobsuelcomremu";
    public static final String PROPERTY_DECIMTER = "decimter";
    public static final String PROPERTY_DECIMCUAR = "decimcuar";
    public static final String PROPERTY_FONDORESERVA = "fondoreserva";
    public static final String PROPERTY_SALARIODIGNO = "salariodigno";
    public static final String PROPERTY_PARTUTIL = "partutil";
    public static final String PROPERTY_DESAUOTRAS = "desauotras";
    public static final String PROPERTY_APOPERIESS = "apoperiess";
    public static final String PROPERTY_DEDUCVIVIENDA = "deducvivienda";
    public static final String PROPERTY_DEDUCSALUD = "deducsalud";
    public static final String PROPERTY_DEDUCEDUCA = "deduceduca";
    public static final String PROPERTY_DEDUCALIEMENT = "deducaliement";
    public static final String PROPERTY_DEDUCVESTIM = "deducvestim";
    public static final String PROPERTY_REBESPDISCAP = "rebespdiscap";
    public static final String PROPERTY_REBESPTERED = "rebesptered";
    public static final String PROPERTY_IMPRENTEMPL = "imprentempl";
    public static final String PROPERTY_SUBTOTAL = "subtotal";
    public static final String PROPERTY_NUMRET = "numret";
    public static final String PROPERTY_NUMMESEMPLEAD = "nummesemplead";
    public static final String PROPERTY_INTGRABGEN = "intgrabgen";
    public static final String PROPERTY_DEDUCCGASTOSOTREMPL = "deduccgastosotrempl";
    public static final String PROPERTY_OTRREBJOTREMPL = "otrrebjotrempl";
    public static final String PROPERTY_BASIMP = "basimp";
    public static final String PROPERTY_IMPRENTCAUS = "imprentcaus";
    public static final String PROPERTY_VALRET = "valret";
    public static final String PROPERTY_VALORIMPEMPANTER = "valorimpempanter";
    public static final String PROPERTY_AO = "ao";
    public static final String PROPERTY_ANIORET = "anioret";
    public static final String PROPERTY_VALIMPASUESTEEMPL = "valimpasuesteempl";
    public static final String PROPERTY_ESTAB = "estab";
    public static final String PROPERTY_OTROSINGRENGRAV = "otrosingrengrav";
    public static final String PROPERTY_INGGRAVCONESTEEMPL = "inggravconesteempl";
    public static final String PROPERTY_APORPERIESSCONOTROSEMPLS = "aporperiessconotrosempls";

    public atrdepCabeceraRetenLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TIPIDRET, false);
        setDefaultValue(PROPERTY_SOBSUELCOMREMU, new BigDecimal(0));
        setDefaultValue(PROPERTY_DECIMTER, new BigDecimal(0));
        setDefaultValue(PROPERTY_DECIMCUAR, new BigDecimal(0));
        setDefaultValue(PROPERTY_FONDORESERVA, new BigDecimal(0));
        setDefaultValue(PROPERTY_SALARIODIGNO, new BigDecimal(0));
        setDefaultValue(PROPERTY_PARTUTIL, new BigDecimal(0));
        setDefaultValue(PROPERTY_DESAUOTRAS, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEDUCVIVIENDA, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEDUCSALUD, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEDUCEDUCA, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEDUCALIEMENT, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEDUCVESTIM, new BigDecimal(0));
        setDefaultValue(PROPERTY_REBESPDISCAP, new BigDecimal(0));
        setDefaultValue(PROPERTY_REBESPTERED, new BigDecimal(0));
        setDefaultValue(PROPERTY_SUBTOTAL, new BigDecimal(0));
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

    public atrdepCabeceraReten getAtrdepCabeceraReten() {
        return (atrdepCabeceraReten) get(PROPERTY_ATRDEPCABECERARETEN);
    }

    public void setAtrdepCabeceraReten(atrdepCabeceraReten atrdepCabeceraReten) {
        set(PROPERTY_ATRDEPCABECERARETEN, atrdepCabeceraReten);
    }

    public BusinessPartner getEmpleado() {
        return (BusinessPartner) get(PROPERTY_EMPLEADO);
    }

    public void setEmpleado(BusinessPartner empleado) {
        set(PROPERTY_EMPLEADO, empleado);
    }

    public String getNumruc() {
        return (String) get(PROPERTY_NUMRUC);
    }

    public void setNumruc(String numruc) {
        set(PROPERTY_NUMRUC, numruc);
    }

    public Boolean isTipidret() {
        return (Boolean) get(PROPERTY_TIPIDRET);
    }

    public void setTipidret(Boolean tipidret) {
        set(PROPERTY_TIPIDRET, tipidret);
    }

    public String getIdret() {
        return (String) get(PROPERTY_IDRET);
    }

    public void setIdret(String idret) {
        set(PROPERTY_IDRET, idret);
    }

    public String getDircal() {
        return (String) get(PROPERTY_DIRCAL);
    }

    public void setDircal(String dircal) {
        set(PROPERTY_DIRCAL, dircal);
    }

    public String getDirnum() {
        return (String) get(PROPERTY_DIRNUM);
    }

    public void setDirnum(String dirnum) {
        set(PROPERTY_DIRNUM, dirnum);
    }

    public String getDirciu() {
        return (String) get(PROPERTY_DIRCIU);
    }

    public void setDirciu(String dirciu) {
        set(PROPERTY_DIRCIU, dirciu);
    }

    public String getDirprov() {
        return (String) get(PROPERTY_DIRPROV);
    }

    public void setDirprov(String dirprov) {
        set(PROPERTY_DIRPROV, dirprov);
    }

    public String getTel() {
        return (String) get(PROPERTY_TEL);
    }

    public void setTel(String tel) {
        set(PROPERTY_TEL, tel);
    }

    public String getSissalnet() {
        return (String) get(PROPERTY_SISSALNET);
    }

    public void setSissalnet(String sissalnet) {
        set(PROPERTY_SISSALNET, sissalnet);
    }

    public BigDecimal getSuelsal() {
        return (BigDecimal) get(PROPERTY_SUELSAL);
    }

    public void setSuelsal(BigDecimal suelsal) {
        set(PROPERTY_SUELSAL, suelsal);
    }

    public BigDecimal getSobsuelcomremu() {
        return (BigDecimal) get(PROPERTY_SOBSUELCOMREMU);
    }

    public void setSobsuelcomremu(BigDecimal sobsuelcomremu) {
        set(PROPERTY_SOBSUELCOMREMU, sobsuelcomremu);
    }

    public BigDecimal getDecimter() {
        return (BigDecimal) get(PROPERTY_DECIMTER);
    }

    public void setDecimter(BigDecimal decimter) {
        set(PROPERTY_DECIMTER, decimter);
    }

    public BigDecimal getDecimcuar() {
        return (BigDecimal) get(PROPERTY_DECIMCUAR);
    }

    public void setDecimcuar(BigDecimal decimcuar) {
        set(PROPERTY_DECIMCUAR, decimcuar);
    }

    public BigDecimal getFondoreserva() {
        return (BigDecimal) get(PROPERTY_FONDORESERVA);
    }

    public void setFondoreserva(BigDecimal fondoreserva) {
        set(PROPERTY_FONDORESERVA, fondoreserva);
    }

    public BigDecimal getSalariodigno() {
        return (BigDecimal) get(PROPERTY_SALARIODIGNO);
    }

    public void setSalariodigno(BigDecimal salariodigno) {
        set(PROPERTY_SALARIODIGNO, salariodigno);
    }

    public BigDecimal getPartutil() {
        return (BigDecimal) get(PROPERTY_PARTUTIL);
    }

    public void setPartutil(BigDecimal partutil) {
        set(PROPERTY_PARTUTIL, partutil);
    }

    public BigDecimal getDesauotras() {
        return (BigDecimal) get(PROPERTY_DESAUOTRAS);
    }

    public void setDesauotras(BigDecimal desauotras) {
        set(PROPERTY_DESAUOTRAS, desauotras);
    }

    public BigDecimal getApoperiess() {
        return (BigDecimal) get(PROPERTY_APOPERIESS);
    }

    public void setApoperiess(BigDecimal apoperiess) {
        set(PROPERTY_APOPERIESS, apoperiess);
    }

    public BigDecimal getDeducvivienda() {
        return (BigDecimal) get(PROPERTY_DEDUCVIVIENDA);
    }

    public void setDeducvivienda(BigDecimal deducvivienda) {
        set(PROPERTY_DEDUCVIVIENDA, deducvivienda);
    }

    public BigDecimal getDeducsalud() {
        return (BigDecimal) get(PROPERTY_DEDUCSALUD);
    }

    public void setDeducsalud(BigDecimal deducsalud) {
        set(PROPERTY_DEDUCSALUD, deducsalud);
    }

    public BigDecimal getDeduceduca() {
        return (BigDecimal) get(PROPERTY_DEDUCEDUCA);
    }

    public void setDeduceduca(BigDecimal deduceduca) {
        set(PROPERTY_DEDUCEDUCA, deduceduca);
    }

    public BigDecimal getDeducaliement() {
        return (BigDecimal) get(PROPERTY_DEDUCALIEMENT);
    }

    public void setDeducaliement(BigDecimal deducaliement) {
        set(PROPERTY_DEDUCALIEMENT, deducaliement);
    }

    public BigDecimal getDeducvestim() {
        return (BigDecimal) get(PROPERTY_DEDUCVESTIM);
    }

    public void setDeducvestim(BigDecimal deducvestim) {
        set(PROPERTY_DEDUCVESTIM, deducvestim);
    }

    public BigDecimal getRebespdiscap() {
        return (BigDecimal) get(PROPERTY_REBESPDISCAP);
    }

    public void setRebespdiscap(BigDecimal rebespdiscap) {
        set(PROPERTY_REBESPDISCAP, rebespdiscap);
    }

    public BigDecimal getRebesptered() {
        return (BigDecimal) get(PROPERTY_REBESPTERED);
    }

    public void setRebesptered(BigDecimal rebesptered) {
        set(PROPERTY_REBESPTERED, rebesptered);
    }

    public BigDecimal getImprentempl() {
        return (BigDecimal) get(PROPERTY_IMPRENTEMPL);
    }

    public void setImprentempl(BigDecimal imprentempl) {
        set(PROPERTY_IMPRENTEMPL, imprentempl);
    }

    public BigDecimal getSubtotal() {
        return (BigDecimal) get(PROPERTY_SUBTOTAL);
    }

    public void setSubtotal(BigDecimal subtotal) {
        set(PROPERTY_SUBTOTAL, subtotal);
    }

    public Long getNumret() {
        return (Long) get(PROPERTY_NUMRET);
    }

    public void setNumret(Long numret) {
        set(PROPERTY_NUMRET, numret);
    }

    public Long getNummesemplead() {
        return (Long) get(PROPERTY_NUMMESEMPLEAD);
    }

    public void setNummesemplead(Long nummesemplead) {
        set(PROPERTY_NUMMESEMPLEAD, nummesemplead);
    }

    public BigDecimal getIntgrabgen() {
        return (BigDecimal) get(PROPERTY_INTGRABGEN);
    }

    public void setIntgrabgen(BigDecimal intgrabgen) {
        set(PROPERTY_INTGRABGEN, intgrabgen);
    }

    public BigDecimal getDeduccgastosotrempl() {
        return (BigDecimal) get(PROPERTY_DEDUCCGASTOSOTREMPL);
    }

    public void setDeduccgastosotrempl(BigDecimal deduccgastosotrempl) {
        set(PROPERTY_DEDUCCGASTOSOTREMPL, deduccgastosotrempl);
    }

    public BigDecimal getOtrrebjotrempl() {
        return (BigDecimal) get(PROPERTY_OTRREBJOTREMPL);
    }

    public void setOtrrebjotrempl(BigDecimal otrrebjotrempl) {
        set(PROPERTY_OTRREBJOTREMPL, otrrebjotrempl);
    }

    public BigDecimal getBasimp() {
        return (BigDecimal) get(PROPERTY_BASIMP);
    }

    public void setBasimp(BigDecimal basimp) {
        set(PROPERTY_BASIMP, basimp);
    }

    public BigDecimal getImprentcaus() {
        return (BigDecimal) get(PROPERTY_IMPRENTCAUS);
    }

    public void setImprentcaus(BigDecimal imprentcaus) {
        set(PROPERTY_IMPRENTCAUS, imprentcaus);
    }

    public BigDecimal getValret() {
        return (BigDecimal) get(PROPERTY_VALRET);
    }

    public void setValret(BigDecimal valret) {
        set(PROPERTY_VALRET, valret);
    }

    public BigDecimal getValorimpempanter() {
        return (BigDecimal) get(PROPERTY_VALORIMPEMPANTER);
    }

    public void setValorimpempanter(BigDecimal valorimpempanter) {
        set(PROPERTY_VALORIMPEMPANTER, valorimpempanter);
    }

    public String getAo() {
        return (String) get(PROPERTY_AO);
    }

    public void setAo(String ao) {
        set(PROPERTY_AO, ao);
    }

    public String getAnioret() {
        return (String) get(PROPERTY_ANIORET);
    }

    public void setAnioret(String anioret) {
        set(PROPERTY_ANIORET, anioret);
    }

    public BigDecimal getValimpasuesteempl() {
        return (BigDecimal) get(PROPERTY_VALIMPASUESTEEMPL);
    }

    public void setValimpasuesteempl(BigDecimal valimpasuesteempl) {
        set(PROPERTY_VALIMPASUESTEEMPL, valimpasuesteempl);
    }

    public String getEstab() {
        return (String) get(PROPERTY_ESTAB);
    }

    public void setEstab(String estab) {
        set(PROPERTY_ESTAB, estab);
    }

    public BigDecimal getOtrosingrengrav() {
        return (BigDecimal) get(PROPERTY_OTROSINGRENGRAV);
    }

    public void setOtrosingrengrav(BigDecimal otrosingrengrav) {
        set(PROPERTY_OTROSINGRENGRAV, otrosingrengrav);
    }

    public BigDecimal getInggravconesteempl() {
        return (BigDecimal) get(PROPERTY_INGGRAVCONESTEEMPL);
    }

    public void setInggravconesteempl(BigDecimal inggravconesteempl) {
        set(PROPERTY_INGGRAVCONESTEEMPL, inggravconesteempl);
    }

    public BigDecimal getAporperiessconotrosempls() {
        return (BigDecimal) get(PROPERTY_APORPERIESSCONOTROSEMPLS);
    }

    public void setAporperiessconotrosempls(BigDecimal aporperiessconotrosempls) {
        set(PROPERTY_APORPERIESSCONOTROSEMPLS, aporperiessconotrosempls);
    }

}
