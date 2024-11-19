package com.tanvir.spring_boot_mvc_jpa_base.movie.application.service;

import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieCatalogUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MovieCatalogPersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MoviePersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class MovieCatalogService implements MovieCatalogUseCase {

    private final MovieCatalogPersistencePort movieCatalogPersistencePort;

    @Override
    public List<MovieCatalog> getAllDataAsList() {
        return movieCatalogPersistencePort.getAllDataAsList();
    }
}
