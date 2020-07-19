/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NewUserView {
	
	private GameController controller;
	private Scene loginScene;
	
	private VBox mainPane;
	private int mainPaneHeight = 500;
	private int mainPaneWidth = 800;
	
	private VBox textFieldPane;
	private HBox accountPane;

	private Label titleLabel;
	private TextField usernameField;
	private PasswordField passwordField;
	private Button createAccountButton;
	private Label errorLabel;
	
	public NewUserView(GameController controller) {
		this.controller = controller;
		
		mainPane = new VBox();
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setSpacing(30);
//		mainPane.setStyle("-fx-background-color: " + controller.getColorDark()); //$NON-NLS-1$
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");
		
		setupTitleLabel();
		setupTextFieldPane();
		setupAccountPane();
		setupErrorLabel();
		setupEvents();
		loginScene = new Scene(mainPane, mainPaneWidth, mainPaneHeight);
	}
	
	private void setupTitleLabel() {
		titleLabel = new Label(Messages.getString("NewUserView.1")); //$NON-NLS-1$
		titleLabel.setFont(Font.font(controller.getFont(), 75));
		titleLabel.setTextFill(Color.DEEPPINK);
		titleLabel.setAlignment(Pos.CENTER);
		
		// Acessibility
		titleLabel.setAccessibleText(titleLabel.getText());
		titleLabel.setFocusTraversable(true);
		
		mainPane.getChildren().add(titleLabel);
	}
	
	private void setupAccountPane() {
		accountPane = new HBox();
		accountPane.setAlignment(Pos.CENTER);
		accountPane.setSpacing(25);
		
		createAccountButton = new Button(Messages.getString("NewUserView.0")); //$NON-NLS-1$
		controller.styleButton(createAccountButton);
		
		// Accessibility
		createAccountButton.setAccessibleText(createAccountButton.getText());
		createAccountButton.setFocusTraversable(true);
		
		accountPane.getChildren().addAll(createAccountButton);
		accountPane.setPadding(new Insets(20, 0, 0, 0));
		
		mainPane.getChildren().addAll(accountPane);
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
		
		
		
		createAccountButton.setOnAction(e -> {
			if (canCreateAccount()) {
				controller.setCreateAccount(true);
				controller.showLoginScene();
			}
			else
				errorLabel.setVisible(true);
		});
	}
	
	private boolean canCreateAccount() {
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
					passwordField.getText().trim().equalsIgnoreCase(password)) {
					errorLabel.setText(Messages.getString("NewUserView.2")); //$NON-NLS-1$
					return false;
				}
			}
			try {
				FileWriter fw = new FileWriter(file.getPath(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				String newAccount = usernameField.getText().trim() + " " + passwordField.getText().trim();
				if (file.length() != 0)	// don't add new line if file is empty
					newAccount = "\n" + newAccount;
				bw.write(newAccount);
				bw.close();
				scanner.close();			
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		
		return true;
	}
	
	public void resetTextLanguage() {
		titleLabel.setText(Messages.getString("NewUserView.1"));
		usernameField.setText(Messages.getString("LoginView.13"));
		passwordField.setText(Messages.getString("LoginView.18"));
		createAccountButton.setText(Messages.getString("NewUserView.0"));
		errorLabel.setText(Messages.getString("LoginView.10"));
		errorLabel.setVisible(false);
		
		usernameField.setStyle("-fx-text-fill: gray;");
		passwordField.setStyle("-fx-text-fill: gray;");
		
		if (Messages.getCurrentLanguage().equalsIgnoreCase("English")) { //$NON-NLS-1$
			titleLabel.setStyle("-fx-font-size: 75px;" + //$NON-NLS-1$
								"-fx-font-family: \"Times New Roman\";"); //$NON-NLS-1$
		}
		else if (Messages.getCurrentLanguage().equalsIgnoreCase("Japanese")) { //$NON-NLS-1$
			titleLabel.setStyle("-fx-font-size: 55px;"); //$NON-NLS-1$
		}
	}
	
	public Scene getScene() {
		return loginScene;
	}
}
