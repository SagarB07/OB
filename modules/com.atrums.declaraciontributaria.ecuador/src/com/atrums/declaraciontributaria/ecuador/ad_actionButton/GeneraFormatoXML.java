package com.atrums.declaraciontributaria.ecuador.ad_actionButton;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class GeneraFormatoXML {

  public static StringBuffer ConstruirXML(String nombreTabla, String detalle, ResultSet tabla) {

    StringBuffer xml = new StringBuffer();

    // xml.append("<?xml version='1.0'?>\n");
    try {

      ResultSetMetaData rsmd = tabla.getMetaData();

      xml.append("<" + nombreTabla + ">\n");

      while (tabla.next()) // Fila
      {
        xml.append("\t<" + detalle + nombreTabla + ">\n");
        for (int columna = 1; columna <= rsmd.getColumnCount(); columna++) // Columna
        {
          if (tabla.getString(columna) != null) {
            xml.append("\t\t<" + rsmd.getColumnName(columna) + ">" + tabla.getString(columna)
                + "</" + rsmd.getColumnName(columna) + ">\n");
          } else {
            xml.append("\t\t<" + rsmd.getColumnName(columna) + "></" + rsmd.getColumnName(columna)
                + ">\n");
          }
        }
        xml.append("\t</" + detalle + nombreTabla + ">\n");
      }

      xml.append("</" + nombreTabla + ">\n");

      return xml;
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
    return null;
  }
}