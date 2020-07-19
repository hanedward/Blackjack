/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package model;

import java.util.List;

public class Dealer implements Player {
	
	private int gold = 100;
	private List<Card> hand;
	
	public Dealer() {}
	
	public Dealer(List<Card> hand) {
		this.hand = hand;
	}

	@Override
	public int getGold() {
		// TODO Auto-generated method stub
		return gold;
	}

	@Override
	public void setGold(int gold) {
		// TODO Auto-generated method stub
		this.gold = gold;
	}

	@Override
	public List<Card> getHand() {
		// TODO Auto-generated method stub
		return hand;
	}

	@Override
	public void addToHand(Card card) {
		// TODO Auto-generated method stub
		hand.add(card);
	}

	@Override
	public int getHandValue() {
		// TODO Auto-generated method stub
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
