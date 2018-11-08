package com.zakiyama.qiita.web.rest;

import java.util.List;

import com.zakiyama.qiita.domain.QiitaItem;
import com.zakiyama.qiita.service.QiitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class QiitaRestController {

  @Autowired
  QiitaService qiitaService;

  @GetMapping(value = "/items")
  public ResponseEntity<List<QiitaItem>> getAllItems() {
    List<QiitaItem> items = qiitaService.findAll();
    return ResponseEntity.ok().body(items);
  }
}