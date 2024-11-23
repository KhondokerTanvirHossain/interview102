package com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.entity.MovieDatabaseEntity;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;

import java.util.List;

public interface MovieUseCase {
    List<Movie> getAllDataAsList();
}
