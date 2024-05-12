package com.vti.blogapp.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @jakarta.persistence.Id
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "comment_id_generator", type = CommentIdGenerator.class)
    @GeneratedValue(generator = "comment_id_generator")
    @Column(name = "id")
//    @JdbcType(JdbcType.class)
    private String id;

    @Column(name = "name", length = 50, nullable = false)
    private String name; // 50

    @Column(name = "email", length = 75, nullable = false)
    private String email; // 75

    @Column(name = "body", length = 100, nullable = false)
    private String body; // 100

    @Column(name = "status", nullable = false)
//    @Enumerated(value = EnumType.ORDINAL)
    @Convert(converter = CommentStatusConverter.class)
    private Status status;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }


    public enum Status {
        REVIEW, OPEN, CLOSED
    }


}
