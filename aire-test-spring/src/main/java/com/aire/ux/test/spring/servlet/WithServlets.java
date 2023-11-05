package com.aire.ux.test.spring.servlet;

import jakarta.servlet.Servlet;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WithServlets {
  Class<? extends Servlet>[] value() default Servlet.class;

  ServletDefinition[] servlets() default {};
}
