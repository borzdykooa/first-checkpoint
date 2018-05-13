package by.borzdykooa.entity.enums;

import lombok.Getter;

@Getter
public enum Role {

    SUPER_ADMIN("администратор"),
    PRESCRIPTION_MANAGER("менеджер рецептов");

    private String description;

    Role(String description) {
        this.description = description;
    }
}
