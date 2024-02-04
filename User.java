import java.util.*;

class User {
    final String username;
    final String password;
    final int passengerID;
    private static int passengerIDCounter = 1;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.passengerID = passengerIDCounter++;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPassengerID() {
        return passengerID;
    }
}

class UserManager {
    final static HashSet<User> users = new HashSet<>();                     // Hash set is used to enforce uniqueness and take advantage of hash-based lookup efficiency

    public static void addUser(String username, String password) {
        users.add(new User(username, password));                            // New Keyword is used to create a new instance of the User class. This is known as object instantiation. The new keyword is followed by the constructor of the User class, and it is responsible for creating a new object based on the blueprint defined by the User class.
        System.out.println("User created successfully!");
    }

    public static User authenticateUser(String username, String password) {

        for (User user : users) {
            if (user != null && Objects.equals(user.getUsername(), username) && user.getPassword().equals(password)) {
                return user;
                                                   // Objects.equals method is used to compare two objects equal or not
            }
        }
        return null; // Authentication failed
    }
}