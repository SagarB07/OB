//Sqlc generated V1.O00-1
package org.openbravo.erpCommon.ad_actionButton;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;

class ActionButtonSQLDefaultData implements FieldProvider {
static Logger log4j = Logger.getLogger(ActionButtonSQLDefaultData.class);
  private String InitRecordNumber="0";
  public String adLanguageId;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("ad_language_id") || fieldName.equals("adLanguageId"))
      return adLanguageId;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

/**
Select for auxiliar field
 */
  public static String selectActP100_AD_LANGUAGE_ID(ConnectionProvider connectionProvider, String AD_LANGUAGE)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT AD_LANGUAGE_ID FROM AD_LANGUAGE WHERE AD_LANGUAGE = ? ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_LANGUAGE);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "ad_language_id");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }

/**
Select for auxiliar field
 */
  public static String selectActP154_M_PriceList_Version_ID(ConnectionProvider connectionProvider, String isSOTrx, String C_BPARTNER_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT MIN(M_PRICELIST_VERSION_ID) AS TOTAL FROM M_PRICELIST_VERSION M, C_BPARTNER B WHERE M.M_PRICELIST_ID = (CASE ? WHEN 'Y' THEN B.M_PRICELIST_ID ELSE COALESCE(B.PO_PRICELIST_ID, B.M_PRICELIST_ID) END) AND M.ISACTIVE = 'Y' AND B.C_BPARTNER_ID = ? ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, isSOTrx);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, C_BPARTNER_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "total");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }

/**
Select for auxiliar field
 */
  public static String selectActP1004400000_C_BPartner_ID(ConnectionProvider connectionProvider, String M_Requisition_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT M_Requisition.C_Bpartner_Id FROM M_Requisition, (SELECT count(*) as SameBP FROM M_Requisition inner join M_requisitionLine ON M_Requisition.M_Requisition_id = M_requisitionLine.M_Requisition_id WHERE M_Requisition.c_bpartner_id = M_requisitionLine.c_bpartner_id AND M_Requisition.M_Requisition_id = ?) SameBP,  (SELECT count(*) as QtyLines FROM M_RequisitionLine WHERE M_RequisitionLine.M_Requisition_id=?) QtyLines  WHERE SameBP.SameBP = QtyLines.QtyLines AND M_Requisition.M_Requisition_id =? ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, M_Requisition_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, M_Requisition_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, M_Requisition_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "c_bpartner_id");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }

/**
Select for auxiliar field
 */
  public static String selectActP1004400000_M_PriceList_ID(ConnectionProvider connectionProvider, String M_Requisition_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT M_Requisition.m_pricelist_id  FROM M_Requisition,       (SELECT count(*) as SamePL        FROM M_Requisition inner join M_requisitionLine ON M_Requisition.M_Requisition_id = M_requisitionLine.M_Requisition_id       WHERE M_Requisition.m_pricelist_id = M_requisitionLine.m_pricelist_id AND M_Requisition.M_Requisition_id = ?) SamePL,        (SELECT count(*) as QtyLines       FROM M_RequisitionLine       WHERE M_RequisitionLine.M_Requisition_id=?) QtyLines   WHERE SamePL.SamePL = QtyLines.QtyLines AND M_Requisition.M_Requisition_id =? ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, M_Requisition_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, M_Requisition_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, M_Requisition_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "m_pricelist_id");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }

/**
Select for auxiliar field
 */
  public static String selectActPFF8081813219E68E013219ECFE930004_Name(ConnectionProvider connectionProvider, String MA_SEQUENCEPRODUCT_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT M_PRODUCT.NAME||' - '||MA_SEQUENCE.NAME||' - '||MA_SEQUENCE.SEQNO AS name FROM MA_SEQUENCEPRODUCT JOIN MA_SEQUENCE ON MA_SEQUENCE.MA_SEQUENCE_ID = MA_SEQUENCEPRODUCT.MA_SEQUENCE_ID LEFT JOIN M_PRODUCT ON MA_SEQUENCEPRODUCT.M_PRODUCT_ID = M_PRODUCT.M_PRODUCT_ID WHERE MA_SEQUENCEPRODUCT_ID = ? ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, MA_SEQUENCEPRODUCT_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "name");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }

