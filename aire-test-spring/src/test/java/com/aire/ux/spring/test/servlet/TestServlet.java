package com.aire.ux.spring.test.servlet;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import lombok.Getter;
import org.mockito.Mockito;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@WebServlet({"/test/*"})
public class TestServlet extends HttpServlet implements DisposableBean, InitializingBean {

  @Getter private Servlet mockServlet;
  private ServletConfig mockServletConfiguration;

  @Override
  public void init(ServletConfig config) throws ServletException {
    mockServlet.init(config);
  }

  @Override
  public ServletConfig getServletConfig() {
    return mockServlet.getServletConfig();
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    mockServlet.service(req, res);
  }

  @Override
  public String getServletInfo() {
    return mockServlet.getServletInfo();
  }

  @Override
  public void destroy() {
    mockServlet.destroy();
    Mockito.reset(mockServlet);
    Mockito.reset(mockServletConfiguration);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    mockServlet = mock(Servlet.class);
    mockServletConfiguration = mock(ServletConfig.class);
    lenient().doReturn(mockServletConfiguration).when(mockServlet).getServletConfig();
  }
}
