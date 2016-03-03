package com.atrums.nomina.ad_process;

import org.apache.log4j.Logger;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.scheduling.Process;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessLogger;
import org.quartz.JobExecutionException;

public class NoGeneraValorMesProvision implements Process {

  private static Logger log = Logger.getLogger(NoGeneraValorMesProvision.class);

  private ConnectionProvider connection;
  private ProcessLogger logger;

  private static int counter = 0;

  @Override
  public void execute(ProcessBundle bundle) throws Exception {
    logger = bundle.getLogger();
    connection = bundle.getConnection();

    logger.log("Iniciando proceso de generacion de privision mensual . Intento " + counter + "\n");

    try {

      // Process all clients
      UtilProcedure.ejecutaFuncion(connection, "no_genera_valor_rprovision()");

    } catch (Exception e) {
      throw new JobExecutionException(e.getMessage(), e);
    } finally {
      logger.log("Proceso Finalizado ");
    }
  }
}
