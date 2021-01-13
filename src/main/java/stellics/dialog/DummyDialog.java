package stellics.dialog;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;

public class DummyDialog extends IntelAwareDialog {

    public DummyDialog(IntelUIAPI ui, IntelInfoPlugin plugin) {
        super(ui, plugin);
    }
}
