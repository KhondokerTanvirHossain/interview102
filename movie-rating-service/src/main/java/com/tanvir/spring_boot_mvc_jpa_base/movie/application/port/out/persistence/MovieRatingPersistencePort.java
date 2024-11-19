package com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence;


import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieRating;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface MovieRatingPersistencePort {
    List<MovieRating> getAllDataAsList();
    Page<MovieRating> getAllDataWithPaginationAndSorting(int page, int size, List<String> sortBy, List<String> sortDirection);
    Page<MovieRating> getAllDataWithPaginationAndSortingAndFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, String> filters);
    Page<MovieRating> getAllDataWithPaginationAndSortingAndListFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, List<String>> filters);
}
