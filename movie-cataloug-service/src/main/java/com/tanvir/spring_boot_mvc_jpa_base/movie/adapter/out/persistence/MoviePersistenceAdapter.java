package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.repository.MovieRepository;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MoviePersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MoviePersistenceAdapter implements MoviePersistencePort {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public List<Movie> getAllDataAsList() {
        return movieRepository.findAll()
            .stream()
            .map(movie -> modelMapper.map(movie, Movie.class))
            .toList()
            ;
    }
}
