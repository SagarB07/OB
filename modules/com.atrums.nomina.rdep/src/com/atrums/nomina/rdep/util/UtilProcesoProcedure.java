package com.atrums.nomina.rdep.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
    strSql = strSql + " SELECT * FROM " + srtrProccedure.toUpperCase() + "(" + strParametro + ")";

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
    } catch (final SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());
    } catch (final Exception ex) {
      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch (final Exception ignore) {
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
    final ArrayList<String> arlLisat = new ArrayList<String>();

    PreparedStatement st = null;

    try {
      st = connectionProvider.getPreparedStatement(strSql);
      result = st.executeQuery();
      while (result.next()) {
        arlLisat.add(result.getObject(1).toString());
      }
      result.close();
    } catch (final SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());
    } catch (final Exception ex) {
      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch (final Exception ignore) {
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
    } catch (final SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());
    } catch (final Exception ex) {
      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch (final Exception ignore) {
        ignore.printStackTrace();
      }
    }
    return arlLisat;
  }

  private static ArrayList<HashMap<String, Object>> resultSetToArrayList(ResultSet rs)
      throws SQLException {

    final ResultSetMetaData md = rs.getMetaData();
    final int columns = md.getColumnCount();
    final ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();

    while (rs.next()) {

      final HashMap<String, Object> row = new HashMap<String, Object>();

      results.add(row);

      for (int i = 1; i <= columns; i++) {

        row.put(md.getColumnName(i), rs.getObject(i));

      }

    }
    return results;

  }

  public static StringBuffer ejecutaProcedure(ConnectionProvider connection, String procedure,
      String param) throws ServletException {

    String strSql = "";
    StringBuffer sb;

    strSql = strSql + "SELECT * FROM " + procedure.toUpperCase() + "('" + param + "')";

    ResultSet result;
    PreparedStatement st = null;
    try {
      st = connection.getPreparedStatement(strSql);
      result = st.executeQuery();

      sb = ConstruirXML("rdep", result);

      result.close();

      BufferedWriter salida = new BufferedWriter(new FileWriter("D:\\retencionesPrueba.xml"));
      String outText = sb.toString();
      salida.write(outText);
      salida.close();
    } catch (final SQLException e) {
      log4j.error("SQL error in query: " + strSql + "Exception:" + e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@"
          + e.getMessage());

    } catch (final Exception ex) {

      log4j.error("Exception in query: " + strSql + "Exception:" + ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    }

    finally {
      try {

      } catch (final Exception ig) {

        ig.printStackTrace();
      }

    }

    return sb;
  }

  public static StringBuffer ConstruirXML(String nombreTabla, ResultSet tabla) {

    StringBuffer xml = new StringBuffer();

    // xml.append("<?xml version='1.0'?>\n");
    try {

      ResultSetMetaData rsmd = tabla.getMetaData();

      xml.append("<" + nombreTabla + ">\n");
      int columnAux = 1;
      while (columnAux < 3 && tabla.first()) {
        xml.append("\t\t<" + rsmd.getColumnName(columnAux) + ">" + tabla.getString(columnAux)
            + "</" + rsmd.getColumnName(columnAux) + ">\n");

        columnAux++;
      }
      tabla.beforeFirst();
      xml.append("\t\t<retRelDep>\n");

      while (tabla.next()) // Fila
      {

        xml.append("\t\t<datRetRelDep>\n");

        for (int columna = 3; columna <= rsmd.getColumnCount(); columna++) // Columna
        {
          if (tabla.getString(columna) != null) {
            xml.append("\t\t<" + rsmd.getColumnName(columna) + ">" + tabla.getString(columna)
                + "</" + rsmd.getColumnName(columna) + ">\n");
          } else {
            xml.append("\t\t<" + rsmd.getColumnName(columna) + "></" + rsmd.getColumnName(columna)
                + ">\n");
          }
        }
        xml.append("\t\t</datRetRelDep>\n");

      }
      xml.append("\t\t</retRelDep>\n");
      xml.append("</" + nombreTabla + ">\n");

      return xml;
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
    return null;
  }

  @Override
  public String getField(String fieldName) {
    // TODO Auto-generated method stub
    return null;
  }

}