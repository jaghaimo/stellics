package stellics.button;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.StorageBoard;
import stellics.filter.IsNotFighterWing;

public class FighterWings extends Button {

    public FighterWings() {
        super("Fighter Wings", new IsNotFighterWing());
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        super.handle(board, ui);
        board.getButtonManager().setEnabledFighterWingButtons(isStateOn());
        ui.updateUIForItem(board);
    }
}
