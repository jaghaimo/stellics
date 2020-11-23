package stellics.rules;

import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.PaginatedOptions;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Misc.Token;

import stellics.helper.DistanceHelper;
import stellics.helper.StarSystemHelper;
import stellics.helper.StorageHelper;

public class StellicsGetMarkets extends PaginatedOptions {

    private OptionPanelAPI options;
    private List<SubmarketAPI> submarkets;

    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Token> params,
            Map<String, MemoryAPI> memoryMap) {

        super.execute(ruleId, dialog, params, memoryMap);
        options = dialog.getOptionPanel();
        submarkets = StorageHelper.getAllSortedWithAccess();
        clearAllOptions();
        addAllMarkets();
        addOptionAllPages("Cut the comm link", "cutCommLink");
        showOptions();
        configureTooltips();
        return true;
    }

    private void clearAllOptions() {
        options.clearOptions();
    }

    private void addAllMarkets() {
        for (SubmarketAPI submarket : submarkets) {
            MarketAPI market = submarket.getMarket();
            addOption("Select " + market.getName() + " in " + StarSystemHelper.getName(market.getStarSystem()),
                    market.getId());
        }
    }

    private void configureTooltips() {
        for (SubmarketAPI submarket : submarkets) {
            setTooltipForMarket(submarket.getMarket());
        }
    }

    private void setTooltipForMarket(MarketAPI market) {
        String marketId = market.getId();
        String factionName = market.getFaction().getDisplayName();
        String distance = String.format("%.2f", DistanceHelper.getDistanceToPlayerLY(market.getPrimaryEntity()));
        String tooltipString = String.format("This market is owned by %s and located %sLY from you.", factionName,
                distance);

        options.setTooltip(marketId, tooltipString);
        options.setTooltipHighlights(marketId, factionName, distance);
        options.setTooltipHighlightColors(marketId, market.getFaction().getColor(), Misc.getHighlightColor());
    }
}
