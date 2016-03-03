package com.atrums.nomina;

import org.openbravo.client.kernel.BaseTemplateComponent;
import org.openbravo.dal.core.OBContext;

public class RolProvisionViewComponent extends BaseTemplateComponent {
  public static final String ROL_PROVISION_VIEW_COMPONENT_ID = "RolProvisionView";

  public String getUserName() {
    return OBContext.getOBContext().getUser().getName();
  }
}
