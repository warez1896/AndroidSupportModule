package com.ediwow.supportmodule.objectholder;

public class User {
    private int id;
    private String name, username;

    public User(int id, String name, String username) {
        setId(id);
        setName(name);
        setUsername(username);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
