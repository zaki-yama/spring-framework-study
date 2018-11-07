package com.zakiyama.qiita.repository;

import com.zakiyama.qiita.domain.QiitaItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QiitaRepository extends JpaRepository<QiitaItem, String> {
}