package stellics.helper;

import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMembersHelper {

    public static int calculateShipQuantity(List<FleetMemberAPI> fleet) {
        return fleet.size();
    }

    public static int calculateShipUpkeep(List<FleetMemberAPI> fleet) {
        return ConfigHelper.warehouseFleetCost() * calculateShipSpace(fleet);
    }

    public static int calculateShipSpace(List<FleetMemberAPI> fleet) {
        int fleetCost = 0;
        for (FleetMemberAPI ship : fleet) {
            fleetCost += ship.getHullSpec().getOrdnancePoints(null);
        }
        return fleetCost;
    }
}
