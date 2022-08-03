package com.codewithjindu.fashionblog.model;

import lombok.*;

import javax.persistence.Entity;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Categories extends BaseEntity{
    private String title;
}
