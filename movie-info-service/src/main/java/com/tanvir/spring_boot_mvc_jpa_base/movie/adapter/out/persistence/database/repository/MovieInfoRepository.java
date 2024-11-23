package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.repository;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.entity.MovieInfoDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieInfoRepository extends JpaRepository<MovieInfoDatabaseEntity, Long>, JpaSpecificationExecutor<MovieInfoDatabaseEntity> {
}
