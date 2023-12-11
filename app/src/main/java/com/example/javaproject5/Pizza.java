package com.example.javaproject5;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Pizza class with necessary parameters and setter/getter methods
 * @author Ishani Mhatre
 * @author Keerthana Talla
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings; //Topping is a enum class
    protected Size size; //Size is a enum class
    protected Sauce sauce; //Sauce is a enum class
    protected boolean extraSauce; //boolean value true or not
    protected boolean extraCheese; //boolean value true or not

    public static final double PRICE_SIZE = 2.00;
    public static final double PRICE_TOPPING = 1.49;

    /**
     * Default constructor to avoid errors
     */
    public Pizza() {
        this.toppings = new ArrayList<Topping>();
    }

    /**
     * Initializes pizza object with required parameters
     * @param toppings based on 13 options for user
     * @param size 3 options of small, medium, and large
     * @param sauce alfredo or tomato options
     * @param extraSauce boolean value for adding or removing, changes price
     * @param extraCheese boolean value for adding or removing, changes price
     */
    public Pizza(List<Topping> toppings, Size size, Sauce sauce, boolean extraSauce, boolean extraCheese) {
        this.toppings = (ArrayList<Topping>) toppings;
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     * This method adds to an array list for toppings
     * @param selectedTopping based on user input for which topping is selected
     */
    public void addTopping(Topping selectedTopping) {
        toppings.add(selectedTopping);
    }


    /**
     * This method removes from an array list for toppings
     * @param selectedTopping based on user input for which topping is selected
     */
    public void removeTopping(Topping selectedTopping) {
        toppings.remove(selectedTopping);
    }



    /**
     * This method gets all the toppings stored in toppings array list
     * @return string for toppings in toppings array
     */
    public ArrayList <Topping> getToppings() { return this.toppings; }

    /**
     * Sets the array list containing all the toppings
     *
     * @param toppings the array list containing all the toppings
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Set true or false based on user input of extra sauce
     * @param extraSauce boolean value for extra sauce option
     */
    public void setExtraSauce(boolean extraSauce) {
        this.extraSauce = extraSauce;
    }

    /**
     * Set true or false based on user input of extra cheese
     * @param extraCheese boolean value for extra cheese option
     */
    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    public double hasExtraCheeseSauce(){
        double extra = 0.0;
        if(extraCheese){
            extra += 1.0;
        }
        if(extraSauce){
            extra += 1.0;
        }
        return extra;
    }

    /**
     * Returns the price of the pizza
     *
     * @return the price of the pizza
     */
    public abstract double price();

    public abstract boolean add(Topping topping);

    public abstract boolean remove(Topping topping);


    /**
     * Returns the size of the pizza
     *
     * @return the size of the pizza
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * Sets the size of the pizza
     *
     * @param size the size of the pizza
     */
    public void setSize(String size) {
        switch (size.toLowerCase()) {
            case "small":
                this.size = Size.SMALL;
                break;
            case "medium":
                this.size = Size.MEDIUM;
                break;
            case "large":
                this.size = Size.LARGE;
                break;
        }

    }

    /**
     * Returns the size of the pizza
     *
     * @return the size of the pizza
     */
    public Sauce getSauce() {
        return this.sauce;
    }

    /**
     * Sets the size of the pizza
     *
     * @param sauce the size of the pizza
     */
    public void setSauce(String sauce) {
        switch (sauce.toLowerCase()) {
            case "tomato":
                this.sauce = Sauce.TOMATO;
                break;
            case "alfredo":
                this.sauce = Sauce.ALFREDO;
                break;
        }

    }

    @Override
    public String toString() {
        String info = "";
        if (this instanceof DeluxePizza) {
            info += "Deluxe";
        } else if (this instanceof SeafoodPizza) {
            info += "Seafood";
        } else if (this instanceof PepperoniPizza) {
            info += "Pepperoni";
        } else if (this instanceof SupremePizza) {
            info += "Supreme";
        } else if (this instanceof MeatzzaPizza) {
            info += "Meatzza";
        } else if (this instanceof BuildYourOwnPizza) {
            info += "Build Your Own";
        }
        info += " pizza,";
        for (Topping t : this.toppings) {
            info += (" " + t + ",");
        }
        info += ("Size: " + size + ",");
        info +=("Sauce: "+ sauce + ", ");
        info += (" $" + String.format("%.2f", this.price()));
        return info;
    }

}