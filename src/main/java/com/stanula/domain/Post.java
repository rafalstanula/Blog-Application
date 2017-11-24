package com.stanula.domain;

public final class Post {

    private final String postName;

    private Post(String postName) {
        this.postName = postName;
    }

    public static Post createInstance(String postName){
        return new Post(postName);
    }
}
