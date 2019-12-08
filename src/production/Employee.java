package production;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Employee class that contains the name, user, password, and email
 *
 * @author Louis Sze
 */
public class Employee {

  private StringBuilder name;
  private String username;
  private String password;
  private String email = "user@oracleacademy.Test";

  /**
   * Employee constructor
   *
   * @param name accepting the password as a string for the constructor
   * @param password accepting the password as a string for the constructor
   */
  public Employee(String name, String password) {
    this.name = new StringBuilder(name);
    if (checkName(name)) {
      setUsername(name);
      setEmail(name);
    } else {
      username = "Admin";
    }

    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "password";
    }
  }

  /**
   * Setting username with first and last
   *
   * @param name Single parameter containing name
   */
  private void setUsername(String name) {

    Pattern firstNLast = Pattern.compile("\\s(.*)", Pattern.MULTILINE);
    Matcher firstNLastMatch = firstNLast.matcher(name);
    firstNLastMatch.find();
    String lastName = firstNLastMatch.group(1);
    String initials = name.substring(0, 1) + lastName;
    this.username = initials.toLowerCase();
  }

  /**
   * Boolean method to check name
   *
   * @param name single string parameter that checks the name
   * @return returns the checked name
   */
  private boolean checkName(String name) {
    Pattern checkPattern = Pattern.compile("\\s");
    Matcher patternMatch = checkPattern.matcher(name);
    boolean check = patternMatch.find();
    return check;
  }

  /**
   * Email method for checking, then used to create email.
   *
   * @param name single parameter contianing the string name.
   */
  private void setEmail(String name) {

    Pattern firstNameCheck = Pattern.compile("(.*)\\s", Pattern.MULTILINE);
    Matcher firstMatched = firstNameCheck.matcher(name);
    firstMatched.find();
    String firstName = firstMatched.group(1);

    Pattern lastNameCheck = Pattern.compile("\\s(.*)", Pattern.MULTILINE);
    Matcher lastMatched = lastNameCheck.matcher(name);
    lastMatched.find();
    String lastName = lastMatched.group(1);

    this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@oracleacademy.Test";
  }

  /**
   * Method for checking password
   *
   * @param password Contains a string password
   * @return the matched password
   */
  private boolean isValidPassword(String password) {

    final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$";
    final Pattern regexCD = Pattern.compile(regex);
    final Matcher matched = regexCD.matcher(password);
    return matched.matches();
  }

  /**
   * toString method that
   * @return the toString method containing a concatenating  string
   */
  public String toString() {
    return "Employee Details"
        + "\n"
        + "Name: "
        + name
        + "\n"
        + "Username: "
        + username
        + "\n"
        + "Email: "
        + email
        + "\n"
        + "Initial Password: "
        + password;
  }
}
