package com.example.javaproject5;

import java.util.Locale;

/**
 * Enum class for topping choices
 * @author Ishani Mhatre
 * @author Keerthana Talla
 */
public enum Topping {
    SAUSAGE,
    PEPPERONI,
    GREEN_PEPPER,
    ONION,
    MUSHROOM,
    HAM,
    BLACK_OLIVE,
    BEEF,
    SHRIMP,
    SQUID,
    CRAB_MEAT,
    JALAPENOS,
    BACON;


    public static String getName(Topping topping) {
        switch (topping) {
            case SAUSAGE:
                return "Sausage";
            case PEPPERONI:
                return "Pepperoni";
            case GREEN_PEPPER:
                return "Green Pepper";
            case ONION:
                return "Onion";
            case MUSHROOM:
                return "Mushroom";
            case HAM:
                return "Ham";
            case BLACK_OLIVE:
                return "Black Olive";
            case BEEF:
                return "Beef";
            case SHRIMP:
                return "Shrimp";
            case SQUID:
                return "Squid";
            case CRAB_MEAT:
                return "Crab Meat";
            case JALAPENOS:
                return "Jalapenos";
            case BACON:
                return "Bacon";
            default:
                throw new IllegalArgumentException("Unsupported topping: " + topping);
        }

    }
}