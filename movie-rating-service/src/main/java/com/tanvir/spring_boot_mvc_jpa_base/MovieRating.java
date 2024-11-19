package com.tanvir.spring_boot_mvc_jpa_base;

import jakarta.persistence.*;

@Entity
public class MovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private int rating;
}
