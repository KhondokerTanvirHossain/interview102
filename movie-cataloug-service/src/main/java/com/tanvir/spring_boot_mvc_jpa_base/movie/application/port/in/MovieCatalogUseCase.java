package com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in;

import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface MovieCatalogUseCase {
    List<MovieCatalog> getAllDataAsList();
    Page<MovieCatalog> getMoviesByPriceRange(BigDecimal lowerPrice, BigDecimal higherPrice, Pageable pageable);
}
