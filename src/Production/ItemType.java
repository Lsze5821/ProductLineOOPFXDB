package Production;

public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIOMOBILE("AM"),
  VISUALMOBILE("VM");
  String type;

  ItemType(String value) {
    type = value;
  }

  public String getItemType() {
    return type;
  }
}
