package eod.effect;

import eod.characters.Character;
import eod.GameObject;
import eod.Player;

import java.awt.*;
import java.util.ArrayList;

public class RegionalAttack implements Effect, GameObject {
    // This class should be used only in regional attacks.
    // If there's a direct attack, use Attack.
    private int hp;
    private Player player;
    private Character attacker;
    private boolean allowConditional = true;
    private boolean willSuccess = true;

    public RegionalAttack(Player player, int hp) {
        this.player = player;
        this.hp = hp;
    }

    public Character attacker() {
        return attacker;
    }

    public RegionalAttack from(Character[] characters) {
        attacker = askToSelectFrom(characters);
        return this;
    }

    public RegionalAttack allowCondition(boolean allow) {
        allowConditional = allow;
        return  this;
    }

    public RegionalAttack willConditionSuccess(boolean success) {
        willSuccess = success;
        return this;
    }

    public RegionalAttack to(ArrayList<Point> targets) {
        attacker.attack(targets, hp, allowConditional, willSuccess);
        return this;
    }

    public RegionalAttack to(ArrayList<Point> candidates, int number) {
        if(number >= candidates.size()) {
            return to(candidates);
        }
        ArrayList<Point> targets = new ArrayList<>();
        for(int i = 0;i < number;i++) {
            Point target = askToSelectFrom(candidates);
            targets.add(target);
            candidates.remove(target);
        }
        attacker.attack(targets, hp, allowConditional, willSuccess);
        return this;
    }

    public RegionalAttack to(Character[] characters) {
        Character target = askToSelectFrom(characters);
        ArrayList<Point> singleTarget = new ArrayList<>();
        singleTarget.add(target.position);
        return to(singleTarget);
    }

    @Override
    public void teardown() {
        player = null;
        attacker = null;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}