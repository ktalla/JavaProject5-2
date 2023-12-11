package com.example.javaproject5;

import android.accessibilityservice.AccessibilityService;

/**
 * Class to initialize new pizza based on pizza type
 * @author Ishani Mhatre
 */
public class PizzaMaker {
    public static Pizza createPizza(String pizzaType) {
        switch (pizzaType.toLowerCase()) {
            case "deluxe":
                return new DeluxePizza();
            case "supreme":
                return new SupremePizza();
            case "meatzza":
                return new MeatzzaPizza();
            case "seafood":
                return new SeafoodPizza();
            case "pepperoni":
                return new PepperoniPizza();
            case "buildyourown":
                return new BuildYourOwnPizza();
            case "cardip":
                return new CardiPPizza();
            case "loverboy":
                return new LoverboyPizza();
            case "grandma":
                return new GrandmaPizza();
            case "baddie":
                return new BaddiePizza();
            case "veggielovers":
                return new VeggieLoversPizza();
            default:
                throw new IllegalArgumentException("Invalid pizza type: " + pizzaType);
        }
    }


}