package com.atrums.nomina.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.xmlEngine.XmlDocument;

public class NO_Validafondosdereserva extends HttpSecureAppServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      // Uso del campo fecha inicial
      String strfechaInicio = vars.getStringParameter("inpfechaInicio");

      // Obtencion del valor del campo de idBpartner
      String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");

      // Obtencion del valor del campo de esFondoReserva
      // String strcPagofondoreserva = vars.getStringParameter("inppagofondoreserva");

      try {
        printPage(response, vars, strfechaInicio, strcBpartnerId);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String strfechaInicio, String strcBpartnerId) throws IOException, ServletException,
      ParseException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine

    .readXmlTemplate("com/atrums/nomina/ad_callouts/CallOut").createXmlDocument();

    // Obtener la fecha de inicio a partir de la consulta SQL
    String strFechaInicioCon = NOValidafondosdereservaData.select(this, strcBpartnerId);

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    // Transformar a tipo Date dd-mm-yyyy
    Date dtfechInicioCont = sdf2.parse(strFechaInicioCon);
    Date dtfechInicio = sdf.parse(strfechaInicio);

    // Para obtener el resultado
    long fechaInicialMs = dtfechInicio.getTime();
    long fechaFinalMs = dtfechInicioCont.getTime();
    long diferencia = fechaInicialMs - fechaFinalMs;

    // Para transformar en dias los milisegundos
    int dias = (int) Math.floor(diferencia / (1000 * 60 * 60 * 24));

    // Si es menor al año
    if (dias < 365)

    {
      StringBuffer resultado = new StringBuffer();
      resultado.append("var calloutName='NO_Validafondosdereserva';\n\n");
      resultado.append("var respuesta = new Array(");
      resultado.append("new Array('ERROR', \""
          + "Los pagos del fondo de reserva se hace a partir del año, "
          + "el primer contrato del tercero fue hace " + dias + " días"
          // + "\nFONDO RESERVA "+ strcPagofondoreserva +" "
          + "\") ");
      resultado.append(");");
      xmlDocument.setParameter("array", resultado.toString());
      xmlDocument.setParameter("frameName", "appFrame");
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println(xmlDocument.print());
      out.close();
    }

  }

}