package production;
/**
 * @Author Louis Sze
 *
 * @brief enums containing item types and the code for item types.
 */
public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIOMOBILE("AM"),
  VISUALMOBILE("VM");
  String type;

  /**
   * creating a code for enums, returning the value back as type.
   *
   * @param value returning the value code
   */
  ItemType(String value) {
    type = value;
  }

  public String getItemType() {
    return type;
  }
}
