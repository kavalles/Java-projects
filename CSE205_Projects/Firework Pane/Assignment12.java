// Assignment #: Arizona State University CSE205 #12
//         Name: Your Name
//    StudentID: Your ID
//      Lecture: Your lecture time (e.g., MWF 9:40am)
//  Description: The Assignment12 class creates a ControlPane object
//               on which we can control two panes that show fireworks.

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Assignment12 extends Application
{
   private final int WIDTH = 550;
   private final int HEIGHT = 400;

   public void start(Stage primaryStage)
   {
      //create a ControlPane object. See ControlPane.java for details.
      ControlPane pane = new ControlPane(WIDTH, HEIGHT);

      //put pane onto the rootPane
      StackPane rootPane = new StackPane();
      rootPane.getChildren().add(pane);

      // Create a scene and place rootPane in the stage
      Scene scene = new Scene(rootPane, WIDTH, HEIGHT);

      primaryStage.setTitle("Fireworks");
      primaryStage.setScene(scene); // Place the scene in the stage
      primaryStage.show(); // Display the stage
   }
   public static void main(String[] args)
   {
      Application.launch(args);
   }
}
