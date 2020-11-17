package stellics.rules;

import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.PaginatedOptions;
import com.fs.starfarer.api.util.Misc.Token;

import stellics.helper.StorageHelper;

public class StellicsGetMarkets extends PaginatedOptions {

    private OptionPanelAPI options;

    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Token> params,
            Map<String, MemoryAPI> memoryMap) {

        super.execute(ruleId, dialog, params, memoryMap);
        options = dialog.getOptionPanel();
        clearAllOptions();
        addAllMarkets();
        addOptionAllPages("Cut the comm link", "cutCommLink");
        showOptions();
        return true;
    }

    private void clearAllOptions() {
        options.clearOptions();
    }

    private void addAllMarkets() {
        for (SubmarketAPI submarket : StorageHelper.getAllWithAccess()) {
            MarketAPI market = submarket.getMarket();
            String marketId = market.getId();
            addOption("Select " + market.getName(), marketId);
            setTooltipForMarket(marketId, market);
        }
    }

    private void setTooltipForMarket(String marketId, MarketAPI market) {
        String tooltipString = "";
        options.setTooltip(marketId, tooltipString);
    }
}
