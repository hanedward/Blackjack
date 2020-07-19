/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package model;

import java.util.List;

public interface Player {
	
	public int getGold();
	public void setGold(int gold);
	public List<Card> getHand();
	public void addToHand(Card card);
	public int getHandValue();
	public void resetHand();
}
