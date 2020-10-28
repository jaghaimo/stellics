package stellics.button;

import java.awt.Color;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stellics.StorageBoard;
import stellics.dialog.CargoDialog;
import stellics.dialog.DialogOption;
import stellics.dialog.IntelAwareDialog;
import stellics.filter.FilterFactory;
import stellics.helper.StorageHelper;
import stellics.transfer.CargoTransferAction;
import stellics.transfer.SmartCargoTransfer;

public class RequestCargo extends Button {

    public RequestCargo() {
        super("Request Cargo");
    }

    @Override
    public Color getColor() {
        return Misc.getButtonTextColor();
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        FilterFactory filterFactory = board.getFilterFactory();
        CargoAPI cargo = StorageHelper.getAllCargo(filterFactory.getCargoStackFilters());
        CargoTransferAction cargoTransfer = new SmartCargoTransfer(cargo);
        IntelAwareDialog cargoDialog = new CargoDialog(DialogOption.SMART_CARGO, cargoTransfer, ui, board);
        ui.showDialog(null, cargoDialog);
    }
}
