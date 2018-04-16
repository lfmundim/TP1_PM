import java.awt.List;
import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.lang.Character;
import java.util.Random;
import java.util.Scanner;

import Hearthstone.Cards.Creature;
import Hearthstone.Game.Board;
import Hearthstone.Hero.Hero;
import Hearthstone.Hero.Player;
import Models.*;
import Models.Enumerations.PlayerClasses;

public class Main
{
	private static String[] Greetings = { "Busy night... but there's always room for another!",
			"Boys - look who it is!",
			"The inn is brimming with explorers tonight!",
			"The inn is oozing with cheer today!",
			"The party's already started. Come on in!",
			"Come on in! ... but, no shenanigans, eh?",
			"Hold on to your mug, we're in for a wild night!",
			"Come in! Tonight we're throwing a party fit for a king.",
			"There you are, the life of the party!" };
	// IDeck _deckManager;
	public static void main(String[] args) {
		Board Game;
		MyConsole myConsole = new MyConsole();

		myConsole.clearConsole();

		myConsole.printLn(GetGreetings());
		myConsole.printLn("What class would you like to play as?\n 0 - Hunter \t 1 - Warlock");

		String answer = myConsole.readLine();
		PlayerClasses selectedClass = answer.equals("0") 
									? PlayerClasses.Hunter 
									: PlayerClasses.Warlock; 

		// constroi os decks e define o priemeiro player
		Game = new Board(selectedClass);
		Game.getActivePlayer().setOponent(Game.getInactivePlayer());
		Game.getInactivePlayer().setOponent(Game.getActivePlayer());

		// initial draw
		InitialDraw(Game.getActivePlayer(), Game.getInactivePlayer());
		Game.getActivePlayer().upCrystals();
		Game.getActivePlayer().draw();

		while(IsGameContinuing(Game)) {
			Game.showBoard(); //mostra o campo antes da interação do jogador
			myConsole.printLn("What would you like to do?\n 0 - Play Card \t 1 - Use Hero Power \t 2 - Attack \t 3 - Pass Turn \t 4 - Concede ");
			answer = myConsole.readLine();

			if(answer.equals("0")) { // Play card
                myConsole.printLn("Which card would you like to play?");
                String handString = Game.getActivePlayer().getHand().viewHand();
                myConsole.printLn(handString);
				answer = myConsole.readLine();
				
                int index = Integer.parseInt(answer);
                Game.getActivePlayer().playCard(index);
                Game.getActivePlayer().getField().Refresh();
                Game.getInactivePlayer().getField().Refresh();
			}
			
			else if(answer.equals("1")) { //Hero power
				Game.getActivePlayer().getHero().getPower().Cast(Game.getActivePlayer(), Game.getInactivePlayer());
			}
			else if(answer.equals("2")) { //Attack
                myConsole.printLn("Choose your attacker:");
				String fieldString = Game.getActivePlayer().showAttackers();
				
                myConsole.printLn(fieldString);
				answer = myConsole.readLine();
				
                int index = Integer.parseInt(answer);
                Creature attacker = Game.getActivePlayer().getField().getField()[index];
                if(attacker.isSick()){
                    myConsole.printLn("This creature can't attack this turn");
                }
                else {
                    myConsole.printLn("Choose the target:");
                    fieldString = Game.getInactivePlayer().showDefenders();
					myConsole.printLn(fieldString);
					
                    answer = myConsole.readLine();
					index = Integer.parseInt(answer);
					
                    if (index != Game.getInactivePlayer().getField().getCount()) { //se alvo!=hero
                        Creature defender = Game.getInactivePlayer().getField().getField()[index];
                        if (Game.getInactivePlayer().getField().getCountTaunt() > 0 && !defender.isTaunt()){
                            myConsole.printLn("A Taunt Creature is on the way!");
                        }
                        else {
                            attacker.Attack(defender);
                            Game.getActivePlayer().getField().Refresh();
                            Game.getInactivePlayer().getField().Refresh();
                        }
                    } else {
                        if(Game.getInactivePlayer().getField().getCountTaunt() > 0){
                            myConsole.printLn("A Taunt Creature is on the way!");
                        }
                        else {
                            Hero defender = Game.getInactivePlayer().getHero();
                            attacker.Attack(defender);
                            Game.getActivePlayer().getField().Refresh();
                            Game.getInactivePlayer().getField().Refresh();
                        }
                    }
                }
			}
			else if(answer.equals("3")) { //Muda a prioridade de turno
				Game.passTurn();
			}
			else if(answer.equals("4")) { //Desiste da partida
				Hero hero = Game.getActivePlayer().getHero();
				int damage = hero.getHealth();
				hero.dealDamage(damage);
			}
		}
	    System.exit(0);
	}


	private static boolean IsGameContinuing(Board Game) {
		return Game.getActivePlayer().getHero().getHealth()>0 && Game.getInactivePlayer().getHero().getHealth()>0;
	}


	private static void InitialDraw(Player homePlayer, Player awayPlayer) {
		for(int i=0; i<3; i++) {
			homePlayer.draw();
			awayPlayer.draw();
		}
		awayPlayer.AddCoin();
	}

	public static String GetGreetings() {
		Random rng = new Random();
		int index = rng.nextInt();
		if(index < 0) index = index*(-1);
		return Greetings[index%9];
	}
}
