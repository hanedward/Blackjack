/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package view;

import java.util.List;

import controller.GameController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Card;

public class GameView {
	
	private GameController controller;
	private Scene gameScene;
	
	private Pane mainPane;
	private int mainPaneHeight = 600;
	private int mainPaneWidth = 1200;
	
	private HBox topPane;
	private HBox bottomPane;
	private VBox rightPane;
	private HBox dealerPane;
	private HBox userPane;
	private VBox centerPane;
	
	private Button hitButton;
	private Button foldButton;
	private Button endTurnButton;
	private Button dealerButton;
	private Button newRoundButton;
	
	private Label dealerLabel;
	private Label dealerGoldLabel;
	private Label dealerGLabel;
	private Label userLabel;
	private Label userGoldLabel;
	private Label userGLabel;
	private Label roundLabel;
	
	private static boolean userTurnEnded = false;
	
	
	public GameView(GameController controller) {
		this.controller = controller;
		mainPane = new Pane();

//		mainPane.setStyle("-fx-background-color: " + controller.getColorLight()); //$NON-NLS-1$
		
		setupPanes();

		gameScene = new Scene(mainPane, mainPaneWidth, mainPaneHeight);
		setupEvents();
	}
	
	private void setupPanes() {
		rightPane = new VBox();
		topPane = new HBox();
		bottomPane = new HBox();
		dealerPane = new HBox();
		userPane = new HBox();
		centerPane = new VBox();
		setupTopPane();
		setupBottomPane();
		setupRightPane();
		setupDealerPane();
		setupUserPane();
		setupCenterPane();
	}
	
	private void setupCenterPane() {
		centerPane.setLayoutX(200);
		centerPane.setLayoutY(150);
		centerPane.setPrefSize(mainPaneWidth - 400, mainPaneHeight - 300);
//		centerPane.setStyle("-fx-background-color: Green"); //$NON-NLS-1$
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");

		
		roundLabel = new Label(Messages.getString("GameView.2")); //$NON-NLS-1$
		roundLabel.setTextFill(Color.WHITE);
		roundLabel.setStyle("-fx-font-family: \"Times New Roman\";" + //$NON-NLS-1$
				   "-fx-font-weight: Bold;" + //$NON-NLS-1$
				   "-fx-font-size: 50px;"); //$NON-NLS-1$
		roundLabel.setVisible(false);
		
		newRoundButton = new Button(Messages.getString("GameView.6")); //$NON-NLS-1$
		newRoundButton.setStyle("-fx-font-family: \"Times New Roman\";" + //$NON-NLS-1$
				   "-fx-font-weight: Bold;" + //$NON-NLS-1$
				   "-fx-font-size: 20px;"); //$NON-NLS-1$
		newRoundButton.setVisible(false);
		
		// Accessibility
		roundLabel.setAccessibleText(roundLabel.getText());
		roundLabel.setFocusTraversable(true);
		newRoundButton.setAccessibleText(newRoundButton.getText());
		newRoundButton.setFocusTraversable(true);
		
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setSpacing(30);
		centerPane.getChildren().addAll(roundLabel, newRoundButton);
		
		mainPane.getChildren().add(centerPane);
	}
	
	private void setupTopPane() {		
		topPane.setLayoutX(200);
		topPane.setPrefSize(mainPaneWidth - 400, 150);
//		topPane.setStyle("-fx-background-color: Green"); //$NON-NLS-1$
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");

		topPane.setAlignment(Pos.CENTER);
		topPane.setSpacing(50);
		
		List<Card> test1 = controller.getDealerHand();
		ImageView dealerCard1Image = test1.get(0).getImageView();
		Card dealerCard2 = test1.get(1);
		dealerCard2.showBackSide();
		ImageView dealerCard2Image = dealerCard2.getImageView();
		
		topPane.getChildren().addAll(dealerCard1Image, dealerCard2Image);
		
		mainPane.getChildren().add(topPane);
	}
	
	private void setupBottomPane() {
		bottomPane.setLayoutX(200);
		bottomPane.setLayoutY(mainPaneHeight - 150);
		bottomPane.setPrefSize(mainPaneWidth - 400, 150);
//		bottomPane.setStyle("-fx-background-color: Green"); //$NON-NLS-1$
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");

		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.setSpacing(50);
		
		List<Card> test2 = controller.getUserHand();
		ImageView userCard1Image = test2.get(0).getImageView();
		ImageView userCard2Image = test2.get(1).getImageView();
		
		bottomPane.getChildren().addAll(userCard1Image, userCard2Image);
		
		mainPane.getChildren().add(bottomPane);
	}
	
