package stellics.intel;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class ShipTransferIntel extends TransferIntel {

    public ShipTransferIntel(String toOrFrom, int cargoCount, float totalCost, MarketAPI market) {
        super(toOrFrom, cargoCount, totalCost, market);
    }

    @Override
    protected String getEntity(int transferCount) {
        if (transferCount == 1) {
            return "ship";
        }
        return "ships";
    }
}
