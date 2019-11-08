package production;
/**
 * @Author Louis Sze
 *
 * @brief Screen class that implements ScreenSpecs
 */
public class Screen implements ScreenSpec {
  // fields for screen class containing specs
  private String resolution;
  private int refreshRate;
  private int responseTime;

  /**
   * Constructor for screen passing 3 parameters
   *
   * @param resolution screen's resolution
   * @param refreshRate screen's refresh rates
   * @param responseTime screens response time
   */
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /**
   * Getters
   *
   * @return resolution, refresh rate, response time
   */
  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * toString method that converts the datatype and converting to a string when returned.
   *
   * @return resolution, refreshRate, responseTime
   */
  public String toString() {
    return "Screen:"
        + "\n"
        + "resolution: "
        + resolution
        + "\n"
        + "Refresh rate: "
        + refreshRate
        + "\n"
        + "Response time: "
        + responseTime;
  }
}
