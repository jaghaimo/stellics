package stellics.helper;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class CargoHelper {

    public static int calculateCargoQuantity(CargoAPI cargo) {
        float cargoSpace = 0;
        for (CargoStackAPI stack : cargo.getStacksCopy()) {
            cargoSpace += stack.getSize();
        }
        return (int) cargoSpace;
    }

    public static int calculateCargoUpkeep(CargoAPI cargo) {
        return ConfigHelper.warehouseCargoCost() * calculateCargoSpace(cargo);
    }

    public static int calculateCargoSpace(CargoAPI cargo) {
        float cargoSpace = 0;
        for (CargoStackAPI stack : cargo.getStacksCopy()) {
            cargoSpace += stack.getCargoSpace();
        }
        return (int) cargoSpace;
    }

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

    public static void replaceCargoStacks(CargoAPI cargo, List<CargoStackAPI> cargoStacks) {
        cargo.clear();
        for (CargoStackAPI cargoStack : cargoStacks) {
            cargo.addFromStack(cargoStack);
        }
        cargo.sort();
    }
}
