package com.aire.ux.test.spring.servlet;

import jakarta.servlet.Servlet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ServletDefinition {

  String[] paths();

  Class<? extends Servlet> type();
}
