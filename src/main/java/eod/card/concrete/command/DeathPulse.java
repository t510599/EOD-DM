package eod.card.concrete.command;

import eod.Party;
import eod.card.abstraction.Card;
import eod.card.abstraction.action.AttackCard;
import eod.effect.Attack;
import eod.warObject.leader.red.Sundar;

import static eod.specifier.WarObjectSpecifier.WarObject;
import static eod.specifier.condition.Conditions.Being;

public class DeathPulse extends AttackCard {

    @Override
    public Attack attack() {
        Sundar sundar = (Sundar) WarObject(player.getBoard()).which(Being(Sundar.class)).get()[0];
        return sundar.deathPulse();
    }

    @Override
    public Card copy() {
        Card c = new DeathPulse();
        c.setPlayer(player);
        return c;
    }

    @Override
    public int getCost() {
        return 4;
    }

    @Override
    public String getName() {
        return "死亡脈衝";
    }

    @Override
    public Party getParty() {
        return Party.RED;
    }
}
