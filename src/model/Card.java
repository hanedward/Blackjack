/*
 * Edward Han
 * ITP 368 (Ocean)
 */

package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
	
	private Suit suit;
	private int value;
	private Image frontImage;
	private Image backImage;
	private ImageView imageView;
	private String imageDesc;
	private String backImageDesc = "/images/Card_Back.png";
	
	public Card() {}
	
	public Card(Suit suit, int value, String imageDesc) {
		this.suit = suit;
		this.value = value;
		this.imageDesc = imageDesc;
		frontImage = new Image(imageDesc);
		backImage = new Image(backImageDesc);
		imageView = new ImageView(frontImage);
	}
	
	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getImageDesc() {
		return imageDesc;
	}
	
	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	public void showFrontSide() {
		imageView.setImage(frontImage);
	}
	
	public void showBackSide() {
		imageView.setImage(backImage);
	}
}
