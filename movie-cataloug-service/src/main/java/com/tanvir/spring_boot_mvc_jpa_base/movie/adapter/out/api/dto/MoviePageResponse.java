package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api.dto;

import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieRating;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
public class MoviePageResponse {
    private int totalPages;
    private long totalElements;
    private PageableDTO pageable;
    private boolean first;
    private boolean last;
    private int size;
    private int number;
    private List<MovieInfo> content;
    private List<MovieRating> rating;
    private SortDTO sort;
    private int numberOfElements;
    private boolean empty;
}
