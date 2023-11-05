package com.aire.ux.test;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.dom.Element;
import lombok.val;

public interface RootElementResolver {

  default int getOrder() {
    return 100;
  }

  default Element getRootElement() {
    val body = new Element("body");
    body.setChild(0, UI.getCurrent().getCurrentView().getElement());
    return body;
  }
}
