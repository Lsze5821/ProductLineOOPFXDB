package Production;

public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitortype;

  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitortype) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitortype = monitortype;
  }

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

  public String toString() {
    return "Name: "
        + name
        + "\n"
        + "Manufacturer: "
        + manufacturer
        + "\n"
        + "Type: "
        + iType
        + " \n"
        + "Screen: "
        + screen
        + "\n"
        + "Monitor Type: "
        + monitortype;
  }
}
