package stellics.helper;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import stellics.CourierIntel;

public class IntelHelper {

    public static void recreate() {
        IntelManagerAPI intelManager = Global.getSector().getIntelManager();
        removeAll(intelManager);
        addAll(intelManager);
    }

    private static void removeAll(IntelManagerAPI intelManager) {
        IntelInfoPlugin intel = intelManager.getFirstIntel(CourierIntel.class);
        while (intel != null) {
            intelManager.removeIntel(intel);
            intel = intelManager.getFirstIntel(CourierIntel.class);
        }
    }

    private static void addAll(IntelManagerAPI intelManager) {
        for (SubmarketAPI submarket : StorageHelper.getAllWithAccess()) {
            IntelInfoPlugin plugin = new CourierIntel(submarket);
            intelManager.addIntel(plugin, true);
        }
    }
}
