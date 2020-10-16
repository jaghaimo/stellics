package stellics.dialog;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;

public class DialogFactory {

    public static InteractionDialogPlugin get(DialogOption option, SubmarketAPI storage, IntelUIAPI ui,
            IntelInfoPlugin plugin) {
        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();
        CargoAPI storageCargo = storage.getCargo();

        switch (option) {
            case REQUEST_CARGO:
                return new CargoDialog(option, storage, storageCargo, playerFleet.getCargo(), ui, plugin);

            case REQUEST_SHIPS:
                return new FleetDialog(option, storage, storageCargo.getMothballedShips(), playerFleet.getFleetData(),
                        ui, plugin);

            case TRANSFER_CARGO:
                return new CargoDialog(option, storage, playerFleet.getCargo(), storageCargo, ui, plugin);

            case TRANSFER_SHIPS:
                return new FleetDialog(option, storage, playerFleet.getFleetData(), storageCargo.getMothballedShips(),
                        ui, plugin);
        }

        return new DummyDialog(ui, plugin);
    }
}
