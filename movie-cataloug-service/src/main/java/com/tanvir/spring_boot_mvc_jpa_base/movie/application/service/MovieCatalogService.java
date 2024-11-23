package com.tanvir.spring_boot_mvc_jpa_base.movie.application.service;

import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.in.MovieCatalogUseCase;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.api.MovieInfoAPIPort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.persistence.MovieCatalogPersistencePort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieCatalog;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieRating;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class MovieCatalogService implements MovieCatalogUseCase {

    private final MovieCatalogPersistencePort movieCatalogPersistencePort;
    private final MovieInfoAPIPort movieInfoAPI;

    @Override
    public List<MovieCatalog> getAllDataAsList() {
        return movieCatalogPersistencePort.getAllDataAsList();
    }

    @Override
    public Page<MovieCatalog> getMoviesByPriceRange(BigDecimal lowerPrice, BigDecimal higherPrice, Pageable pageable) {

        // Fetch movies by price range
        Page<MovieCatalog> movieCatalogPage = movieCatalogPersistencePort.getMoviesByPriceRange(lowerPrice, higherPrice, pageable);

        // Extract movie IDs and rating IDs from the fetched movies
        List<Long> movieIds = movieCatalogPage.getContent().stream()
                .map(movieCatalog -> movieCatalog.getMovie().getId())
                .toList();

        List<Integer> ratingIds = movieCatalogPage.getContent().stream()
                .map(movieCatalog -> movieCatalog.getMovie().getRating())
                .toList();

        // Fetch additional movie information and ratings
        List<MovieInfo> movieInfos = movieInfoAPI.getFilteredMovieInfoBatch(0, 1, new String[]{"movieId"}, new String[]{"asc"}, movieIds);
        List<MovieRating> ratings = movieInfoAPI.getFilteredMovieRatingBatch(0, 1, new String[]{"movieId"}, new String[]{"asc"}, movieIds, ratingIds);

        // Return the original page if no additional info or ratings are found
        if ((movieInfos == null || movieInfos.isEmpty()) && (ratings == null || ratings.isEmpty())) {
            return movieCatalogPage;
        }

        // Map MovieInfo and ratings to their respective movie IDs
        Map<Long, MovieInfo> movieInfoMap = movieInfos != null ? movieInfos.stream()
                .collect(Collectors.toMap(
                        movieInfo -> Long.valueOf(movieInfo.getMovieId()),
                        movieInfo -> movieInfo
                )) : Collections.emptyMap();

        Map<Long, List<MovieRating>> movieRatingsMap = ratings != null ? ratings.stream()
                .collect(Collectors.groupingBy(
                        movieRating -> Long.valueOf(movieRating.getMovieId())
                )) : Collections.emptyMap();

        // Update the MovieCatalog objects with additional info and ratings
        List<MovieCatalog> updatedMovieCatalogs = movieCatalogPage.getContent().stream()
                .peek(movieCatalog -> {
                    Long movieId = movieCatalog.getMovie().getId();

                    // Update movie info if available
                    MovieInfo movieInfo = movieInfoMap.get(movieId);
                    if (movieInfo != null) {
                        movieCatalog.setMovieInfo(movieInfo);
                    }

                    // Update movie ratings if available
                    MovieRating movieRating = (MovieRating) movieRatingsMap.get(movieId);
                    if (movieRating != null) {
                        movieCatalog.setMovieRating(movieRating); // Assuming a setter or property for ratings
                    }
                })
                .toList();

        // Return the updated page of MovieCatalog
        return new PageImpl<>(updatedMovieCatalogs, pageable, movieCatalogPage.getTotalElements());
    }





}
