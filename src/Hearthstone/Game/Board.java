package Hearthstone.Game;

import Hearthstone.Hero.Player;
import Models.MyConsole;
import Models.Enumerations.PlayerClasses;

public class Board {

    private Player activePlayer;
    private Player inactivePlayer;
    private Player aux;
    private MyConsole myConsole;
    
    public Board(PlayerClasses chosenClass){
    	this.activePlayer = new Player(chosenClass);
    	PlayerClasses otherClass = chosenClass == PlayerClasses.Warlock ? PlayerClasses.Hunter : PlayerClasses.Warlock;
    	this.inactivePlayer = new Player(otherClass);
    	this.inactivePlayer.getHero().setOponent(activePlayer);
        this.activePlayer.getHero().setOponent(inactivePlayer);
        myConsole = new MyConsole();
    }

    public void showBoard(){
    	//opponent
    	myConsole.printLn( "Opponent's Health: " + inactivePlayer.getHero().getHealth() + " | Opponent's deck size: " + inactivePlayer.getDeck().getTotal() + "\n");
    	myConsole.printLn( "Opponent's hand card count: " + inactivePlayer.getHand().getSize() + "\n");
    	myConsole.printLn( "Opponent's Creatures: \n" + inactivePlayer.getField().viewField() + "\n");

        //player
    	myConsole.printLn( "Your Creatures: \n" + activePlayer.getField().viewField() + "\n");
    	myConsole.printLn( "Your hand \n" + activePlayer.getHand().viewHand() + "\n");
    	myConsole.printLn( "Your Health: " + activePlayer.getHero().getHealth() + " | Your deck size: " + activePlayer.getDeck().getTotal() + "\n"); 
        myConsole.printLn( "Your hero: \n" + this.activePlayer.getHero().getName() + "(" + this.activePlayer.getHero().getRole() + ")" + ", " + this.activePlayer.getHero().getPower().viewPower() + "\n");
        myConsole.printLn( "Your mana: [" + this.activePlayer.getMana() + "/" + this.activePlayer.getCrystals() + "]");
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getInactivePlayer() {
        return inactivePlayer;
    }

    public void passTurn(){
        this.aux = this.activePlayer;
        this.activePlayer = this.inactivePlayer;
        this.inactivePlayer = this.aux;
        this.activePlayer.draw();
        this.activePlayer.upCrystals();
        this.activePlayer.getHero().getPower().setUsed(false);
        this.activePlayer.resetSick();
    }
}
