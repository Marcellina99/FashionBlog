package com.codewithjindu.fashionblog.repository;

import com.codewithjindu.fashionblog.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
