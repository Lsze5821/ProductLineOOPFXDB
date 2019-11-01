package Production;

public class AudioPlayer extends Product implements MultimediaControl {

  /**
   * AudioPlayer class that extends product and implements MultimediaControl to.String method
   * converts abstract variables to string
   */
  String supportedAudioformats;

  String supportedPlaylistformats;

  AudioPlayer(
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.name = name;
    this.manufacturer = manufacturer;
    this.supportedAudioformats = supportedAudioFormats;
    this.supportedPlaylistformats = supportedPlaylistFormats;
  }

  @Override
  public int getID() {
    return 0;
  }

  public void stop() {
    System.out.println("Stopping");
  }

  public void play() {
    System.out.println("Playing");
  }

  public void next() {
    System.out.println("Next");
  }

  public void previous() {
    System.out.println("Previous");
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
        + "Supported Audio Formats: "
        + supportedAudioformats
        + "\n"
        + "Supported Playlist Formats: "
        + supportedPlaylistformats;
  }
}
