/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package model;

import java.util.List;

public class User implements Player {

	private String name;
	private int gold = 100;
	private List<Card> hand;
	
	public User() {}
	
	public User(String name, List<Card> hand) {
		this.name = name;
		this.hand = hand;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void addToHand(Card card) {
		hand.add(card);
	}
	
	public int getHandValue() {
		int handValue = 0;
		for (int i=0; i<hand.size(); i++) {
			handValue += hand.get(i).getValue();
		}
		return handValue;
	}
	
	public void resetHand() {
		hand.clear();
	}
}
