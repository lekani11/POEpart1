/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;

import java.util.Scanner;

/**
 *
 * @author Student
 *
 * Handles the login retry loop: keeps asking for username and password
 * until the details are correct, instead of the program just stopping
 * after one failed attempt.
 */
public class WhileLoop {

    public static void runLogin(Scanner scan, Login register) {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("\nEnter your username: ");
            String loginName = scan.nextLine();

            System.out.println("Enter your password: ");
            String loginPassword = scan.nextLine();

            loggedIn = register.loginUser(loginName, loginPassword);
            System.out.println(register.returnLoginStatus(loggedIn));

            if (!loggedIn) {
                System.out.println("Please try again.");
            }
        }
    }
}