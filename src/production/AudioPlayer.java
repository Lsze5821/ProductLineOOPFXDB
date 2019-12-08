package production;
/**
 * This it he audio player class that extends products implementing multimedia control, contains
 * constructors and methods for multimedia controls.
 *
 * @author Louis Sze
 */

public class AudioPlayer extends Product implements MultimediaControl {

  // fields for audio player that extends product and implements multimedia control
  private String supportedAudioFormats;
  private String supportedPlaylistFormats;

  /**
   * AudioPlayer constructor passing 4 parameters.
   *
   * @param name name of product
   * @param manufacturer manufacturer of product
   * @param supportedAudioFormats format for supported audio
   * @param supportedPlaylistFormats format for playlists
   */
  AudioPlayer(
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.name = name;
    this.manufacturer = manufacturer;
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  @Override
  public void setID(int id) {

  }

  /**
   * Audio Player methods functionality stop, play, next, previous.
   *
   * @return ID
   */
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

  /**
   * to string method that converts the value to string.
   *
   * @return name , manufacturer, type, supportedAudioFormats, supportedPlaylistFormats
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
        + "Supported Audio Formats: "
        + supportedAudioFormats
        + "\n"
        + "Supported Playlist Formats: "
        + supportedPlaylistFormats;
  }
}
