package com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in;

import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;

import java.util.List;

public interface MovieCatalogUseCase {
    List<MovieCatalog> getAllDataAsList();
}
