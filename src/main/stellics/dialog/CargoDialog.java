package stellics.dialog;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoPickerListener;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stellics.transfer.CargoTransferAction;

public class CargoDialog extends IntelAwareDialog implements CargoPickerListener {

    private DialogOption option;
    private CargoTransferAction cargoTransfer;

    public CargoDialog(DialogOption option, CargoTransferAction cargoTransfer, IntelUIAPI ui, IntelInfoPlugin plugin) {
        super(ui, plugin);
        this.option = option;
        this.cargoTransfer = cargoTransfer;
    }

    @Override
    public void cancelledCargoSelection() {
        dismiss();
    }

    @Override
    public void pickedCargo(CargoAPI cargo) {
        cargoTransfer.transfer(cargo);
        refresh();
        dismiss();
    }

    @Override
    public void recreateTextPanel(TooltipMakerAPI panel, CargoAPI cargo, CargoStackAPI pickedUp,
            boolean pickedUpFromSource, CargoAPI combined) {
    }

    @Override
    protected void show(InteractionDialogAPI dialog) {
        dialog.showCargoPickerDialog(option.getTitle(), option.getAction(), "Cancel", false, 0f,
                cargoTransfer.getSource(), this);
    }
}
