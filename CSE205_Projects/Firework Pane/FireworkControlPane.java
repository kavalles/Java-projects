// Assignment #: Arizona State University CSE205 #12
//         Name: Katelyn Valles
//    StudentID: 1216331558
//      Lecture: MWF 8:35am - 9:25am
//  Description: The FireworkControlPane class creates 3 buttons and 2 sliders to
//               to control the movement of Firework, and also contains a Pane
//               displaying Firework.

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.StackPane;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ColorPicker;

import javafx.scene.paint.Color;
import javafx.geometry.Orientation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class FireworkControlPane extends StackPane
 {
      //components of the Pane
      private FireworkPane fwPane;
      private Button start, stop;
      private Slider speed, beam;
      private Label label1, label2;
      private ColorPicker picker;

      private int width, height;
      private Color color;

      //Constructor to create all components, set their handler/listener,
      //and arrange them using layout panes.
      public FireworkControlPane(int width, int height, Color initialColor)
       {
           this.color = initialColor;
           this.width = width;
           this.height = height;

           //create a Pane displaying firework, with the specified color
           fwPane = new FireworkPane(initialColor, width);
           fwPane.setMinSize(width, height*(0.62));
           fwPane.setMaxSize(width, height*(0.62));
           //sets the background color of the firework pane to black
           fwPane.setStyle("-fx-background-color: black;");

           //create 2 buttons, start and stop.
           start = new Button("Start");
           stop = new Button("Stop");
           
           //create a color picker with the specified initial color
           picker = new ColorPicker(color);
           
           start.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
           stop.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
           picker.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

           //organize two buttons and the color picker in vertical
           TilePane buttons1 = new TilePane(Orientation.VERTICAL);
           buttons1.setPrefColumns(1);
           buttons1.getChildren().addAll(start, stop, picker);

           //create a slider for the spead/rate with major tick 10,
           //minor tick 1. The minimum value is 0, the maximum
           //is 50, and the initial set value is 20.
           speed = new Slider(0, 50, 20);
           speed.setMajorTickUnit(10);
           speed.setMinorTickCount(1);
           speed.setShowTickLabels(true);
           speed.setShowTickMarks(true);
           speed.setOrientation(Orientation.VERTICAL);

           //create a label for the speed
           label1 = new Label("Speed");

           BorderPane Pane3 = new BorderPane();
           Pane3.setTop(label1);
           Pane3.setCenter(speed);


           //create a slider for the number of beams/arcs for the firework with major tick spacing 4,
           //minor tick spacing 1. The minimum value is 4, the maximum
           //is 32, and the initial set value is 8.
           beam = new Slider(4, 32, 8);
           beam.setMajorTickUnit(4);
           beam.setMinorTickCount(1);
           beam.setShowTickLabels(true);
           beam.setShowTickMarks(true);
           beam.setOrientation(Orientation.VERTICAL);

           //create a label for the number of beams/arcs of the firework
           label2 = new Label("Beam Num");

           BorderPane Pane4 = new BorderPane();
           Pane4.setTop(label2);
           Pane4.setCenter(beam);

           TilePane Pane6 = new TilePane();
           Pane6.setPrefColumns(2);
           Pane6.getChildren().addAll(Pane3, Pane4);
           
           TilePane Pane5 = new TilePane();
           Pane5.setPrefColumns(2);
           Pane5.getChildren().addAll(buttons1, Pane6);

           BorderPane Pane7 = new BorderPane();
           Pane7.setTop(Pane5);
           Pane7.setCenter(fwPane);
        
           this.getChildren().add(Pane7);
           
           //Step #1: set an appropriate handler object to the buttons and the color picker
           /***to be completed***/
           //start, stop, picker
           
           start.setOnAction(new ButtonHandler());
           stop.setOnAction(new ButtonHandler());
           picker.setOnAction(new ColorHandler());
           
           //Step #2: set an appropriate handler object to the sliders
           /***to be completed***/
           
           speed.valueProperty().addListener(new SpeedHandler());
           beam.valueProperty().addListener(new BeamHandler());
           
}

  //ButtonHandler defines actions to be performed when each button
  //is pushed.
  private class ButtonHandler implements EventHandler<ActionEvent>
   {
       public void handle(ActionEvent event)
        {
            //Step #3 complete the method here
            /***to be completed***/
            if(event.getSource() == start) 
            {
            	fwPane.resume();
            	
            }
            else if(event.getSource() == stop)
            {
            	fwPane.suspend();
            }
            
         }
     } //end of ButtonHandler
     

     //ColorHandler defines actions to be performed when a color is chosen
     //using the color picker
     private class ColorHandler implements EventHandler<ActionEvent>
     {
         public void handle(ActionEvent event)
         {
             color = picker.getValue();
             fwPane.changeColor(color);
         }
     } //end of ColorHandler
     
     
   //SpeedListener adjusts the speed/rate of firework based on
   //the chosen integer in the corresponding slider.
   private class SpeedHandler implements ChangeListener<Number>
    {
         public void changed(ObservableValue<? extends Number> observable,
                             Number oldValue, Number newValue)
         {
             //Step #4 complete the method here
             /***to be completed***/
        	 
        	 //get the doubleValue of newValue parameter, and set it as the new rate for the timeline of the corresponding firework
        	 fwPane.setRate((int)newValue.doubleValue());
         }

     } //end of SpeedListener


     //BeamListener adjusts the number of beams/arcs of firework based on
     //the chosen integer in the corresponding slider.
     private class BeamHandler implements ChangeListener<Number>
      {
           public void changed(ObservableValue<? extends Number> observable,
                            Number oldValue, Number newValue)
           {
             //Step #5 complete the method here
             /***to be completed***/
        	   //get the doubleValue of newValue parameter, and set it as the new number of beams of the corresponding firework
        	   fwPane.setBeamNumber((int)newValue.doubleValue());
           }
     } //end of BeamListener
 }
