package com.codeup.gonzaloblog.models;

import javax.persistence.*;

@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;


    public Post() {
    }

    public Post(String title, String body, long id) {
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setId(long id) {
        this.id = id;
    }
}
