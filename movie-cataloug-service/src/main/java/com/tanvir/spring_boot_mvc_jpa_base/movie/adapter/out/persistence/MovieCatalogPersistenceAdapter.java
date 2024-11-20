package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.repository.MovieCatalogRepository;
import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.repository.MovieRepository;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MovieCatalogPersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MoviePersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieCatalogPersistenceAdapter implements MovieCatalogPersistencePort {

    private final MovieCatalogRepository movieCatalogRepository;
    private final ModelMapper modelMapper;

    public List<MovieCatalog> getAllDataAsList() {
        return movieCatalogRepository.findAll()
            .stream()
            .map(movie -> modelMapper.map(movie, MovieCatalog.class))
            .toList()
            ;
    }

    public Page<MovieCatalog> getMoviesByPriceRange(BigDecimal lowerPrice, BigDecimal higherPrice, Pageable pageable) {
        return movieCatalogRepository.findByPriceBetween(lowerPrice, higherPrice, pageable)
            .map(movie -> modelMapper.map(movie, MovieCatalog.class));
    }
}
