package com.stanula.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "author", length = 100, nullable = false)
    @NotEmpty
    private String author;

    @Column(name = "content", length = 1000, nullable = false)
    @NotEmpty
    private String content;

    private Post() {
    }

    private Post(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public static Post createPost (final String author, final String content){
        return new Post(author, content);
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }
}
