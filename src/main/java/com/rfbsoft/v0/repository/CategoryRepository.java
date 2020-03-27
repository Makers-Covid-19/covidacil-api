package com.rfbsoft.v0.repository;

import com.rfbsoft.v0.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
