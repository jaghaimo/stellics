package stellics.intel;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stellics.helper.StarSystemHelper;

public abstract class TransferIntel extends TransientIntel {

    private String toOrFrom;
    private int transferCount;
    private float transferCost;
    private MarketAPI market;

    public TransferIntel(String toOrFrom, int cargoCount, float totalCost, MarketAPI market) {
        this.toOrFrom = toOrFrom;
        this.transferCount = cargoCount;
        this.transferCost = totalCost;
        this.market = market;
        show();
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        String marketName = market.getName();
        String systemName = StarSystemHelper.getName(market.getStarSystem());
        String cost = Misc.getDGSCredits(transferCost);

        String title = "Stellar Logistics";
        String message = String.format("%d %s have been transfered %s %s in %s. The cost of this operation was %s.",
                transferCount, getEntity(transferCount), toOrFrom, marketName, systemName, cost);

        info.addPara(title, getTitleColor(mode), 0f);
        indent(info);
        info.addPara(message, 3f, getBulletColorForMode(mode), Misc.getHighlightColor(), marketName, systemName, cost);
        unindent(info);
    }

    @Override
    public String getIcon() {
        return Global.getSettings().getSpriteName("stellics", "transfer");
    }

    protected abstract String getEntity(int transferCount);
}
