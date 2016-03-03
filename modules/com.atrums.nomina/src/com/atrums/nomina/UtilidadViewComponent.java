package com.atrums.nomina;

import org.openbravo.client.kernel.BaseTemplateComponent;
import org.openbravo.dal.core.OBContext;

public class UtilidadViewComponent extends BaseTemplateComponent {
  public static final String UTILIDAD_VIEW_COMPONENT_ID = "UtilidadView";

  public String getUserName() {
    return OBContext.getOBContext().getUser().getName();
  }
}
