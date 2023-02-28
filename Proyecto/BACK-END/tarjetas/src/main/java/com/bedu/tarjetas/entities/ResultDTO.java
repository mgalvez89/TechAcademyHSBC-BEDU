package com.bedu.tarjetas.entities;

public class ResultDTO {

    private String name_package;
    private String name_product;
    private String number_cards;
    private String name_location;
    private String name_branch;

    public ResultDTO(String name_package, String name_product, String number_cards, String name_location, String name_branch) {
        this.name_package = name_package;
        this.name_product = name_product;
        this.number_cards = number_cards;
        this.name_location = name_location;
        this.name_branch = name_branch;
    }

    public String getName_package() {
        return name_package;
    }

    public void setName_package(String name_package) {
        this.name_package = name_package;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getNumber_cards() {
        return number_cards;
    }

    public void setNumber_cards(String number_cards) {
        this.number_cards = number_cards;
    }

    public String getName_location() {
        return name_location;
    }

    public void setName_location(String name_location) {
        this.name_location = name_location;
    }

    public String getName_branch() {
        return name_branch;
    }

    public void setName_branch(String name_branch) {
        this.name_branch = name_branch;
    }
}
