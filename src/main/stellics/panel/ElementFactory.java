package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.filter.FilterManager;

public class ElementFactory {

    private StorageBoard board;
    private CustomPanelAPI panel;
    private float height;

    public ElementFactory(StorageBoard board, CustomPanelAPI panel, float height) {
        this.board = board;
        this.panel = panel;
        this.height = height;
    }

    public BoardElement getControlColumn(float width) {
        FilterManager filterManager = board.getFilterManager();
        switch (filterManager.getBoardView()) {
            case Ships:
                return new ShipControl(board, panel, width, height);
            case Cargo:
            default:
                return new CargoControl(board, panel, width, height);
        }
    }

    public BoardElement getDisplayColumn(float width) {
        FilterManager filterManager = board.getFilterManager();
        switch (filterManager.getBoardView()) {
            case Ships:
                return new ShipDisplay(board, panel, width, height);
            case Cargo:
            default:
                return new CargoDisplay(board, panel, width, height);
        }
    }
}
