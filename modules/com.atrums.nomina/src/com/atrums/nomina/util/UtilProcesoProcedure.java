package com.atrums.nomina.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.openbravo.data.FieldProvider;
import org.openbravo.data.UtilSql;
import org.openbravo.database.ConnectionProvider;

public class UtilProcesoProcedure implements FieldProvider {
  static Logger log4j = Logger.getLogger(UtilProcesoProcedure.class);
  private String InitRecordNumber = "0";
  public String adUserId;
  public String adOrgId;
  public String adTableId;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public static String ejecutaProcedure(ConnectionProvider connectionProvider,
      String srtrProccedure, String strParametro, String strColumnName) throws ServletException {
    String strSql = "";
    strSql = strSql + " SELECT * FROM " + srtrProccedure.toUpperCase() + "('" + strParametro + "')";

    ResultSet result;
    String strReturn = null;
    PreparedStatement st = null;

    try {
      st = connectionProvider.getPreparedStatement(strSql);
      result = st.executeQuery();

      if (result.next()) {
        strReturn = UtilSql.getValue(result, strColumnName);
      }
      result.close();
    } catch (SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());
    } catch (Exception ex) {
      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch (Exception ignore) {
        ignore.printStackTrace();
      }
    }
    return (strReturn);
  }

  public static String ejecutaProcedure(Connection connection, String srtrProccedure,
      String strParametro, String strColumnName) throws ServletException {
    String strSql = "";
    strSql = strSql + " SELECT * FROM " + srtrProccedure.toUpperCase() + "(" + strParametro + ")";

    ResultSet result;
    String strReturn = null;
    PreparedStatement st = null;

    try {
      st = connection.prepareStatement(strSql);
      result = st.executeQuery();

      if (result.next()) {
        strReturn = UtilSql.getValue(result, strColumnName);
      }
      result.close();
    } catch (SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());
    } catch (Exception ex) {
      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {

        // connection.(st);
      } catch (Exception ignore) {
        ignore.printStackTrace();
      }
    }
    return (strReturn);
  }

  public static String ejecutaProcedure(ConnectionProvider connectionProvider,
      String srtrProccedure, String strParametro, String strParametro1, String strParametro2,
      String strParametro3, String strColumnName) throws ServletException {
    String strSql = "";
    strSql = strSql + " SELECT * FROM " + srtrProccedure.toUpperCase() + "('" + strParametro
        + "','" + strParametro1 + "','" + strParametro2 + "','" + strParametro3 + "')";

    ResultSet result;
    String strReturn = null;
    PreparedStatement st = null;

    try {
      st = connectionProvider.getPreparedStatement(strSql);
      result = st.executeQuery();

      if (result.next()) {
        strReturn = UtilSql.getValue(result, strColumnName);
      }
      result.close();
    } catch (SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());
    } catch (Exception ex) {
      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch (Exception ignore) {
        ignore.printStackTrace();
      }
    }
    return (strReturn);
  }

  public static String ejecutaProcedure(ConnectionProvider connectionProvider,
      String srtrProccedure, String strParametro, String strParametro1, String strParametro2,
      String strColumnName) throws ServletException {
    String strSql = "";
    strSql = strSql + " SELECT * FROM " + srtrProccedure.toUpperCase() + "('" + strParametro
        + "','" + strParametro1 + "','" + strParametro2 + "')";

    ResultSet result;
    String strReturn = null;
    PreparedStatement st = null;

    try {
      st = connectionProvider.getPreparedStatement(strSql);
      result = st.executeQuery();

      if (result.next()) {
        strReturn = UtilSql.getValue(result, strColumnName);
      }
      result.close();
    } catch (SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());
    } catch (Exception ex) {
      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch (Exception ignore) {
        ignore.printStackTrace();
      }
    }
    return (strReturn);
  }

  public static ArrayList<String> ejecutaProcedure(ConnectionProvider connectionProvider,
      String srtrProccedure) throws ServletException {
    String strSql = "";

    strSql = strSql + " SELECT " + srtrProccedure.toUpperCase() + "()";

    ResultSet result;
    ArrayList<String> arlLisat = new ArrayList<String>();

    PreparedStatement st = null;

    try {
      st = connectionProvider.getPreparedStatement(strSql);
      result = st.executeQuery();
      while (result.next()) {
        arlLisat.add(result.getObject(1).toString());
      }
      result.close();
    } catch (SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());
    } catch (Exception ex) {
      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch (Exception ignore) {
        ignore.printStackTrace();
      }
    }
    return arlLisat;
  }

  public static ArrayList<HashMap<String, Object>> SelectFrom(
      ConnectionProvider connectionProvider, String srtrProccedure) throws ServletException {
    String strSql = "";

    strSql = strSql + " SELECT * FROM " + srtrProccedure.toUpperCase() + "()";

    ResultSet result;
    ArrayList<HashMap<String, Object>> arlLisat = new ArrayList<HashMap<String, Object>>();

    PreparedStatement st = null;

    try {
      st = connectionProvider.getPreparedStatement(strSql);
      result = st.executeQuery();
      do {
        arlLisat = resultSetToArrayList(result);
      } while (result.next());
      result.close();
    } catch (SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());
    } catch (Exception ex) {
      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch (Exception ignore) {
        ignore.printStackTrace();
      }
    }
    return arlLisat;
  }

  private static ArrayList<HashMap<String, Object>> resultSetToArrayList(ResultSet rs)
      throws SQLException {

    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();

    while (rs.next()) {

      HashMap<String, Object> row = new HashMap<String, Object>();

      results.add(row);

      for (int i = 1; i <= columns; i++) {

        row.put(md.getColumnName(i), rs.getObject(i));

      }

    }
    return results;

  }

  @Override
  public String getField(String fieldName) {
    // TODO Auto-generated method stub
    return null;
  }

}