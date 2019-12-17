
// Assignment #: Assignment #6
//         Name: Katelyn Valles
//    StudentID: 1216331558
//      Lecture: CSE205 MWF 8:35-9:25AM
//  Description: ReviewPane displays a list of available movies
//  from which a user can select to review.

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent; //**Need to import to handle event
import javafx.event.EventHandler; //**Need to import to handle event
import javafx.geometry.Insets;

import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

//import all other necessary javafx classes here
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class ReviewPane extends VBox {
	private ArrayList<Movie> movieList;

	// A ListView to display movies created
	private ListView<Movie> movieListView;

	// declare all other necessary GUI variables here
	private Label listViewHeading;
	private RadioButton poor, fair, avg, good, excellent;
	private Button submitReview;

	// constructor
	public ReviewPane(ArrayList<Movie> list) {

		// create an HBox to put variables into layout
		HBox radioBox = new HBox();
		radioBox.setSpacing(10.0);
		radioBox.setPadding(new Insets(0, 0, 0, 10.0));

		// initialize instance variables for radio buttons

		this.poor = new RadioButton("1 Poor");
		this.fair = new RadioButton("2 Fair");
		this.avg = new RadioButton("3 Average");
		this.good = new RadioButton("4 Good");
		this.excellent = new RadioButton("5 Excellent");
		this.submitReview = new Button("Submit Review");

		// create a list object
		this.movieList = list;
		this.listViewHeading = new Label("Choose a movie to give a review, and select a rating:");
		movieListView = new ListView<Movie>();

		// this is created so the radio buttons are mutually exclusive
		ToggleGroup radioGroup = new ToggleGroup();
		this.poor.setToggleGroup(radioGroup);
		this.fair.setToggleGroup(radioGroup);
		this.avg.setToggleGroup(radioGroup);
		this.good.setToggleGroup(radioGroup);
		this.excellent.setToggleGroup(radioGroup);

		radioBox.getChildren().addAll(this.poor, this.fair, this.avg, this.good, this.excellent);
		
		//Displays everything in the pane
		this.getChildren().addAll(this.listViewHeading, this.movieListView, radioBox, this.submitReview);

		submitReview.setOnAction(new RatingHandler());

		//initially sets the ratings at poor
		poor.setSelected(true);

	} // end of constructor

	// This method refreshes the ListView whenever there's a new movie added in
	// CreatePane
	// you will need to update the underline ObservableList object in order for
	// ListView
	// object to show the updated movie list

	public void updateMovieList(Movie newMovie) {
		movieListView.setItems(FXCollections.observableArrayList(movieList));
	}

	// Step 2: Create a RatingHandler class
	private class RatingHandler implements EventHandler<ActionEvent> {
		// Override the abstact method handle()
		public void handle(ActionEvent event) {
			// if nothing selected, select the first item
			if (movieListView.getSelectionModel().isEmpty()) {
				movieListView.getSelectionModel().select(0);
			}

			// When "Submit Review" button is pressed and a movie is selected from
			// the list view's average rating is updated by adding a additional
			// rating specified by a selected radio button

			Object source = event.getSource();

			if (source == submitReview) {
				Movie movieToBeReviewed = movieListView.getSelectionModel().getSelectedItem();

				int movieToBeReviewedIndex = movieList.indexOf(movieToBeReviewed);

				double rating = 0.0;
				// poor fair avg good excellent
				if (poor.isSelected()) {
					rating = 1.0;
				} else if (fair.isSelected()) {
					rating = 2.0;
				} else if (avg.isSelected()) {
					rating = 3.0;
				} else if (good.isSelected()) {
					rating = 4.0;
				} else if (excellent.isSelected()) {
					rating = 5.0;
				}
				movieToBeReviewed.getReview().updateRating(rating);

				movieList.set(movieToBeReviewedIndex, movieToBeReviewed);

				movieListView.setItems(FXCollections.observableArrayList(movieList));
				movieListView.refresh();

			}

		}
	} // end of RatingHandler
} // end of ReviewPane class
