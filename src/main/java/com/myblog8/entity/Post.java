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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title",nullable=false)
    private String title;
    @Column(name="description", nullable=false)
    private String description;

    @Column(name="content", nullable=false)
    private String content;
}
