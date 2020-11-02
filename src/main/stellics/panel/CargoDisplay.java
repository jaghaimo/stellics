package stellics.panel;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stellics.StorageBoard;
import stellics.filter.FilterFactory;
import stellics.helper.CargoHelper;
import stellics.helper.CollectionHelper;
import stellics.helper.StorageHelper;

public class CargoDisplay extends Display {

    private boolean hasResults = false;

    public CargoDisplay(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    protected void renderForTransfer() {
        FilterFactory filterFactory = board.getFilterFactory();
        CargoAPI cargo = StorageHelper.getAllCargo(filterFactory.getCargoStackFilters());
        renderCargo(cargo);
    }

    @Override
    protected void renderForLocation() {
        TooltipMakerAPI display = panel.createUIElement(width, height, true);
        FilterFactory filterFactory = board.getFilterFactory();
        List<SubmarketAPI> storages = StorageHelper.getAllSortedWithAccess();
        for (SubmarketAPI storage : storages) {
            CargoAPI storageCargo = storage.getCargo();
            CargoAPI displayedCargo = storageCargo.createCopy();
            List<CargoStackAPI> cargoStacks = storageCargo.getStacksCopy();
            CollectionHelper.reduce(cargoStacks, filterFactory.getCargoStackFilters());
            CargoHelper.replaceCargoStacks(displayedCargo, cargoStacks);
            renderStorageCargo(display, storage.getMarket(), displayedCargo);
        }
        if (!hasResults) {
            display.addPara("There is no matching cargo to display.", 10f);
        }
        panel.addUIElement(display);
    }

    private void renderStorageCargo(TooltipMakerAPI display, MarketAPI market, CargoAPI cargo) {
        if (cargo.isEmpty()) {
            return;
        }
        hasResults = true;
        FactionAPI faction = market.getFaction();
        display.addSectionHeading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor(), Alignment.MID,
                10f);
        display.showCargo(cargo, cargo.getStacksCopy().size(), false, 5f);
    }
}
