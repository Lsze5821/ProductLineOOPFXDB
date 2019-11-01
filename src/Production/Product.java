package Production;

abstract class Product implements Item {

  int id;
  ItemType iType;
  String manufacturer;
  String name;

  Product(String name, String manufacturer, ItemType iType) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.iType = iType;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ItemType getiType() {
    return this.iType;
  }

  public void setiType(ItemType iType) {
    this.iType = iType;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: " + iType;
  }
}
