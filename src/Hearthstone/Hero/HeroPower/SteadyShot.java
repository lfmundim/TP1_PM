package Hearthstone.Hero.HeroPower;

import Hearthstone.Hero.Player;

public class SteadyShot extends HeroPower {
    public void Cast(){};

    public SteadyShot(){
        this.setName("Steady Shot");
        this.setEffect("Deal 2 damage to the enemy Hero.");
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
            oponent.getHero().dealDamage(2);
            this.setUsed(true);
        }
    }
}
