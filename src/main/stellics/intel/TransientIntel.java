package stellics.intel;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

public abstract class TransientIntel extends BaseIntelPlugin {

    protected void show() {
        IntelManagerAPI intelManager = Global.getSector().getIntelManager();
        intelManager.addIntel(this);
        intelManager.removeIntel(this);
    }
}
