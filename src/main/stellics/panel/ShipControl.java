package stellics.panel;

import java.util.Arrays;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.button.ButtonManager;
import stellics.button.RequestShips;
import stellics.button.DisplayMode;

public class ShipControl extends BoardElement {

    public ShipControl(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        renderFilters();
        renderControls(new RequestShips());
    }

    private void renderFilters() {
        float currentHeight = 0;
        ButtonManager buttonManager = board.getButtonManager();
        currentHeight = renderFilters(Arrays.asList(buttonManager.getDisplayModeButton(), new DisplayMode(true)),
                currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getShipSizeButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getShipTypeButtons(), currentHeight);
    }
}
