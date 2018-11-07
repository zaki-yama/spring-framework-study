package com.zakiyama.qiita.config;

import org.springframework.context.annotation.Bean;

@Configuration
public class QiitaConfig {

  @Bean
  public QiitaParam qiitaParam() {
    return new QiitaParam();
  }
}