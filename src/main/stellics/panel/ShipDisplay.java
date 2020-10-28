package stellics.panel;

import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.button.RequestShips;
import stellics.button.ShowCargo;
import stellics.filter.FilterFactory;
import stellics.helper.StorageHelper;

public class ShipDisplay extends BoardElement {

    private List<FleetMemberAPI> ships;

    public ShipDisplay(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
        FilterFactory filterFactory = board.getFilterFactory();
        ships = StorageHelper.getAllShips(filterFactory.getFleetMemberFilters());
    }

    @Override
    public void render() {
        float currentHeight = 0;
        currentHeight = renderShips(ships, currentHeight, 25f);
        currentHeight = renderDoubleButton(new RequestShips(), new ShowCargo(), currentHeight);
    }
}
