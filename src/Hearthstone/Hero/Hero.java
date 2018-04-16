package Hearthstone.Hero;

import Hearthstone.Hero.HeroPower.HeroPower;
import Hearthstone.Hero.HeroPower.LifeTap;
import Hearthstone.Hero.HeroPower.SteadyShot;
import Models.Enumerations.PlayerClasses;

public class Hero extends Character{
    private HeroPower Power;
    private String Role;
    
    public Hero(PlayerClasses playerClass){
    	this.setHealth(30);
    	if (playerClass.equals(PlayerClasses.Hunter)){
    		setHunter();
    	}
    	else {
    		setWarlock();
    	}
    }
    
    public String getRole() {
    	return this.Role;
    }
    
    public HeroPower getPower() {
    	return this.Power;
    }

    private void setHunter(){
        this.Role = "Hunter";
        this.setName("Rexxar");
        this.Power = new SteadyShot();
    }

    private void setWarlock(){
        this.Role = "Warlock";
        this.setName("Gul'dan");
        this.Power = new LifeTap();
    }

    @Override
    public void Play(Player owner, Player oponent){
        this.setOwner(owner);
        this.setOponent(oponent);
    }

    @Override
    public String viewCard(){
        return this.getName() + ": " + this.Role + ", " + this.Power.viewPower(); 
    }

    @Override
    protected void Die(){
    	Hero opponent = this.getOponent().getHero();
    	System.out.println(opponent.getName() + "(" + opponent.getRole() + ") Wins!");
    }


}
