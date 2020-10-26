package stellics.button;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.StorageBoard;
import stellics.filter.IsNotWeapon;

public class Weapons extends Button {

    public Weapons() {
        super("Weapons");
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        super.handle(board, ui);
        ui.updateUIForItem(board);
    }

    @Override
    public Object getFilter() {
        if (isStateOn()) {
            return new IsNotWeapon();
        }
        return super.getFilter();
    }
}
