package stellics;

import com.fs.starfarer.api.BaseModPlugin;

public class StellicsMod extends BaseModPlugin {

    @Override
    public void onNewGameAfterTimePass() {
        init();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        init();
        StorageBoard.removeInstance();
    }

    private void init() {
        CourierListener.getInstance();
        StellicsBoard.getInstance();
    }
}
