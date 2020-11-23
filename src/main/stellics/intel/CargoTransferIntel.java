package stellics.intel;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class CargoTransferIntel extends TransferIntel {

    public CargoTransferIntel(String toOrFrom, int cargoCount, float totalCost, MarketAPI market) {
        super(toOrFrom, cargoCount, totalCost, market);
    }

    @Override
    protected String getEntity(int transferCount) {
        if (transferCount == 1) {
            return "item";
        }
        return "items";
    }
}
