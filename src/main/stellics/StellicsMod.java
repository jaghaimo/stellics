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
    }

    private void init() {
        CourierListener.getInstance();
        StorageBoard.getInstance();
    }
}
