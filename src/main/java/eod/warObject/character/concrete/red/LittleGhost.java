package eod.warObject.character.concrete.red;

import eod.Player;
import eod.warObject.character.abstraction.other.Ghost;

public class LittleGhost extends Ghost {
    public LittleGhost(Player player) {
        super(player, 1, 1);
        // TODO: ask Spacezipper about the details of LittleGhost
    }

    @Override
    public String getName() {
        return "小亡靈";
    }
}