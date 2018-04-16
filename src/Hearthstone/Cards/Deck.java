package Hearthstone.Cards;

import Models.Enumerations.PlayerClasses;
import Models.Enumerations.Targets;

public class Deck{
    private Card[] deck;
    private int total;
    private int i, j;

    public Deck(PlayerClasses classe) {
    	deck = new Card[30];
    	total = 30;
    	j = 0;
    	
    	if(classe.equals(PlayerClasses.Hunter)){
    		CreateHunterDeck(deck);
    	}
    	else{//Se for hunter
    		CreateWarlockDeck(deck);
    	}
    }

    public int getTotal() {
        return total;
    }

    public Card removeCard(int index) {
        Card c = deck[index];
        deck[index] = deck[this.total-1];
        deck[this.total-1] = null;
        this.total--;
        return c;
    }
    

	private void CreateHunterDeck(Card[] deck) {
		for (i = 0; i<2; i++){
		    deck[j++] = new SpellCard("Arcane Shot", 1, "Deal 2 damage", false, true, false, Targets.Anything, null);
		    deck[j++] = new SpellCard("Hunter's Mark", 1, "Change a minion's health to 1", false, true, false, Targets.Creature, null);
		    deck[j++] = new SpellCard("Deadly Shot", 3, "Destroy a random enemy minion", false, true, false, Targets.RandomCreatures, null);
		    deck[j++] = new SpellCard("Multi Shot", 4, "Deal 3 damage to two random enemy minions", false, true, false, Targets.RandomCreatures, null );
		    deck[j++] = new Creature(3, "Carrion Grub", false, true, 2, 5);
		    deck[j++] = new Creature(1, "Stonetusk Boar", false, false, 1, 1);
		    deck[j++] = new Creature(2, "Bloodfen Raptor", false, true, 3, 2);
		    deck[j++] = new Creature(2, "River Crocolisk", false, true, 2, 3);
		    deck[j++] = new Creature(3, "Ironfur Grizzly", true, true, 3, 3);
		    deck[j++] = new Creature(3, "Am'gam Rager", false, true, 1, 5);
		    deck[j++] = new Creature(3, "Silverback Patriarch", true, true, 1, 4);
		    deck[j++] = new Creature(3, "Wolfrider", false, false, 3, 1);
		    deck[j++] = new Creature(5, "Fen Creeper", true, true, 3, 6);
		    deck[j++] = new Creature(7, "Core Hound", false, true, 9, 5);
		}
		deck[j++] = new Creature(9, "King Krush", false, false, 8, 8);
		deck[j] = new Creature(9, "Giant Mastodon", true, true, 6, 10);
	}

	private void CreateWarlockDeck(Card[] deck) {
		for (i = 0; i<2; i++){
		    deck[j++] = new SpellCard("Mortal Coil", 1, "Deal 1 damage to a minion. If it dies, draw a card", false, true, true, Targets.Creature, null);
		    deck[j++] = new SpellCard("Darkbomb", 2, "Deal 3 damage", false, true, false, Targets.Anything, null);
		    deck[j++] = new SpellCard("Hellfire", 4, "Deal 3 damage to all characters", false, true, false, Targets.Everything, null);
		    deck[j++] = new SpellCard("Siphon Soul", 6, "Destroy target minion, restore 3 health of your hero", true, true, false, Targets.Creature, Targets.Player);
		    deck[j++] = new Creature(1, "Dire Mole", false, true, 1, 3);
		    deck[j++] = new Creature(1, "Stonetusk Boar", false, false, 1, 1);
		    deck[j++] = new Creature(1, "Voidwalker", true, true, 1, 3);
		    deck[j++] = new Creature(2, "River Crocolisk", false, true, 2, 3);
		    deck[j++] = new Creature(3, "Ironfur Grizzly", true, true, 3, 3);
		    deck[j++] = new Creature(3, "Spider Tank", false, true, 3, 4);
		    deck[j++] = new Creature(4, "Chillwind Yeti", false, true, 4, 5);
		    deck[j++] = new Creature(4, "Sen'jin Shieldmasta", true, true, 3, 5);
		    deck[j++] = new Creature(4, "Evil Heckler", true, true, 5, 4);
		    deck[j++] = new Creature(6, "Boulderfist Ogre", false, true, 6, 7);                
		}
		deck[j++] = new Creature(7, "Bog Creeper", true, true, 6, 8);
		deck[j] = new Creature(10, "Ultrasaur", false, true, 7, 14);
	}

}
