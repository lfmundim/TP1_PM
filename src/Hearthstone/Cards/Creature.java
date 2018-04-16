package Hearthstone.Cards;

import Hearthstone.Hero.Character;
import Hearthstone.Hero.Player;
import Models.MyConsole;

public class Creature extends Character{

    private boolean Taunt;
    private boolean Sick;
    private int position;
    private MyConsole myConsole;

    public boolean isTaunt() {
        return Taunt;

    }

    public boolean isSick() {
        return Sick;

    }

    public void setTaunt(boolean taunt){
        if (taunt) Taunt = true;
        else Taunt = false;
    }

    public void setSick(boolean sick){
        if (sick) Sick = true;
        else Sick = false;
    }

    @Override
    public void Play(Player owner, Player oponent){
        this.setOwner(owner);
        this.setOponent(oponent);
        this.position = this.getOwner().getField().getCount();
        this.getOwner().getField().Summon(this);
    }

    @Override
    public String viewCard(){
    	String cost = "(" + this.getManaCost()+") ";
    	String name = this.getName()+": ";
    	String taunt = "", charge = "";
    	String attack = "Attack: "+ this.getAttack() + " / ";
    	String health = "Health: "+ this.getHealth();
    	
        if(this.isTaunt()) {
            taunt = "{Taunt} ";
        }
        if(!this.isSick()) {
        	charge = "{Charge} ";
        }
        return cost + name + taunt + charge + attack + health;
    }

    @Override
    protected void Die(){
        if (this.isTaunt()){
            this.getOwner().getField().reduceTaunt();
        }
        this.getOwner().getField().Destroy(this.position);
        myConsole.printLn(this.getName() + " died.");
    }

    public void Attack(Character target){
        target.dealDamage(this.getAttack());
        this.dealDamage(target.getAttack());
    }

    public void setPosition(int i){
        this.position = i;
    }

    public int getPosition() {
        return position;
    }

    public Creature(int mana, String name, boolean taunt, boolean sick, int attack, int health) {
        this.setManaCost(mana);
        this.setName(name);
        this.setTaunt(taunt);
        this.setSick(sick);
        this.setAttack(attack);
        this.setHealth(health);
        myConsole = new MyConsole();
    }

}
