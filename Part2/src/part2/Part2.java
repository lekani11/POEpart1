package part2;

import java.util.Scanner;

/**
 * QuickChat entry point: register, log in, then use the message menu.
 *
 * @author Student
 */
public class Part2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Login login = new Login();

        // ===== Registration (Part 1) =====
        System.out.println("===== REGISTRATION =====");
        System.out.print("Enter your first name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scan.nextLine();

        String username;
        do {
            System.out.print("Enter username: ");
            username = scan.nextLine();
        } while (!reportUsername(login, username));

        String password;
        do {
            System.out.print("Enter password: ");
            password = scan.nextLine();
        } while (!reportPassword(login, password));

        String cellPhone;
        do {
            System.out.print("Enter cell phone number (e.g. +27838968976): ");
            cellPhone = scan.nextLine();
        } while (!reportCellPhone(login, cellPhone));

        System.out.println(login.registerUser(username, password, cellPhone));

        // ===== Login: nothing below runs until this succeeds =====
        boolean loggedIn;
        do {
            System.out.println("\n===== LOGIN =====");
            System.out.print("Enter username: ");
            String loginName = scan.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = scan.nextLine();

            loggedIn = login.loginUser(loginName, loginPassword);
            if (loggedIn) {
                System.out.println("Welcome " + firstName + ", " + lastName
                        + " it is great to see you again.");
            } else {
                System.out.println("Username or password incorrect, please try again.");
            }
        } while (!loggedIn);

        // ===== QuickChat =====
        Message msg = new Message(scan);
        System.out.println("\nWelcome to QuickChat.");

        int limit;
        do {
            limit = msg.readInt("How many messages do you wish to enter? ");
        } while (limit < 1);
        msg.setMessageLimit(limit);

        int option;
        do {
            System.out.println("\n===== Select an option =====");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");

            option = msg.readInt("Enter your option: ");

            switch (option) {
                case 1 -> msg.sendMessages();
                case 2 -> System.out.println("Coming Soon.");
                case 3 -> System.out.println("Goodbye and Thanks for using Quickchat.");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 3);
    }

    private static boolean reportUsername(Login login, String username) {
        boolean valid = login.checkUserName(username);
        System.out.println(valid ? "Username successfully captured."
                : "Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.");
        return valid;
    }

    private static boolean reportPassword(Login login, String password) {
        boolean valid = login.checkPasswordComplexity(password);
        System.out.println(valid ? "Password successfully captured."
                : "Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, "
                + "and a special character.");
        return valid;
    }

    private static boolean reportCellPhone(Login login, String cellPhone) {
        boolean valid = login.checkCellPhoneNumber(cellPhone);
        System.out.println(valid ? "Cell phone number successfully added."
                : "Cell phone number incorrectly formatted or does not contain "
                + "international code.");
        return valid;
    }
}