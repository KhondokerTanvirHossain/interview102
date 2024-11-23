package com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity;

import lombok.Data;

@Data
public class MovieInfo {

    private Long id;
    private String movieId;
    private String director;

}
