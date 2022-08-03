package com.codewithjindu.fashionblog.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends BaseEntity{
    private String title;
    private String content;
    @ManyToOne
    private Categories categories;
    @ManyToOne
    User user;
}
