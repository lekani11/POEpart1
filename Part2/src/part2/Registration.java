/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part2;

/**
 *
 * @author Student
 */
public class Registration {
    String userName;
    String passWord;
    String cellPhone;

    public boolean checkUserName(String username) { //username method start
        return username.contains("_") && username.length() <= 5;
    } //end of method

    public boolean checkPasswordComplexity(String password) { //password method
        return password.length() >= 8
                && password.matches(".*[A-Z].*") // checks if password has a capital letter
                && password.matches(".*\\d.*") // checks if password has a digit
                && password.matches(".*[^a-zA-Z0-9].*"); // checks if password has a special character
    } // end method

    public boolean checkCellPhoneNumber(String cellphone) { // method start
        // Must start with +27 (SA international code) followed by exactly 9 digits.
        return cellphone.matches("^\\+27\\d{9}$");
    } //method end

    public String registerUser(String username, String password, String cellphone) {
        // IMPORTANT: check the values just typed in (the parameters),
        // not the class fields, since the fields haven't been set yet.
        if (checkUserName(username) && checkPasswordComplexity(password) && checkCellPhoneNumber(cellphone)) {
            this.userName = username;
            this.passWord = password;
            this.cellPhone = cellphone;
            return "Registration successful";
        } else {
            return "Registration unsuccessful";
        }
    }

    public boolean registerStatus(String username, String password, String cellphone) {
        return checkUserName(username) && checkPasswordComplexity(password) && checkCellPhoneNumber(cellphone);
    }
}
