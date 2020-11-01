package stellics.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.button.Button;
import stellics.button.ButtonManager;
import stellics.button.LocateCargo;
import stellics.button.RequestCargo;
import stellics.button.ShowShips;
import stellics.helper.ConfigHelper;

public class CargoControl extends BoardElement {

    public CargoControl(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        renderFilters();
        renderControls(getActionButton(), new ShowShips());
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

    private Button getActionButton() {
        if (ConfigHelper.allowTransfer()) {
            return new RequestCargo();
        }
        return new LocateCargo();
    }
}
