package Hearthstone.Cards;

import Hearthstone.Hero.Player;

public abstract class Card{

    private String Name;
    private int ManaCost;
    private Player Owner, Oponent;

    public Card() {
        super();
    }

    public abstract void Play(Player owner, Player oponent);

    public String getName(){
        return this.Name;
    }

    public int getManaCost(){
        return this.ManaCost;
    }

    protected void setManaCost(int manaCost) {
        ManaCost = manaCost;
    }

    protected void setName(String name) {
        Name = name;
    }

    protected void setOwner(Player owner) {
        Owner = owner;
    }

    public void setOponent(Player oponent) {
        Oponent = oponent;
    }

    public Player getOwner() {
        return Owner;
    }

    public Player getOponent() {
        return Oponent;
    }

    public abstract String viewCard();
}
