package com.compassuol.springbootblog.repository;


import com.compassuol.springbootblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);

    Comment findByPostIdAndId(long postId,long id);
}
