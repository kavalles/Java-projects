
// Assignment #: Assignment #6
//         Name: Katelyn Valles
//    StudentID: 1216331558
//      Lecture: CSE205 MWF 8:35-9:25AM
//  Description: CreatePane generates a pane where a user can enter
//  a movie information and create a list of available movies.

import java.util.ArrayList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import all other necessary javafx classes here
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent; //handles events
import javafx.event.EventHandler; //handles events
import javafx.scene.paint.Color;

public class CreatePane extends HBox {
	private ArrayList<Movie> movieList;
	private TextArea textArea;

	private Label movieTitle;
	private Label movieLength;
	private Label movieYear;
	private Label notificationLabel; // this label changes color to red

	private TextField title;
	private TextField length;
	private TextField year;
	private Button createMovieButton;

	// The relationship between CreatePane and ReviewPane is Aggregation
	private ReviewPane reviewPane;

	// constructor
	public CreatePane(ArrayList<Movie> list, ReviewPane rePane) {
		this.movieList = list;
		this.reviewPane = rePane;

		// Step #1: initialize each instance variable and set up the layout
		// ----
		// Labels
		this.movieTitle = new Label("Title");
		this.movieLength = new Label("Length");
		this.movieYear = new Label("Year");
		this.notificationLabel = new Label();
		notificationLabel.setTextFill(Color.RED);

		// Textfields
		int textFieldWidth = 120;
		this.title = new TextField();
		this.title.setMaxSize(textFieldWidth, 2.0);
		this.length = new TextField();
		this.length.setMaxSize(textFieldWidth, 2.0);
		this.year = new TextField();
		this.year.setMaxSize(textFieldWidth, 2.0);

		VBox leftPane = new VBox();

		// create a GridPane to hold those labels & text fields
		// consider using .setPadding() or setHgap(), setVgap()
		// to control the spacing and gap, etc.
		// ----
		GridPane gridPane = new GridPane();

		// Set up the layout for the left half of the CreatePane.
		// ----

		notificationLabel.setPadding(new Insets(8.0, 0.0, 0.0, 8.0));
		notificationLabel.setVisible(false);

		gridPane.setAlignment(Pos.TOP_CENTER);

		// movieAdded.setPadding(new Insets(8.0,0.0,0.0,0.0));

		gridPane.setPadding(new Insets(8.0, 12.5, 13.5, 44.5));
		gridPane.setHgap(5.5);
		gridPane.setVgap(5.5);

		// movie title label and textfield components
		gridPane.add(movieTitle, 0, 0);
		gridPane.add(title, 1, 0);

		// movie length label and textfield component
		gridPane.add(movieLength, 0, 1);
		gridPane.add(length, 1, 1);

		// movie year label and textfield component
		gridPane.add(movieYear, 0, 2);
		gridPane.add(year, 1, 2);

		// button component at col 1, row 3
		createMovieButton = new Button("Create a Movie");
		gridPane.add(createMovieButton, 1, 3);

		// the right half of the InputPane is simply a TextArea object
		// Note: a ScrollPane will be added to it automatically when there are no
		// enough space
		this.textArea = new TextArea("No Movie"); // when adding a new movie, contents will show in here

		// Add the left half and right half to the CreatePane
		// Note: CreatePane extends from HBox
		// ----
		leftPane.getChildren().addAll(notificationLabel, gridPane);

		this.getChildren().addAll(leftPane, textArea);

		// Step #3: register source object with event handler
		// ----
		createMovieButton.setOnAction(new ButtonHandler());

	} // end of constructor

	// Step 2: Create a ButtonHandler class
	// ButtonHandler listens to see if the button "Create a Movie" is pushed or not,
	// When the event occurs, it get a movie's Title, Year, and Length
	// information from the relevant text fields, then create a new movie and add it
	// inside
	// the movieList. Meanwhile it will display the movie's information inside the
	// text area.
	// It also does error checking in case any of the textfields are empty or
	// non-numeric string is typed

	private class ButtonHandler implements EventHandler<ActionEvent> {
		// Override the abstact method handle()
		public void handle(ActionEvent event) {
			// declare any necessary local variables here
			// ---
			Object source = event.getSource();

			// when a text field is empty and the button is pushed
			if (source == createMovieButton) {
				//String newTitle = title.getText().trim();

				String titleInput = title.getText().trim();
				String yearInput = year.getText().trim();
				String lengthInput = length.getText().trim();

				// error handling - if empty fields then show an error message
				if (titleInput.equals("") || yearInput.equals("") || lengthInput.equals("")) {
					notificationLabel.setText("Please fill all fields");
					notificationLabel.setVisible(true);
					return;
				}

				Movie movieObj = new Movie();
				// error handling - if there is incorrect data i.e.) length is eighty-eight
				try {
					movieObj.setMovieTitle(titleInput);
					movieObj.setYear(Integer.parseInt(yearInput));
					movieObj.setLength(Integer.parseInt(lengthInput));
				} catch (NumberFormatException e) {
					notificationLabel.setText("Incorrect data format");
					notificationLabel.setVisible(true);
					return;
				}

				// add this movie to this list
				movieList.add(movieObj);
				reviewPane.updateMovieList(movieObj);
				updateTextArea();
				notificationLabel.setText("Movie added");
				notificationLabel.setVisible(true);

				title.clear();
				year.clear();
				length.clear();

			}
			// ----
			// at the end, don't forget to update the new arrayList
			// information on the ListView of the ReviewPane
			// ----

			// Also somewhere you will need to use try & catch block to catch
			// the NumberFormatException
		} // end of handle() method

		//this method displays the movie list(s) inside of the createpane textarea
		private void updateTextArea() {
			textArea.clear();

			for (int i = 0; i < movieList.size(); i++) {
				Movie mov = movieList.get(i);

				textArea.appendText(mov.toString());

			}
		}
	} // end of ButtonHandler class
} // end of CreatePane class
