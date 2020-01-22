/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviationexercise;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Anton
 */
public class OrderList {
    public static final String ORDER_PATH = "./src/coding-assigment-orders.json";
    private ArrayList<Order> orders = null;
    
    public OrderList() {
        orders = new ArrayList();
        try{
            JSONObject rawOrders = (JSONObject) new JSONParser().parse(new FileReader(ORDER_PATH));
            HashMap<String, JSONObject> ordersMap = (HashMap)rawOrders;
            ArrayList<String> keys = new ArrayList(rawOrders.keySet());
            for(String key: keys) {
                Order newOrder = new Order(key, (String) ordersMap.get(key).get("destination"));
                orders.add(newOrder);
            }
            orders.sort((Order q, Order t) -> {
                return q.getOrderId().compareTo(t.getOrderId());
            });
        } catch(Exception e){
            System.out.println(e);
        }
    }
    // given the flight list, foes through the list of unassigned orders and assigns them to flights
    // also links the orders to the flights they are being taken on for convenience
    public void assignOrdersToFlights(FlightList flights) {
        for(Order item: orders){
            if (!item.isLoaded()) { //load only items that are not loaded
                try {
                    Flight schedule = flights.getList().stream().filter(q -> {
                        return q.getArrivalCode().compareTo(item.getDestinationCode()) == 0;
                    }).filter(q -> {
                        return q.hasCapacity();
                    }).findFirst().get();
                    schedule.loadOrder(item);
                    item.loadOrder(schedule);
                } catch (NoSuchElementException e) {
                    // none found
                }
            }
        }
    }
    
    // print out all the existing orders
    public void print() {
            for(Order order: orders){
                if (order.isLoaded()) {
                    System.out.println("order: " + order.getOrderId()
                        + ", flightNumber" + order.getFlight().getFlightNo()
                        + ", departure: " + order.getFlight().getDeparture()
                        + ", arrival: " + order.getFlight().getArrival()
                        + ", day" + order.getFlight().getDay());
                }
            }
            for(Order order: orders) {
                if (!order.isLoaded()) {
                    System.out.println("order: " + order.getOrderId()
                        + ", flightNumber: not scheduled");
                }
            }
    }
    
    // add a new order to the list
    public void addOrder(Order item) {
        orders.add(item);
    }
}
