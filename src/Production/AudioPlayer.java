package Production;
/** @Author Louis Sze */
public class AudioPlayer extends Product implements MultimediaControl {

  // fields for audio player that extends product and implements multimedia control
  String supportedAudioformats;

  String supportedPlaylistformats;

  // constructor for audio player
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
  // methods for audio player
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

  // to string method that converts the value to string and returns it as a string

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
        + "Supported Audio Formats: "
        + supportedAudioformats
        + "\n"
        + "Supported Playlist Formats: "
        + supportedPlaylistformats;
  }
}
