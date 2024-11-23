package com.tanvir.spring_boot_mvc_jpa_base.movie.application.service;

import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieInfoUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MovieInfoPersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class MovieInfoService implements MovieInfoUseCase {

    private final MovieInfoPersistencePort movieInfoPersistencePort;

    @Override
    public List<MovieInfo> getAllDataAsList() {
        return movieInfoPersistencePort.getAllDataAsList();
    }

    public Page<MovieInfo> getAllDataWithPaginationAndSorting(int page, int size, List<String> sortBy, List<String> sortDirection) {
        return movieInfoPersistencePort.getAllDataWithPaginationAndSorting(page, size, sortBy, sortDirection);
    }

    public Page<MovieInfo> getAllDataWithPaginationAndSortingAndFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, String> filters) {
        return movieInfoPersistencePort.getAllDataWithPaginationAndSortingAndFiltering(page, size, sortBy, sortDirection, filters);
    }

    public Page<MovieInfo> getAllDataWithPaginationAndSortingAndListFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, List<String>> filters) {
        return movieInfoPersistencePort.getAllDataWithPaginationAndSortingAndListFiltering(page, size, sortBy, sortDirection, filters);
    }

}
