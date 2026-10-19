/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package part2;

import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Message class, using the test data from the brief.
 * JUnit 4: assertEquals takes (expected, actual).
 *
 * @author Student
 */
public class MessageTest {

    // Test data from the brief
    private final String recipient1 = "+27718693002";
    private final String text1 = "Hi Mike, can you join us for dinner tonight?";
    private final String recipient2 = "08575975889";
    private final String text2 = "Hi Keegan, did you receive the payment?";

    private Message instance;

    @Before
    public void setUp() {
        instance = new Message(new Scanner(System.in));
    }

    @Test
    public void testMessageLengthSuccess() {
        assertEquals("Message ready to send.", instance.checkMessageLength(text1));
    }

    @Test
    public void testMessageLengthFailure() {
        String tooLong = "a".repeat(260); // 10 over the limit
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                instance.checkMessageLength(tooLong));
    }

    @Test
    public void testRecipientCellSuccess() {
        assertEquals("Cell phone number successfully captured.",
                instance.checkRecipientCell(recipient1));
    }

    @Test
    public void testRecipientCellFailure() {
        assertEquals("Cell phone number is incorrectly formatted or does not contain an "
                + "international code. Please correct the number and try again.",
                instance.checkRecipientCell(recipient2));
    }

    @Test
    public void testMessageHash() {
        assertEquals("00:1:HITONIGHT",
                instance.createMessageHash("0012345678", 1, text1));
    }

    @Test
    public void testMessageHashLoop() {
        String[] ids = {"0012345678", "0087654321"};
        String[] texts = {text1, text2};
        String[] expected = {"00:1:HITONIGHT", "00:2:HIPAYMENT"};

        for (int i = 0; i < texts.length; i++) {
            assertEquals(expected[i], instance.createMessageHash(ids[i], i + 1, texts[i]));
        }
    }

    @Test
    public void testMessageIDCreated() {
        String id = instance.generateMessageID();
        assertEquals("Message ID generated: " + id, instance.getMessageIDStatus(id));
        assertEquals(10, id.length());
        assertTrue(instance.checkMessageID(id));
    }

    @Test
    public void testCheckMessageIDTooLong() {
        assertFalse(instance.checkMessageID("12345678901"));
    }

    @Test
    public void testSentMessageSend() {
        assertEquals("Message successfully sent.", instance.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        assertEquals("Press 0 to delete the message.", instance.SentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        assertEquals("Message successfully stored.", instance.SentMessage(3));
    }

    @Test
    public void testReturnTotalMessages() {
        // Message 1 is sent, message 2 is disregarded -> total sent is 1
        instance.recordMessage("0012345678", recipient1, text1, "00:1:HITONIGHT", "Sent");
        instance.recordMessage("0087654321", recipient2, text2, "00:2:HIPAYMENT", "Disregarded");
        assertEquals(1, instance.returnTotalMessages());
    }

    @Test
    public void testPrintMessagesOnlyShowsSent() {
        instance.recordMessage("0012345678", recipient1, text1, "00:1:HITONIGHT", "Sent");
        instance.recordMessage("0087654321", recipient2, text2, "00:2:HIPAYMENT", "Disregarded");

        String printed = instance.printMessages();
        assertTrue(printed.contains(text1));
        assertFalse(printed.contains(text2));
    }
}