package stellics;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MonthlyReport.FDNode;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipCreator;

public class CourierTooltipCreator implements TooltipCreator {

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, Object tooltipParam) {
        FDNode node = (FDNode) tooltipParam;
        FactionAPI faction = Global.getSector().getPlayerFaction();
        if (node.custom instanceof MarketAPI) {
            MarketAPI market = (MarketAPI) node.custom;
            faction = market.getFaction();
            tooltip.addTitle(market.getName());
        }
        tooltip.addPara("Courier services for transferring cargo and ships.", 10f);
        if (node.custom2 instanceof CargoAPI) {
            CargoAPI cargo = (CargoAPI) node.custom2;
            List<CargoStackAPI> cargoStacks = cargo.getStacksCopy();
            List<FleetMemberAPI> ships = cargo.getMothballedShips().getMembersListCopy();
            tooltip.addSectionHeading("Cargo transferred", faction.getBaseUIColor(), faction.getDarkUIColor(),
                    Alignment.MID, 10f);
            tooltip.showCargo(cargo, cargoStacks.size(), false, 10f);
            tooltip.addSectionHeading("Ships transferred", faction.getBaseUIColor(), faction.getDarkUIColor(),
                    Alignment.MID, 10f);
            tooltip.showShips(ships, ships.size(), false, 10f);
        }
    }

    @Override
    public float getTooltipWidth(Object tooltipParam) {
        return 450;
    }

    @Override
    public boolean isTooltipExpandable(Object tooltipParam) {
        return false;
    }

}
