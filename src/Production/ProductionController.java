package Production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;

/** @Author Louis Sze */

/**
 * Controller class that runs the database and gui. Creating private button linked to button id
 * called addProduct Creating a private combobox linked to the id quantityBox
 */
public class ProductionController {

  @FXML private Button addProduct;
  @FXML private ComboBox<Integer> quantityBox;
  @FXML private Button recordProd;
  @FXML private ChoiceBox<ItemType> itemBox;
  @FXML private TextArea productionLog;
  @FXML private TextField txt_prodName;
  @FXML private TextField txt_manufacturer;
  @FXML private TableView<Product> tb_existingProd;
  @FXML private TableColumn<?, ?> tbc_prodName;
  @FXML private TableColumn<?, ?> tbc_manufacturer;
  @FXML private TableColumn<?, ?> tbc_itemType;
  @FXML private ObservableList<Product> productLine = FXCollections.observableArrayList();
  private PreparedStatement preparedStatement;

  /** This class starts calls comboBox, initializeDB, and button Starts the program. */

  // @Override
  public void initialize() {

    comboBox();
    itemBox();
    initializeDB();
    testing();
    tableColumns();
    addProduct.setOnAction(this::handleButtonAction);
    recordProd.setOnAction(this::ProductionButtonAction);
  }

  /*populating the choice box from ItemType enum */
  private void itemBox() {
    itemBox.setItems(FXCollections.observableArrayList(ItemType.values()));
  }

  /*This is the comboBox. for loop used to iterate 1-10 for the quantitybox. */
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
   * This is the where the connecting to the h2 driver is connected. H2 driver URL and the Data base
   * URL connection. Try statement connecting to the h2 database. Exception state catches anything
   * that goes wrong in the try.
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
   * This is the Button actions when it is clicked. When the button is clicked runs the try
   * statement adding the hardcoded data into the database.
   *
   * @param event that runs the code for when the button is clicked.
   */
  @FXML
  private void ProductionButtonAction(ActionEvent event) {
    System.out.println("Product Recorded");
  }

  @FXML
  private void handleButtonAction(ActionEvent event) {
    /* Creating a String variable to get text from the textfield */
    String typeText = txt_prodName.getText();
    String manuText = txt_manufacturer.getText();
    ItemType itemChoice = itemBox.getValue();
    System.out.println("Product Added");

    /**
     * SQL statement using preparedstatement to set string from the textfield and insert it into the
     * database
     */
    try {
      // inserting the data into the database
      String sql = "INSERT INTO Product(name, manufacturer, type) VALUES ( ?,?,?)";
      preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setString(1, typeText);
      preparedStatement.setString(2, manuText);
      preparedStatement.setString(3, itemChoice.toString());
      preparedStatement.executeUpdate();

      // adding products to observable array list and displaying into the table
      productLine.add(new Widget(typeText, manuText, itemChoice));
      System.out.println(productLine);
      tb_existingProd.setItems(productLine);

      /* clear() resets the text field back to an empty field */
      txt_manufacturer.clear();
      txt_prodName.clear();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void tableColumns() {
    // Table Columns setting the values
    tbc_prodName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tbc_manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    tbc_itemType.setCellValueFactory(new PropertyValueFactory<>("type"));
  }

  private void testing() {
    // for loop that creates testing the production log
    Product productProduced = new Widget("iPod", "Apple", ItemType.AUDIO);
    int numProduced = 3; // this will come from the combobox in the UI
    int itemCount = 0;
    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);
      // using the iterator as the product id for testing
      System.out.println(pr.toString());
      ProductionRecord prodRec = new ProductionRecord(0);
      productionLog.setText(prodRec.toString());
    }

    ProductionRecord prodRec2 = new ProductionRecord(productProduced, itemCount++);
    productionLog.setText(prodRec2.toString());
  }
}

/*
 * https://noblecodemonkeys.com/how-to-handle-javafx-button-click-events/
 * https://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm
 * https://docs.oracle.com/javafx/2/ui_controls/choice-box.htm
 * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 */
