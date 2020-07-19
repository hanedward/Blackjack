/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package view;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class EndView {

	private GameController controller;
	private Scene endScene;

	private VBox mainPane;
	private int mainPaneHeight = 500;
	private int mainPaneWidth = 800;

	private Label winnerLabel; // label to display winner
	private Button restartButton;

	public EndView(GameController controller) {
		this.controller = controller;
		mainPane = new VBox();
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setSpacing(30);
//		mainPane.setStyle("-fx-background-color: " + controller.getColorDark());
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");

		setupPane();
		setupEvents();
		endScene = new Scene(mainPane, mainPaneWidth, mainPaneHeight);
	}

	// set up pane components
	private void setupPane() {
		winnerLabel = new Label("NO WINNER RIGHT NOW!");
		winnerLabel.setTextAlignment(TextAlignment.CENTER);
		winnerLabel.setWrapText(true);
		winnerLabel.setFont(Font.font(controller.getFont(), 40));
		winnerLabel.setTextFill(Color.LIGHTCYAN);
		winnerLabel.setAlignment(Pos.CENTER);
		restartButton = new Button("Restart Game");
		controller.styleButton(restartButton);
		
		// Accessibility
		winnerLabel.setAccessibleText(winnerLabel.getText());
		winnerLabel.setFocusTraversable(true);
		restartButton.setAccessibleText(restartButton.getText());
		restartButton.setFocusTraversable(true);
		
		mainPane.getChildren().addAll(winnerLabel, restartButton);
	}
	
	// event handling inside scene
	public void setupEvents() {
		restartButton.setOnAction(e -> {
			controller.showStartScene();
			controller.resetGame();
		});
	}
	
	// set winner label text
	public void setWinnerLabelText(String winner) {
		winnerLabel.setText(winner);
	}
	
	public void resetTextLanguage() {
		String winnerText = winnerLabel.getText();
		if (winnerText.equalsIgnoreCase("Dealer is the Blackjack Champion!"))
			winnerLabel.setText(Messages.getString("EndView.0"));
		else
			winnerLabel.setText(Messages.getString("EndView.1"));
		
		if (Messages.getCurrentLanguage().equalsIgnoreCase("Japanese")) {
			winnerLabel.setStyle("-fx-font-size: 30");
		}
		else {
			winnerLabel.setStyle("-fx-font-size: 40;" +
								 "-fx-font-family: \"Times New Roman\";");
		}
		
		restartButton.setText(Messages.getString("EndView.2"));
	}
	
	// get scene object
	public Scene getScene() {
		return endScene;
	}
}
