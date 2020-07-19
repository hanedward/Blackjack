/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Card;
import model.Dealer;
import model.Deck;
import model.User;
import view.EndView;
import view.GameView;
import view.LoginView;
import view.NewUserView;
import view.StartView;

public class GameController extends Application {
	
	private Stage primaryStage;
	private LoginView loginView;
	private NewUserView newUserView;
	private StartView startView;
	private GameView gameView;
	private EndView endView;
	
	private Deck deck;
	private Dealer dealer;
	private User user;
	
	private boolean userTurnEnded = false;
	private boolean dealerCardFlipped = false;
	private boolean allSideButtonsDisabled = false;
	private boolean createAccount = false;
	
	private String fontName = "Times New Roman";
	private String colorDark = "Green";
	private String colorLight = "Green";
	private String defaultButtonColor = "Yellow";
	private String hoverButtonColor = "Yellow";
	
	
	public static void main(String[] args) {
		launch(args);
	}

	/* Set the initial stage to the start view */
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		deck = Deck.getInstance();
		initializePlayers();
		
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
		primaryStage.show();
		setupScenes();
		showLoginScene();
	}
	
	// Returns the current stage
	public Stage getStage(){
		return primaryStage;
	}
	
	// Sets up all scenes to be toggled
	public void setupScenes() {
		loginView = new LoginView(this);
		newUserView = new NewUserView(this);
		startView = new StartView(this);
		gameView = new GameView(this);
		endView = new EndView(this);
	}
	
	public void showLoginScene() {
		if (createAccount)
			loginView.createAccountLabel();
		primaryStage.setScene(loginView.getScene());
	}
	
	public void showNewUserScene() {
		newUserView.resetTextLanguage();
		primaryStage.setScene(newUserView.getScene());
	}
	
	// display the starting scene
	public void showStartScene(){
//		System.out.println("Controller: " + language);
		startView.resetTextLanguage();
		primaryStage.setScene(startView.getScene());
	}
	
	// display the game scene
	public void showGameScene() {
		gameView.resetTextLanguage();
		primaryStage.setScene(gameView.getScene());
//		gameView.startGame();
	}
	
	// display the end scene
	public void showEndScene(String winner){
		endView.setWinnerLabelText(winner); // takes in a string representing the name of the winner
		endView.resetTextLanguage();
		primaryStage.setScene(endView.getScene());
	}
	
	public String getFont() {
		return fontName;
	}
	
	// Provides styling for buttons in the views
	public void styleButton(Button button) {
		button.setFont(Font.font(getFont(), 20));
		button.setStyle("-fx-background-color: " + defaultButtonColor);
		button.setOnMouseEntered(me -> {
			button.setStyle("-fx-background-color: " + hoverButtonColor);
			button.setEffect(new DropShadow(50, Color.LIGHTGOLDENRODYELLOW)); // shadow around button
		});
		button.setOnMouseExited(me -> {
			button.setStyle("-fx-background-color: " + defaultButtonColor);
			button.setEffect(null);
		});
	}
	
	public String getColorDark() {
		return colorDark;
	}

	public String getColorLight() {
		return colorLight;
	}
	
	public void initializePlayers() {
		// Initialize User
		Card userCard1 = deck.popDeck();
		Card userCard2 = deck.popDeck();
		List<Card> userHand = new ArrayList<Card>();
		userHand.add(userCard1);
		userHand.add(userCard2);
		user = new User("Ed", userHand);
		
		// Initialize Dealer
		Card dealerCard1 = deck.popDeck();
		Card dealerCard2 = deck.popDeck();
		List<Card> dealerHand = new ArrayList<Card>();
		dealerHand.add(dealerCard1);
		dealerHand.add(dealerCard2);
		dealer = new Dealer(dealerHand);
	}
	
	public void addToUserHand(Card card) {
		user.addToHand(card);
	}
	
	public void addToDealerHand(Card card) {
		dealer.addToHand(card);
	}
	
	/* WIN CONDITIONS */
	
	public boolean isUserSumOver() {
		if (user.getHandValue() > 21)
			return true;
		return false;
	}
	
	public boolean isDealerSumOver() {
		if (dealer.getHandValue() > 21)
			return true;
		return false;
	}
	
	/*** HELPER METHODS ***/
	
	public Card dealCard() {
		if (deck.getCards().isEmpty()) {
			System.out.println("New Deck");
			deck.createDeck();	
		}
		Card card = deck.popDeck();
		return card;
	}
	
	public List<Card> getDealerHand() {
		return dealer.getHand();
	}
	
	public List<Card> getUserHand() {
		return user.getHand();
	}
	
	public void endUserTurn() {
		userTurnEnded = true;
	}
	
	public boolean userTurnEnded() {
		return userTurnEnded;
	}
	
	public void flipDealerCard() {
		dealerCardFlipped = true;
	}
	
	public boolean dealerCardFlipped() {
		return dealerCardFlipped;
	}
	
	public void disableAllSideButtons() {
		allSideButtonsDisabled = true;
	}
	
	public boolean allSideButtonsDisabled() {
		return allSideButtonsDisabled;
	}
	
	public void resetAllSideButtons() {
		userTurnEnded = false;
		dealerCardFlipped = false;
		allSideButtonsDisabled = false;
	}
	
	public void resetHands() {
		user.resetHand();
		dealer.resetHand();
	}
	
	public void updateDealerWin() {
		user.setGold(user.getGold() - 25);
		dealer.setGold(dealer.getGold() + 25);
		if (user.getGold() == 0)
			showEndScene("Dealer is the Blackjack Champion!");
	}
	
	public void updateUserWin() {
		user.setGold(user.getGold() + 25);
		dealer.setGold(dealer.getGold() - 25);
		if (dealer.getGold() == 0)
			showEndScene("You are the Blackjack Champion!");
	}
	
	public int getUserGold() {
		return user.getGold();
	}
	
	public int getDealerGold() {
		return dealer.getGold();
	}
	
	public void setCreateAccount(boolean account) {
		createAccount = account;
	}
	
	public boolean createdAccount() {
		return createAccount;
	}
	
	
	/*** RESET GAME ***/
	public void resetGame() {
		user.setGold(100);
		dealer.setGold(100);
		user.resetHand();
		dealer.resetHand();
		
		deck.getCards().clear();
		deck.createDeck();
		
		gameView.resetGameView();
		resetAllSideButtons();
	}
}
