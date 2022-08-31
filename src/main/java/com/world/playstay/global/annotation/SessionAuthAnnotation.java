package com.world.playstay.global.annotation;

import com.world.playstay.user.enums.UserType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Guest 또는 Host 세션 로그인 여부 확인
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SessionAuthAnnotation {

  UserType[] userType();

}
