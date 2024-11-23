package com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence;


import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface MovieInfoPersistencePort {
    List<MovieInfo> getAllDataAsList();
    Page<MovieInfo> getAllDataWithPaginationAndSorting(int page, int size, List<String> sortBy, List<String> sortDirection);
    Page<MovieInfo> getAllDataWithPaginationAndSortingAndFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, String> filters);
    Page<MovieInfo> getAllDataWithPaginationAndSortingAndListFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, List<String>> filters);
}
