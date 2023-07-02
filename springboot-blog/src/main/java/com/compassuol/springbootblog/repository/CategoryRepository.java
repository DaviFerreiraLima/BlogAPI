package com.compassuol.springbootblog.repository;

import com.compassuol.springbootblog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
