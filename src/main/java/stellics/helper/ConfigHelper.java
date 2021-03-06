package stellics.helper;

import com.fs.starfarer.api.Global;

public class ConfigHelper {

    public static boolean canTransferBetweenStorages() {
        return Global.getSettings().getBoolean("enableTransferBetweenStorages");
    }

    public static boolean canTransferWhileTraveling() {
        return Global.getSettings().getBoolean("enableTransferWhileTraveling");
    }

    public static int warehouseCargoCost() {
        return Global.getSettings().getInt("courierCargoTransferCostPerUnit");
    }

    public static int warehouseFleetCost() {
        return Global.getSettings().getInt("courierShipTransferCostPerOP");
    }
}
