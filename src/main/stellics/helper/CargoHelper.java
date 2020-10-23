package stellics.helper;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.util.Misc;

public class CargoHelper {

    private static enum CostType {
        CARGO, FLEET
    }

    public static int calculateCargoUpkeep(CargoAPI cargo) {
        int spaceUsed = calculateCargoSpace(cargo);

        return getCost(spaceUsed, CostType.CARGO);
    }

    public static int calculateCargoSpace(CargoAPI cargo) {
        float cargoSpace = 0;
        for (CargoStackAPI stack : cargo.getStacksCopy()) {
            cargoSpace += stack.getCargoSpace();
        }

        return (int) cargoSpace;
    }

    public static int calculateShipUpkeep(List<FleetMemberAPI> fleet) {
        int spaceUsed = calculateShipSpace(fleet);

        return getCost(spaceUsed, CostType.FLEET);
    }

    public static int calculateShipSpace(List<FleetMemberAPI> fleet) {
        int fleetCost = 0;
        for (FleetMemberAPI ship : fleet) {
            fleetCost += ship.getHullSpec().getOrdnancePoints(null);
        }

        return fleetCost;
    }

    private static int getCost(int spaceUsed, CostType type) {
        String infix = Misc.ucFirst(type.name().toLowerCase());
        int cost = Global.getSettings().getInt("warehouse" + infix + "Upkeep");

        return spaceUsed * cost;
    }
}
