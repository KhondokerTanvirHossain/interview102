package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movie_rating")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MovieRatingDatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movieId;
    private int rating;
}

