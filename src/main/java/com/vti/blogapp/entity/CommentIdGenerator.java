package com.vti.blogapp.entity;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CommentIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        var comment = (Comment) object;
        var hql = "SELECT COUNT(*) FROM Comment WHERE status = :status";
        var count = session.createSelectionQuery(hql, Long.class)
                .setParameter("status", comment.getStatus())
                .getSingleResult();
        return comment.getStatus().toString().charAt(0) + "-" + (count + 1);
    }
}
