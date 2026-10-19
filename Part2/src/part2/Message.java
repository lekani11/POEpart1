/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Handles creating, validating, sending, disregarding and storing messages
 * for QuickChat (Part 2).
 *
 * @author Student
 */
public class Message {

    // Parallel lists: index i in every list describes the same message
    ArrayList<String> messageIds = new ArrayList<>();
    ArrayList<String> recipientNumber = new ArrayList<>();
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> msgHash = new ArrayList<>();
    ArrayList<String> messageStatus = new ArrayList<>(); // Sent / Disregarded / Stored

    private final Scanner input;           // shared with Part2 so System.in is only wrapped once
    private final Random random = new Random();

    private int numMessageSent = 0;        // messages actually sent
    private int messagesEntered = 0;       // messages entered so far (loop position)
    private int messageLimit = 0;          // how many messages the user said they want to enter

    public Message(Scanner input) {
        this.input = input;
    }

    Message() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setMessageLimit(int messageLimit) {
        this.messageLimit = messageLimit;
    }

    /**
     * Reads a whole number from the user, asking again until one is entered.
     */
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    /**
     * Lets the user enter the messages they said they wanted (for loop),
     * from wherever they left off, until the limit is reached.
     */
    public void sendMessages() {
        if (messagesEntered >= messageLimit) {
            System.out.println("You have already entered all " + messageLimit + " messages.");
            return;
        }

        for (int i = messagesEntered; i < messageLimit; i++) {
            System.out.println("\nMESSAGE " + (i + 1) + " of " + messageLimit);

            String messageID = generateMessageID();
            System.out.println(getMessageIDStatus(messageID));

            String recipient = askForRecipient();
            String messageText = askForMessage();
            String hash = createMessageHash(messageID, i + 1, messageText);

            String status = chooseAction();
            recordMessage(messageID, recipient, messageText, hash, status);
            messagesEntered++;

            if (status.equals("Sent")) {
                System.out.println("\n" + formatMessage(messages.size() - 1));
            } else if (status.equals("Stored")) {
                storeMessage();
            }
        }

        System.out.println("\nTotal number of messages sent: " + returnTotalMessages());
    }

    /** Keeps asking until the recipient number is valid. */
    private String askForRecipient() {
        while (true) {
            System.out.print("Enter recipient cell number (e.g. +27718693002): ");
            String recipient = input.nextLine().trim();
            String result = checkRecipientCell(recipient);
            System.out.println(result);
            if (result.equals("Cell phone number successfully captured.")) {
                return recipient;
            }
        }
    }

    /** Keeps asking until the message is between 1 and 250 characters. */
    private String askForMessage() {
        while (true) {
            System.out.print("Enter your message: ");
            String text = input.nextLine();
            if (text.trim().isEmpty()) {
                System.out.println("The message cannot be empty.");
                continue;
            }
            String result = checkMessageLength(text);
            System.out.println(result);
            if (result.equals("Message ready to send.")) {
                return text;
            }
        }
    }

    /** Asks what to do with the message until a valid decision is made. */
    private String chooseAction() {
        while (true) {
            System.out.println("\nWhat would you like to do with this message?");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");

            int choice = readInt("Enter your choice: ");
            System.out.println(SentMessage(choice));

            switch (choice) {
                case 1:
                    return "Sent";
                case 3:
                    return "Stored";
                case 2:
                    if (readInt("") == 0) {
                        System.out.println("Message deleted.");
                        return "Disregarded";
                    }   System.out.println("Message was not deleted.");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Adds a message to every list and counts it if it was sent.
     */
    public void recordMessage(String messageID, String recipient, String text,
            String hash, String status) {
        messageIds.add(messageID);
        recipientNumber.add(recipient);
        messages.add(text);
        msgHash.add(hash);
        messageStatus.add(status);

        if (status.equals("Sent")) {
            numMessageSent++;
        }
    }

    public int returnTotalMessages() {
        return numMessageSent;
    }

    /**
     * Generates a random ten-digit message ID (digits may start with 0).
     */
    public String generateMessageID() {
        String messageID;
        do {
            StringBuilder digits = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                digits.append(random.nextInt(10));
            }
            messageID = digits.toString();
        } while (messageIds.contains(messageID));
        return messageID;
    }

    public String getMessageIDStatus(String messageID) {
        return "Message ID generated: " + messageID;
    }

    /** A valid ID is digits only and no more than ten characters long. */
    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.matches("\\d{1,10}");
    }

    /** Recipient needs the +27 international code, same rule as Part 1. */
    public String checkRecipientCell(String cellphone) {
        if (cellphone.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an "
                + "international code. Please correct the number and try again.";
    }

    public String checkMessageLength(String messageText) {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }
        int excess = messageText.length() - 250;
        return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
    }

    /**
     * Hash = first two ID digits : message number : first word + last word,
     * punctuation removed, all caps (e.g. 00:0:HITONIGHT).
     */
    public String createMessageHash(String messageID, int messageNumber, String messageText) {
        String firstTwoNumbers = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");

        return (firstTwoNumbers + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    /**
     * Returns the response for the user's send / disregard / store choice.
     */
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    /** Details of one message in the required order. */
    private String formatMessage(int index) {
        return "Message ID: " + messageIds.get(index)
                + "\nMessage Hash: " + msgHash.get(index)
                + "\nRecipient: " + recipientNumber.get(index)
                + "\nMessage: " + messages.get(index);
    }

    /** Returns every message sent while the program has been running. */
    public String printMessages() {
        StringBuilder allMessages = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            if (messageStatus.get(i).equals("Sent")) {
                allMessages.append(formatMessage(i)).append("\n\n");
            }
        }
        return allMessages.toString();
    }

    /**
     * Writes every stored message to messages.json.
     *
     * Reference: add the source you actually used, in Harvard Anglia style,
     * e.g. // Author (year) Title. Available at: URL (Accessed: date).
     */
    public void storeMessage() {
        StringBuilder json = new StringBuilder("[\n");
        boolean firstEntry = true;

        for (int i = 0; i < messages.size(); i++) {
            if (!messageStatus.get(i).equals("Stored")) {
                continue;
            }
            if (!firstEntry) {
                json.append(",\n");
            }
            firstEntry = false;

            json.append("  {\n")
                    .append("    \"messageID\": \"").append(escapeJson(messageIds.get(i))).append("\",\n")
                    .append("    \"messageHash\": \"").append(escapeJson(msgHash.get(i))).append("\",\n")
                    .append("    \"recipient\": \"").append(escapeJson(recipientNumber.get(i))).append("\",\n")
                    .append("    \"message\": \"").append(escapeJson(messages.get(i))).append("\"\n")
                    .append("  }");
        }
        json.append("\n]");

        try (FileWriter writer = new FileWriter("messages.json")) {
            writer.write(json.toString());
        } catch (IOException e) {
            System.out.println("Could not write messages.json: " + e.getMessage());
        }
    }

    /** Escapes backslashes and quotes so the text is safe inside JSON. */
    private String escapeJson(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    void sendingMessages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void messasgeManagementMenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}