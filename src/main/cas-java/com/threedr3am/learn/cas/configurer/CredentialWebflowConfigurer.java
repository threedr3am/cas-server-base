package com.threedr3am.learn.cas.configurer;


import com.threedr3am.learn.cas.credential.CustomCredential;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.apereo.cas.web.flow.configurer.DefaultLoginWebflowConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * @author xuanyh
 */
public class CredentialWebflowConfigurer extends DefaultLoginWebflowConfigurer {

  /**
   * Instantiates a new Default webflow configurer.
   *
   * @param flowBuilderServices the flow builder services
   * @param flowDefinitionRegistry the flow definition registry
   * @param applicationContext the application context
   * @param casProperties the cas properties
   */
  public CredentialWebflowConfigurer(
      FlowBuilderServices flowBuilderServices,
      FlowDefinitionRegistry flowDefinitionRegistry,
      ApplicationContext applicationContext,
      CasConfigurationProperties casProperties) {
    super(flowBuilderServices, flowDefinitionRegistry, applicationContext, casProperties);
  }

  @Override
  protected void createRememberMeAuthnWebflowConfig(Flow flow) {
    createFlowVariable(flow, CasWebflowConstants.VAR_ID_CREDENTIAL, CustomCredential.class);
    if (casProperties.getTicket().getTgt().getRememberMe().isEnabled()) {
      final ViewState state = getState(flow, CasWebflowConstants.STATE_ID_VIEW_LOGIN_FORM, ViewState.class);
      final BinderConfiguration cfg = getViewStateBinderConfiguration(state);
      cfg.addBinding(new BinderConfiguration.Binding("rememberMe", null, false));
    }
  }
}
