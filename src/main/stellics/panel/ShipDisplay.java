package stellics.panel;

import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stellics.StorageBoard;
import stellics.helper.StorageHelper;

public class ShipDisplay extends BoardElement {

    private List<FleetMemberAPI> ships;

    public ShipDisplay(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
        ships = StorageHelper.getAllShips();
    }

    @Override
    public void render() {
        TooltipMakerAPI header = panel.createUIElement(width, 20f, false);
        header.addSectionHeading("Ships", Alignment.MID, 5f);
        panel.addUIElement(header).inTL(0, 0);

        TooltipMakerAPI shipView = panel.createUIElement(width, height - 25f, true);
        shipView.showShips(ships, ships.size(), false, 5f);
        panel.addUIElement(shipView).inTL(0, 25f);
    }
}
