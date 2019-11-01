package Production;
/** @Author Louis Sze */

// enums that contain the item type and the code for item types

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
