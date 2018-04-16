package Hearthstone.Hero.HeroPower;

import Hearthstone.Hero.Player;

public abstract class HeroPower {
    private String Name;
    private String Effect;
    private int ManaCost = 2;
    private boolean used;

    public String getName() {
        return Name;
    }

    public void setUsed(boolean flag) {
    	this.used = flag;
    }
    
    public void setName(String name) {
        Name = name;
    }

    public String getEffect() {
        return Effect;
    }

    public void setEffect(String effect) {
        Effect = effect;
    }

    public int getManaCost() {
        return ManaCost;
    }

    public boolean isUsed(){
        return this.used;
    }
    public String viewPower(){
        String cost;
    	if(this.isUsed()){
    		cost = "Used this turn. ";
        }
        else {
        	cost = "("+this.getManaCost()+") ";
        }
    	return cost + this.getName() + ". " + this.getEffect() + "\n";
    }
    
    public abstract void Cast(Player owner, Player oponent);
}
