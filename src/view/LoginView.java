/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.GameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class LoginView {
	
	private GameController controller;
	private Scene loginScene;
	
	private VBox mainPane;
	private int mainPaneHeight = 500;
	private int mainPaneWidth = 800;
	
	private VBox textFieldPane;
	private HBox loginPane;
	private HBox languagePane;

	private Label titleLabel;
	private TextField usernameField;
	private PasswordField passwordField;
	private Button loginButton;
	private Button newUserButton;
	private ComboBox<String> languageBox;
	private Button languageButton;
	private ObservableList<String> languages;
	private Label errorLabel;
	
	public LoginView(GameController controller) {
		this.controller = controller;
		
		mainPane = new VBox();
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setSpacing(30);
		
		RadialGradient grad = new RadialGradient(1.0,
				1.0,
				mainPane.getLayoutX(),
				mainPane.getLayoutY(),
				5.0,
				true,
				CycleMethod.NO_CYCLE,
				new Stop(0, Color.RED),
	            new Stop(1, Color.BLACK));
		
		final Circle ball = new Circle(mainPane.getLayoutX(), mainPane.getLayoutY(), 200);
		ball.setFill(grad);
		
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");
		
//		mainPane.setStyle("-fx-background-color: " + controller.getColorDark()); //$NON-NLS-1$
		
		setupTitleLabel();
		setupLanguagePane();
		setupTextFieldPane();
		setupLoginPane();
		setupErrorLabel();
		setupEvents();
		loginScene = new Scene(mainPane, mainPaneWidth, mainPaneHeight);
	}
	
	private void setupTitleLabel() {
		titleLabel = new Label(Messages.getString("LoginView.1")); //$NON-NLS-1$
		titleLabel.setFont(Font.font(controller.getFont(), 100));
//		titleLabel.setTextFill(new RadialGradient(0, .1, titleLabel.getLayoutX(), titleLabel.getLayoutY(), 55, true, CycleMethod.NO_CYCLE, new Stop(0, Color.RED), new Stop(1, Color.BROWN)));
		titleLabel.setTextFill(Color.DEEPPINK);
		titleLabel.setAlignment(Pos.CENTER);
		
		// Accessibility
		titleLabel.setAccessibleText(titleLabel.getText());
		titleLabel.setFocusTraversable(true);
		
		mainPane.getChildren().add(titleLabel);
	}
	
	private void setupLoginPane() {
		loginPane = new HBox();
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setSpacing(25);
		
		newUserButton = new Button(Messages.getString("LoginView.2")); //$NON-NLS-1$
		controller.styleButton(newUserButton);
		loginButton = new Button(Messages.getString("LoginView.3")); //$NON-NLS-1$
		controller.styleButton(loginButton);
		
		// Accessibility
		newUserButton.setAccessibleText(newUserButton.getText());
		newUserButton.setFocusTraversable(true);
		loginButton.setAccessibleText(loginButton.getText());
		loginButton.setFocusTraversable(true);
		
		
		loginPane.getChildren().addAll(loginButton, newUserButton);
		loginPane.setPadding(new Insets(20, 0, 0, 0));
		
		mainPane.getChildren().addAll(loginPane);
	}
	
	private void setupTextFieldPane() {
		textFieldPane = new VBox();
		textFieldPane.setAlignment(Pos.CENTER);
		textFieldPane.setFillWidth(false);
		textFieldPane.setLayoutX(100);
		textFieldPane.setSpacing(25);
		
		usernameField = new TextField();
		usernameField.setText(Messages.getString("LoginView.13")); //$NON-NLS-1$
		usernameField.setPrefWidth(215);
		usernameField.setFont(Font.font(controller.getFont(), 15));
		usernameField.setStyle("-fx-text-fill: Gray;"); //$NON-NLS-1$
		
		passwordField = new PasswordField();
		passwordField.setText(Messages.getString("LoginView.18")); //$NON-NLS-1$
		passwordField.setPrefWidth(215);
		passwordField.setFont(Font.font(controller.getFont(), 15));
		passwordField.setStyle("-fx-text-fill: Gray;"); //$NON-NLS-1$
		
		// Accessibility
		usernameField.setAccessibleText(usernameField.getText());
		usernameField.setFocusTraversable(true);
		passwordField.setAccessibleText(passwordField.getText());
		passwordField.setFocusTraversable(true);
		
		textFieldPane.getChildren().addAll(usernameField, passwordField);
		
		mainPane.getChildren().add(textFieldPane);
	}
	
	private void setupLanguagePane() {
		languages = FXCollections.observableArrayList("English", "Korean", "Japanese"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		languageBox = new ComboBox<String>(languages);
		languageBox.getSelectionModel().selectFirst();
		languageBox.setStyle("-fx-font-family: \"Times New Roman\";" + //$NON-NLS-1$
							 "-fx-font-size: 12.09px;"); //$NON-NLS-1$
		
		languageButton = new Button(Messages.getString("LoginView.0")); //$NON-NLS-1$
		languageButton.setFont(Font.font(controller.getFont(), 12.09));
		
		languagePane = new HBox();
		languagePane.setAlignment(Pos.CENTER);
		languagePane.setSpacing(20);
		languagePane.getChildren().addAll(languageBox, languageButton);
		
		// Accessibility
		languageBox.setAccessibleText(languageBox.getSelectionModel().getSelectedItem());
		languageBox.setFocusTraversable(true);
		languageButton.setAccessibleText(languageButton.getText());
		languageButton.setFocusTraversable(true);
		
		mainPane.getChildren().add(languagePane);
	}
	
	private void setupErrorLabel() {
		errorLabel = new Label(Messages.getString("LoginView.10")); //$NON-NLS-1$
		errorLabel.setFont(Font.font(controller.getFont(), 20));
		errorLabel.setAlignment(Pos.CENTER);
		errorLabel.setTextFill(Color.ORANGE);
		errorLabel.setStyle("-fx-font-weight: bold"); //$NON-NLS-1$
		errorLabel.setVisible(false);
		
		// Accessibility
		errorLabel.setAccessibleText(errorLabel.getText());
		errorLabel.setFocusTraversable(true);
		
		mainPane.getChildren().add(errorLabel);
	}
	
	public void setupEvents() {
		mainPane.setOnMouseClicked(e -> {
			String usernameText = Messages.getString("LoginView.13");
			if (usernameField.getText().trim().isEmpty() || usernameField.getText().equals(usernameText)) { //$NON-NLS-1$
				usernameField.setText(Messages.getString("LoginView.13")); //$NON-NLS-1$
				usernameField.setStyle("-fx-display-caret: false;" + //$NON-NLS-1$
								   "-fx-text-fill: gray;"); //$NON-NLS-1$
			}
			else {
				usernameField.setStyle("-fx-display-caret: false;"); //$NON-NLS-1$
			}
			
			String passwordText = Messages.getString("LoginView.18");
			if (passwordField.getText().trim().isEmpty() || passwordField.getText().equals(passwordText)) { //$NON-NLS-1$
				passwordField.setText(Messages.getString("LoginView.18")); //$NON-NLS-1$
				passwordField.setStyle("-fx-display-caret: false;" + //$NON-NLS-1$
								   "-fx-text-fill: gray;"); //$NON-NLS-1$
			}
			else {
				passwordField.setStyle("-fx-display-caret: false;"); //$NON-NLS-1$
			}
		});
		
		
		
		usernameField.setOnMouseClicked(e -> {
			String usernameText = Messages.getString("LoginView.13");
			if (usernameField.getText().equals(usernameText)) //$NON-NLS-1$
				usernameField.clear();
			usernameField.setStyle("-fx-text-fill: black"); //$NON-NLS-1$
			
			if (passwordField.getText().trim().isEmpty()) {
				passwordField.setText(Messages.getString("LoginView.18")); //$NON-NLS-1$
				passwordField.setStyle("-fx-text-fill: gray;"); //$NON-NLS-1$
			}
		});
		
		usernameField.setOnKeyPressed(ke -> {
			KeyCode code = ke.getCode();
			switch(code){
			case TAB:
				String passwordText = Messages.getString("LoginView.18");
				if (passwordField.getText().equals(passwordText)) //$NON-NLS-1$
					passwordField.clear();
				passwordField.setStyle("-fx-text-fill: black"); //$NON-NLS-1$
				
				if (usernameField.getText().trim().isEmpty()) {
					usernameField.setText(Messages.getString("LoginView.13")); //$NON-NLS-1$
					usernameField.setStyle("-fx-text-fill: gray;"); //$NON-NLS-1$
				}
				break;
			default:
//				System.out.println("Ignoring key press: " + code); //$NON-NLS-1$
			}
		});
		
		
		
		passwordField.setOnMouseClicked(e -> {
			String passwordText = Messages.getString("LoginView.18");
			if (passwordField.getText().equals(passwordText)) //$NON-NLS-1$
				passwordField.clear();
			passwordField.setStyle("-fx-text-fill: black"); //$NON-NLS-1$
			
			if (usernameField.getText().trim().isEmpty()) {
				usernameField.setText(Messages.getString("LoginView.13")); //$NON-NLS-1$
				usernameField.setStyle("-fx-text-fill: gray;"); //$NON-NLS-1$
			}
		});
		
		passwordField.setOnKeyPressed(ke -> {
			KeyCode code = ke.getCode();
			switch(code){
			case TAB:
				String usernameText = Messages.getString("LoginView.13");
				if (usernameField.getText().equals(usernameText)) //$NON-NLS-1$
					usernameField.clear();
				usernameField.setStyle("-fx-text-fill: black"); //$NON-NLS-1$
				
				if (passwordField.getText().trim().isEmpty()) {
					passwordField.setText(Messages.getString("LoginView.18")); //$NON-NLS-1$
					passwordField.setStyle("-fx-text-fill: gray;"); //$NON-NLS-1$
				}
				break;
			default:
//				System.out.println("Ignoring key press: " + code); //$NON-NLS-1$
			}
		});
		
		
		
		languageBox.setOnAction(e -> {
			String usernameText = Messages.getString("LoginView.13");
			if (usernameField.getText().trim().isEmpty() || usernameField.getText().equals(usernameText)) { //$NON-NLS-1$
				usernameField.setText(Messages.getString("LoginView.13")); //$NON-NLS-1$
				usernameField.setStyle("-fx-display-caret: false;" + //$NON-NLS-1$
								   "-fx-text-fill: gray;"); //$NON-NLS-1$
			}
			else {
				usernameField.setStyle("-fx-display-caret: false;"); //$NON-NLS-1$
			}
			
			String passwordText = Messages.getString("LoginView.18");
			if (passwordField.getText().trim().isEmpty() || passwordField.getText().equals(passwordText)) { //$NON-NLS-1$
				passwordField.setText(Messages.getString("LoginView.18")); //$NON-NLS-1$
				passwordField.setStyle("-fx-display-caret: false;" + //$NON-NLS-1$
								   "-fx-text-fill: gray;"); //$NON-NLS-1$
			}
			else {
				passwordField.setStyle("-fx-display-caret: false;"); //$NON-NLS-1$
			}
		});
		
		
		
		loginButton.setOnAction(e -> {
			if (isValidLogin()) {
				controller.showStartScene();
			}
			else
				errorLabel.setVisible(true);
		});
		
		
		
		newUserButton.setOnAction(e -> {
			controller.showNewUserScene();
		});
		
		
		languageButton.setOnAction(e -> {
			String newLanguage = languageBox.getSelectionModel().getSelectedItem().toString();
			Messages.changeLocalization(newLanguage);
			resetText();
		});
	}
	
	private boolean isValidLogin() {
		String usernameText = Messages.getString("LoginView.13");
		String passwordText = Messages.getString("LoginView.18");
		
		if (usernameField.getText().trim().isEmpty()) {
			usernameField.setText(usernameText);
			usernameField.setStyle("-fx-text-fill: gray;");
		}
		if (passwordField.getText().trim().isEmpty()) {
			passwordField.setText(passwordText);
			passwordField.setStyle("-fx-text-fill: gray;");
		}
		
		
		if (usernameField.getText().equalsIgnoreCase(usernameText) && //$NON-NLS-1$
			passwordField.getText().equalsIgnoreCase(passwordText)) { //$NON-NLS-1$
			errorLabel.setText(Messages.getString("LoginView.42")); //$NON-NLS-1$
			return false;
		}
		if (usernameField.getText().equalsIgnoreCase(usernameText)) { //$NON-NLS-1$
			errorLabel.setText(Messages.getString("LoginView.44")); //$NON-NLS-1$
			return false;
		}
		if (passwordField.getText().equalsIgnoreCase(passwordText)) { //$NON-NLS-1$
			errorLabel.setText(Messages.getString("LoginView.46")); //$NON-NLS-1$
			return false;
		}

		
		
		File file = new File("src/accounts.txt"); //$NON-NLS-1$
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			String line;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				String username = line.substring(0, line.indexOf(' '));
				String password = line.substring(line.indexOf(' ') + 1, line.length());
				
//				System.out.println(username + " " + password);
				if (usernameField.getText().trim().equalsIgnoreCase(username) &&
					passwordField.getText().trim().equalsIgnoreCase(password))
					return true;
			}
			errorLabel.setText(Messages.getString("LoginView.48")); //$NON-NLS-1$
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (scanner != null)
				scanner.close();
		}
		
		return true;
	}
	
	private void resetText() {
		titleLabel.setText(Messages.getString("LoginView.1")); //$NON-NLS-1$
		newUserButton.setText(Messages.getString("LoginView.2")); //$NON-NLS-1$
		loginButton.setText(Messages.getString("LoginView.3")); //$NON-NLS-1$
		languageButton.setText(Messages.getString("LoginView.0")); //$NON-NLS-1$
		usernameField.setText(Messages.getString("LoginView.13"));
		passwordField.setText(Messages.getString("LoginView.18"));
		errorLabel.setText("");
		
		usernameField.setStyle("-fx-text-fill: gray;");
		passwordField.setStyle("-fx-text-fill: gray;");
		
		if (Messages.getCurrentLanguage().equalsIgnoreCase("English")) { //$NON-NLS-1$
			titleLabel.setStyle("-fx-font-size: 100px;" + //$NON-NLS-1$
								"-fx-font-family: \"Times New Roman\";"); //$NON-NLS-1$
		}
		else if (Messages.getCurrentLanguage().equalsIgnoreCase("Japanese")) { //$NON-NLS-1$
			titleLabel.setStyle("-fx-font-size: 75px;"); //$NON-NLS-1$
		}
	}
	
	public void createAccountLabel() {
		resetText();
		errorLabel.setText(Messages.getString("NewUserView.3"));
		errorLabel.setVisible(true);
	}
	
	public Scene getScene() {
		return loginScene;
	}
}
