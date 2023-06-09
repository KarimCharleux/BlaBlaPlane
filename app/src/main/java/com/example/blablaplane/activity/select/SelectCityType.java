package com.example.blablaplane.activity.select;

public enum SelectCityType {
    SEARCH_DEPARTURE("Départ", "Séléctionnez votre ville de départ", "Valider"),
    SEARCH_DESTINATION("Destination", "Séléctionnez votre ville de destination", "Valider"),
    CREATE_DEPARTURE("D'ou partez-vous ?", "Séléctionnez votre ville de départ", "Suivant"),
    CREATE_DESTINATION("Ou allez-vous ?", "Séléctionnez votre ville de destination", "Suivant");

    private final String title;
    private final String Description;
    private final String buttonText;

    SelectCityType(String title, String description, String buttonText) {
        this.title = title;
        Description = description;
        this.buttonText = buttonText;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public String getButtonText() {
        return buttonText;
    }
}
