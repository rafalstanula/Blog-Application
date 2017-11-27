package com.stanula.domain;

public final class Post {

    private final String author;
    private final String content;

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
