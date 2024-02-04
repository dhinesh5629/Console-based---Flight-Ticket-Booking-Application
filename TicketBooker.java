import java.util.*;

public class TicketBooker {                          // Main class
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Flight> flights = new ArrayList<>();
        int passengerID = 1;

        while (true) {
            System.out.println("1. Create new Admin\n2. Create new User\n3. User Login\n4. Admin Login\n5. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createNewAdmin();
                    break;

                case 2:
                    createNewUser();
                    break;

                case 3:
                    userLogin(flights, passengerID);
                    break;

                case 4:
                    adminLogin(flights);
                    break;

                case 5:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private static void createNewAdmin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter admin username: ");
        String adminUsername = sc.next();
        System.out.println("Enter admin password: ");
        String adminPassword = sc.next();
        AdminManager.createAdmin(adminUsername, adminPassword);
    }

    private static void createNewUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user username: ");
        String userUsername = sc.next();
        System.out.println("Enter user password: ");
        String userPassword = sc.next();
        UserManager.addUser(userUsername, userPassword);
    }

    private static void userLogin(ArrayList<Flight> flights, int passengerID) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();
        User user = UserManager.authenticateUser(username, password);

        if (user != null) {
            System.out.println("User logged in successfully!");

            while (true) {
                System.out.println("1. Search Flight 2. Exit");
                int userChoice = sc.nextInt();

                if (userChoice == 1) {
                    searchFlight(flights, user);
                    passengerID++;
                } else if (userChoice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Invalid credentials");
        }
    }

    private static void searchFlight(ArrayList<Flight> flights, User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Flight ID");
        int fid = sc.nextInt();

        if (fid <= flights.size() && fid > 0) {
            Flight currentFlight = flights.get(fid - 1);

            while (true) {
                System.out.println("1. Book Ticket 2. Cancel 3. Print 4. Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        bookTicket(currentFlight, user.getPassengerID());
                        break;
                    case 2:
                        cancelTicket(currentFlight);
                        break;
                    case 3:
                        printDetails(currentFlight, user.getPassengerID());
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Flight not found!");
        }
    }

    private static void bookTicket(Flight currentFlight, int passengerID) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of tickets");
        int numberOfTickets = sc.nextInt();

        if(numberOfTickets<=0)
        {
            System.out.println("Enter valid number of tickets");
            return;
        }

        if (numberOfTickets <= currentFlight.tickets) {
            String passengerDetail = "Passenger ID " + passengerID + " -- Number of Tickets Booked "
                    + numberOfTickets + " -- Total cost " + currentFlight.price * numberOfTickets;


            currentFlight.addPassengerDetails(passengerDetail, numberOfTickets, passengerID);          // Create a new PassengerDetails object for each booking

            currentFlight.flightSummary();       // Update the current ticket price after booking

            System.out.println("Booked successfully!");
        } else {
            System.out.println("Not Enough Tickets");
        }
    }

    private static void cancelTicket(Flight currentFlight) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter passenger ID to cancel booking");
        int cancelPassengerID = sc.nextInt();

        if (currentFlight.cancelTicket(cancelPassengerID)) {
            System.out.println("Booking canceled successfully!");
        } else {
            System.out.println("Passenger with ID " + cancelPassengerID + " not found in this flight.");
        }
    }


    private static void printDetails(Flight currentFlight, int passengerID) {
        boolean found = false;
        for (int i = currentFlight.passengerList.size() - 1; i >= 0; i--) {
            PassengerDetails passengerDetails = currentFlight.passengerList.get(i);
            if (passengerDetails.getPassengerID() == passengerID) {
                System.out.println("Flight ID: " + currentFlight.flightID);
                System.out.println("Remaining Tickets: " + currentFlight.tickets);
                System.out.println("Current Ticket Price: " + currentFlight.price);
                System.out.println(passengerDetails.details);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Passenger with ID " + passengerID + " not found in this flight.");
        }
    }


    private static void adminLogin(ArrayList<Flight> flights) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter admin username: ");
        String adminUsername = sc.next();
        System.out.println("Enter admin password: ");
        String adminPassword = sc.next();
        Admin admin = AdminManager.authenticateAdmin(adminUsername, adminPassword);

        if (admin != null) {
            System.out.println("Admin logged in successfully!");

            while (true) {
                System.out.println("1. Create new Flight 2. View Bookings 3. Exit");
                int adminChoice = sc.nextInt();

                switch (adminChoice) {
                    case 1:
                        createNewFlight(flights);
                        break;
                    case 2:
                        viewBookings(flights);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Invalid credentials");
        }
    }

    private static void createNewFlight(ArrayList<Flight> flights) {
        Flight newFlight = new Flight();
        flights.add(newFlight);
        System.out.println("New flight created successfully!");
    }

    private static void viewBookings(ArrayList<Flight> flights) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search Flight 2. View All Bookings 3. Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Enter Flight ID");
                int fid = sc.nextInt();

                if (fid <= flights.size() && fid > 0) {
                    Flight selectedFlight = flights.get(fid - 1);
                    selectedFlight.printDetails();
                } else {
                    System.out.println("Flight not found!");
                }
            } else if (choice == 2) {
                printAllBookings(flights);
            } else if (choice == 3) {
                return;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }



    private static void printAllBookings(ArrayList<Flight> flights) {
        for (Flight f : flights) {
            f.printDetails();           // print the flight summary along with their passenger details
            System.out.println();
        }
    }

}
