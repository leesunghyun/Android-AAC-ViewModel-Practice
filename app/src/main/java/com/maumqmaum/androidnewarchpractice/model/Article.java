package com.maumqmaum.androidnewarchpractice.model;

public class Article {
    public String title;
    public String description;
    public String author;
    public boolean isLiked;

    public Article(String title, String description, String author, boolean isLiked) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.isLiked = isLiked;
    }
}
