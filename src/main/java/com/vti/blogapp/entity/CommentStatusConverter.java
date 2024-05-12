package com.vti.blogapp.entity;

import jakarta.persistence.AttributeConverter;
import org.hibernate.mapping.Property;
import org.hibernate.metamodel.internal.AttributeContext;
import org.hibernate.metamodel.model.domain.ManagedDomainType;

import javax.swing.text.AbstractDocument;

public class CommentStatusConverter implements AttributeConverter<Comment.Status, Character> {

    @Override
    public Character convertToDatabaseColumn(Comment.Status status) {
        return status.toString().charAt(0);
    }

    @Override
    public Comment.Status convertToEntityAttribute(Character code) {
        if (code == 'R') {
            return Comment.Status.REVIEW;
        } else if (code == 'O') {
            return Comment.Status.OPEN;
        } else if (code == 'C') {
            return Comment.Status.CLOSED;
        } else {
            throw new IllegalArgumentException("Invalid value: " + code);
        }
    }
}
