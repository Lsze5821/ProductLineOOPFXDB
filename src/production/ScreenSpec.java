package production;
/**
 * Interface ScreenSpec containing gets resolution, refresh rate, and response time
 *
 * @author Louis Sze
 */
public interface ScreenSpec {
  public String getResolution();

  public int getRefreshRate();

  public int getResponseTime();
}
