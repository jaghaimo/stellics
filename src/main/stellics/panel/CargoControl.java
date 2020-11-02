package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.button.ButtonManager;
import stellics.button.RequestCargo;
import stellics.button.ShowShips;

public class CargoControl extends BoardElement {

    public CargoControl(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        renderFilters();
        renderControls(new RequestCargo(), new ShowShips());
    }

    private void renderFilters() {
        float currentHeight = 0;
        ButtonManager buttonManager = board.getButtonManager();
        currentHeight = renderFilters(buttonManager.getCargoTypeButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoWeaponButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoFighterWingsButtons(), currentHeight);
    }
}
