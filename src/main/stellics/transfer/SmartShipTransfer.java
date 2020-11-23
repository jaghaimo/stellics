package stellics.transfer;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stellics.helper.StorageHelper;

public class SmartShipTransfer extends IntelAwareTransfer implements ShipTransferAction {

    private List<FleetMemberAPI> filteredShips;
    private FleetDataAPI playerFleet;

    public SmartShipTransfer(List<FleetMemberAPI> filteredShips) {
        this.filteredShips = filteredShips;
        this.playerFleet = Global.getSector().getPlayerFleet().getFleetData();
    }

    @Override
    public List<FleetMemberAPI> getSource() {
        return filteredShips;
    }

    @Override
    public void transfer(List<FleetMemberAPI> fleet) {
        List<SubmarketAPI> storages = StorageHelper.getAllWithAccess();
        for (SubmarketAPI storage : storages) {
            transfer(fleet, storage);
        }
    }

    private void transfer(List<FleetMemberAPI> fleet, SubmarketAPI storage) {
        List<FleetMemberAPI> transferredShips = new ArrayList<>();
        FleetDataAPI storageFleet = storage.getCargo().getMothballedShips();
        for (FleetMemberAPI ship : fleet) {
            if (!storageFleet.getMembersListCopy().contains(ship)) {
                continue;
            }
            storageFleet.removeFleetMember(ship);
            playerFleet.addFleetMember(ship);
            transferredShips.add(ship);
        }
        fireIntel(transferredShips, storage.getMarket());
    }

    @Override
    protected String getToOrFrom() {
        return "from";
    }
}
