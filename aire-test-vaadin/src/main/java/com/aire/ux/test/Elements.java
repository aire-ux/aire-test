package com.aire.ux.test;

import com.vaadin.flow.dom.Element;
import java.util.ServiceLoader;

public final class Elements {
  public static RootElementResolver getResolver() {
    return ServiceLoader.load(
            RootElementResolver.class, Thread.currentThread().getContextClassLoader())
        .stream()
        .map(ServiceLoader.Provider::get)
        .sorted((lhs, rhs) -> Integer.compare(rhs.getOrder(), lhs.getOrder()))
        .findAny()
        .orElse(new RootElementResolver() {});
  }

  public static Element getRootElement() {
    return getResolver().getRootElement();
  }
}
