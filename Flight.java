import java.util.*;

class Flight {
    private static int flightCounter = 1;
    protected int flightID;
    protected int tickets;
    protected int price;
    public ArrayList<PassengerDetails> passengerList;

    public Flight() {
        this.flightID = flightCounter++;
        this.tickets = 60; // Initial number of tickets
        this.price = 5000; // Initial ticket price
        this.passengerList = new ArrayList<>();
    }

    public void addPassengerDetails(String details, int numberOfTickets, int passengerID) {
        passengerList.add(new PassengerDetails(passengerID, details, numberOfTickets));
        tickets -= numberOfTickets;
        price += numberOfTickets * 200;
    }

    public boolean cancelTicket(int passengerID) {
        for (PassengerDetails passengerDetails : passengerList) {
            if (passengerDetails.getPassengerID() == passengerID) {
                tickets += passengerDetails.getNumberOfTickets();
                price -=  passengerDetails.getNumberOfTickets() * 200;
                passengerList.remove(passengerDetails);
                return true; // Cancellation successful
            }
        }
        return false; // Passenger not found
    }

    public void flightSummary() {
        System.out.println("Flight ID: " + flightID);
        System.out.println("Remaining Tickets: " + tickets);
        System.out.println("Current Ticket Price: " + price);
    }

    public void printDetails() {
        System.out.println("Flight ID: " + flightID);
        System.out.println("Remaining Tickets: " + tickets);
        System.out.println("Current Ticket Price: " + price);
        for (PassengerDetails passengerDetails : passengerList) {
            System.out.println(passengerDetails);
        }
    }
}

class PassengerDetails {
    final int passengerID;          // final keyword is used to manage passengerId, details and numberOfTickets as Immutable objects and to ensure thread safety
    final String details;
    final int numberOfTickets;

    public  PassengerDetails(int passengerID, String details, int numberOfTickets) {
        this.passengerID = passengerID;
        this.details = details;
        this.numberOfTickets = numberOfTickets;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    @Override
    public String toString() {
        return details;
    }
}