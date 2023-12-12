package com.example.javaproject5;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stores Information about a Seafood Pizza
 * addAllToppings for this option
 * @author Ishani Mhatre
 * @author Keerthana Talla
 */
public class SeafoodPizza extends Pizza {

    private static String pizzaType = "Seafood"; //case of seafod speciality pizza
    private static ArrayList<Topping> topping = new ArrayList<>(Arrays.asList(Topping.SHRIMP, Topping.SQUID, Topping.CRAB_MEAT));

    /**
     * Constructor for Deluxe Pizza Object
     */
    public SeafoodPizza() {
        super();
        this.addAllTopings();
        this.setSauce("alfredo");
    }

    /**
     * Adds all the toppings to the pizza
     */
    private void addAllTopings() {
        addTopping(Topping.SHRIMP);
        addTopping(Topping.SQUID);
        addTopping(Topping.CRAB_MEAT);
    }

    public static ArrayList<Topping> getStandardToppings(){
        return topping;
    }

    /**
     * Returns the price depending on the pizza size
     * @return 17.99 if Pizza is small, 19.99 if medium, 21.99 if large
     */
    @Override
    public double price() {
        double basePrice=0;

        switch (getSize()) {
            case SMALL:
                basePrice = 17.99;
                break;
            case MEDIUM:
                basePrice = 19.99;
                break;
            case LARGE:
                basePrice = 21.99;
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
     * @param topping from Topping array
     * @return false for abstract
     */
    public boolean add(Topping topping){
        return false;
    }

    /**
     * boolean function for abstract method located in Pizza class
     * @param topping from Topping array
     * @return false for abstract
     */
    public boolean remove(Topping topping) {
        return false;
    }
}