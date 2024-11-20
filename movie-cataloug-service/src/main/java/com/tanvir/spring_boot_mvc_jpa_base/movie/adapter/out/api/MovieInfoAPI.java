package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api;

import com.tanvir.spring_boot_mvc_jpa_base.core.exception.ExceptionHandlerUtil;
import com.tanvir.spring_boot_mvc_jpa_base.core.exception.WebClientExceptionHandlerUtil;
import com.tanvir.spring_boot_mvc_jpa_base.core.util.constants.Constants;
import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api.dto.MoviePageResponse;
import com.tanvir.spring_boot_mvc_jpa_base.movie.application.port.out.api.MovieInfoAPIPort;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
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

