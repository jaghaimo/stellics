package stellics.rules;

import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.util.Misc.Token;

import stellics.helper.StorageHelper;

@Deprecated
public class StellicsGetMarkets extends BaseCommandPlugin {

    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Token> params,
            Map<String, MemoryAPI> memoryMap) {

        OptionPanelAPI options = dialog.getOptionPanel();
        options.clearOptions();
        for (SubmarketAPI submarket : StorageHelper.getAllWithAccess()) {
            MarketAPI market = submarket.getMarket();
            // TODO remove currently docked market
            // TODO add pagintation
            options.addOption("Select " + market.getName(), market.getId());
        }
        options.addOption("Cut the comm link", "cutCommLink");
        return true;
    }
}
