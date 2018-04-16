package Hearthstone.Hero;

import Hearthstone.Cards.Card;

public class Hand {

    private Card[] hand;
    private int size;
 //  private int index;

    public String viewHand(){
        String cards = "";
    	for (int i = 0; i< size; i++){
             cards = cards + i + "- " +this.hand[i].viewCard() + "\n";
        }
    	return cards;
    }
    
    public Card[] getCards(){
        return this.hand;
    }

    public void addCard(Card c){
        this.hand[size] = c;
        this.size++;
    }

    public void removeCard(int index) {
        int i;
    	for (i = index; i<this.size; i++){
            this.hand[i] = this.hand[i+1];
        }
        this.hand[i] = null;
        this.size--;
    }

    public int getSize() {
        return this.size;
    }

    public Hand(){
        this.hand = new Card[10];
    }
}
