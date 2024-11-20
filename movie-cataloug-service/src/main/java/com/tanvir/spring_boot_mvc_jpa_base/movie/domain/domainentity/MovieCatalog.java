package com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity;

import lombok.*;

import java.math.BigDecimal;

@Data
public class MovieCatalog {

    private Long id;
    private Movie movie;
    private Boolean available;
    private BigDecimal price;
    private MovieInfo movieInfo;

}
