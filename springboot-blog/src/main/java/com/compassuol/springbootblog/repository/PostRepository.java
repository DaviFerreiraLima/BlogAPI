package com.compassuol.springbootblog.repository;

import com.compassuol.springbootblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
