package com.example.javaproject5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stores Information about a Pepperoni Pizza
 * addAllToppings for this option
 * @author Ishani Mhatre
 * @author Keerthana Talla
 */
public class PepperoniPizza extends Pizza {

    private static String pizzaType = "Pepperoni"; //case of pepperoni speciality pizza
    private static ArrayList<Topping> topping = new ArrayList<>(Arrays.asList(Topping.PEPPERONI));

    /**
     * Constructor for Deluxe Pizza Object
     */
    public PepperoniPizza() {
        super();
        this.addAllTopings();
        this.setSauce("tomato");
    }

    /**
     * Adds all the toppings to the pizza
     */
    private void addAllTopings() {
        addTopping(Topping.PEPPERONI);
    }

    public static ArrayList<Topping> getStandardToppings(){
        return topping;
    }

    /**
     * Returns the price depending on the pizza size
     * @return 10.99 if Pizza is small, 12.99 if medium, 14.99 if large
     */
    @Override
    public double price() {
        double basePrice=0;

        switch (getSize()) {
            case SMALL:
                basePrice = 10.99;
                break;
            case MEDIUM:
                basePrice = 12.99;
                break;
            case LARGE:
                basePrice = 14.99;
                break;
        }

        return basePrice + hasExtraCheeseSauce();
    }

    /**
     * Gets pizza type of this object
     * @return pizza type as a string
     */
    public String getPizzaType() {
        return pizzaType;
    }

    /**
     * boolean function for abstract method located in Pizza class
     * @param topping from topping array based on user input
     * @return false
     */
    public boolean add(Topping topping){
        return false;
    }

    /**
     * boolean function for abstract method located in Pizza class
     * @param topping from topping array based on user input
     * @return false
     */
    public boolean remove(Topping topping) {
        return false;
    }
}