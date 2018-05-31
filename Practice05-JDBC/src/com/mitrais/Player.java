package com.mitrais;

public class Player {

    private String name;
    private String email;
    private Integer score;
    private Integer id;

    public Player (String name, String email, Integer score) {
        this.name = name;
        this.email = email;
        this.score = score;
    }

    public Player (String name, String email, Integer score, Integer id) {
        this.name = name;
        this.email = email;
        this.score = score;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
