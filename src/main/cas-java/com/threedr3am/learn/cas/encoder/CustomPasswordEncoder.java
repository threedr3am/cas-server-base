package com.threedr3am.learn.cas.encoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xuanyh
 */
@Slf4j
public class CustomPasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence charSequence) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(charSequence.toString().getBytes());
      String pwd = new BigInteger(1, md.digest()).toString(16);
      log.info("encode password, before ({}), after ({})", charSequence, pwd);
      return pwd;
    } catch (NoSuchAlgorithmException e) {
      log.error("encode password error", e);
    }
    return null;
  }

  @Override
  public boolean matches(CharSequence charSequence, String s) {
    if (StringUtils.isEmpty(charSequence))
      return false;
    String pass = encode(charSequence);
    log.info("request password ({}) encode is ({}), db password is ({})", charSequence, pass, s);
    return StringUtils.equals(pass, s);
  }
}
