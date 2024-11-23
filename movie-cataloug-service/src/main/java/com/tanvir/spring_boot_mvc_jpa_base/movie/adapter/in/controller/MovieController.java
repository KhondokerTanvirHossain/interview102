package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.in.controller;

import com.tanvir.spring_boot_mvc_jpa_base.core.util.constants.Constants;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.MOVIE_API_V1)
@RequiredArgsConstructor
public class MovieController {

    private final MovieUseCase movieUseCase;

    @GetMapping()
    public @ResponseBody ResponseEntity<List<Movie>> getAllDataAsList() {
        return ResponseEntity.ok(movieUseCase.getAllDataAsList()) ;
    }
}
