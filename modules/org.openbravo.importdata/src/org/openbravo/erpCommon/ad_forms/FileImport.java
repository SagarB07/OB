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
 * All portions are Copyright (C) 2001-2011 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package org.openbravo.erpCommon.ad_forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.businessUtility.WindowTabs;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.LeftTabsBar;
import org.openbravo.erpCommon.utility.NavigationBar;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.erpCommon.utility.ToolBar;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.xmlEngine.XmlDocument;

public class FileImport extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  private static boolean firstRowHeaders = true;

  private static final int THRESHOLD = 1000;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    if (log4j.isDebugEnabled())
      log4j.debug("Command: " + vars.getStringParameter("Command"));
    String strFirstLineHeader = vars.getStringParameter("inpFirstLineHeader");
    firstRowHeaders = (strFirstLineHeader.equals("Y")) ? true : false;
    FileLoadData fieldsData = null;

    if (vars.commandIn("DEFAULT")) {
      String strAdImpformatId = vars.getStringParameter("inpadImpformatId");
      printPage(response, vars, strFirstLineHeader, vars.getCommand(), "", strAdImpformatId);
    } else if (vars.commandIn("FIND")) {
      String strAdImpformatId = vars.getStringParameter("inpadImpformatId");
      FieldProvider[] rows = null;
      String strSeparator = FileImportData.selectSeparator(this, strAdImpformatId);
      if (log4j.isDebugEnabled())
        log4j.debug("First Row Header: " + firstRowHeaders);
      if (strSeparator.equalsIgnoreCase("F"))
        rows = FileImportData.select(this, strAdImpformatId);
      fieldsData = new FileLoadData(vars, "inpFile", firstRowHeaders, strSeparator, rows);
      printSampleImport(vars, fieldsData.getFieldProvider(), request, response, strAdImpformatId, strFirstLineHeader);
    } else if (vars.commandIn("SAVE")) {
      String strAdImpformatId = vars.getStringParameter("inpadImpformatId");
      FieldProvider[] rows = null;
      String strSeparator = FileImportData.selectSeparator(this, strAdImpformatId);
      if (strSeparator.equalsIgnoreCase("F"))
        rows = FileImportData.select(this, strAdImpformatId);
      fieldsData = new FileLoadData(vars, "inpFile", firstRowHeaders, strSeparator, rows);
      OBError myMessage = importarFichero(vars, fieldsData.getFieldProvider(), request, response,
          strAdImpformatId);
      vars.setMessage("FileImport", myMessage);
      printPageResult(response, vars, "", "SAVE");
    } else
      pageError(response);
  }

  @SuppressWarnings("unused")
