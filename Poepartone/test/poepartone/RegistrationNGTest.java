/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poepartone;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Student
 */
public class RegistrationNGTest {

    public RegistrationNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    
    public void setUpMethod() throws Exception {
    }

   
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of registerStatus method, of class Registration.
     * Valid username, valid password, valid cellphone -> true
     */
    @Test
    public void testRegisterStatus() {
        System.out.println("registerStatus");
        String userName = "kyl_1";
        String passWord = "Ch&&sec@ke99!";
        String cellPhone = "+27821234567";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.registerStatus(userName, passWord, cellPhone);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerStatus method, of class Registration.
     * Invalid username -> false
     */
    @Test
    public void testRegisterStatus_invalidUsername() {
        System.out.println("registerStatus - invalid username");
        String userName = "kyle!!!!!!!";
        String passWord = "Ch&&sec@ke99!";
        String cellPhone = "+27821234567";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.registerStatus(userName, passWord, cellPhone);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerStatus method, of class Registration.
     * Invalid password (no special character/digit/capital) -> false
     */
    @Test
    public void testRegisterStatus_invalidPassword() {
        System.out.println("registerStatus - invalid password");
        String userName = "kyl_1";
        String passWord = "password";
        String cellPhone = "+27821234567";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.registerStatus(userName, passWord, cellPhone);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerStatus method, of class Registration.
     * Invalid cellphone number (wrong format) -> false
     */
    @Test
    public void testRegisterStatus_invalidCellphone() {
        System.out.println("registerStatus - invalid cellphone");
        String userName = "kyl_1";
        String passWord = "Ch&&sec@ke99!";
        String cellPhone = "0821234567";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.registerStatus(userName, passWord, cellPhone);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method, of class Login.
     * Correct username and password -> true
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        Login instance = new Login();
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27821234567");

        String name = "kyl_1";
        String password = "Ch&&sec@ke99!";
        boolean expResult = true;
        boolean result = instance.loginUser(name, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method, of class Login.
     * Incorrect username or password -> false
     */
    @Test
    public void testLoginUser_invalid() {
        System.out.println("loginUser - invalid credentials");
        Login instance = new Login();
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27821234567");

        String name = "kyl_1";
        String password = "wrongPassword1!";
        boolean expResult = false;
        boolean result = instance.loginUser(name, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     * loginStatus true -> "Login Successful"
     */
    @Test
    public void testReturnLoginStatus_success() {
        System.out.println("returnLoginStatus - success");
        Login instance = new Login();
        boolean loginStatus = true;
        String expResult = "Login Successful";
        String result = instance.returnLoginStatus(loginStatus);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     * loginStatus false -> "Login Failed"
     */
    @Test
    public void testReturnLoginStatus_failure() {
        System.out.println("returnLoginStatus - failure");
        Login instance = new Login();
        boolean loginStatus = false;
        String expResult = "Login Failed";
        String result = instance.returnLoginStatus(loginStatus);
        assertEquals(expResult, result);
    }

}