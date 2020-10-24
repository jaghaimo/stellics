package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;

public abstract class BoardElement {

    protected StorageBoard board;
    protected CustomPanelAPI panel;
    protected float width;
    protected float height;

    public BoardElement(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        this.board = board;
        this.panel = panel;
        this.width = width;
        this.height = height;
    }

    public abstract void render();
}
