package com.atrums.nomina;

import org.openbravo.client.kernel.BaseTemplateComponent;
import org.openbravo.dal.core.OBContext;

public class AvanceViewComponent extends BaseTemplateComponent {
  public static final String AVANCE_VIEW_COMPONENT_ID = "AvanceView";

  public String getUserName() {
    return OBContext.getOBContext().getUser().getName();
  }
}
