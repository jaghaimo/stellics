package stellics.transfer;

import java.util.LinkedHashMap;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

public class CargoTransferMap extends LinkedHashMap<SubmarketAPI, CargoAPI> {

    private static final long serialVersionUID = 1L;

    public void addCargoStack(SubmarketAPI key, CargoStackAPI cargoStack) {
        if (cargoStack.isNull()) {
            return;
        }
        CargoAPI cargo = getOrNew(key);
        cargo.addFromStack(cargoStack);
    }

    private CargoAPI getOrNew(SubmarketAPI key) {
        if (containsKey(key)) {
            return get(key);
        }

        CargoAPI cargo = Global.getFactory().createCargo(true);
        put(key, cargo);
        return cargo;
    }
}
