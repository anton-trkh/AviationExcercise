/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviationexercise;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
/**
 *
 * @author Anton
 */
public class FlightList {
    public static final String FLIGHTS_PATH = "./src/default_flights.json";
    
    private ArrayList<Flight> flights = null;
    private Scanner stdin;

    public FlightList() {
        flights = new ArrayList();
        try {
            stdin = new Scanner(System.in);
            JSONObject raw = (JSONObject) new JSONParser()
                    .parse(new FileReader(FLIGHTS_PATH));
            JSONArray rawFlights = (JSONArray) raw.get("flights");
            for (Object q: rawFlights) {
                JSONObject item = (JSONObject) q;
                flights.add(new Flight((String)item.get("departure"),
                    (String)item.get("departureCode"), (String)item.get("arrival"),
                    (String)item.get("arrivalCode"), (String)item.get("day")));
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    // remove a flight from the flight list, based on flight number
    public void removeFlight() {
        int flightNo = 0;
        try {
            System.out.println("________________________");
            System.out.println("Enter the flight number you wish to remove:");
            flightNo = stdin.nextInt();
            System.out.println("________________________");
        } catch (Exception e) {
            System.out.println(e);
            stdin.nextLine();
            return;
        }
        
        Flight toRemove = null;
        for (Flight item: flights) {
            if (flightNo == item.getFlightNo()) toRemove = item;
        }
        
        if (toRemove != null) {
            flights.remove(toRemove);
            for (Order order: toRemove.getOrders()) {
                order.unloadOrder();
            }
            System.out.println("Flight " + flightNo + " removed succesfully!");
        }
        else System.out.println("Flight " + flightNo + " not found, please try again!");
        
        System.out.println("________________________");
    }
    
    // addes a flight to the flight list, aks for info
    public void addFlight() {
        String departure, departureCode, arrival, arrivalCode, day = null;
        System.out.println("________________________________");
        System.out.println("Enter flight day: ");
        while(day == null || day.length() < 1) day = stdin.nextLine(); // the loop fixes a strange bug, that's difficult to describe concisely
        System.out.println("Enter departure airport:");
        departure = stdin.nextLine();
        System.out.println("Enter departure airport code:");
        departureCode = stdin.nextLine();
        System.out.println("Enter arrival airport");
        arrival = stdin.nextLine();
        System.out.println("Enter arrival airport code:");
        arrivalCode = stdin.nextLine();
        
        flights.add(new Flight(departure, departureCode, arrival, arrivalCode, day));
    }
    
    // retrieves the base ArrayList of flights
    public ArrayList<Flight> getList() {
        return flights;
    }
    
    // prints the list of flights
    public void printFlights() {
        for (Flight item: flights) {
            item.print();
        }
    }
}
