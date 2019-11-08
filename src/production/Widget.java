package production;
/**
 * @Author Louis Sze
 *
 * @brief this is the widget class that extends product
 */
public class Widget extends Product {
  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }

  /** @return 0 */
  @Override
  public void setID(int id) {}

  @Override
  public int getID() {
    return 0;
  }
}
