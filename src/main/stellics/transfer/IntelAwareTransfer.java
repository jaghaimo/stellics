package stellics.transfer;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stellics.helper.DistanceHelper;
import stellics.helper.IntelHelper;

public abstract class IntelAwareTransfer {

    public void fireIntel(CargoAPI cargo, MarketAPI market) {
        IntelHelper.fireIntel(getToOrFrom(), cargo, market,
                DistanceHelper.getDistanceToPlayerLY(market.getPrimaryEntity()));
    }

    public void fireIntel(List<FleetMemberAPI> ships, MarketAPI market) {
        IntelHelper.fireIntel(getToOrFrom(), ships, market,
                DistanceHelper.getDistanceToPlayerLY(market.getPrimaryEntity()));
    }

    protected abstract String getToOrFrom();
}
