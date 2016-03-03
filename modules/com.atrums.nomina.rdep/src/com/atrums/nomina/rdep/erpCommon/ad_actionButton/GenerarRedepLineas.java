package com.atrums.nomina.rdep.erpCommon.ad_actionButton;

import java.sql.ResultSet;

import org.openbravo.dal.core.OBContext;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

import com.atrums.nomina.rdep.util.UtilProcesoProcedure;

public class GenerarRedepLineas extends DalBaseProcess {

  private ConnectionProvider connection;

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    // TODO Auto-generated method stub
    try {
      final String atrdepCabId = (String) bundle.getParams().get("Atrdep_Cabecera_Reten_ID");

      connection = bundle.getConnection();

      bundle.setResult(LineasRedep(connection, atrdepCabId));

    } catch (final Exception e) {

      e.printStackTrace(System.err);
      final OBError msg = new OBError();
      msg.setMessage(e.getMessage());
      msg.setTitle(Utility.messageBD(new DalConnectionProvider(), ERROR, OBContext.getOBContext()
          .getLanguage().getLanguage()));
      bundle.setResult(msg);
    }

  }

  OBError LineasRedep(ConnectionProvider connection, String atrdepCabId) {

    final OBError miMensaje = new OBError();

    String strMsg = "";
    String strTipoMsg = "";
    StringBuffer sp;
    ResultSet result = null;

    try {

      sp = UtilProcesoProcedure.ejecutaProcedure(connection, "atrdep_listar_datos", atrdepCabId);
      if (result.getRow() == 0) {
        strTipoMsg = "Error";
        strMsg = "No existen registros para generar el archivo";
      }

    } catch (final Exception e) {

    } finally {

      miMensaje.setType(strTipoMsg);
      miMensaje.setMessage(strMsg);
      miMensaje.setTitle(Utility.messageBD(new DalConnectionProvider(), strTipoMsg, OBContext
          .getOBContext().getLanguage().getLanguage()));
    }

    return miMensaje;
  }

}
