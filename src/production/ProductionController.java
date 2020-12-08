package production;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller class that runs the database and gui. Creating private button linked to button * id
 * called addProduct Creating a private combobox linked to the id quantityBox
 *
 * @author Louis Sze
 */
public class ProductionController {

  @FXML private Button recordProd;
  @FXML private ComboBox<Integer> quantityBox;
  @FXML private ChoiceBox<ItemType> itemBox;
  @FXML private TextArea productionLog;
  @FXML private TextField userNameTf;
  @FXML private TextField passwordTf;
  @FXML private TextField txtProductName;
  @FXML private TextField txtManufacturer;
  @FXML private TableView<Product> tbExistingProd;
  @FXML private TableColumn<?, ?> tbcProdName;
  @FXML private TableColumn<?, ?> tbcManufacturer;
  @FXML private TableColumn<?, ?> tbcItemType;
  @FXML private ObservableList<Product> productLine = FXCollections.observableArrayList();
  @FXML private ListView<Product> lvChooseProduct;
  @FXML private TextArea accountTextArea;
  @FXML private Label error1;
  @FXML private Label error2;
  @FXML private Label productError;
  @FXML private Label manuError;
  @FXML private Label itemError;
  @FXML private Label selectError;

  /**
   * Initialize starts when program is running, calls comboBox, itemBox, initializeDB, popTableview,
   * loadProductList, Buttons.
   */
  public void initialize() {
    Database database = Database.initializeDB();
    loadComboBox();
    loadItemBox();
    loadProduct();
    loadProductList();
    loadProductLog();
    recordProd.setOnAction(this::recordProdButton);
  }

  private void correctUser() {
    error1.setText("");
  }

  private void correctPass() {
    error2.setText("");
  }

  private void invalidUser() {
    error1.setText("Invalid UserName *");
  }

  private void invalidPass() {
    error2.setText("Invalid Password *");
  }

  private void invalidProduct() {
    productError.setText("Product Name *");
  }

  private void invalidManufact() {
    manuError.setText("Manufacturer *");
  }

  private void invalidItem() {
    itemError.setText("Select Item *");
  }

  private void correctProduct() {
    productError.setText("");
  }

  private void correctManufact() {
    manuError.setText("");
  }

  private void correctItem() {
    itemError.setText("");
  }

  /** this method populating the choice box from ItemType enum. */
  private void loadItemBox() {
    itemBox.setItems(FXCollections.observableArrayList(ItemType.values()));
  }

  /** This method populates the comboBox. for loop used to iterate 1-10 for the quantityBox. */
  private void loadComboBox() {
    for (int n = 1; n < 11; n++) {
      quantityBox.getItems().add(n);
      quantityBox.setEditable(true);
      quantityBox.getSelectionModel().selectFirst();
    }
  }

  /**
   * this method adds the product inputs from the gui and inserts it to the database and arraylist
   * when the button is pressed.
   *
   * @param event action event listener for the button
   */
  @FXML
  private void addProdButton(ActionEvent event) {
    /* Creating a String variable to get text from the textfield */
    String typeText = txtProductName.getText();
    String manuText = txtManufacturer.getText();
    ItemType itemChoice = itemBox.getValue();

    /*SQL statement using prepared statement to set string from the textfield and insert it into the
     * database
     *
     * nested if statements to check for errors of empty fields
     */

    if (txtProductName.getText().equals("")
        && txtManufacturer.getText().equals("")
        && itemChoice == null) {
      invalidProduct();
      invalidManufact();
      invalidItem();
    } else if (txtProductName.getText().equals("") && txtManufacturer.getText().equals("")) {
      invalidProduct();
      invalidManufact();
      correctItem();
    } else {
      valCheck(typeText, manuText, itemChoice);
    }
  }

  private void valCheck(String typeText, String manuText, ItemType itemChoice) {
    if (txtManufacturer.getText().equals("") && itemChoice == null) {
      correctProduct();
      invalidManufact();
      invalidItem();
    } else if (txtProductName.getText().equals("") && itemChoice == null) {
      invalidProduct();
      correctManufact();
      invalidItem();
    } else {
      valCheck2(typeText, manuText, itemChoice);
    }
  }

