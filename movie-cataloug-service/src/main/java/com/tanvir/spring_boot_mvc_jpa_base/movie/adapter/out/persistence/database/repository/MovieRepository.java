package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.repository;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.entity.MovieDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieDatabaseEntity, Long> {
}
