package stellics.helper;

import com.fs.starfarer.api.Global;

public class ConfigHelper {

    public static boolean allowTransfer() {
        return Global.getSettings().getBoolean("allowTransfer");
    }

    public static int warehouseCargoCost() {
        return Global.getSettings().getInt("courierCargoTransferCostPerUnit");
    }

    public static int warehouseFleetCost() {
        return Global.getSettings().getInt("courierShipTransferCostPerOP");
    }
}
