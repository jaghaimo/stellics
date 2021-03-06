package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StellicsBoard;

public class ElementFactory {

    private StellicsBoard board;
    private CustomPanelAPI panel;
    private float height;

    public ElementFactory(StellicsBoard board, CustomPanelAPI panel, float height) {
        this.board = board;
        this.panel = panel;
        this.height = height;
    }

    public BoardElement getControlColumn(float width) {
        switch (board.getActivePane()) {
            case Ships:
                return new ShipControl(board, panel, width, height);
            case Cargo:
            default:
                return new CargoControl(board, panel, width, height);
        }
    }

    public BoardElement getDisplayColumn(float width) {
        switch (board.getActivePane()) {
            case Ships:
                return new ShipDisplay(board, panel, width, height);
            case Cargo:
            default:
                return new CargoDisplay(board, panel, width, height);
        }
    }
}
