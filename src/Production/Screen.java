package Production;
/** @Author Louis Sze */
public class Screen implements ScreenSpec {

  private String resolution;
  private int refreshrate;
  private int responsetime;

  public Screen(String resolution, int refreshrate, int responsetime) {
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshrate;
  }

  @Override
  public int getResponseTime() {
    return refreshrate;
  }

  /** @return */
  public String toString() {
    return "Screen:"
        + "\n"
        + "resolution: "
        + resolution
        + "\n"
        + "Refresh rate: "
        + refreshrate
        + "\n"
        + "Response time: "
        + responsetime;
  }
}
