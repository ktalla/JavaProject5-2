package com.example.javaproject5;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class to add new order to array list based on user input
 * @author Ishani Mhatre
 * @author Keerthana Talla
 */
public class Order {

    private int orderNumber; //number integer of current order
    private ArrayList<Pizza> pizzas; //array list for all pizzas in current order
    private static final double TAXRATE = 0.06625; //tax rate for every order

    /**
     * Constructor of Order Object.
     *
     */
    public Order() {
        //this.orderNumber = StoreOrders.generateOrderNum();
        this.pizzas = new ArrayList<Pizza>();
    }

    public Order(ArrayList<Pizza> pizzas){
        this.pizzas = pizzas;
    }

    /**
     * @return the order number of the given order.
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * @return the arraylist holding the pizza.
     */
    public ArrayList<Pizza> getPizzas() {
        return this.pizzas;
    }

    /**
     * @return the subtotal price of the pizza in the given order.
     */
    public double getSubtotal() {
        double price = 0;
        for (Pizza p : pizzas) {
            price += p.price();
        }
        return price;
    }

    /**
     * @return the tax rate of the subtotal price of the pizza in the given order.
     */
    public double getTax() {
        return (this.getSubtotal() * TAXRATE);
    }

    /**
     * @return the total price of the pizza in the given order.
     */
    public double getOrderTotal() {
        return (this.getSubtotal() + this.getTax());
    }

    /**
     * Add the pizza into the arraylist holding pizza.
     *
     * @param pizza the selected pizza.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Remove the pizza into the arraylist holding pizza.
     *
     * @param pizza the selected pizza.
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Check if two orders are the same by checking customer's phone number.
     *
     * @param number the potential customer number of the order.
     * @return true if two orders are the same, false otherwise.
     */
    public boolean isSameOrder(int number) {
        return number==orderNumber;
    }

    /**
     * Clears the entire order
     */
    public void clearOrder() {
        this.pizzas.clear();
    }
}