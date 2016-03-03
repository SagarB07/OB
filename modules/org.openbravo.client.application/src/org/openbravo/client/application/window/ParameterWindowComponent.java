/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2012-2013 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package org.openbravo.client.application.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Restrictions;
import org.openbravo.client.application.Parameter;
import org.openbravo.client.application.Process;
import org.openbravo.client.kernel.BaseTemplateComponent;
import org.openbravo.client.kernel.KernelConstants;
import org.openbravo.client.kernel.Template;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.domain.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The component which takes care of creating a class for a specific paramter window.
 * 
 * @author alostale
 */
public class ParameterWindowComponent extends BaseTemplateComponent {
  private static final String DEFAULT_TEMPLATE_ID = "FF80818132F916130132F9357DE10016";
  private static final Logger log = LoggerFactory.getLogger(ParameterWindowComponent.class);

  static final String BUTTON_LIST_REFERENCE_ID = "FF80818132F94B500132F9575619000A";

  private Boolean inDevelopment = null;
  private String uniqueString = "" + System.currentTimeMillis();
  private Process process;

  @Inject
  private OBViewParameterHandler paramHandler;
  private boolean popup;

  protected Template getComponentTemplate() {
    return OBDal.getInstance().get(Template.class, DEFAULT_TEMPLATE_ID);
  }

  public String getWindowClientClassName() {
    // see the ViewComponent#correctViewId
    // changes made in this if statement should also be done in that method
    if (isIndevelopment()) {
      return KernelConstants.ID_PREFIX + process.getId() + KernelConstants.ID_PREFIX + uniqueString;
    }
    return KernelConstants.ID_PREFIX + process.getId();
  }

  public void setUniqueString(String uniqueString) {
    this.uniqueString = uniqueString;
  }

  public boolean isIndevelopment() {
    if (inDevelopment != null) {
      return inDevelopment;
    }

    // check window, tabs and fields
    inDevelopment = Boolean.FALSE;
    if (process.getModule().isInDevelopment() && process.getModule().isEnabled()) {
      inDevelopment = Boolean.TRUE;
    }

    return inDevelopment;
  }

  public String generate() {
    final String jsCode = super.generate();
    return jsCode;
  }

  public boolean isPopup() {
    return this.popup;
  }

  public void setPoup(boolean popup) {
    this.popup = popup;
  }

  public String getThreadSafe() {
    return "true";
  }

  public void setProcess(org.openbravo.client.application.Process process) {
    this.process = process;
    paramHandler.setProcess(process);
    paramHandler.setParamWindow(this);
  }

  public OBViewParameterHandler getParamHandler() {
    return paramHandler;
  }

  public String getActionHandler() {
    return process.getJavaClassName();
  }

  public String getProcessId() {
    return process.getId();
  }

  public List<org.openbravo.model.ad.domain.List> getButtonList() {
    for (Parameter p : process.getOBUIAPPParameterList()) {
      if (p.isActive() && p.getReference().getId().equals(BUTTON_LIST_REFERENCE_ID)) {
        OBCriteria<org.openbravo.model.ad.domain.List> qList = OBDal.getInstance().createCriteria(
            org.openbravo.model.ad.domain.List.class);
        qList.add(Restrictions.eq(org.openbravo.model.ad.domain.List.PROPERTY_REFERENCE,
            p.getReferenceSearchKey()));
        qList.add(Restrictions.eq(org.openbravo.model.ad.domain.List.PROPERTY_ACTIVE, true));
        qList.addOrderBy(org.openbravo.model.ad.domain.List.PROPERTY_SEQUENCENUMBER, true);
        qList.addOrderBy(org.openbravo.model.ad.domain.List.PROPERTY_SEARCHKEY, true);
        return qList.list();
      }
    }
    return new ArrayList<org.openbravo.model.ad.domain.List>();
  }

  public String getDynamicColumns() {
    List<Parameter> paramsWithValidation = new ArrayList<Parameter>();
    List<String> allParams = new ArrayList<String>();
    Map<String, List<String>> dynCols = new HashMap<String, List<String>>();

    for (Parameter param : process.getOBUIAPPParameterList()) {
      Validation validation = param.getValidation();
      if (validation != null) {
        if (validation.getType().equals("HQL_JS")) {
          paramsWithValidation.add(param);
        } else {
          log.error("Unsupported validation type {} for param {} in process {}", new Object[] {
              "HQL_JS", param, process });
        }
      }
      allParams.add(param.getDBColumnName());
    }

    for (Parameter paramWithVal : paramsWithValidation) {
      parseValidation(paramWithVal.getValidation(), dynCols, allParams,
          paramWithVal.getDBColumnName());
    }

    JSONObject jsonDynCols = new JSONObject();

    for (String dynColName : dynCols.keySet()) {
      JSONArray affectedColumns = new JSONArray();
      for (String affectedCol : dynCols.get(dynColName)) {
        affectedColumns.put(affectedCol);
      }
      try {
        jsonDynCols.put(dynColName, affectedColumns);
      } catch (JSONException e) {
        log.error("Error generating dynamic columns for process {}", process.getName(), e);
      }
    }
    return jsonDynCols.toString();
  }

  /**
   * Dynamic columns is a list of columns that cause others to be modified, it includes the ones
   * causing the modification as well as the affected ones.
   * 
   * Columns are identified as strings surrounded by quotes (" or ') matching one of the names of
   * the parameters.
   */
  private void parseValidation(Validation validation, Map<String, List<String>> dynCols,
      List<String> allParams, String paramName) {
    String token = validation.getValidationCode().replace("\"", "'");

    List<String> columns;

    int i = token.indexOf("'");
    while (i != -1) {
      token = token.substring(i + 1);
      i = token.indexOf("'");
      if (i != -1) {
        String strAux = token.substring(0, i);
        token = token.substring(i + 1);
        columns = dynCols.get(token);

        if (!strAux.equals(paramName) && allParams.contains(strAux)) {
          if (dynCols.containsKey(strAux)) {
            columns = dynCols.get(strAux);
          } else {
            columns = new ArrayList<String>();
            dynCols.put(strAux, columns);
          }
          if (!columns.contains(paramName)) {
            columns.add(paramName);
          }
        }
      }
      if (token.indexOf("'") != -1) {
        token = "'" + token;
      }
      i = token.indexOf("'");
    }
  }
}