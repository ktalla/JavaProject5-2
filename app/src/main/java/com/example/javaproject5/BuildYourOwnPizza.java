package com.example.javaproject5;

import java.util.List;

/**
 * Class for Building Your Own Pizza option with minimum and maximum toppings
 * Options for removing and adding topings and outputting correct price
 * @author Ishani Mhatre
 */
public class BuildYourOwnPizza extends Pizza {
    private int numOfToppings = 0; //total toppings on pizza
    private static String pizzaType = "BuildYourOwn"; //build your own case string
    //final static int MIN_TOPPINGS = 3;
    //final static int MAX_TOPPINGS = 7;
    final static double EXTRA_TOPPINGS_COST = 1.49; //cost for every additional topping
    /**
     * Constructor for BuildYourOwn object
     */
    public BuildYourOwnPizza() {
        super();
    }

    /** This method adds a topping to BuildYourOwn pizza.
     * @param topping options for pizza based on user input
     * @return boolean true if topping is added, false otherwise
     */
    public boolean add(Topping topping) {

        Topping selectedTopping = topping;
//
//        //if maximum # of toppings is reached
//        if (numOfToppings >= MAX_TOPPINGS) {
//            return false;
//        }

        //first 3 toppings are free
//        if (numOfToppings <= MIN_TOPPINGS) {
//            this.addTopping(selectedTopping);
//            numOfToppings = getToppings().size();
//            System.out.println(numOfToppings + " add");
//            return true;
//        }

        //over 7 toppings cost extra
        this.addTopping(selectedTopping);
        numOfToppings = getToppings().size();
        return true;

    }

    /**
     * Removes toppings from topping array list
     * @param topping
     * @return true or false
     */
    public boolean remove(Topping topping) {

        Topping selectedTopping = topping;
        this.removeTopping(selectedTopping);
        numOfToppings = getToppings().size();
        return true;
    }

    /**
     * This method calculate the price of BuildYourOwn pizza.
     * @return double cost of BuildYourOwn pizza
     */
    @Override
    public double price() {
        double cost = 0.0;

        //set price based on size
        Size sizeOfPizza = getSize();
        switch(sizeOfPizza) {
            case SMALL:
                cost = 8.99;
                break;
            case MEDIUM:
                cost = 10.99;
                break;
            case LARGE:
                cost = 12.99;
                break;
        }

        //set price based on extra toppings
        if (numOfToppings >= 3) {
            cost = cost + ((numOfToppings - 3) * EXTRA_TOPPINGS_COST);
        }

        return (cost + hasExtraCheeseSauce());
    }
    /**
     * Gets pizza type of this object
     * @return pizza type as a string
     */
    public String getPizzaType() {return pizzaType;}
}