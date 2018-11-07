package com.zakiyama.qiita.service;

import java.util.List;

import com.zakiyama.qiita.domain.QiitaItem;
import com.zakiyama.qiita.repository.QiitaRepository;
import com.zakiyama.qiita.repository.RestComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QiitaService {

  RestComponent restComponent;

  QiitaRepository qiitaRepository;

  @Autowired
  public QiitaService(
      RestComponent restComponent,
      QiitaRepository qiitaRepository) {
    this.restComponent = restComponent;
    this.qiitaRepository = qiitaRepository;
  }

  @Transactional(readOnly = false)
  public void crawlItems() {
    List<QiitaItem> items = restComponent.getItems();
    qiitaRepository.saveAll(items);
  }

  @Transactional(readOnly = true)
  public List<QiitaItem> findAll() {
    return qiitaRepository.findAll();
  }
}