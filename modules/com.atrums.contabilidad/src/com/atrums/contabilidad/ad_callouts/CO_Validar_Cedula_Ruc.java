package com.atrums.contabilidad.ad_callouts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.xmlEngine.XmlDocument;

public class CO_Validar_Cedula_Ruc extends HttpSecureAppServlet {

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
      String strCBPartnerId = vars.getStringParameter("inpcBpartnerId");
      String strtaxid = vars.getStringParameter("inptaxid");
      String stremCoTipoIdentificacion = vars.getStringParameter("inpemCoTipoIdentificacion");

      try {
        if (strtaxid != null)
          printPage(response, vars, strCBPartnerId, strtaxid, stremCoTipoIdentificacion);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String strCBPartnerId, String strtaxid, String stremCoTipoIdentificacion) throws IOException,
      ServletException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/contabilidad/ad_callouts/CallOut").createXmlDocument();

    StringBuffer result = new StringBuffer();
    String mensaje = "";

    mensaje = validarDocumento(vars, strtaxid, stremCoTipoIdentificacion); // Llama
    // al
    // metodo
    // de
    // validación

    if (!mensaje.equals("")) {
      String strConvRateErrorMsg = "";
      String cadena = "CI/RUC/Pas:";

      strConvRateErrorMsg = cadena + strtaxid + mensaje;
      // FIN
      result.append("var calloutName='CO_Validar_Cedula_Ruc';\n\n");
      result.append("var respuesta = new Array(new Array(\"MESSAGE\", ");
      result.append("\"" + " " + strConvRateErrorMsg + "\"),");

      result.append("new Array(\"inptaxid\", \"" + "" + "\")");
      result.append(");");

    } /*else {
      result.append("var calloutName='CO_Validar_Cedula_Ruc';\n\n");
      result.append("var respuesta = new Array(");
      result.append("new Array(\"inpvalue\", \"" + strtaxid.trim() + "\")");
      result.append(");");
    }*/

    // inject the generated code
    xmlDocument.setParameter("array", result.toString());

