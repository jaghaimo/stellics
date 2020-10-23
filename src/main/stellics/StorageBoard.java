package stellics;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stellics.panel.LeftColumn;
import stellics.panel.MiddleColumn;
import stellics.panel.PanelElement;
import stellics.panel.RightColumn;

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
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        info.addPara("Stellar Logistics", getTitleColor(mode), 0);
        info.addPara("Provides single view into content of all storages. Will "
                + "request cargo and ships from appropriate storages and will "
                + "transfer cargo and ships to the nearest storage.", 1f);
    }

    @Override
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        float middleWidth = 100;
        float sideWidth = (width - 100) / 2;
        PanelElement leftColumn = new LeftColumn(this, panel, sideWidth, height);
        PanelElement middleColumn = new MiddleColumn(this, panel, middleWidth, height);
        PanelElement rightColumn = new RightColumn(this, panel, sideWidth, height);
        leftColumn.render(0, 0);
        middleColumn.render(width / 2, 0);
        rightColumn.render(width, 0);
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
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_0;
    }

    public FilterManager getFilterManager() {
        return filterManager;
    }
}
