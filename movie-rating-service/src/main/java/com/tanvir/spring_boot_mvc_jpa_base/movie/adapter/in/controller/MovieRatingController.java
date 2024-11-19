package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.in.controller;

import com.tanvir.spring_boot_mvc_jpa_base.core.util.constants.Constants;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieRatingUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieRating;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Constants.MOVIE_RATING_API_V1)
@RequiredArgsConstructor
public class MovieRatingController {

    private final MovieRatingUseCase MovieRatingUseCase;

    @GetMapping()
    public @ResponseBody ResponseEntity<List<MovieRating>> getAllDataAsList() {
        return ResponseEntity.ok(MovieRatingUseCase.getAllDataAsList()) ;
    }

    @GetMapping(Constants.PAGE)
    public ResponseEntity<Page<MovieRating>> getAllDataWithPaginationAndSorting(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String[] sortBy,
        @RequestParam(defaultValue = "asc") String[] sortDirection) {
        List<String> sortByList = Arrays.asList(sortBy);
        List<String> sortDirectionList = Arrays.asList(sortDirection);
        Page<MovieRating> movies = MovieRatingUseCase.getAllDataWithPaginationAndSorting(page, size, sortByList, sortDirectionList);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(Constants.FILTER)
    public ResponseEntity<Page<MovieRating>> getAllDataWithPaginationAndSortingAndFiltering(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String[] sortBy,
        @RequestParam(defaultValue = "asc") String[] sortDirection,
        @RequestParam Map<String, String> filters) {
        List<String> sortByList = Arrays.asList(sortBy);
        List<String> sortDirectionList = Arrays.asList(sortDirection);
        filters = filters.entrySet().stream()
            .filter(entry -> !entry.getKey().equals("page") && !entry.getKey().equals("size") && !entry.getKey().equals("sortBy") && !entry.getKey().equals("sortDirection"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Page<MovieRating> movies = MovieRatingUseCase.getAllDataWithPaginationAndSortingAndFiltering(page, size, sortByList, sortDirectionList, filters);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(Constants.LIST_FILTER)
    public ResponseEntity<Page<MovieRating>> getAllDataWithPaginationAndSortingAndListFiltering(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String[] sortBy,
        @RequestParam(defaultValue = "asc") String[] sortDirection,
        @RequestParam Map<String, String> filters) {
        List<String> sortByList = Arrays.asList(sortBy);
        List<String> sortDirectionList = Arrays.asList(sortDirection);
        Map<String, List<String>> filterMap = filters.entrySet().stream()
            .filter(entry -> !entry.getKey().equals("page") && !entry.getKey().equals("size") && !entry.getKey().equals("sortBy") && !entry.getKey().equals("sortDirection"))
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> Arrays.asList(entry.getValue().split(","))));
        Page<MovieRating> movies = MovieRatingUseCase.getAllDataWithPaginationAndSortingAndListFiltering(page, size, sortByList, sortDirectionList, filterMap);
        return ResponseEntity.ok(movies);
    }
}
