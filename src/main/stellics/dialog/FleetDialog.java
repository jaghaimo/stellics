package stellics.dialog;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.FleetMemberPickerListener;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.helper.MonthlyReportHelper;

public class FleetDialog extends IntelAwareDialog implements FleetMemberPickerListener {

    private DialogOption option;
    private SubmarketAPI storage;
    private FleetDataAPI sourceFleet;
    private FleetDataAPI targetFleet;

    public FleetDialog(DialogOption option, SubmarketAPI storage, FleetDataAPI sourceFleet, FleetDataAPI targetFleet,
            IntelUIAPI ui, IntelInfoPlugin plugin) {
        super(ui, plugin);
        this.option = option;
        this.storage = storage;
        this.sourceFleet = sourceFleet;
        this.targetFleet = targetFleet;
    }

    @Override
    public void cancelledFleetMemberPicking() {
        dismiss();
    }

    @Override
    public void pickedFleetMembers(List<FleetMemberAPI> fleet) {
        List<FleetMemberAPI> fleetCopy = filterFlagship(fleet);
        if (fleetCopy.isEmpty()) {
            dismiss();
            return;
        }
        MonthlyReportHelper.registerTransfer(storage, fleetCopy);
        transferShips(fleetCopy);
        refresh();
        dismiss();
    }

    @Override
    protected void show(InteractionDialogAPI dialog) {
        dialog.showFleetMemberPickerDialog(option.getTitle(), option.getAction(), "Cancel", 8, 12, 64f, true, true,
                sourceFleet.getMembersInPriorityOrder(), this);
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
        for (FleetMemberAPI f : fleetMembers) {
            sourceFleet.removeFleetMember(f);
            targetFleet.addFleetMember(f);
        }
    }
}
