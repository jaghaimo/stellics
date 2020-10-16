package stellics.dialog;

import java.util.Map;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;

public class IntelAwareDialog implements InteractionDialogPlugin {

    private InteractionDialogAPI dialog;
    private IntelUIAPI ui;
    private IntelInfoPlugin plugin;

    public IntelAwareDialog(IntelUIAPI ui, IntelInfoPlugin plugin) {
        this.ui = ui;
        this.plugin = plugin;
    }

    @Override
    public void advance(float amount) {
    }

    @Override
    public void backFromEngagement(EngagementResultAPI battleResult) {
    }

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }

    @Override
    public void init(InteractionDialogAPI dialog) {
        this.dialog = dialog;
        // FIXME option to exit is not needed in >0.9.1a
        dialog.getOptionPanel().addOption("Exit", null);
        show(dialog);
    }

    @Override
    public void optionMousedOver(String optionText, Object optionData) {
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        dismiss();
    }

    protected void dismiss() {
        dialog.dismiss();
    }

    protected void refresh() {
        ui.updateUIForItem(plugin);
    }

    protected void show(InteractionDialogAPI dialog) {
        dismiss();
    }
}
