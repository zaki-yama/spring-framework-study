package com.zakiyama.qiita.web;

import java.util.List;

import com.zakiyama.qiita.domain.QiitaItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class QiitaWebController {

  @Autowired
  QiitaService qiitaService;

  @GetMapping(value = "/items")
  public ModelAndView showAllItemsPage() {
    ModelAndView modelAndView = new ModelAndView("items");

    List<QiitaItem> items = qiitaService.findAll();
    modelAndView.addObject("items", items);
    return modelAndView;
  }
}