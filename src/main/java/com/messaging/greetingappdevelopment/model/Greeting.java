package com.messaging.greetingappdevelopment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "greeting")
public class Greeting {
    @Id
    private long id;
    private String content;
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
    public Greeting()
    {}

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
