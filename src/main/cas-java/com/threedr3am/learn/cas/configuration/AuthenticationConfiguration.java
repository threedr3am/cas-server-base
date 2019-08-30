package com.threedr3am.learn.cas.configuration;

import com.threedr3am.learn.cas.authentication.CustomAuthenticationHandler;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuanyh
 */
@Configuration
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class AuthenticationConfiguration implements AuthenticationEventExecutionPlanConfigurer {

  @Autowired
  @Qualifier("servicesManager")
  private ServicesManager servicesManager;

  /**
   * 自定义验证器
   *
   * @return
   */
  @Bean
  public AuthenticationHandler customAuthenticationHandler() {
    CustomAuthenticationHandler handler = new CustomAuthenticationHandler(CustomAuthenticationHandler.class.getSimpleName(), servicesManager, new DefaultPrincipalFactory(), 1);
    return handler;
  }

  /**
   * 注册验证器
   * @param plan
   */
  @Override
  public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
    plan.registerAuthenticationHandler(customAuthenticationHandler());
  }
}
