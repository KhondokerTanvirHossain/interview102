package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.entity.MovieInfoDatabaseEntity;
import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.specification.MovieInfoSpecification;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.repository.MovieInfoRepository;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MovieInfoPersistencePort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieInfoPersistenceAdapter implements MovieInfoPersistencePort {

    private final MovieInfoRepository movieInfoRepository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public List<MovieInfo> getAllDataAsList() {
        return movieInfoRepository.findAll()
            .stream()
            .map(movie -> modelMapper.map(movie, MovieInfo.class))
            .toList()
            ;
    }

    public Page<MovieInfo> getAllDataWithPaginationAndSorting(int page, int size, List<String> sortBy, List<String> sortDirection) {
        Sort sort = Sort.by(
            sortBy.stream()
                .map(field -> new Sort.Order(Sort.Direction.fromString(sortDirection.get(sortBy.indexOf(field))), field))
                .collect(Collectors.toList())
        );
        Pageable pageable = PageRequest.of(page, size, sort);
        return movieInfoRepository.findAll(pageable)
            .map(movie -> modelMapper.map(movie, MovieInfo.class));
    }

    public Page<MovieInfo> getAllDataWithPaginationAndSortingAndFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, String> filters) {
        Sort sort = Sort.by(
            sortBy.stream()
                .map(field -> new Sort.Order(Sort.Direction.fromString(sortDirection.get(sortBy.indexOf(field))), field))
                .collect(Collectors.toList())
        );
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<MovieInfoDatabaseEntity> specification = MovieInfoSpecification.getFilterSpecifications(filters, entityManager.getMetamodel());
        return movieInfoRepository.findAll(specification, pageable)
            .map(movie -> modelMapper.map(movie, MovieInfo.class));
    }

    public Page<MovieInfo> getAllDataWithPaginationAndSortingAndListFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, List<String>> filters) {
        Sort sort = Sort.by(
            sortBy.stream()
                .map(field -> new Sort.Order(Sort.Direction.fromString(sortDirection.get(sortBy.indexOf(field))), field))
                .collect(Collectors.toList())
        );
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<MovieInfoDatabaseEntity> specification = MovieInfoSpecification.getListFilterSpecifications(filters, entityManager.getMetamodel());
        return movieInfoRepository.findAll(specification, pageable)
            .map(movie -> modelMapper.map(movie, MovieInfo.class));
    }
}
