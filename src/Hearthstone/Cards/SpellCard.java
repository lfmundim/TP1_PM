package Hearthstone.Cards;

import java.util.Random;
import java.util.Scanner;

import Hearthstone.Game.Field;
import Hearthstone.Hero.Player;
import Models.Enumerations.Targets;

public class SpellCard extends Card {
    public SpellCard(String spellName,
                     int manaCost, 
                     String description,
                     boolean isHeal,
                     boolean isDamage,
                     boolean isConditionalDraw,
                     Targets damageTargets,
                     Targets healTargets) {
        this.Effect = description;
        this.setManaCost(manaCost);
        this.setName(spellName);
        this.damageTargets = damageTargets;
        this.healTargets = healTargets;
        this.isHeal = isHeal;
        this.isDamage = isDamage;
        this.isConditionalDraw = isConditionalDraw;
    }
    
    private String Effect;
    private boolean isHeal, isDamage, isConditionalDraw;
    private Targets damageTargets, healTargets;

    public String getEffect() {
        return Effect;
    }

    // public void setEffect(String effect) {
    //     Effect = effect;
    // }


    @Override
    public String viewCard(){
        return "(" + this.getManaCost()+ ") " + this.getName()+ ": " + this.getEffect();
    }

	@Override
	public void Play(Player owner, Player oponent) {
		Scanner scanner = new Scanner(System.in);
		String answer = "";
		if(damageTargets == Targets.Anything || damageTargets == Targets.Creature) {			
			System.out.println("Which target?");
			//System.out.println(oponent.getField().viewField());
			Creature[] creatures = getAnyCreature(oponent);
			System.out.println(showPossibleSpellTargets(damageTargets, creatures, oponent));
			answer = scanner.nextLine();
			if(answer.toLowerCase().equals("p")) { //player
				resolveEffect(oponent, owner);
			}
			else { //creature
				int targetIndex = Integer.parseInt(answer);
				resolveEffect(creatures[targetIndex], owner);
			}
		}
		else if(damageTargets == Targets.Everything) {
			resolveHellfire(owner, oponent);
		}
		else if(damageTargets == Targets.RandomCreatures) {
			Random rng = new Random();
			if(this.getName().equals("Deadly Shot")) {
				resolveDeadlyShot(oponent.getField(), rng);
			}
			else if(this.getName().equals("Multi Shot")) {
				resolveMultiShot(oponent.getField(), rng);
			}
		}
	}

	private void resolveEffect(Player oponent, Player owner) {
		if(this.Effect.contains("Deal")) {
			int damage = Integer.parseInt(this.Effect.substring(5, 6));
			oponent.getHero().dealDamage(damage);
		}		
	}
	
	private void resolveEffect(Creature creature, Player owner) {
		if(this.Effect.contains("Deal")) {
			if(this.Effect.contains("dies")) {
				if(creature.getHealth() == 1) owner.draw();
			}
			int damage = Integer.parseInt(this.Effect.substring(5, 6));
			creature.dealDamage(damage);
		}
		if(this.Effect.contains("Change")) {
			creature.setHealth(1);
		}
		if(this.Effect.contains("Destroy")) {
			creature.dealDamage(creature.getHealth());
			owner.getHero().Heal(3);
		}
	}

	private void resolveDeadlyShot(Field field, Random rng) {
		int creatureCount = field.getCreatureCount();
		Creature[] creatures = field.getField();
		Creature target = creatures[rng.nextInt()%creatureCount];
		target.dealDamage(target.getHealth());
	}

	private void resolveMultiShot(Field field, Random rng) {
		int creatureCount = field.getCreatureCount();
		Creature[] creatures = field.getField();
		
		int firstTargetIndex = rng.nextInt()%creatureCount;
		int secondTargetIndex = rng.nextInt()%creatureCount;
		
		while(secondTargetIndex == firstTargetIndex)
			secondTargetIndex = rng.nextInt()%creatureCount;
		
		Creature firstTarget = creatures[firstTargetIndex];
		Creature secondTarget = creatures[secondTargetIndex];
		
		firstTarget.dealDamage(2);
		secondTarget.dealDamage(2);
	}

	private void resolveHellfire(Player owner, Player oponent) {
		oponent.getHero().dealDamage(3);
		owner.getHero().dealDamage(3);
		
		Creature[] ownerCreatures = owner.getField().getField();
		Creature[] oponentCreatures = owner.getField().getField();
		
		for(Creature c : ownerCreatures) {
			c.dealDamage(3);
		}
		for(Creature c : oponentCreatures) {
			c.dealDamage(3);
		}
	}


	private String showPossibleSpellTargets(Targets targets, Creature[] creatures, Player oponent) {
		String str = "";
		if(targets == Targets.Anything) {
			str = showOponentCreatures(creatures, str);
			str = str + "P- " + oponent.getHero().getName() + "(Player)";
		}
		else {
			str = showOponentCreatures(creatures, str);
		}
		return str;
	}

	private String showOponentCreatures(Creature[] creatures, String str) {
		for(Creature c : creatures) {
			if(c==null) break;
			str = str + c.getPosition() + "- " + c.viewCard() + "\n";
		}
		return str;
	}

	private Creature[] getAnyCreature(Player oponent) {
		return oponent.getField().getField();
	}
}

