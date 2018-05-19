package com.borzdykooa.entity.enums;

import lombok.Getter;

@Getter
public enum AdminRole {

    SUPER_ADMIN("администратор"),
    PRESCRIPTION_MANAGER("менеджер рецептов");

    private String description;

    AdminRole(String description) {
        this.description = description;
    }
}
