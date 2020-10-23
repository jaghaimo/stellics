package stellics.panel;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.helper.StorageHelper;

public class LeftColumn extends PanelElement {

    private CargoAPI cargo;

    public LeftColumn(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
        cargo = StorageHelper.getCombinedCargo();
    }

    @Override
    public void render(float posX, float posY) {
    }
}
