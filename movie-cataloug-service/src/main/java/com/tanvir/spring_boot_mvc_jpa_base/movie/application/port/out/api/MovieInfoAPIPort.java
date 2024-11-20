package com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.api;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api.dto.MoviePageResponse;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import org.springframework.data.domain.Page;

public interface MovieInfoAPIPort {
    MoviePageResponse getFilteredMovieInfo(int page, int size, String[] sortBy, String[] sortDirection, Long id);
}
