package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.helper.ConfigHelper;

public abstract class Display extends BoardElement {

    public Display(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        if (ConfigHelper.allowTransfer()) {
            renderForTransfer();
        } else {
            renderForLocation();
        }
    }

    protected abstract void renderForTransfer();

    protected abstract void renderForLocation();
}
