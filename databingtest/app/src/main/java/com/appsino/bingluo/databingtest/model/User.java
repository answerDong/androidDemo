package com.appsino.bingluo.databingtest.model;

/**
 * Created by Answer on 2018/5/29.
 */

public class User {
    private String name;
    private String password;

    public User(String leavesC, String s) {
        setName(leavesC);
        setPassword(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
