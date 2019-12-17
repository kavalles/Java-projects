// Assignment #: Arizona State University CSE205 #12
//         Name: Katelyn Valles
//    StudentID: 1216331558
//      Lecture: MWF 8:35-9:25 AM 
//  Description: FireworkPane is a subclass of Pane and it's used to define a pane 
				//where fireworks are moving


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

//Firework Pane class is a subclass of Pane
public class FireworkPane extends Pane
{
	private double centerX, centerY;
	private double radius;
	private double radiusLimit;
	private Color color;
	private int beamNum;
	private double step;
	private double angleSize;
	private Timeline timeline1;
	
	public FireworkPane(Color color, int width) 
	{
		this.color = color;

		centerX = width/2;
		centerY = width/2;
		step = 2.0;
		
		//initial radiusLimit
		radiusLimit = (width-10)/(4.0);
		//initial beamNum
		beamNum = 8;
		//initial angle size
		angleSize = 360/(beamNum*2);
		//initial radius
		radius = 25.0;
		
		for (int currentAngle=0; currentAngle <= 360; currentAngle += 2*angleSize)
		  {
		    Arc arc1 = new Arc(centerX, centerY, radius, radius, currentAngle, angleSize);
		    arc1.setFill(color);
		    arc1.setStroke(color);
		    arc1.setType(ArcType.ROUND);
		    this.getChildren().add(arc1);
		   }
		//Then timeline1 should be instantiated using an object of KeyFrame with Duration time being 500 mill sec, with an object of FireworkHandler class (FireworkHandler class will be defined later). 
		//Its cycle count should be indefinite, and its initial rate should be 20, and it should start ticking as soon as the pane is opened using the following code:
			
		//keyframe
		FireworkHandler aHandler = new FireworkHandler();
		
		KeyFrame kf = new KeyFrame(Duration.millis(500), aHandler);
		timeline1 = new Timeline(kf);
		
	
			timeline1.setCycleCount(Timeline.INDEFINITE);
		    timeline1.setRate(20);  //default rate = 20
		    timeline1.play();
		
	}
	
	public void resume() 
	{
		timeline1.play();
	}
	public void suspend() 
	{
		timeline1.pause();
	}
	public void changeColor(Color anotherColor) 
	{
		this.color = anotherColor;
	}
	public void setBeamNumber(int beam) 
	{
		this.beamNum = beam;
		this.angleSize = (360.0)/(beamNum*2);
	}
	
	public void setRate(int rate1) 
	{
		this.timeline1.setRate(rate1);
	}
	


	private class FireworkHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event) 
		{
			getChildren().clear();
			
			//handle method defines how a firework is drawn by changing its radius by adding "step" value to it
			 radius = radius+step;
			 
			 angleSize = 360/(beamNum*2);
			    for (int currentAngle=0; currentAngle <= 360; currentAngle += 2*angleSize)
			      {
			        Arc arc1 = new Arc(centerX, centerY, radius, radius, currentAngle, angleSize);
			        arc1.setFill(color);
			        arc1.setStroke(color);
			        arc1.setType(ArcType.ROUND);
			        getChildren().add(arc1);
			      }
			    
			    //if the value of radius becomes same or larger than radiusLimit, then the radius needs to be re-set to 0.
			    if(radius >= radiusLimit) 
			    {
			    	radius = 0;
			    }
			
		}
		
	} 
}//end of fireworkpane class

