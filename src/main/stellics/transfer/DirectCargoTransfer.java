package stellics.transfer;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import stellics.helper.MonthlyReportHelper;

public class DirectCargoTransfer implements CargoTransferAction {

    private CargoAPI sourceCargo;
    private CargoAPI targetCargo;
    private SubmarketAPI storage;

    public DirectCargoTransfer(CargoAPI sourceCargo, CargoAPI targetCargo, SubmarketAPI storage) {
        this.sourceCargo = sourceCargo;
        this.targetCargo = targetCargo;
        this.storage = storage;
    }

    @Override
    public CargoAPI getSource() {
        return sourceCargo;
    }

    @Override
    public void transfer(CargoAPI cargo) {
        filterInvalidCargo(cargo);
        cargo.removeEmptyStacks();
        if (cargo.isEmpty()) {
            return;
        }
        MonthlyReportHelper.registerTransfer(storage.getMarket(), cargo);
        targetCargo.addAll(cargo);
    }

    private void filterInvalidCargo(CargoAPI cargo) {
        for (CargoStackAPI c : cargo.getStacksCopy()) {
            if (storage.isIllegalOnSubmarket(c, null)) {
                cargo.removeStack(c);
                sourceCargo.addFromStack(c);
            }
        }
    }
}
