package production;
/**
 * Movie Player class that extend products and implements multimedia controls.
 *
 * @author Louis Sze
 */

public class MoviePlayer extends Product implements MultimediaControl {

  // fields fore movie player extending product and implements multimedia control
  private Screen screen;
  private MonitorType monitorType;

  /**
   * Constructor passing 4 parameters for movie player.
   *
   * @param name product name
   * @param manufacturer product manufacturer
   * @param screen Screen Specs
   * @param monitorType Monitor type
   */
  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }
  // methods for movie player

  /** methods for movie player play, stop, previous, next. */
  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  @Override
  public void next() {
    System.out.println("Next movie");
  }
  // to string method that converts the value to string value and returns as a string value

  /**
   * a toString method that converts the data type and returns it as a string.
   *
   * @return name, manufacturer, type, screen, and monitor type
   */
  public String toString() {
    return "Name: "
        + name
        + "\n"
        + "Manufacturer: "
        + manufacturer
        + "\n"
        + "Type: "
        + type
        + " \n"
        + "Screen: "
        + screen
        + "\n"
        + "Monitor Type: "
        + monitorType;
  }

  @Override
  public void setID(int id) {

  }

  @Override
  public int getID() {
    return 0;
  }
}
