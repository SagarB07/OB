package com.atrums.nomina;

import org.openbravo.client.kernel.BaseTemplateComponent;
import org.openbravo.dal.core.OBContext;

public class NominaViewComponent extends BaseTemplateComponent {
  public static final String NOMINA_VIEW_COMPONENT_ID = "NominaView";

  public String getUserName() {
    return OBContext.getOBContext().getUser().getName();
  }
}
