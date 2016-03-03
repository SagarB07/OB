package com.atrums.nomina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.openbravo.client.kernel.BaseComponentProvider;
import org.openbravo.client.kernel.Component;
import org.openbravo.client.kernel.ComponentProvider;
import org.openbravo.client.kernel.KernelConstants;

@ApplicationScoped
@ComponentProvider.Qualifier(RolProvisionComponentProvider.ROL_PROVISION_VIEW_COMPONENT_TYPE)
public class RolProvisionComponentProvider extends BaseComponentProvider {

  public static final String ROL_PROVISION_VIEW_COMPONENT_TYPE = "OBEXAPP_RolProvisionViewType";

  @Override
  public Component getComponent(String componentId, Map<String, Object> parameters) {
    // TODO Auto-generated method stub
    if (componentId.equals(RolProvisionViewComponent.ROL_PROVISION_VIEW_COMPONENT_ID)) {
      final RolProvisionViewComponent component = new RolProvisionViewComponent();
      component.setId(RolProvisionViewComponent.ROL_PROVISION_VIEW_COMPONENT_ID);
      component.setParameters(parameters);
      return component;
    }
    throw new IllegalArgumentException("Component id " + componentId + " not supported.");
  }

  @Override
  public List<ComponentResource> getGlobalComponentResources() {
    final List<ComponentResource> globalResources = new ArrayList<ComponentResource>();
    globalResources.add(createStaticResource(
        "web/com.atrums.nomina/js/rolprovision-prov-toolbar-button.js", false));
    globalResources
        .add(createStyleSheetResource(
            "web/org.openbravo.userinterface.smartclient/openbravo/skins/"
                + KernelConstants.SKIN_PARAMETER + "/com.atrums.nomina/rolprovision-styles.css",
            false));

    return globalResources;
  }

  @Override
  public List<String> getTestResources() {
    return Collections.emptyList();
  }
}
