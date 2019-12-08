package production;
/**
 * this is the widget class that extends product allows the program to access abstract * product
 * class and be used.
 *
 * @author Louis Sze
 */
public class Widget extends Product {
  Widget(String name, String manufacturer, ItemType type){
    super(name, manufacturer, type);
  }
  public Widget(String name, String manufacturer, ItemType type, Integer id) {
    super(id, name, manufacturer, type);
  }

  /** @param id a constructor that accepts single parameter int id */
  @Override
  public void setID(int id) {}

  @Override
  public int getID() {
    return 0;
  }
}
