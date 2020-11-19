package stellics;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;

import stellics.helper.ConfigHelper;
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
        if (ConfigHelper.canManage()) {
            PersonHelper.addMissing();
        } else {
            PersonHelper.removeAll();
        }
    }

    @Override
    public void reportEconomyMonthEnd() {
        if (!ConfigHelper.canManage()) {
            return;
        }
        MarketAPI market = TransferHelper.getMarket();
        if (market != null) {
            TransferHelper.transferAll(market);
        }
    }
}