	private void setupRightPane() {
		rightPane.setLayoutX(mainPaneWidth - 200);
		rightPane.setPrefSize(200, 600);
//		rightPane.setStyle("-fx-background-color: Green"); //$NON-NLS-1$
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");

		
		hitButton = new Button(Messages.getString("GameView.13")); //$NON-NLS-1$
		hitButton.setPrefSize(130, 50);
		hitButton.setStyle("-fx-font-family: \"Times New Roman\";" + //$NON-NLS-1$
						   "-fx-font-weight: Bold;" + //$NON-NLS-1$
						   "-fx-font-size: 20px;"); //$NON-NLS-1$
		
		foldButton = new Button(Messages.getString("GameView.17")); //$NON-NLS-1$
		foldButton.setPrefSize(130, 50);
		foldButton.setStyle("-fx-font-family: \"Times New Roman\";" + //$NON-NLS-1$
				   "-fx-font-weight: Bold;" + //$NON-NLS-1$
				   "-fx-font-size: 20px;"); //$NON-NLS-1$
		
		dealerButton = new Button(Messages.getString("GameView.21")); //$NON-NLS-1$
		dealerButton.setPrefSize(130, 50);
		dealerButton.setStyle("-fx-font-family: \"Times New Roman\";" + //$NON-NLS-1$
				   "-fx-font-weight: Bold;" + //$NON-NLS-1$
				   "-fx-font-size: 20px;"); //$NON-NLS-1$
		
		endTurnButton = new Button(Messages.getString("GameView.25")); //$NON-NLS-1$
		endTurnButton.setPrefSize(130, 50);
		endTurnButton.setStyle("-fx-font-family: \"Times New Roman\";" + //$NON-NLS-1$
				   "-fx-font-weight: Bold;" + //$NON-NLS-1$
				   "-fx-font-size: 20px;"); //$NON-NLS-1$
		
		// Accessibility
		hitButton.setAccessibleText(hitButton.getText());
		hitButton.setFocusTraversable(true);
		foldButton.setAccessibleText(foldButton.getText());
		foldButton.setFocusTraversable(true);
		dealerButton.setAccessibleText(dealerButton.getText());
		dealerButton.setFocusTraversable(true);
		endTurnButton.setAccessibleText(endTurnButton.getText());
		endTurnButton.setFocusTraversable(true);
		
		rightPane.getChildren().addAll(hitButton, foldButton, dealerButton, endTurnButton);
		rightPane.setAlignment(Pos.CENTER);
		rightPane.setSpacing(50);
		
		mainPane.getChildren().add(rightPane);
	}
	
	private void setupDealerPane() {
		dealerPane.setPrefSize(200, 150);
//		dealerPane.setStyle("-fx-background-color: Green"); //$NON-NLS-1$
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");

		
		dealerLabel = new Label(Messages.getString("GameView.30")); //$NON-NLS-1$
		dealerLabel.setFont(new Font(29));
		dealerLabel.setPadding(new Insets(10, 10, 0, 15));
		dealerLabel.setTextFill(Color.WHITE);
		dealerLabel.setStyle("-fx-font-weight: bold;" + //$NON-NLS-1$
							 "-fx-font-family: \"Times New Roman\";"); //$NON-NLS-1$
		
		// Mongolian Baiti
		// Palatino Linotype
		
		dealerGoldLabel = new Label("100"); //$NON-NLS-1$
		dealerGoldLabel.setFont(new Font(29));
		dealerGoldLabel.setPadding(new Insets(10, 5, 0, 0));
		dealerGoldLabel.setTextFill(Color.WHITE);
		dealerGoldLabel.setStyle("-fx-font-weight: bold;" + //$NON-NLS-1$
								 "-fx-font-family: \"Times New Roman\";"); //$NON-NLS-1$
		
		dealerGLabel = new Label("G"); //$NON-NLS-1$
		dealerGLabel.setFont(new Font(29));
		dealerGLabel.setPadding(new Insets(10, 0, 0, 0));
		dealerGLabel.setTextFill(Color.WHITE);
		dealerGLabel.setStyle("-fx-font-weight: bold;" + //$NON-NLS-1$
							  "-fx-font-family: \"Times New Roman\";"); //$NON-NLS-1$
		
		// Accessibility
		dealerLabel.setAccessibleText(dealerLabel.getText());
		dealerLabel.setFocusTraversable(true);
		dealerGoldLabel.setAccessibleText(dealerGoldLabel.getText());
		dealerGoldLabel.setFocusTraversable(true);
		dealerGLabel.setAccessibleText(dealerGLabel.getText());
		dealerGLabel.setFocusTraversable(true);
		
//		List<String> fonts = Font.getFamilies();
//		for (String font : fonts) {
//			System.out.println(font);
//		}
//		
		dealerPane.getChildren().addAll(dealerLabel, dealerGoldLabel, dealerGLabel);		
		mainPane.getChildren().add(dealerPane);
	}
	
