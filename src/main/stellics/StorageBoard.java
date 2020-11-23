package stellics;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

@Deprecated
public class StorageBoard extends BaseIntelPlugin {

    @Override
    public boolean isEnded() {
        return true;
    }
}
