package com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity;

import lombok.Data;

@Data
public class MovieRating {

    private Long id;
    private String movieId;
    private int rating;

}