    xmlDocument.setParameter("frameName", "appFrame");

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }

  /** Valida la cedula o el ruc */
  private String validarDocumento(VariablesSecureApp vars, String numero, String tipoIdentificacion) {
    boolean valor = true;
    String mensaje = "";
    
    

    if (tipoIdentificacion.equals("02") || tipoIdentificacion.equals("01")) {

      try {
        int suma = 0;
        int residuo = 0;
        boolean privada = false;
        boolean publica = false;
        boolean natural = false;
        int numeroProvincias = 24;
        int digitoVerificador = 0;
        int modulo = 11;

        int d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;
        int p1, p2, p3, p4, p5, p6, p7, p8, p9;

        d1 = d2 = d3 = d4 = d5 = d6 = d7 = d8 = d9 = d10 = 0;
        p1 = p2 = p3 = p4 = p5 = p6 = p7 = p8 = p9 = 0;

        if (numero.length() < 10) {
          // mensaje = "  No es válido";
          mensaje = " CI/RUC no válido";

          valor = false;
        }

        // Los primeros dos digitos corresponden al codigo de la
        // provincia
        int provincia = Integer.parseInt(numero.substring(0, 2));

        if (provincia <= 0 || provincia > numeroProvincias) {
          mensaje = " CI/RUC no válido";
          // JOptionPane
          // .showMessageDialog(Motor.getVentana(), "El c" + Motor.o
          // + "digo de la provincia (dos primeros d" + Motor.i +
          // "gitos) es inv" + Motor.a
          // + "lido");
          valor = false;
        }

        // Aqui almacenamos los digitos de la cedula en variables.
        d1 = Integer.parseInt(numero.substring(0, 1));
        d2 = Integer.parseInt(numero.substring(1, 2));
        d3 = Integer.parseInt(numero.substring(2, 3));
        d4 = Integer.parseInt(numero.substring(3, 4));
        d5 = Integer.parseInt(numero.substring(4, 5));
        d6 = Integer.parseInt(numero.substring(5, 6));
        d7 = Integer.parseInt(numero.substring(6, 7));
        d8 = Integer.parseInt(numero.substring(7, 8));
        d9 = Integer.parseInt(numero.substring(8, 9));
        d10 = Integer.parseInt(numero.substring(9, 10));

        // El tercer digito es:
        // 9 para sociedades privadas y extranjeros
        // 6 para sociedades publicas
        // menor que 6 (0,1,2,3,4,5) para personas naturales
        if (d3 == 7 || d3 == 8) {
          mensaje = " CI/RUC no válido";
          // JOptionPane.showMessageDialog(Motor.getVentana(),
          // "El tercer d" + Motor.i
          // + "gito ingresado es inv" + Motor.a + "lido");
          valor = false;
        }

        // Solo para personas naturales (modulo 10)
        if (d3 < 6) {
          natural = true;
          modulo = 10;
          p1 = d1 * 2;
          if (p1 >= 10)
            p1 -= 9;
          p2 = d2 * 1;
          if (p2 >= 10)
            p2 -= 9;
          p3 = d3 * 2;
          if (p3 >= 10)
            p3 -= 9;
          p4 = d4 * 1;
          if (p4 >= 10)
            p4 -= 9;
          p5 = d5 * 2;
          if (p5 >= 10)
            p5 -= 9;
          p6 = d6 * 1;
          if (p6 >= 10)
            p6 -= 9;
          p7 = d7 * 2;
          if (p7 >= 10)
            p7 -= 9;
          p8 = d8 * 1;
          if (p8 >= 10)
            p8 -= 9;
          p9 = d9 * 2;
          if (p9 >= 10)
            p9 -= 9;
        }

        // Solo para sociedades publicas (modulo 11)
        // Aqui el digito verficador esta en la posicion 9, en las otras
        // 2
        // en la pos. 10
        if (d3 == 6) {
          publica = true;
          p1 = d1 * 3;
          p2 = d2 * 2;
          p3 = d3 * 7;
          p4 = d4 * 6;
          p5 = d5 * 5;
          p6 = d6 * 4;
          p7 = d7 * 3;
          p8 = d8 * 2;
          p9 = 0;
        }

        /* Solo para entidades privadas (modulo 11) */
        if (d3 == 9) {
          privada = true;
          p1 = d1 * 4;
          p2 = d2 * 3;
          p3 = d3 * 2;
          p4 = d4 * 7;
          p5 = d5 * 6;
          p6 = d6 * 5;
          p7 = d7 * 4;
          p8 = d8 * 3;
          p9 = d9 * 2;
        }

        suma = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;
        residuo = suma % modulo;

        // Si residuo=0, dig.ver.=0, caso contrario 10 - residuo
        digitoVerificador = residuo == 0 ? 0 : modulo - residuo;
        int longitud = numero.length(); // Longitud del string

        // ahora comparamos el elemento de la posicion 10 con el dig.
        // ver.

        if (publica == true) {
          if (digitoVerificador != d9) {
            mensaje = "  El RUC de la empresa del sector público es incorrecto";
            // JOptionPane.showMessageDialog(Motor.getVentana(),
            // "El ruc de la empresa del sector p"
            // + Motor.u + "blico es incorrecto.");
            valor = false;
          }
          /*
           * El ruc de las empresas del sector publico terminan con 0001
           */
          if (!numero.substring(9, longitud).equals("0001")) {
            mensaje = "  El RUC de la empresa del sector público debe terminar con 0001";
            // JOptionPane.showMessageDialog(Motor.getVentana(),
            // "El ruc de la empresa del sector p"
            // + Motor.u + "blico debe terminar con 0001");
            valor = false;
          }
          if (!tipoIdentificacion.equals("01")) {
            mensaje = "Tipo identificacion incorrecto";
            valor = false;
          }
        }

        if (privada == true) {
          if (digitoVerificador != d10) {
            mensaje = "  El RUC de la empresa del sector privado es incorrecto";
            // JOptionPane.showMessageDialog(Motor.getVentana(),
            // "El ruc de la empresa del sector privado es incorrecto.");
            valor = false;
          }
          if (!numero.substring(10, longitud).equals("001")) {
            mensaje = "  El RUC de la empresa del sector privado debe terminar con 001";
            // JOptionPane.showMessageDialog(Motor.getVentana(),
            // "El ruc de la empresa del sector privado debe terminar con 001");
            valor = false;
          }
          if (!tipoIdentificacion.equals("01")) {
            mensaje = "Tipo identificacion incorrecto";
            valor = false;
          }
        }

        if (natural == true) {
          if (digitoVerificador != d10) {

            mensaje = "  El número de cédula de la persona natural es incorrecto";
            valor = false;
          }
          if (numero.length() > 10 && !numero.substring(10, longitud).equals("001")) {

            mensaje = "  El ruc de la persona natural debe terminar con 001";
            // JOptionPane.showMessageDialog(Motor.getVentana(),
            // "El ruc de la persona natural debe terminar con 001");
            valor = false;
          }

          if (mensaje.equals("")) {

            if (numero.length() > 10 && tipoIdentificacion.equals("02")) {
              mensaje = "Tipo identificacion incorrecto";
              valor = false;
            } else if (numero.length() == 10 && tipoIdentificacion.equals("01")) {
              mensaje = "Tipo identificacion incorrecto";
              valor = false;
            }

          }

        }
      } catch (Exception e) {
        valor = false;
        mensaje = " CI/RUC no válido";
      }
    }
    return mensaje;
  }
}
