package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.entity.MovieRatingDatabaseEntity;
import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.repository.MovieRatingRepository;
import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.specification.MovieRatingSpecification;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MovieRatingPersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieRating;
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
public class MovieRatingPersistenceAdapter implements MovieRatingPersistencePort {

    private final MovieRatingRepository MovieRatingRepository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public List<MovieRating> getAllDataAsList() {
        return MovieRatingRepository.findAll()
            .stream()
            .map(movie -> modelMapper.map(movie, MovieRating.class))
            .toList()
            ;
    }

    public Page<MovieRating> getAllDataWithPaginationAndSorting(int page, int size, List<String> sortBy, List<String> sortDirection) {
        Sort sort = Sort.by(
            sortBy.stream()
                .map(field -> new Sort.Order(Sort.Direction.fromString(sortDirection.get(sortBy.indexOf(field))), field))
                .collect(Collectors.toList())
        );
        Pageable pageable = PageRequest.of(page, size, sort);
        return MovieRatingRepository.findAll(pageable)
            .map(movie -> modelMapper.map(movie, MovieRating.class));
    }

    public Page<MovieRating> getAllDataWithPaginationAndSortingAndFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, String> filters) {
        Sort sort = Sort.by(
            sortBy.stream()
                .map(field -> new Sort.Order(Sort.Direction.fromString(sortDirection.get(sortBy.indexOf(field))), field))
                .collect(Collectors.toList())
        );
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<MovieRatingDatabaseEntity> specification = MovieRatingSpecification.getFilterSpecifications(filters, entityManager.getMetamodel());
        return MovieRatingRepository.findAll(specification, pageable)
            .map(movie -> modelMapper.map(movie, MovieRating.class));
    }

    public Page<MovieRating> getAllDataWithPaginationAndSortingAndListFiltering(int page, int size, List<String> sortBy, List<String> sortDirection, Map<String, List<String>> filters) {
        Sort sort = Sort.by(
            sortBy.stream()
                .map(field -> new Sort.Order(Sort.Direction.fromString(sortDirection.get(sortBy.indexOf(field))), field))
                .collect(Collectors.toList())
        );
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<MovieRatingDatabaseEntity> specification = MovieRatingSpecification.getListFilterSpecifications(filters, entityManager.getMetamodel());
        return MovieRatingRepository.findAll(specification, pageable)
            .map(movie -> modelMapper.map(movie, MovieRating.class));
    }
}
