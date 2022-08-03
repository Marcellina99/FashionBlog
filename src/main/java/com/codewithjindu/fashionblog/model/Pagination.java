package com.codewithjindu.fashionblog.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Setter
@Getter
public class Pagination {
    int pageNumber = 0;
    int pageSize = 15;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "title";
}
