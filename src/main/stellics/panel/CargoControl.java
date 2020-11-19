package stellics.panel;

import java.util.Arrays;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StellicsBoard;
import stellics.button.ButtonManager;
import stellics.button.DisplayMode;
import stellics.button.RequestCargo;

public class CargoControl extends BoardElement {

    public CargoControl(StellicsBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        renderFilters();
        renderControls(new RequestCargo());
    }

    private void renderFilters() {
        float currentHeight = 0;
        ButtonManager buttonManager = board.getButtonManager();
        currentHeight = renderFilters(Arrays.asList(buttonManager.getDisplayModeButton(), new DisplayMode(false)),
                currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoTypeButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoWeaponButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoFighterWingsButtons(), currentHeight);
    }
}
