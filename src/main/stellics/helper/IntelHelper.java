package stellics.helper;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stellics.intel.CargoTransferIntel;
import stellics.intel.CourierIntel;
import stellics.intel.ShipTransferIntel;

public class IntelHelper {

    public static void fireIntel(String toOrFrom, List<FleetMemberAPI> ships, MarketAPI market, float distance) {
        int shipsCount = ships.size();
        float shipsCost = MonthlyReportHelper.getUpkeep(FleetMembersHelper.calculateShipUpkeep(ships), distance);
        new ShipTransferIntel(toOrFrom, shipsCount, shipsCost, market);
    }

    public static void fireIntel(String toOrFrom, CargoAPI cargo, MarketAPI market, float distance) {
        int cargoCount = CargoHelper.calculateCargoQuantity(cargo);
        float cargoCost = MonthlyReportHelper.getUpkeep(CargoHelper.calculateCargoUpkeep(cargo), distance);
        new CargoTransferIntel(toOrFrom, cargoCount, cargoCost, market);
    }

    public static void recreate() {
        IntelManagerAPI intelManager = Global.getSector().getIntelManager();
        removeAll(intelManager);
        addAll(intelManager);
    }

    private static void removeAll(IntelManagerAPI intelManager) {
        IntelInfoPlugin intel = intelManager.getFirstIntel(CourierIntel.class);
        while (intel != null) {
            intelManager.removeIntel(intel);
            intel = intelManager.getFirstIntel(CourierIntel.class);
        }
    }

    private static void addAll(IntelManagerAPI intelManager) {
        for (SubmarketAPI submarket : StorageHelper.getAllWithAccess()) {
            IntelInfoPlugin plugin = new CourierIntel(submarket);
            intelManager.addIntel(plugin, true);
        }
    }
}
