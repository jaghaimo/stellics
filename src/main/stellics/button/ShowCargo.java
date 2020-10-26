package stellics.button;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.StorageBoard;

public class ShowCargo extends Button {

    public ShowCargo() {
        super("Swap");
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        board.togglePane();
        ui.updateUIForItem(board);
    }
}
