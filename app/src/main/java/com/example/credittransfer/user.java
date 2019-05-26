package com.example.credittransfer;

public class user {
    private  String name;
    private Integer credit;

    public user(String name, Integer credit) {
        this.name = name;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public Integer getCredit() {
        return credit;
    }

}
