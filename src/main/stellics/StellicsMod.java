package stellics;

import com.fs.starfarer.api.BaseModPlugin;

public class StellicsMod extends BaseModPlugin {

    @Override
    public void onNewGameAfterTimePass() {
        CourierListener.getInstance();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        CourierListener.getInstance();
    }
}
