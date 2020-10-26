package stellics.button;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.StorageBoard;

public interface ButtonHandler {

    public void handle(StorageBoard board, IntelUIAPI ui);
}
