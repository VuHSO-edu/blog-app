package com.vti.blogapp.repository;

import com.vti.blogapp.entity.Post;
import com.vti.blogapp.form.PostDeleteForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    // Cách 1: Method names
    // findBy,countBy,deleteBy,exitstsBy
    Page<Post> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);
    void deleteByTitle(String title);

    // Cách 2 @Query annotation
    // Hibernate Query Language: HQL
//    @Query("SELECT * FROM Post WHERE description =?2 AND id = ?1")
//    void deleteByDesc(Long id, String description);
//    @Query("SELECT * FROM Post WHERE description = :description ")
//    void deleteByDesc(@Param("description") String description);
    // Structured Quert Language: SQL
    @Query(value = "DELETE from post WHERE description = ?1" , nativeQuery = true)
    @Modifying
    void deleteByDesc(String description);



}
