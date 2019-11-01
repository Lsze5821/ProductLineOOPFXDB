package Production;

public class Widget extends Product {
  Widget(String name, String manufacturer, ItemType iType) {
    super(name, manufacturer, iType);
  }

  @Override
  public int getID() {
    return 0;
  }
}
