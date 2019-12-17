// Assignment #: Arizona State University CSE205
//         Name: Katelyn Valles	
//    StudentID: 1216331558
//      Lecture: MWF 8:35-9:25 AM
//  Description: The DrawingPane class creates a canvas where we can use
//               mouse key to draw lines with different colors and widths.
//               We can also use the the two buttons to erase the last
//				     drawn line or clear them all.


//import any classes necessary here
//----
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.awt.List;
import java.util.ArrayList;

public class DrawingPane extends BorderPane
{
   private Button undoButton, eraseButton;
   private ArrayList<Line> lineList;
   private Pane canvas;
   
   //declare any other necessary instance variables here
   //----
   private ComboBox<String> colorCombo;
   private ComboBox<String> widthCombo;
   private double x1 = 0.0,y1 = 0.0,x2 = 0.0,y2 = 0.0; 
   private Line line;
   private Color lineColor;
   private double lineWidth;
 
   //Constructor

   public DrawingPane()
   {
      //Step #1: initialize instance variable and set up layout
      undoButton = new Button("Undo");
      eraseButton = new Button("Erase");
      undoButton.setMinWidth(80.0);
      eraseButton.setMinWidth(80.0);
      
      //set standard values for combo boxes
      lineColor = Color.BLACK;
      lineWidth = 1.0;
   
      
      colorCombo = new ComboBox<String>();
      widthCombo = new ComboBox<String>();
      
      x1 = y1 = x2 = y2 = 0.0;
      line = null;

      //Create the color comboBox and width comboBox,
 
      colorCombo.getItems().addAll("Black", "Blue", "Red", "Yellow", "Green");
      colorCombo.setValue("Black");
       
      widthCombo.getItems().addAll("1","3","5","7");
      widthCombo.setValue("1");
      
       
      //initialize lineList, it is a data structure we used
      //to track the lines we created
      //----
      lineList = new ArrayList<Line>();
       
      //topPane should contain two combo boxes and two buttons
      HBox topPane = new HBox();
      topPane.setSpacing(40);
      topPane.setPadding(new Insets(10, 10, 10, 10));
      topPane.setStyle("-fx-border-color: black");
      
      topPane.getChildren().addAll(colorCombo,widthCombo, undoButton, eraseButton);
       
       //canvas is a Pane where we will draw lines
      canvas = new Pane();
      canvas.setStyle("-fx-background-color: white;");
       
      
       
      //add the canvas at the center of the pane and top panel
      //should have two combo boxes and two buttons
      this.setCenter(canvas);
      this.setTop(topPane);

     
       
      //Step #3: Register the source nodes with its handler objects
      canvas.setOnMousePressed(new MouseHandler());
      canvas.setOnMouseDragged(new MouseHandler() );
      canvas.setOnMouseReleased(new MouseHandler());
      
      colorCombo.setOnAction(new ColorHandler());
      widthCombo.setOnAction(new WidthHandler());
      undoButton.setOnAction(new ButtonHandler());
      eraseButton.setOnAction(new ButtonHandler());
      
   }

    //Step #2(A) - MouseHandler
    private class MouseHandler implements EventHandler<MouseEvent>
    {
        public void handle(MouseEvent event)
        {
            //handle MouseEvent here
            //Note: you can use if(event.getEventType()== MouseEvent.MOUSE_PRESSED)
            //to check whether the mouse key is pressed, dragged or released
            //write your own codes here
            //----
            
        	if(event.getEventType()== MouseEvent.MOUSE_PRESSED) 
        	{
        		x1 = event.getX();
        		y1 = event.getY();
        		line = new Line();
        		line.setStartX(x1);
        		line.setStartY(y1);
    
        	}
        	
        	else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) 
        	{
        		x2 = event.getX();
        		y2 = event.getY();
        		
        		line.setEndX(x2);
        		line.setEndY(y2);
        	}
        	
        	else if(event.getEventType() == MouseEvent.MOUSE_RELEASED) 
        	{
        		
        		line.setStroke(lineColor);
        		line.setStrokeWidth(lineWidth);
        		lineList.add(line);
        		
        		canvas.getChildren().clear();
        		canvas.getChildren().addAll(lineList);
        	}
            
            
            
        }//end handle()
    }//end MouseHandler

    //Step #2(B)- A handler class used to handle events from Undo & Erase buttons
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            //write your codes here
            //----
        	Object source = event.getSource();
        	if(source == undoButton) 
        	{
        		if(lineList.size() > 0) {
        		lineList.remove(lineList.size()-1);
        		
        		canvas.getChildren().clear();
        		canvas.getChildren().addAll(lineList);
        		}
        	}
        	else if(source == eraseButton)
        	{
        		lineList.clear();
        		canvas.getChildren().clear();
        	}
      
        }
    }//end ButtonHandler



   //Step #2(C)- A handler class used to handle colors
   private class ColorHandler implements EventHandler<ActionEvent>
   {
       public void handle(ActionEvent event)
       {
           //write your own codes here
           //----
    	// if the color selected is Blue, it draws a line in blue color
    	if(colorCombo.getValue().equals("Blue"))
    	{
    		lineColor = Color.BLUE;
    	}
    	else if(colorCombo.getValue().equals("Red"))
    	{
    		lineColor = Color.RED;
    	}
    	
    	else if(colorCombo.getValue().equals("Yellow")) 
    	{
    		lineColor = Color.YELLOW;
    	}
    	else if(colorCombo.getValue().equals("Green")) 
    	{
    		lineColor = Color.GREEN;
    	}
    	else if (colorCombo.getValue().equals("Black"))
    	{
    		lineColor = Color.BLACK;
    	}
    	
       }//end handle method
   }//end ColorHandler
    
    //Step #2(D)- A handler class used to handle widths of lines
    private class WidthHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            //write your own codes here
            //----
        	lineWidth = Double.parseDouble(widthCombo.getValue());
     
        }
    }//end WidthHandler
}//end class DrawingPane