/**
Select for auxiliar field
 */
  public static String selectActPFF8081813219E68E013219ECFE930004_Value(ConnectionProvider connectionProvider, String MA_SEQUENCEPRODUCT_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT M_PRODUCT.VALUE||' - '||MA_SEQUENCE.VALUE||' - '||MA_SEQUENCE.SEQNO AS value FROM MA_SEQUENCEPRODUCT JOIN MA_SEQUENCE ON MA_SEQUENCE.MA_SEQUENCE_ID = MA_SEQUENCEPRODUCT.MA_SEQUENCE_ID LEFT JOIN M_PRODUCT ON MA_SEQUENCEPRODUCT.M_PRODUCT_ID = M_PRODUCT.M_PRODUCT_ID WHERE MA_SEQUENCEPRODUCT_ID = ? ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, MA_SEQUENCEPRODUCT_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "value");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }

/**
Select for auxiliar field
 */
  public static String selectActP15C8708DFC464C2D91286E59624FDD18_C_GLItem_ID(ConnectionProvider connectionProvider, String C_GLItem_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT name FROM C_GLItem WHERE C_GLItem_ID=? ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, C_GLItem_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "name");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }

/**
Select for auxiliar field
 */
  public static String selectActPB670ED126EC0470A966FB31C6EEB8647_c_period_id(ConnectionProvider connectionProvider, String AD_ORG_ID, String AD_CLIENT_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT P.C_PERIOD_ID AS DEFAULTVALUE FROM C_PERIOD P WHERE EXISTS (SELECT * FROM C_PERIODCONTROL PC WHERE P.C_PERIOD_ID=PC.C_PERIOD_ID AND UPPER(PC.PERIODSTATUS)='O') AND EXISTS(SELECT * FROM C_CALENDAR C, C_YEAR Y WHERE Y.C_CALENDAR_ID=C.C_CALENDAR_ID AND P.C_YEAR_ID=Y.C_YEAR_ID AND AD_ISORGINCLUDED(?, C.AD_ORG_ID, ?)<> -1) AND P.AD_CLIENT_ID=? AND NOW() BETWEEN STARTDATE AND ENDDATE ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_ORG_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_CLIENT_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_CLIENT_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "defaultvalue");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }

/**
Select for auxiliar field
 */
  public static String selectActPBB3C25ED65BC4816A097E6F4E6E179B8_c_period_id(ConnectionProvider connectionProvider, String AD_ORG_ID, String AD_CLIENT_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT P.C_PERIOD_ID AS DEFAULTVALUE FROM C_PERIOD P WHERE EXISTS (SELECT * FROM C_PERIODCONTROL PC WHERE P.C_PERIOD_ID=PC.C_PERIOD_ID AND UPPER(PC.PERIODSTATUS)='O') AND EXISTS(SELECT * FROM C_CALENDAR C, C_YEAR Y WHERE Y.C_CALENDAR_ID=C.C_CALENDAR_ID AND P.C_YEAR_ID=Y.C_YEAR_ID AND AD_ISORGINCLUDED(?, C.AD_ORG_ID, ?)<> -1) AND P.AD_CLIENT_ID=? AND NOW() BETWEEN STARTDATE AND ENDDATE ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_ORG_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_CLIENT_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_CLIENT_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "defaultvalue");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }

/**
Select for auxiliar field
 */
  public static String selectActPF03D64524CD642EFA6DDC11150EDC498_c_period_id(ConnectionProvider connectionProvider, String AD_ORG_ID, String AD_CLIENT_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT P.C_PERIOD_ID AS DEFAULTVALUE FROM C_PERIOD P WHERE EXISTS (SELECT * FROM C_PERIODCONTROL PC WHERE P.C_PERIOD_ID=PC.C_PERIOD_ID AND UPPER(PC.PERIODSTATUS)='O') AND EXISTS(SELECT * FROM C_CALENDAR C, C_YEAR Y WHERE Y.C_CALENDAR_ID=C.C_CALENDAR_ID AND P.C_YEAR_ID=Y.C_YEAR_ID AND AD_ISORGINCLUDED(?, C.AD_ORG_ID, ?)<> -1) AND P.AD_CLIENT_ID=? AND NOW() BETWEEN STARTDATE AND ENDDATE ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_ORG_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_CLIENT_ID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, AD_CLIENT_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "defaultvalue");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(strReturn);
  }
}
