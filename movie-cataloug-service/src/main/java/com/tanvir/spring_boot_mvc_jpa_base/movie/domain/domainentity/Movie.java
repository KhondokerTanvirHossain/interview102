package com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Movie {

    private Long id;
    private String title;
    private LocalDate releaseDate;
    private String genre;

}
