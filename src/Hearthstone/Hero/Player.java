package Hearthstone.Hero;

import java.lang.Character;
import java.util.Random;

import Hearthstone.Cards.Card;
import Hearthstone.Cards.Creature;
import Hearthstone.Cards.Deck;
import Hearthstone.Cards.SpellCard;
import Hearthstone.Game.Field;
import Hearthstone.Hero.Hand;
import Models.Enumerations.PlayerClasses;

public class Player{
    private int Crystals = 0;
    private int Mana = 0;
    private Deck deck;
    private Hand hand;
    private Hero hero;
    private Field field;
    private int FatigueDamage = 0;
    private Card drawnC;
    private Player Oponent;
    private int health = 30;

    public Player(PlayerClasses playerClass){
    	deck = new Deck(playerClass);
    	hero = new Hero(playerClass);
    	hand = new Hand();
    	field = new Field();
    }
    
    public void setOponent(Player oponent) {
    	this.Oponent = oponent;
    }
    
    public int getHealth() {
    	return this.health;
    }
    
    public Hand getHand() {
    	return this.hand;
    }
    
    public Hero getHero() {
    	return this.hero;
    }
    
    public void AddCoin() {
    	SpellCard coin = new SpellCard("The Coin", -1, "Gain one mana crystal this turn only", false, false, false, null, null);
    	this.hand.addCard(coin);
    }

    public Deck getDeck(){
        return this.deck;
    }
    
    public int getMana() {
        return this.Mana;
    }

    public int getCrystals() {
        return this.Crystals;
    }

    public void upCrystals(){
        if (this.Crystals < 10) {
            this.Crystals = this.Crystals + 1;
        }
        this.Mana = this.Crystals;
    }

    public void upMana(int m){
        this.Mana = this.Mana + m;
    }

    public void playCard(int index){
        Card card = this.hand.getCards()[index];
        if (card.getManaCost() > this.Mana){
            System.out.println("Not enought mana");
        }
        else{
            this.Mana = this.Mana - card.getManaCost();
            card.Play(this, this.Oponent);
            this.hand.removeCard(index);
        }
    }
    
    public void SpendMana(int ammount) {
    	this.Mana = this.Mana - ammount;
    }

    public Field getField() {
        return field;
    }

    public int takeFatigue(){
        FatigueDamage++;
        return FatigueDamage;
    }
    
    public void resetSick() {
    	Creature[] creatures = this.field.getField();
    	for(Creature creature : creatures) {
    		if(creature!=null) creature.setSick(false);
    	}
    }
    
    public void draw(){
        /*
        1:Gera um num random entre 0 e o total de cartas restantes indicando o indice da carta
        2:Da draw nesse indice (Coloca na mão)
        3:troca a ultima carta do deck com a do esse indice
        4:faz a ultima carta do deck ser = a null
        5:diminui o numero total de cartas restantes no deck
        6:Overdraw: remove do deck mas não adiciona na mão
         */

        if(this.deck.getTotal() == 0){
            this.hero.dealDamage(this.takeFatigue());
        }

        else {
            Random generator = new Random();
            if(this.deck.getTotal() > 0){ // TODO not working
                this.drawnC = this.deck.removeCard(generator.nextInt(this.deck.getTotal()-1));

                if (this.hand.getSize() < 10) {
                    this.hand.addCard(this.drawnC);
                } else {
                    System.out.println("You have overdrawn: " + this.drawnC.getName());
                    this.drawnC.viewCard();
                }
            }
        }
    }

    public String showAttackers(){
        String str = "";
        Creature[] creatures = this.field.getField();
        for(Creature c : creatures){
        	if(c == null) break;
            if (!c.isSick()){
                str = str + c.getPosition() + "- " + c.viewCard() + "\n";
            }
        }
        return str;
    }

    public String showDefenders(){
        String str = "";
        for(Creature c : this.field.getField()){
            if (this.field.getCountTaunt()>0){
                if(c==null)break;
            	if (c.isTaunt())
                    str = str + c.getPosition() + "- " + c.viewCard() + "\n";
            }
            else {
            	if(c==null)break;
                str = str + c.getPosition() + "- " + c.viewCard() + "\n";
            }
        }
        if (this.field.getCountTaunt() == 0){
            str = str + this.field.getCount() + "- " + this.hero.viewCard() + "\n";
        }
        return str;
    }

}
