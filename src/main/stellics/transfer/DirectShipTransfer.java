package stellics.transfer;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stellics.helper.MonthlyReportHelper;

public class DirectShipTransfer implements ShipTransferAction {

    private FleetDataAPI sourceFleet;
    private FleetDataAPI targetFleet;
    private SubmarketAPI storage;

    public DirectShipTransfer(FleetDataAPI sourceFleet, FleetDataAPI targetFleet, SubmarketAPI storage) {
        this.sourceFleet = sourceFleet;
        this.targetFleet = targetFleet;
        this.storage = storage;
    }

    @Override
    public List<FleetMemberAPI> getSource() {
        return sourceFleet.getMembersInPriorityOrder();
    }

    @Override
    public void transfer(List<FleetMemberAPI> fleet) {
        List<FleetMemberAPI> fleetCopy = filterFlagship(fleet);
        if (fleetCopy.isEmpty()) {
            return;
        }
        MonthlyReportHelper.registerTransfer(storage.getMarket(), fleetCopy);
        transferShips(fleetCopy);
    }

    private List<FleetMemberAPI> filterFlagship(List<FleetMemberAPI> fleet) {
        List<FleetMemberAPI> fleetCopy = new ArrayList<FleetMemberAPI>(fleet);
        for (FleetMemberAPI f : fleet) {
            if (f.getCaptain().isPlayer()) {
                fleetCopy.remove(f);
            }
        }
        return fleetCopy;
    }

    private void transferShips(List<FleetMemberAPI> fleetMembers) {
        for (FleetMemberAPI fleetMember : fleetMembers) {
            fleetMember.setCaptain(null);
            sourceFleet.removeFleetMember(fleetMember);
            targetFleet.addFleetMember(fleetMember);
        }
    }
}
