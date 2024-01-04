package com.ll.medium_clone_coding.domain.member.member.entity;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"),
    PAID("ROLE_PAID");

    MemberRole(String value) {
        this.value = value;
    }

    private String value;
}
