package com.threedr3am.learn.cas.authentication;

import com.threedr3am.learn.cas.credential.CustomCredential;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import javax.security.auth.login.AccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

/**
 * 短信验证码登录验证器
 *
 * @author xuanyh
 */
@Slf4j
public class CustomAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {


  public CustomAuthenticationHandler(String name,
      ServicesManager servicesManager,
      PrincipalFactory principalFactory, Integer order) {
    super(name, servicesManager, principalFactory, order);
  }

  @Override
  protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential)
      throws GeneralSecurityException, PreventedException {
    CustomCredential customCredential = (CustomCredential) credential;
    log.info("RememberMePhoneAuthenticationHandler -----------------------> " + customCredential.toString());
    if (StringUtils.isNotEmpty(customCredential.getUsername())) {
      //账号密码登录
      if (StringUtils.equals(customCredential.getUsername(), "xuanyh") && StringUtils.equals(customCredential.getPassword(), "123456")) {
        return createHandlerResult(customCredential,
            this.principalFactory.createPrincipal(customCredential.getId()),
            new ArrayList<>(0));
      }
    } else if (StringUtils.isNotEmpty(customCredential.getPhone())) {
      //手机号登录
      if (StringUtils.equals(customCredential.getPhone(), "13221008760") && StringUtils.equals(customCredential.getPhoneCode(), "123456")) {
        return createHandlerResult(customCredential,
            this.principalFactory.createPrincipal(customCredential.getId()),
            new ArrayList<>(0));
      }
    } else if (StringUtils.isNotEmpty(customCredential.getEmail())) {
      //邮箱登录
      if (StringUtils.equals(customCredential.getEmail(), "929811313@qq.com") && StringUtils.equals(customCredential.getEmailCode(), "123456")) {
        return createHandlerResult(customCredential,
            this.principalFactory.createPrincipal(customCredential.getId()),
            new ArrayList<>(0));
      }
    }
    throw new AccountNotFoundException("登录凭证无效");
  }

  @Override
  public boolean supports(Credential credential) {
    return credential instanceof CustomCredential;
  }
}
