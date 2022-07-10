package com.world.playstay.global.util;

import com.world.playstay.global.exception.EncryptFailException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashUtil {

  private static final int HEX_255 = 0xff;
  private static final int HEX_ADD = 0x100;
  private static final int HEX = 16;

  private static String byteToHexString(byte bt) {
    /* 비트연산자 &을 수행하는 경우 비트수가 넓은 곳에 맞춰서 낮은 비트를 가진 자료형을 확장한다.
    byte형은 8비트의 공간을 차지하고, int는 32비트의 공간을 차지한다.
    byte&0xff: byte는 32비트의 int형으로 강제 형변환이 될 때, 가장 앞의 비트가 1인 경우 1로 모든 비트가 확장되는 것을 0으로 바꿔준다.
    + 0x100: hex String로 변환할 때 10보다 작은 수를 강제로 3자리로 만들어 주며 맨앞에 1이 붙는다.
    .subString(1): +0x100 을 통해서 필요없이 붙은 맨 앞의 hex String 인 1 을 제거할 필요가 있다.
     */
    return Integer.toString((bt & HEX_255) + HEX_ADD, HEX).substring(1);
  }

  public static String encryptSHA256(String password) {
    return encrypt(password, "SHA-256");
  }

  public static String encryptMD5(String password) {
    return encrypt(password, "MD5");
  }

  public static String encrypt(String inputData, String messageDigest) {
    /*
    Message Digest: 메시지를 hash하는 것을 의미한다. 임의의 길이를 가진 메시지를 message digest 함수에 넣으면 일정한 길이를 가진 데이터를 얻는다.
    inputData를 message digest로 암호화하고, 생성된 byte[] 로 Hex String 을 생성한다.
     */
    try {
      MessageDigest md = MessageDigest.getInstance(messageDigest);
      md.update(inputData.getBytes());
      byte[] hashValue = md.digest();

      StringBuilder stringBuilder = new StringBuilder();
      for (byte bt : hashValue) {
        stringBuilder.append(byteToHexString(bt));
      }
      return stringBuilder.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new EncryptFailException(e.toString());
    }
  }

}
