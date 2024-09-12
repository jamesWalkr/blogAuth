package com.example.Blog.Exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException (String message){
        super(message);
    }
}
