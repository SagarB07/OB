package com.atrums.nomina;

import org.openbravo.client.kernel.BaseTemplateComponent;
import org.openbravo.dal.core.OBContext;

public class PagoNominaViewComponent extends BaseTemplateComponent {
  public static final String PAGO_NOMINA_VIEW_COMPONENT_ID = "PagoNominaView";

  public String getUserName() {
    return OBContext.getOBContext().getUser().getName();
  }
}
