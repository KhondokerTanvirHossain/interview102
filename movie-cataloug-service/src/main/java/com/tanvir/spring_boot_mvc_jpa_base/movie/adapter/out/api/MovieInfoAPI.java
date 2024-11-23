package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api;

import com.tanvir.spring_boot_mvc_jpa_base.core.exception.ExceptionHandlerUtil;
import com.tanvir.spring_boot_mvc_jpa_base.core.exception.WebClientExceptionHandlerUtil;
import com.tanvir.spring_boot_mvc_jpa_base.core.util.constants.Constants;
import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api.dto.MoviePageResponse;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.api.MovieInfoAPIPort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieRating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieInfoAPI implements MovieInfoAPIPort {
    private final WebClient webClient;

    @Override
    public MoviePageResponse getFilteredMovieInfo(int page, int size, String[] sortBy, String[] sortDirection, Long id) {
        return webClient
            .get()
            .uri(uriBuilder -> buildUri(uriBuilder, page, size, sortBy, sortDirection, id))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::isError, response -> processError(response, Constants.MOVIE_INFO_API_V1))
            .bodyToMono(MoviePageResponse.class) // Use the MoviePageResponse DTO
            .doOnError(throwable -> log.error("Failed to get response from movie info API: {}", throwable.getMessage()))
            .doOnSuccess(response -> log.info("Successfully got response from movie info API"))
            .block();
    }

    @Override
    public List<MovieInfo> getFilteredMovieInfoBatch(int page, int size, String[] sortBy, String[] sortDirection,List<Long> ids) {
        try {
            // Fetch movie info in batch
            MoviePageResponse response = webClient
                    .get()
                    .uri(uriBuilder -> buildBatchUri(uriBuilder,page, size, sortBy, sortDirection, ids))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, clientResponse -> processError(clientResponse, Constants.MOVIE_INFO_API_V1))
                    .bodyToMono(MoviePageResponse.class)
                    .doOnError(throwable -> log.error("Failed to get batch response from movie info API: {}", throwable.getMessage()))
                    .doOnSuccess(resp -> log.info("Successfully got batch response from movie info API"))
                    .block();

            // Return the list of MovieInfo
            return response != null ? response.getContent() : Collections.emptyList();
        } catch (Exception e) {
            log.error("Exception while fetching batch movie info: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<MovieRating> getFilteredMovieRatingBatch(int page, int size, String[] sortBy, String[] sortDirection, List<Long> ids, List<Integer> ratings) {
        try {
            MoviePageResponse response = webClient
                    .get()
                    .uri(uriBuilder -> buildRatingBatchUri(uriBuilder, page, size, sortBy, sortDirection, ids, ratings))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, clientResponse -> processError(clientResponse, Constants.MOVIE_RATING_API_V1))
                    .bodyToMono(MoviePageResponse.class)
                    .doOnError(throwable -> log.error("Failed to get batch response from movie rating API: {}", throwable.getMessage()))
                    .doOnSuccess(resp -> log.info("Successfully got batch response from movie rating API"))
                    .block();

            return response != null ? response.getRating() : Collections.emptyList();
        } catch (Exception e) {
            log.error("Exception while fetching batch movie ratings: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    private URI buildUri(UriBuilder uriBuilder, int page, int size, String[] sortBy, String[] sortDirection, Long id) {
        return uriBuilder
            .path(Constants.MOVIE_INFO_API_V1 + "/filter")
            .queryParam("page", page)
            .queryParam("size", size)
            .queryParam("sortBy", (Object[]) sortBy)
            .queryParam("sortDirection", (Object[]) sortDirection)
            .queryParam("id", id)
            .build();
    }

    private URI buildBatchUri(UriBuilder uriBuilder,int page, int size, String[] sortBy, String[] sortDirection, List<Long> ids) {
        return uriBuilder
                .path(Constants.MOVIE_INFO_API_V1 + "/list-filter")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sortBy", (Object[]) sortBy)
                .queryParam("sortDirection", (Object[]) sortDirection)
                .queryParam("id", ids.toArray()) // Include all movie IDs as a comma-separated list
                .build();
    }

    private URI buildRatingBatchUri(UriBuilder uriBuilder, int page, int size, String[] sortBy, String[] sortDirection, List<Long> ids, List<Integer> ratings) {
        return uriBuilder
                .path(Constants.MOVIE_RATING_API_V1 + "/list-filter")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sortBy", (Object[]) sortBy)
                .queryParam("sortDirection", (Object[]) sortDirection)
                .queryParam("id", ids.toArray())
                .queryParam("rating", ratings.toArray())
                .build();
    }

    private Mono<Throwable> processError(ClientResponse response, String url) {
        return response.bodyToMono(Map.class)
            .doOnSuccess(stringStringMap ->
                log.error("""
                                        Client returned error response for {}
                                         Response Status : {}
                                         Response Body : {}
                                        """,
                    url,
                    response.statusCode(),
                    stringStringMap)
            )
            .switchIfEmpty(Mono.error(new WebClientExceptionHandlerUtil(response.statusCode(), "Service Sent Empty Error Response Body", null)))
            .flatMap(stringStringMap -> {
                log.error("Error Body : {}", stringStringMap);
                return Mono.error(new ExceptionHandlerUtil(response.statusCode(), stringStringMap.get("error").toString()));
            });
    }

}

