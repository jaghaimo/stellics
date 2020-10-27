package stellics.dialog;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.transfer.CargoTransferAction;
import stellics.transfer.DirectCargoTransfer;
import stellics.transfer.DirectShipTransfer;
import stellics.transfer.ShipTransferAction;

public class DialogFactory {

    public static InteractionDialogPlugin get(DialogOption option, SubmarketAPI storage, IntelUIAPI ui,
            IntelInfoPlugin plugin) {
        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();
        CargoAPI storageCargo = storage.getCargo();
        CargoTransferAction cargoTransfer;
        ShipTransferAction shipTransfer;

        switch (option) {
            case GLOBAL_CARGO:
                // TODO finish me

            case REQUEST_CARGO:
                cargoTransfer = new DirectCargoTransfer(storageCargo, playerFleet.getCargo(), storage);
                return new CargoDialog(option, cargoTransfer, ui, plugin);

            case TRANSFER_CARGO:
                cargoTransfer = new DirectCargoTransfer(playerFleet.getCargo(), storageCargo, storage);
                return new CargoDialog(option, cargoTransfer, ui, plugin);

            case GLOBAL_SHIPS:
                // TODO finish me

            case REQUEST_SHIPS:
                shipTransfer = new DirectShipTransfer(storageCargo.getMothballedShips(), playerFleet.getFleetData(),
                        storage);
                return new FleetDialog(option, shipTransfer, ui, plugin);

            case TRANSFER_SHIPS:
                shipTransfer = new DirectShipTransfer(playerFleet.getFleetData(), storageCargo.getMothballedShips(),
                        storage);
                return new FleetDialog(option, shipTransfer, ui, plugin);
        }

        return new DummyDialog(ui, plugin);
    }
}
