package com.tanvir.spring_boot_mvc_jpa_base.movie.application.service;

import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MoviePersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class MovieService implements MovieUseCase {

    private final MoviePersistencePort moviePersistencePort;

    @Override
    public List<Movie> getAllDataAsList() {
        return moviePersistencePort.getAllDataAsList();
    }
}
