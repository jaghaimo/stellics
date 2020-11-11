package stellics;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;

import stellics.helper.IntelHelper;
import stellics.helper.PersonHelper;
import stellics.helper.TransferHelper;

public class CourierListener implements EconomyTickListener {

    public static CourierListener getInstance() {
        ListenerManagerAPI listenerManager = Global.getSector().getListenerManager();
        List<CourierListener> listeners = listenerManager.getListeners(CourierListener.class);
        if (listeners.isEmpty()) {
            CourierListener listener = new CourierListener();
            listenerManager.addListener(listener);
            return listener;
        }
        return listeners.get(0);
    }

    @Override
    public void reportEconomyTick(int iterIndex) {
        IntelHelper.recreate();
        PersonHelper.addMissing();
    }

    @Override
    public void reportEconomyMonthEnd() {
        TransferHelper.transferCargo();
        TransferHelper.trasnferShips();
    }
}
