package stellics.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class IsNotCommodity implements CargoStackFilter {

    @Override
    public boolean accept(CargoStackAPI object) {
        return !object.isCommodityStack();
    }
}
