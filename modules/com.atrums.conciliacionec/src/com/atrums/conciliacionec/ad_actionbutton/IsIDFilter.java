package com.atrums.conciliacionec.ad_actionbutton;

import org.openbravo.base.filter.RegexFilter;

/**
 * Filter to check, if the input value is an uuid.
 * 
 * @author huehner
 * 
 */
public class IsIDFilter extends RegexFilter {

  public final static IsIDFilter instance = new IsIDFilter();

  public IsIDFilter() {
    super("[a-zA-Z0-9]*");
  }

}
