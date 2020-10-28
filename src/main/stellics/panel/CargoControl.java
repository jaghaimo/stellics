package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.button.ButtonManager;

public class CargoControl extends BoardElement {

    public CargoControl(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        float currentHeight = 0;
        ButtonManager buttonManager = board.getButtonManager();
        currentHeight = renderFilters(buttonManager.getCargoTypeButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoWeaponButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoFighterWingsButtons(), currentHeight);
    }
}
