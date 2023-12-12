package com.example.javaproject5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stores Information about a Loveryboy Pizza
 * addAllToppings for this option
 * @author Keerthana Talla
 * @author Ishani Mhatre
 */
public class LoverboyPizza extends Pizza {
    private static String pizzaType = "Loverboy"; //case of supreme speciality pizza
    private static ArrayList<Topping> topping = new ArrayList<>(Arrays.asList( Topping.PEPPERONI, Topping.ONION, Topping.BLACK_OLIVE));

    /**
     * public constructor to avoid errors
     */
    public LoverboyPizza(){
        super();
        this.addAllTopings();
        this.setSauce("tomato");
    }

    /**
     * method to add relevant toppings
     */
    private void addAllTopings() {
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.BLACK_OLIVE);
        addTopping(Topping.ONION);
    }
    public static ArrayList<Topping> getStandardToppings(){
        return topping;
    }

    /**
     * Returns the price depending on the pizza size
     * @return 15.99 if Pizza is small, 17.99 if medium, 19.99 if large
     */
    @Override
    public double price() {
        double basePrice=0;

        switch (getSize()) {
            case SMALL:
                basePrice = 12.99;
                break;
            case MEDIUM:
                basePrice = 14.99;
                break;
            case LARGE:
                basePrice = 16.99;
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
     * @param topping
     * @return false
     */
    public boolean add(Topping topping){
        return false;
    }

    /**
     * boolean function for abstract method located in Pizza class
     * @param topping
     * @return false
     */
    public boolean remove(Topping topping) {
        return false;
    }
}