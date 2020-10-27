package stellics.button;

import java.awt.Color;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stellics.StorageBoard;

public class ShowCargo extends Button {

    public ShowCargo() {
        super("Show Cargo");
    }

    @Override
    public Color getColor() {
        return Misc.getButtonTextColor();
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        board.togglePane();
        ui.updateUIForItem(board);
    }
}
