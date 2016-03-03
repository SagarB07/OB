/*package com.atrums.indumot.datosmaestros.erpCommon.ad_callouts;

public class ATIMDM_Concatena_Nombre_Comercial {

}
 */
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

public class CO_Concatena_Nombre_Comercial extends HttpSecureAppServlet {

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
      String strEmCoNombre = vars.getStringParameter("inpemCoNombres");
      String strEmCoApellido = vars.getStringParameter("inpemCoApellidos");

      try {
        printPage(response, vars, strCBPartnerId, strEmCoNombre, strEmCoApellido);
      } catch (ServletException ex) {
        pageErrorCallOut(response);
      }
    } else
      pageError(response);
  }

  private void printPage(HttpServletResponse response, VariablesSecureApp vars,
      String strCBpGroupId, String strEmCoNombre, String strEmCoApellido) throws IOException,
      ServletException {
    log4j.debug("Output: dataSheet");

    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "com/atrums/contabilidad/ad_callouts/CallOut").createXmlDocument();

    StringBuffer result = new StringBuffer();
    result.append("var calloutName='CO_Concatena_Nombre_Comercial';\n\n");

    result.append("var respuesta = new Array(");

    if (strEmCoNombre == null) {
      strEmCoNombre = "";
      // copiar a razon social
      result.append("new Array(\"inpname2\",\"" + strEmCoApellido.trim() + " "
          + strEmCoNombre.trim() + "\")");
      result.append(");");

    } else if (strEmCoApellido == null) {
      strEmCoApellido = "";
      // copiar a razon social
      result.append("new Array(\"inpname2\", \"" + strEmCoApellido.trim() + " "
          + strEmCoNombre.trim() + "\")");
      result.append(");");

    } else {
      // copiar a razon social
      result.append("new Array(\"inpname2\", \"" + strEmCoApellido + " "
          + strEmCoNombre.trim() + "\")");
      result.append(");");

    }

    // inject the generated code xmlDocument.setParameter("array",
    // result.toString());
    xmlDocument.setParameter("array", result.toString());
    xmlDocument.setParameter("frameName", "appFrame");

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(xmlDocument.print());
    out.close();
  }
}
