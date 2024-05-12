package com.vti.blogapp.specification;

import com.vti.blogapp.entity.Comment;
import com.vti.blogapp.form.CommentFilterForm;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.LinkedList;

public class CommentSpecification {
    public static Specification<Comment> buildSpec(CommentFilterForm form) {
        return form == null ? null : (root, query, criteriaBuilder) -> {
            var predicates = new LinkedList<Predicate>();

            var search = form.getSearch();
            if (StringUtils.hasText(search)) {
                var pattern = "%" + search.trim() + "%";
                var hasBodyLike = criteriaBuilder.like(root.get("body"), pattern);
                var hasNameLike = criteriaBuilder.like(root.get("name"), pattern);
                var predicate = criteriaBuilder.or(hasBodyLike, hasNameLike);
                predicates.add(predicate);
            }

            var postId = form.getPostId();
            if (postId != null) {
                var predicate = criteriaBuilder.equal(root.get("post").get("id"), postId);
                predicates.add(predicate);
            }

            var status = form.getStatus();
            if (status != null) {
                var predicate = criteriaBuilder.equal(root.get("status"), status);
                predicates.add(predicate);
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
