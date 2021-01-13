package stellics.button;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.StellicsBoard;

public interface ButtonHandler {

    public void handle(StellicsBoard board, IntelUIAPI ui);
}
