package com.example.javaproject5;


import java.util.ArrayList;

    public class Singleton {
        private static final Singleton instance = new Singleton();

        private Order order;
        private StoreOrders storeOrders;

        public Pizza getPizza() {
            return pizza;
        }

        public void setPizza(Pizza pizza) {
            this.pizza = pizza;
        }

        private Pizza pizza;

        private boolean orderAdded = false;

        public static Singleton getInstance(){
            return instance;
        }

        public void initializeOrder(ArrayList<Pizza> pizzaList){
            order = new Order(pizzaList);
        }

        public Order getOrder() {
            return order;
        }

        public boolean getOrderAdded(){
            return orderAdded;
        }

        public void setOrderAdded(boolean bool){
            orderAdded = bool;
        }

        public void setOrder(Order newOrder){
            order = newOrder;
        }

        public void initializeStoreOrders(ArrayList <Order> ordersList){
            storeOrders = new StoreOrders(ordersList);
        }

        public StoreOrders getStoreOrders(){
            return storeOrders;
        }

        public void setStoreOrders(StoreOrders newStoreOrder){
            storeOrders = newStoreOrder;
        }

        public int getCurrentOrderNum(){
            if(storeOrders!=null){
                if(storeOrders.getOrders()!=null)
                    return StoreOrders.getNextOrderNumber();
                else
                    return 1;
            }
            else{
                return 1;
            }
        }

    }

