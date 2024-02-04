import java.util.*;

class Admin {
    final String adminUsername;
    final String adminPassword;

    public Admin(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}

class AdminManager {
    final static HashSet<Admin> admins = new HashSet<>();          // Hash set is used to enforce uniqueness and take advantage of hash-based lookup efficiency

    public static void createAdmin(String adminUsername, String adminPassword) {
        admins.add(new Admin(adminUsername, adminPassword));
        System.out.println("Admin created successfully!");
    }

    public static Admin authenticateAdmin(String adminUsername, String adminPassword) {

        for (Admin admin : admins) {
            if (admin != null && Objects.equals(admin.getAdminUsername(), adminUsername) && admin.getAdminPassword().equals(adminPassword)) {
                return admin;
                                                  // Objects.equals method is used to compare two objects equal or not
            }
        }
        return null; // Authentication failed
    }
}