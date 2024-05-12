package com.vti.blogapp.repository;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {
    Page<Comment> findByPostId(Long postId, Pageable pageable);

    Page<Comment> findCommentByBody(String body, Pageable pageable);

    // search, minCretedAt, maxCreatedAt

}
