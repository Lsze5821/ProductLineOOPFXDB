package Production;
/** @Author Louis Sze */
public class MoviePlayer extends Product implements MultimediaControl {

  // fields fore movie player extending product and implements multimedia control
  Screen screen;
  MonitorType monitortype;

  // constructor for movie player

  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitortype) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitortype = monitortype;
  }
  // methods for movie player

  @Override
  public int getID() {
    return 0;
  }

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
        + monitortype;
  }
}
