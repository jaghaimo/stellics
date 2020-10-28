package stellics.transfer;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stellics.helper.StorageHelper;

public class SmartShipTransfer implements ShipTransferAction {

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
        List<SubmarketAPI> storages = StorageHelper.getAllSortedWithAccess();
        for (FleetMemberAPI ship : fleet) {
            transfer(ship, storages);
        }
    }

    private void transfer(FleetMemberAPI ship, List<SubmarketAPI> storages) {
        for (SubmarketAPI storage : storages) {
            FleetDataAPI storageFleet = storage.getCargo().getMothballedShips();
            if (!storageFleet.getMembersListCopy().contains(ship)) {
                continue;
            }
            ShipTransferAction transferAction = new DirectShipTransfer(storageFleet, playerFleet, storage);
            transferAction.transfer(Arrays.asList(ship));
            return;
        }
    }
}
