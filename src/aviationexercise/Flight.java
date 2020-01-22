/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviationexercise;

import java.util.ArrayList;

/**
 *
 * @author Anton
 */
public class Flight {
    public static final int MAX_CAPACITY = 20;
    
    private String departure = "Montreal";
    private String departureCode = "YUL";
    
    private final String arrival;
    private final String arrivalCode;
    
    private String day;
    private int FlightNo;
    private int capacity;
    
    private ArrayList<Order> loadedOrders = null;
    
    private static int masterFlightNo = 0;
    public Flight(String arrival, String arrivalCode, String day) {
        this.arrival = arrival;
        this.arrivalCode = arrivalCode;
        this.day = day;
        this.capacity = 0;
        this.loadedOrders = new ArrayList<Order>();
        FlightNo = ++masterFlightNo;
    }
    public Flight(String departure, String departureCode, String arrival, 
            String arrivalCode, String day) {
        this.departure = departure;
        this.departureCode = departureCode;
        this.arrival = arrival;
        this.arrivalCode = arrivalCode;
        this.day = day;
        this.capacity = 0;
        this.loadedOrders = new ArrayList<Order>();
        FlightNo = ++masterFlightNo;
    }

    /*****************************SETTER**************************************/
    
    /* @param order: the order to be loaded
     * @return -1 if over capacity */
    public int loadOrder(Order order) {
        if (capacity < MAX_CAPACITY) {
            loadedOrders.add(order);
            return ++capacity;
        }
        else return -1;
    }
    /*****************************GETTERS**************************************/
    public String getDay() {
        return this.day;
    }
    public int getFlightNumber() {
        return FlightNo;
    }
    public String getDeparture() {
        return this.departure;
    }
    public String getArrival() {
        return this.arrival;
    }
    public ArrayList<Order> getOrders() {
        return this.loadedOrders;
    }
    
    public boolean hasCapacity() {
        return capacity < MAX_CAPACITY;
    }
    public int getFlightNo() {
        return FlightNo;
    }
    public String getArrivalCode() {
        return arrivalCode;
    }
    
    // Prints out the flight info
    public void print() {
        System.out.println("Flight " + FlightNo + ", departure: " +
                departureCode + ", arrival: " + arrivalCode + ", day: " + day);
    }
}
