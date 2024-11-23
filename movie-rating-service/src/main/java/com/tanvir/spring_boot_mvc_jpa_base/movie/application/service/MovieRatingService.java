package com.tanvir.spring_boot_mvc_jpa_base.movie.application.service;

import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieRatingUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MovieRatingPersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieRating;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class MovieRatingService implements MovieRatingUseCase {

    private final MovieRatingPersistencePort MovieRatingPersistencePort;

    @Override
    public List<MovieRating> getAllDataAsList() {
        return MovieRatingPersistencePort.getAllDataAsList();
    }

    public Page<MovieRating> getAllDataWithPaginationAndSorting(int page, int size, List<String> sortBy, List<String> sortDirection) {
        return MovieRatingPersistencePort.getAllDataWithPaginationAndSorting(page, size, sortBy, sortDirection);
    }

    public Page<MovieRating> getAllDataWithPaginationAndSortingAndFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, String> filters) {
        return MovieRatingPersistencePort.getAllDataWithPaginationAndSortingAndFiltering(page, size, sortBy, sortDirection, filters);
    }

    public Page<MovieRating> getAllDataWithPaginationAndSortingAndListFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, List<String>> filters) {
        return MovieRatingPersistencePort.getAllDataWithPaginationAndSortingAndListFiltering(page, size, sortBy, sortDirection, filters);
    }

}
