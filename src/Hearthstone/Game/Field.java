package Hearthstone.Game;

import Hearthstone.Cards.Creature;
import Models.MyConsole;

public class Field {
    private Creature[] field;
    private int limit = 7;
    private int count = 0; // contador de minions
    private int new_count;
    private int countTaunt = 0;
    private int i, j; // aux

    public Field(){
        field = new Creature[7];
    }
    
    public void reduceTaunt(){
        this.countTaunt--;
    }

    public int getCountTaunt(){
        return this.countTaunt;
    }

    public String viewField(){
    	String field = "";
        for (int i = 0; i< count; i++){
            field = field + this.field[i].viewCard() + "\n";
        }
        return field;
    }
    
    public int getCreatureCount() {
		int creatureCount = 0;
		for(Creature c : field) {
			if(c != null) creatureCount++;
		}
		return creatureCount;
	}

    public Creature[] getField() {
    	return this.field;
    }
    
    public void Summon(Creature c){
        MyConsole myConsole = new MyConsole();
        if(count == limit){
            myConsole.printLn("The field is full!");
        }
        else {
            this.field[count] = c;
            if(c.isTaunt()){
                this.countTaunt++;
            }
            this.count++;
        }
    }

    public void Destroy(int index){
        field[index] = null;
    }

    public void Refresh(){
        new_count = count;
        for (i = 0; i<count; i++){
            if (field[i] == null){
                new_count = i;
                for (j=i; j<count; j++){
                    if (field[j] != null){
                        field[j].setPosition(i);
                        field[i] = field[j];
                        field[j] = null;
                    }
                }
            }
        }
        count = new_count;
    }


    public int getCount() {
        return this.count;
    }
}
