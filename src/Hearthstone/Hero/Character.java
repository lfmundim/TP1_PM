package Hearthstone.Hero;

import Hearthstone.Cards.Card;

public abstract class Character extends Card{
    public Character(){
        //
    }
    private int Health;
    private int Attack;
    private int Damage = 0;

    public int getAttack(){
        return this.Attack;
    }

    public void setAttack(int attack){
        this.Attack = attack;
    }

    public int getHealth() {
        return this.Health - this.Damage;
    }

    public void dealDamage(int damage) {
        this.Damage = this.Damage + damage;
        if (this.Damage >= this.Health){
            this.Die();
        }
    }

    protected abstract void Die();

    public void Heal(int heal) {
        this.Damage = this.Damage - heal;
        if(this.Damage < 0){
            this.Damage = 0;
        }
    }

    public void setHealth(int health) {
        this.Health = health;
        this.Damage = 0;
    }

}
