package stellics;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;

public class StellicsMod extends BaseModPlugin {

    @Override
    public void onNewGameAfterTimePass() {
        init();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        removeDeprecated();
        init();
    }

    private void init() {
        CourierListener.getInstance();
        StellicsBoard.getInstance();
    }

    private void removeDeprecated() {
        IntelManagerAPI intelManager = Global.getSector().getIntelManager();
        intelManager.removeAllThatShouldBeRemoved();
    }
}
