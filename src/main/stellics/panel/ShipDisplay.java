package stellics.panel;

import java.util.List;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stellics.StorageBoard;
import stellics.filter.FilterFactory;
import stellics.helper.CollectionHelper;
import stellics.helper.StorageHelper;

public class ShipDisplay extends Display {

    private boolean hasResults = false;

    public ShipDisplay(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    protected void renderForTransfer() {
        FilterFactory filterFactory = board.getFilterFactory();
        List<FleetMemberAPI> ships = StorageHelper.getAllShips(filterFactory.getFleetMemberFilters());
        renderShips(ships);
    }

    @Override
    protected void renderForLocation() {
        TooltipMakerAPI display = panel.createUIElement(width, height, true);
        FilterFactory filterFactory = board.getFilterFactory();
        List<SubmarketAPI> storages = StorageHelper.getAllSortedWithAccess();
        for (SubmarketAPI storage : storages) {
            List<FleetMemberAPI> fleetMembers = storage.getCargo().getMothballedShips().getMembersInPriorityOrder();
            CollectionHelper.reduce(fleetMembers, filterFactory.getFleetMemberFilters());
            renderStorageFleet(display, storage.getMarket(), fleetMembers);
        }
        if (!hasResults) {
            display.addPara("There are no matching ships to display.", 10f);
        }
        panel.addUIElement(display);
    }

    private void renderStorageFleet(TooltipMakerAPI display, MarketAPI market, List<FleetMemberAPI> fleetMembers) {
        if (fleetMembers.isEmpty()) {
            return;
        }
        hasResults = true;
        FactionAPI faction = market.getFaction();
        display.addSectionHeading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor(), Alignment.MID,
                10f);
        display.showShips(fleetMembers, fleetMembers.size(), false, 5f);
    }
}
