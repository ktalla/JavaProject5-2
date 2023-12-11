package com.example.javaproject5;

import java.util.List;

public class Item {

    String specialtyPizzaName;
    List<Topping> toppings;
    int image;
    Sauce sauce;

    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public String getSpecialtyPizzaName() {
        return specialtyPizzaName;
    }

    public void setSpecialtyPizzaName(String specialtyPizzaName) {
        this.specialtyPizzaName = specialtyPizzaName;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Item(String specialtyPizzaName, List<Topping> toppings, int image, Sauce sauce) {
        this.specialtyPizzaName = specialtyPizzaName;
        this.toppings = toppings;
        this.image = image;
        this.sauce = sauce;
    }


}