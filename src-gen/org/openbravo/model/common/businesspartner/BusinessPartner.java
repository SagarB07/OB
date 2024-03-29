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
package org.openbravo.model.common.businesspartner;

import com.atrums.activos.data.atecacActivoEmp;
import com.atrums.aserlaco.venta.data.asvPedidoInventario;
import com.atrums.contabilidad.data.BpRetencionVenta;
import com.atrums.contabilidad.data.CO_BpRetencionCompra;
import com.atrums.contabilidad.data.CO_Retencion_Compra;
import com.atrums.contabilidad.data.CoRetencionVentaView;
import com.atrums.depositos.data.DP_FinaccTransactionV;
import com.atrums.importaciondatos.IdtContrato;
import com.atrums.importaciondatos.IdtImportacionDatos;
import com.atrums.nomina.data.NO_Registro_Hora_Extra;
import com.atrums.nomina.data.NoEmpleadoIngresoEgreso;
import com.atrums.nomina.data.NoPermiso;
import com.atrums.nomina.data.noAreaEmpresa;
import com.atrums.nomina.data.noCargaEmpleado;
import com.atrums.nomina.data.noCbEmpleadoAcct;
import com.atrums.nomina.data.noContratoEmpleado;
import com.atrums.nomina.data.noGastosEmpleado;
import com.atrums.nomina.data.noLiquidacionEmpleado;
import com.atrums.nomina.data.noNovedadLinea;
import com.atrums.nomina.data.noPagoLine;
import com.atrums.nomina.data.noPrestamo;
import com.atrums.nomina.data.noReComisionDetalle;
import com.atrums.nomina.data.noRegistraQuincLine;
import com.atrums.nomina.data.noRegistroGasto;
import com.atrums.nomina.data.noRolPagoProvision;
import com.atrums.nomina.data.noRptCargasFamiliaresV;
import com.atrums.nomina.data.noSalarioDignoLinea;
import com.atrums.nomina.data.noUtilidadLinea;
import com.atrums.nomina.data.noVacacion;
import com.atrums.nomina.empleados.data.nePerfilRubro;
import com.atrums.nomina.rdep.data.atrdepCabeceraRetenLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_FinaccTransactionV;
import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.APRM_PaymentProposalPickEdit;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.ClientInformation;
import org.openbravo.model.ad.system.Language;
import org.openbravo.model.common.bank.Bank;
import org.openbravo.model.common.bank.BankAccount;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.common.enterprise.WarehouseShipper;
import org.openbravo.model.common.geography.City;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.hcm.SalaryCategory;
import org.openbravo.model.common.interaction.EmailInteraction;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.invoice.InvoiceLineAccountingDimension;
import org.openbravo.model.common.invoice.InvoiceLineV2;
import org.openbravo.model.common.invoice.InvoiceSchedule;
import org.openbravo.model.common.invoice.InvoiceTaxCashVAT_V;
import org.openbravo.model.common.invoice.InvoiceV2;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.order.OrderLineAccountingDimension;
import org.openbravo.model.common.order.ReturnMaterialOrderPickEditLines;
import org.openbravo.model.common.plm.ApprovedVendor;
import org.openbravo.model.common.plm.ProductCustomer;
import org.openbravo.model.dataimport.BankStatement;
import org.openbravo.model.dataimport.BudgetLine;
import org.openbravo.model.dataimport.GLJournal;
import org.openbravo.model.dataimport.Invoice;
import org.openbravo.model.dataimport.Order;
import org.openbravo.model.dataimport.Product;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchemaElement;
import org.openbravo.model.financialmgmt.assetmgmt.AmortizationLineAccountingDimension;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.financialmgmt.payment.DebtPayment;
import org.openbravo.model.financialmgmt.payment.DebtPaymentCancelV;
import org.openbravo.model.financialmgmt.payment.DebtPaymentGenerateV;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebt;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebtRun;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebtV;
import org.openbravo.model.financialmgmt.payment.FIN_BankStatementLine;
import org.openbravo.model.financialmgmt.payment.FIN_FinaccTransaction;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_Payment;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentDetailV;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentPropDetailV;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentProposal;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentScheduleDetail;
import org.openbravo.model.financialmgmt.payment.Incoterms;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.financialmgmt.tax.TaxPayment;
import org.openbravo.model.manufacturing.maintenance.Worker;
import org.openbravo.model.manufacturing.transaction.ProductionRunEmployee;
import org.openbravo.model.manufacturing.transaction.WorkEffortEmployee;
import org.openbravo.model.materialmgmt.onhandquantity.PrereservationManualPickEdit;
import org.openbravo.model.materialmgmt.onhandquantity.Reservation;
import org.openbravo.model.materialmgmt.transaction.InOutLineAccountingDimension;
import org.openbravo.model.materialmgmt.transaction.MaterialTransactionV;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.model.mrp.ProductionRun;
import org.openbravo.model.mrp.PurchasingRun;
import org.openbravo.model.mrp.PurchasingRunLine;
import org.openbravo.model.mrp.SalesForecast;
import org.openbravo.model.pos.ExternalPOS;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.model.pricing.pricelist.PriceListSchemeLine;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.project.ActiveProposal;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectProposal;
import org.openbravo.model.project.ProjectVendor;
import org.openbravo.model.sales.Commission;
import org.openbravo.model.sales.CommissionLine;
import org.openbravo.model.shipping.ShippingCompany;
import org.openbravo.model.timeandexpense.Sheet;
import org.openbravo.model.timeandexpense.SheetLine;
import org.openbravo.model.timeandexpense.SheetLineV;
/**
 * Entity class for entity BusinessPartner (stored in table C_BPartner).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class BusinessPartner extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BPartner";
    public static final String ENTITY_NAME = "BusinessPartner";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_NAME2 = "name2";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORY = "businessPartnerCategory";
    public static final String PROPERTY_ONETIMETRANSACTION = "oneTimeTransaction";
    public static final String PROPERTY_POTENTIALCUSTOMER = "potentialCustomer";
    public static final String PROPERTY_VENDOR = "vendor";
    public static final String PROPERTY_CUSTOMER = "customer";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_ISSALESREPRESENTATIVE = "isSalesRepresentative";
    public static final String PROPERTY_REFERENCENO = "referenceNo";
    public static final String PROPERTY_DUNS = "dUNS";
    public static final String PROPERTY_URL = "uRL";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_TAXID = "taxID";
    public static final String PROPERTY_TAXEXEMPT = "taxExempt";
    public static final String PROPERTY_INVOICESCHEDULE = "invoiceSchedule";
    public static final String PROPERTY_VALUATION = "valuation";
    public static final String PROPERTY_VOLUMEOFSALES = "volumeOfSales";
    public static final String PROPERTY_NOOFEMPLOYEES = "noOfEmployees";
    public static final String PROPERTY_NAICSSIC = "nAICSSIC";
    public static final String PROPERTY_DATEOFFIRSTSALE = "dateOfFirstSale";
    public static final String PROPERTY_ACQUISITIONCOST = "acquisitionCost";
    public static final String PROPERTY_EXPECTEDLIFETIMEREVENUE = "expectedLifetimeRevenue";
    public static final String PROPERTY_LIFETIMEREVENUETODATE = "lifetimeRevenueToDate";
    public static final String PROPERTY_SHARE = "share";
    public static final String PROPERTY_FORMOFPAYMENT = "formOfPayment";
    public static final String PROPERTY_CREDITLIMIT = "creditLimit";
    public static final String PROPERTY_CREDITUSED = "creditUsed";
    public static final String PROPERTY_PAYMENTTERMS = "paymentTerms";
    public static final String PROPERTY_PRICELIST = "priceList";
    public static final String PROPERTY_PRINTDISCOUNT = "printDiscount";
    public static final String PROPERTY_ORDERDESCRIPTION = "orderDescription";
    public static final String PROPERTY_ORDERREFERENCE = "orderReference";
    public static final String PROPERTY_POFORMOFPAYMENT = "pOFormOfPayment";
    public static final String PROPERTY_PURCHASEPRICELIST = "purchasePricelist";
    public static final String PROPERTY_POPAYMENTTERMS = "pOPaymentTerms";
    public static final String PROPERTY_NUMBEROFCOPIES = "numberOfCopies";
    public static final String PROPERTY_GREETING = "greeting";
    public static final String PROPERTY_INVOICETERMS = "invoiceTerms";
    public static final String PROPERTY_DELIVERYTERMS = "deliveryTerms";
    public static final String PROPERTY_DELIVERYMETHOD = "deliveryMethod";
    public static final String PROPERTY_SALESREPRESENTATIVE = "salesRepresentative";
    public static final String PROPERTY_PARTNERPARENT = "partnerParent";
    public static final String PROPERTY_CREDITSTATUS = "creditStatus";
    public static final String PROPERTY_FORCEDORG = "forcedOrg";
    public static final String PROPERTY_PRICESSHOWNINORDER = "pricesShownInOrder";
    public static final String PROPERTY_INVOICEGROUPING = "invoiceGrouping";
    public static final String PROPERTY_MATURITYDATE1 = "maturityDate1";
    public static final String PROPERTY_MATURITYDATE2 = "maturityDate2";
    public static final String PROPERTY_MATURITYDATE3 = "maturityDate3";
    public static final String PROPERTY_OPERATOR = "operator";
    public static final String PROPERTY_UPCEAN = "uPCEAN";
    public static final String PROPERTY_SALARYCATEGORY = "salaryCategory";
    public static final String PROPERTY_INVOICEPRINTFORMAT = "invoicePrintformat";
    public static final String PROPERTY_CONSUMPTIONDAYS = "consumptionDays";
    public static final String PROPERTY_BANKACCOUNT = "bankAccount";
    public static final String PROPERTY_TAXCATEGORY = "taxCategory";
    public static final String PROPERTY_POMATURITYDATE1 = "pOMaturityDate1";
    public static final String PROPERTY_POMATURITYDATE2 = "pOMaturityDate2";
    public static final String PROPERTY_POMATURITYDATE3 = "pOMaturityDate3";
    public static final String PROPERTY_TRANSACTIONALBANKACCOUNT = "transactionalBankAccount";
    public static final String PROPERTY_SOBPTAXCATEGORY = "sOBPTaxCategory";
    public static final String PROPERTY_FISCALCODE = "fiscalcode";
    public static final String PROPERTY_ISOFISCALCODE = "isofiscalcode";
    public static final String PROPERTY_INCOTERMSPO = "incotermsPO";
    public static final String PROPERTY_INCOTERMSSO = "incotermsSO";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_POPAYMENTMETHOD = "pOPaymentMethod";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_POFINANCIALACCOUNT = "pOFinancialAccount";
    public static final String PROPERTY_CUSTOMERBLOCKING = "customerBlocking";
    public static final String PROPERTY_VENDORBLOCKING = "vendorBlocking";
    public static final String PROPERTY_COTIPOCONTRIB = "coTipocontrib";
    public static final String PROPERTY_PAYMENTIN = "paymentIn";
    public static final String PROPERTY_NOESTADOCIVIL = "noEstadocivil";
    public static final String PROPERTY_PAYMENTOUT = "paymentOut";
    public static final String PROPERTY_NOGENERO = "noGenero";
    public static final String PROPERTY_SALESINVOICE = "salesInvoice";
    public static final String PROPERTY_NOFECHANACIMIENTO = "noFechanacimiento";
    public static final String PROPERTY_PURCHASEINVOICE = "purchaseInvoice";
    public static final String PROPERTY_NOAREAEMPRESA = "noAreaEmpresa";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_PURCHASEORDER = "purchaseOrder";
    public static final String PROPERTY_GOODSSHIPMENT = "goodsShipment";
    public static final String PROPERTY_GOODSRECEIPT = "goodsReceipt";
    public static final String PROPERTY_CASHVAT = "cashVAT";
    public static final String PROPERTY_NOMOTIVOSALIDA = "noMotivoSalida";
    public static final String PROPERTY_COEMAIL = "coEmail";
    public static final String PROPERTY_CONIVEDU = "coNivEdu";
    public static final String PROPERTY_NOISDISCAPACITADO = "noIsdiscapacitado";
    public static final String PROPERTY_COVIVIENDA = "coVivienda";
    public static final String PROPERTY_NOISTERCERAEDAD = "noIsterceraEdad";
    public static final String PROPERTY_COANIOSRES = "coAniosRes";
    public static final String PROPERTY_COMESESRES = "coMesesRes";
    public static final String PROPERTY_COTOTALMESES = "coTotalMeses";
    public static final String PROPERTY_COFECHANAC = "coFechanac";
    public static final String PROPERTY_COCIUDADNAC = "coCiudadnac";
    public static final String PROPERTY_CONACIONALIDAD = "coNacionalidad";
    public static final String PROPERTY_CONOMBRES = "coNombres";
    public static final String PROPERTY_COAPELLIDOS = "coApellidos";
    public static final String PROPERTY_COCOMTARJCRED = "coComTarjCred";
    public static final String PROPERTY_CONATURALJURIDICO = "coNaturalJuridico";
    public static final String PROPERTY_COBPFECVENCTAUTFCSRI = "coBpFecVenctAutFcSri";
    public static final String PROPERTY_COBPFECVENCTAUTRTSRI = "coBpFecVenctAutRtSri";
    public static final String PROPERTY_COBPNROAUTFCSRI = "coBpNroAutFcSri";
    public static final String PROPERTY_COBPNROAUTRTSRI = "coBpNroAutRtSri";
    public static final String PROPERTY_COBPNROESTAB = "coBpNroEstab";
    public static final String PROPERTY_COBPPUNTOEMISION = "coBpPuntoEmision";
    public static final String PROPERTY_COTIPOIDENTIFICACION = "cOTipoIdentificacion";
    public static final String PROPERTY_COBCALIFICACION = "cobCalificacion";
    public static final String PROPERTY_NECACCTSCHEMA = "neCAcctschema";
    public static final String PROPERTY_NEEDAD = "neEdad";
    public static final String PROPERTY_NENUMCARDISCAPACITADO = "neNumCarDiscapacitado";
    public static final String PROPERTY_NEPERFILRUBRO = "nePerfilRubro";
    public static final String PROPERTY_NOFECHADEINGRESO = "noFechaDeIngreso";
    public static final String PROPERTY_NOFECHADESALIDA = "noFechaDeSalida";
    public static final String PROPERTY_NOPAGOACCT = "noPagoAcct";
    public static final String PROPERTY_NOSISSALNET = "noSisSalNet";
    public static final String PROPERTY_ADUSERLIST = "aDUserList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONVLIST = "aPRMFinaccTransactionVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST = "aPRMPaymentProposalPickEditList";
    public static final String PROPERTY_ACTIVEPROPOSALVLIST = "activeProposalVList";
    public static final String PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST = "amortizationLineAccountingDimensionList";
    public static final String PROPERTY_APPROVEDVENDORLIST = "approvedVendorList";
    public static final String PROPERTY_BANKLIST = "bankList";
    public static final String PROPERTY_BUSINESSPARTNERSALESREPRESENTATIVELIST = "businessPartnerSalesRepresentativeList";
    public static final String PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST = "businessPartnerBankAccountList";
    public static final String PROPERTY_BUSINESSPARTNERDISCOUNTLIST = "businessPartnerDiscountList";
    public static final String PROPERTY_BUSINESSPARTNERLOCATIONLIST = "businessPartnerLocationList";
    public static final String PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST = "businessPartnerProductTemplateList";
    public static final String PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST = "businessPartnerWithholdingList";
    public static final String PROPERTY_BPRETENCIONVENTALIST = "bPRETENCIONVENTAList";
    public static final String PROPERTY_BPRETENCIONCOMPRALIST = "bpRetencionCompraList";
    public static final String PROPERTY_RETENCIONCOMPRALIST = "retencionCompraList";
    public static final String PROPERTY_INVOICETAXCASHVATVLIST = "invoiceTaxCashVATVList";
    public static final String PROPERTY_CLIENTINFORMATIONTEMPLATEBPARTNERLIST = "clientInformationTemplateBPartnerList";
    public static final String PROPERTY_CUSTOMERACCOUNTSLIST = "customerAccountsList";
    public static final String PROPERTY_FINACCTRANSACTIONVLIST = "finaccTransactionVList";
    public static final String PROPERTY_DATAIMPORTBANKSTATEMENTLIST = "dataImportBankStatementList";
    public static final String PROPERTY_DATAIMPORTBUDGETLINELIST = "dataImportBudgetLineList";
    public static final String PROPERTY_DATAIMPORTBUSINESSPARTNERLIST = "dataImportBusinessPartnerList";
    public static final String PROPERTY_DATAIMPORTGLJOURNALLIST = "dataImportGLJournalList";
    public static final String PROPERTY_DATAIMPORTINVOICELIST = "dataImportInvoiceList";
    public static final String PROPERTY_DATAIMPORTORDERLIST = "dataImportOrderList";
    public static final String PROPERTY_DATAIMPORTPRODUCTLIST = "dataImportProductList";
    public static final String PROPERTY_EMAILINTERACTIONLIST = "emailInteractionList";
    public static final String PROPERTY_EMPLOYEEACCOUNTSLIST = "employeeAccountsList";
    public static final String PROPERTY_EMPLOYEESALARYCATEGORYLIST = "employeeSalaryCategoryList";
    public static final String PROPERTY_EXTERNALPOSLIST = "externalPOSList";
    public static final String PROPERTY_FINBANKSTATEMENTLINELIST = "fINBankStatementLineList";
    public static final String PROPERTY_FINDOUBTFULDEBTLIST = "fINDoubtfulDebtList";
    public static final String PROPERTY_FINDOUBTFULDEBTRUNLIST = "fINDoubtfulDebtRunList";
    public static final String PROPERTY_FINDOUBTFULDEBTVLIST = "fINDoubtfulDebtVList";
    public static final String PROPERTY_FINFINACCTRANSACTIONLIST = "fINFinaccTransactionList";
    public static final String PROPERTY_FINFINANCIALACCOUNTLIST = "fINFinancialAccountList";
    public static final String PROPERTY_FINPAYMENTLIST = "fINPaymentList";
    public static final String PROPERTY_FINPAYMENTDETAILVLIST = "fINPaymentDetailVList";
    public static final String PROPERTY_FINPAYMENTDETAILVBUSINESSPARTNERDIMLIST = "fINPaymentDetailVBusinessPartnerdimList";
    public static final String PROPERTY_FINPAYMENTPROPDETAILVLIST = "fINPaymentPropDetailVList";
    public static final String PROPERTY_FINPAYMENTPROPOSALLIST = "fINPaymentProposalList";
    public static final String PROPERTY_FINPAYMENTSCHEDULEDETAILLIST = "fINPaymentScheduleDetailList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST = "financialMgmtAccountingCombinationList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST = "financialMgmtAcctSchemaElementList";
    public static final String PROPERTY_FINANCIALMGMTASSETLIST = "financialMgmtAssetList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST = "financialMgmtDebtPaymentList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST = "financialMgmtDebtPaymentCancelVList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST = "financialMgmtDebtPaymentGenerateVList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLIST = "financialMgmtGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";
    public static final String PROPERTY_FINANCIALMGMTTAXPAYMENTLIST = "financialMgmtTaxPaymentList";
    public static final String PROPERTY_FINANCIALMGMTWITHHOLDINGBENEFICIARYLIST = "financialMgmtWithholdingBeneficiaryList";
    public static final String PROPERTY_IDTCONTRATOLIST = "iDTContratoList";
    public static final String PROPERTY_IDTNOVEDADLIST = "iDTNovedadList";
    public static final String PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST = "inOutLineAccountingDimensionList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST = "invoiceLineAccountingDimensionList";
    public static final String PROPERTY_INVOICELINEV2LIST = "invoiceLineV2List";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_MRPPRODUCTIONRUNLIST = "mRPProductionRunList";
    public static final String PROPERTY_MRPPURCHASINGRUNVENDORLIST = "mRPPurchasingRunVendorList";
    public static final String PROPERTY_MRPPURCHASINGRUNLIST = "mRPPurchasingRunList";
    public static final String PROPERTY_MRPPURCHASINGRUNLINELIST = "mRPPurchasingRunLineList";
    public static final String PROPERTY_MRPSALESFORECASTLIST = "mRPSalesForecastList";
    public static final String PROPERTY_MANUFACTURINGMAINTENANCEWORKERLIST = "manufacturingMaintenanceWorkerList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST = "manufacturingProductionRunEmployeeList";
    public static final String PROPERTY_MANUFACTURINGWORKEFFORTEMPLOYEELIST = "manufacturingWorkEffortEmployeeList";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONLIST = "materialMgmtReservationList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST = "materialMgmtShipmentInOutList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST = "materialMgmtShipmentInOutLineList";
    public static final String PROPERTY_REGISTROHORAEXTRALIST = "registroHoraExtraList";
    public static final String PROPERTY_RPTCARGASFAMILIARESLIST = "rptCargasFamiliaresList";
    public static final String PROPERTY_CBEMPLEADOACCTLIST = "cbEmpleadoAcctList";
    public static final String PROPERTY_EMPLEADOINGEGRLIST = "empleadoIngEgrList";
    public static final String PROPERTY_PERMISOLIST = "permisoList";
    public static final String PROPERTY_ROLPAGOPROVISIONLIST = "rolPagoProvisionList";
    public static final String PROPERTY_RECOMISIONDETALLELIST = "reComisionDetalleList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_ORDERDROPSHIPPARTNERLIST = "orderDropShipPartnerList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST = "orderLineAccountingDimensionList";
    public static final String PROPERTY_ORGANIZATIONINFORMATIONLIST = "organizationInformationList";
    public static final String PROPERTY_PRERESERVATIONMANUALPICKEDITLIST = "prereservationManualPickEditList";
    public static final String PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST = "pricingAdjustmentBusinessPartnerList";
    public static final String PROPERTY_PRICINGPRICELISTSCHEMELINELIST = "pricingPriceListSchemeLineList";
    public static final String PROPERTY_PRICINGVOLUMEDISCOUNTBUSINESSPARTNERLIST = "pricingVolumeDiscountBusinessPartnerList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLIST = "procurementRequisitionList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLINELIST = "procurementRequisitionLineList";
    public static final String PROPERTY_PRODUCTLIST = "productList";
    public static final String PROPERTY_PRODUCTCUSTOMERLIST = "productCustomerList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_PROJECTPERSONINCHARGELIST = "projectPersonInChargeList";
    public static final String PROPERTY_PROJECTPROPOSALLIST = "projectProposalList";
    public static final String PROPERTY_PROJECTVENDORLIST = "projectVendorList";
    public static final String PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST = "returnMaterialOrderPickEditLinesList";
    public static final String PROPERTY_SALESCOMMISSIONLIST = "salesCommissionList";
    public static final String PROPERTY_SALESCOMMISSIONLINELIST = "salesCommissionLineList";
    public static final String PROPERTY_SHIPPINGSHIPPINGCOMPANYLIST = "shippingShippingCompanyList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLIST = "timeAndExpenseSheetList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINELIST = "timeAndExpenseSheetLineList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINEVLIST = "timeAndExpenseSheetLineVList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINEVCHARGEDBUSINESSPARTNERLIST = "timeAndExpenseSheetLineVChargedBusinessPartnerList";
    public static final String PROPERTY_TRANSACTIONVLIST = "transactionVList";
    public static final String PROPERTY_VENDORACCOUNTSLIST = "vendorAccountsList";
    public static final String PROPERTY_WAREHOUSESHIPPERLIST = "warehouseShipperList";
    public static final String PROPERTY_ASVPEDIDOINVENTARIOLIST = "asvPedidoInventarioList";
    public static final String PROPERTY_ATECACACTIVOEMPLIST = "atecacActivoEmpList";
    public static final String PROPERTY_ATRDEPCABECERARETENLINELIST = "atrdepCabeceraRetenLineList";
    public static final String PROPERTY_VISTARETENCIONVENTASSALESREPIDLIST = "vistaRetencionVentasSalesrepIDList";
    public static final String PROPERTY_VISTARETENCIONVENTASLIST = "vistaRetencionVentasList";
    public static final String PROPERTY_CARGAEMPLEADOLIST = "cargaEmpleadoList";
    public static final String PROPERTY_CONTRATOEMPLEADOLIST = "contratoEmpleadoList";
    public static final String PROPERTY_GASTOSEMPLEADOLIST = "gastosEmpleadoList";
    public static final String PROPERTY_LIQUIDACIONEMPLEADOLIST = "liquidacionEmpleadoList";
    public static final String PROPERTY_NOVEDADLINEALIST = "novedadLineaList";
    public static final String PROPERTY_PAGOLINELIST = "pagoLineList";
    public static final String PROPERTY_PRESTAMOLIST = "prestamoList";
    public static final String PROPERTY_REGISTRAQUINCLINELIST = "registraQuincLineList";
    public static final String PROPERTY_REGISTROGASTOLIST = "registroGastoList";
    public static final String PROPERTY_SALARIODIGNOLINEALIST = "salarioDignoLineaList";
    public static final String PROPERTY_UTILIDADLINEALIST = "utilidadLineaList";
    public static final String PROPERTY_VACACIONLIST = "vacacionList";

    public BusinessPartner() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_ONETIMETRANSACTION, false);
        setDefaultValue(PROPERTY_POTENTIALCUSTOMER, false);
        setDefaultValue(PROPERTY_VENDOR, false);
        setDefaultValue(PROPERTY_CUSTOMER, true);
        setDefaultValue(PROPERTY_EMPLOYEE, false);
        setDefaultValue(PROPERTY_ISSALESREPRESENTATIVE, false);
        setDefaultValue(PROPERTY_TAXEXEMPT, false);
        setDefaultValue(PROPERTY_PRINTDISCOUNT, false);
        setDefaultValue(PROPERTY_INVOICETERMS, "I");
        setDefaultValue(PROPERTY_PRICESSHOWNINORDER, true);
        setDefaultValue(PROPERTY_INVOICEGROUPING, "000000000000000");
        setDefaultValue(PROPERTY_OPERATOR, false);
        setDefaultValue(PROPERTY_CONSUMPTIONDAYS, (long) 1000);
        setDefaultValue(PROPERTY_CUSTOMERBLOCKING, false);
        setDefaultValue(PROPERTY_VENDORBLOCKING, false);
        setDefaultValue(PROPERTY_PAYMENTIN, false);
        setDefaultValue(PROPERTY_PAYMENTOUT, true);
        setDefaultValue(PROPERTY_SALESINVOICE, true);
        setDefaultValue(PROPERTY_PURCHASEINVOICE, true);
        setDefaultValue(PROPERTY_SALESORDER, true);
        setDefaultValue(PROPERTY_PURCHASEORDER, true);
        setDefaultValue(PROPERTY_GOODSSHIPMENT, true);
        setDefaultValue(PROPERTY_GOODSRECEIPT, false);
        setDefaultValue(PROPERTY_CASHVAT, false);
        setDefaultValue(PROPERTY_NOISDISCAPACITADO, false);
        setDefaultValue(PROPERTY_NOISTERCERAEDAD, false);
        setDefaultValue(PROPERTY_ADUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ACTIVEPROPOSALVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APPROVEDVENDORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERSALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERDISCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERLOCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BPRETENCIONVENTALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BPRETENCIONCOMPRALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETENCIONCOMPRALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICETAXCASHVATVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONTEMPLATEBPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CUSTOMERACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINACCTRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTBANKSTATEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMAILINTERACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMPLOYEEACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMPLOYEESALARYCATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXTERNALPOSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINBANKSTATEMENTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINACCTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTDETAILVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTDETAILVBUSINESSPARTNERDIMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPDETAILVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDULEDETAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTWITHHOLDINGBENEFICIARYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_IDTCONTRATOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_IDTNOVEDADLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNVENDORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPSALESFORECASTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGMAINTENANCEWORKERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGWORKEFFORTEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REGISTROHORAEXTRALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RPTCARGASFAMILIARESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CBEMPLEADOACCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMPLEADOINGEGRLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PERMISOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ROLPAGOPROVISIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RECOMISIONDETALLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERDROPSHIPPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRERESERVATIONMANUALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGPRICELISTSCHEMELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGVOLUMEDISCOUNTBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCUSTOMERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPERSONINCHARGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTVENDORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SHIPPINGSHIPPINGCOMPANYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINEVCHARGEDBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_VENDORACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSESHIPPERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ASVPEDIDOINVENTARIOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ATECACACTIVOEMPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ATRDEPCABECERARETENLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_VISTARETENCIONVENTASSALESREPIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_VISTARETENCIONVENTASLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CARGAEMPLEADOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CONTRATOEMPLEADOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_GASTOSEMPLEADOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LIQUIDACIONEMPLEADOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_NOVEDADLINEALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PAGOLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRESTAMOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REGISTRAQUINCLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REGISTROGASTOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALARIODIGNOLINEALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_UTILIDADLINEALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_VACACIONLIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getName2() {
        return (String) get(PROPERTY_NAME2);
    }

    public void setName2(String name2) {
        set(PROPERTY_NAME2, name2);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public Category getBusinessPartnerCategory() {
        return (Category) get(PROPERTY_BUSINESSPARTNERCATEGORY);
    }

    public void setBusinessPartnerCategory(Category businessPartnerCategory) {
        set(PROPERTY_BUSINESSPARTNERCATEGORY, businessPartnerCategory);
    }

    public Boolean isOneTimeTransaction() {
        return (Boolean) get(PROPERTY_ONETIMETRANSACTION);
    }

    public void setOneTimeTransaction(Boolean oneTimeTransaction) {
        set(PROPERTY_ONETIMETRANSACTION, oneTimeTransaction);
    }

    public Boolean isPotentialCustomer() {
        return (Boolean) get(PROPERTY_POTENTIALCUSTOMER);
    }

    public void setPotentialCustomer(Boolean potentialCustomer) {
        set(PROPERTY_POTENTIALCUSTOMER, potentialCustomer);
    }

    public Boolean isVendor() {
        return (Boolean) get(PROPERTY_VENDOR);
    }

    public void setVendor(Boolean vendor) {
        set(PROPERTY_VENDOR, vendor);
    }

    public Boolean isCustomer() {
        return (Boolean) get(PROPERTY_CUSTOMER);
    }

    public void setCustomer(Boolean customer) {
        set(PROPERTY_CUSTOMER, customer);
    }

    public Boolean isEmployee() {
        return (Boolean) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(Boolean employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Boolean isSalesRepresentative() {
        return (Boolean) get(PROPERTY_ISSALESREPRESENTATIVE);
    }

    public void setSalesRepresentative(Boolean isSalesRepresentative) {
        set(PROPERTY_ISSALESREPRESENTATIVE, isSalesRepresentative);
    }

    public String getReferenceNo() {
        return (String) get(PROPERTY_REFERENCENO);
    }

    public void setReferenceNo(String referenceNo) {
        set(PROPERTY_REFERENCENO, referenceNo);
    }

    public String getDUNS() {
        return (String) get(PROPERTY_DUNS);
    }

    public void setDUNS(String dUNS) {
        set(PROPERTY_DUNS, dUNS);
    }

    public String getURL() {
        return (String) get(PROPERTY_URL);
    }

    public void setURL(String uRL) {
        set(PROPERTY_URL, uRL);
    }

    public Language getLanguage() {
        return (Language) get(PROPERTY_LANGUAGE);
    }

    public void setLanguage(Language language) {
        set(PROPERTY_LANGUAGE, language);
    }

    public String getTaxID() {
        return (String) get(PROPERTY_TAXID);
    }

    public void setTaxID(String taxID) {
        set(PROPERTY_TAXID, taxID);
    }

    public Boolean isTaxExempt() {
        return (Boolean) get(PROPERTY_TAXEXEMPT);
    }

    public void setTaxExempt(Boolean taxExempt) {
        set(PROPERTY_TAXEXEMPT, taxExempt);
    }

    public InvoiceSchedule getInvoiceSchedule() {
        return (InvoiceSchedule) get(PROPERTY_INVOICESCHEDULE);
    }

    public void setInvoiceSchedule(InvoiceSchedule invoiceSchedule) {
        set(PROPERTY_INVOICESCHEDULE, invoiceSchedule);
    }

    public String getValuation() {
        return (String) get(PROPERTY_VALUATION);
    }

    public void setValuation(String valuation) {
        set(PROPERTY_VALUATION, valuation);
    }

    public BigDecimal getVolumeOfSales() {
        return (BigDecimal) get(PROPERTY_VOLUMEOFSALES);
    }

    public void setVolumeOfSales(BigDecimal volumeOfSales) {
        set(PROPERTY_VOLUMEOFSALES, volumeOfSales);
    }

    public Long getNoOfEmployees() {
        return (Long) get(PROPERTY_NOOFEMPLOYEES);
    }

    public void setNoOfEmployees(Long noOfEmployees) {
        set(PROPERTY_NOOFEMPLOYEES, noOfEmployees);
    }

    public String getNAICSSIC() {
        return (String) get(PROPERTY_NAICSSIC);
    }

    public void setNAICSSIC(String nAICSSIC) {
        set(PROPERTY_NAICSSIC, nAICSSIC);
    }

    public Date getDateOfFirstSale() {
        return (Date) get(PROPERTY_DATEOFFIRSTSALE);
    }

    public void setDateOfFirstSale(Date dateOfFirstSale) {
        set(PROPERTY_DATEOFFIRSTSALE, dateOfFirstSale);
    }

    public BigDecimal getAcquisitionCost() {
        return (BigDecimal) get(PROPERTY_ACQUISITIONCOST);
    }

    public void setAcquisitionCost(BigDecimal acquisitionCost) {
        set(PROPERTY_ACQUISITIONCOST, acquisitionCost);
    }

    public BigDecimal getExpectedLifetimeRevenue() {
        return (BigDecimal) get(PROPERTY_EXPECTEDLIFETIMEREVENUE);
    }

    public void setExpectedLifetimeRevenue(BigDecimal expectedLifetimeRevenue) {
        set(PROPERTY_EXPECTEDLIFETIMEREVENUE, expectedLifetimeRevenue);
    }

    public BigDecimal getLifetimeRevenueToDate() {
        return (BigDecimal) get(PROPERTY_LIFETIMEREVENUETODATE);
    }

    public void setLifetimeRevenueToDate(BigDecimal lifetimeRevenueToDate) {
        set(PROPERTY_LIFETIMEREVENUETODATE, lifetimeRevenueToDate);
    }

    public Long getShare() {
        return (Long) get(PROPERTY_SHARE);
    }

    public void setShare(Long share) {
        set(PROPERTY_SHARE, share);
    }

    public String getFormOfPayment() {
        return (String) get(PROPERTY_FORMOFPAYMENT);
    }

    public void setFormOfPayment(String formOfPayment) {
        set(PROPERTY_FORMOFPAYMENT, formOfPayment);
    }

    public BigDecimal getCreditLimit() {
        return (BigDecimal) get(PROPERTY_CREDITLIMIT);
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        set(PROPERTY_CREDITLIMIT, creditLimit);
    }

    public BigDecimal getCreditUsed() {
        return (BigDecimal) get(PROPERTY_CREDITUSED);
    }

    public void setCreditUsed(BigDecimal creditUsed) {
        set(PROPERTY_CREDITUSED, creditUsed);
    }

    public PaymentTerm getPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_PAYMENTTERMS);
    }

    public void setPaymentTerms(PaymentTerm paymentTerms) {
        set(PROPERTY_PAYMENTTERMS, paymentTerms);
    }

    public PriceList getPriceList() {
        return (PriceList) get(PROPERTY_PRICELIST);
    }

    public void setPriceList(PriceList priceList) {
        set(PROPERTY_PRICELIST, priceList);
    }

    public Boolean isPrintDiscount() {
        return (Boolean) get(PROPERTY_PRINTDISCOUNT);
    }

    public void setPrintDiscount(Boolean printDiscount) {
        set(PROPERTY_PRINTDISCOUNT, printDiscount);
    }

    public String getOrderDescription() {
        return (String) get(PROPERTY_ORDERDESCRIPTION);
    }

    public void setOrderDescription(String orderDescription) {
        set(PROPERTY_ORDERDESCRIPTION, orderDescription);
    }

    public String getOrderReference() {
        return (String) get(PROPERTY_ORDERREFERENCE);
    }

    public void setOrderReference(String orderReference) {
        set(PROPERTY_ORDERREFERENCE, orderReference);
    }

    public String getPOFormOfPayment() {
        return (String) get(PROPERTY_POFORMOFPAYMENT);
    }

    public void setPOFormOfPayment(String pOFormOfPayment) {
        set(PROPERTY_POFORMOFPAYMENT, pOFormOfPayment);
    }

    public PriceList getPurchasePricelist() {
        return (PriceList) get(PROPERTY_PURCHASEPRICELIST);
    }

    public void setPurchasePricelist(PriceList purchasePricelist) {
        set(PROPERTY_PURCHASEPRICELIST, purchasePricelist);
    }

    public PaymentTerm getPOPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_POPAYMENTTERMS);
    }

    public void setPOPaymentTerms(PaymentTerm pOPaymentTerms) {
        set(PROPERTY_POPAYMENTTERMS, pOPaymentTerms);
    }

    public Long getNumberOfCopies() {
        return (Long) get(PROPERTY_NUMBEROFCOPIES);
    }

    public void setNumberOfCopies(Long numberOfCopies) {
        set(PROPERTY_NUMBEROFCOPIES, numberOfCopies);
    }

    public Greeting getGreeting() {
        return (Greeting) get(PROPERTY_GREETING);
    }

    public void setGreeting(Greeting greeting) {
        set(PROPERTY_GREETING, greeting);
    }

    public String getInvoiceTerms() {
        return (String) get(PROPERTY_INVOICETERMS);
    }

    public void setInvoiceTerms(String invoiceTerms) {
        set(PROPERTY_INVOICETERMS, invoiceTerms);
    }

    public String getDeliveryTerms() {
        return (String) get(PROPERTY_DELIVERYTERMS);
    }

    public void setDeliveryTerms(String deliveryTerms) {
        set(PROPERTY_DELIVERYTERMS, deliveryTerms);
    }

    public String getDeliveryMethod() {
        return (String) get(PROPERTY_DELIVERYMETHOD);
    }

    public void setDeliveryMethod(String deliveryMethod) {
        set(PROPERTY_DELIVERYMETHOD, deliveryMethod);
    }

    public BusinessPartner getSalesRepresentative() {
        return (BusinessPartner) get(PROPERTY_SALESREPRESENTATIVE);
    }

    public void setSalesRepresentative(BusinessPartner salesRepresentative) {
        set(PROPERTY_SALESREPRESENTATIVE, salesRepresentative);
    }

    public String getPartnerParent() {
        return (String) get(PROPERTY_PARTNERPARENT);
    }

    public void setPartnerParent(String partnerParent) {
        set(PROPERTY_PARTNERPARENT, partnerParent);
    }

    public String getCreditStatus() {
        return (String) get(PROPERTY_CREDITSTATUS);
    }

    public void setCreditStatus(String creditStatus) {
        set(PROPERTY_CREDITSTATUS, creditStatus);
    }

    public Organization getForcedOrg() {
        return (Organization) get(PROPERTY_FORCEDORG);
    }

    public void setForcedOrg(Organization forcedOrg) {
        set(PROPERTY_FORCEDORG, forcedOrg);
    }

    public Boolean isPricesShownInOrder() {
        return (Boolean) get(PROPERTY_PRICESSHOWNINORDER);
    }

    public void setPricesShownInOrder(Boolean pricesShownInOrder) {
        set(PROPERTY_PRICESSHOWNINORDER, pricesShownInOrder);
    }

    public String getInvoiceGrouping() {
        return (String) get(PROPERTY_INVOICEGROUPING);
    }

    public void setInvoiceGrouping(String invoiceGrouping) {
        set(PROPERTY_INVOICEGROUPING, invoiceGrouping);
    }

    public Long getMaturityDate1() {
        return (Long) get(PROPERTY_MATURITYDATE1);
    }

    public void setMaturityDate1(Long maturityDate1) {
        set(PROPERTY_MATURITYDATE1, maturityDate1);
    }

    public Long getMaturityDate2() {
        return (Long) get(PROPERTY_MATURITYDATE2);
    }

    public void setMaturityDate2(Long maturityDate2) {
        set(PROPERTY_MATURITYDATE2, maturityDate2);
    }

    public Long getMaturityDate3() {
        return (Long) get(PROPERTY_MATURITYDATE3);
    }

    public void setMaturityDate3(Long maturityDate3) {
        set(PROPERTY_MATURITYDATE3, maturityDate3);
    }

    public Boolean isOperator() {
        return (Boolean) get(PROPERTY_OPERATOR);
    }

    public void setOperator(Boolean operator) {
        set(PROPERTY_OPERATOR, operator);
    }

    public String getUPCEAN() {
        return (String) get(PROPERTY_UPCEAN);
    }

    public void setUPCEAN(String uPCEAN) {
        set(PROPERTY_UPCEAN, uPCEAN);
    }

    public SalaryCategory getSalaryCategory() {
        return (SalaryCategory) get(PROPERTY_SALARYCATEGORY);
    }

    public void setSalaryCategory(SalaryCategory salaryCategory) {
        set(PROPERTY_SALARYCATEGORY, salaryCategory);
    }

    public String getInvoicePrintformat() {
        return (String) get(PROPERTY_INVOICEPRINTFORMAT);
    }

    public void setInvoicePrintformat(String invoicePrintformat) {
        set(PROPERTY_INVOICEPRINTFORMAT, invoicePrintformat);
    }

    public Long getConsumptionDays() {
        return (Long) get(PROPERTY_CONSUMPTIONDAYS);
    }

    public void setConsumptionDays(Long consumptionDays) {
        set(PROPERTY_CONSUMPTIONDAYS, consumptionDays);
    }

    public BankAccount getBankAccount() {
        return (BankAccount) get(PROPERTY_BANKACCOUNT);
    }

    public void setBankAccount(BankAccount bankAccount) {
        set(PROPERTY_BANKACCOUNT, bankAccount);
    }

    public TaxCategory getTaxCategory() {
        return (TaxCategory) get(PROPERTY_TAXCATEGORY);
    }

    public void setTaxCategory(TaxCategory taxCategory) {
        set(PROPERTY_TAXCATEGORY, taxCategory);
    }

    public Long getPOMaturityDate1() {
        return (Long) get(PROPERTY_POMATURITYDATE1);
    }

    public void setPOMaturityDate1(Long pOMaturityDate1) {
        set(PROPERTY_POMATURITYDATE1, pOMaturityDate1);
    }

    public Long getPOMaturityDate2() {
        return (Long) get(PROPERTY_POMATURITYDATE2);
    }

    public void setPOMaturityDate2(Long pOMaturityDate2) {
        set(PROPERTY_POMATURITYDATE2, pOMaturityDate2);
    }

    public Long getPOMaturityDate3() {
        return (Long) get(PROPERTY_POMATURITYDATE3);
    }

    public void setPOMaturityDate3(Long pOMaturityDate3) {
        set(PROPERTY_POMATURITYDATE3, pOMaturityDate3);
    }

    public BankAccount getTransactionalBankAccount() {
        return (BankAccount) get(PROPERTY_TRANSACTIONALBANKACCOUNT);
    }

    public void setTransactionalBankAccount(BankAccount transactionalBankAccount) {
        set(PROPERTY_TRANSACTIONALBANKACCOUNT, transactionalBankAccount);
    }

    public TaxCategory getSOBPTaxCategory() {
        return (TaxCategory) get(PROPERTY_SOBPTAXCATEGORY);
    }

    public void setSOBPTaxCategory(TaxCategory sOBPTaxCategory) {
        set(PROPERTY_SOBPTAXCATEGORY, sOBPTaxCategory);
    }

    public String getFiscalcode() {
        return (String) get(PROPERTY_FISCALCODE);
    }

    public void setFiscalcode(String fiscalcode) {
        set(PROPERTY_FISCALCODE, fiscalcode);
    }

    public String getIsofiscalcode() {
        return (String) get(PROPERTY_ISOFISCALCODE);
    }

    public void setIsofiscalcode(String isofiscalcode) {
        set(PROPERTY_ISOFISCALCODE, isofiscalcode);
    }

    public Incoterms getIncotermsPO() {
        return (Incoterms) get(PROPERTY_INCOTERMSPO);
    }

    public void setIncotermsPO(Incoterms incotermsPO) {
        set(PROPERTY_INCOTERMSPO, incotermsPO);
    }

    public Incoterms getIncotermsSO() {
        return (Incoterms) get(PROPERTY_INCOTERMSSO);
    }

    public void setIncotermsSO(Incoterms incotermsSO) {
        set(PROPERTY_INCOTERMSSO, incotermsSO);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public FIN_PaymentMethod getPOPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_POPAYMENTMETHOD);
    }

    public void setPOPaymentMethod(FIN_PaymentMethod pOPaymentMethod) {
        set(PROPERTY_POPAYMENTMETHOD, pOPaymentMethod);
    }

    public FIN_FinancialAccount getAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(FIN_FinancialAccount account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public FIN_FinancialAccount getPOFinancialAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_POFINANCIALACCOUNT);
    }

    public void setPOFinancialAccount(FIN_FinancialAccount pOFinancialAccount) {
        set(PROPERTY_POFINANCIALACCOUNT, pOFinancialAccount);
    }

    public Boolean isCustomerBlocking() {
        return (Boolean) get(PROPERTY_CUSTOMERBLOCKING);
    }

    public void setCustomerBlocking(Boolean customerBlocking) {
        set(PROPERTY_CUSTOMERBLOCKING, customerBlocking);
    }

    public Boolean isVendorBlocking() {
        return (Boolean) get(PROPERTY_VENDORBLOCKING);
    }

    public void setVendorBlocking(Boolean vendorBlocking) {
        set(PROPERTY_VENDORBLOCKING, vendorBlocking);
    }

    public String getCoTipocontrib() {
        return (String) get(PROPERTY_COTIPOCONTRIB);
    }

    public void setCoTipocontrib(String coTipocontrib) {
        set(PROPERTY_COTIPOCONTRIB, coTipocontrib);
    }

    public Boolean isPaymentIn() {
        return (Boolean) get(PROPERTY_PAYMENTIN);
    }

    public void setPaymentIn(Boolean paymentIn) {
        set(PROPERTY_PAYMENTIN, paymentIn);
    }

    public String getNoEstadocivil() {
        return (String) get(PROPERTY_NOESTADOCIVIL);
    }

    public void setNoEstadocivil(String noEstadocivil) {
        set(PROPERTY_NOESTADOCIVIL, noEstadocivil);
    }

    public Boolean isPaymentOut() {
        return (Boolean) get(PROPERTY_PAYMENTOUT);
    }

    public void setPaymentOut(Boolean paymentOut) {
        set(PROPERTY_PAYMENTOUT, paymentOut);
    }

    public String getNoGenero() {
        return (String) get(PROPERTY_NOGENERO);
    }

    public void setNoGenero(String noGenero) {
        set(PROPERTY_NOGENERO, noGenero);
    }

    public Boolean isSalesInvoice() {
        return (Boolean) get(PROPERTY_SALESINVOICE);
    }

    public void setSalesInvoice(Boolean salesInvoice) {
        set(PROPERTY_SALESINVOICE, salesInvoice);
    }

    public Date getNoFechanacimiento() {
        return (Date) get(PROPERTY_NOFECHANACIMIENTO);
    }

    public void setNoFechanacimiento(Date noFechanacimiento) {
        set(PROPERTY_NOFECHANACIMIENTO, noFechanacimiento);
    }

    public Boolean isPurchaseInvoice() {
        return (Boolean) get(PROPERTY_PURCHASEINVOICE);
    }

    public void setPurchaseInvoice(Boolean purchaseInvoice) {
        set(PROPERTY_PURCHASEINVOICE, purchaseInvoice);
    }

    public noAreaEmpresa getNoAreaEmpresa() {
        return (noAreaEmpresa) get(PROPERTY_NOAREAEMPRESA);
    }

    public void setNoAreaEmpresa(noAreaEmpresa noAreaEmpresa) {
        set(PROPERTY_NOAREAEMPRESA, noAreaEmpresa);
    }

    public Boolean isSalesOrder() {
        return (Boolean) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(Boolean salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public Boolean isPurchaseOrder() {
        return (Boolean) get(PROPERTY_PURCHASEORDER);
    }

    public void setPurchaseOrder(Boolean purchaseOrder) {
        set(PROPERTY_PURCHASEORDER, purchaseOrder);
    }

    public Boolean isGoodsShipment() {
        return (Boolean) get(PROPERTY_GOODSSHIPMENT);
    }

    public void setGoodsShipment(Boolean goodsShipment) {
        set(PROPERTY_GOODSSHIPMENT, goodsShipment);
    }

    public Boolean isGoodsReceipt() {
        return (Boolean) get(PROPERTY_GOODSRECEIPT);
    }

    public void setGoodsReceipt(Boolean goodsReceipt) {
        set(PROPERTY_GOODSRECEIPT, goodsReceipt);
    }

    public Boolean isCashVAT() {
        return (Boolean) get(PROPERTY_CASHVAT);
    }

    public void setCashVAT(Boolean cashVAT) {
        set(PROPERTY_CASHVAT, cashVAT);
    }

    public String getNoMotivoSalida() {
        return (String) get(PROPERTY_NOMOTIVOSALIDA);
    }

    public void setNoMotivoSalida(String noMotivoSalida) {
        set(PROPERTY_NOMOTIVOSALIDA, noMotivoSalida);
    }

    public String getCoEmail() {
        return (String) get(PROPERTY_COEMAIL);
    }

    public void setCoEmail(String coEmail) {
        set(PROPERTY_COEMAIL, coEmail);
    }

    public String getCoNivEdu() {
        return (String) get(PROPERTY_CONIVEDU);
    }

    public void setCoNivEdu(String coNivEdu) {
        set(PROPERTY_CONIVEDU, coNivEdu);
    }

    public Boolean isNoIsdiscapacitado() {
        return (Boolean) get(PROPERTY_NOISDISCAPACITADO);
    }

    public void setNoIsdiscapacitado(Boolean noIsdiscapacitado) {
        set(PROPERTY_NOISDISCAPACITADO, noIsdiscapacitado);
    }

    public String getCoVivienda() {
        return (String) get(PROPERTY_COVIVIENDA);
    }

    public void setCoVivienda(String coVivienda) {
        set(PROPERTY_COVIVIENDA, coVivienda);
    }

    public Boolean isNoIsterceraEdad() {
        return (Boolean) get(PROPERTY_NOISTERCERAEDAD);
    }

    public void setNoIsterceraEdad(Boolean noIsterceraEdad) {
        set(PROPERTY_NOISTERCERAEDAD, noIsterceraEdad);
    }

    public Long getCoAniosRes() {
        return (Long) get(PROPERTY_COANIOSRES);
    }

    public void setCoAniosRes(Long coAniosRes) {
        set(PROPERTY_COANIOSRES, coAniosRes);
    }

    public Long getCoMesesRes() {
        return (Long) get(PROPERTY_COMESESRES);
    }

    public void setCoMesesRes(Long coMesesRes) {
        set(PROPERTY_COMESESRES, coMesesRes);
    }

    public Long getCoTotalMeses() {
        return (Long) get(PROPERTY_COTOTALMESES);
    }

    public void setCoTotalMeses(Long coTotalMeses) {
        set(PROPERTY_COTOTALMESES, coTotalMeses);
    }

    public Date getCoFechanac() {
        return (Date) get(PROPERTY_COFECHANAC);
    }

    public void setCoFechanac(Date coFechanac) {
        set(PROPERTY_COFECHANAC, coFechanac);
    }

    public City getCoCiudadnac() {
        return (City) get(PROPERTY_COCIUDADNAC);
    }

    public void setCoCiudadnac(City coCiudadnac) {
        set(PROPERTY_COCIUDADNAC, coCiudadnac);
    }

    public Country getCoNacionalidad() {
        return (Country) get(PROPERTY_CONACIONALIDAD);
    }

    public void setCoNacionalidad(Country coNacionalidad) {
        set(PROPERTY_CONACIONALIDAD, coNacionalidad);
    }

    public String getCoNombres() {
        return (String) get(PROPERTY_CONOMBRES);
    }

    public void setCoNombres(String coNombres) {
        set(PROPERTY_CONOMBRES, coNombres);
    }

    public String getCoApellidos() {
        return (String) get(PROPERTY_COAPELLIDOS);
    }

    public void setCoApellidos(String coApellidos) {
        set(PROPERTY_COAPELLIDOS, coApellidos);
    }

    public Long getCoComTarjCred() {
        return (Long) get(PROPERTY_COCOMTARJCRED);
    }

    public void setCoComTarjCred(Long coComTarjCred) {
        set(PROPERTY_COCOMTARJCRED, coComTarjCred);
    }

    public String getCoNaturalJuridico() {
        return (String) get(PROPERTY_CONATURALJURIDICO);
    }

    public void setCoNaturalJuridico(String coNaturalJuridico) {
        set(PROPERTY_CONATURALJURIDICO, coNaturalJuridico);
    }

    public Date getCoBpFecVenctAutFcSri() {
        return (Date) get(PROPERTY_COBPFECVENCTAUTFCSRI);
    }

    public void setCoBpFecVenctAutFcSri(Date coBpFecVenctAutFcSri) {
        set(PROPERTY_COBPFECVENCTAUTFCSRI, coBpFecVenctAutFcSri);
    }

    public Date getCoBpFecVenctAutRtSri() {
        return (Date) get(PROPERTY_COBPFECVENCTAUTRTSRI);
    }

    public void setCoBpFecVenctAutRtSri(Date coBpFecVenctAutRtSri) {
        set(PROPERTY_COBPFECVENCTAUTRTSRI, coBpFecVenctAutRtSri);
    }

    public String getCoBpNroAutFcSri() {
        return (String) get(PROPERTY_COBPNROAUTFCSRI);
    }

    public void setCoBpNroAutFcSri(String coBpNroAutFcSri) {
        set(PROPERTY_COBPNROAUTFCSRI, coBpNroAutFcSri);
    }

    public String getCoBpNroAutRtSri() {
        return (String) get(PROPERTY_COBPNROAUTRTSRI);
    }

    public void setCoBpNroAutRtSri(String coBpNroAutRtSri) {
        set(PROPERTY_COBPNROAUTRTSRI, coBpNroAutRtSri);
    }

    public String getCoBpNroEstab() {
        return (String) get(PROPERTY_COBPNROESTAB);
    }

    public void setCoBpNroEstab(String coBpNroEstab) {
        set(PROPERTY_COBPNROESTAB, coBpNroEstab);
    }

    public String getCoBpPuntoEmision() {
        return (String) get(PROPERTY_COBPPUNTOEMISION);
    }

    public void setCoBpPuntoEmision(String coBpPuntoEmision) {
        set(PROPERTY_COBPPUNTOEMISION, coBpPuntoEmision);
    }

    public String getCOTipoIdentificacion() {
        return (String) get(PROPERTY_COTIPOIDENTIFICACION);
    }

    public void setCOTipoIdentificacion(String cOTipoIdentificacion) {
        set(PROPERTY_COTIPOIDENTIFICACION, cOTipoIdentificacion);
    }

    public String getCobCalificacion() {
        return (String) get(PROPERTY_COBCALIFICACION);
    }

    public void setCobCalificacion(String cobCalificacion) {
        set(PROPERTY_COBCALIFICACION, cobCalificacion);
    }

    public AcctSchema getNeCAcctschema() {
        return (AcctSchema) get(PROPERTY_NECACCTSCHEMA);
    }

    public void setNeCAcctschema(AcctSchema neCAcctschema) {
        set(PROPERTY_NECACCTSCHEMA, neCAcctschema);
    }

    public BigDecimal getNeEdad() {
        return (BigDecimal) get(PROPERTY_NEEDAD);
    }

    public void setNeEdad(BigDecimal neEdad) {
        set(PROPERTY_NEEDAD, neEdad);
    }

    public String getNeNumCarDiscapacitado() {
        return (String) get(PROPERTY_NENUMCARDISCAPACITADO);
    }

    public void setNeNumCarDiscapacitado(String neNumCarDiscapacitado) {
        set(PROPERTY_NENUMCARDISCAPACITADO, neNumCarDiscapacitado);
    }

    public nePerfilRubro getNePerfilRubro() {
        return (nePerfilRubro) get(PROPERTY_NEPERFILRUBRO);
    }

    public void setNePerfilRubro(nePerfilRubro nePerfilRubro) {
        set(PROPERTY_NEPERFILRUBRO, nePerfilRubro);
    }

    public Date getNoFechaDeIngreso() {
        return (Date) get(PROPERTY_NOFECHADEINGRESO);
    }

    public void setNoFechaDeIngreso(Date noFechaDeIngreso) {
        set(PROPERTY_NOFECHADEINGRESO, noFechaDeIngreso);
    }

    public Date getNoFechaDeSalida() {
        return (Date) get(PROPERTY_NOFECHADESALIDA);
    }

    public void setNoFechaDeSalida(Date noFechaDeSalida) {
        set(PROPERTY_NOFECHADESALIDA, noFechaDeSalida);
    }

    public AccountingCombination getNoPagoAcct() {
        return (AccountingCombination) get(PROPERTY_NOPAGOACCT);
    }

    public void setNoPagoAcct(AccountingCombination noPagoAcct) {
        set(PROPERTY_NOPAGOACCT, noPagoAcct);
    }

    public String getNoSisSalNet() {
        return (String) get(PROPERTY_NOSISSALNET);
    }

    public void setNoSisSalNet(String noSisSalNet) {
        set(PROPERTY_NOSISSALNET, noSisSalNet);
    }

    @SuppressWarnings("unchecked")
    public List<User> getADUserList() {
      return (List<User>) get(PROPERTY_ADUSERLIST);
    }

    public void setADUserList(List<User> aDUserList) {
        set(PROPERTY_ADUSERLIST, aDUserList);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
      return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_FinaccTransactionV> getAPRMFinaccTransactionVList() {
      return (List<APRM_FinaccTransactionV>) get(PROPERTY_APRMFINACCTRANSACTIONVLIST);
    }

    public void setAPRMFinaccTransactionVList(List<APRM_FinaccTransactionV> aPRMFinaccTransactionVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONVLIST, aPRMFinaccTransactionVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
      return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_PaymentProposalPickEdit> getAPRMPaymentProposalPickEditList() {
      return (List<APRM_PaymentProposalPickEdit>) get(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST);
    }

    public void setAPRMPaymentProposalPickEditList(List<APRM_PaymentProposalPickEdit> aPRMPaymentProposalPickEditList) {
        set(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, aPRMPaymentProposalPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<ActiveProposal> getActiveProposalVList() {
      return (List<ActiveProposal>) get(PROPERTY_ACTIVEPROPOSALVLIST);
    }

    public void setActiveProposalVList(List<ActiveProposal> activeProposalVList) {
        set(PROPERTY_ACTIVEPROPOSALVLIST, activeProposalVList);
    }

    @SuppressWarnings("unchecked")
    public List<AmortizationLineAccountingDimension> getAmortizationLineAccountingDimensionList() {
      return (List<AmortizationLineAccountingDimension>) get(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setAmortizationLineAccountingDimensionList(List<AmortizationLineAccountingDimension> amortizationLineAccountingDimensionList) {
        set(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST, amortizationLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<ApprovedVendor> getApprovedVendorList() {
      return (List<ApprovedVendor>) get(PROPERTY_APPROVEDVENDORLIST);
    }

    public void setApprovedVendorList(List<ApprovedVendor> approvedVendorList) {
        set(PROPERTY_APPROVEDVENDORLIST, approvedVendorList);
    }

    @SuppressWarnings("unchecked")
    public List<Bank> getBankList() {
      return (List<Bank>) get(PROPERTY_BANKLIST);
    }

    public void setBankList(List<Bank> bankList) {
        set(PROPERTY_BANKLIST, bankList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerSalesRepresentativeList() {
      return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERSALESREPRESENTATIVELIST);
    }

    public void setBusinessPartnerSalesRepresentativeList(List<BusinessPartner> businessPartnerSalesRepresentativeList) {
        set(PROPERTY_BUSINESSPARTNERSALESREPRESENTATIVELIST, businessPartnerSalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.businesspartner.BankAccount> getBusinessPartnerBankAccountList() {
      return (List<org.openbravo.model.common.businesspartner.BankAccount>) get(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST);
    }

    public void setBusinessPartnerBankAccountList(List<org.openbravo.model.common.businesspartner.BankAccount> businessPartnerBankAccountList) {
        set(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, businessPartnerBankAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<Discount> getBusinessPartnerDiscountList() {
      return (List<Discount>) get(PROPERTY_BUSINESSPARTNERDISCOUNTLIST);
    }

    public void setBusinessPartnerDiscountList(List<Discount> businessPartnerDiscountList) {
        set(PROPERTY_BUSINESSPARTNERDISCOUNTLIST, businessPartnerDiscountList);
    }

    @SuppressWarnings("unchecked")
    public List<Location> getBusinessPartnerLocationList() {
      return (List<Location>) get(PROPERTY_BUSINESSPARTNERLOCATIONLIST);
    }

    public void setBusinessPartnerLocationList(List<Location> businessPartnerLocationList) {
        set(PROPERTY_BUSINESSPARTNERLOCATIONLIST, businessPartnerLocationList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductTemplate> getBusinessPartnerProductTemplateList() {
      return (List<ProductTemplate>) get(PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST);
    }

    public void setBusinessPartnerProductTemplateList(List<ProductTemplate> businessPartnerProductTemplateList) {
        set(PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST, businessPartnerProductTemplateList);
    }

    @SuppressWarnings("unchecked")
    public List<Withholding> getBusinessPartnerWithholdingList() {
      return (List<Withholding>) get(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST);
    }

    public void setBusinessPartnerWithholdingList(List<Withholding> businessPartnerWithholdingList) {
        set(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST, businessPartnerWithholdingList);
    }

    @SuppressWarnings("unchecked")
    public List<BpRetencionVenta> getBPRETENCIONVENTAList() {
      return (List<BpRetencionVenta>) get(PROPERTY_BPRETENCIONVENTALIST);
    }

    public void setBPRETENCIONVENTAList(List<BpRetencionVenta> bPRETENCIONVENTAList) {
        set(PROPERTY_BPRETENCIONVENTALIST, bPRETENCIONVENTAList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_BpRetencionCompra> getBpRetencionCompraList() {
      return (List<CO_BpRetencionCompra>) get(PROPERTY_BPRETENCIONCOMPRALIST);
    }

    public void setBpRetencionCompraList(List<CO_BpRetencionCompra> bpRetencionCompraList) {
        set(PROPERTY_BPRETENCIONCOMPRALIST, bpRetencionCompraList);
    }

    @SuppressWarnings("unchecked")
    public List<CO_Retencion_Compra> getRetencionCompraList() {
      return (List<CO_Retencion_Compra>) get(PROPERTY_RETENCIONCOMPRALIST);
    }

    public void setRetencionCompraList(List<CO_Retencion_Compra> retencionCompraList) {
        set(PROPERTY_RETENCIONCOMPRALIST, retencionCompraList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceTaxCashVAT_V> getInvoiceTaxCashVATVList() {
      return (List<InvoiceTaxCashVAT_V>) get(PROPERTY_INVOICETAXCASHVATVLIST);
    }

    public void setInvoiceTaxCashVATVList(List<InvoiceTaxCashVAT_V> invoiceTaxCashVATVList) {
        set(PROPERTY_INVOICETAXCASHVATVLIST, invoiceTaxCashVATVList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationTemplateBPartnerList() {
      return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONTEMPLATEBPARTNERLIST);
    }

    public void setClientInformationTemplateBPartnerList(List<ClientInformation> clientInformationTemplateBPartnerList) {
        set(PROPERTY_CLIENTINFORMATIONTEMPLATEBPARTNERLIST, clientInformationTemplateBPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<CustomerAccounts> getCustomerAccountsList() {
      return (List<CustomerAccounts>) get(PROPERTY_CUSTOMERACCOUNTSLIST);
    }

    public void setCustomerAccountsList(List<CustomerAccounts> customerAccountsList) {
        set(PROPERTY_CUSTOMERACCOUNTSLIST, customerAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<DP_FinaccTransactionV> getFinaccTransactionVList() {
      return (List<DP_FinaccTransactionV>) get(PROPERTY_FINACCTRANSACTIONVLIST);
    }

    public void setFinaccTransactionVList(List<DP_FinaccTransactionV> finaccTransactionVList) {
        set(PROPERTY_FINACCTRANSACTIONVLIST, finaccTransactionVList);
    }

    @SuppressWarnings("unchecked")
    public List<BankStatement> getDataImportBankStatementList() {
      return (List<BankStatement>) get(PROPERTY_DATAIMPORTBANKSTATEMENTLIST);
    }

    public void setDataImportBankStatementList(List<BankStatement> dataImportBankStatementList) {
        set(PROPERTY_DATAIMPORTBANKSTATEMENTLIST, dataImportBankStatementList);
    }

    @SuppressWarnings("unchecked")
    public List<BudgetLine> getDataImportBudgetLineList() {
      return (List<BudgetLine>) get(PROPERTY_DATAIMPORTBUDGETLINELIST);
    }

    public void setDataImportBudgetLineList(List<BudgetLine> dataImportBudgetLineList) {
        set(PROPERTY_DATAIMPORTBUDGETLINELIST, dataImportBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.dataimport.BusinessPartner> getDataImportBusinessPartnerList() {
      return (List<org.openbravo.model.dataimport.BusinessPartner>) get(PROPERTY_DATAIMPORTBUSINESSPARTNERLIST);
    }

    public void setDataImportBusinessPartnerList(List<org.openbravo.model.dataimport.BusinessPartner> dataImportBusinessPartnerList) {
        set(PROPERTY_DATAIMPORTBUSINESSPARTNERLIST, dataImportBusinessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getDataImportGLJournalList() {
      return (List<GLJournal>) get(PROPERTY_DATAIMPORTGLJOURNALLIST);
    }

    public void setDataImportGLJournalList(List<GLJournal> dataImportGLJournalList) {
        set(PROPERTY_DATAIMPORTGLJOURNALLIST, dataImportGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getDataImportInvoiceList() {
      return (List<Invoice>) get(PROPERTY_DATAIMPORTINVOICELIST);
    }

    public void setDataImportInvoiceList(List<Invoice> dataImportInvoiceList) {
        set(PROPERTY_DATAIMPORTINVOICELIST, dataImportInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getDataImportOrderList() {
      return (List<Order>) get(PROPERTY_DATAIMPORTORDERLIST);
    }

    public void setDataImportOrderList(List<Order> dataImportOrderList) {
        set(PROPERTY_DATAIMPORTORDERLIST, dataImportOrderList);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getDataImportProductList() {
      return (List<Product>) get(PROPERTY_DATAIMPORTPRODUCTLIST);
    }

    public void setDataImportProductList(List<Product> dataImportProductList) {
        set(PROPERTY_DATAIMPORTPRODUCTLIST, dataImportProductList);
    }

    @SuppressWarnings("unchecked")
    public List<EmailInteraction> getEmailInteractionList() {
      return (List<EmailInteraction>) get(PROPERTY_EMAILINTERACTIONLIST);
    }

    public void setEmailInteractionList(List<EmailInteraction> emailInteractionList) {
        set(PROPERTY_EMAILINTERACTIONLIST, emailInteractionList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeAccounts> getEmployeeAccountsList() {
      return (List<EmployeeAccounts>) get(PROPERTY_EMPLOYEEACCOUNTSLIST);
    }

    public void setEmployeeAccountsList(List<EmployeeAccounts> employeeAccountsList) {
        set(PROPERTY_EMPLOYEEACCOUNTSLIST, employeeAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeSalaryCategory> getEmployeeSalaryCategoryList() {
      return (List<EmployeeSalaryCategory>) get(PROPERTY_EMPLOYEESALARYCATEGORYLIST);
    }

    public void setEmployeeSalaryCategoryList(List<EmployeeSalaryCategory> employeeSalaryCategoryList) {
        set(PROPERTY_EMPLOYEESALARYCATEGORYLIST, employeeSalaryCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<ExternalPOS> getExternalPOSList() {
      return (List<ExternalPOS>) get(PROPERTY_EXTERNALPOSLIST);
    }

    public void setExternalPOSList(List<ExternalPOS> externalPOSList) {
        set(PROPERTY_EXTERNALPOSLIST, externalPOSList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_BankStatementLine> getFINBankStatementLineList() {
      return (List<FIN_BankStatementLine>) get(PROPERTY_FINBANKSTATEMENTLINELIST);
    }

    public void setFINBankStatementLineList(List<FIN_BankStatementLine> fINBankStatementLineList) {
        set(PROPERTY_FINBANKSTATEMENTLINELIST, fINBankStatementLineList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebt> getFINDoubtfulDebtList() {
      return (List<DoubtfulDebt>) get(PROPERTY_FINDOUBTFULDEBTLIST);
    }

    public void setFINDoubtfulDebtList(List<DoubtfulDebt> fINDoubtfulDebtList) {
        set(PROPERTY_FINDOUBTFULDEBTLIST, fINDoubtfulDebtList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebtRun> getFINDoubtfulDebtRunList() {
      return (List<DoubtfulDebtRun>) get(PROPERTY_FINDOUBTFULDEBTRUNLIST);
    }

    public void setFINDoubtfulDebtRunList(List<DoubtfulDebtRun> fINDoubtfulDebtRunList) {
        set(PROPERTY_FINDOUBTFULDEBTRUNLIST, fINDoubtfulDebtRunList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebtV> getFINDoubtfulDebtVList() {
      return (List<DoubtfulDebtV>) get(PROPERTY_FINDOUBTFULDEBTVLIST);
    }

    public void setFINDoubtfulDebtVList(List<DoubtfulDebtV> fINDoubtfulDebtVList) {
        set(PROPERTY_FINDOUBTFULDEBTVLIST, fINDoubtfulDebtVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinaccTransaction> getFINFinaccTransactionList() {
      return (List<FIN_FinaccTransaction>) get(PROPERTY_FINFINACCTRANSACTIONLIST);
    }

    public void setFINFinaccTransactionList(List<FIN_FinaccTransaction> fINFinaccTransactionList) {
        set(PROPERTY_FINFINACCTRANSACTIONLIST, fINFinaccTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccount> getFINFinancialAccountList() {
      return (List<FIN_FinancialAccount>) get(PROPERTY_FINFINANCIALACCOUNTLIST);
    }

    public void setFINFinancialAccountList(List<FIN_FinancialAccount> fINFinancialAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTLIST, fINFinancialAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_Payment> getFINPaymentList() {
      return (List<FIN_Payment>) get(PROPERTY_FINPAYMENTLIST);
    }

    public void setFINPaymentList(List<FIN_Payment> fINPaymentList) {
        set(PROPERTY_FINPAYMENTLIST, fINPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentDetailV> getFINPaymentDetailVList() {
      return (List<FIN_PaymentDetailV>) get(PROPERTY_FINPAYMENTDETAILVLIST);
    }

    public void setFINPaymentDetailVList(List<FIN_PaymentDetailV> fINPaymentDetailVList) {
        set(PROPERTY_FINPAYMENTDETAILVLIST, fINPaymentDetailVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentDetailV> getFINPaymentDetailVBusinessPartnerdimList() {
      return (List<FIN_PaymentDetailV>) get(PROPERTY_FINPAYMENTDETAILVBUSINESSPARTNERDIMLIST);
    }

    public void setFINPaymentDetailVBusinessPartnerdimList(List<FIN_PaymentDetailV> fINPaymentDetailVBusinessPartnerdimList) {
        set(PROPERTY_FINPAYMENTDETAILVBUSINESSPARTNERDIMLIST, fINPaymentDetailVBusinessPartnerdimList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentPropDetailV> getFINPaymentPropDetailVList() {
      return (List<FIN_PaymentPropDetailV>) get(PROPERTY_FINPAYMENTPROPDETAILVLIST);
    }

    public void setFINPaymentPropDetailVList(List<FIN_PaymentPropDetailV> fINPaymentPropDetailVList) {
        set(PROPERTY_FINPAYMENTPROPDETAILVLIST, fINPaymentPropDetailVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentProposal> getFINPaymentProposalList() {
      return (List<FIN_PaymentProposal>) get(PROPERTY_FINPAYMENTPROPOSALLIST);
    }

    public void setFINPaymentProposalList(List<FIN_PaymentProposal> fINPaymentProposalList) {
        set(PROPERTY_FINPAYMENTPROPOSALLIST, fINPaymentProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentScheduleDetail> getFINPaymentScheduleDetailList() {
      return (List<FIN_PaymentScheduleDetail>) get(PROPERTY_FINPAYMENTSCHEDULEDETAILLIST);
    }

    public void setFINPaymentScheduleDetailList(List<FIN_PaymentScheduleDetail> fINPaymentScheduleDetailList) {
        set(PROPERTY_FINPAYMENTSCHEDULEDETAILLIST, fINPaymentScheduleDetailList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingCombination> getFinancialMgmtAccountingCombinationList() {
      return (List<AccountingCombination>) get(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST);
    }

    public void setFinancialMgmtAccountingCombinationList(List<AccountingCombination> financialMgmtAccountingCombinationList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST, financialMgmtAccountingCombinationList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
      return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaElement> getFinancialMgmtAcctSchemaElementList() {
      return (List<AcctSchemaElement>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaElementList(List<AcctSchemaElement> financialMgmtAcctSchemaElementList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, financialMgmtAcctSchemaElementList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetList() {
      return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETLIST);
    }

    public void setFinancialMgmtAssetList(List<Asset> financialMgmtAssetList) {
        set(PROPERTY_FINANCIALMGMTASSETLIST, financialMgmtAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.financialmgmt.accounting.BudgetLine> getFinancialMgmtBudgetLineList() {
      return (List<org.openbravo.model.financialmgmt.accounting.BudgetLine>) get(PROPERTY_FINANCIALMGMTBUDGETLINELIST);
    }

    public void setFinancialMgmtBudgetLineList(List<org.openbravo.model.financialmgmt.accounting.BudgetLine> financialMgmtBudgetLineList) {
        set(PROPERTY_FINANCIALMGMTBUDGETLINELIST, financialMgmtBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPayment> getFinancialMgmtDebtPaymentList() {
      return (List<DebtPayment>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST);
    }

    public void setFinancialMgmtDebtPaymentList(List<DebtPayment> financialMgmtDebtPaymentList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, financialMgmtDebtPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentCancelV> getFinancialMgmtDebtPaymentCancelVList() {
      return (List<DebtPaymentCancelV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST);
    }

    public void setFinancialMgmtDebtPaymentCancelVList(List<DebtPaymentCancelV> financialMgmtDebtPaymentCancelVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, financialMgmtDebtPaymentCancelVList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentGenerateV> getFinancialMgmtDebtPaymentGenerateVList() {
      return (List<DebtPaymentGenerateV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST);
    }

    public void setFinancialMgmtDebtPaymentGenerateVList(List<DebtPaymentGenerateV> financialMgmtDebtPaymentGenerateVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, financialMgmtDebtPaymentGenerateVList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.financialmgmt.gl.GLJournal> getFinancialMgmtGLJournalList() {
      return (List<org.openbravo.model.financialmgmt.gl.GLJournal>) get(PROPERTY_FINANCIALMGMTGLJOURNALLIST);
    }

    public void setFinancialMgmtGLJournalList(List<org.openbravo.model.financialmgmt.gl.GLJournal> financialMgmtGLJournalList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLIST, financialMgmtGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournalLine> getFinancialMgmtGLJournalLineList() {
      return (List<GLJournalLine>) get(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST);
    }

    public void setFinancialMgmtGLJournalLineList(List<GLJournalLine> financialMgmtGLJournalLineList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, financialMgmtGLJournalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxPayment> getFinancialMgmtTaxPaymentList() {
      return (List<TaxPayment>) get(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST);
    }

    public void setFinancialMgmtTaxPaymentList(List<TaxPayment> financialMgmtTaxPaymentList) {
        set(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, financialMgmtTaxPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.financialmgmt.tax.Withholding> getFinancialMgmtWithholdingBeneficiaryList() {
      return (List<org.openbravo.model.financialmgmt.tax.Withholding>) get(PROPERTY_FINANCIALMGMTWITHHOLDINGBENEFICIARYLIST);
    }

    public void setFinancialMgmtWithholdingBeneficiaryList(List<org.openbravo.model.financialmgmt.tax.Withholding> financialMgmtWithholdingBeneficiaryList) {
        set(PROPERTY_FINANCIALMGMTWITHHOLDINGBENEFICIARYLIST, financialMgmtWithholdingBeneficiaryList);
    }

    @SuppressWarnings("unchecked")
    public List<IdtContrato> getIDTContratoList() {
      return (List<IdtContrato>) get(PROPERTY_IDTCONTRATOLIST);
    }

    public void setIDTContratoList(List<IdtContrato> iDTContratoList) {
        set(PROPERTY_IDTCONTRATOLIST, iDTContratoList);
    }

    @SuppressWarnings("unchecked")
    public List<IdtImportacionDatos> getIDTNovedadList() {
      return (List<IdtImportacionDatos>) get(PROPERTY_IDTNOVEDADLIST);
    }

    public void setIDTNovedadList(List<IdtImportacionDatos> iDTNovedadList) {
        set(PROPERTY_IDTNOVEDADLIST, iDTNovedadList);
    }

    @SuppressWarnings("unchecked")
    public List<InOutLineAccountingDimension> getInOutLineAccountingDimensionList() {
      return (List<InOutLineAccountingDimension>) get(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setInOutLineAccountingDimensionList(List<InOutLineAccountingDimension> inOutLineAccountingDimensionList) {
        set(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST, inOutLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.invoice.Invoice> getInvoiceList() {
      return (List<org.openbravo.model.common.invoice.Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<org.openbravo.model.common.invoice.Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
      return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineAccountingDimension> getInvoiceLineAccountingDimensionList() {
      return (List<InvoiceLineAccountingDimension>) get(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST);
    }

    public void setInvoiceLineAccountingDimensionList(List<InvoiceLineAccountingDimension> invoiceLineAccountingDimensionList) {
        set(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, invoiceLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineV2> getInvoiceLineV2List() {
      return (List<InvoiceLineV2>) get(PROPERTY_INVOICELINEV2LIST);
    }

    public void setInvoiceLineV2List(List<InvoiceLineV2> invoiceLineV2List) {
        set(PROPERTY_INVOICELINEV2LIST, invoiceLineV2List);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2List() {
      return (List<InvoiceV2>) get(PROPERTY_INVOICEV2LIST);
    }

    public void setInvoiceV2List(List<InvoiceV2> invoiceV2List) {
        set(PROPERTY_INVOICEV2LIST, invoiceV2List);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRun> getMRPProductionRunList() {
      return (List<ProductionRun>) get(PROPERTY_MRPPRODUCTIONRUNLIST);
    }

    public void setMRPProductionRunList(List<ProductionRun> mRPProductionRunList) {
        set(PROPERTY_MRPPRODUCTIONRUNLIST, mRPProductionRunList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRun> getMRPPurchasingRunVendorList() {
      return (List<PurchasingRun>) get(PROPERTY_MRPPURCHASINGRUNVENDORLIST);
    }

    public void setMRPPurchasingRunVendorList(List<PurchasingRun> mRPPurchasingRunVendorList) {
        set(PROPERTY_MRPPURCHASINGRUNVENDORLIST, mRPPurchasingRunVendorList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRun> getMRPPurchasingRunList() {
      return (List<PurchasingRun>) get(PROPERTY_MRPPURCHASINGRUNLIST);
    }

    public void setMRPPurchasingRunList(List<PurchasingRun> mRPPurchasingRunList) {
        set(PROPERTY_MRPPURCHASINGRUNLIST, mRPPurchasingRunList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRunLine> getMRPPurchasingRunLineList() {
      return (List<PurchasingRunLine>) get(PROPERTY_MRPPURCHASINGRUNLINELIST);
    }

    public void setMRPPurchasingRunLineList(List<PurchasingRunLine> mRPPurchasingRunLineList) {
        set(PROPERTY_MRPPURCHASINGRUNLINELIST, mRPPurchasingRunLineList);
    }

    @SuppressWarnings("unchecked")
    public List<SalesForecast> getMRPSalesForecastList() {
      return (List<SalesForecast>) get(PROPERTY_MRPSALESFORECASTLIST);
    }

    public void setMRPSalesForecastList(List<SalesForecast> mRPSalesForecastList) {
        set(PROPERTY_MRPSALESFORECASTLIST, mRPSalesForecastList);
    }

    @SuppressWarnings("unchecked")
    public List<Worker> getManufacturingMaintenanceWorkerList() {
      return (List<Worker>) get(PROPERTY_MANUFACTURINGMAINTENANCEWORKERLIST);
    }

    public void setManufacturingMaintenanceWorkerList(List<Worker> manufacturingMaintenanceWorkerList) {
        set(PROPERTY_MANUFACTURINGMAINTENANCEWORKERLIST, manufacturingMaintenanceWorkerList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunEmployee> getManufacturingProductionRunEmployeeList() {
      return (List<ProductionRunEmployee>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST);
    }

    public void setManufacturingProductionRunEmployeeList(List<ProductionRunEmployee> manufacturingProductionRunEmployeeList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST, manufacturingProductionRunEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkEffortEmployee> getManufacturingWorkEffortEmployeeList() {
      return (List<WorkEffortEmployee>) get(PROPERTY_MANUFACTURINGWORKEFFORTEMPLOYEELIST);
    }

    public void setManufacturingWorkEffortEmployeeList(List<WorkEffortEmployee> manufacturingWorkEffortEmployeeList) {
        set(PROPERTY_MANUFACTURINGWORKEFFORTEMPLOYEELIST, manufacturingWorkEffortEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> getMaterialMgmtReservationList() {
      return (List<Reservation>) get(PROPERTY_MATERIALMGMTRESERVATIONLIST);
    }

    public void setMaterialMgmtReservationList(List<Reservation> materialMgmtReservationList) {
        set(PROPERTY_MATERIALMGMTRESERVATIONLIST, materialMgmtReservationList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutList() {
      return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST);
    }

    public void setMaterialMgmtShipmentInOutList(List<ShipmentInOut> materialMgmtShipmentInOutList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, materialMgmtShipmentInOutList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOutLine> getMaterialMgmtShipmentInOutLineList() {
      return (List<ShipmentInOutLine>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST);
    }

    public void setMaterialMgmtShipmentInOutLineList(List<ShipmentInOutLine> materialMgmtShipmentInOutLineList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, materialMgmtShipmentInOutLineList);
    }

    @SuppressWarnings("unchecked")
    public List<NO_Registro_Hora_Extra> getRegistroHoraExtraList() {
      return (List<NO_Registro_Hora_Extra>) get(PROPERTY_REGISTROHORAEXTRALIST);
    }

    public void setRegistroHoraExtraList(List<NO_Registro_Hora_Extra> registroHoraExtraList) {
        set(PROPERTY_REGISTROHORAEXTRALIST, registroHoraExtraList);
    }

    @SuppressWarnings("unchecked")
    public List<noRptCargasFamiliaresV> getRptCargasFamiliaresList() {
      return (List<noRptCargasFamiliaresV>) get(PROPERTY_RPTCARGASFAMILIARESLIST);
    }

    public void setRptCargasFamiliaresList(List<noRptCargasFamiliaresV> rptCargasFamiliaresList) {
        set(PROPERTY_RPTCARGASFAMILIARESLIST, rptCargasFamiliaresList);
    }

    @SuppressWarnings("unchecked")
    public List<noCbEmpleadoAcct> getCbEmpleadoAcctList() {
      return (List<noCbEmpleadoAcct>) get(PROPERTY_CBEMPLEADOACCTLIST);
    }

    public void setCbEmpleadoAcctList(List<noCbEmpleadoAcct> cbEmpleadoAcctList) {
        set(PROPERTY_CBEMPLEADOACCTLIST, cbEmpleadoAcctList);
    }

    @SuppressWarnings("unchecked")
    public List<NoEmpleadoIngresoEgreso> getEmpleadoIngEgrList() {
      return (List<NoEmpleadoIngresoEgreso>) get(PROPERTY_EMPLEADOINGEGRLIST);
    }

    public void setEmpleadoIngEgrList(List<NoEmpleadoIngresoEgreso> empleadoIngEgrList) {
        set(PROPERTY_EMPLEADOINGEGRLIST, empleadoIngEgrList);
    }

    @SuppressWarnings("unchecked")
    public List<NoPermiso> getPermisoList() {
      return (List<NoPermiso>) get(PROPERTY_PERMISOLIST);
    }

    public void setPermisoList(List<NoPermiso> permisoList) {
        set(PROPERTY_PERMISOLIST, permisoList);
    }

    @SuppressWarnings("unchecked")
    public List<noRolPagoProvision> getRolPagoProvisionList() {
      return (List<noRolPagoProvision>) get(PROPERTY_ROLPAGOPROVISIONLIST);
    }

    public void setRolPagoProvisionList(List<noRolPagoProvision> rolPagoProvisionList) {
        set(PROPERTY_ROLPAGOPROVISIONLIST, rolPagoProvisionList);
    }

    @SuppressWarnings("unchecked")
    public List<noReComisionDetalle> getReComisionDetalleList() {
      return (List<noReComisionDetalle>) get(PROPERTY_RECOMISIONDETALLELIST);
    }

    public void setReComisionDetalleList(List<noReComisionDetalle> reComisionDetalleList) {
        set(PROPERTY_RECOMISIONDETALLELIST, reComisionDetalleList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.order.Order> getOrderList() {
      return (List<org.openbravo.model.common.order.Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<org.openbravo.model.common.order.Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.order.Order> getOrderDropShipPartnerList() {
      return (List<org.openbravo.model.common.order.Order>) get(PROPERTY_ORDERDROPSHIPPARTNERLIST);
    }

    public void setOrderDropShipPartnerList(List<org.openbravo.model.common.order.Order> orderDropShipPartnerList) {
        set(PROPERTY_ORDERDROPSHIPPARTNERLIST, orderDropShipPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
      return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLineAccountingDimension> getOrderLineAccountingDimensionList() {
      return (List<OrderLineAccountingDimension>) get(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setOrderLineAccountingDimensionList(List<OrderLineAccountingDimension> orderLineAccountingDimensionList) {
        set(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, orderLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationInformation> getOrganizationInformationList() {
      return (List<OrganizationInformation>) get(PROPERTY_ORGANIZATIONINFORMATIONLIST);
    }

    public void setOrganizationInformationList(List<OrganizationInformation> organizationInformationList) {
        set(PROPERTY_ORGANIZATIONINFORMATIONLIST, organizationInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<PrereservationManualPickEdit> getPrereservationManualPickEditList() {
      return (List<PrereservationManualPickEdit>) get(PROPERTY_PRERESERVATIONMANUALPICKEDITLIST);
    }

    public void setPrereservationManualPickEditList(List<PrereservationManualPickEdit> prereservationManualPickEditList) {
        set(PROPERTY_PRERESERVATIONMANUALPICKEDITLIST, prereservationManualPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.pricing.priceadjustment.BusinessPartner> getPricingAdjustmentBusinessPartnerList() {
      return (List<org.openbravo.model.pricing.priceadjustment.BusinessPartner>) get(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST);
    }

    public void setPricingAdjustmentBusinessPartnerList(List<org.openbravo.model.pricing.priceadjustment.BusinessPartner> pricingAdjustmentBusinessPartnerList) {
        set(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST, pricingAdjustmentBusinessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<PriceListSchemeLine> getPricingPriceListSchemeLineList() {
      return (List<PriceListSchemeLine>) get(PROPERTY_PRICINGPRICELISTSCHEMELINELIST);
    }

    public void setPricingPriceListSchemeLineList(List<PriceListSchemeLine> pricingPriceListSchemeLineList) {
        set(PROPERTY_PRICINGPRICELISTSCHEMELINELIST, pricingPriceListSchemeLineList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.pricing.volumediscount.BusinessPartner> getPricingVolumeDiscountBusinessPartnerList() {
      return (List<org.openbravo.model.pricing.volumediscount.BusinessPartner>) get(PROPERTY_PRICINGVOLUMEDISCOUNTBUSINESSPARTNERLIST);
    }

    public void setPricingVolumeDiscountBusinessPartnerList(List<org.openbravo.model.pricing.volumediscount.BusinessPartner> pricingVolumeDiscountBusinessPartnerList) {
        set(PROPERTY_PRICINGVOLUMEDISCOUNTBUSINESSPARTNERLIST, pricingVolumeDiscountBusinessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<Requisition> getProcurementRequisitionList() {
      return (List<Requisition>) get(PROPERTY_PROCUREMENTREQUISITIONLIST);
    }

    public void setProcurementRequisitionList(List<Requisition> procurementRequisitionList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLIST, procurementRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RequisitionLine> getProcurementRequisitionLineList() {
      return (List<RequisitionLine>) get(PROPERTY_PROCUREMENTREQUISITIONLINELIST);
    }

    public void setProcurementRequisitionLineList(List<RequisitionLine> procurementRequisitionLineList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLINELIST, procurementRequisitionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.Product> getProductList() {
      return (List<org.openbravo.model.common.plm.Product>) get(PROPERTY_PRODUCTLIST);
    }

    public void setProductList(List<org.openbravo.model.common.plm.Product> productList) {
        set(PROPERTY_PRODUCTLIST, productList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductCustomer> getProductCustomerList() {
      return (List<ProductCustomer>) get(PROPERTY_PRODUCTCUSTOMERLIST);
    }

    public void setProductCustomerList(List<ProductCustomer> productCustomerList) {
        set(PROPERTY_PRODUCTCUSTOMERLIST, productCustomerList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
      return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectPersonInChargeList() {
      return (List<Project>) get(PROPERTY_PROJECTPERSONINCHARGELIST);
    }

    public void setProjectPersonInChargeList(List<Project> projectPersonInChargeList) {
        set(PROPERTY_PROJECTPERSONINCHARGELIST, projectPersonInChargeList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectProposal> getProjectProposalList() {
      return (List<ProjectProposal>) get(PROPERTY_PROJECTPROPOSALLIST);
    }

    public void setProjectProposalList(List<ProjectProposal> projectProposalList) {
        set(PROPERTY_PROJECTPROPOSALLIST, projectProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectVendor> getProjectVendorList() {
      return (List<ProjectVendor>) get(PROPERTY_PROJECTVENDORLIST);
    }

    public void setProjectVendorList(List<ProjectVendor> projectVendorList) {
        set(PROPERTY_PROJECTVENDORLIST, projectVendorList);
    }

    @SuppressWarnings("unchecked")
    public List<ReturnMaterialOrderPickEditLines> getReturnMaterialOrderPickEditLinesList() {
      return (List<ReturnMaterialOrderPickEditLines>) get(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST);
    }

    public void setReturnMaterialOrderPickEditLinesList(List<ReturnMaterialOrderPickEditLines> returnMaterialOrderPickEditLinesList) {
        set(PROPERTY_RETURNMATERIALORDERPICKEDITLINESLIST, returnMaterialOrderPickEditLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<Commission> getSalesCommissionList() {
      return (List<Commission>) get(PROPERTY_SALESCOMMISSIONLIST);
    }

    public void setSalesCommissionList(List<Commission> salesCommissionList) {
        set(PROPERTY_SALESCOMMISSIONLIST, salesCommissionList);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionLine> getSalesCommissionLineList() {
      return (List<CommissionLine>) get(PROPERTY_SALESCOMMISSIONLINELIST);
    }

    public void setSalesCommissionLineList(List<CommissionLine> salesCommissionLineList) {
        set(PROPERTY_SALESCOMMISSIONLINELIST, salesCommissionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ShippingCompany> getShippingShippingCompanyList() {
      return (List<ShippingCompany>) get(PROPERTY_SHIPPINGSHIPPINGCOMPANYLIST);
    }

    public void setShippingShippingCompanyList(List<ShippingCompany> shippingShippingCompanyList) {
        set(PROPERTY_SHIPPINGSHIPPINGCOMPANYLIST, shippingShippingCompanyList);
    }

    @SuppressWarnings("unchecked")
    public List<Sheet> getTimeAndExpenseSheetList() {
      return (List<Sheet>) get(PROPERTY_TIMEANDEXPENSESHEETLIST);
    }

    public void setTimeAndExpenseSheetList(List<Sheet> timeAndExpenseSheetList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLIST, timeAndExpenseSheetList);
    }

    @SuppressWarnings("unchecked")
    public List<SheetLine> getTimeAndExpenseSheetLineList() {
      return (List<SheetLine>) get(PROPERTY_TIMEANDEXPENSESHEETLINELIST);
    }

    public void setTimeAndExpenseSheetLineList(List<SheetLine> timeAndExpenseSheetLineList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLINELIST, timeAndExpenseSheetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<SheetLineV> getTimeAndExpenseSheetLineVList() {
      return (List<SheetLineV>) get(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST);
    }

    public void setTimeAndExpenseSheetLineVList(List<SheetLineV> timeAndExpenseSheetLineVList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST, timeAndExpenseSheetLineVList);
    }

    @SuppressWarnings("unchecked")
    public List<SheetLineV> getTimeAndExpenseSheetLineVChargedBusinessPartnerList() {
      return (List<SheetLineV>) get(PROPERTY_TIMEANDEXPENSESHEETLINEVCHARGEDBUSINESSPARTNERLIST);
    }

    public void setTimeAndExpenseSheetLineVChargedBusinessPartnerList(List<SheetLineV> timeAndExpenseSheetLineVChargedBusinessPartnerList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLINEVCHARGEDBUSINESSPARTNERLIST, timeAndExpenseSheetLineVChargedBusinessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransactionV> getTransactionVList() {
      return (List<MaterialTransactionV>) get(PROPERTY_TRANSACTIONVLIST);
    }

    public void setTransactionVList(List<MaterialTransactionV> transactionVList) {
        set(PROPERTY_TRANSACTIONVLIST, transactionVList);
    }

    @SuppressWarnings("unchecked")
    public List<VendorAccounts> getVendorAccountsList() {
      return (List<VendorAccounts>) get(PROPERTY_VENDORACCOUNTSLIST);
    }

    public void setVendorAccountsList(List<VendorAccounts> vendorAccountsList) {
        set(PROPERTY_VENDORACCOUNTSLIST, vendorAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<WarehouseShipper> getWarehouseShipperList() {
      return (List<WarehouseShipper>) get(PROPERTY_WAREHOUSESHIPPERLIST);
    }

    public void setWarehouseShipperList(List<WarehouseShipper> warehouseShipperList) {
        set(PROPERTY_WAREHOUSESHIPPERLIST, warehouseShipperList);
    }

    @SuppressWarnings("unchecked")
    public List<asvPedidoInventario> getAsvPedidoInventarioList() {
      return (List<asvPedidoInventario>) get(PROPERTY_ASVPEDIDOINVENTARIOLIST);
    }

    public void setAsvPedidoInventarioList(List<asvPedidoInventario> asvPedidoInventarioList) {
        set(PROPERTY_ASVPEDIDOINVENTARIOLIST, asvPedidoInventarioList);
    }

    @SuppressWarnings("unchecked")
    public List<atecacActivoEmp> getAtecacActivoEmpList() {
      return (List<atecacActivoEmp>) get(PROPERTY_ATECACACTIVOEMPLIST);
    }

    public void setAtecacActivoEmpList(List<atecacActivoEmp> atecacActivoEmpList) {
        set(PROPERTY_ATECACACTIVOEMPLIST, atecacActivoEmpList);
    }

    @SuppressWarnings("unchecked")
    public List<atrdepCabeceraRetenLine> getAtrdepCabeceraRetenLineList() {
      return (List<atrdepCabeceraRetenLine>) get(PROPERTY_ATRDEPCABECERARETENLINELIST);
    }

    public void setAtrdepCabeceraRetenLineList(List<atrdepCabeceraRetenLine> atrdepCabeceraRetenLineList) {
        set(PROPERTY_ATRDEPCABECERARETENLINELIST, atrdepCabeceraRetenLineList);
    }

    @SuppressWarnings("unchecked")
    public List<CoRetencionVentaView> getVistaRetencionVentasSalesrepIDList() {
      return (List<CoRetencionVentaView>) get(PROPERTY_VISTARETENCIONVENTASSALESREPIDLIST);
    }

    public void setVistaRetencionVentasSalesrepIDList(List<CoRetencionVentaView> vistaRetencionVentasSalesrepIDList) {
        set(PROPERTY_VISTARETENCIONVENTASSALESREPIDLIST, vistaRetencionVentasSalesrepIDList);
    }

    @SuppressWarnings("unchecked")
    public List<CoRetencionVentaView> getVistaRetencionVentasList() {
      return (List<CoRetencionVentaView>) get(PROPERTY_VISTARETENCIONVENTASLIST);
    }

    public void setVistaRetencionVentasList(List<CoRetencionVentaView> vistaRetencionVentasList) {
        set(PROPERTY_VISTARETENCIONVENTASLIST, vistaRetencionVentasList);
    }

    @SuppressWarnings("unchecked")
    public List<noCargaEmpleado> getCargaEmpleadoList() {
      return (List<noCargaEmpleado>) get(PROPERTY_CARGAEMPLEADOLIST);
    }

    public void setCargaEmpleadoList(List<noCargaEmpleado> cargaEmpleadoList) {
        set(PROPERTY_CARGAEMPLEADOLIST, cargaEmpleadoList);
    }

    @SuppressWarnings("unchecked")
    public List<noContratoEmpleado> getContratoEmpleadoList() {
      return (List<noContratoEmpleado>) get(PROPERTY_CONTRATOEMPLEADOLIST);
    }

    public void setContratoEmpleadoList(List<noContratoEmpleado> contratoEmpleadoList) {
        set(PROPERTY_CONTRATOEMPLEADOLIST, contratoEmpleadoList);
    }

    @SuppressWarnings("unchecked")
    public List<noGastosEmpleado> getGastosEmpleadoList() {
      return (List<noGastosEmpleado>) get(PROPERTY_GASTOSEMPLEADOLIST);
    }

    public void setGastosEmpleadoList(List<noGastosEmpleado> gastosEmpleadoList) {
        set(PROPERTY_GASTOSEMPLEADOLIST, gastosEmpleadoList);
    }

    @SuppressWarnings("unchecked")
    public List<noLiquidacionEmpleado> getLiquidacionEmpleadoList() {
      return (List<noLiquidacionEmpleado>) get(PROPERTY_LIQUIDACIONEMPLEADOLIST);
    }

    public void setLiquidacionEmpleadoList(List<noLiquidacionEmpleado> liquidacionEmpleadoList) {
        set(PROPERTY_LIQUIDACIONEMPLEADOLIST, liquidacionEmpleadoList);
    }

    @SuppressWarnings("unchecked")
    public List<noNovedadLinea> getNovedadLineaList() {
      return (List<noNovedadLinea>) get(PROPERTY_NOVEDADLINEALIST);
    }

    public void setNovedadLineaList(List<noNovedadLinea> novedadLineaList) {
        set(PROPERTY_NOVEDADLINEALIST, novedadLineaList);
    }

    @SuppressWarnings("unchecked")
    public List<noPagoLine> getPagoLineList() {
      return (List<noPagoLine>) get(PROPERTY_PAGOLINELIST);
    }

    public void setPagoLineList(List<noPagoLine> pagoLineList) {
        set(PROPERTY_PAGOLINELIST, pagoLineList);
    }

    @SuppressWarnings("unchecked")
    public List<noPrestamo> getPrestamoList() {
      return (List<noPrestamo>) get(PROPERTY_PRESTAMOLIST);
    }

    public void setPrestamoList(List<noPrestamo> prestamoList) {
        set(PROPERTY_PRESTAMOLIST, prestamoList);
    }

    @SuppressWarnings("unchecked")
    public List<noRegistraQuincLine> getRegistraQuincLineList() {
      return (List<noRegistraQuincLine>) get(PROPERTY_REGISTRAQUINCLINELIST);
    }

    public void setRegistraQuincLineList(List<noRegistraQuincLine> registraQuincLineList) {
        set(PROPERTY_REGISTRAQUINCLINELIST, registraQuincLineList);
    }

    @SuppressWarnings("unchecked")
    public List<noRegistroGasto> getRegistroGastoList() {
      return (List<noRegistroGasto>) get(PROPERTY_REGISTROGASTOLIST);
    }

    public void setRegistroGastoList(List<noRegistroGasto> registroGastoList) {
        set(PROPERTY_REGISTROGASTOLIST, registroGastoList);
    }

    @SuppressWarnings("unchecked")
    public List<noSalarioDignoLinea> getSalarioDignoLineaList() {
      return (List<noSalarioDignoLinea>) get(PROPERTY_SALARIODIGNOLINEALIST);
    }

    public void setSalarioDignoLineaList(List<noSalarioDignoLinea> salarioDignoLineaList) {
        set(PROPERTY_SALARIODIGNOLINEALIST, salarioDignoLineaList);
    }

    @SuppressWarnings("unchecked")
    public List<noUtilidadLinea> getUtilidadLineaList() {
      return (List<noUtilidadLinea>) get(PROPERTY_UTILIDADLINEALIST);
    }

    public void setUtilidadLineaList(List<noUtilidadLinea> utilidadLineaList) {
        set(PROPERTY_UTILIDADLINEALIST, utilidadLineaList);
    }

    @SuppressWarnings("unchecked")
    public List<noVacacion> getVacacionList() {
      return (List<noVacacion>) get(PROPERTY_VACACIONLIST);
    }

    public void setVacacionList(List<noVacacion> vacacionList) {
        set(PROPERTY_VACACIONLIST, vacacionList);
    }

}