  private void valCheck2(String typeText, String manuText, ItemType itemChoice) {
    if (txtProductName.getText().equals("")) {
      invalidProduct();
      correctManufact();
      correctItem();
    } else if (txtManufacturer.getText().equals("")) {
      correctProduct();
      invalidManufact();
      correctItem();
    } else if (itemChoice == null) {
      correctProduct();
      correctManufact();
      invalidItem();
    } else {
      try {
        correctProduct();
        correctManufact();
        correctItem();

        // inserting the data into the database
        String sql = "INSERT INTO Product(name, manufacturer, type) VALUES ( ?,?,?)";
        PreparedStatement addProd = Database.initializeDB().conn.prepareStatement(sql);
        addProd.setString(1, typeText);
        addProd.setString(2, manuText);
        addProd.setString(3, itemChoice.toString());
        addProd.executeUpdate();

        // adding products to observable array list and displaying into the table
        productLine.add(new Widget(typeText, manuText, itemChoice));
        System.out.println("Product Added");
        System.out.println(productLine);

        /* clear() resets the text field back to an empty field */
        txtManufacturer.clear();
        txtProductName.clear();
        itemBox.setValue(null);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
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

    if (lvChooseProduct.getSelectionModel().isEmpty()) {
      selectError.setText("Select Product *");
    } else if (lvChooseProduct.getSelectionModel().getSelectedItems() != null) {
      selectError.setText("");
      try {
        // pulls data from the list view
        selectError.setText("");
        Product productProduced = lvChooseProduct.getSelectionModel().getSelectedItem();
        int numProduced = 1;
        try {
          // converting a the value to string
          String numProducedString = String.valueOf(quantityBox.getValue());
          // converting it back to int
          numProduced = Integer.parseInt(numProducedString);
          // numProduced = quantityBox.getValue(); // this will come from the combobox in the UI
        } catch (java.lang.ClassCastException exception) {
          System.out.println("Error String");
        }
        int itemCount = 1;
        // sql statement that populates the production record table
        String sql =
            "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID,SERIAL_NUM,DATE_PRODUCED)VALUES(?,?,?)";
        PreparedStatement preparedStatement = Database.initializeDB().conn.prepareStatement(sql);
        System.out.println("Product Recorded");
        // Getting the Id from Product and implementing it to Production Record
        int cellID = lvChooseProduct.getSelectionModel().getSelectedIndex();
        Product idProduct = productLine.get(cellID);
        int newId = idProduct.getId();
        for (int prodProduce = 0; prodProduce < numProduced; prodProduce++) {
          ProductionRecord prodRec = new ProductionRecord(productProduced, itemCount++);
          // using the iterator as the product id for testing
          // System.out.println(prodRec.toString());
          // Converting java date and time stamp
          java.util.Date myDate = new java.util.Date(String.valueOf(prodRec.getDateProduced()));
          Timestamp currentDate = new java.sql.Timestamp(myDate.getTime());

          // preparedStatements that stores the data into the database
          preparedStatement.setInt(1, newId);
          preparedStatement.setString(2, prodRec.getSerialNumber());
          preparedStatement.setTimestamp(3, currentDate);
          preparedStatement.executeUpdate();
        }
        // repopulate ProductLog
        reloadProductLog();
      } catch (NullPointerException | SQLException e) {
        e.printStackTrace();
      }
    } else if (lvChooseProduct.getSelectionModel().isEmpty()) {
      selectError.setText("Select Product *");
    }
  }

  /**
   * creating admin user and password login information.
   *
   * @param event action when button is clicked
   */
  @FXML
  void createAccBtn(ActionEvent event) {
    // if loops to check if the text fields are empty
    if (passwordTf.getText().equals("") && userNameTf.getText().equals("")) {
      invalidUser();
      invalidPass();
    } else if (passwordTf.getText().equals("")) {
      correctUser();
      invalidPass();
    } else if (userNameTf.getText().equals("")) {
      invalidUser();
      correctPass();
    } else {
      correctUser();
      correctPass();
      String newUsername = userNameTf.getText();
      String newPassword = passwordTf.getText();
      Employee employee = new Employee(newUsername, newPassword);
      accountTextArea.setText(employee.toString());
      accountTextArea.setEditable(false);
      System.out.println(employee.toString());
      userNameTf.clear();
      passwordTf.clear();
    }
  }

  /** method for populating the table view columns. */
  private void loadProduct() {
    tbcProdName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tbcManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    tbcItemType.setCellValueFactory(new PropertyValueFactory<>("type"));
    tbExistingProd.setItems(productLine);
    // Query to select items from the data base and insert it into the observable arraylist that
    // populates the table.
    try {
      ResultSet rs =
          Database.initializeDB().conn.createStatement().executeQuery("SELECT * FROM Product");
      while (rs.next()) {
        productLine.add(
            new Widget(
                rs.getString(2), rs.getString(4), ItemType.valueOf(rs.getString(3)), rs.getInt(1)) {
              @Override
              public void setID(int id) {}

              @Override
              public int getID() {
                return 0;
              }
            });
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /** method for populating list view. */
  private void loadProductList() {
    lvChooseProduct.setItems(productLine);
  }

  /** Production Log load method that populates the production log. */
  private void loadProductLog() {
    try {
      Statement stmt = Database.initializeDB().conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTIONRECORD");
      // pulls Data from the database and populating it into the text area
      while (rs.next()) {
        ProductionRecord productionRecLog =
            new ProductionRecord(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4));
        // production Log population
        productionLog.appendText(productionRecLog.toString());
        productionLog.setEditable(false);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * reload productLog method clears the table and repopulate the table in the production log view.
   */
  private void reloadProductLog() {
    productionLog.clear();

    loadProductLog();
  }
}

/*
 * https://noblecodemonkeys.com/how-to-handle-javafx-button-click-events/
 * https://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm
 * https://docs.oracle.com/javafx/2/ui_controls/choice-box.htm
 * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 */
