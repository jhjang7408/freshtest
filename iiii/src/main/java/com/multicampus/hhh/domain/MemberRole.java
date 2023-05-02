package com.multicampus.hhh.domain;


// role domain 처리
public enum MemberRole {
    USER(0),
    ADMIN(1);

    private final int value;

    MemberRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
