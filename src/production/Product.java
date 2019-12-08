package production;
/**
 * Abstract class product that implements items accessing the enums containing the type.
 *
 * @author Louis Sze
 */

abstract class Product implements Item {

  // fields for the Abstract Product class
  private int id;
  ItemType type;
  String manufacturer;
  String name;
  /* constructor for product initializing object of the class with defined value instead of using a
  null value*/

  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  Product(int id, String name, String manufacturer, ItemType type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }
  // setters and getters for the field and methods

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * To string method that returns variables as a string.
   *
   * @return name, manufacturer, type
   */
  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: " + type;
  }
}
