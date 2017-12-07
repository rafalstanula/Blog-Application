package com.stanula.domain;

import javax.persistence.*;

@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author", length = 100, nullable = false)
    private String author;
    private String content;

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
}
