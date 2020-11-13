package stellics.rules;

import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.MemKeys;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.util.Misc.Token;

@Deprecated
public class StellicsSetMarketName extends BaseCommandPlugin {

    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Token> params,
            Map<String, MemoryAPI> memoryMap) {

        MemoryAPI localMemory = memoryMap.get(MemKeys.LOCAL);
        String marketId = localMemory.getString("$stellicsDefaultId");
        MarketAPI market = Global.getSector().getEconomy().getMarket(marketId);
        String marketName = market.getName();
        localMemory.set("$stellicsDefaultName", marketName);
        return true;
    }
}
