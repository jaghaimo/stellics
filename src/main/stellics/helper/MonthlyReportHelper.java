package stellics.helper;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MonthlyReport;
import com.fs.starfarer.api.campaign.econ.MonthlyReport.FDNode;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;

import stellics.CourierTooltipCreator;

public class MonthlyReportHelper {

    public static void registerTransfer(MarketAPI market, CargoAPI cargo) {
        FDNode transferNode = getTransferNode(market);
        addToCargo(transferNode, cargo);
        transferNode.upkeep += getUpkeep(CargoHelper.calculateCargoUpkeep(cargo), market);
    }

    public static void registerTransfer(MarketAPI market, List<FleetMemberAPI> ships) {
        FDNode transferNode = getTransferNode(market);
        addToCargo(transferNode, ships);
        transferNode.upkeep += getUpkeep(FleetMembersHelper.calculateShipUpkeep(ships), market);
    }

    private static FDNode getTransferNode(MarketAPI market) {
        MonthlyReport report = SharedData.getData().getCurrentReport();
        FDNode storageNode = report.getNode(MonthlyReport.STORAGE);
        FDNode transferNode = report.getNode(storageNode, "courier_" + market.getId());
        transferNode.name = market.getName() + " (courier services)";
        transferNode.tooltipCreator = new CourierTooltipCreator();
        transferNode.custom = market;
        return transferNode;
    }

    private static void addToCargo(FDNode node, CargoAPI cargo) {
        CargoAPI nodeCargo = getCargo(node);
        nodeCargo.addAll(cargo);
        node.custom2 = nodeCargo;
    }

    private static void addToCargo(FDNode node, List<FleetMemberAPI> ships) {
        CargoAPI nodeCargo = getCargo(node);
        for (FleetMemberAPI ship : ships) {
            nodeCargo.getMothballedShips().addFleetMember(ship);
        }
        node.custom2 = nodeCargo;
    }

    private static CargoAPI getCargo(FDNode node) {
        if (!(node.custom2 instanceof CargoAPI)) {
            CargoAPI cargo = Global.getFactory().createCargo(true);
            cargo.initMothballedShips(Factions.PLAYER);
            node.custom2 = cargo;
        }
        return (CargoAPI) node.custom2;
    }

    private static float getUpkeep(int upkeep, MarketAPI market) {
        return upkeep * (1 + DistanceHelper.getDistanceToPlayerLY(market.getPrimaryEntity()));
    }
}
