package production;
/**
 * @Author Louis Sze
 *
 * @brief Production record class that records products
 */
import java.util.Date;

public class ProductionRecord {
  // fields for production class
  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  /**
   * constructor with one parameter for production record
   *
   * @param productID returning product ID
   */
  ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /**
   * overload production Record with 4 parameters
   *
   * @param productionNumber the production number
   * @param productID Product Id
   * @param serialNumber Serial Number
   * @param dateProduced Date Produced
   */
  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  /**
   * Overload Constructor with 2 parameters
   *
   * @param product Products
   * @param counter Counter for id formatting
   */
  ProductionRecord(Product product, int counter) {
    String idNumber = String.format("%05d", counter);
    this.serialNumber =
        product.getManufacturer().substring(0, 3) + product.getType().getItemType() + idNumber;

    this.dateProduced = new Date();
  }

  /**
   * Setters and Getters
   *
   * @return productionNumber, productID, serialNumber, dateProduced
   */
  public int getProductionNumber() {
    return productionNumber;
  }

  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Date getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  /**
   * toString Method that converts data types to strings
   *
   * @return productionNumber, productId, serialNumber, dateProduced
   */
  @Override
  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + " Product ID: "
        + productID
        + " Serial Num: "
        + serialNumber
        + " Date: "
        + dateProduced;
  }
}
