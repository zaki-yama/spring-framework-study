package com.zakiyama.qiita.param;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "qiita.param")
public class QiitaParam {

  private String url;

  private Integer limit;
}