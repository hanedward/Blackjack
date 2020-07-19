/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private final String[] SUIT_STRINGS = {"Clubs", "Diamonds", "Hearts", "Spades"};

	private static Deck single_instance = null;	// Singleton
	
	List<Card> cards = new ArrayList<Card>(52);

	private int counter = 0;
	
	private Deck() {
		createDeck();
	}
	
	public static Deck getInstance() {
		if (single_instance == null)
			single_instance = new Deck();
		return single_instance;
	}
	
	// Helper method that constructs the file path to the images folder, for the corresponding file
	private String loadImageString(String suitString, int value) {
		String imagePath = "/images/";
		switch (value) {
			case 11:
				imagePath += "Jack_" + suitString + ".png";
				break;
			case 12:
				imagePath += "Queen_" + suitString + ".png";
				break;
			case 13:
				imagePath += "King_" + suitString + ".png";
				break;
			default:
				imagePath += Integer.toString(value) + "_" + suitString + ".png";
				break;
		}
		return imagePath;
	}

	public void createDeck() {
		
		for (int i=1; i<=13; i++) {
			Card clubs = new Card(Suit.CLUBS, i, loadImageString(SUIT_STRINGS[0], i));
			Card diamonds = new Card(Suit.DIAMONDS, i, loadImageString(SUIT_STRINGS[1], i));
			Card hearts = new Card(Suit.HEARTS, i, loadImageString(SUIT_STRINGS[2], i));
			Card spades = new Card(Suit.SPADES, i, loadImageString(SUIT_STRINGS[3], i));
			
			if (i > 10) {	// Face Cards all have value 10 (Jack, Queen, King)
				clubs.setValue(10);
				diamonds.setValue(10);
				hearts.setValue(10);
				spades.setValue(10);
			}
			
			cards.add(clubs);
			cards.add(diamonds);
			cards.add(hearts);
			cards.add(spades);
		}
		Collections.shuffle(cards);
	}
	
	public Card popDeck() {
		Card card = cards.remove(counter);
		return card;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void shuffleDeck() {
		Collections.shuffle(cards);
	}
}
