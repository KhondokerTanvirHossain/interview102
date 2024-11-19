package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.in.controller;

import com.tanvir.spring_boot_mvc_jpa_base.core.util.constants.Constants;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieCatalogUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