	private void setupUserPane() {
		userPane.setPrefSize(200, 150);
		userPane.setLayoutY(mainPaneHeight - 150);
//		userPane.setStyle("-fx-background-color: Green"); //$NON-NLS-1$
		mainPane.setStyle("-fx-background-color: radial-gradient(focus-distance 0% , center 50% 50% , radius 55% , #04d43c, #005417);");

		userPane.setAlignment(Pos.BOTTOM_LEFT);
		
		userLabel = new Label(Messages.getString("GameView.40")); //$NON-NLS-1$
		userLabel.setFont(new Font(29));
		userLabel.setPadding(new Insets(10, 10, 10, 15));
		userLabel.setTextFill(Color.WHITE);
		userLabel.setStyle("-fx-font-weight: bold;" + //$NON-NLS-1$
						   "-fx-font-family: \"Times New Roman\";"); //$NON-NLS-1$
		
		userGoldLabel = new Label("100"); //$NON-NLS-1$
		userGoldLabel.setFont(new Font(29));
		userGoldLabel.setPadding(new Insets(10, 5, 10, 0));
		userGoldLabel.setTextFill(Color.WHITE);
		userGoldLabel.setStyle("-fx-font-weight: bold;" + //$NON-NLS-1$
							   "-fx-font-family: \"Times New Roman\";"); //$NON-NLS-1$
		
		userGLabel = new Label("G"); //$NON-NLS-1$
		userGLabel.setFont(new Font(29));
		userGLabel.setPadding(new Insets(10, 0, 10, 0));
		userGLabel.setTextFill(Color.WHITE);
		userGLabel.setStyle("-fx-font-weight: bold;" + //$NON-NLS-1$
							"-fx-font-family: \"Times New Roman\";"); //$NON-NLS-1$
		
		// Accessibility
		userLabel.setAccessibleText(userLabel.getText());
		userLabel.setFocusTraversable(true);
		userGoldLabel.setAccessibleText(userGoldLabel.getText());
		userGoldLabel.setFocusTraversable(true);
		userGLabel.setAccessibleText(userGLabel.getText());
		userGLabel.setFocusTraversable(true);
		
		userPane.getChildren().addAll(userLabel, userGoldLabel, userGLabel);		
		mainPane.getChildren().add(userPane);
	}
	
	private void setupEvents() {		
		/**
		 * Only user can manually draw more cards; Deactivate hit button if user has ended their turn
		 */
		
		hitButton.setOnAction(e -> {
			if (!controller.allSideButtonsDisabled()) {
				if (!controller.userTurnEnded()) {
					Card dealCard = controller.dealCard();
					controller.addToUserHand(dealCard);
					ImageView dealCardView = dealCard.getImageView();
					bottomPane.getChildren().add(dealCardView);
					
					if (controller.isUserSumOver()) {
						// player loses; stop round
						controller.disableAllSideButtons();
						roundLabel.setText(Messages.getString("GameView.49")); //$NON-NLS-1$
						roundLabel.setVisible(true);
						newRoundButton.setVisible(true);
						newRoundButton.setText(Messages.getString("GameView.6"));
						System.out.println(Integer.toString(controller.getUserGold()));
						controller.updateDealerWin();
						dealerGoldLabel.setText(Integer.toString(controller.getDealerGold()));
						userGoldLabel.setText(Integer.toString(controller.getUserGold()));
					}
						
				}
			}
		});
		

		
		foldButton.setOnAction(e -> {
			roundLabel.setVisible(true);
			newRoundButton.setVisible(true);
			roundLabel.setText(Messages.getString("GameView.49"));
			controller.updateDealerWin();
			dealerGoldLabel.setText(Integer.toString(controller.getDealerGold()));
			userGoldLabel.setText(Integer.toString(controller.getUserGold()));
		});
		
		endTurnButton.setOnAction(e -> {
			if (!controller.allSideButtonsDisabled()) {
				controller.endUserTurn();	
			}
		});
		
		dealerButton.setOnAction(e -> {
			if (!controller.allSideButtonsDisabled()) {
				if (controller.userTurnEnded()) {
					List<Card> dealerHand = controller.getDealerHand();
					Card dealerCard2 = dealerHand.get(1);
					dealerCard2.showFrontSide();
					ImageView dealerCardImage2 = dealerCard2.getImageView();
					
					ObservableList<Node> dealerCards = topPane.getChildren();
					dealerCards.set(1, dealerCardImage2);
					
					if (!controller.dealerCardFlipped())
						controller.flipDealerCard();
					else {
						if (controller.userTurnEnded()) {
							Card dealCard = controller.dealCard();
							controller.addToDealerHand(dealCard);
							ImageView dealCardView = dealCard.getImageView();
							topPane.getChildren().add(dealCardView);
							
							if (controller.isDealerSumOver()) {
								// player loses; stop round
								controller.disableAllSideButtons();
								roundLabel.setText(Messages.getString("GameView.50")); //$NON-NLS-1$
								roundLabel.setVisible(true);
								newRoundButton.setVisible(true);
								newRoundButton.setText(Messages.getString("GameView.6"));
								controller.updateUserWin();
								dealerGoldLabel.setText(Integer.toString(controller.getDealerGold()));
								userGoldLabel.setText(Integer.toString(controller.getUserGold()));
							}
						}
					}	
				}
			}
		});
		
		newRoundButton.setOnAction(e -> {
			controller.resetAllSideButtons();
			roundLabel.setVisible(false);
			newRoundButton.setVisible(false);
			topPane.getChildren().clear();
			bottomPane.getChildren().clear();
			controller.resetHands();
			resetCardViews();
		});
	}
	
