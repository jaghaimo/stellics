package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.button.ButtonManager;
import stellics.button.RequestShips;
import stellics.button.ShowCargo;

public class ShipControl extends BoardElement {

    public ShipControl(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        float currentHeight = 0;
        ButtonManager buttonManager = board.getButtonManager();
        currentHeight = renderFilters(buttonManager.getShipSizeButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getShipTypeButtons(), currentHeight);

        renderControls(new RequestShips(), new ShowCargo());
    }
}
