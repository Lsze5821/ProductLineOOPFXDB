package production;
/**
 * @Author Louis Sze
 *
 * @brief Item Interface with getters and setters
 */
public interface Item {
  void setID(int id);

  int getID();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturer);

  String getManufacturer();
}
