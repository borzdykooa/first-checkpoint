package com.borzdykooa.entity.enums;

import lombok.Getter;

@Getter
public enum Status {

    DONE("выполнен"),
    PROCESSED("обрабатывается");

    private String description;

    Status(String description) {
        this.description = description;
    }
}
