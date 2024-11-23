package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.repository;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.entity.MovieRatingDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieRatingRepository extends JpaRepository<MovieRatingDatabaseEntity, Long>, JpaSpecificationExecutor<MovieRatingDatabaseEntity> {
}
