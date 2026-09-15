/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepartone;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Poepartone {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login register = new Login();
        Scanner scan = new Scanner(System.in);

        System.out.println("\n===== Register =====");

        // ----- username -----
        System.out.println("Please enter the username: ");
        String name = scan.nextLine();

        while (!register.checkUserName(name)) {
            System.out.println("Username is not correctly formatted; please ensure that "
                    + "your username contains an underscore and is no more than five "
                    + "characters in length.");
            System.out.println("Please enter the username: ");
            name = scan.nextLine();
        }
        System.out.println("Username successfully captured.");

        // ----- password -----
        System.out.println("Enter password: ");
        String password = scan.nextLine();

        while (!register.checkPasswordComplexity(password)) {
            System.out.println("Password is not correctly formatted; please ensure that the "
                    + "password contains at least eight characters, a capital letter, "
                    + "a number, and a special character.");
            System.out.println("Enter password: ");
            password = scan.nextLine();
        }
        System.out.println("Password successfully captured.");

        // ----- cell number -----
        System.out.println("Enter Phone number (e.g. +27838968976): ");
        String cellphone = scan.nextLine();

        while (!register.checkCellPhoneNumber(cellphone)) {
            System.out.println("Cell phone number incorrectly formatted or does not "
                    + "contain international code.");
            System.out.println("Enter Phone number (e.g. +27838968976): ");
            cellphone = scan.nextLine();
        }
        System.out.println("Cell phone number successfully added.");

        // Now that all three fields are valid, actually register the user.
        String registrationMessage = register.registerUser(name, password, cellphone);
        System.out.println(registrationMessage);

        // ----- login -----
      if (registrationMessage.equals("Registration successful")) {
            System.out.println("\n===== Login =====");
            WhileLoop.runLogin(scan, register);
        }

        scan.close();
    }
}