/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviationexercise;

import java.util.Scanner;

/**
 *
 * @author Anton
 */
public class Menu {
    public static final int SCHEDULE_CODE = 1;
    public static final int REMOVE_CODE = 2;
    public static final int PRINT_CODE = 3;
    public static final int LOAD_ORDERS_CODE = 4;
    public static final int PRINT_ORDERS_CODE = 5;
    public static final int EXIT_CODE = 6;
    public static final int UNKNOWN = 2000;
    
    private Scanner stdin = null;
    private int choice = -1; //menu controller
    private FlightList flightList = null;
    private OrderList orderList = null;
    
    public Menu() {
        stdin = new Scanner(System.in);
        flightList = new FlightList();
        orderList = new OrderList();
        orderList.assignOrdersToFlights(flightList);
    }

    // menu loop
    public void start() {
        while(choice != EXIT_CODE) {
            System.out.println("Welcome");
            System.out.println("______________________________________");
            System.out.println(SCHEDULE_CODE + ". Schedule a flight");
            System.out.println(REMOVE_CODE + ". Remove a flight");
            System.out.println(PRINT_CODE + ". Print Scehdule");
            System.out.println(LOAD_ORDERS_CODE + ". Reload Orders");
            System.out.println(PRINT_ORDERS_CODE + ". Print Orders");
            System.out.println(EXIT_CODE + ". Exit");
            try{
                choice = stdin.nextInt();
            } catch (Exception e) {
                choice = UNKNOWN;
            } finally {
                stdin.nextLine();
            }
            switch(choice) {
                case SCHEDULE_CODE:
                    flightList.addFlight();
                    break;
                case REMOVE_CODE:
                    flightList.removeFlight();
                    break;
                case PRINT_CODE:
                    flightList.printFlights();
                    break;
                case LOAD_ORDERS_CODE:
                    orderList.assignOrdersToFlights(flightList);
                    break;
                case PRINT_ORDERS_CODE:
                    orderList.print();
                    break;
                case EXIT_CODE:
                    break;
                default:
                    System.out.println("Unknown command, try again!");
            }
            System.out.println("______________________________________");
        }
    }
}
