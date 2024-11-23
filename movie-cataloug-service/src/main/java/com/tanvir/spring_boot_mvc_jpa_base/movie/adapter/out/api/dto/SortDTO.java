package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api.dto;

import lombok.Data;

@Data
public class SortDTO {
    private boolean sorted;
    private boolean unsorted;
    private boolean empty;
}
