package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.button.Button;
import stellics.button.ButtonManager;

public abstract class Display extends BoardElement {

    public Display(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        ButtonManager buttonManager = board.getButtonManager();
        Button displayMode = buttonManager.getDisplayModeButton();
        if (displayMode.isStateOn()) {
            renderForTransfer();
        } else {
            renderForLocation();
        }
    }

    protected abstract void renderForTransfer();

    protected abstract void renderForLocation();
}
