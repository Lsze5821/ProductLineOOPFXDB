package Production;
/** @Author Louis Sze */
public class Widget extends Product {
  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }

  @Override
  public int getID() {
    return 0;
  }
}
