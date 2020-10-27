package stellics.button;

import java.awt.Color;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stellics.StorageBoard;

public class ShowShips extends Button {

    public ShowShips() {
        super("Show Ships");
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
