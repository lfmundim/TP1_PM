package Hearthstone.Hero.HeroPower;

import Hearthstone.Hero.Player;

public class LifeTap extends HeroPower {
    public void Cast(){};

    public LifeTap(){
       this.setName("Life Tap");
       this.setEffect("Lose 2 hp, draw a card.");
    }
    
    public void Cast(Player owner, Player oponent){
        if (this.getManaCost() > owner.getMana()){
            System.out.println("Not enought mana");
        }
        else if(this.isUsed()) {
        	System.out.println("Hero power already used this turn.");
        }
        else{
            owner.SpendMana(this.getManaCost());
            owner.getHero().dealDamage(2);
            owner.draw();
            this.setUsed(true);
        }
    }
}
