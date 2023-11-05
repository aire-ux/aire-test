package com.aire.ux.test;

import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServlet;
import java.util.Optional;
import java.util.function.Supplier;

public interface VaadinServletFactory {

  default int getOrder() {
    return 100;
  }

  Supplier<UI> getUIFactory();

  Optional<VaadinServlet> createServlet(Routes routes);
}
