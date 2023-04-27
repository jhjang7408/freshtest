package com.multicampus.hhh.domain;


// role domain 처리
public enum MemberRole {
    USER(1),
    ADMIN(2);

    private final int value;

    MemberRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
