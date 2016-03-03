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
@ComponentProvider.Qualifier(NominaComponentProvider.NOMINA_VIEW_COMPONENT_TYPE)
public class NominaComponentProvider extends BaseComponentProvider {

  public static final String NOMINA_VIEW_COMPONENT_TYPE = "OBEXAPP_NominaViewType";

  @Override
  public Component getComponent(String componentId, Map<String, Object> parameters) {
    // TODO Auto-generated method stub
    if (componentId.equals(NominaViewComponent.NOMINA_VIEW_COMPONENT_ID)) {
      final NominaViewComponent component = new NominaViewComponent();
      component.setId(NominaViewComponent.NOMINA_VIEW_COMPONENT_ID);
      component.setParameters(parameters);
      return component;
    }
    throw new IllegalArgumentException("Component id " + componentId + " not supported.");
  }

  @Override
  public List<ComponentResource> getGlobalComponentResources() {
    final List<ComponentResource> globalResources = new ArrayList<ComponentResource>();
    globalResources.add(createStaticResource(
        "web/com.atrums.nomina/js/liquidacion-liqu-toolbar-button.js", false));
    globalResources.add(createStyleSheetResource(
        "web/org.openbravo.userinterface.smartclient/openbravo/skins/"
            + KernelConstants.SKIN_PARAMETER + "/com.atrums.nomina/liquidacion-styles.css", false));

    return globalResources;
  }

  @Override
  public List<String> getTestResources() {
    return Collections.emptyList();
  }
}
