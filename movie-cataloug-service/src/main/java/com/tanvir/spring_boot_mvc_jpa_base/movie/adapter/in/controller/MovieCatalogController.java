package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.in.controller;

import com.tanvir.spring_boot_mvc_jpa_base.core.util.constants.Constants;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieCatalogUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(Constants.MOVIE_CATALOG_API_V1)
@RequiredArgsConstructor
public class MovieCatalogController {

    private final MovieCatalogUseCase movieCatalogUseCase;

    @GetMapping()
    public @ResponseBody ResponseEntity<List<MovieCatalog>> getAllDataAsList() {
        return ResponseEntity.ok(movieCatalogUseCase.getAllDataAsList()) ;
    }

    @GetMapping(Constants.PRICE_RANGE)
    public ResponseEntity<Page<MovieCatalog>> getMoviesByPriceRange(
        @RequestParam BigDecimal lowerPrice,
        @RequestParam BigDecimal higherPrice,
        @PageableDefault(size = 10) Pageable pageable) {
        Page<MovieCatalog> movies = movieCatalogUseCase.getMoviesByPriceRange(lowerPrice, higherPrice, pageable);
        return ResponseEntity.ok(movies);
    }
}