private String procesarFichero(VariablesSecureApp vars, FieldProvider[] data2, HttpServletRequest request, HttpServletResponse response, String strAdImpformatId,  String strFirstLineHeader) throws ServletException, IOException {
    if (data2 == null)
      return "";
    StringBuffer texto = new StringBuffer("");
    FileImportData[] data = FileImportData.select(this, strAdImpformatId);
    if (data == null)
      return "";
    int constant = 0;
    texto
        .append("<table cellspacing=\"0\" cellpadding=\"0\" width=\"99%\" class=\"DataGrid_Header_Table DataGrid_Body_Table\" style=\"table-layout: auto;\">"
            + "<tr class=\"DataGrid_Body_Row\">  " + "<td>");
    if (log4j.isDebugEnabled())
      log4j.debug("data2.length: " + data2.length);
    for (int i = 0; i < data2.length; i++) {
      if (log4j.isDebugEnabled())
        log4j.debug("i:" + i + " - data.length" + data.length);
      texto.append("<tr class=\"DataGrid_Body_Row DataGrid_Body_Row_" + (i % 2 == 0 ? "0" : "1")
          + "\">");
      for (int j = 0; j < data.length; j++) {
        if (i == 0 && strFirstLineHeader.equalsIgnoreCase("Y"))
          texto.append("<th class=\"DataGrid_Header_Cell\">");
        else
          texto.append("<td class=\"DataGrid_Body_Cell\">");
        if (!data[j].constantvalue.equals("")) {
          texto.append(data[j].constantvalue);
          constant = constant + 1;
        } else
          texto.append(parseField(data2[i].getField(String.valueOf(j - constant)),
              data[j].fieldlength, data[j].datatype, data[j].dataformat, data[j].decimalpoint, ""));
        if (i == 0 && strFirstLineHeader.equalsIgnoreCase("Y"))
          texto.append("</th>");
        else
          texto.append("</td>");
      }
      constant = 0;
      texto.append("</tr>");
    }
    texto.append("</td></table>");
    return texto.toString();
  }
  
 private StringBuffer obtenerCodigosContrato (Connection conn, ConnectionProvider connectionProvider,StringBuffer campo, String valorCampo) throws ServletException{
	 StringBuffer dato = new StringBuffer("");//
	 String strSql = "";
	 Integer campoTipoDocumento = campo.indexOf("C_Doctype_ID");
	 Integer campoNumDocumento = campo.indexOf("Documentno");
	 Integer campoEmpleado = campo.indexOf("C_Bpartner_ID");
	 Integer campoFechaIni = campo.indexOf("Fecha_Inicio");
	 Integer campoFechaFin = campo.indexOf("Fecha_Fin");
	 Integer campoRegion = campo.indexOf("NE_Region");
	 Integer campoCargo = campo.indexOf("Atnorh_Cargo_ID");
	 Integer campoVActuales = campo.indexOf("NE_Vacacion_Prop");
	 Integer campoVTomadas = campo.indexOf("NE_Vacacion_Tom");
	 Integer campoVRestantes = campo.indexOf("NE_Vacacion_Res");
	 Integer campoSalario = campo.indexOf("Salario");
	 Integer campoMoneda = campo.indexOf("C_Currency_ID");
	 Integer campoJparcial = campo.indexOf("NE_Is_Jornada_Parcial");
	 Integer campoHorasParciales = campo.indexOf("NE_Num_Horas_Parciales");
	 Integer campoSistemaSalario = campo.indexOf("NE_Sissalnet");
	 Integer campoPagoFondoReserva = campo.indexOf("Pagofondoreserva");
	 Integer campoAplicaUtilidad = campo.indexOf("Aplica_Utilidad");
	 Integer campoMotivoSalida = campo.indexOf("NE_Motivo_Salida");
	 Integer campoObservaciones = campo.indexOf("NE_Observaciones");
	 Integer campoArea = campo.indexOf("NO_Area_Empresa_ID");
	 
	 
	 //Atnorh_Cargo

	 if (campoTipoDocumento>=0 && campoNumDocumento < 0  ){		 
		 valorCampo = valorCampo.replace("'", "");
		 strSql ="SELECT C_Doctype_ID FROM c_doctype where name like '%"+valorCampo+"%'";
		 String valoRetorno= FileImportUtil.obtenerIDCampo(conn, connectionProvider, strSql, campo.toString());
		 if (valoRetorno!= null){
			 {
				 dato.append("'");
				 dato.append(valoRetorno);
				 dato.append("'");
			 }
		 }
	 } else if (campoNumDocumento>=0 && campoEmpleado<0 ){		 
		 if (valorCampo!= null){
			 {
				 dato.append(valorCampo);
			 }
		 }
	 }else if (campoEmpleado>=0 && campoFechaIni < 0 ) {
		 valorCampo = valorCampo.replace("'", "");
		 strSql ="SELECT C_BPARTNER_ID FROM C_BPARTNER where taxid like '%"+valorCampo+"%'";
		 String valoRetorno= FileImportUtil.obtenerIDCampo(conn, connectionProvider, strSql, "C_Bpartner_ID");
		 if (valoRetorno!= null){
			 {
				 dato.append("'");
				 dato.append(valoRetorno);
				 dato.append("'");
			 }
		 }
		 
	 } else if (campoCargo>=0 && campoVActuales< 0){
		 valorCampo = valorCampo.replace("'", "");
		 strSql ="SELECT Atnorh_Cargo_ID FROM Atnorh_Cargo where name like '%"+valorCampo+"%'";
		 String valoRetorno= FileImportUtil.obtenerIDCampo(conn, connectionProvider, strSql, "Atnorh_Cargo_ID");
		 if (valoRetorno!= null){
			 {
				 dato.append("'");
				 dato.append(valoRetorno);
				 dato.append("'");
			 }
		 } 
		 
	 }else if (campoVActuales>= 0 && campoVTomadas< 0){
		 dato.append(valorCampo);
	 }else if (campoVTomadas>= 0&& campoVRestantes< 0){
		 dato.append(valorCampo);
	 }else if (campoVRestantes>= 0 && campoSalario<0){
		 dato.append(valorCampo);
	 }else if (campoSalario>= 0 && campoMoneda<0 ){
		 dato.append(valorCampo);
	 }else if (campoMoneda>= 0 && campoJparcial<0 ){
		 //SELECT * FROM C_Currency
		 valorCampo = valorCampo.replace("'", "");
		 strSql ="SELECT  C_Currency_ID FROM C_Currency where iso_code like '%"+valorCampo+"%'";
		 String valoRetorno= FileImportUtil.obtenerIDCampo(conn, connectionProvider, strSql, "C_Currency_ID");
		 if (valoRetorno!= null){
			 {
				 dato.append("'");
				 dato.append(valoRetorno);
				 dato.append("'");
			 }
		 } 
	 }else if (campoJparcial>= 0 && campoHorasParciales<0 ){
			 dato.append(valorCampo);
	 }else if (campoHorasParciales>= 0 && campoSistemaSalario<0 && campoRegion<0){
		 dato.append(valorCampo);
	 }else if (campoSistemaSalario>=0 && campoPagoFondoReserva<0){
		 dato.append("'1'");
	 }else if (campoPagoFondoReserva>=0 && campoAplicaUtilidad<0){
		 dato.append(valorCampo);
	 }else if (campoAplicaUtilidad>=0 && campoMotivoSalida<0){
		 dato.append(valorCampo);
	 }else if (campoMotivoSalida>=0 && campoObservaciones<0){
		 dato.append(valorCampo);
	 }else if (campoObservaciones>=0 && campoRegion<0 ){
		 dato.append(valorCampo);
	 }else if (campoRegion>=0 && campoArea<0){
		 if(valorCampo.equals("'COSTA'")){
		 dato.append("'2'");
		 }else if(valorCampo.equals("'SIERRA'")){
		  dato.append("'1'");
		 } else {
			 dato.append("'0'");
		 }
	 }else if (campoArea>=0){
		 //select * from no_area_empresa where nombre like '%RECURSOS HUMANOS%';
		 valorCampo = valorCampo.replace("'", "");
		 strSql ="select * from no_area_empresa where nombre like '%"+valorCampo+"%'";
		 String valoRetorno= FileImportUtil.obtenerIDCampo(conn, connectionProvider, strSql, "NO_Area_Empresa_ID");
		 if (valoRetorno!= null){
			 {
				 dato.append("'");
				 dato.append(valoRetorno);
				 dato.append("'");
			 }
		 } 
		 
	 }
	 
	 campo.append(dato);
	// System.out.println(campo);
	 
	 return campo;
  }
  private StringBuffer obtenerCodigos (Connection conn, ConnectionProvider connectionProvider,StringBuffer campo, String valorCampo){
	  StringBuffer dato = new StringBuffer("");
	  Integer valCampoingEgre = campo.indexOf("NO_Tipo_Ingreso_Egreso_ID");
	  Integer valPeriodo = campo.indexOf("C_Period_ID");
	  Integer valValor = campo.indexOf("Valor");
	  
	  if (valCampoingEgre > 0 && valPeriodo < 0 &&  valValor < 0 ){
		  dato.append("'");
			try {
					  String valoRetorno= FileImportUtil.obtenerIDCampoRubro(conn, connectionProvider, valorCampo);
					  dato.append(valoRetorno);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  dato.append("'");
	  }
	  
	  if (valPeriodo > 0 &&  valValor < 0){
		  dato.append("'");
			try {
					  String valoRetorno= FileImportUtil.obtenerIDPeriodo(conn, connectionProvider, valorCampo);
					  dato.append(valoRetorno);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  dato.append("'");
	  }
	  if (valValor > 0){
		  dato.append(valorCampo);
	  }
	  campo.append(dato);
	  return campo;
  }
  
  private OBError importarFichero(VariablesSecureApp vars, FieldProvider[] data2, HttpServletRequest request, HttpServletResponse response, String strAdImpformatId)    throws ServletException, IOException {
    Connection con = null;
    StringBuffer strFields = new StringBuffer("");
    StringBuffer strValues = new StringBuffer("");
    FileImportData[] data = null;
    int constant = 0;
    OBError myMessage = null;
    int i = 0;

    try {
      con = getTransactionConnection();
      data = FileImportData.select(this, strAdImpformatId);
      String strTable = FileImportData.table(this, strAdImpformatId);
      for (i = 0; i < data2.length; i++) {
        // create a basic row with uuid to be updated in the next step
        String sequence = SequenceIdData.getUUID();
        try {
          FileImportData.insert(con, this, strTable, (strTable + "_ID"), sequence,  vars.getClient(), vars.getOrg(), vars.getUser());
        } catch (ServletException ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          releaseRollbackConnection(con);
          return myMessage;
        }
        // generate the updated row information and update the basic row already created.
        int jj = 0;
        for (int j = 0; j < data.length; j++) {
          if ((data2[i].getField(String.valueOf(j - constant)) == null || data2[i].getField( String.valueOf(j - constant)).equals("")) && data[j].constantvalue.equals(""))
            continue;
          if (jj > 0)
            strFields.append(",");
          jj++;
          strFields.append(data[j].columnname).append(" = ");
          strValues.append("'");
          if ((data[j].datatype.equals("C")) && (!data[j].constantvalue.equals(""))) {
            strValues.append(data[j].constantvalue);
            constant = constant + 1;
          } 
          else
          strValues.append(parseField(data2[i].getField(String.valueOf(j - constant)),data[j].fieldlength, data[j].datatype, data[j].dataformat, data[j].decimalpoint,data[j].referencename));
         //System.out.println(data2[i].getField(String.valueOf(j - constant)));
         
          strValues.append("'");
          String valorCampoTemporal = strValues.toString();
          if (strTable.equals("IDT_novedad")){
        	  StringBuffer auxStrFields = new StringBuffer(strFields.toString());
        	  strFields = obtenerCodigos (con, this,strFields, valorCampoTemporal );
        	  if (strFields.toString().equals(auxStrFields.toString())){
        		  strFields.append(strValues);  
        	  }
          }else if (strTable.equals("IDT_contrato")){
        	  StringBuffer auxStrFields = new StringBuffer(strFields.toString());
        	  strFields = obtenerCodigosContrato (con, this,strFields, valorCampoTemporal );
        	  if (strFields.toString().equals(auxStrFields.toString())){
        		  strFields.append(strValues);  
        	  }
          }else if (strTable.equals("I_Product")){
        	  if(data[j].columnname.equals("C_UOM_ID")){
        		  String strFieldId = null;
        		  strFieldId = FileImportUtil.findFieldId(con, this, data[j].columnname, data2[i].getField(String.valueOf(j - constant)));
        		  if(strFieldId==null){
        			  myMessage = Utility.translateError(this, vars, vars.getLanguage(), "Error");
        	          myMessage.setMessage("<strong>" + Utility.messageBD(this, "Line", vars.getLanguage()) + "&nbsp;</strong>" + (i + 1) + "<br><strong>" + Utility.messageBD(this, "Error", vars.getLanguage()) + "&nbsp;&nbsp;</strong>" + "No se encontró la unidad de medida del producto");
        	          releaseRollbackConnection(con);
        	          return myMessage;
        		  }else{
        			  strFields.append(strFieldId);
        		  }
        	  }else if(data[j].columnname.equals("M_Product_Category_ID")){
        		  String strFieldId = null;
        		  strFieldId = FileImportUtil.findFieldId(con, this, data[j].columnname, data2[i].getField(String.valueOf(j - constant)));
        		  if(strFieldId==null){
        			  myMessage = Utility.translateError(this, vars, vars.getLanguage(), "Error");
        	          myMessage.setMessage("<strong>" + Utility.messageBD(this, "Line", vars.getLanguage())
        	              + "&nbsp;</strong>" + (i + 1) + "<br><strong>"
        	              + Utility.messageBD(this, "Error", vars.getLanguage()) + "&nbsp;&nbsp;</strong>"
        	              + "No se encontró la categoría del producto");
        	          releaseRollbackConnection(con);
        	          return myMessage;
        		  }else{
        			  strFields.append(strFieldId);
        		  }
        	  }else if(data[j].columnname.equals("EM_Idt_C_Taxcategory_ID")){
        		  String strFieldId = null;
        		  strFieldId = FileImportUtil.findFieldId(con, this, data[j].columnname, data2[i].getField(String.valueOf(j - constant)));
        		  if(strFieldId==null){
        			  myMessage = Utility.translateError(this, vars, vars.getLanguage(), "Error");
        	          myMessage.setMessage("<strong>" + Utility.messageBD(this, "Line", vars.getLanguage())
        	              + "&nbsp;</strong>" + (i + 1) + "<br><strong>"
        	              + Utility.messageBD(this, "Error", vars.getLanguage()) + "&nbsp;&nbsp;</strong>"
        	              + "No se encontró la categoría de impuestos del producto");
        	          releaseRollbackConnection(con);
        	          return myMessage;
        		  }else{
        			  strFields.append(strFieldId);
        		  }
        	  }else if(data[j].columnname.equals("EM_Idt_C_Taxcategory_ID")){
        		  String strFieldId = null;
        		  strFieldId = FileImportUtil.findFieldId(con, this, data[j].columnname, data2[i].getField(String.valueOf(j - constant)));
        		  if(strFieldId==null){
        			  myMessage = Utility.translateError(this, vars, vars.getLanguage(), "Error");
        	          myMessage.setMessage("<strong>" + Utility.messageBD(this, "Line", vars.getLanguage())
        	              + "&nbsp;</strong>" + (i + 1) + "<br><strong>"
        	              + Utility.messageBD(this, "Error", vars.getLanguage()) + "&nbsp;&nbsp;</strong>"
        	              + "No se encontró la categoría de impuestos del producto");
        	          releaseRollbackConnection(con);
        	          return myMessage;
        		  }else{
        			  strFields.append(strFieldId);
        		  }
        	  }else if(data[j].columnname.equals("ProductType")){
        		  if(data2[i].getField(String.valueOf(j - constant)).equals("Item")){
        			  strFields.append("'I'");
        		  }
        		  if(data2[i].getField(String.valueOf(j - constant)).equals("Service")){
        			  strFields.append("'S'");
        		  }
        		  if(data2[i].getField(String.valueOf(j - constant)).equals("Resource")){
        			  strFields.append("'R'");
        		  }
        		  if(data2[i].getField(String.valueOf(j - constant)).equals("Expense type")){
        			  strFields.append("'E'");
        		  }
        		  if(data2[i].getField(String.valueOf(j - constant)).equals("Online")){
        			  strFields.append("'O'");
        		  }
        	  }else if(data[j].columnname.equals("EM_Idt_C_Uom_Weight_ID")){
        		  String strFieldId = null;
        		  strFieldId = FileImportUtil.findFieldId(con, this, data[j].columnname, data2[i].getField(String.valueOf(j - constant)));
        		  if(strFieldId==null && !data2[i].getField(String.valueOf(j - constant)).isEmpty()){
        			  myMessage = Utility.translateError(this, vars, vars.getLanguage(), "Error");
        	          myMessage.setMessage("<strong>" + Utility.messageBD(this, "Line", vars.getLanguage())
        	              + "&nbsp;</strong>" + (i + 1) + "<br><strong>"
        	              + Utility.messageBD(this, "Error", vars.getLanguage()) + "&nbsp;&nbsp;</strong>"
        	              + "No se encontró la unidad de peso del producto");
        	          releaseRollbackConnection(con);
        	          return myMessage;
        		  }else{
        			  strFields.append(strFieldId);
        		  }
        	  }else if(data[j].columnname.equals("EM_Idt_M_Brand_ID")){
        		  String strFieldId = null;
        		  strFieldId = FileImportUtil.findFieldId(con, this, data[j].columnname, data2[i].getField(String.valueOf(j - constant)));
        		  if(strFieldId==null && !data2[i].getField(String.valueOf(j - constant)).isEmpty()){
        			  myMessage = Utility.translateError(this, vars, vars.getLanguage(), "Error");
        	          myMessage.setMessage("<strong>" + Utility.messageBD(this, "Line", vars.getLanguage())
        	              + "&nbsp;</strong>" + (i + 1) + "<br><strong>"
        	              + Utility.messageBD(this, "Error", vars.getLanguage()) + "&nbsp;&nbsp;</strong>"
        	              + "No se encontró la marca del producto");
        	          releaseRollbackConnection(con);
        	          return myMessage;
        		  }else{
        			  strFields.append(strFieldId);
        		  }
        	  }else{
        		  strFields.append(strValues);
        	  }
          }else {
        	  strFields.append(strValues);
          }
          strValues.delete(0, strValues.length());
        }
        constant = 0;
       // if (log4j.isDebugEnabled())
         // log4j.debug("##########iteration - " + (i + 1) + " - strFields = " + strFields);
        
        //Actualiza la tabla cuand esta ya se ingreso los registros base
        try {
          FileImportData.update(con, this, strTable, strFields.toString(), (strTable + "_id = '"+ sequence + "'"));
        } catch (ServletException ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          if (i == 0 && !firstRowHeaders) {
            myMessage.setTitle(Utility.messageBD(this,
                "Error while inserting data. Please  check if the CSV file contains a header", vars
                    .getLanguage()));
          } else {
        	  String mensajeError = "";
        	  ex.toString().indexOf("no_tipo_ingreso_egreso_id");
        	  if (ex.toString().indexOf("no_tipo_ingreso_egreso_id")> 0){
        		  mensajeError = "EXISTE UN ERROR EN EL CAMPO RUBRO, REVISE EL REGISTRO DESCRITO A CONTINUACIÓN ";
        	  }
        	  if (ex.toString().indexOf("c_period_id")> 0){
        		  mensajeError = "EXISTE UN ERROR EN EL CAMPO PERIODO, REVISE EL REGISTRO DESCRITO A CONTINUACIÓN";
        	  }
        	  
            myMessage.setTitle(Utility.messageBD(this, mensajeError, vars.getLanguage()));
          }
          String strMessage = myMessage.getMessage();
          myMessage.setMessage("<strong>" + Utility.messageBD(this, "Line", vars.getLanguage())
              + "&nbsp;</strong>" + (i + 1) + "<br><strong>"
              + Utility.messageBD(this, "Inserting data", vars.getLanguage())
              + ":&nbsp;&nbsp;</strong>" + strFields + "<br><strong>"
              + Utility.messageBD(this, "Error", vars.getLanguage()) + "&nbsp;&nbsp;</strong>"
              + strMessage);
          releaseRollbackConnection(con);
          return myMessage;
        }
        
        strFields.delete(0, strFields.length());
      }

      releaseCommitConnection(con);
      myMessage = new OBError();
      myMessage.setType("Success");
      myMessage.setTitle(Utility.messageBD(this, "Success", vars.getLanguage()));
      myMessage.setMessage(Utility.messageBD(this, "Records inserted in the temporary table", vars
          .getLanguage())
          + ": " + i);
    } catch (Exception e) {
      try {
        releaseRollbackConnection(con);
      } catch (Exception ignored) {
      }
      e.printStackTrace();
      myMessage = Utility.translateError(this, vars, vars.getLanguage(), "ProcessRunError");
    }
    return myMessage;
  }

  private String parseField(String strTexto, String strLength, String strDataType,String strDataFormat, String strDecimalPoint, String strReferenceName) throws ServletException {
    if (strReferenceName.equals("TableDir")) {
      strLength = "33";
    }
    if (strDataType.equals("D")) {
      strTexto = FileImportData.parseDate(this, strTexto, strDataFormat);
      return strTexto;
    } else if (strDataType.equals("N")) {
      if (strDecimalPoint.equals(",")) {
        strTexto = strTexto.replace('.', ' ').trim();
        return strTexto.replace(',', '.');
      } else {
        return strTexto;
      }
    } else {
      if (log4j.isDebugEnabled())
        log4j.debug("##########iteration - strTexto:" + strTexto + " - length:" + strLength);
      int len = Integer.valueOf(strLength).intValue();
      strTexto = strTexto.substring(0, (len > strTexto.length()) ? strTexto.length() : len);
      if (log4j.isDebugEnabled())
        log4j.debug("########## end of iteration - ");
      return strTexto.replace('\'', '´').trim();
    }
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String strFirstLineHeader, String strCommand, String texto, String strAdImpformatId)
      throws IOException, ServletException {
    if (log4j.isDebugEnabled())
      log4j.debug("Output: file importing Frame 1");
    XmlDocument xmlDocument = null;
    xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_forms/FileImport").createXmlDocument();

    ToolBar toolbar = new ToolBar(this, vars.getLanguage(), "FileImport", false, "", "", "", false,
        "ad_forms", strReplaceWith, false, true);

    toolbar.prepareSimpleToolBarTemplate();
    xmlDocument.setParameter("toolbar", toolbar.toString());
    if (log4j.isDebugEnabled())
      log4j.debug("2");

    try {
      WindowTabs tabs = new WindowTabs(this, vars, "org.openbravo.erpCommon.ad_forms.FileImport");
      xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
      xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
      xmlDocument.setParameter("childTabContainer", tabs.childTabs());
      NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "FileImport.html",classInfo.id, classInfo.type, strReplaceWith, tabs.breadcrumb());
      xmlDocument.setParameter("navigationBar", nav.toString());
      LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "FileImport.html", strReplaceWith);
      xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ServletException(ex);
    }

    xmlDocument.setParameter("theme", vars.getTheme());
    {
      OBError myMessage = vars.getMessage("FileImport");
      vars.removeMessage("FileImport");
      if (myMessage != null) {
        xmlDocument.setParameter("messageType", myMessage.getType());
        xmlDocument.setParameter("messageTitle", myMessage.getTitle());
        xmlDocument.setParameter("messageMessage", myMessage.getMessage());
      }
    }

    if (log4j.isDebugEnabled())
      log4j.debug("3");

    xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
    xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
    xmlDocument.setParameter("firstLineHeader", strFirstLineHeader);
    if (log4j.isDebugEnabled())
      log4j.debug("4");

    try {
      ComboTableData comboTableData = new ComboTableData(vars, this, "TABLEDIR", "AD_ImpFormat_ID",
          "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(
              this, vars, "#User_Client", ""), 0);
      Utility.fillSQLParameters(this, vars, null, comboTableData, "", "");
      xmlDocument.setData("reportImpFormat", "liststructure", comboTableData.select(false));
      comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

  /**
   * Prints the intermediate output of the .csv import - a manually generated table. The number of
   * rows displayed (loaded) is limited to THRESHOLD. Large .csv files can cause out of memory
   * exceptions, so we limit what can be loaded in the intermediate step here.
   * 
   * @param vars
   * @param data2
   * @param request
   * @param response
   * @param strAdImpformatId
   * @param strFirstLineHeader
   * @throws ServletException
   * @throws IOException
   */
  private void printSampleImport(VariablesSecureApp vars, FieldProvider[] data2,HttpServletRequest request, HttpServletResponse response, String strAdImpformatId,String strFirstLineHeader) throws ServletException, IOException {
    int count = 0;
    StringBuilder sb = new StringBuilder();
    if (data2 != null) {
      FileImportData[] data = FileImportData.select(this, strAdImpformatId);

      int constant = 0;
      sb.append("<table cellspacing=\"0\" cellpadding=\"0\" "
          + "width=\"99%\" class=\"DataGrid_Header_Table "
          + "DataGrid_Body_Table\" style=\"table-layout: auto;\">"
          + "<tr class=\"DataGrid_Body_Row\">  " + "<td>");
      if (log4j.isDebugEnabled())
        log4j.debug("data2.length: " + data2.length);
      for (int i = 0; i < data2.length && i < THRESHOLD; i++) {
        if (log4j.isDebugEnabled())
          log4j.debug("i:" + i + " - data.length" + data.length);
        sb.append("<tr class=\"DataGrid_Body_Row DataGrid_Body_Row_" + (i % 2 == 0 ? "0" : "1")
            + "\">");
        for (int j = 0; j < data.length; j++) {
          if (i == 0 && strFirstLineHeader.equalsIgnoreCase("Y"))
            sb.append("<th class=\"DataGrid_Header_Cell\">");
          else
            sb.append("<td class=\"DataGrid_Body_Cell\">");
          if (!data[j].constantvalue.equals("")) {
            sb.append(data[j].constantvalue);
            constant = constant + 1;
          } else
        	  
        try {

            sb.append(parseField(data2[i].getField(String.valueOf(j - constant)), data[j].fieldlength, data[j].datatype, data[j].dataformat,  data[j].decimalpoint, ""));
         //   System.out.println(data2[i].getField(String.valueOf(j - constant)));

        }catch (Exception ex){
        //	System.out.println(data2[i]);
  	  	}
            
          	
          if (i == 0 && strFirstLineHeader.equalsIgnoreCase("Y"))
            sb.append("</th>");
          else
            sb.append("</td>");
        }
        constant = 0;
        sb.append("</tr>");
        count++;
      }
      sb.append("</td></table>");
      if (count < data2.length) {
        sb.insert(0, "<p class=\"LabelText\">&nbsp; ** The following table is a sample " + count
            + " rows of the " + data2.length + " rows of data in the selected file.</p><br/>");
      }
    }

    XmlDocument xmlDocument = null;
    xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_forms/FileImport_Result")
        .createXmlDocument();
    response.setContentType("text/html; charset=UTF-8");
    String strJS = "\n var r = '" + sb.toString() + "'; \n"
        + "parent.frames['appFrame'].setResult(r); \n "
        + "parent.frames['appFrame'].setProcessingMode('window', false); \n";
    xmlDocument.setParameter("result", strJS);
    xmlDocument.setParameter("messageType", "Success");
    xmlDocument.setParameter("messageTitle", "Success");
    xmlDocument.setParameter("messageMessage", "Process completed ooh yeah");

    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

  private void printPageResult(HttpServletResponse response, VariablesSecureApp vars, String text,
      String command) throws IOException, ServletException {
    XmlDocument xmlDocument = null;
    xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_forms/FileImport_Result").createXmlDocument();
    response.setContentType("text/html; charset=UTF-8");
    String strJS = "\n parent.frames['appFrame'].setProcessingMode('window', false); \n"
        + "parent.frames['appFrame'].document.getElementById('buttonRefresh').onclick();\n";
    xmlDocument.setParameter("result", strJS);
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

  public String getServletInfo() {
    return "Servlet that presents the files-importing process";
    // end of getServletInfo() method
  }
}
