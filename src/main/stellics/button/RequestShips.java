package stellics.button;

import java.awt.Color;
import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stellics.StorageBoard;
import stellics.dialog.DialogOption;
import stellics.dialog.FleetDialog;
import stellics.dialog.IntelAwareDialog;
import stellics.filter.FilterFactory;
import stellics.helper.StorageHelper;
import stellics.transfer.ShipTransferAction;
import stellics.transfer.SmartShipTransfer;

public class RequestShips extends Button {

    public RequestShips() {
        super("Request Ships");
    }

    @Override
    public Color getColor() {
        return Misc.getButtonTextColor();
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        FilterFactory filterFactory = board.getFilterFactory();
        List<FleetMemberAPI> ships = StorageHelper.getAllShips(filterFactory.getFleetMemberFilters());
        ShipTransferAction shipTransfer = new SmartShipTransfer(ships);
        IntelAwareDialog fleetDialog = new FleetDialog(DialogOption.SMART_SHIPS, shipTransfer, ui, board);
        ui.showDialog(null, fleetDialog);
    }
}
