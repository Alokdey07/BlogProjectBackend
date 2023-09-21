package com.myblog8.entity;

import ch.qos.logback.classic.db.names.ColumnName;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(
        name="posts", uniqueConstraints = {@UniqueConstraint(columnNames ={"title"})}
        )
@Data
public class Post {
    // The unique identifier for each post
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // The title of the blog post, which is required and mapped to the "title" column
    @Column(name="title",nullable=false)
    private String title;

    // The description of the blog post, which is required and mapped to the "description" column
    @Column(name="description", nullable=false)
    private String description;

    // The content of the blog post, which is required and mapped to the "content" column
    @Column(name="content", nullable=false)
    private String content;
}
