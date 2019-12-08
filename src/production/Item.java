package production;
/**
 * Item Interface with getters and setters
 *
 * @author Louis Sze
 */
public interface Item {
  void setID(int id);

  int getID();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturer);

  String getManufacturer();
}
