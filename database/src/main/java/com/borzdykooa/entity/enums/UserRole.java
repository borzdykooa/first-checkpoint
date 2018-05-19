package com.borzdykooa.entity.enums;

import lombok.Getter;

@Getter
public enum  UserRole {
    CLIENT ("клиент"),
    ADMIN ("администратор");

    private String description;

    UserRole(String description) {
        this.description = description;
    }
}
