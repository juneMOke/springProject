package org.o7planning.thymeleaf.controller;

public enum  State {
    FACTORIAL ("Nombre paire inférieur à 51" ),
    FIBONACCI ("Nombre Impaire"),
    NEGATIVE ("Nombre Négatif"),
    NATURAL ("Nombre paire supérieur à 51");
    private String description;

    State(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
