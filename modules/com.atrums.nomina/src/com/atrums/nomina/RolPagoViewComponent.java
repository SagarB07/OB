package com.atrums.nomina;

import org.openbravo.client.kernel.BaseTemplateComponent;
import org.openbravo.dal.core.OBContext;

public class RolPagoViewComponent extends BaseTemplateComponent {
  public static final String ROL_PAGO_VIEW_COMPONENT_ID = "RolPagoView";

  public String getUserName() {
    return OBContext.getOBContext().getUser().getName();
  }
}
