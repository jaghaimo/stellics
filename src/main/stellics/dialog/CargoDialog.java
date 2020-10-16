package stellics.dialog;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoPickerListener;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stellics.helper.MonthlyReportHelper;

public class CargoDialog extends IntelAwareDialog implements CargoPickerListener {

    private DialogOption option;
    private SubmarketAPI storage;
    private CargoAPI sourceCargo;
    private CargoAPI targetCargo;

    public CargoDialog(DialogOption option, SubmarketAPI storage, CargoAPI sourceCargo, CargoAPI targetCargo,
            IntelUIAPI ui, IntelInfoPlugin plugin) {
        super(ui, plugin);
        this.option = option;
        this.storage = storage;
        this.sourceCargo = sourceCargo;
        this.targetCargo = targetCargo;
    }

    @Override
    public void cancelledCargoSelection() {
        dismiss();
    }

    @Override
    public void pickedCargo(CargoAPI cargo) {
        cargo.removeEmptyStacks();
        filterInvalidCargo(cargo);
        if (cargo.isEmpty()) {
            dismiss();
            return;
        }
        MonthlyReportHelper.registerTransfer(storage, cargo);
        targetCargo.addAll(cargo);
        refresh();
        dismiss();
    }

    @Override
    public void recreateTextPanel(TooltipMakerAPI panel, CargoAPI cargo, CargoStackAPI pickedUp,
            boolean pickedUpFromSource, CargoAPI combined) {
    }

    @Override
    protected void show(InteractionDialogAPI dialog) {
        dialog.showCargoPickerDialog(option.getTitle(), option.getAction(), "Cancel", false, 0f, sourceCargo, this);
    }

    private void filterInvalidCargo(CargoAPI cargo) {
        for (CargoStackAPI c : cargo.getStacksCopy()) {
            if (storage.isIllegalOnSubmarket(c, null)) {
                cargo.removeStack(c);
                sourceCargo.addFromStack(c);
            }
        }
    }
}
