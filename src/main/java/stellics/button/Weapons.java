package stellics.button;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.StellicsBoard;
import stellics.filter.IsNotWeapon;

public class Weapons extends Button {

    public Weapons() {
        super("Weapons", new IsNotWeapon());
    }

    @Override
    public void handle(StellicsBoard board, IntelUIAPI ui) {
        super.handle(board, ui);
        board.getButtonManager().setEnabledWeaponButtons(isStateOn());
        ui.updateUIForItem(board);
    }
}
