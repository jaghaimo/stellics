package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.FilterManager.BoardView;

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
        return new ControlColumn(board, panel, width, height);
    }

    public BoardElement getDisplayColumn(BoardView boardView, float rightWidth) {
        switch (boardView) {
            case Ships:
                return new ShipColumn(board, panel, rightWidth, height);
            case Cargo:
            default:
                return new CargoColumn(board, panel, rightWidth, height);
        }
    }
}
