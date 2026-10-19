/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Registration class, using the test data from the brief.
 * JUnit 4: assertEquals takes (expected, actual).
 *
 * @author Student
 */
public class RegistrationTest {

    // Valid and invalid test data from the brief
    private final String validUsername = "kyl_1";
    private final String invalidUsername = "kyle!!!!!!!";
    private final String validPassword = "Ch&&sec@ke99!";
    private final String invalidPassword = "password";
    private final String validCell = "+27838968976";
    private final String invalidCell = "08966553";

    private Registration instance;

    @Before
    public void setUp() {
        instance = new Registration();
    }

    // ===== checkUserName =====

    @Test
    public void testCheckUserNameValid() {
        assertTrue(instance.checkUserName(validUsername));
    }

    @Test
    public void testCheckUserNameInvalid() {
        assertFalse(instance.checkUserName(invalidUsername));
    }

    @Test
    public void testCheckUserNameNoUnderscore() {
        assertFalse(instance.checkUserName("kyle1"));
    }

    @Test
    public void testCheckUserNameTooLong() {
        assertFalse(instance.checkUserName("ky_le1")); // 6 characters
    }

    // ===== checkPasswordComplexity =====

    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(instance.checkPasswordComplexity(validPassword));
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        assertFalse(instance.checkPasswordComplexity(invalidPassword));
    }

    @Test
    public void testCheckPasswordTooShort() {
        assertFalse(instance.checkPasswordComplexity("Ch&1"));
    }

    @Test
    public void testCheckPasswordNoCapital() {
        assertFalse(instance.checkPasswordComplexity("ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordNoNumber() {
        assertFalse(instance.checkPasswordComplexity("Ch&&sec@ke!!"));
    }

    @Test
    public void testCheckPasswordNoSpecialCharacter() {
        assertFalse(instance.checkPasswordComplexity("Chasecake99"));
    }

    // ===== checkCellPhoneNumber =====

    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue(instance.checkCellPhoneNumber(validCell));
    }

    @Test
    public void testCheckCellPhoneNumberInvalid() {
        assertFalse(instance.checkCellPhoneNumber(invalidCell));
    }

    @Test
    public void testCheckCellPhoneNumberTooShort() {
        assertFalse(instance.checkCellPhoneNumber("+2783896897"));
    }

    @Test
    public void testCheckCellPhoneNumberTooLong() {
        assertFalse(instance.checkCellPhoneNumber("+278389689761"));
    }

    // ===== registerStatus =====

    @Test
    public void testRegisterStatusAllValid() {
        assertTrue(instance.registerStatus(validUsername, validPassword, validCell));
    }

    @Test
    public void testRegisterStatusInvalidUsername() {
        assertFalse(instance.registerStatus(invalidUsername, validPassword, validCell));
    }

    @Test
    public void testRegisterStatusInvalidPassword() {
        assertFalse(instance.registerStatus(validUsername, invalidPassword, validCell));
    }

    @Test
    public void testRegisterStatusInvalidCell() {
        assertFalse(instance.registerStatus(validUsername, validPassword, invalidCell));
    }

    // ===== registerUser =====

    @Test
    public void testRegisterUserSuccess() {
        assertEquals("Registration successful",
                instance.registerUser(validUsername, validPassword, validCell));
        // The details must now be stored so login can use them
        assertEquals(validUsername, instance.userName);
        assertEquals(validPassword, instance.passWord);
        assertEquals(validCell, instance.cellPhone);
    }

    @Test
    public void testRegisterUserFailure() {
        assertEquals("Registration unsuccessful",
                instance.registerUser(invalidUsername, validPassword, validCell));
        // Nothing should be stored when registration fails
        assertNull(instance.userName);
    }
}