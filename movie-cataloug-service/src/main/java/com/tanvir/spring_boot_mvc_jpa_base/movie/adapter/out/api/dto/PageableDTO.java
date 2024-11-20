package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api.dto;

import lombok.Data;

@Data
public class PageableDTO {
    private int pageNumber;
    private int pageSize;
    private SortDTO sort;
    private long offset;
    private boolean paged;
    private boolean unpaged;
}
