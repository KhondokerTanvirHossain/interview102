package com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.api;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api.dto.MoviePageResponse;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieRating;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieInfoAPIPort {
    MoviePageResponse getFilteredMovieInfo(int page, int size, String[] sortBy, String[] sortDirection, Long id);
    List<MovieInfo> getFilteredMovieInfoBatch(int page, int size, String[] sortBy, String[] sortDirection,List<Long> ids);
    List<MovieRating> getFilteredMovieRatingBatch(int page, int size, String[] sortBy, String[] sortDirection, List<Long> ids, List<Integer> ratings);
}
