package eod.card.concrete.normal;

import eod.Gameboard;
import eod.Party;
import eod.Player;
import eod.card.abstraction.Card;
import eod.card.abstraction.action.NormalCard;
import eod.effect.EffectExecutor;
import eod.effect.Summon;
import eod.warObject.character.abstraction.supporter.Sapper;
import eod.warObject.character.concrete.transparent.Drone;

import java.awt.*;
import java.util.ArrayList;

import static eod.effect.EffectFunctions.Summon;
import static eod.specifier.WarObjectSpecifier.Character;
import static eod.specifier.condition.Conditions.Being;
import static eod.specifier.condition.Conditions.OwnedBy;

public class DroneSupport extends NormalCard {

    private Gameboard board;

    @Override
    public void setPlayer(Player p) {
        super.setPlayer(p);
        board = p.getBoard();
    }

    @Override
    public void applyEffect(EffectExecutor executor) {
        Summon effect = Summon(new Drone(player)).onOnePointOf(player, board.allEmptySpaces(new Point(Gameboard.firstLine, 0)));
        executor.tryToExecute(effect);
        if(twoSapper()) {
            ArrayList<Point> emptySpaces = board.getSurroundingEmpty(effect.getSummonPoint(), 1);
            if(emptySpaces.size() != 0) {
                executor.tryToExecute(Summon(new Drone(player)).onOnePointOf(player, emptySpaces));
            }
        }
    }

    private boolean twoSapper() {
        return Character(player.getBoard())
                .which(OwnedBy(player))
                .which(Being(Sapper.class)).get()
                .length>=2;
    }

    @Override
    public Card copy() {
        Card c = new DroneSupport();
        c.setPlayer(player);
        return c;
    }

    @Override
    public int getCost() {
        return 3;
    }

    @Override
    public String getName() {
        return "無人機支援";
    }

    @Override
    public Party getParty() {
        return Party.TRANSPARENT;
    }
}
