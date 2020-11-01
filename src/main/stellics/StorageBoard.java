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

import stellics.button.ButtonHandler;
import stellics.button.ButtonManager;
import stellics.filter.FilterFactory;
import stellics.helper.StorageHelper;
import stellics.panel.BoardElement;
import stellics.panel.ElementFactory;

public class StorageBoard extends BaseIntelPlugin {

    public enum Pane {
        Cargo, Ships;
    }

    private Pane activePane;
    private ButtonManager buttonManager;
    private FilterFactory filterFactory;

    public static StorageBoard getInstance() {
        IntelInfoPlugin intel = Global.getSector().getIntelManager().getFirstIntel(StorageBoard.class);
        if (intel == null) {
            StorageBoard board = new StorageBoard();
            Global.getSector().getIntelManager().addIntel(board);
            Global.getSector().addScript(board);
        }
        return (StorageBoard) intel;
    }

    private StorageBoard() {
        activePane = Pane.Cargo;
        buttonManager = new ButtonManager();
        filterFactory = new FilterFactory(buttonManager);
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        if (buttonId instanceof ButtonHandler) {
            ButtonHandler handler = (ButtonHandler) buttonId;
            handler.handle(this, ui);
        }
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
        float spacer = 20;
        float controlWidth = 180;
        float displayWidth = width - controlWidth - spacer;
        ElementFactory factory = new ElementFactory(this, panel, height);
        BoardElement controls = factory.getControlColumn(controlWidth);
        BoardElement displays = factory.getDisplayColumn(displayWidth);
        controls.render();
        displays.render();
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

    public Pane getActivePane() {
        return activePane;
    }

    public ButtonManager getButtonManager() {
        return buttonManager;
    }

    public FilterFactory getFilterFactory() {
        return filterFactory;
    }

    public void togglePane() {
        activePane = Pane.Cargo.equals(activePane) ? Pane.Ships : Pane.Cargo;
    }

    private String getDescription(int cargoCount, int shipCount, int storageCount) {
        if (storageCount == 0) {
            return "You don't have any storages.";
        }
        if (cargoCount == 0 && shipCount == 0) {
            return "You don't store anything in your storages.";
        }
        return getFormattedDescription(cargoCount, shipCount, storageCount);
    }

    private String getFormattedDescription(int cargoCount, int shipCount, int storageCount) {
        String items = cargoCount != 1 ? "s " : " ";
        String ships = shipCount != 1 ? "s " : " ";
        String locations = storageCount != 1 ? " between %s locations." : " in one location.";
        return "You have %s item" + items + "and %s ship" + ships + "stored" + locations;
    }
}
