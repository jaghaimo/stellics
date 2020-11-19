package stellics;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

@Deprecated
public class StorageBoard extends BaseIntelPlugin {

    public static void removeInstance() {
        IntelManagerAPI intelManager = Global.getSector().getIntelManager();
        IntelInfoPlugin intel = intelManager.getFirstIntel(StorageBoard.class);
        if (intel != null) {
            intelManager.removeIntel(intel);
        }
    }
}
