package production;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Database {
  protected static Connection conn;
  protected static Statement stmt;
  /**
   * This is the where the connecting to the h2 driver is connected. H2 driver URL and the Data base
   * URL connection. Try statement connecting to the h2 database. Exception state catches anything
   * that goes wrong in the try.
   * @return
   */
  protected static Database initializeDB() {
    final String h2_Driver = "org.h2.Driver";
    final String dB_Url = "jdbc:h2:./res/DB";
    final String user = "";
    String pass;

    try {
      Class.forName(h2_Driver);
      Properties prop = new Properties();
      prop.load(new FileInputStream("res/properties.properties"));
      pass = reverseString(prop.getProperty("password"));
      System.out.println("Connecting");
      // Opening a Connection
      conn = DriverManager.getConnection(dB_Url, user, pass);
      System.out.println("Connection Successful");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * a recursion method to reverse the password for extra security.
   *
   * @param password passing the password into the function
   * @return returns the password after the recursion method
   */
  private static String reverseString(String password) {
    //if statement that if the string id is empty return id ends the loop

    if (password.isEmpty()) {
      return password;
    }
    // reverseString reverses the string with the substring of 1 which reverses each char .
    return reverseString(password.substring(1)) + password.charAt(0);

  }
}
