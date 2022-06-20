package com.world.playstay.global.util;

import com.world.playstay.global.util.exception.EncryptException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class HashUtil {

  public static String encryptSHA256(String password) {
    return encrypt(password, "SHA-256");
  }

  public static String encryptMD5(String password) {
    return encrypt(password, "MD5");
  }

  public static String encrypt(String inputData, String messageDigest) {
    try {
      MessageDigest md = MessageDigest.getInstance(messageDigest);
      md.update(inputData.getBytes());
      byte[] hashValue = md.digest();

      StringBuilder stringBuilder = new StringBuilder();
      for (byte bt : hashValue) {
        stringBuilder.append(Integer.toString((bt & 0xff) + 0x100, 16).substring(1));
      }
      return stringBuilder.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new EncryptException(e.toString());
    }
  }

}
