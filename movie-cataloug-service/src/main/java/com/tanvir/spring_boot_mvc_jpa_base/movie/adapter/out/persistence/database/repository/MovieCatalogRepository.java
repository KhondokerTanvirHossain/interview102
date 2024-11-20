package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.repository;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.entity.MovieCatalogDatabaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface MovieCatalogRepository extends JpaRepository<MovieCatalogDatabaseEntity, Long> {

    Page<MovieCatalogDatabaseEntity> findByPriceBetween(BigDecimal price, BigDecimal price2, Pageable pageable);
}
