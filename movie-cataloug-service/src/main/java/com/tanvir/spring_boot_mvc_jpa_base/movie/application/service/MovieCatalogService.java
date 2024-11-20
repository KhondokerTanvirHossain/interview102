package com.tanvir.spring_boot_mvc_jpa_base.movie.application.service;

import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieCatalogUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.api.MovieInfoAPIPort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MovieCatalogPersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
class MovieCatalogService implements MovieCatalogUseCase {

    private final MovieCatalogPersistencePort movieCatalogPersistencePort;
    private final MovieInfoAPIPort movieInfoAPI;

    @Override
    public List<MovieCatalog> getAllDataAsList() {
        return movieCatalogPersistencePort.getAllDataAsList();
    }

    @Override
    public Page<MovieCatalog> getMoviesByPriceRange(BigDecimal lowerPrice, BigDecimal higherPrice, Pageable pageable) {
        Page<MovieCatalog> movieCatalogPage = movieCatalogPersistencePort.getMoviesByPriceRange(lowerPrice, higherPrice, pageable);
        movieCatalogPage.forEach(movieCatalog -> {
                MovieInfo movieInfo = movieInfoAPI.getFilteredMovieInfo(0, 1, new String[]{"movieId"}, new String[]{"asc"}, movieCatalog.getMovie().getId()).getContent().get(0);
                movieCatalog.setMovieInfo(movieInfo);
            });
        return movieCatalogPage;
    }
}
