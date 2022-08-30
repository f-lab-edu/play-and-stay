package com.world.playstay.global.config;

import com.world.playstay.global.interceptor.SessionAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * interceptor과 같은 webMvcConfiguerer 등록을 위한 Config 파일
 *
 * @Component로 Bean으로 등록된 Interceptor를 가져와 registry에 등록한다.
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

  private static final String API_URL_PATTERNS = "/**";  // 인터셉터가 동작 해야 될 요청 주소 mapping 목록
  private static final String BASE_URL_PATTERNS = "/";

  private final SessionAuthInterceptor sessionAuthInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(sessionAuthInterceptor)
        .addPathPatterns(API_URL_PATTERNS)
        .excludePathPatterns(BASE_URL_PATTERNS);
  }


}
