package com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence;

import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface MovieCatalogPersistencePort {
    List<MovieCatalog> getAllDataAsList();
    Page<MovieCatalog> getMoviesByPriceRange(BigDecimal lowerPrice, BigDecimal higherPrice, Pageable pageable);
}
