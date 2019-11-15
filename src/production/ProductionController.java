package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @Author Louis Sze
 *
 * @brief Controller class that runs the database and gui. Creating private button linked to button
 *     id called addProduct Creating a private combobox linked to the id quantityBox
 */
public class ProductionController {

  @FXML private Button addProduct;
  @FXML private ComboBox<Integer> quantityBox;
  @FXML private Button recordProd;
  @FXML private ChoiceBox<ItemType> itemBox;
  @FXML private TextArea productionLog;
  @FXML private TextField txtProductName;
  @FXML private TextField txtManufacturer;
  @FXML private TableView<Product> tbExistingProd;
  @FXML private TableColumn<?, ?> tbcProdName;
  @FXML private TableColumn<?, ?> tbcManufacturer;
  @FXML private TableColumn<?, ?> tbcItemType;
  @FXML private ObservableList<Product> productLine = FXCollections.observableArrayList();
  @FXML private ListView<Product> lvChooseProduct;

  /**
   * Initialize starts when program is running, calls comboBox, itemBox, initializeDB, popTableview,
   * popListView, Buttons.
   */

  // @Override
  public void initialize() {

    comboBox();
    itemBox();
    initializeDB();
    popTableView();
    popListView();
    addProduct.setOnAction(this::addProdButton);
    recordProd.setOnAction(this::recordProdButton);
  }

  /** this method populating the choice box from ItemType enum */
  private void itemBox() {
    itemBox.setItems(FXCollections.observableArrayList(ItemType.values()));
  }

  /** This method populates the comboBox. for loop used to iterate 1-10 for the quantityBox. */
  private void comboBox() {
    for (int n = 1; n < 11; n++) {
      quantityBox.getItems().add(n);
      quantityBox.setEditable(true);
      quantityBox.getSelectionModel().selectFirst();
    }
  }

  private Connection conn;

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
      Statement stmt = conn.createStatement();
      // String sql =
      //  "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', 'Apple', 'iPod' )";
      // stmt.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @brief this method adds the product inputs from the gui and inserts it to the database and
   *     arraylist whent he button is pressed.
   * @param event action event listener for the button
   */
  @FXML
  private void addProdButton(ActionEvent event) {
    /* Creating a String variable to get text from the textfield */
    String typeText = txtProductName.getText();
    String manuText = txtManufacturer.getText();
    ItemType itemChoice = itemBox.getValue();
    System.out.println("Product Added");
    /*SQL statement using prepared statement to set string from the textfield and insert it into the
     * database
     */
    try {
      // inserting the data into the database
      String sql = "INSERT INTO Product(name, manufacturer, type) VALUES ( ?,?,?)";
      PreparedStatement addProd = conn.prepareStatement(sql);
      addProd.setString(1, typeText);
      addProd.setString(2, manuText);
      addProd.setString(3, itemChoice.toString());
      addProd.executeUpdate();

      // adding products to observable array list and displaying into the table
      productLine.add(new Widget(typeText, manuText, itemChoice));
      System.out.println(productLine);

      /* clear() resets the text field back to an empty field */
      txtManufacturer.clear();
      txtProductName.clear();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  /**
   * This is the Button actions when it is clicked. When the button is clicked runs and stores the
   * data into the production record database.
   *
   * @param event that runs the code for when the button is clicked.
   */
  @FXML
  private void recordProdButton(ActionEvent event) {
    try {
      // pulls data from the list view
      Product productProduced = lvChooseProduct.getSelectionModel().getSelectedItem();
      int numProduced = 0;
      try {
        // converting a the value to string
        String numProducedString = String.valueOf(quantityBox.getValue());
        // converting it back to int
        numProduced = Integer.parseInt(numProducedString);
        // numProduced = quantityBox.getValue(); // this will come from the combobox in the UI
      } catch (java.lang.ClassCastException exeception) {
        System.out.println("Error String");
      }

      int itemCount = 0;
      // sql statement that populates the production record table
      String sql =
          "INSERT INTO PRODUCTIONRECORD(PRODUCTION_NUM,PRODUCT_ID,SERIAL_NUM,DATE_PRODUCED)VALUES(?,?,?,?)";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      // for loop that iterates by the amount of product produced
      for (int productionRunProduct = 0;
          productionRunProduct < numProduced;
          productionRunProduct++) {
        ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);
        // using the iterator as the product id for testing
        System.out.println(pr.toString());
        System.out.println("Product Recorded");
        productionLog.appendText(pr.toString());
        productionLog.appendText("\n");
        // Converting java date and sql date
        java.util.Date myDate = new java.util.Date(String.valueOf(pr.getDateProduced()));
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        // preparedStatements that stores the data into the database
        preparedStatement.setInt(1, pr.getProductionNumber());
        preparedStatement.setInt(2, pr.getProductID());
        preparedStatement.setString(3, pr.getSerialNumber());
        preparedStatement.setDate(4, sqlDate);
        preparedStatement.executeUpdate();
      }
    } catch (NullPointerException | SQLException e) {
      e.printStackTrace();
    }
  }

  /** @brief method for populating the table view columns */
  private void popTableView() {
    tbcProdName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tbcManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    tbcItemType.setCellValueFactory(new PropertyValueFactory<>("type"));
    tbExistingProd.setItems(productLine);
  }
  /** @brief method for populating list view */
  private void popListView() {
    lvChooseProduct.setItems(productLine);
  }
}

/*
 * https://noblecodemonkeys.com/how-to-handle-javafx-button-click-events/
 * https://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm
 * https://docs.oracle.com/javafx/2/ui_controls/choice-box.htm
 * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 */