	private void resetCardViews() {
		// Reset the dealer's hand
		Card dealerCard1 = controller.dealCard();
		controller.addToDealerHand(dealerCard1);
		Card dealerCard2 = controller.dealCard();
		controller.addToDealerHand(dealerCard2);
		ImageView dealerCard1Image = dealerCard1.getImageView();
		dealerCard2.showBackSide();
		ImageView dealerCard2Image = dealerCard2.getImageView();
		
		topPane.getChildren().addAll(dealerCard1Image, dealerCard2Image);
		
		
		// Reset the user's hand
		List<Card> test2 = controller.getUserHand();
		Card userCard1 = controller.dealCard();
		controller.addToUserHand(userCard1);
		Card userCard2 = controller.dealCard();
		controller.addToUserHand(userCard2);
		ImageView userCard1Image = userCard1.getImageView();
		ImageView userCard2Image = userCard2.getImageView();
		
		bottomPane.getChildren().addAll(userCard1Image, userCard2Image);
	}

	public void resetTextLanguage() {
		roundLabel.setText(Messages.getString("GameView.2"));
		newRoundButton.setText(Messages.getString("GameView.6"));
		hitButton.setText(Messages.getString("GameView.13"));
		foldButton.setText(Messages.getString("GameView.17"));
		dealerButton.setText(Messages.getString("GameView.21"));
		endTurnButton.setText(Messages.getString("GameView.25"));
		dealerLabel.setText(Messages.getString("GameView.30"));
		userLabel.setText(Messages.getString("GameView.40"));
		
		// Different languages have different sizes; Modify size accordingly
		if (Messages.getCurrentLanguage().equalsIgnoreCase("Japanese")) {			
			dealerLabel.setStyle("-fx-font-size: 21px;" +
								 "-fx-font-weight: bold;" +
								 "-fx-font-family: \"Times New Roman\";");
			dealerGoldLabel.setStyle("-fx-font-size: 20px;" +
									 "-fx-font-weight: bold;" +
					 				 "-fx-font-family: \"Times New Roman\";");
			dealerGLabel.setStyle("-fx-font-size: 20px;" +
								  "-fx-font-weight: bold;" +
	 				 			  "-fx-font-family: \"Times New Roman\";");
			
			
			userLabel.setStyle("-fx-font-size: 21px;" +
					 			 "-fx-font-weight: bold;" +
					 			 "-fx-font-family: \"Times New Roman\";");
			userGoldLabel.setStyle("-fx-font-size: 20px;" +
						 			 "-fx-font-weight: bold;" +
		 				 			 "-fx-font-family: \"Times New Roman\";");
			userGLabel.setStyle("-fx-font-size: 20px;" +
					  			  "-fx-font-weight: bold;" +
		 			  			  "-fx-font-family: \"Times New Roman\";");
		}
	}
	
	public void resetGameView() {
		topPane.getChildren().clear();
		bottomPane.getChildren().clear();
		resetCardViews();
		dealerGoldLabel.setText("100");
		userGoldLabel.setText("100");
		roundLabel.setVisible(false);
		newRoundButton.setVisible(false);
	}
	
	public Scene getScene() {
		return gameScene;
	}
	
}
