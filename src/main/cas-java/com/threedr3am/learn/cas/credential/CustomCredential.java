package com.threedr3am.learn.cas.credential;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

/**
 * @author xuanyh
 */
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomCredential extends RememberMeUsernamePasswordCredential {

  private String username;

  private String password;

  private String phone;

  private String phoneCode;

  private String email;

  private String emailCode;

  @Override
  public String getId() {
    return StringUtils.isNotEmpty(username) ? username : phone;
  }
}
