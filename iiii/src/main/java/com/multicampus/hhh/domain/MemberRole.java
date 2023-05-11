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

    @Override
    public String toString() {
        return this.name();
    }

    public static MemberRole valueOf(int value) {
        for (MemberRole role : MemberRole.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("MemberRole enum error " + value);
    }
}
