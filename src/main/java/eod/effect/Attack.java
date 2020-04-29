package eod.effect;

import eod.Character;
import eod.GameObject;
import eod.Player;

public class Attack implements Effect, GameObject {
    private int hp;
    private Player player;
    private Character from, to;

    public Attack(Player player, int hp) {
        this.hp = hp;
        this.player = player;
    }

    public Attack from(Character[] characters) {
        from = askToSelect(player, characters);
        return this;
    }

    public void to(Character[] characters) {
        to = askToSelect(player.rival(), characters);
        from.attack(new Character[] {to}, hp);
    }

    @Override
    public void teardown() {
        //don't need to do anything
    }
}
