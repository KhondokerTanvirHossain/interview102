package com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence;

import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;

import java.util.List;

public interface MovieCatalogPersistencePort {
    List<MovieCatalog> getAllDataAsList();
}
