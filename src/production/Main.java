package production;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class that starts the fxml loader and instances
 *
 * @author Louis Sze
 *
 */
public class Main extends Application {

  /**
   * @param primaryStage The primary stage.
   * @throws Exception Any problem with the code.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Production.fxml"));
    primaryStage.setTitle("Production Table");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  /** @param args Stores array of string which stores arguments passed by the command line */
  public static void main(String[] args) {
    launch(args);
  }
}
