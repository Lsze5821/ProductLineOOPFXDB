package Production;
/** @Author Louis Sze */

// interface of item with getters and setters

public interface Item {
  int getID();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturer);

  String getManufacturer();
}
