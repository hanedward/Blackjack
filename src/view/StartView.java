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

public class StartView {

	private GameController controller;
	private Scene startScene;
	
	private VBox mainPane;
	private int mainPaneHeight = 500;
	private int mainPaneWidth = 800;

	private Label titleLabel;
	private Button startButton;
	
	public StartView(GameController controller) {
		this.controller = controller;
		
		mainPane = new VBox();
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setSpacing(30);
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");

//		mainPane.setStyle("-fx-background-color: " + controller.getColorDark()); //$NON-NLS-1$
		
		setupPane();
		setupEvents();
		startScene = new Scene(mainPane, mainPaneWidth, mainPaneHeight);
	}
	
	private void setupPane() {
		titleLabel = new Label(Messages.getString("StartView.1")); //$NON-NLS-1$
		titleLabel.setFont(Font.font(controller.getFont(), 100));
		titleLabel.setTextFill(Color.DEEPPINK);
		titleLabel.setAlignment(Pos.CENTER);
		startButton = new Button(Messages.getString("StartView.2")); //$NON-NLS-1$
		controller.styleButton(startButton);
		
		// Acessibility
		titleLabel.setAccessibleText(titleLabel.getText());
		titleLabel.setFocusTraversable(true);
		startButton.setAccessibleText(startButton.getText());
		startButton.setFocusTraversable(true);
		
		mainPane.getChildren().addAll(titleLabel, startButton);
	}
	
	public void setupEvents() {
		startButton.setOnAction(e -> {
			controller.showGameScene();
		});
	}
	
	public Scene getScene() {
		return startScene;
	}
	
	public void resetTextLanguage() {
//		currentLanguage = language;
//		System.out.println("StartView: " + currentLanguage);
//		Messages.changeLocalization(currentLanguage);
//		System.out.println(Messages.getCurrentLanguage());
		titleLabel.setText(Messages.getString("StartView.1"));
		startButton.setText(Messages.getString("StartView.2"));
		
		if (Messages.getCurrentLanguage().equalsIgnoreCase("Japanese")) {
			titleLabel.setStyle("-fx-font-size: 50px");
		}
	}
}
