package com.codewithjindu.fashionblog.repository;

import com.codewithjindu.fashionblog.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikePost, Long> {
}
