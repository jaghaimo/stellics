package stellics.panel;

import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.helper.StorageHelper;

public class RightColumn extends PanelElement {

    private List<FleetMemberAPI> ships;

    public RightColumn(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
        ships = StorageHelper.getCombinedShips();
    }

    @Override
    public void render(float posX, float posY) {
    }
}
