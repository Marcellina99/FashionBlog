package com.codewithjindu.fashionblog.repository;

import com.codewithjindu.fashionblog.model.Categories;
import com.codewithjindu.fashionblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByTitleContainsIgnoreCase(String title);
}
