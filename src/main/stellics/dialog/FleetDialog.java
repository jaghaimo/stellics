package stellics.dialog;

import java.util.List;

import com.fs.starfarer.api.campaign.FleetMemberPickerListener;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.transfer.ShipTransferAction;

public class FleetDialog extends IntelAwareDialog implements FleetMemberPickerListener {

    private DialogOption option;
    private ShipTransferAction transferAction;

    public FleetDialog(DialogOption option, ShipTransferAction transferAction, IntelUIAPI ui, IntelInfoPlugin plugin) {
        super(ui, plugin);
        this.option = option;
        this.transferAction = transferAction;
    }

    @Override
    public void cancelledFleetMemberPicking() {
        dismiss();
    }

    @Override
    public void pickedFleetMembers(List<FleetMemberAPI> fleet) {
        transferAction.transfer(fleet);
        refresh();
        dismiss();
    }

    @Override
    protected void show(InteractionDialogAPI dialog) {
        dialog.showFleetMemberPickerDialog(option.getTitle(), option.getAction(), "Cancel", 8, 12, 64f, true, true,
                transferAction.getSource(), this);
    }
}
