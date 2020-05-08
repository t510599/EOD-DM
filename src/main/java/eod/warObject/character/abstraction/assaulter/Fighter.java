package eod.warObject.character.abstraction.assaulter;

import eod.warObject.character.Character;
import eod.Party;
import eod.Player;

public abstract class Fighter extends Character {
    public Fighter(Player player, int hp, int range, Party party) {
        super(player, hp, range, party);
    }
}