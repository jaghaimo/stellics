package stellics;

import java.util.Set;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stellics.helper.StorageHelper;
import stellics.panel.BoardElement;
import stellics.panel.ElementFactory;

public class StorageBoard extends BaseIntelPlugin {

    private FilterManager filterManager;

    public static StorageBoard getInstance() {
        IntelInfoPlugin intel = Global.getSector().getIntelManager().getFirstIntel(StorageBoard.class);
        if (intel == null) {
            StorageBoard board = new StorageBoard();
            Global.getSector().getIntelManager().addIntel(board);
            Global.getSector().addScript(board);
        }
        return (StorageBoard) intel;
    }

    public StorageBoard() {
        filterManager = new FilterManager();
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        // TODO
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        int cargoCount = StorageHelper.getAllCargoCount();
        int shipCount = StorageHelper.getAllShipCount();
        int storageCount = StorageHelper.getStorageCount();
        info.addPara("Stellar Logistics", getTitleColor(mode), 0);
        info.addPara(getDescription(cargoCount, shipCount, storageCount), 1f, getBulletColorForMode(mode),
                Misc.getHighlightColor(), String.valueOf(cargoCount), String.valueOf(shipCount),
                String.valueOf(storageCount));
    }

    @Override
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        float leftWidth = 200;
        float rightWidth = width - leftWidth;
        ElementFactory factory = new ElementFactory(this, panel, height);
        BoardElement displays = factory.getDisplayColumn(filterManager.getBoardView(), rightWidth);
        BoardElement controls = factory.getControlColumn(leftWidth);
        displays.render();
        controls.render();
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }

    @Override
    public String getIcon() {
        return Global.getSettings().getSpriteName("stellics", "board");
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add("Logistics");
        return tags;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_0;
    }

    public FilterManager getFilterManager() {
        return filterManager;
    }

    private String getDescription(int cargoCount, int shipCount, int storageCount) {
        if (storageCount == 0) {
            return "You don't have any storages.";
        }
        if (cargoCount == 0 && shipCount == 0) {
            return "You don't store anything in your storages.";
        }
        String items = cargoCount != 1 ? "s " : " ";
        String ships = shipCount != 1 ? "s " : " ";
        String locations = storageCount != 1 ? " between %s locations." : " in one location.";
        return "You have %s item" + items + "and %s ship" + ships + "stored" + locations;
    }
}
