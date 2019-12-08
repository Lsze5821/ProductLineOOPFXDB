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

  private void setUsername(String name) {

    Pattern firstNLast = Pattern.compile("\\s(.*)", Pattern.MULTILINE);
    Matcher firstNLastMatch = firstNLast.matcher(name);
    firstNLastMatch.find();
    String lastName = firstNLastMatch.group(1);
    String initials = name.substring(0, 1) + lastName;
    this.username = initials.toLowerCase();
  }

  private boolean checkName(String name) {
    Pattern checkPattern = Pattern.compile("\\s");
    Matcher patternMatch = checkPattern.matcher(name);
    boolean check = patternMatch.find();
    return check;
  }

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

  private boolean isValidPassword(String password) {

    final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$";
    final Pattern pattern = Pattern.compile(regex);
    final Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }

  public String toString() {
    return "Employee Details"
        + "\n"
        + "Name: "
        + name
        + "\n"
        + "Username: "
        + username
        + "\n"
        + "Email :"
        + email
        + "\n"
        + "Initial Password: "
        + password;
  }
}
