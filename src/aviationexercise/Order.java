/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviationexercise;

/**
 *
 * @author Anton
 */
public class Order {
    private String orderId;
    private Flight flight;
    private boolean isLoaded;
    private String destinationCode;
    
    public Order(String orderId, String destinationCode) {
        this.orderId = orderId;
        this.flight = null;
        this.isLoaded = false;
        this.destinationCode = destinationCode;
    }
    
    // modifies the order data to indicate it has not been loaded
    public void unloadOrder() {
        flight = null;
        isLoaded = false;
    }
    
    // modifies the order data to indicate it has been loaded
    public void loadOrder(Flight flight) {
        if(!isLoaded) {
            this.flight = flight;
            isLoaded = true;
        }
    }
    /**************************GETTERS*****************************/
    public String getDestinationCode() {
        return destinationCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public boolean isLoaded() {
        return isLoaded;
    }
    
    public Flight getFlight() {
        return flight;
    }
}
