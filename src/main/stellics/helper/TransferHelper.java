package stellics.helper;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.SubmarketPlugin.TransferAction;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;

import stellics.filter.IsNotMarket;

public class TransferHelper {

    private static final String KEY = "$stellicsDefaultId";

    public static MarketAPI getMarket() {
        String marketId = Global.getSector().getMemory().getString(KEY);
        if (marketId == null) {
            return null;
        }

        return Global.getSector().getEconomy().getMarket(marketId);
    }

    public static void transferAll(MarketAPI market) {
        SubmarketAPI storage = market.getSubmarket(Submarkets.SUBMARKET_STORAGE);
        List<SubmarketAPI> submarkets = StorageHelper.getAllWithAccess();
        CollectionHelper.reduce(submarkets, new IsNotMarket(market));
        for (SubmarketAPI submarket : submarkets) {
            float distance = DistanceHelper.getDistanceLY(market.getPrimaryEntity(),
                    submarket.getMarket().getPrimaryEntity());
            transferShips(submarket, storage, distance);
            transferCargo(submarket, storage, distance);
        }
    }

    private static void transferShips(SubmarketAPI sourceStorage, SubmarketAPI targetStorage, float distance) {
        FleetDataAPI sourceFleet = sourceStorage.getCargo().getMothballedShips();
        FleetDataAPI targetFleet = targetStorage.getCargo().getMothballedShips();
        List<FleetMemberAPI> ships = sourceFleet.getMembersListCopy();
        if (!ships.isEmpty()) {
            transferAllShips(ships, sourceFleet, targetFleet);
            MonthlyReportHelper.registerShipsTransfer(targetStorage.getMarket(), ships, distance);
        }
    }

    private static void transferCargo(SubmarketAPI sourceStorage, SubmarketAPI targetStorage, float distance) {
        CargoAPI sourceCargo = sourceStorage.getCargo();
        CargoAPI targetCargo = targetStorage.getCargo();
        CargoAPI validCargo = getValidCargo(sourceCargo, targetStorage);
        if (!validCargo.isEmpty()) {
            sourceCargo.removeAll(validCargo);
            targetCargo.addAll(validCargo);
            MonthlyReportHelper.registerCargoTransfer(targetStorage.getMarket(), validCargo, distance);
        }
    }

    private static void transferAllShips(List<FleetMemberAPI> ships, FleetDataAPI sourceFleet,
            FleetDataAPI targetFleet) {
        for (FleetMemberAPI ship : ships) {
            sourceFleet.removeFleetMember(ship);
            targetFleet.addFleetMember(ship);
        }
    }

    private static CargoAPI getValidCargo(CargoAPI sourceCargo, SubmarketAPI targetStorage) {
        CargoAPI validCargo = sourceCargo.createCopy();
        for (CargoStackAPI cargoStack : validCargo.getStacksCopy()) {
            if (targetStorage.isIllegalOnSubmarket(cargoStack, TransferAction.PLAYER_SELL)) {
                validCargo.removeStack(cargoStack);
            }
        }
        return validCargo;
    }
}
