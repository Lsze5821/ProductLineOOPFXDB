package production;
/**
 * @Author Louis Sze
 *
 * @brief Interface ScreenSpec containing gets resolution, refresh rate, and response time
 */
public interface ScreenSpec {
  public String getResolution();

  public int getRefreshRate();

  public int getResponseTime();
}
