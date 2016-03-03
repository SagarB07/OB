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
@ComponentProvider.Qualifier(UtilidadComponentProvider.UTILIDAD_VIEW_COMPONENT_TYPE)
public class UtilidadComponentProvider extends BaseComponentProvider {

  public static final String UTILIDAD_VIEW_COMPONENT_TYPE = "OBEXAPP_UtilidadViewType";

  @Override
  public Component getComponent(String componentId, Map<String, Object> parameters) {
    // TODO Auto-generated method stub
    if (componentId.equals(UtilidadViewComponent.UTILIDAD_VIEW_COMPONENT_ID)) {
      final UtilidadViewComponent component = new UtilidadViewComponent();
      component.setId(UtilidadViewComponent.UTILIDAD_VIEW_COMPONENT_ID);
      component.setParameters(parameters);
      return component;
    }
    throw new IllegalArgumentException("Component id " + componentId + " not supported.");
  }

  @Override
  public List<ComponentResource> getGlobalComponentResources() {
    final List<ComponentResource> globalResources = new ArrayList<ComponentResource>();
    globalResources.add(createStaticResource(
        "web/com.atrums.nomina/js/utilidad-util-toolbar-button.js", false));
    globalResources.add(createStyleSheetResource(
        "web/org.openbravo.userinterface.smartclient/openbravo/skins/"
            + KernelConstants.SKIN_PARAMETER + "/com.atrums.nomina/utilidad-styles.css", false));

    return globalResources;
  }

  @Override
  public List<String> getTestResources() {
    return Collections.emptyList();
  }
}
