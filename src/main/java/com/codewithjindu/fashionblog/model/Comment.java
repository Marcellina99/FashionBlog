package com.codewithjindu.fashionblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity{
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}

