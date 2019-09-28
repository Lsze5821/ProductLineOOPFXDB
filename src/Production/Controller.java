package Production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
/**
 * Author Louis Sze
 */

/**
 *  Controller class that runs the database and gui.
 *  Creating private button linked to button id called addProduct
 *  Creating a private combobox linked to the id quantityBox
 */
public class Controller {

  @FXML private Button addProduct;
  @FXML private ComboBox<Integer> quantityBox = new ComboBox<>();
  /*
  @FXML
  private void handleButtonAction(ActionEvent event) {
    System.out.println("Product Added");
  }
  */
  /**
   *  This class starts calls comboBox, initializeDB, and button Starts the program.
   */

  // @Override
  public void initialize() {

    addProduct.setOnAction(this::handleButtonAction);
    comboBox();
    initializeDB();
  }

  /**
   * This is the comboBox.
   * for loop used to iterate 1-10 for the quantitybox.
   */
  private void comboBox() {
    for (int n = 1; n < 11; n++) {
      quantityBox.getItems().add(n);
      quantityBox.setEditable(true);
      quantityBox.getSelectionModel().selectFirst();
    }
  }

  private Connection conn;
  private Statement stmt;
  /**
   * This is the where the connecting to the h2 driver is connected.
   *  H2 driver URL and the Data base URL connection.
   *  Try statement connecting to the h2 database.
   *  Exception state catches anything that goes wrong in the try.
   */
  private void initializeDB() {
    final String H2_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/DB";
    // Connection conn;
    // Statement stmt;
    try {
      Class.forName(H2_DRIVER);
      System.out.println("Connecting");
      // Opening a Connection
      conn = DriverManager.getConnection(DB_URL);
      System.out.println("Connection Successful");
      // Executing Query
      stmt = conn.createStatement();
      // String sql =
      //  "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', 'Apple', 'iPod' )";
      // stmt.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This is the Button actions when it is clicked.
   * When the button is clicked runs the try statement adding the hardcoded data into the database.
   * @param event that runs the code for when the button is clicked.
   */
  @FXML
  private void handleButtonAction(ActionEvent event) {
    System.out.println("Product Added");
    try {
      String sql =
          "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', 'Apple', 'iPod' )";
      stmt.executeUpdate(sql);
      sql = "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', 'Orange', 'Ipad' )";
      stmt.executeUpdate(sql);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
/**
 * Adam Dressel helped me linked controller to .fxml file
 * https://noblecodemonkeys.com/how-to-handle-javafx-button-click-events/
 * https://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm
 */